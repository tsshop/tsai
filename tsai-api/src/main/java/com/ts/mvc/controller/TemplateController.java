package com.ts.mvc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.config.exception.ApiResult;
import com.ts.config.token.PassToken;
import com.ts.mvc.domain.Template;
import com.ts.mvc.domain.TemplateType;
import com.ts.mvc.domain.User;
import com.ts.mvc.service.TemplateService;
import com.ts.mvc.service.TemplateTypeService;
import com.ts.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 模板;(template)表控制层
 * @author : tsai
 * @date : 2023-5-10
 */
@RestController
@RequestMapping("/template")
public class TemplateController{
    @Autowired
    private TemplateService templateService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private TemplateTypeService templateTypeService;

    /**
     * 模板列表
     * @return
     */
    @GetMapping("/list")
    public ApiResult list(){
        List<TemplateType> templateTypeList = templateTypeService.list(new LambdaQueryWrapper<TemplateType>()
                .eq(TemplateType::getIsUse, true)
                .orderByAsc(TemplateType::getOrderBy));
        templateTypeList.forEach(item -> {
            List<Template> templateList = templateService.list(new LambdaQueryWrapper<Template>()
                    .eq(Template::getTypeId,item.getId())
                    .eq(Template::getIsUse, true));
            item.setTemplateList(templateList);
        });
        return ApiResult.ok(templateTypeList);
    }

    /**
     * 模板详情
     * @param id
     * @return
     */
    @GetMapping("/view")
    public ApiResult view(Long id){
        return ApiResult.ok(templateService.getById(id));
    }

    /**
     * 首页展示模板
     * @return
     */
    @GetMapping("/indexList")
    @PassToken
    public ApiResult indexList(){
        List<Template> templateList = templateService.list(new LambdaQueryWrapper<Template>()
                    .eq(Template::getIsIndex,true)
                    .eq(Template::getIsUse, true));
        return ApiResult.ok(templateList);
    }

}