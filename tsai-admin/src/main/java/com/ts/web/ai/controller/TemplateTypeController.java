package com.ts.web.ai.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ts.ai.domain.ITemplateType;
import com.ts.ai.service.ITemplateTypeService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 模板类型Controller
 *
 * @author tsai
 * @date 2023-05-16
 */
@RestController
@RequestMapping("/ai/TemplateType")
public class TemplateTypeController extends BaseController
{
    @Autowired
    private ITemplateTypeService templateTypeService;

    /**
     * 查询模板类型列表
     */
    @PreAuthorize("@ss.hasPermi('ai:TemplateType:list')")
    @GetMapping("/list")
    public TableDataInfo list(ITemplateType templateType)
    {
        startPage();
        List<ITemplateType> list = templateTypeService.selectTemplateTypeList(templateType);
        return getDataTable(list);
    }

    /**
     * 导出模板类型列表
     */
    @PreAuthorize("@ss.hasPermi('ai:TemplateType:export')")
    @Log(title = "模板类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ITemplateType templateType)
    {
        List<ITemplateType> list = templateTypeService.selectTemplateTypeList(templateType);
        ExcelUtil<ITemplateType> util = new ExcelUtil<ITemplateType>(ITemplateType.class);
        util.exportExcel(response, list, "模板类型数据");
    }

    /**
     * 获取模板类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:TemplateType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(templateTypeService.selectTemplateTypeById(id));
    }

    /**
     * 新增模板类型
     */
    @PreAuthorize("@ss.hasPermi('ai:TemplateType:add')")
    @Log(title = "模板类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ITemplateType templateType)
    {
        return toAjax(templateTypeService.insertTemplateType(templateType));
    }

    /**
     * 修改模板类型
     */
    @PreAuthorize("@ss.hasPermi('ai:TemplateType:edit')")
    @Log(title = "模板类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ITemplateType templateType)
    {
        return toAjax(templateTypeService.updateTemplateType(templateType));
    }

    /**
     * 删除模板类型
     */
    @PreAuthorize("@ss.hasPermi('ai:TemplateType:remove')")
    @Log(title = "模板类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(templateTypeService.deleteTemplateTypeByIds(ids));
    }
}
