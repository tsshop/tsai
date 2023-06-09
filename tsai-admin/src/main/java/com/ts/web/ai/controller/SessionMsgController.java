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
import com.ts.ai.domain.ISessionMsg;
import com.ts.ai.service.ISessionMsgService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 会话聊天记录Controller
 *
 * @author tsai
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/ai/SessionMsg")
public class SessionMsgController extends BaseController
{
    @Autowired
    private ISessionMsgService sessionMsgService;

    /**
     * 查询会话聊天记录列表
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionMsg:list')")
    @GetMapping("/list")
    public TableDataInfo list(ISessionMsg sessionMsg)
    {
        startPage();
        List<ISessionMsg> list = sessionMsgService.selectSessionMsgList(sessionMsg);
        return getDataTable(list);
    }

    /**
     * 导出会话聊天记录列表
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionMsg:export')")
    @Log(title = "会话聊天记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ISessionMsg sessionMsg)
    {
        List<ISessionMsg> list = sessionMsgService.selectSessionMsgList(sessionMsg);
        ExcelUtil<ISessionMsg> util = new ExcelUtil<ISessionMsg>(ISessionMsg.class);
        util.exportExcel(response, list, "会话聊天记录数据");
    }

    /**
     * 获取会话聊天记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionMsg:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sessionMsgService.selectSessionMsgById(id));
    }

    /**
     * 新增会话聊天记录
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionMsg:add')")
    @Log(title = "会话聊天记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ISessionMsg sessionMsg)
    {
        return toAjax(sessionMsgService.insertSessionMsg(sessionMsg));
    }

    /**
     * 修改会话聊天记录
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionMsg:edit')")
    @Log(title = "会话聊天记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ISessionMsg sessionMsg)
    {
        return toAjax(sessionMsgService.updateSessionMsg(sessionMsg));
    }

    /**
     * 删除会话聊天记录
     */
    @PreAuthorize("@ss.hasPermi('ai:SessionMsg:remove')")
    @Log(title = "会话聊天记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sessionMsgService.deleteSessionMsgByIds(ids));
    }
}
