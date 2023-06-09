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
import com.ts.ai.domain.INumberConfig;
import com.ts.ai.service.INumberConfigService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 使用次数套餐Controller
 *
 * @author tsai
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/ai/NumberConfig")
public class NumberConfigController extends BaseController
{
    @Autowired
    private INumberConfigService numberConfigService;

    /**
     * 查询使用次数套餐列表
     */
    @PreAuthorize("@ss.hasPermi('ai:NumberConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(INumberConfig numberConfig)
    {
        startPage();
        List<INumberConfig> list = numberConfigService.selectNumberConfigList(numberConfig);
        return getDataTable(list);
    }

    /**
     * 导出使用次数套餐列表
     */
    @PreAuthorize("@ss.hasPermi('ai:NumberConfig:export')")
    @Log(title = "使用次数套餐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, INumberConfig numberConfig)
    {
        List<INumberConfig> list = numberConfigService.selectNumberConfigList(numberConfig);
        ExcelUtil<INumberConfig> util = new ExcelUtil<INumberConfig>(INumberConfig.class);
        util.exportExcel(response, list, "使用次数套餐数据");
    }

    /**
     * 获取使用次数套餐详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:NumberConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(numberConfigService.selectNumberConfigById(id));
    }

    /**
     * 新增使用次数套餐
     */
    @PreAuthorize("@ss.hasPermi('ai:NumberConfig:add')")
    @Log(title = "使用次数套餐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody INumberConfig numberConfig)
    {
        return toAjax(numberConfigService.insertNumberConfig(numberConfig));
    }

    /**
     * 修改使用次数套餐
     */
    @PreAuthorize("@ss.hasPermi('ai:NumberConfig:edit')")
    @Log(title = "使用次数套餐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody INumberConfig numberConfig)
    {
        return toAjax(numberConfigService.updateNumberConfig(numberConfig));
    }

    /**
     * 删除使用次数套餐
     */
    @PreAuthorize("@ss.hasPermi('ai:NumberConfig:remove')")
    @Log(title = "使用次数套餐", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(numberConfigService.deleteNumberConfigByIds(ids));
    }
}
