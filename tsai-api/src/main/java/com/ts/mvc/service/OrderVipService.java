package com.ts.mvc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ts.mvc.domain.OrderVip;

/**
 * VIP订单表;(order_vip)表服务接口
 * @author : tsai
 * @date : 2023-5-8
 */
public interface OrderVipService extends IService<OrderVip>{
    void success(OrderVip order);
}
