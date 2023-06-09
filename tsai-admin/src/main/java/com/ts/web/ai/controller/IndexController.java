package com.ts.web.ai.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ts.ai.domain.*;
import com.ts.ai.service.IOrderNumberService;
import com.ts.ai.service.IOrderVipService;
import com.ts.ai.service.IUserService;
import com.ts.common.core.domain.AjaxResult;
import com.ts.common.enums.SystemConstant;
import com.ts.web.ai.controller.DTO.DataStatisticsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : tsai
 * @date : 2023/5/16
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderNumberService orderNumberService;

    @Autowired
    private IOrderVipService orderVipService;

    @GetMapping("/num")
    public AjaxResult num()
    {
        Map<String,Object> result = new HashMap<>();
        //用户数
        result.put("userNum",userService.count());
        //vip用户
        result.put("vipUserNum",userService.count(new LambdaQueryWrapper<IUser>()
                .gt(IUser::getVipEndTime,new DateTime())));
        //购买次数用户
        result.put("numberUserNum",userService.count(new LambdaQueryWrapper<IUser>()
                .exists("SELECT 1 from order_number where order_number.type = 1 and order_number.order_status = 2 and order_number.user_id = user.id")));
        //被邀请用户
        result.put("inviterUserNum",userService.count(new LambdaQueryWrapper<IUser>()
                .isNotNull(IUser::getInviterId)));
        return AjaxResult.success(result);
    }

    @GetMapping("/userProportion")
    public AjaxResult userProportion()
    {
        Map<String,Object> result = new HashMap<>();
        //总用户数
        result.put("allUserNum",userService.count());
        //普通用户数
        result.put("ordinaryUserNum",userService.count(new LambdaQueryWrapper<IUser>()
                .nested(i -> {
                    i.lt(IUser::getVipEndTime,new DateTime()).or().isNull(IUser::getVipEndTime);
                })
                .notExists("SELECT 1 from order_number where order_number.type = 1 and order_number.order_status = 2 and order_number.user_id = user.id")));
        //vip用户
        result.put("vipUserNum",userService.count(new LambdaQueryWrapper<IUser>()
                .gt(IUser::getVipEndTime,new DateTime())));
        //购买次数用户
        result.put("numberUserNum",userService.count(new LambdaQueryWrapper<IUser>()
                .nested(i -> {
                    i.lt(IUser::getVipEndTime,new DateTime()).or().isNull(IUser::getVipEndTime);
                })
                .exists("SELECT 1 from order_number where order_number.type = 1 and order_number.order_status = 2 and order_number.user_id = user.id")));
        return AjaxResult.success(result);
    }

    @GetMapping("/profit")
    public AjaxResult profit()
    {
        Map<String,Object> result = new HashMap<>();
        Map<String, Object> numberMap = orderNumberService.getMap(new QueryWrapper<IOrderNumber>()
                .select(" ifNull(sum(total_amt),0) profit ")
                .eq("type", SystemConstant.NumberTypeEnum.ONE.getCode())
                .eq("order_status", SystemConstant.OrderStatusEnum.SUCCESS.getCode()));
        BigDecimal numberProfit = (BigDecimal)numberMap.get("profit");
        Map<String, Object> vipMap = orderVipService.getMap(new QueryWrapper<IOrderVip>()
                .select(" ifNull(sum(total_amt),0) profit ")
                .eq("order_status"  , SystemConstant.OrderStatusEnum.SUCCESS.getCode()));
        BigDecimal vipProfit = (BigDecimal)vipMap.get("profit");
        BigDecimal allProfit = numberProfit.add(vipProfit);
        result.put("numberProfit",numberProfit);
        result.put("vipProfit",vipProfit);
        result.put("allProfit",allProfit);
        return AjaxResult.success(result);
    }

    @PostMapping("/userDataStatistics")
    public AjaxResult  dataStatistics(@RequestBody DataStatisticsDto dataStatisticsDto){
        Map<String,Object> result = new HashMap<>();
        DateTime now = new DateTime();
        Integer length = 0;
        Map<Integer,Long> userMap= null;
        List<Long> userList = new ArrayList<>();
        List<Integer> unitNumList = new ArrayList<>();
        switch (dataStatisticsDto.getType()){
            case 1:
                length = 12;
                userMap = Optional.ofNullable(userService.monthCount(new LambdaQueryWrapper<IUser>()
                                .between(IUser::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsStatistics>())
                        .stream().collect(Collectors.toMap(TsStatistics::getUnitNum,TsStatistics::getCount));
                for (int i = 1; i <= length; i++){
                    unitNumList.add(i);
                    userList.add(ObjectUtil.isEmpty(userMap.get(i))?0L : userMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("userNumber",userList);
                break;
            case 2:
                dataStatisticsDto.setStartTime(DateUtil.beginOfMonth(now));
                dataStatisticsDto.setEndTime(DateUtil.endOfMonth(now));
                length = DateUtil.lengthOfMonth(now.month()+1,DateUtil.isLeapYear(now.year()));
                userMap = Optional.ofNullable(userService.dayMonthCount(new LambdaQueryWrapper<IUser>()
                                .between(IUser::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsStatistics>())
                        .stream().collect(Collectors.toMap(TsStatistics::getUnitNum,TsStatistics::getCount));
                for (int i = 1; i <= length; i++){
                    unitNumList.add(i);
                    userList.add(ObjectUtil.isEmpty(userMap.get(i))?0L : userMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("userNumber",userList);
                break;
            case 3:
                length = 7;
                dataStatisticsDto.setStartTime(DateUtil.beginOfWeek(now));
                dataStatisticsDto.setEndTime(DateUtil.endOfWeek(now));
                userMap = Optional.ofNullable(userService.dayWeekCount(new LambdaQueryWrapper<IUser>()
                                .between(IUser::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsStatistics>())
                        .stream().collect(Collectors.toMap(TsStatistics::getUnitNum,TsStatistics::getCount));
                for (int i = 1; i <= length; i++){
                    unitNumList.add(i);
                    userList.add(ObjectUtil.isEmpty(userMap.get(i))?0L : userMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("userNumber",userList);
                break;
            case 4:
                length = 24;
                dataStatisticsDto.setStartTime(DateUtil.beginOfDay(now));
                dataStatisticsDto.setEndTime(DateUtil.endOfDay(now));
                userMap = Optional.ofNullable(userService.hourCount(new LambdaQueryWrapper<IUser>()
                                .between(IUser::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsStatistics>())
                        .stream().collect(Collectors.toMap(TsStatistics::getUnitNum,TsStatistics::getCount));
                for (int i = 0; i < length; i++){
                    unitNumList.add(i);
                    userList.add(ObjectUtil.isEmpty(userMap.get(i))?0L : userMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("userNumber",userList);
                break;
            case 5:
                userMap = Optional.ofNullable(userService.dayCount(new LambdaQueryWrapper<IUser>()
                                .between(IUser::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsStatistics>())
                        .stream().collect(Collectors.toMap(TsStatistics::getUnitNum,TsStatistics::getCount));
                List<DateTime> list = DateUtil.rangeToList(dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime(), DateField.DAY_OF_YEAR);
                for (DateTime item:list) {
                    Integer i  = Integer.valueOf(item.toString("yyyyMMdd"));
                    unitNumList.add(i);
                    userList.add(ObjectUtil.isEmpty(userMap.get(i))?0L : userMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("userNumber",userList);
                break;
        }
        return AjaxResult.success(result);
    }

    @PostMapping("/profitDataStatistics")
    public AjaxResult  profitDataStatistics(@RequestBody DataStatisticsDto dataStatisticsDto){
        Map<String,Object> result = new HashMap<>();
        DateTime now = new DateTime();
        Integer length = 0;
        Map<Integer, BigDecimal> vipProfitMap = null;
        Map<Integer, BigDecimal> numberProfitMap = null;
        List<BigDecimal> vipProfitList = new ArrayList<>();
        List<BigDecimal> numberProfitList = new ArrayList<>();
        List<Integer> unitNumList = new ArrayList<>();
        switch (dataStatisticsDto.getType()){
            case 1:
                length = 12;
                vipProfitMap = Optional.ofNullable(orderVipService.monthSum(new LambdaQueryWrapper<IOrderVip>()
                                .eq(IOrderVip::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderVip::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                numberProfitMap = Optional.ofNullable(orderNumberService.monthSum(new LambdaQueryWrapper<IOrderNumber>()
                                .eq(IOrderNumber::getType,SystemConstant.NumberTypeEnum.ONE)
                                .eq(IOrderNumber::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderNumber::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                for (int i = 1; i <= length; i++){
                    unitNumList.add(i);
                    vipProfitList.add(ObjectUtil.isEmpty(vipProfitMap.get(i))?new BigDecimal(0) : vipProfitMap.get(i));
                    numberProfitList.add(ObjectUtil.isEmpty(numberProfitMap.get(i))?new BigDecimal(0): numberProfitMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("vipProfit",vipProfitList);
                result.put("numberProfit",numberProfitList);
                break;
            case 2:
                dataStatisticsDto.setStartTime(DateUtil.beginOfMonth(now));
                dataStatisticsDto.setEndTime(DateUtil.endOfMonth(now));
                length = DateUtil.lengthOfMonth(now.month()+1,DateUtil.isLeapYear(now.year()));
                vipProfitMap = Optional.ofNullable(orderVipService.dayMonthSum(new LambdaQueryWrapper<IOrderVip>()
                                .eq(IOrderVip::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderVip::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                numberProfitMap = Optional.ofNullable(orderNumberService.dayMonthSum(new LambdaQueryWrapper<IOrderNumber>()
                                .eq(IOrderNumber::getType,SystemConstant.NumberTypeEnum.ONE)
                                .eq(IOrderNumber::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderNumber::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                for (int i = 1; i <= length; i++){
                    unitNumList.add(i);
                    vipProfitList.add(ObjectUtil.isEmpty(vipProfitMap.get(i))?new BigDecimal(0) : vipProfitMap.get(i));
                    numberProfitList.add(ObjectUtil.isEmpty(numberProfitMap.get(i))?new BigDecimal(0): numberProfitMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("vipProfit",vipProfitList);
                result.put("numberProfit",numberProfitList);
                break;
            case 3:
                length = 7;
                dataStatisticsDto.setStartTime(DateUtil.beginOfWeek(now));
                dataStatisticsDto.setEndTime(DateUtil.endOfWeek(now));
                vipProfitMap = Optional.ofNullable(orderVipService.dayWeekSum(new LambdaQueryWrapper<IOrderVip>()
                                .eq(IOrderVip::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderVip::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                numberProfitMap = Optional.ofNullable(orderNumberService.dayWeekSum(new LambdaQueryWrapper<IOrderNumber>()
                                .eq(IOrderNumber::getType,SystemConstant.NumberTypeEnum.ONE)
                                .eq(IOrderNumber::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderNumber::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                for (int i = 1; i <= length; i++){
                    unitNumList.add(i);
                    vipProfitList.add(ObjectUtil.isEmpty(vipProfitMap.get(i))?new BigDecimal(0) : vipProfitMap.get(i));
                    numberProfitList.add(ObjectUtil.isEmpty(numberProfitMap.get(i))?new BigDecimal(0): numberProfitMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("vipProfit",vipProfitList);
                result.put("numberProfit",numberProfitList);
                break;
            case 4:
                length = 24;
                dataStatisticsDto.setStartTime(DateUtil.beginOfDay(now));
                dataStatisticsDto.setEndTime(DateUtil.endOfDay(now));
                vipProfitMap = Optional.ofNullable(orderVipService.hourSum(new LambdaQueryWrapper<IOrderVip>()
                                .eq(IOrderVip::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderVip::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                numberProfitMap = Optional.ofNullable(orderNumberService.hourSum(new LambdaQueryWrapper<IOrderNumber>()
                                .eq(IOrderNumber::getType,SystemConstant.NumberTypeEnum.ONE)
                                .eq(IOrderNumber::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderNumber::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                for (int i = 0; i < length; i++){
                    unitNumList.add(i);
                    vipProfitList.add(ObjectUtil.isEmpty(vipProfitMap.get(i))?new BigDecimal(0) : vipProfitMap.get(i));
                    numberProfitList.add(ObjectUtil.isEmpty(numberProfitMap.get(i))?new BigDecimal(0): numberProfitMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("vipProfit",vipProfitList);
                result.put("numberProfit",numberProfitList);
                break;
            case 5:
                vipProfitMap = Optional.ofNullable(orderVipService.daySum(new LambdaQueryWrapper<IOrderVip>()
                                .eq(IOrderVip::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderVip::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                numberProfitMap = Optional.ofNullable(orderNumberService.daySum(new LambdaQueryWrapper<IOrderNumber>()
                                .eq(IOrderNumber::getType,SystemConstant.NumberTypeEnum.ONE)
                                .eq(IOrderNumber::getOrderStatus, SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .between(IOrderNumber::getCreateTime,dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime()))).orElseGet(() -> new ArrayList<TsProfit>())
                        .stream().collect(Collectors.toMap(TsProfit::getUnitNum,TsProfit::getSum));
                List<DateTime> list = DateUtil.rangeToList(dataStatisticsDto.getStartTime(),dataStatisticsDto.getEndTime(), DateField.DAY_OF_YEAR);
                for (DateTime item:list) {
                    Integer i  = Integer.valueOf(item.toString("yyyyMMdd"));
                    unitNumList.add(i);
                    vipProfitList.add(ObjectUtil.isEmpty(vipProfitMap.get(i))?new BigDecimal(0) : vipProfitMap.get(i));
                    numberProfitList.add(ObjectUtil.isEmpty(numberProfitMap.get(i))?new BigDecimal(0): numberProfitMap.get(i));
                }
                result.put("unitNum",unitNumList);
                result.put("vipProfit",vipProfitList);
                result.put("numberProfit",numberProfitList);
                break;
        }
        return AjaxResult.success(result);
    }
}
