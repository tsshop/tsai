package com.ts.ai.domain;

import java.math.BigDecimal;
import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 使用次数套餐对象 number_config
 *
 * @author tsai
 * @date 2023-05-15
 */
@Data
public class INumberConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 次数 */
    @Excel(name = "次数")
    private Long number;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal amt;

    /** 有效时长 */
    @Excel(name = "有效时长")
    private Long time;

    /** 原价 */
    @Excel(name = "原价")
    private BigDecimal originalAmt;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Long isUse;

    /** 类型 */
    @Excel(name = "类型")
    private Long type;

}
