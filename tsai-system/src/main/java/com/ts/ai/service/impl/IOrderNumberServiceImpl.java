package com.ts.ai.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.ai.domain.TsProfit;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.IOrderNumberMapper;
import com.ts.ai.domain.IOrderNumber;
import com.ts.ai.service.IOrderNumberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 使用次数订单Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class IOrderNumberServiceImpl extends ServiceImpl<IOrderNumberMapper, IOrderNumber> implements IOrderNumberService
{
    @Autowired
    private IOrderNumberMapper IOrderNumberMapper;

    /**
     * 查询使用次数订单
     *
     * @param id 使用次数订单主键
     * @return 使用次数订单
     */
    @Override
    public IOrderNumber selectOrderNumberById(Long id)
    {
        return IOrderNumberMapper.selectOrderNumberById(id);
    }

    /**
     * 查询使用次数订单列表
     *
     * @param orderNumber 使用次数订单
     * @return 使用次数订单
     */
    @Override
    public List<IOrderNumber> selectOrderNumberList(IOrderNumber orderNumber)
    {
        return IOrderNumberMapper.selectOrderNumberList(orderNumber);
    }

    /**
     * 新增使用次数订单
     *
     * @param orderNumber 使用次数订单
     * @return 结果
     */
    @Override
    public int insertOrderNumber(IOrderNumber orderNumber)
    {
        orderNumber.setCreateTime(DateUtils.getNowDate());
        return IOrderNumberMapper.insertOrderNumber(orderNumber);
    }

    /**
     * 修改使用次数订单
     *
     * @param orderNumber 使用次数订单
     * @return 结果
     */
    @Override
    public int updateOrderNumber(IOrderNumber orderNumber)
    {
        orderNumber.setUpdateTime(DateUtils.getNowDate());
        return IOrderNumberMapper.updateOrderNumber(orderNumber);
    }

    /**
     * 批量删除使用次数订单
     *
     * @param ids 需要删除的使用次数订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderNumberByIds(Long[] ids)
    {
        return IOrderNumberMapper.deleteOrderNumberByIds(ids);
    }

    /**
     * 删除使用次数订单信息
     *
     * @param id 使用次数订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderNumberById(Long id)
    {
        return IOrderNumberMapper.deleteOrderNumberById(id);
    }

    @Override
    public List<TsProfit> monthSum(LambdaQueryWrapper<IOrderNumber> wrapper) {
        return IOrderNumberMapper.monthSum(wrapper);
    }

    @Override
    public List<TsProfit> daySum(LambdaQueryWrapper<IOrderNumber> wrapper) {
        return IOrderNumberMapper.daySum(wrapper);
    }

    @Override
    public List<TsProfit> dayWeekSum(LambdaQueryWrapper<IOrderNumber> wrapper) {
        return IOrderNumberMapper.dayWeekSum(wrapper);
    }

    @Override
    public List<TsProfit> hourSum(LambdaQueryWrapper<IOrderNumber> wrapper) {
        return IOrderNumberMapper.hourSum(wrapper);
    }

    @Override
    public List<TsProfit> dayMonthSum(LambdaQueryWrapper<IOrderNumber> wrapper) {
        return IOrderNumberMapper.dayMonthSum(wrapper);
    }
}
