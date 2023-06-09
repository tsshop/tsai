package com.ts.ai.domain;

import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 群二维码对象 group_qr_code
 *
 * @author tsai
 * @date 2023-05-15
 */
@Data
public class IGroupQrCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @Excel(name = "id")
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 图片 */
    @Excel(name = "图片")
    private String code;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Long isUse;

}
