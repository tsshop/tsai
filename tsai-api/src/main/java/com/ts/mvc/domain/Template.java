package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 模板;
 * @author : tsai
 * @date : 2023-5-8
 */
@Data
@TableName("template")
public class Template implements Serializable{

    /** id */
    private Long id ;

    /** 名称 */
    private String name ;

    /** 简介 */
    private String blurb ;

    /** 说明 */
    private String illustrate ;

    /** 示例 */
    private String example ;

    /** 是否启用 */
    private Integer isUse ;

    /** 内容 */
    private String content ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

    @TableField(exist = false)
    private Integer scoreType;

    private Integer score;

    private Long typeId;

    private Boolean isIndex;

}