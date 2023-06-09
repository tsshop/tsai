package com.ts.ai.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 反馈对象 feedback
 *
 * @author tsai
 * @date 2023-05-15
 */
@Data
@TableName("feedback")
public class IFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    private Boolean status;

    private String userPhone;

}
