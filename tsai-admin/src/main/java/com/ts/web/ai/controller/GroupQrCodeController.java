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
import com.ts.ai.domain.IGroupQrCode;
import com.ts.ai.service.IGroupQrCodeService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 群二维码Controller
 *
 * @author tsai
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/ai/GroupQrCode")
public class GroupQrCodeController extends BaseController
{
    @Autowired
    private IGroupQrCodeService groupQrCodeService;

    /**
     * 查询群二维码列表
     */
    @PreAuthorize("@ss.hasPermi('ai:GroupQrCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(IGroupQrCode groupQrCode)
    {
        startPage();
        List<IGroupQrCode> list = groupQrCodeService.selectGroupQrCodeList(groupQrCode);
        return getDataTable(list);
    }

    /**
     * 导出群二维码列表
     */
    @PreAuthorize("@ss.hasPermi('ai:GroupQrCode:export')")
    @Log(title = "群二维码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IGroupQrCode groupQrCode)
    {
        List<IGroupQrCode> list = groupQrCodeService.selectGroupQrCodeList(groupQrCode);
        ExcelUtil<IGroupQrCode> util = new ExcelUtil<IGroupQrCode>(IGroupQrCode.class);
        util.exportExcel(response, list, "群二维码数据");
    }

    /**
     * 获取群二维码详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:GroupQrCode:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(groupQrCodeService.selectGroupQrCodeById(id));
    }

    /**
     * 新增群二维码
     */
    @PreAuthorize("@ss.hasPermi('ai:GroupQrCode:add')")
    @Log(title = "群二维码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IGroupQrCode groupQrCode)
    {
        return toAjax(groupQrCodeService.insertGroupQrCode(groupQrCode));
    }

    /**
     * 修改群二维码
     */
    @PreAuthorize("@ss.hasPermi('ai:GroupQrCode:edit')")
    @Log(title = "群二维码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IGroupQrCode groupQrCode)
    {
        return toAjax(groupQrCodeService.updateGroupQrCode(groupQrCode));
    }

    /**
     * 删除群二维码
     */
    @PreAuthorize("@ss.hasPermi('ai:GroupQrCode:remove')")
    @Log(title = "群二维码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(groupQrCodeService.deleteGroupQrCodeByIds(ids));
    }
}
