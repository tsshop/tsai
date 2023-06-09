package com.ts.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.config.exception.CustomException;
import com.ts.config.token.JwtUserInfo;
import com.ts.config.token.TokenUtil;
import com.ts.mvc.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : tsai
 * @date : 2023/5/9
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedisUtil {

    private final RedisTemplate redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public User getCurrentUser(HttpServletRequest httpServletRequest) {
        // 从 http 请求头中取出 token;
        try {
            String token = httpServletRequest.getHeader("token");
            return getUserByToken(token);
        }catch (Exception ex){
            throw new CustomException(401,"用户未登录或已过期");
        }
    }

    public User getUserByToken(String token){
        JwtUserInfo account = TokenUtil.getAccount(token);
        Long userId = account.getUserId();
        Object o=redisTemplate.opsForValue().get("user:" + userId);

        User user = objectMapper.convertValue(o, User.class);
        if (user == null) {
            throw new CustomException(401,"用户未登录或已过期");
        }
        return user;
    }
}
