package com.ts.mvc.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.Config;
import com.ts.mvc.mapper.ConfigMapper;
import com.ts.mvc.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 配置;(config)表服务实现类
 * @author : tsai
 * @date : 2023-5-8
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getByKey(String key) {
        if (redisTemplate.hasKey("config:"+key)){
            return (String) redisTemplate.opsForValue().get("config:"+key);
        }else {
            Config config = getOne(new LambdaQueryWrapper<Config>()
                    .eq(Config::getConfigKey,key));
            if (ObjectUtil.isNotEmpty(config)){
                redisTemplate.opsForValue().set("config:"+key,config.getConfigValue());
                return config.getConfigValue();
            }else {
                return "";
            }
        }
    }
}