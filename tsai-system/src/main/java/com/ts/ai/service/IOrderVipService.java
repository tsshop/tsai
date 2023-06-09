package com.ts.ai.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.ai.domain.IOrderVip;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ts.ai.domain.TsProfit;


/**
 * VIP订单Service接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IOrderVipService extends IService<IOrderVip>
{
    /**
     * 查询VIP订单
     *
     * @param id VIP订单主键
     * @return VIP订单
     */
    public IOrderVip selectOrderVipById(Long id);

    /**
     * 查询VIP订单列表
     *
     * @param orderVip VIP订单
     * @return VIP订单集合
     */
    public List<IOrderVip> selectOrderVipList(IOrderVip orderVip);

    /**
     * 新增VIP订单
     *
     * @param orderVip VIP订单
     * @return 结果
     */
    public int insertOrderVip(IOrderVip orderVip);

    /**
     * 修改VIP订单
     *
     * @param orderVip VIP订单
     * @return 结果
     */
    public int updateOrderVip(IOrderVip orderVip);

    /**
     * 批量删除VIP订单
     *
     * @param ids 需要删除的VIP订单主键集合
     * @return 结果
     */
    public int deleteOrderVipByIds(Long[] ids);

    /**
     * 删除VIP订单信息
     *
     * @param id VIP订单主键
     * @return 结果
     */
    public int deleteOrderVipById(Long id);

    List<TsProfit> monthSum(LambdaQueryWrapper<IOrderVip> wrapper);

    List<TsProfit> daySum(LambdaQueryWrapper<IOrderVip> wrapper);

    List<TsProfit> dayWeekSum(LambdaQueryWrapper<IOrderVip> wrapper);

    List<TsProfit> hourSum(LambdaQueryWrapper<IOrderVip> wrapper);

    List<TsProfit> dayMonthSum(LambdaQueryWrapper<IOrderVip> wrapper);
}
