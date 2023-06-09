package com.ts.mvc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ts.mvc.domain.OrderNumber;

/**
 * 使用次数订单表;(order_number)表服务接口
 * @author : tsai
 * @date : 2023-5-8
 */
public interface OrderNumberService extends IService<OrderNumber>{
    Integer deductionNumber(Long id);
}