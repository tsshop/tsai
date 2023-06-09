package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 * 配置;(config)表数据库访问层
 * @author : tsai
 * @date : 2023-5-8
 */
@Mapper
public interface ConfigMapper  extends BaseMapper<Config>{
}