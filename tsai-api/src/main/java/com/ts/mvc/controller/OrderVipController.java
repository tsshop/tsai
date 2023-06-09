package com.ts.mvc.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.common.enums.SystemConstant;
import com.ts.common.utils.ip.IpUtils;
import com.ts.config.exception.ApiResult;
import com.ts.config.token.PassToken;
import com.ts.mvc.domain.OrderVip;
import com.ts.mvc.domain.User;
import com.ts.mvc.domain.VipConfig;
import com.ts.mvc.service.ConfigService;
import com.ts.mvc.service.OrderVipService;
import com.ts.mvc.service.UserService;
import com.ts.mvc.service.VipConfigService;
import com.ts.util.PayUtil;
import com.ts.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * VIP订单表;(order_vip)表控制层
 * @author : tsai
 * @date : 2023-5-8
 */
@Slf4j
@RestController
@RequestMapping("/orderVip")
public class OrderVipController{
    @Autowired
    private OrderVipService orderVipService;
    @Autowired
    private PayUtil payUtil;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private VipConfigService vipConfigService;
    @Value("${precreate.notify.vip}")
    private String notifyUrl;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigService configService;

    /**
     * vip订单列表
     * @param request
     * @return
     */
    @GetMapping("/list")
    public ApiResult list(HttpServletRequest request){
        User user = userUtil.getUser(request);
        return ApiResult.ok(orderVipService.list(new LambdaQueryWrapper<OrderVip>()
                .eq(OrderVip::getOrderStatus,SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                .eq(OrderVip::getUserId,user.getId())
                .orderByDesc(OrderVip::getCreateTime)));
    }

    /**
     * 购买vip
     * @param configId
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/buy")
    public ApiResult buy(Long configId,HttpServletRequest request) throws Exception{
        Long userId = userUtil.getUser(request).getId();
        User user = userService.getById(userId);
        VipConfig vipConfig = vipConfigService.getById(configId);
        if (ObjectUtil.isEmpty(vipConfig)){
            return ApiResult.fail("该套餐不存在，请刷新后重试");
        }
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        String orderNo="VIP"+snowflake.nextId()+"";
        OrderVip orderVip = new OrderVip()
                .setOrderNo(orderNo)
                .setOrderStatus(SystemConstant.OrderStatusEnum.WAIT.getCode())
                .setIp(IpUtils.getIpAddr(request))
                .setRemark("VIP购买")
                .setTime(vipConfig.getTime())
                .setTotalAmt(vipConfig.getAmt())
                .setUserId(user.getId());
        orderVipService.save(orderVip);
        String result  = payUtil.pay(orderVip.getTotalAmt(),orderVip.getOrderNo(),notifyUrl,user.getOpenId());
        return ApiResult.ok(result);
    }

    /**
     * vip订单回调
     * @param request
     * @return
     */
    @PostMapping("/notify")
    @PassToken
    public String notify(HttpServletRequest request){

        Map<String, String> params = payUtil.getRequestParams(request);
        log.info("params:" + params);
        String sign = params.get("sign");
        log.info(sign);

        if (ObjectUtil.isEmpty(params)){
            return "";
        }

        String strSign = payUtil.makeSign(params);

        if (!sign.equals(strSign)) {
            System.out.println("验签未通过");
            return "";
        }

        JSONObject pay = JSONUtil.parseObj(configService.getByKey("pay"));
        String orderPrefix = pay.getStr("orderPrefix");

        String payAmt=params.get("buyerPayAmount");
        String orderNo=params.get("merOrderId").replace(orderPrefix,"");
        String tradeNo=params.get("targetOrderId");

        OrderVip order=orderVipService.getOne(new LambdaQueryWrapper<OrderVip>().eq(OrderVip::getOrderNo,orderNo));
        if(order==null || order.getOrderStatus()!=1){
            return "SUCCESS";
        }

        order.setId(order.getId());
        order.setOrderStatus(SystemConstant.OrderStatusEnum.SUCCESS.getCode());
        order.setPayAmt(new BigDecimal(payAmt).divide(new BigDecimal(100)));
        order.setPayNo(tradeNo);
        orderVipService.success(order);

        return "SUCCESS";
    }
}
