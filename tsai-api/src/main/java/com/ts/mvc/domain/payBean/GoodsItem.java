package com.ts.mvc.domain.payBean;

import lombok.Data;

/**
 * @author : tsai
 * @date : 2023/3/30
 */
@Data
public class GoodsItem {
    //商品ID
    private String goodsId;
    //商品名称
    private String goodsName;
    //商品数量
    private String quantity;
    //商品单价（分）
    private String price;
    //商品分类
    private String goodsCategory;
    //商品说明
    private String body;
    //商品单位
    private int unit;
    //商品折扣
    private int discount;
    //子商户号
    private String subMerchantId;
    //子商户商品总额
    private int subOrderAmount;

    String toJson(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if(this.goodsId!=null) sb.append("\"goodsId\":\""+this.goodsId+"\",");
        if(this.goodsName!=null) sb.append("\"goodsName\":\""+this.goodsName+"\",");
        if(this.quantity!=null) sb.append("\"quantity\":\""+this.quantity+"\",");
        if(this.price!=null) sb.append("\"price\":\""+this.price+"\",");
        if(this.goodsCategory!=null) sb.append("\"goodsCategory\":\""+this.goodsCategory+"\",");
        if(this.body!=null) sb.append("\"body\":\""+this.body+"\",");
        if(this.unit!=0) sb.append("\"unit\":\""+this.unit+"\",");
        if(this.discount!=0) sb.append("\"discount\":\""+this.discount+"\",");
        if(this.subMerchantId!=null) sb.append("\"subMerchantId\":\""+this.subMerchantId+"\",");
        if(this.subOrderAmount!=0) sb.append("\"subOrderAmount\":\""+this.subOrderAmount+"\",");
        if(sb.charAt(sb.length()-1) == ',')
            sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }
    public String toString(){
        return this.toJson();
    }
}
