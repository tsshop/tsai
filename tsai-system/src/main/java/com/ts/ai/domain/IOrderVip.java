package com.ts.ai.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * VIP订单对象 order_vip
 *
 * @author tsai
 * @date 2023-05-15
 */
@Data
@TableName("order_vip")
public class IOrderVip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 时长 */
    @Excel(name = "时长")
    private Long time;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal totalAmt;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payAmt;

    /** 0:未支付 1:已支付  -1:已取消 */
    @Excel(name = "0:未支付 1:已支付  -1:已取消")
    private Long orderStatus;

    /** 下单单号 */
    @Excel(name = "下单单号")
    private String payNo;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String ip;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private BigDecimal refundAmt;

    /** 是否已删除 （0：正常/1：删除） */
    @Excel(name = "是否已删除 ", readConverterExp = "0=：正常/1：删除")
    private Long isDel;

}
