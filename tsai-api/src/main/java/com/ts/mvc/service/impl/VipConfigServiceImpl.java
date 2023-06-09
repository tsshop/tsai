package com.ts.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.VipConfig;
import com.ts.mvc.mapper.VipConfigMapper;
import com.ts.mvc.service.VipConfigService;
import org.springframework.stereotype.Service;

/**
 * vip套餐;(vip_config)表服务实现类
 * @author : tsai
 * @date : 2023-5-8
 */
@Service
public class VipConfigServiceImpl extends ServiceImpl<VipConfigMapper, VipConfig> implements VipConfigService {

}