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
* @TableName teachplan_work
*/
@Data
@AllArgsConstructor
public class TeachplanWork implements Serializable {

    /**
    * 主键
    */
    @NotNull(message="[主键]不能为空")
    @ApiModelProperty("主键")
    private Long id;
    /**
    * 作业信息标识
    */
    @NotNull(message="[作业信息标识]不能为空")
    @ApiModelProperty("作业信息标识")
    private Long workId;
    /**
    * 作业标题
    */
    @NotBlank(message="[作业标题]不能为空")
    @Size(max= 60,message="编码长度不能超过60")
    @ApiModelProperty("作业标题")
    @Length(max= 60,message="编码长度不能超过60")
    private String workTitle;
    /**
    * 课程计划标识
    */
    @NotNull(message="[课程计划标识]不能为空")
    @ApiModelProperty("课程计划标识")
    private Long teachplanId;
    /**
    * 课程标识
    */
    @ApiModelProperty("课程标识")
    private Long courseId;
    /**
    * 
    */
    @ApiModelProperty("")
    private Date createDate;
    /**
    * 
    */
    @ApiModelProperty("")
    private Long coursePubId;

    /**
    * 主键
    */
    private void setId(Long id){
    this.id = id;
    }

    /**
    * 作业信息标识
    */
    private void setWorkId(Long workId){
    this.workId = workId;
    }

    /**
    * 作业标题
    */
    private void setWorkTitle(String workTitle){
    this.workTitle = workTitle;
    }

    /**
    * 课程计划标识
    */
    private void setTeachplanId(Long teachplanId){
    this.teachplanId = teachplanId;
    }

    /**
    * 课程标识
    */
    private void setCourseId(Long courseId){
    this.courseId = courseId;
    }

    /**
    * 
    */
    private void setCreateDate(Date createDate){
    this.createDate = createDate;
    }

    /**
    * 
    */
    private void setCoursePubId(Long coursePubId){
    this.coursePubId = coursePubId;
    }


    /**
    * 主键
    */
    private Long getId(){
    return this.id;
    }

    /**
    * 作业信息标识
    */
    private Long getWorkId(){
    return this.workId;
    }

    /**
    * 作业标题
    */
    private String getWorkTitle(){
    return this.workTitle;
    }

    /**
    * 课程计划标识
    */
    private Long getTeachplanId(){
    return this.teachplanId;
    }

    /**
    * 课程标识
    */
    private Long getCourseId(){
    return this.courseId;
    }

    /**
    * 
    */
    private Date getCreateDate(){
    return this.createDate;
    }

    /**
    * 
    */
    private Long getCoursePubId(){
    return this.coursePubId;
    }

}
