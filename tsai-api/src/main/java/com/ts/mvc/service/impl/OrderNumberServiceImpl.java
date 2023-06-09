package com.ts.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.OrderNumber;
import com.ts.mvc.mapper.OrderNumberMapper;
import com.ts.mvc.service.OrderNumberService;
import org.springframework.stereotype.Service;

/**
 * 使用次数订单表;(order_number)表服务实现类
 * @author : tsai
 * @date : 2023-5-8
 */
@Service
public class OrderNumberServiceImpl extends ServiceImpl<OrderNumberMapper, OrderNumber> implements OrderNumberService {

    @Override
    public Integer deductionNumber(Long id) {
        return baseMapper.deductionNumber(id);
    }
}