package com.ts.web.ai.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.config.PassToken;
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
import com.ts.ai.domain.IConfig;
import com.ts.ai.service.IConfigService;
import com.ts.common.utils.poi.ExcelUtil;
import com.ts.common.core.page.TableDataInfo;

/**
 * 配置Controller
 *
 * @author tsai
 * @date 2023-05-16
 */
@RestController
@RequestMapping("/ai/Config")
public class ConfigController extends BaseController
{
    @Autowired
    private IConfigService configService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询配置列表
     */
    @PreAuthorize("@ss.hasPermi('ai:Config:list')")
    @GetMapping("/list")
    public TableDataInfo list(IConfig config)
    {
        startPage();
        List<IConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    /**
     * 导出配置列表
     */
    @PreAuthorize("@ss.hasPermi('ai:Config:export')")
    @Log(title = "配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IConfig config)
    {
        List<IConfig> list = configService.selectConfigList(config);
        ExcelUtil<IConfig> util = new ExcelUtil<IConfig>(IConfig.class);
        util.exportExcel(response, list, "配置数据");
    }

    /**
     * 获取配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:Config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(configService.selectConfigById(id));
    }

    /**
     * 新增配置
     */
    @PreAuthorize("@ss.hasPermi('ai:Config:add')")
    @Log(title = "配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IConfig config)
    {
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改配置
     */
    @PreAuthorize("@ss.hasPermi('ai:Config:edit')")
    @Log(title = "配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IConfig config)
    {
        redisTemplate.delete("config:"+config.getConfigKey());
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 删除配置
     */
    @PreAuthorize("@ss.hasPermi('ai:Config:remove')")
    @Log(title = "配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(configService.deleteConfigByIds(ids));
    }

    /**
     * 删除配置
     */
    @PassToken
    @GetMapping("/getProjectConfig")
    public AjaxResult getProjectConfig()
    {
        IConfig background = configService.getOne(new LambdaQueryWrapper<IConfig>()
                .in(IConfig::getConfigKey, "background"));
        return AjaxResult.success(background);
    }

}
