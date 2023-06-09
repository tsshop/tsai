package com.ts.ai.domain;

import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 会话记录对象 session_log
 *
 * @author tsai
 * @date 2023-05-15
 */
@Data
public class ISessionLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 模板id */
    @Excel(name = "模板id")
    private String templateId;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

}
