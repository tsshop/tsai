package com.ts.ai.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.ai.domain.IOrderNumber;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ts.ai.domain.TsProfit;


/**
 * 使用次数订单Service接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IOrderNumberService extends IService<IOrderNumber>
{
    /**
     * 查询使用次数订单
     *
     * @param id 使用次数订单主键
     * @return 使用次数订单
     */
    public IOrderNumber selectOrderNumberById(Long id);

    /**
     * 查询使用次数订单列表
     *
     * @param orderNumber 使用次数订单
     * @return 使用次数订单集合
     */
    public List<IOrderNumber> selectOrderNumberList(IOrderNumber orderNumber);

    /**
     * 新增使用次数订单
     *
     * @param orderNumber 使用次数订单
     * @return 结果
     */
    public int insertOrderNumber(IOrderNumber orderNumber);

    /**
     * 修改使用次数订单
     *
     * @param orderNumber 使用次数订单
     * @return 结果
     */
    public int updateOrderNumber(IOrderNumber orderNumber);

    /**
     * 批量删除使用次数订单
     *
     * @param ids 需要删除的使用次数订单主键集合
     * @return 结果
     */
    public int deleteOrderNumberByIds(Long[] ids);

    /**
     * 删除使用次数订单信息
     *
     * @param id 使用次数订单主键
     * @return 结果
     */
    public int deleteOrderNumberById(Long id);

    List<TsProfit> monthSum(LambdaQueryWrapper<IOrderNumber> wrapper);

    List<TsProfit> daySum(LambdaQueryWrapper<IOrderNumber> wrapper);

    List<TsProfit> dayWeekSum(LambdaQueryWrapper<IOrderNumber> wrapper);

    List<TsProfit> hourSum(LambdaQueryWrapper<IOrderNumber> wrapper);

    List<TsProfit> dayMonthSum(LambdaQueryWrapper<IOrderNumber> wrapper);}
