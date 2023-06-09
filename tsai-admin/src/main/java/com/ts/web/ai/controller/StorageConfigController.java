package com.ts.web.ai.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ts.ai.domain.SmsConfig;
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
import com.ts.ai.domain.StorageConfig;
import com.ts.ai.service.StorageConfigService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 存储配置Controller
 *
 * @author tsai
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/ai/StorageConfig")
public class StorageConfigController extends BaseController
{
    @Autowired
    private StorageConfigService storageConfigService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询存储配置列表
     */
    @PreAuthorize("@ss.hasPermi('ai:StorageConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageConfig storageConfig)
    {
        startPage();
        List<StorageConfig> list = storageConfigService.selectStorageConfigList(storageConfig);
        return getDataTable(list);
    }

    /**
     * 导出存储配置列表
     */
    @PreAuthorize("@ss.hasPermi('ai:StorageConfig:export')")
    @Log(title = "存储配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageConfig storageConfig)
    {
        List<StorageConfig> list = storageConfigService.selectStorageConfigList(storageConfig);
        ExcelUtil<StorageConfig> util = new ExcelUtil<StorageConfig>(StorageConfig.class);
        util.exportExcel(response, list, "存储配置数据");
    }

    /**
     * 获取存储配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:StorageConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storageConfigService.selectStorageConfigById(id));
    }

    /**
     * 新增存储配置
     */
    @PreAuthorize("@ss.hasPermi('ai:StorageConfig:add')")
    @Log(title = "存储配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageConfig storageConfig)
    {
        return toAjax(storageConfigService.insertStorageConfig(storageConfig));
    }

    /**
     * 修改存储配置
     */
    @PreAuthorize("@ss.hasPermi('ai:StorageConfig:edit')")
    @Log(title = "存储配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageConfig storageConfig)
    {
        if (storageConfig.getIsUse()){
            storageConfigService.update(new LambdaUpdateWrapper<StorageConfig>()
                    .set(StorageConfig::getIsUse,false));
            redisTemplate.opsForValue().set("storage",storageConfig);
        }
        return toAjax(storageConfigService.updateStorageConfig(storageConfig));
    }

    /**
     * 删除存储配置
     */
    @PreAuthorize("@ss.hasPermi('ai:StorageConfig:remove')")
    @Log(title = "存储配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageConfigService.deleteStorageConfigByIds(ids));
    }
}
