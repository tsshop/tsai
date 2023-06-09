package com.ts.mvc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ts.mvc.domain.Config;

/**
 * @author : tsai
 * @date : 2023/5/8
 */
public interface ConfigService extends IService<Config> {

    public String getByKey(String key);
}
