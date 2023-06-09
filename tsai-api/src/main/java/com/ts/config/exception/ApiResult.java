package com.ts.config.exception;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * REST API 返回结果
 * </p>
 *
 * @author : tsai
 * @since 2020-10-16
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1762924401215256839L;

    private int status;

    private T data;

    private String msg;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    public ApiResult() {

    }
    public static <T> ApiResult<T> result(Integer code,String msg,T data){
        String message = msg;
        if (StringUtils.isNotBlank(msg)){
            message = msg;
        }
        return (ApiResult<T>) ApiResult.builder()
                .status(code)
                .msg(message)
                .data(data)
                .time(new Date())
                .build();
    }
    public static <T> ApiResult<T> ok(T data){
        return (ApiResult<T>) ApiResult.builder()
                .status(200)
                .msg("ok")
                .data(data)
                .time(new Date())
                .build();
    }
    public static <T> ApiResult<T> ok(){
        return (ApiResult<T>) ApiResult.builder()
                .status(200)
                .msg("ok")
                .data("")
                .time(new Date())
                .build();
    }
    public static <T> ApiResult<T> fail(String msg){
        return (ApiResult<T>) ApiResult.builder()
                .status(500)
                .msg(msg)
                .data(null)
                .time(new Date())
                .build();
    }

}