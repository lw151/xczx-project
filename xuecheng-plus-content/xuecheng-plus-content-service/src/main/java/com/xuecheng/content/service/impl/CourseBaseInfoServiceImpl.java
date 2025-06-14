package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.service.CourseMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CourseBaseInfoServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseBaseInfoService {

    @Autowired
    private CourseBaseMapper courseBaseMapper;
    @Autowired
    private CourseMarketMapper courseMarketMapper;
    @Autowired
    private CourseCategoryMapper courseCategoryMapper;
    @Autowired
    private CourseMarkService courseMarkService;

    /*
     * 分页查询课程信息
     * */
    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        //构建wrapper条件
        LambdaQueryWrapper<CourseBase> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(!StringUtils.isEmpty(queryCourseParamsDto.getPublishStatus()), CourseBase::getStatus,
                queryCourseParamsDto.getPublishStatus());
        wrapper.eq(!StringUtils.isEmpty(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus,
                queryCourseParamsDto.getAuditStatus());
        wrapper.like(!StringUtils.isEmpty(queryCourseParamsDto.getCourseName()), CourseBase::getName,
                queryCourseParamsDto.getCourseName());
        //构建page对象
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        //调用分页接口
        Page<CourseBase> courseBasePage = courseBaseMapper.selectPage(page, wrapper);
        //返回PageResult结果
        PageResult<CourseBase> pageResult = new PageResult<>();
        pageResult.setPage(courseBasePage.getCurrent());
        pageResult.setPageSizes(courseBasePage.getSize());
        pageResult.setItems(courseBasePage.getRecords());
        pageResult.setCounts(courseBasePage.getTotal());
        return pageResult;
    }

    /*
     * 新增课程
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseBaseInfoDto createCourseBaseInfo(Long companyId, AddCourseDto addCourseDto) {

        //向课程基本信息表course_base填入信息
        CourseBase newCourseBase = new CourseBase();
        BeanUtils.copyProperties(addCourseDto, newCourseBase);
        newCourseBase.setAuditStatus("202002");//默认提交状态为未提交
        newCourseBase.setStatus("203001");//发布状态默认为未发布
        newCourseBase.setCompanyId(companyId);//设置机构ID
        newCourseBase.setCreateDate(LocalDateTime.now());//设置创建时间
        int insert=courseBaseMapper.insert(newCourseBase);
        Long courseId=newCourseBase.getId();
        //向课程营销表course_market填入数据
        CourseMarket newCourseMarket = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto, newCourseMarket);
        newCourseMarket.setId(courseId);
        String charge = newCourseMarket.getCharge();
        if ("201001".equals(charge)) {
            Float price = addCourseDto.getPrice();
            if (price == null || price.floatValue() <= 0) {
                throw new RuntimeException("课程设置了收费，价格不能为空，且必须大于0");
            }
        }
        //  插入课程营销信息表
        int marketInsert = courseMarketMapper.insert(newCourseMarket);
        if (insert <= 0 || marketInsert <= 0) {
            throw new RuntimeException("新增课程基本信息失败");
        }
        // 返回添加的课程信息
        return getCourseBaseInfo(courseId);
    }

    /*保存课程营销数据*/
    private int saveCourseMarket(CourseMarket newCourseMarket) {
        String charge = newCourseMarket.getCharge();
        if (StringUtils.isEmpty(charge)) {
            throw new RuntimeException("收费规则为空");
        }
        if (charge.equals("201000") && newCourseMarket.getPrice() != 0.0f) {
            XueChengPlusException.cast("免费价格应该为0");
        }
        if (charge.equals("201001") && newCourseMarket.getPrice() == 0.0f) {
            XueChengPlusException.cast("收费价格不应为0");
        }
        CourseMarket selectCourseMarket = courseMarketMapper.selectById(newCourseMarket.getId());
        if (selectCourseMarket == null) {
            return courseMarketMapper.insert(newCourseMarket);
        } else {
            return courseMarketMapper.updateById(newCourseMarket);
        }
    }

    /*组装课程信息*/
    public CourseBaseInfoDto getCourseBaseInfo(Long courseId) {
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        // 1. 根据课程id查询课程基本信息
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null)
            return null;
        // 1.1 拷贝属性
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        // 2. 根据课程id查询课程营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        // 2.1 拷贝属性
        if (courseMarket != null)
            BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);
        // 3. 查询课程分类名称，并设置属性
        // 3.1 根据小分类id查询课程分类对象
        CourseCategory courseCategoryBySt = courseCategoryMapper.selectById(courseBase.getSt());
        // 3.2 设置课程的小分类名称
        courseBaseInfoDto.setStName(courseCategoryBySt.getName());
        // 3.3 根据大分类id查询课程分类对象
        CourseCategory courseCategoryByMt = courseCategoryMapper.selectById(courseBase.getMt());
        // 3.4 设置课程大分类名称
        courseBaseInfoDto.setMtName(courseCategoryByMt.getName());
        return courseBaseInfoDto;
    }

    /*修改课程信息*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto) {
        Long courseId = editCourseDto.getId();
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (!companyId.equals(courseBase.getCompanyId())) {
            XueChengPlusException.cast("机构ID不一致");
        }
        BeanUtils.copyProperties(editCourseDto, courseBase);
        // 查询课程营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        // 由于课程营销信息不是必填项，故这里先判断一下
        if (courseMarket == null) {
            courseMarket = new CourseMarket();
        }

        // 获取课程收费状态并设置
        String charge = editCourseDto.getCharge();
        courseMarket.setCharge(charge);
        // 如果课程收费，则判断价格是否正常
        if (charge.equals("201001")) {
            Float price = editCourseDto.getPrice();
            if (price <= 0) {
                XueChengPlusException.cast("课程设置了收费，价格不能为空，且必须大于0");
            }
        }
        // 对象拷贝
        BeanUtils.copyProperties(editCourseDto, courseMarket);
        courseMarket.setId(courseId);
        // 有则更新，无则插入
        courseMarkService.updateOrSave(courseMarket);
        return getCourseBaseInfo(courseId);
    }
}
