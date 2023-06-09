package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.VipConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * vip套餐;(vip_config)表数据库访问层
 * @author : tsai
 * @date : 2023-5-8
 */
@Mapper
public interface VipConfigMapper  extends BaseMapper<VipConfig>{
}