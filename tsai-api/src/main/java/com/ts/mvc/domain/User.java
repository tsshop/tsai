package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表;
 * @author : tsai
 * @date : 2023-5-8
 */
@Data
@TableName("user")
public class User implements Serializable{

    /** 用户ID */
    private Long id ;

    /** 昵称 */
    private String name ;

    /** 头像 */
    private String avatarUrl ;

    /** 性别：0：未知 1-男，2：女 */
    private Integer gender ;

    /** 手机号 */
    private String phone ;

    /** 密码 */
    private String password ;

    /** 明文密码 */
    private String visiblePassword ;

    /** 封禁状态：0：正常，1：封禁(不允许登录) */
    private Boolean deleted ;

    /** 备注 */
    private String remark ;

    /** vip结束时间 */
    private Date vipEndTime ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

    private String openId;

    @TableField(exist = false)
    private String token;

    private Long inviterId;
}