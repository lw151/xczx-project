package com.xuecheng.content;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.ContentApplication;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootTest(classes = ContentApplication.class)

public class CourseBaseMapperTests {

    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Test
    public void testCourseBaseMapper() {

        QueryCourseParamsDto queryCourseParamsDto = new QueryCourseParamsDto();
        queryCourseParamsDto.setCourseName("java");

        LambdaQueryWrapper<CourseBase> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(queryCourseParamsDto.getCourseName()),
                CourseBase::getName, queryCourseParamsDto.getCourseName());
        wrapper.eq(!StringUtils.isEmpty(queryCourseParamsDto.getAuditStatus()),
                CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());
        wrapper.eq(!StringUtils.isEmpty(queryCourseParamsDto.getPublishStatus()),
                CourseBase::getAuditStatus,queryCourseParamsDto.getPublishStatus());
        PageParams pageParams = new PageParams(1L,2L);
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, wrapper);
        List<CourseBase> items = pageResult.getRecords();
        long total = pageResult.getTotal();


        PageResult<CourseBase> courseBasePageResult = new PageResult<CourseBase>(items,total,
                pageParams.getPageNo(), pageParams.getPageSize());
        System.out.println(courseBasePageResult);


    }
}
