package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 会话记录;
 * @author : tsai
 * @date : 2023-5-8
 */
@Data
@TableName("session_log")
public class SessionLog implements Serializable{

    /** id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id ;

    /** 模板id */
    @NotNull(message = "模板id不能为空")
    private Long templateId ;

    /** 用户Id */
    private Long userId ;

    /** 状态 */
    private Integer status ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

    @TableField(exist = false)
    private SessionMsg sessionMsg;

}