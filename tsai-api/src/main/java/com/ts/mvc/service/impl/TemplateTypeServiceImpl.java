package com.ts.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.TemplateType;
import com.ts.mvc.mapper.TemplateTypeMapper;
import com.ts.mvc.service.TemplateTypeService;
import org.springframework.stereotype.Service;

/**
 * 模板类型;(template_type)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2023-5-16
 */
@Service
public class TemplateTypeServiceImpl extends ServiceImpl<TemplateTypeMapper, TemplateType> implements TemplateTypeService {

}