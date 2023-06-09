package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.IConfigMapper;
import com.ts.ai.domain.IConfig;
import com.ts.ai.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 配置Service业务层处理
 *
 * @author tsai
 * @date 2023-05-16
 */
@Service
public class IConfigServiceImpl extends ServiceImpl<IConfigMapper, IConfig> implements IConfigService
{
    @Autowired
    private IConfigMapper configMapper;

    /**
     * 查询配置
     *
     * @param id 配置主键
     * @return 配置
     */
    @Override
    public IConfig selectConfigById(Long id)
    {
        return configMapper.selectConfigById(id);
    }

    /**
     * 查询配置列表
     *
     * @param config 配置
     * @return 配置
     */
    @Override
    public List<IConfig> selectConfigList(IConfig config)
    {
        return configMapper.selectConfigList(config);
    }

    /**
     * 新增配置
     *
     * @param config 配置
     * @return 结果
     */
    @Override
    public int insertConfig(IConfig config)
    {
        config.setCreateTime(DateUtils.getNowDate());
        return configMapper.insertConfig(config);
    }

    /**
     * 修改配置
     *
     * @param config 配置
     * @return 结果
     */
    @Override
    public int updateConfig(IConfig config)
    {
        config.setUpdateTime(DateUtils.getNowDate());
        return configMapper.updateConfig(config);
    }

    /**
     * 批量删除配置
     *
     * @param ids 需要删除的配置主键
     * @return 结果
     */
    @Override
    public int deleteConfigByIds(Long[] ids)
    {
        return configMapper.deleteConfigByIds(ids);
    }

    /**
     * 删除配置信息
     *
     * @param id 配置主键
     * @return 结果
     */
    @Override
    public int deleteConfigById(Long id)
    {
        return configMapper.deleteConfigById(id);
    }
}
