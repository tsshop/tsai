package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 反馈表;
 *  @author : tsai
 * @date : 2023-5-11
 */
@Data
@TableName("feedback")
public class Feedback implements Serializable{

    /** id */
    private Long id ;

    /** 用户id */
    private Long userId ;

    /** 内容 */
    private String content ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

    private Boolean status;

    private String userPhone;

}
