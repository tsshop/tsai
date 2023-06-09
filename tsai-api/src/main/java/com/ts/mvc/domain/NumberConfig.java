package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 使用次数套餐;
 * @author : tsai
 * @date : 2023-5-8
 */
@Data
@TableName("number_config")
public class NumberConfig implements Serializable{

    /** id */
    private Long id ;

    /** 名称 */
    private String name ;

    /** 次数 */
    private Integer number ;

    /** 价格 */
    private BigDecimal amt ;

    /** 有效时长 */
    private Integer time ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

    private BigDecimal originalAmt;

    private Boolean isUse;

    private Integer type;
}