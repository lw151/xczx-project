package com.xuecheng.content.model.po;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
* 
* @TableName mq_message
*/
public class MqMessage implements Serializable {

    /**
    * 消息id
    */
    @NotNull(message="[消息id]不能为空")
    @ApiModelProperty("消息id")
    private Long id;
    /**
    * 消息类型代码: course_publish ,  media_test
    */
    @NotBlank(message="[消息类型代码: course_publish ,  media_test]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("消息类型代码: course_publish ,  media_test")
    @Length(max= 32,message="编码长度不能超过32")
    private String messageType;
    /**
    * 关联业务信息
    */
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("关联业务信息")
    @Length(max= 64,message="编码长度不能超过64")
    private String businessKey1;
    /**
    * 关联业务信息
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("关联业务信息")
    @Length(max= 255,message="编码长度不能超过255")
    private String businessKey2;
    /**
    * 关联业务信息
    */
    @Size(max= 512,message="编码长度不能超过512")
    @ApiModelProperty("关联业务信息")
    @Length(max= 512,message="编码长度不能超过512")
    private String businessKey3;
    /**
    * 通知次数
    */
    @NotNull(message="[通知次数]不能为空")
    @ApiModelProperty("通知次数")
    private Integer executeNum;
    /**
    * 处理状态，0:初始，1:成功
    */
    @NotNull(message="[处理状态，0:初始，1:成功]不能为空")
    @ApiModelProperty("处理状态，0:初始，1:成功")
    private String state;
    /**
    * 回复失败时间
    */
    @ApiModelProperty("回复失败时间")
    private Date returnfailureDate;
    /**
    * 回复成功时间
    */
    @ApiModelProperty("回复成功时间")
    private Date returnsuccessDate;
    /**
    * 回复失败内容
    */
    @Size(max= 2048,message="编码长度不能超过2048")
    @ApiModelProperty("回复失败内容")
    @Length(max= 2048,message="编码长度不能超过2,048")
    private String returnfailureMsg;
    /**
    * 最近通知时间
    */
    @ApiModelProperty("最近通知时间")
    private Date executeDate;
    /**
    * 阶段1处理状态, 0:初始，1:成功
    */
    @NotNull(message="[阶段1处理状态, 0:初始，1:成功]不能为空")
    @ApiModelProperty("阶段1处理状态, 0:初始，1:成功")
    private String stageState1;
    /**
    * 阶段2处理状态, 0:初始，1:成功
    */
    @NotNull(message="[阶段2处理状态, 0:初始，1:成功]不能为空")
    @ApiModelProperty("阶段2处理状态, 0:初始，1:成功")
    private String stageState2;
    /**
    * 阶段3处理状态, 0:初始，1:成功
    */
    @NotNull(message="[阶段3处理状态, 0:初始，1:成功]不能为空")
    @ApiModelProperty("阶段3处理状态, 0:初始，1:成功")
    private String stageState3;
    /**
    * 阶段4处理状态, 0:初始，1:成功
    */
    @NotNull(message="[阶段4处理状态, 0:初始，1:成功]不能为空")
    @ApiModelProperty("阶段4处理状态, 0:初始，1:成功")
    private String stageState4;

    /**
    * 消息id
    */
    private void setId(Long id){
    this.id = id;
    }

    /**
    * 消息类型代码: course_publish ,  media_test
    */
    private void setMessageType(String messageType){
    this.messageType = messageType;
    }

    /**
    * 关联业务信息
    */
    private void setBusinessKey1(String businessKey1){
    this.businessKey1 = businessKey1;
    }

    /**
    * 关联业务信息
    */
    private void setBusinessKey2(String businessKey2){
    this.businessKey2 = businessKey2;
    }

    /**
    * 关联业务信息
    */
    private void setBusinessKey3(String businessKey3){
    this.businessKey3 = businessKey3;
    }

    /**
    * 通知次数
    */
    private void setExecuteNum(Integer executeNum){
    this.executeNum = executeNum;
    }

    /**
    * 处理状态，0:初始，1:成功
    */
    private void setState(String state){
    this.state = state;
    }

    /**
    * 回复失败时间
    */
    private void setReturnfailureDate(Date returnfailureDate){
    this.returnfailureDate = returnfailureDate;
    }

    /**
    * 回复成功时间
    */
    private void setReturnsuccessDate(Date returnsuccessDate){
    this.returnsuccessDate = returnsuccessDate;
    }

    /**
    * 回复失败内容
    */
    private void setReturnfailureMsg(String returnfailureMsg){
    this.returnfailureMsg = returnfailureMsg;
    }

    /**
    * 最近通知时间
    */
    private void setExecuteDate(Date executeDate){
    this.executeDate = executeDate;
    }

    /**
    * 阶段1处理状态, 0:初始，1:成功
    */
    private void setStageState1(String stageState1){
    this.stageState1 = stageState1;
    }

    /**
    * 阶段2处理状态, 0:初始，1:成功
    */
    private void setStageState2(String stageState2){
    this.stageState2 = stageState2;
    }

    /**
    * 阶段3处理状态, 0:初始，1:成功
    */
    private void setStageState3(String stageState3){
    this.stageState3 = stageState3;
    }

    /**
    * 阶段4处理状态, 0:初始，1:成功
    */
    private void setStageState4(String stageState4){
    this.stageState4 = stageState4;
    }


    /**
    * 消息id
    */
    private Long getId(){
    return this.id;
    }

    /**
    * 消息类型代码: course_publish ,  media_test
    */
    private String getMessageType(){
    return this.messageType;
    }

    /**
    * 关联业务信息
    */
    private String getBusinessKey1(){
    return this.businessKey1;
    }

    /**
    * 关联业务信息
    */
    private String getBusinessKey2(){
    return this.businessKey2;
    }

    /**
    * 关联业务信息
    */
    private String getBusinessKey3(){
    return this.businessKey3;
    }

    /**
    * 通知次数
    */
    private Integer getExecuteNum(){
    return this.executeNum;
    }

    /**
    * 处理状态，0:初始，1:成功
    */
    private String getState(){
    return this.state;
    }

    /**
    * 回复失败时间
    */
    private Date getReturnfailureDate(){
    return this.returnfailureDate;
    }

    /**
    * 回复成功时间
    */
    private Date getReturnsuccessDate(){
    return this.returnsuccessDate;
    }

    /**
    * 回复失败内容
    */
    private String getReturnfailureMsg(){
    return this.returnfailureMsg;
    }

    /**
    * 最近通知时间
    */
    private Date getExecuteDate(){
    return this.executeDate;
    }

    /**
    * 阶段1处理状态, 0:初始，1:成功
    */
    private String getStageState1(){
    return this.stageState1;
    }

    /**
    * 阶段2处理状态, 0:初始，1:成功
    */
    private String getStageState2(){
    return this.stageState2;
    }

    /**
    * 阶段3处理状态, 0:初始，1:成功
    */
    private String getStageState3(){
    return this.stageState3;
    }

    /**
    * 阶段4处理状态, 0:初始，1:成功
    */
    private String getStageState4(){
    return this.stageState4;
    }

}
