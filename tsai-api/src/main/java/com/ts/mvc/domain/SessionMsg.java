package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 会话聊天记录;
 * @author : tsai
 * @date : 2023-5-8
 */
@Data
@TableName("session_msg")
public class SessionMsg implements Serializable{

    /** id */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id ;

    private Long templateId;

    /** 用户Id */
    private Long logId ;

    /** 是否用户发送 */
    private Boolean isFromUser ;

    /** 消息内容 */
    private String content ;

    /** 发送时间 */
    private Date sendTime ;

    /** 更新时间 */
    private Date updateTime ;

    private Integer score;

}