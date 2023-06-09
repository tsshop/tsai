package com.ts.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.Template;
import com.ts.mvc.mapper.TemplateMapper;
import com.ts.mvc.service.TemplateService;
import org.springframework.stereotype.Service;

/**
 * 模板;(template)表服务实现类
 * @author : tsai
 * @date : 2023-5-8
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements TemplateService {

}
