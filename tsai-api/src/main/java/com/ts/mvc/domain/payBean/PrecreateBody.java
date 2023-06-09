package com.ts.mvc.domain.payBean;

import lombok.Data;

import java.util.List;

/**
 * @author : tsai
 * @date : 2023/3/30
 */
@Data
public class PrecreateBody {
    //消息ID
    private String msgId;
    //报文请求时间，格式yyyy-MM-ddHH:mm:ss
    private String requestTimestamp;
    //商户订单号
    private String merOrderId;
    //请求系统预留字段
    private String srcReserve;
    //商户号
    private String mid;
    //终端号
    private String tid;
    //业务类型
    private String instMid;
    //商品信息
    private List<GoodsItem> goods;
    //商户附加数据
    private String attachedData;
    //订单过期时间
    private String expireTime;
    //商品标记
    private String goodsTag;
    //商品交易单号
    private String goodsTradeNo;
    //账单描述
    private String orderDesc;
    //订单原始金额
    private String originalAmount;
    //商品ID
    private String productId;
    //支付总金额
    private String totalAmount;
    //分账标记
    private String divisionFlag;
    //平台商户分账金额
    private String platformAmount;
    //子订单信息
    private List<SubOrderItem> subOrders;
    //支付结果通知地址
    private String notifyUrl;
    //订单展示页面
    private String showUrl;
    //担保交易标识
    private String secureTransaction;
    //交易类型
    private String tradeType;
    //商户用户号
    private String merchantUserId;
    //实名认证手机号
    private String mobile;
    //是否需要限制信用卡支付
    private String limitCreditCard;

    private String subAppId;

    private String subOpenId;

    String toJson(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.msgId != null) sb.append("\"msgId\":\"" + this.msgId + "\",");
        if (this.requestTimestamp != null) sb.append("\"requestTimestamp\":\"" + this.requestTimestamp + "\",");
        if (this.merOrderId != null) sb.append("\"merOrderId\":\"" + this.merOrderId + "\",");
        if (this.srcReserve != null) sb.append("\"srcReserve\":\"" + this.srcReserve + "\",");
        if (this.mid != null) sb.append("\"mid\":\"" + this.mid + "\",");
        if (this.tid != null) sb.append("\"tid\":\"" + this.tid + "\",");
        if (this.instMid != null) sb.append("\"instMid\":\"" + this.instMid + "\",");
        if (this.goods != null && this.goods.size()>0) {
            sb.append("\"goods\":[");
            for(int i=0;i<goods.size();i++){
                sb.append(goods.get(i));
                sb.append(",");
            }
            if (sb.charAt(sb.length() - 1) == ',')
                sb.deleteCharAt(sb.length() - 1);
            sb.append("],");
        }
        if (this.attachedData != null) sb.append("\"attachedData\":\"" + this.attachedData + "\",");
        if (this.expireTime != null) sb.append("\"expireTime\":\"" + this.expireTime + "\",");
        if (this.goodsTag != null) sb.append("\"goodsTag\":\"" + this.goodsTag + "\",");
        if (this.goodsTradeNo != null) sb.append("\"goodsTradeNo\":\"" + this.goodsTradeNo + "\",");
        if (this.orderDesc != null) sb.append("\"orderDesc\":\"" + this.orderDesc + "\",");
        if (this.originalAmount != null) sb.append("\"originalAmount\":\"" + this.originalAmount + "\",");
        if (this.productId != null) sb.append("\"productId\":\"" + this.productId + "\",");
        if (this.totalAmount != null) sb.append("\"totalAmount\":\"" + this.totalAmount + "\",");
        if (this.divisionFlag != null) sb.append("\"divisionFlag\":\"" + this.divisionFlag + "\",");
        if (this.platformAmount != null) sb.append("\"platformAmount\":\"" + this.platformAmount + "\",");
        if (this.subOrders != null && this.subOrders.size()>0) {
            sb.append("\"subOrders\":[");
            for(int i=0;i<subOrders.size();i++){
                sb.append(subOrders.get(i));
                sb.append(",");
            }
            if (sb.charAt(sb.length() - 1) == ',')
                sb.deleteCharAt(sb.length() - 1);
            sb.append("],");
        }
        if (this.notifyUrl != null) sb.append("\"notifyUrl\":\"" + this.notifyUrl + "\",");
        if (this.showUrl != null) sb.append("\"showUrl\":\"" + this.showUrl + "\",");
        if (this.secureTransaction != null) sb.append("\"secureTransaction\":\"" + this.secureTransaction + "\",");
        if (this.tradeType != null) sb.append("\"tradeType\":\"" + this.tradeType + "\",");
        if (this.merchantUserId != null) sb.append("\"merchantUserId\":\"" + this.merchantUserId + "\",");
        if (this.mobile != null) sb.append("\"mobile\":\"" + this.mobile + "\",");
        if (this.limitCreditCard != null) sb.append("\"limitCreditCard\":\"" + this.limitCreditCard + "\",");
        if (this.subAppId != null) sb.append("\"subAppId\":\"" + this.subAppId + "\",");
        if (this.subOpenId != null) sb.append("\"subOpenId\":\"" + this.subOpenId + "\",");
        if (sb.charAt(sb.length() - 1) == ',')
            sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public String toString(){
        return this.toJson();
    }
}
