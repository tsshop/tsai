package com.ts.mvc.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.common.enums.SystemConstant;
import com.ts.config.exception.ApiResult;
import com.ts.mvc.domain.NumberConfig;
import com.ts.mvc.domain.OrderNumber;
import com.ts.mvc.domain.User;
import com.ts.mvc.service.ConfigService;
import com.ts.mvc.service.NumberConfigService;
import com.ts.mvc.service.OrderNumberService;
import com.ts.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用次数套餐;(number_config)表控制层
 * @author : tsai
 * @date : 2023-5-10
 */
@RestController
@RequestMapping("/numberConfig")
public class NumberConfigController{
    @Autowired
    private NumberConfigService numberConfigService;

    @Autowired
    private OrderNumberService orderNumberService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private UserUtil userUtil;

    /**
     * 获取普通套餐
     * @return
     */
    @GetMapping("/list")
    public ApiResult list(){
        return ApiResult.ok(numberConfigService.list(new LambdaQueryWrapper<NumberConfig>()
                .eq(NumberConfig::getType, SystemConstant.NumberTypeEnum.ONE.getCode())
                .eq(NumberConfig::getIsUse,true)));
    }


    /**
     * 邀请赠送套餐
     * @param request
     * @return
     */
    @GetMapping("/inviteProgress")
    public ApiResult inviteProgress(HttpServletRequest request){
        User user = userUtil.getUser(request);
        NumberConfig numberConfig = numberConfigService.getOne(new LambdaQueryWrapper<NumberConfig>()
                .eq(NumberConfig::getType, SystemConstant.NumberTypeEnum.THREE.getCode())
                .eq(NumberConfig::getIsUse, true)
                .last(" limit 1 "));
        if (ObjectUtil.isEmpty(numberConfig)){
            return ApiResult.ok();
        }
        Map<String,Object> result = new HashMap<>();
        Date now = new Date();
        Date begin = DateUtil.beginOfDay(now);
        Date end = DateUtil.endOfDay(now);
        int count = orderNumberService.count(new LambdaQueryWrapper<OrderNumber>()
                .eq(OrderNumber::getType, SystemConstant.NumberTypeEnum.THREE.getCode())
                .eq(OrderNumber::getUserId, user.getId())
                .between(OrderNumber::getCreateTime, begin, end));
        result.put("number",numberConfig.getNumber());
        result.put("inviteNumber",count);
        result.put("inviteMaxNumber",configService.getByKey("INVITE_MAX_NUMBER"));
        return ApiResult.ok(result);
    }

}