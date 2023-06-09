package com.ts.web.controller.system;

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
import com.ts.system.domain.SysDistrict;
import com.ts.system.service.ISysDistrictService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 中国省市区数据Controller
 *
 * @author xu
 * @date 2022-11-11
 */
@RestController
@RequestMapping("/system/district")
public class SysDistrictController extends BaseController
{
    @Autowired
    private ISysDistrictService sysDistrictService;

    /**
     * 查询中国省市区数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:district:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDistrict sysDistrict)
    {
        startPage();
        List<SysDistrict> list = sysDistrictService.selectSysDistrictList(sysDistrict);
        return getDataTable(list);
    }

    /**
     * 导出中国省市区数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:district:export')")
    @Log(title = "中国省市区数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDistrict sysDistrict)
    {
        List<SysDistrict> list = sysDistrictService.selectSysDistrictList(sysDistrict);
        ExcelUtil<SysDistrict> util = new ExcelUtil<SysDistrict>(SysDistrict.class);
        util.exportExcel(response, list, "中国省市区数据数据");
    }

    /**
     * 获取中国省市区数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:district:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(sysDistrictService.selectSysDistrictById(id));
    }

    /**
     * 新增中国省市区数据
     */
    @PreAuthorize("@ss.hasPermi('system:district:add')")
    @Log(title = "中国省市区数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDistrict sysDistrict)
    {
        return toAjax(sysDistrictService.insertSysDistrict(sysDistrict));
    }

    /**
     * 修改中国省市区数据
     */
    @PreAuthorize("@ss.hasPermi('system:district:edit')")
    @Log(title = "中国省市区数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDistrict sysDistrict)
    {
        return toAjax(sysDistrictService.updateSysDistrict(sysDistrict));
    }

    /**
     * 删除中国省市区数据
     */
    @PreAuthorize("@ss.hasPermi('system:district:remove')")
    @Log(title = "中国省市区数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(sysDistrictService.deleteSysDistrictByIds(ids));
    }
}
