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
import com.ts.ai.domain.ISessionLog;
import com.ts.ai.service.ISessionLogService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 会话记录Controller
 *
 * @author tsai
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/ai/SessionLog")
public class SessionLogController extends BaseController
{
    @Autowired
    private ISessionLogService sessionLogService;

    /**
     * 查询会话记录列表
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(ISessionLog sessionLog)
    {
        startPage();
        List<ISessionLog> list = sessionLogService.selectSessionLogList(sessionLog);
        return getDataTable(list);
    }

    /**
     * 导出会话记录列表
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionLog:export')")
    @Log(title = "会话记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ISessionLog sessionLog)
    {
        List<ISessionLog> list = sessionLogService.selectSessionLogList(sessionLog);
        ExcelUtil<ISessionLog> util = new ExcelUtil<ISessionLog>(ISessionLog.class);
        util.exportExcel(response, list, "会话记录数据");
    }

    /**
     * 获取会话记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionLog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sessionLogService.selectSessionLogById(id));
    }

    /**
     * 新增会话记录
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionLog:add')")
    @Log(title = "会话记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ISessionLog sessionLog)
    {
        return toAjax(sessionLogService.insertSessionLog(sessionLog));
    }

    /**
     * 修改会话记录
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionLog:edit')")
    @Log(title = "会话记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ISessionLog sessionLog)
    {
        return toAjax(sessionLogService.updateSessionLog(sessionLog));
    }

    /**
     * 删除会话记录
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionLog:remove')")
    @Log(title = "会话记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sessionLogService.deleteSessionLogByIds(ids));
    }
}
