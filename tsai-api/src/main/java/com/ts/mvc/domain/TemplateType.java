package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 模板类型;
 * @author : tsai
 * @date : 2023-5-16
 */
@Data
@TableName("template_type")
public class TemplateType implements Serializable{

    /** id */
    private Long id ;

    /** 名称 */
    private String name ;

    /** 是否启用 */
    private Boolean isUse ;

    /** 排序 */
    private Boolean orderBy ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

    @TableField(exist = false)
    private List<Template> templateList;
}