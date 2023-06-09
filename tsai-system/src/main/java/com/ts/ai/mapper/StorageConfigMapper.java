package com.ts.ai.mapper;

import java.util.List;
import com.ts.ai.domain.StorageConfig;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 存储配置Mapper接口
 *
 * @author tsai
 * @date 2023-06-07
 */
@Mapper
public interface StorageConfigMapper extends BaseMapper<StorageConfig>
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
     * 删除存储配置
     *
     * @param id 存储配置主键
     * @return 结果
     */
    public int deleteStorageConfigById(Long id);

    /**
     * 批量删除存储配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStorageConfigByIds(Long[] ids);
}
