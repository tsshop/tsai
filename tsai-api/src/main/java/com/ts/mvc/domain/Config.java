package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 配置;
 * @author : tsai
 * @date : 2023-5-8
 */
@Data
@TableName("config")
public class Config implements Serializable{

    /** id */
    private Long id ;

    /** 键 */
    private String configKey ;

    /** 值 */
    private String configValue ;

    /** 标题 */
    private String title ;

    /** 备注 */
    private String remark ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

}