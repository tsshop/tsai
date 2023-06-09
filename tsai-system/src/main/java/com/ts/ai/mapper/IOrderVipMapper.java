package com.ts.ai.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ts.ai.domain.IOrderVip;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.ai.domain.TsProfit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * VIP订单Mapper接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IOrderVipMapper extends BaseMapper<IOrderVip>
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
     * 删除VIP订单
     *
     * @param id VIP订单主键
     * @return 结果
     */
    public int deleteOrderVipById(Long id);

    /**
     * 批量删除VIP订单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderVipByIds(Long[] ids);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%m' ) AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_vip " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> monthSum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderVip> wrapper);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%Y%m%d' ) AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_vip " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> daySum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderVip> wrapper);

    @Select("SELECT " +
            " WEEKDAY(create_time)+1 AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_vip " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> dayWeekSum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderVip> wrapper);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%H' ) AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_vip " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> hourSum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderVip> wrapper);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%d' ) AS unitNum, " +
            " sum( total_amt ) AS sum  " +
            "FROM " +
            " order_vip " +
            "${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsProfit> dayMonthSum(@Param(Constants.WRAPPER) LambdaQueryWrapper<IOrderVip> wrapper);
}
