package com.ts.ai.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 配置对象 config
 *
 * @author tsai
 * @date 2023-05-16
 */
@Data
@TableName("config")
public class IConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 键 */
    @Excel(name = "键")
    private String configKey;

    /** 值 */
    @Excel(name = "值")
    private String configValue;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

}
