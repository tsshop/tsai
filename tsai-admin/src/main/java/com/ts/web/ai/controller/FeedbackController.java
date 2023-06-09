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
import com.ts.ai.domain.IFeedback;
import com.ts.ai.service.IFeedbackService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 反馈Controller
 *
 * @author tsai
 * @date 2023-05-15
 */
@RestController
@RequestMapping("/ai/Feedback")
public class FeedbackController extends BaseController
{
    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 查询反馈列表
     */
    @PreAuthorize("@ss.hasPermi('ai:Feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(IFeedback feedback)
    {
        startPage();
        List<IFeedback> list = feedbackService.selectFeedbackList(feedback);
        return getDataTable(list);
    }

    /**
     * 导出反馈列表
     */
    @PreAuthorize("@ss.hasPermi('ai:Feedback:export')")
    @Log(title = "反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IFeedback feedback)
    {
        List<IFeedback> list = feedbackService.selectFeedbackList(feedback);
        ExcelUtil<IFeedback> util = new ExcelUtil<IFeedback>(IFeedback.class);
        util.exportExcel(response, list, "反馈数据");
    }

    /**
     * 获取反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:Feedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(feedbackService.selectFeedbackById(id));
    }

    /**
     * 新增反馈
     */
    @PreAuthorize("@ss.hasPermi('ai:Feedback:add')")
    @Log(title = "反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IFeedback feedback)
    {
        return toAjax(feedbackService.insertFeedback(feedback));
    }

    /**
     * 修改反馈
     */
    @PreAuthorize("@ss.hasPermi('ai:Feedback:edit')")
    @Log(title = "反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IFeedback feedback)
    {
        return toAjax(feedbackService.updateFeedback(feedback));
    }

    /**
     * 删除反馈
     */
    @PreAuthorize("@ss.hasPermi('ai:Feedback:remove')")
    @Log(title = "反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(feedbackService.deleteFeedbackByIds(ids));
    }
}
