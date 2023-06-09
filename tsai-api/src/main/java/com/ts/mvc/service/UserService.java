package com.ts.mvc.service;

import com.ts.config.exception.ApiResult;
import com.ts.mvc.domain.User;
import com.ts.mvc.domain.po.UserLoginParam;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User>{


    ApiResult<Object> loginByScenario(UserLoginParam loginParam) throws Exception;

    ApiResult<Object> sendMessage(String phone, String s);
}
