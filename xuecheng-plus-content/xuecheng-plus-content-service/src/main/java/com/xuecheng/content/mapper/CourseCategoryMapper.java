package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

    //查询分类接口
    List<CourseCategoryTreeDto> selectTreeNodes(String id);

    @Select("select * from course_category limit 1")
    CourseCategory test();
}