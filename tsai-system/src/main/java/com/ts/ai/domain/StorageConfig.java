package com.ts.ai.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 存储配置对象 storage_config
 *
 * @author tsai
 * @date 2023-06-07
 */
@Data
@TableName("storage_config")
public class StorageConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Boolean isUse;

    /** 配置 */
    @Excel(name = "配置")
    private String config;

}
