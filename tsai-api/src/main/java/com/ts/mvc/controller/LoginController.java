package com.ts.mvc.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.config.exception.ApiResult;
import com.ts.config.token.PassToken;
import com.ts.mvc.domain.User;
import com.ts.mvc.mapper.UserMapper;
import com.ts.mvc.service.UserService;
import com.ts.util.PhoneUtil;
import com.ts.util.UserUtil;
import com.ts.mvc.domain.po.UserLoginParam;
import com.ts.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    UserUtil userUtil;
    @Autowired
    private RedisCache redisService;

    @Autowired
    UserMapper userMapper;


    /**
     * 登录
     * @param loginParam
     * @return
     * @throws Exception
     */
    @PostMapping("/loginByScenario")
    @PassToken
    public ApiResult<Object> loginByScenario(@RequestBody UserLoginParam loginParam) throws Exception {
        return userService.loginByScenario(loginParam);
    }

    @PassToken
    @GetMapping(value = "/getCode")
    public ApiResult<Object> getCode(@RequestParam("phone") String phone) {
        if (!PhoneUtil.isPhoneLegal(phone)) {
            return ApiResult.fail("请输入正确的手机号");
        }
        return userService.sendMessage(phone,"login:");
    }


    @GetMapping("/out")
    public ApiResult<Object> out(HttpServletRequest httpServletRequest)  {
        User user= userUtil.getUser(httpServletRequest);
        redisService.deleteObject("user:"+user.getId());
        return ApiResult.ok(user);
    }


    @GetMapping("/delete")
    public ApiResult<Object> out(String phone)  {
        int delete = userMapper.delete(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        return ApiResult.ok(delete);
    }

}
