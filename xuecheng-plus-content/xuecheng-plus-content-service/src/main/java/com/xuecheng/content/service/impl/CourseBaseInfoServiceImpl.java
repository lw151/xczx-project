package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Autowired
    CourseBaseMapper courseBaseMapper;

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
}
