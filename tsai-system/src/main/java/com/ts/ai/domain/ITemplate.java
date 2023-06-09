package com.ts.ai.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 模板对象 template
 *
 * @author tsai
 * @date 2023-05-15
 */
@Data
public class ITemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 简介 */
    @Excel(name = "简介")
    private String blurb;

    /** 说明 */
    @Excel(name = "说明")
    private String illustrate;

    /** 示例 */
    @Excel(name = "示例")
    private String example;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Long isUse;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 评价 */
    @Excel(name = "评价")
    private Long score;

    private Long typeId;

    private Boolean isIndex;

    @TableField(exist = false)
    private Long goodScore;

    @TableField(exist = false)
    private Long badScore;

    @TableField(exist = false)
    private String type;
}
