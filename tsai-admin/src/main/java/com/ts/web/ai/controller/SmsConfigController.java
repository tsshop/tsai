package com.ts.web.ai.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.data.redis.core.RedisTemplate;
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
import com.ts.ai.domain.SmsConfig;
import com.ts.ai.service.SmsConfigService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 短信配置Controller
 *
 * @author tsai
 * @date 2023-06-08
 */
@RestController
@RequestMapping("/ai/SmsConfig")
public class SmsConfigController extends BaseController
{
    @Autowired
    private SmsConfigService smsConfigService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询短信配置列表
     */
    @PreAuthorize("@ss.hasPermi('ai:SmsConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(SmsConfig smsConfig)
    {
        startPage();
        List<SmsConfig> list = smsConfigService.selectSmsConfigList(smsConfig);
        return getDataTable(list);
    }

    /**
     * 导出短信配置列表
     */
    @PreAuthorize("@ss.hasPermi('ai:SmsConfig:export')")
    @Log(title = "短信配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsConfig smsConfig)
    {
        List<SmsConfig> list = smsConfigService.selectSmsConfigList(smsConfig);
        ExcelUtil<SmsConfig> util = new ExcelUtil<SmsConfig>(SmsConfig.class);
        util.exportExcel(response, list, "短信配置数据");
    }

    /**
     * 获取短信配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:SmsConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(smsConfigService.selectSmsConfigById(id));
    }

    /**
     * 新增短信配置
     */
    @PreAuthorize("@ss.hasPermi('ai:SmsConfig:add')")
    @Log(title = "短信配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsConfig smsConfig)
    {
        return toAjax(smsConfigService.insertSmsConfig(smsConfig));
    }

    /**
     * 修改短信配置
     */
    @PreAuthorize("@ss.hasPermi('ai:SmsConfig:edit')")
    @Log(title = "短信配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsConfig smsConfig)
    {
        if (smsConfig.getIsUse()){
            smsConfigService.update(new LambdaUpdateWrapper<SmsConfig>()
                    .set(SmsConfig::getIsUse,false));
            redisTemplate.opsForValue().set("sms",smsConfig);
        }
        return toAjax(smsConfigService.updateSmsConfig(smsConfig));
    }

    /**
     * 删除短信配置
     */
    @PreAuthorize("@ss.hasPermi('ai:SmsConfig:remove')")
    @Log(title = "短信配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsConfigService.deleteSmsConfigByIds(ids));
    }
}
