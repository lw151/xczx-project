package com.xuecheng.content;


import com.xuecheng.ContentApplication;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ContentApplication.class)
public class CourseCategoryMapperTests {

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;


    @Test
    public void test01(){
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes("1");
        courseCategoryTreeDtos.forEach(System.out::println);
    }

    @Test
    public void test02(){
        CourseCategory test1 = courseCategoryMapper.test();
        System.out.println(test1);
    }
}
