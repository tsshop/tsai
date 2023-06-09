package com.ts.ai.domain;

import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 模板类型对象 template_type
 *
 * @author tsai
 * @date 2023-05-16
 */
@Data
public class ITemplateType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Long isUse;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderBy;

}
