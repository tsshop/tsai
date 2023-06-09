package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.NumberConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 使用次数套餐;(number_config)表数据库访问层
 * @author : tsai
 * @date : 2023-5-8
 */
@Mapper
public interface NumberConfigMapper  extends BaseMapper<NumberConfig>{
}