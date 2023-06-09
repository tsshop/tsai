package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.INumberConfigMapper;
import com.ts.ai.domain.INumberConfig;
import com.ts.ai.service.INumberConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 使用次数套餐Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class INumberConfigServiceImpl extends ServiceImpl<INumberConfigMapper, INumberConfig> implements INumberConfigService
{
    @Autowired
    private INumberConfigMapper numberConfigMapper;

    /**
     * 查询使用次数套餐
     *
     * @param id 使用次数套餐主键
     * @return 使用次数套餐
     */
    @Override
    public INumberConfig selectNumberConfigById(Long id)
    {
        return numberConfigMapper.selectNumberConfigById(id);
    }

    /**
     * 查询使用次数套餐列表
     *
     * @param numberConfig 使用次数套餐
     * @return 使用次数套餐
     */
    @Override
    public List<INumberConfig> selectNumberConfigList(INumberConfig numberConfig)
    {
        return numberConfigMapper.selectNumberConfigList(numberConfig);
    }

    /**
     * 新增使用次数套餐
     *
     * @param numberConfig 使用次数套餐
     * @return 结果
     */
    @Override
    public int insertNumberConfig(INumberConfig numberConfig)
    {
        numberConfig.setCreateTime(DateUtils.getNowDate());
        return numberConfigMapper.insertNumberConfig(numberConfig);
    }

    /**
     * 修改使用次数套餐
     *
     * @param numberConfig 使用次数套餐
     * @return 结果
     */
    @Override
    public int updateNumberConfig(INumberConfig numberConfig)
    {
        numberConfig.setUpdateTime(DateUtils.getNowDate());
        return numberConfigMapper.updateNumberConfig(numberConfig);
    }

    /**
     * 批量删除使用次数套餐
     *
     * @param ids 需要删除的使用次数套餐主键
     * @return 结果
     */
    @Override
    public int deleteNumberConfigByIds(Long[] ids)
    {
        return numberConfigMapper.deleteNumberConfigByIds(ids);
    }

    /**
     * 删除使用次数套餐信息
     *
     * @param id 使用次数套餐主键
     * @return 结果
     */
    @Override
    public int deleteNumberConfigById(Long id)
    {
        return numberConfigMapper.deleteNumberConfigById(id);
    }
}
