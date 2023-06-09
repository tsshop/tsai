package com.ts.mvc.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.OrderVip;
import com.ts.mvc.domain.User;
import com.ts.mvc.mapper.OrderVipMapper;
import com.ts.mvc.mapper.UserMapper;
import com.ts.mvc.service.OrderVipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * VIP订单表;(order_vip)表服务实现类
 * @author : tsai
 * @date : 2023-5-8
 */
@Service
public class OrderVipServiceImpl extends ServiceImpl<OrderVipMapper, OrderVip> implements OrderVipService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void success(OrderVip order) {
        updateById(order);
        User user = userMapper.selectById(order.getUserId());
        Date now = new Date();
        Date endDate = null;
        if (ObjectUtil.isEmpty(user.getVipEndTime()) || user.getVipEndTime().getTime() < now.getTime()){
            endDate = DateUtil.offsetDay(now, order.getTime());
        }else {
            endDate = DateUtil.offsetDay(user.getVipEndTime(), order.getTime());
        }
        user.setVipEndTime(endDate);
        userMapper.updateById(user);
    }
}