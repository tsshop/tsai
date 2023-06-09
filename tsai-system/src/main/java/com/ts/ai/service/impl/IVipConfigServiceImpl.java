package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.IVipConfigMapper;
import com.ts.ai.domain.IVipConfig;
import com.ts.ai.service.IVipConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * vip套餐Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class IVipConfigServiceImpl extends ServiceImpl<IVipConfigMapper, IVipConfig> implements IVipConfigService
{
    @Autowired
    private IVipConfigMapper vipConfigMapper;

    /**
     * 查询vip套餐
     *
     * @param id vip套餐主键
     * @return vip套餐
     */
    @Override
    public IVipConfig selectVipConfigById(Long id)
    {
        return vipConfigMapper.selectVipConfigById(id);
    }

    /**
     * 查询vip套餐列表
     *
     * @param vipConfig vip套餐
     * @return vip套餐
     */
    @Override
    public List<IVipConfig> selectVipConfigList(IVipConfig vipConfig)
    {
        return vipConfigMapper.selectVipConfigList(vipConfig);
    }

    /**
     * 新增vip套餐
     *
     * @param vipConfig vip套餐
     * @return 结果
     */
    @Override
    public int insertVipConfig(IVipConfig vipConfig)
    {
        vipConfig.setCreateTime(DateUtils.getNowDate());
        return vipConfigMapper.insertVipConfig(vipConfig);
    }

    /**
     * 修改vip套餐
     *
     * @param vipConfig vip套餐
     * @return 结果
     */
    @Override
    public int updateVipConfig(IVipConfig vipConfig)
    {
        vipConfig.setUpdateTime(DateUtils.getNowDate());
        return vipConfigMapper.updateVipConfig(vipConfig);
    }

    /**
     * 批量删除vip套餐
     *
     * @param ids 需要删除的vip套餐主键
     * @return 结果
     */
    @Override
    public int deleteVipConfigByIds(Long[] ids)
    {
        return vipConfigMapper.deleteVipConfigByIds(ids);
    }

    /**
     * 删除vip套餐信息
     *
     * @param id vip套餐主键
     * @return 结果
     */
    @Override
    public int deleteVipConfigById(Long id)
    {
        return vipConfigMapper.deleteVipConfigById(id);
    }
}
