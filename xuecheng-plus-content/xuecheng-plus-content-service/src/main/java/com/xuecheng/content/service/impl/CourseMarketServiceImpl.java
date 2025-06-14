package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseMarketServiceImpl extends ServiceImpl<CourseMarketMapper,CourseMarket> implements CourseMarkService {
    @Autowired
    private CourseMarketMapper courseMarketMapper;

    @Override
    public void updateOrSave(CourseMarket courseMarket) {
        Long id = courseMarket.getId();
        if(courseMarketMapper.selectById(id)==null){
            courseMarketMapper.insert(courseMarket);
        }
        else{
            courseMarketMapper.updateById(courseMarket);
        }
    }
}
