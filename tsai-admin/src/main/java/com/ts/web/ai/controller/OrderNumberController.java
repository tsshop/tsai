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
import com.ts.ai.domain.IOrderNumber;
import com.ts.ai.service.IOrderNumberService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 使用次数订单Controller
 *
 * @author tsai
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/ai/OrderNumber")
public class OrderNumberController extends BaseController
{
    @Autowired
    private IOrderNumberService orderNumberService;

    /**
     * 查询使用次数订单列表
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderNumber:list')")
    @GetMapping("/list")
    public TableDataInfo list(IOrderNumber orderNumber)
    {
        startPage();
        List<IOrderNumber> list = orderNumberService.selectOrderNumberList(orderNumber);
        return getDataTable(list);
    }

    /**
     * 导出使用次数订单列表
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderNumber:export')")
    @Log(title = "使用次数订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IOrderNumber orderNumber)
    {
        List<IOrderNumber> list = orderNumberService.selectOrderNumberList(orderNumber);
        ExcelUtil<IOrderNumber> util = new ExcelUtil<IOrderNumber>(IOrderNumber.class);
        util.exportExcel(response, list, "使用次数订单数据");
    }

    /**
     * 获取使用次数订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderNumber:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderNumberService.selectOrderNumberById(id));
    }

    /**
     * 新增使用次数订单
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderNumber:add')")
    @Log(title = "使用次数订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IOrderNumber orderNumber)
    {
        return toAjax(orderNumberService.insertOrderNumber(orderNumber));
    }

    /**
     * 修改使用次数订单
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderNumber:edit')")
    @Log(title = "使用次数订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IOrderNumber orderNumber)
    {
        return toAjax(orderNumberService.updateOrderNumber(orderNumber));
    }

    /**
     * 删除使用次数订单
     */
    @PreAuthorize("@ss.hasPermi('ai:OrderNumber:remove')")
    @Log(title = "使用次数订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderNumberService.deleteOrderNumberByIds(ids));
    }
}
