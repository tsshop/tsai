package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.TemplateType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 模板类型;(template_type)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2023-5-16
 */
@Mapper
public interface TemplateTypeMapper  extends BaseMapper<TemplateType>{
}