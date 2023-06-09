package com.ts.mvc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.config.exception.ApiResult;
import com.ts.mvc.domain.VipConfig;
import com.ts.mvc.service.VipConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * vip套餐;(vip_config)表控制层
 * @author : tsai
 * @date : 2023-5-10
 */
@RestController
@RequestMapping("/vipConfig")
public class VipConfigController{
    @Autowired
    private VipConfigService vipConfigService;

    /**
     * vip套餐列表
     * @return
     */
    @GetMapping("/list")
    public ApiResult list(){
        return ApiResult.ok(vipConfigService.list(new LambdaQueryWrapper<VipConfig>()
                .eq(VipConfig::getIsUse,true)));
    }
}