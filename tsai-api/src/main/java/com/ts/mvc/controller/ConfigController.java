package com.ts.mvc.controller;

import com.ts.config.exception.ApiResult;
import com.ts.config.token.PassToken;
import com.ts.mvc.domain.Config;
import com.ts.mvc.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * 配置;(config)表控制层
 * @author :  tsai
 * @date : 2023-5-11
 */
@RestController
@RequestMapping("/config")
public class ConfigController{

    @Autowired
    private ConfigService configService;

    /**
     * 获取配置列表
     * @return
     */
    @GetMapping("/list")
    @PassToken
    public ApiResult getConfigList(){
        return ApiResult.ok(configService.list().stream().collect(Collectors.toMap(Config::getConfigKey,Config::getConfigValue)));
    }

}
