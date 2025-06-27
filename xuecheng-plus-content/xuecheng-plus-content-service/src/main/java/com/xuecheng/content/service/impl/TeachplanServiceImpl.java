package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    /*
     * 修改或创建课程计划
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {
        Long id = teachplanDto.getId();
        if (id != null) {     //id不为空，为修改课程计划
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto, teachplan);
            int i = teachplanMapper.updateById(teachplan);
            if(i<=0){
                XueChengPlusException.cast("修改失败");
            }
        } else {        //id为空，为增加课程计划
            int count=getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
            Teachplan teachplan = new Teachplan();
            teachplan.setCreateDate(LocalDateTime.now());
            teachplan.setOrderby(count+1);
            BeanUtils.copyProperties(teachplanDto, teachplan);
            int insert = teachplanMapper.insert(teachplan);
            if (insert <= 0) {
                XueChengPlusException.cast("新增失败");
            }
        }
    }

    private int getTeachplanCount(Long courseId, Long parentId) {
        LambdaQueryWrapper<Teachplan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teachplan::getCourseId, courseId);
        wrapper.eq(Teachplan::getParentid, parentId);
        return teachplanMapper.selectCount(wrapper);
    }
}
