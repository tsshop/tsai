package com.ts.mvc.domain.po;

import lombok.Data;

/**
 * @author nine
 */
@Data
public class UserLoginParam {

    /**
     * 登录场景
     * login_password:phone+password
     * login_code:phone+code
     */
    private String scenario;

    /**
     * 电话
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;
    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String avatarUrl;
    private String name;

    private Integer type;

    private String wxCode;

    private Long inviterId;

    private String content;
}
