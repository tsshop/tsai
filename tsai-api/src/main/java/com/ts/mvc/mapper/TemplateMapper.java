package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.Template;
import org.apache.ibatis.annotations.Mapper;

/**
 * 模板;(template)表数据库访问层
 * @author : tsai
 * @date : 2023-5-8
 */
@Mapper
public interface TemplateMapper  extends BaseMapper<Template>{
}
