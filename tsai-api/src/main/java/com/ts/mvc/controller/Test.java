package com.ts.mvc.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.Blob;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.ts.common.utils.FileUtil;
import com.ts.common.utils.test.EncryptUtils;
import com.ts.config.exception.ApiResult;
import com.ts.config.token.PassToken;
import com.ts.mvc.service.ConfigService;
import com.ts.util.GptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : tsai
 * @date : 2023/5/8
 */
@RequestMapping("/test")
@RestController
public class Test {

    @Autowired
    private GptUtil gptUtil;

    @Autowired
    private ConfigService configService;

    public static void main(String[] args) throws Exception{
        System.out.println(EncryptUtils.desEncrypt("abc123456".toUpperCase()));
    }

    @PostMapping("/test")
    @PassToken
    public ApiResult<Object> test1(@RequestBody List<ChatMessage> messages){
        String msg = gptUtil.send(messages, configService.getByKey("MODEL"), Integer.valueOf(configService.getByKey("MAX_TOKENS")));
        return ApiResult.ok(msg);
    }

    @PostMapping("/test2")
    @PassToken
    public ApiResult<Object> test2(@RequestParam("file") MultipartFile multipartFile){

        return ApiResult.ok(FileUtil.getBase64String(multipartFile));
    }
}
