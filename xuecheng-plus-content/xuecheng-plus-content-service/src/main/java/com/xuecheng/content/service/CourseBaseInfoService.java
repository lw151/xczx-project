package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

public interface CourseBaseInfoService {
    /*课程查询接口*/
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /*新增课程接口*/
    CourseBaseInfoDto createCourseBaseInfo(Long companyId, AddCourseDto addCourseDto);

    /*根据ID查询课程信息*/
    CourseBaseInfoDto getCourseBaseInfo(Long courseId);

    /*修改课程信息*/
    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto);
    /*删除课程信息*/
    void delectCourse(Long companyId, Long courseId);
}
