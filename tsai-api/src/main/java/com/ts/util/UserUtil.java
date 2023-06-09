package com.ts.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.ts.config.exception.CustomException;
import com.ts.config.token.AccessToken;
import com.ts.config.token.TokenUtil;
import com.ts.mvc.domain.User;
import com.ts.common.core.redis.RedisCache;
import com.ts.mvc.service.UserService;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class UserUtil {
    @Autowired
    UserService userService;
    @Autowired
    private RedisCache redisService;

    public User getUser(String token){
        AccessToken accessToken = TokenUtil.verify(token);
        User user =new User();
        try {
            user = (User) redisService.getCacheObject("user:"+String.valueOf(accessToken.getUserId()));
            if(user==null){
                throw new CustomException(401,"登录失效");
            }
        }catch (Exception e){
            throw new CustomException(401,"登录失效");
        }
        return user;
    }
    public User getUser(HttpServletRequest request){
        String token = request.getHeader("token");
        return getUser(token);
    }

    public static Date getPermanentTime(){
        return new DateTime("9999-12-31 23:59:59");
    }
}
