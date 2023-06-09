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
import com.ts.ai.domain.IVipConfig;
import com.ts.ai.service.IVipConfigService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * vip套餐Controller
 *
 * @author tsai
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/ai/VipConfig")
public class VipConfigController extends BaseController
{
    @Autowired
    private IVipConfigService vipConfigService;

    /**
     * 查询vip套餐列表
     */
    @PreAuthorize("@ss.hasPermi('ai:VipConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(IVipConfig vipConfig)
    {
        startPage();
        List<IVipConfig> list = vipConfigService.selectVipConfigList(vipConfig);
        return getDataTable(list);
    }

    /**
     * 导出vip套餐列表
     */
    @PreAuthorize("@ss.hasPermi('ai:VipConfig:export')")
    @Log(title = "vip套餐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IVipConfig vipConfig)
    {
        List<IVipConfig> list = vipConfigService.selectVipConfigList(vipConfig);
        ExcelUtil<IVipConfig> util = new ExcelUtil<IVipConfig>(IVipConfig.class);
        util.exportExcel(response, list, "vip套餐数据");
    }

    /**
     * 获取vip套餐详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:VipConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(vipConfigService.selectVipConfigById(id));
    }

    /**
     * 新增vip套餐
     */
    @PreAuthorize("@ss.hasPermi('ai:VipConfig:add')")
    @Log(title = "vip套餐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IVipConfig vipConfig)
    {
        return toAjax(vipConfigService.insertVipConfig(vipConfig));
    }

    /**
     * 修改vip套餐
     */
    @PreAuthorize("@ss.hasPermi('ai:VipConfig:edit')")
    @Log(title = "vip套餐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IVipConfig vipConfig)
    {
        return toAjax(vipConfigService.updateVipConfig(vipConfig));
    }

    /**
     * 删除vip套餐
     */
    @PreAuthorize("@ss.hasPermi('ai:VipConfig:remove')")
    @Log(title = "vip套餐", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vipConfigService.deleteVipConfigByIds(ids));
    }
}
