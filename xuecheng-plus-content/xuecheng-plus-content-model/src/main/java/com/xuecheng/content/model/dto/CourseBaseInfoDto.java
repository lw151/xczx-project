package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @description 课程基本信息dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseBaseInfoDto extends CourseBase {


 /**
  * 收费规则，对应数据字典
  */
 @NotEmpty(message = "收费规则必须存在")
 private String charge;

 /**
  * 价格
  */
 @Min(message = "价格错误",value = 0)
 private Float price;


 /**
  * 原价
  */
 @Min(message = "价格错误",value = 0)
 private Float originalPrice;

 /**
  * 咨询qq
  */
 private String qq;

 /**
  * 微信
  */
 private String wechat;

 /**
  * 电话
  */
 private String phone;

 /**
  * 有效期天数
  */
 private Integer validDays;

 /**
  * 大分类名称
  */
 @NotEmpty(message = "分类必须存在")
 private String mtName;

 /**
  * 小分类名称
  */
 @NotEmpty(message = "分类必须存在")
 private String stName;

}
