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
import com.ts.ai.domain.IOrderVip;
import com.ts.ai.service.IOrderVipService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * VIP订单Controller
 *
 * @author tsai
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/ai/OrderVip")
public class OrderVipController extends BaseController
{
    @Autowired
    private IOrderVipService orderVipService;

    /**
     * 查询VIP订单列表
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderVip:list')")
    @GetMapping("/list")
    public TableDataInfo list(IOrderVip orderVip)
    {
        startPage();
        List<IOrderVip> list = orderVipService.selectOrderVipList(orderVip);
        return getDataTable(list);
    }

    /**
     * 导出VIP订单列表
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderVip:export')")
    @Log(title = "VIP订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IOrderVip orderVip)
    {
        List<IOrderVip> list = orderVipService.selectOrderVipList(orderVip);
        ExcelUtil<IOrderVip> util = new ExcelUtil<IOrderVip>(IOrderVip.class);
        util.exportExcel(response, list, "VIP订单数据");
    }

    /**
     * 获取VIP订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderVip:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderVipService.selectOrderVipById(id));
    }

    /**
     * 新增VIP订单
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderVip:add')")
    @Log(title = "VIP订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IOrderVip orderVip)
    {
        return toAjax(orderVipService.insertOrderVip(orderVip));
    }

    /**
     * 修改VIP订单
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderVip:edit')")
    @Log(title = "VIP订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IOrderVip orderVip)
    {
        return toAjax(orderVipService.updateOrderVip(orderVip));
    }

    /**
     * 删除VIP订单
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderVip:remove')")
    @Log(title = "VIP订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderVipService.deleteOrderVipByIds(ids));
    }
}
