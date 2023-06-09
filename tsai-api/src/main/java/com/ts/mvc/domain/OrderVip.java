package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * VIP订单表;
 * @author : tsai
 * @date : 2023-5-8
 */
@Data
@TableName("order_vip")
@Accessors(chain = true)
public class OrderVip implements Serializable{

    /** 订单ID */
    private Long id ;

    /** 订单编号 */
    private String orderNo ;

    /** 用户ID */
    private Long userId ;

    /** 时长 */
    private Integer time ;

    /** 总金额 */
    private BigDecimal totalAmt ;

    /** 支付金额 */
    private BigDecimal payAmt ;

    /** 0:未支付 1:已支付  -1:已取消 */
    private Integer orderStatus ;

    /** 下单单号 */
    private String payNo ;

    /** 备注 */
    private String remark ;

    /** ip地址 */
    private String ip ;

    /** 退款金额 */
    private BigDecimal refundAmt ;

    /** 创建时间/下单时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

    /** 是否已删除 （0：正常/1：删除） */
    private Integer isDel ;

}
