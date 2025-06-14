package com.xuecheng.content.model.dto;

import com.xuecheng.base.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Validation;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "AddCourseDto", description = "新增课程基本信息")
public class AddCourseDto {

    @ApiModelProperty(value = "课程名称", required = true)
    @NotEmpty(message = "新增课程名称不能为空",groups = {ValidationGroups.Insert.class})
    @NotEmpty(message = "修改课程名称不能为空",groups = {ValidationGroups.Update.class})
    private String name;

    @ApiModelProperty(value = "适用人群", required = true)
    @Size(message = "适用人群过少",min = 5)
    private String users;

    @ApiModelProperty(value = "课程标签")
    @Size(message = "字数有误",min = 5,max = 200,groups = {ValidationGroups.Insert.class})
    private String tags;

    @ApiModelProperty(value = "大分类", required = true)
    private String mt;

    @ApiModelProperty(value = "小分类", required = true)
    private String st;

    @ApiModelProperty(value = "课程等级", required = true)
    @NotEmpty(message = "课程等级不能为空")
    private String grade;

    @ApiModelProperty(value = "教学模式（普通，录播，直播等）", required = true)
    private String teachmode;

    @ApiModelProperty(value = "课程介绍")
    private String description;

    @ApiModelProperty(value = "课程图片", required = true)
    private String pic;

    @ApiModelProperty(value = "收费规则，对应数据字典", required = true)
    private String charge;

    @ApiModelProperty(value = "价格")
    @Min(message = "价格错误",value = 0,groups = {ValidationGroups.Insert.class})
    @Min(message = "价格错误",value = 0,groups = {ValidationGroups.Update.class})
    private Float price;

    @ApiModelProperty(value = "原价")
    private Float originalPrice;

    @ApiModelProperty(value = "qq")
    private String qq;

    @ApiModelProperty(value = "微信")
    private String wechat;

    @ApiModelProperty(value = "电话")
    @NotEmpty(message = "电话不能为空")
    private String phone;

    @ApiModelProperty(value = "有效期")
    private Integer validDays;
}