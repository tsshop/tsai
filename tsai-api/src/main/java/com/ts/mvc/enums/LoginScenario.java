package com.ts.mvc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginScenario {
    /**
     * 登录场景
     * login_password:phone+password
     * login_code:phone+code
     * login_openId:openId
     */
    PASSWORD("login_password", "登录参数:phone+password"),
    CODE("login_code", "登录参数:phone+code"),

    WX_PASSWORD("login_wx_password", "登录参数:phone+password+openId"),

    WX_CODE("login_wx_code", "登录参数:phone+code+openId");


    private String value;

    private String msg;

}
