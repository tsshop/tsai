package com.ts.ai.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ts.ai.domain.IOrderNumber;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.ai.domain.TsProfit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 使用次数订单Mapper接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IOrderNumberMapper extends BaseMapper<IOrderNumber>
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
     * 删除使用次数订单
     *
     * @param id 使用次数订单主键
     * @return 结果
     */
    public int deleteOrderNumberById(Long id);

    /**
     * 批量删除使用次数订单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderNumberByIds(Long[] ids);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%m' ) AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_number " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> monthSum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderNumber> wrapper);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%Y%m%d' ) AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_number " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> daySum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderNumber> wrapper);

    @Select("SELECT " +
            " WEEKDAY(create_time)+1 AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_number " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> dayWeekSum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderNumber> wrapper);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%H' ) AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_number " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> hourSum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderNumber> wrapper);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%d' ) AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_number " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> dayMonthSum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderNumber> wrapper);
}
