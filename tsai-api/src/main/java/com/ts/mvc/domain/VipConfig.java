package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * vip套餐;
 * @author : tsai
 * @date : 2023-5-8
 */
@Data
@TableName("vip_config")
public class VipConfig implements Serializable{

    /** id */
    private Long id ;

    /** 名称 */
    private String name ;

    /** 时长 */
    private Integer time ;

    /** 价格 */
    private BigDecimal amt ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

    private BigDecimal originalAmt;

    private Boolean isUse;
}
