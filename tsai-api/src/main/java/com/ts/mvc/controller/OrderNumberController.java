package com.ts.mvc.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ts.common.enums.SystemConstant;
import com.ts.common.utils.ip.IpUtils;
import com.ts.config.exception.ApiResult;
import com.ts.config.token.PassToken;
import com.ts.mvc.domain.*;
import com.ts.mvc.service.ConfigService;
import com.ts.mvc.service.NumberConfigService;
import com.ts.mvc.service.OrderNumberService;
import com.ts.mvc.service.UserService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用次数订单表;(order_number)表控制层
 * @author : tsai
 * @date : 2023-5-9
 */
@Slf4j
@RestController
@RequestMapping("/orderNumber")
public class OrderNumberController{
    @Autowired
    private OrderNumberService orderNumberService;
    @Autowired
    private PayUtil payUtil;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private NumberConfigService numberConfigService;
    @Value("${precreate.notify.number}")
    private String notifyUrl;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfigService configService;

    /**
     * 获取次数订单
     * @param isExpire
     * @param request
     * @return
     */
    @GetMapping("/list")
    public ApiResult list(Boolean isExpire,HttpServletRequest request){
        User user = userUtil.getUser(request);
        LambdaQueryWrapper<OrderNumber> wrapper = new LambdaQueryWrapper<OrderNumber>()
                .eq(OrderNumber::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                .eq(OrderNumber::getUserId, user.getId())
                .last(" order by if (now() > valid_end_time , 1 , 0 ) , if (residue_number = 0 , 1 , 0 ) , valid_end_time");
        if (ObjectUtil.isNotEmpty(isExpire) && isExpire){
            Date begin = new Date();
            Date end = DateUtil.offsetDay(begin,3);
            wrapper.between(OrderNumber::getValidEndTime,begin,end);
        }
        return ApiResult.ok(orderNumberService.list(wrapper));
    }

    /**
     * 购买套餐
     * @param configId
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/buy")
    public ApiResult buy(Long configId, HttpServletRequest request) throws Exception{
        Long userId = userUtil.getUser(request).getId();
        User user = userService.getById(userId);
        NumberConfig numberConfig = numberConfigService.getById(configId);
        if (ObjectUtil.isEmpty(numberConfig)){
            return ApiResult.fail("该套餐不存在，请刷新后重试");
        }
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        String orderNo="NUMBER"+snowflake.nextId()+"";
        OrderNumber orderNumber = new OrderNumber()
                .setOrderNo(orderNo)
                .setOrderStatus(SystemConstant.OrderStatusEnum.WAIT.getCode())
                .setIp(IpUtils.getIpAddr(request))
                .setRemark("使用次数购买")
                .setValidEndTime(DateUtil.offsetDay(new Date(),numberConfig.getTime()))
                .setTotalAmt(numberConfig.getAmt())
                .setUserId(user.getId())
                .setAllNumber(numberConfig.getNumber())
                .setResidueNumber(numberConfig.getNumber())
                .setType(SystemConstant.NumberTypeEnum.ONE.getCode());
        orderNumberService.save(orderNumber);
        String result  = payUtil.pay(orderNumber.getTotalAmt(),orderNumber.getOrderNo(),notifyUrl,user.getOpenId());
        return ApiResult.ok(result);
    }

    /**
     * 订单回调
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

        OrderNumber order=orderNumberService.getOne(new LambdaQueryWrapper<OrderNumber>().eq(OrderNumber::getOrderNo,orderNo));
        if(order==null || order.getOrderStatus()!=1){
            return "SUCCESS";
        }

        order.setId(order.getId());
        order.setOrderStatus(SystemConstant.OrderStatusEnum.SUCCESS.getCode());
        order.setPayAmt(new BigDecimal(payAmt).divide(new BigDecimal(100)));
        order.setPayNo(tradeNo);
        orderNumberService.updateById(order);

        return "SUCCESS";
    }

    /**
     * 邀请订单列表
     * @param request
     * @return
     */
    @GetMapping("/inviteList")
    public ApiResult inviteList(HttpServletRequest request){
        User user = userUtil.getUser(request);
        Map<String,Object> result = new HashMap<>();

        int count = userService.count(new LambdaQueryWrapper<User>()
                .eq(User::getInviterId, user.getId()));
        result.put("totalNumberPeople",count);

        Map<String, Object> map = orderNumberService.getMap(new QueryWrapper<OrderNumber>()
                .select(" ifNull(sum(all_number),0) number ")
                .eq("user_id", user.getId())
                .eq("type",SystemConstant.NumberTypeEnum.THREE.getCode()));
        BigDecimal number = (BigDecimal)map.get("number");
        result.put("allNumber",number.intValue());

        LambdaQueryWrapper<OrderNumber> wrapper = new LambdaQueryWrapper<OrderNumber>()
                .eq(OrderNumber::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                .eq(OrderNumber::getType,SystemConstant.NumberTypeEnum.THREE.getCode())
                .eq(OrderNumber::getUserId, user.getId())
                .orderByDesc(OrderNumber::getCreateTime);
        List<OrderNumber> orderNumberList = orderNumberService.list(wrapper);
        orderNumberList.forEach(item -> {
            item.setSourceUserName(userService.getById(item.getSourceUserId()).getName());
        });
        result.put("list",orderNumberList);
        return ApiResult.ok(result);
    }

}
