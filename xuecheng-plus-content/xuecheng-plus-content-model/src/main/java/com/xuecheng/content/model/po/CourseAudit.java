package com.xuecheng.content.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName course_audit
*/
@Data
@AllArgsConstructor
public class CourseAudit implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Long id;
    /**
    * 课程id
    */
    @NotNull(message="[课程id]不能为空")
    @ApiModelProperty("课程id")
    private Long courseId;
    /**
    * 审核意见
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("审核意见")
    @Length(max= 255,message="编码长度不能超过255")
    private String auditMind;
    /**
    * 审核状态
    */
    @NotBlank(message="[审核状态]不能为空")
    @Size(max= 10,message="编码长度不能超过10")
    @ApiModelProperty("审核状态")
    @Length(max= 10,message="编码长度不能超过10")
    private String auditStatus;
    /**
    * 审核人
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("审核人")
    @Length(max= 50,message="编码长度不能超过50")
    private String auditPeople;
    /**
    * 审核时间
    */
    @ApiModelProperty("审核时间")
    private Date auditDate;

    /**
    * 
    */
    private void setId(Long id){
    this.id = id;
    }

    /**
    * 课程id
    */
    private void setCourseId(Long courseId){
    this.courseId = courseId;
    }

    /**
    * 审核意见
    */
    private void setAuditMind(String auditMind){
    this.auditMind = auditMind;
    }

    /**
    * 审核状态
    */
    private void setAuditStatus(String auditStatus){
    this.auditStatus = auditStatus;
    }

    /**
    * 审核人
    */
    private void setAuditPeople(String auditPeople){
    this.auditPeople = auditPeople;
    }

    /**
    * 审核时间
    */
    private void setAuditDate(Date auditDate){
    this.auditDate = auditDate;
    }


    /**
    * 
    */
    private Long getId(){
    return this.id;
    }

    /**
    * 课程id
    */
    private Long getCourseId(){
    return this.courseId;
    }

    /**
    * 审核意见
    */
    private String getAuditMind(){
    return this.auditMind;
    }

    /**
    * 审核状态
    */
    private String getAuditStatus(){
    return this.auditStatus;
    }

    /**
    * 审核人
    */
    private String getAuditPeople(){
    return this.auditPeople;
    }

    /**
    * 审核时间
    */
    private Date getAuditDate(){
    return this.auditDate;
    }

}
