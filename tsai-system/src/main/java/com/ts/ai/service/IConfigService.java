package com.ts.ai.service;

import java.util.List;
import com.ts.ai.domain.IConfig;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 配置Service接口
 *
 * @author tsai
 * @date 2023-05-16
 */
public interface IConfigService extends IService<IConfig>
{
    /**
     * 查询配置
     *
     * @param id 配置主键
     * @return 配置
     */
    public IConfig selectConfigById(Long id);

    /**
     * 查询配置列表
     *
     * @param config 配置
     * @return 配置集合
     */
    public List<IConfig> selectConfigList(IConfig config);

    /**
     * 新增配置
     *
     * @param config 配置
     * @return 结果
     */
    public int insertConfig(IConfig config);

    /**
     * 修改配置
     *
     * @param config 配置
     * @return 结果
     */
    public int updateConfig(IConfig config);

    /**
     * 批量删除配置
     *
     * @param ids 需要删除的配置主键集合
     * @return 结果
     */
    public int deleteConfigByIds(Long[] ids);

    /**
     * 删除配置信息
     *
     * @param id 配置主键
     * @return 结果
     */
    public int deleteConfigById(Long id);
}
