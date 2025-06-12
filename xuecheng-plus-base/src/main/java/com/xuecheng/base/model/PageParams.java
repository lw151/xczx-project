package com.xuecheng.base.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class PageParams {
    //当前页码，默认为第1页
    private Long pageNo=1L;
    //每页显示记录数，默认为10条
    private Long pageSize=10L;

}
