package com.ts.ai.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.ai.domain.TsProfit;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.IOrderVipMapper;
import com.ts.ai.domain.IOrderVip;
import com.ts.ai.service.IOrderVipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * VIP订单Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class IOrderVipServiceImpl extends ServiceImpl<IOrderVipMapper, IOrderVip> implements IOrderVipService
{
    @Autowired
    private IOrderVipMapper orderVipMapper;

    /**
     * 查询VIP订单
     *
     * @param id VIP订单主键
     * @return VIP订单
     */
    @Override
    public IOrderVip selectOrderVipById(Long id)
    {
        return orderVipMapper.selectOrderVipById(id);
    }

    /**
     * 查询VIP订单列表
     *
     * @param orderVip VIP订单
     * @return VIP订单
     */
    @Override
    public List<IOrderVip> selectOrderVipList(IOrderVip orderVip)
    {
        return orderVipMapper.selectOrderVipList(orderVip);
    }

    /**
     * 新增VIP订单
     *
     * @param orderVip VIP订单
     * @return 结果
     */
    @Override
    public int insertOrderVip(IOrderVip orderVip)
    {
        orderVip.setCreateTime(DateUtils.getNowDate());
        return orderVipMapper.insertOrderVip(orderVip);
    }

    /**
     * 修改VIP订单
     *
     * @param orderVip VIP订单
     * @return 结果
     */
    @Override
    public int updateOrderVip(IOrderVip orderVip)
    {
        orderVip.setUpdateTime(DateUtils.getNowDate());
        return orderVipMapper.updateOrderVip(orderVip);
    }

    /**
     * 批量删除VIP订单
     *
     * @param ids 需要删除的VIP订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderVipByIds(Long[] ids)
    {
        return orderVipMapper.deleteOrderVipByIds(ids);
    }

    /**
     * 删除VIP订单信息
     *
     * @param id VIP订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderVipById(Long id)
    {
        return orderVipMapper.deleteOrderVipById(id);
    }

    @Override
    public List<TsProfit> monthSum(LambdaQueryWrapper<IOrderVip> wrapper) {
        return orderVipMapper.monthSum(wrapper);
    }

    @Override
    public List<TsProfit> daySum(LambdaQueryWrapper<IOrderVip> wrapper) {
        return orderVipMapper.daySum(wrapper);
    }

    @Override
    public List<TsProfit> dayWeekSum(LambdaQueryWrapper<IOrderVip> wrapper) {
        return orderVipMapper.dayWeekSum(wrapper);
    }

    @Override
    public List<TsProfit> hourSum(LambdaQueryWrapper<IOrderVip> wrapper) {
        return orderVipMapper.hourSum(wrapper);
    }

    @Override
    public List<TsProfit> dayMonthSum(LambdaQueryWrapper<IOrderVip> wrapper) {
        return orderVipMapper.dayMonthSum(wrapper);
    }
}
