package com.ts.web.controller.enmus;

/**
 * 领取优惠券失败原因类型
 */
public enum EnumUserSign {


    SIGN_IS_DOWN(1,"今日已签到"),
    SIGN_IS_READY(2,"签到成功"),
    ;



    private Integer value;
    private String desc;

    EnumUserSign(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
