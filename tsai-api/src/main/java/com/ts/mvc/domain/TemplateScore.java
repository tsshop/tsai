package com.ts.mvc.domain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 模板反馈;
 *  @author : tsai
 * @date : 2023-5-11
 */
@Data
@Accessors(chain = true)
@TableName("template_score")
public class TemplateScore implements Serializable{

    /** id */
    private Long id ;

    /** 模板id */
    private Long templateId ;

    /** 用户id */
    private Long userId ;

    /** 类型 */
    private Integer scoreType ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

}