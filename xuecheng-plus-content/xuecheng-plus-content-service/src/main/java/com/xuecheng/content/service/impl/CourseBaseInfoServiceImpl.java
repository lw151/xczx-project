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
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseBaseInfoService;
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

        if (StringUtils.isEmpty(addCourseDto.getName())) {
            XueChengPlusException.cast("课程名称不能为空");
        }
        if (addCourseDto.getPrice() <= 0) {
            XueChengPlusException.cast("价格错误，请重新输入");
        }
        if (addCourseDto.getOriginalPrice() < 0) {
            XueChengPlusException.cast("价格错误，请重新输入");
        }
        if(addCourseDto.getCharge()==null){
            XueChengPlusException.cast("内容请完善");
        }
        //向课程基本信息表course_base填入信息
        CourseBase newCourseBase = new CourseBase();
        BeanUtils.copyProperties(addCourseDto, newCourseBase);
        newCourseBase.setCompanyId(companyId);//设置机构ID
        newCourseBase.setCreateDate(LocalDateTime.now());//设置创建时间
        newCourseBase.setAuditStatus("202002");//默认提交状态为未提交
        newCourseBase.setStatus("203001");//发布状态默认为未发布
        if (courseBaseMapper.insert(newCourseBase) < 0) {
            throw new RuntimeException("新增课程失败");
        }
        //向课程营销表course_market填入数据
        CourseMarket newCourseMarket = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto, newCourseMarket);
        newCourseMarket.setId(newCourseBase.getId());
        int save = saveCourseMarket(newCourseMarket);
        if (save <= 0) {
            throw new RuntimeException("保存失败");
        }

        return getCourseBaseInfo(newCourseBase.getId());
    }

    /*保存课程营销数据*/
    private int saveCourseMarket(CourseMarket newCourseMarket) {
        String charge = newCourseMarket.getCharge();
        if (StringUtils.isEmpty(charge)) {
            throw new RuntimeException("收费规则为空");
        }
        if (charge.equals("201001")) {
            if (StringUtils.isEmpty(newCourseMarket.getPrice()) || newCourseMarket.getPrice() <= 0) {
                throw new RuntimeException("课程价格错误");
            }
        }

        CourseMarket selectCourseMarket = courseMarketMapper.selectById(newCourseMarket.getId());
        if (selectCourseMarket == null) {
            return courseMarketMapper.insert(newCourseMarket);
        } else {
            return courseMarketMapper.updateById(newCourseMarket);
        }
    }

    /*组装课程信息*/
    private CourseBaseInfoDto getCourseBaseInfo(Long courseId) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            return null;
        }
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);
        String mt = courseBaseInfoDto.getMt();
        String st = courseBaseInfoDto.getSt();
        CourseCategory c1 = lambdaQuery().eq(CourseCategory::getId, mt).one();
        String n1 = c1.getName();//大分类名称
        CourseCategory c2 = lambdaQuery().eq(CourseCategory::getId, st).one();
        String n2 = c2.getName();//小分类名称
        courseBaseInfoDto.setMtName(n1);
        courseBaseInfoDto.setStName(n2);
        return courseBaseInfoDto;
    }
}
