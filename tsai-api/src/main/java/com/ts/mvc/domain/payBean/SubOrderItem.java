package com.ts.mvc.domain.payBean;

import lombok.Data;

/**
 * @author : tsai
 * @date : 2023/3/30
 */
@Data
public class SubOrderItem{
    //子商户号
    private String mid;
    //子商户分账金额
    private int totalAmount;

    String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.mid != null) {
            sb.append("\"mid\":\"" + this.mid + "\",");
        }
        if (this.totalAmount != 0) {
            sb.append("\"totalAmount\":\"" + this.totalAmount + "\",");
        }
        if (sb.charAt(sb.length() - 1) == ',')
            sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public String toString(){
        return this.toJson();
    }
}
