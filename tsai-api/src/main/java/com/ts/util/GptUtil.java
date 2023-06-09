package com.ts.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import com.ts.config.exception.ApiResult;
import com.ts.mvc.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static com.theokanning.openai.service.OpenAiService.*;

/**
 * gpt工具类
 * @author : tsai
 * @date : 2023/5/8
 */
@Slf4j
@Component
public class GptUtil {

    private OpenAiService service;

    @Autowired
    private ConfigService configService;

    /**
     * 项目启动初始化连接
     */
    @PostConstruct
    private void initialization(){
        log.error("初始化连接");
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client;
        if (Boolean.valueOf(configService.getByKey("isAgent"))){
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(configService.getByKey("host"), Integer.valueOf(configService.getByKey("port"))));
            client = defaultClient(configService.getByKey("token"),Duration.ofSeconds(60))
                    .newBuilder()
                    .proxy(proxy)
                    .build();
        }else {
            client = defaultClient(configService.getByKey("token"),Duration.ofSeconds(60));
        }

        Retrofit retrofit = defaultRetrofit(client, mapper).newBuilder().baseUrl(configService.getByKey("requestUrl")).build();
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        service = new OpenAiService(api);
    }

    /**
     * 发送消息
     * @param messages
     * @param model
     * @param maxTokens
     * @return
     */
    public String send(List<ChatMessage> messages,String model,Integer maxTokens){
        System.out.println(new DateTime());
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model(model)
                .messages(messages)
                .n(1)
                .maxTokens(maxTokens)
                .logitBias(new HashMap<>())
                .build();

        List<ChatCompletionChoice> choices = this.service.createChatCompletion(chatCompletionRequest).getChoices();
        System.out.println(new DateTime());
        choices.forEach(item -> {
            System.out.println(item);
        });
        return choices.get(0).getMessage().getContent();
    }

}
