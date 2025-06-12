package com.xuecheng.content;


import com.xuecheng.ContentApplication;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.po.CourseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ContentApplication.class)

public class CourseBaseMapperTests {
    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Test
    public void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(18);
        System.out.println(courseBase);
    }
}
