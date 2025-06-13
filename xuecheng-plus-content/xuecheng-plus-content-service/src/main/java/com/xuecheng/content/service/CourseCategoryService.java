package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

public interface CourseCategoryService {

    /*分页列表展示*/
    List<CourseCategoryTreeDto> queryTreeDto(String id);

}
