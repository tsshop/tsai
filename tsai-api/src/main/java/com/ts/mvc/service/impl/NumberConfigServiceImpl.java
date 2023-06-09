package com.ts.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.NumberConfig;
import com.ts.mvc.mapper.NumberConfigMapper;
import com.ts.mvc.service.NumberConfigService;
import org.springframework.stereotype.Service;

/**
 * 使用次数套餐;(number_config)表服务实现类
 * @author : tsai
 * @date : 2023-5-8
 */
@Service
public class NumberConfigServiceImpl extends ServiceImpl<NumberConfigMapper, NumberConfig> implements NumberConfigService {

}