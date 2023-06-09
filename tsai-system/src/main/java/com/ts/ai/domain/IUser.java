package com.ts.ai.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 用户对象 user
 *
 * @author tsai
 * @date 2023-05-15
 */
@Data
@TableName("user")
public class IUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long id;

    /** 昵称 */
    @Excel(name = "昵称")
    private String name;

    /** 头像 */
    @Excel(name = "头像")
    private String avatarUrl;

    /** 性别：0：未知 1-男，2：女 */
    @Excel(name = "性别：0：未知 1-男，2：女")
    private Long gender;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 明文密码 */
    @Excel(name = "明文密码")
    private String visiblePassword;

    /** 封禁状态：0：正常，1：封禁(不允许登录) */
    @Excel(name = "封禁状态：0：正常，1：封禁(不允许登录)")
    private Long deleted;

    /** vip结束时间 */
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "vip结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vipEndTime;

    /** 微信oppenId */
    @Excel(name = "微信oppenId")
    private String openId;

    /** 分享人 */
    @Excel(name = "分享人")
    private Long inviterId;

}
