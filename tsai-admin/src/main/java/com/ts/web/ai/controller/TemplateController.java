package com.ts.web.ai.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.ai.domain.ISessionMsg;
import com.ts.ai.domain.ITemplateType;
import com.ts.ai.service.ISessionMsgService;
import com.ts.ai.service.ITemplateTypeService;
import com.ts.common.enums.SystemConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ts.common.annotation.Log;
import com.ts.common.core.controller.BaseController;
import com.ts.common.core.domain.AjaxResult;
import com.ts.common.enums.BusinessType;
import com.ts.ai.domain.ITemplate;
import com.ts.ai.service.ITemplateService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 模板Controller
 *
 * @author tsai
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/ai/Template")
public class TemplateController extends BaseController
{
    @Autowired
    private ITemplateService templateService;

    @Autowired
    private ITemplateTypeService templateTypeService;

    @Autowired
    private ISessionMsgService sessionMsgService;

    /**
     * 查询模板列表
     */
    @PreAuthorize("@ss.hasPermi('ai:Template:list')")
    @GetMapping("/list")
    public TableDataInfo list(ITemplate template)
    {
        startPage();
        List<ITemplate> list = templateService.selectTemplateList(template);
        list.forEach(item -> {
            Long goodCount = sessionMsgService.count(new LambdaQueryWrapper<ISessionMsg>()
                    .eq(ISessionMsg::getTemplateId,item.getId())
                    .eq(ISessionMsg::getScore, 2));
            Long badCount = sessionMsgService.count(new LambdaQueryWrapper<ISessionMsg>()
                    .eq(ISessionMsg::getTemplateId,item.getId())
                    .eq(ISessionMsg::getScore, 3));
            ITemplateType iTemplateType = Optional.ofNullable(templateTypeService.selectTemplateTypeById(item.getTypeId()))
                    .orElseGet(() -> new ITemplateType());
            item.setType(iTemplateType.getName());
            item.setGoodScore(goodCount);
            item.setBadScore(badCount);
        });
        return getDataTable(list);
    }

    /**
     * 导出模板列表
     */
    @PreAuthorize("@ss.hasPermi('ai:Template:export')")
    @Log(title = "模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ITemplate template)
    {
        List<ITemplate> list = templateService.selectTemplateList(template);
        ExcelUtil<ITemplate> util = new ExcelUtil<ITemplate>(ITemplate.class);
        util.exportExcel(response, list, "模板数据");
    }

    /**
     * 获取模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:Template:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(templateService.selectTemplateById(id));
    }

    /**
     * 新增模板
     */
    @PreAuthorize("@ss.hasPermi('ai:Template:add')")
    @Log(title = "模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ITemplate template)
    {
        return toAjax(templateService.insertTemplate(template));
    }

    /**
     * 修改模板
     */
    @PreAuthorize("@ss.hasPermi('ai:Template:edit')")
    @Log(title = "模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ITemplate template)
    {
        return toAjax(templateService.updateTemplate(template));
    }

    /**
     * 删除模板
     */
    @PreAuthorize("@ss.hasPermi('ai:Template:remove')")
    @Log(title = "模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(templateService.deleteTemplateByIds(ids));
    }
}
