package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 使用次数订单表;
 * @author : tsai
 * @date : 2023-5-8
 */
@Data
@Accessors(chain = true)
@TableName("order_number")
public class OrderNumber implements Serializable{

    /** 订单ID */
    private Long id ;

    /** 订单编号 */
    private String orderNo ;

    /** 用户ID */
    private Long userId ;

    /** 总次数 */
    private Integer allNumber ;

    /** 剩余次数 */
    private Integer residueNumber ;

    /** 总金额 */
    private BigDecimal totalAmt ;

    /** 支付金额 */
    private BigDecimal payAmt ;

    /** 0:未支付 1:已支付  -1:已取消 */
    private Integer orderStatus ;

    /** 来源类型 */
    private Integer type ;

    /** 下单单号 */
    private String payNo ;

    /** 备注 */
    private String remark ;

    /** ip地址 */
    private String ip ;

    /** 退款金额 */
    private Double refundAmt ;

    /** 有效截止时间 */
    private Date validEndTime ;

    /** 创建时间/下单时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

    /** 是否已删除 （0：正常/1：删除） */
    private Integer isDel ;

    private Long sourceUserId;

    @TableField(exist = false)
    private String sourceUserName;
}
