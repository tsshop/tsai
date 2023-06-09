package com.ts.ai.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 会话聊天记录对象 session_msg
 *
 * @author tsai
 * @date 2023-05-15
 */
@Data
@TableName("session_msg")
public class ISessionMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 用户Id */
    @Excel(name = "用户Id")
    private Long logId;

    /** 模板id */
    @Excel(name = "模板id")
    private Long templateId;

    /** 是否用户发送 */
    @Excel(name = "是否用户发送")
    private Integer isFromUser;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String content;

    /** 发送时间 */
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    /** 评价 */
    @Excel(name = "评价")
    private Long score;

}
