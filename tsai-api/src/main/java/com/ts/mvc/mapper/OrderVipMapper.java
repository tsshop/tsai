package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.OrderVip;
import org.apache.ibatis.annotations.Mapper;

/**
 * VIP订单表;(order_vip)表数据库访问层
 * @author : tsai
 * @date : 2023-5-8
 */
@Mapper
public interface OrderVipMapper  extends BaseMapper<OrderVip>{
}