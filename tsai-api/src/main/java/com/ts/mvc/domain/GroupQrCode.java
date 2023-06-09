package com.ts.mvc.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 群二维码;
 *  @author : tsai
 * @date : 2023-5-12
 */
@Data
@TableName("group_qr_code")
public class GroupQrCode implements Serializable{

    /** id */
    private Long id ;

    /** 名称 */
    private String name ;

    /** 图片 */
    private String code ;

    /** 是否启用 */
    private Boolean isUse ;

    /** 创建时间 */
    private Date createTime ;

    /** 更新时间 */
    private Date updateTime ;

}