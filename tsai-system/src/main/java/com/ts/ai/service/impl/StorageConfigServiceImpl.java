package com.ts.ai.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.StorageConfigMapper;
import com.ts.ai.domain.StorageConfig;
import com.ts.ai.service.StorageConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 存储配置Service业务层处理
 *
 * @author tsai
 * @date 2023-06-07
 */
@Service
public class StorageConfigServiceImpl extends ServiceImpl<StorageConfigMapper, StorageConfig> implements StorageConfigService
{
    @Autowired
    private StorageConfigMapper storageConfigMapper;

    /**
     * 查询存储配置
     *
     * @param id 存储配置主键
     * @return 存储配置
     */
    @Override
    public StorageConfig selectStorageConfigById(Long id)
    {
        return storageConfigMapper.selectStorageConfigById(id);
    }

    /**
     * 查询存储配置列表
     *
     * @param storageConfig 存储配置
     * @return 存储配置
     */
    @Override
    public List<StorageConfig> selectStorageConfigList(StorageConfig storageConfig)
    {
        return storageConfigMapper.selectStorageConfigList(storageConfig);
    }

    /**
     * 新增存储配置
     *
     * @param storageConfig 存储配置
     * @return 结果
     */
    @Override
    public int insertStorageConfig(StorageConfig storageConfig)
    {
        storageConfig.setCreateTime(DateUtils.getNowDate());
        return storageConfigMapper.insertStorageConfig(storageConfig);
    }

    /**
     * 修改存储配置
     *
     * @param storageConfig 存储配置
     * @return 结果
     */
    @Override
    public int updateStorageConfig(StorageConfig storageConfig)
    {
        storageConfig.setUpdateTime(DateUtils.getNowDate());
        return storageConfigMapper.updateStorageConfig(storageConfig);
    }

    /**
     * 批量删除存储配置
     *
     * @param ids 需要删除的存储配置主键
     * @return 结果
     */
    @Override
    public int deleteStorageConfigByIds(Long[] ids)
    {
        return storageConfigMapper.deleteStorageConfigByIds(ids);
    }

    /**
     * 删除存储配置信息
     *
     * @param id 存储配置主键
     * @return 结果
     */
    @Override
    public int deleteStorageConfigById(Long id)
    {
        return storageConfigMapper.deleteStorageConfigById(id);
    }

    @Override
    public JSONObject getConfigByType(String type) {
        StorageConfig config = getOne(new LambdaQueryWrapper<StorageConfig>()
                .eq(StorageConfig::getType, type)
                .eq(StorageConfig::getIsUse, true)
                .last(" limit 1 "));
        if (ObjectUtil.isNotEmpty(config)){
            return JSONUtil.parseObj(config.getConfig());
        }
        return null;
    }
}
