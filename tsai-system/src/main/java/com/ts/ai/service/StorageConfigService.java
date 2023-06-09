package com.ts.ai.service;

import java.util.List;

import cn.hutool.json.JSONObject;
import com.ts.ai.domain.StorageConfig;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 存储配置Service接口
 *
 * @author tsai
 * @date 2023-06-07
 */
public interface StorageConfigService extends IService<StorageConfig>
{
    /**
     * 查询存储配置
     *
     * @param id 存储配置主键
     * @return 存储配置
     */
    public StorageConfig selectStorageConfigById(Long id);

    /**
     * 查询存储配置列表
     *
     * @param storageConfig 存储配置
     * @return 存储配置集合
     */
    public List<StorageConfig> selectStorageConfigList(StorageConfig storageConfig);

    /**
     * 新增存储配置
     *
     * @param storageConfig 存储配置
     * @return 结果
     */
    public int insertStorageConfig(StorageConfig storageConfig);

    /**
     * 修改存储配置
     *
     * @param storageConfig 存储配置
     * @return 结果
     */
    public int updateStorageConfig(StorageConfig storageConfig);

    /**
     * 批量删除存储配置
     *
     * @param ids 需要删除的存储配置主键集合
     * @return 结果
     */
    public int deleteStorageConfigByIds(Long[] ids);

    /**
     * 删除存储配置信息
     *
     * @param id 存储配置主键
     * @return 结果
     */
    public int deleteStorageConfigById(Long id);

    JSONObject getConfigByType(String type);
}
