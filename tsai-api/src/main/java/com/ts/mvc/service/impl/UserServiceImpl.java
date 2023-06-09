package com.ts.mvc.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.beust.jcommander.internal.Maps;
import com.github.pagehelper.util.StringUtil;
import com.ts.common.enums.SystemConstant;
import com.ts.config.exception.ApiResult;
import com.ts.config.token.TokenUtil;
import com.ts.mvc.domain.NumberConfig;
import com.ts.mvc.domain.OrderNumber;
import com.ts.mvc.domain.User;
import com.ts.mvc.enums.LoginScenario;
import com.ts.mvc.mapper.UserMapper;
import com.ts.mvc.service.ConfigService;
import com.ts.mvc.service.NumberConfigService;
import com.ts.mvc.service.OrderNumberService;
import com.ts.mvc.service.UserService;
import com.ts.mvc.domain.dto.UserDto;
import com.ts.mvc.domain.po.UserLoginParam;
import com.ts.common.core.redis.RedisCache;
import com.ts.common.utils.test.EncryptUtils;
import com.ts.util.SmsUtil;
import com.ts.util.UserUtil;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RedisCache redisService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${jwt.token-validity-in-seconds}")
    private Long time;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private OrderNumberService orderNumberService;

    @Autowired
    private NumberConfigService numberConfigService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private SmsUtil smsUtil;

    @Override
    public ApiResult<Object> sendMessage(String phone, String folder) {
        JSONObject jsonBody = new JSONObject();

        Long code = Math.round((Math.random() + 1) * 100000);

        redisService.saveCode(folder + "timePhone:" + phone, 3, 10 * 60L);
        redisService.saveCode(folder + "msgCode:" + phone, code, 10 * 60L);

        try{
            Boolean send = smsUtil.send(phone, String.valueOf(code));
            if (send){
                return ApiResult.ok("发送成功");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        redisTemplate.delete(folder + "msgCode:" + phone);
        redisTemplate.delete(folder + "timePhone:" + phone);

        return ApiResult.fail("发送失败");
    }

    @Override
    public ApiResult<Object> loginByScenario(UserLoginParam loginParam) throws Exception {
        String scenario = loginParam.getScenario();
        if (LoginScenario.PASSWORD.getValue().equals(scenario)) {
            return loginPassword(loginParam);
        }
        if (LoginScenario.CODE.getValue().equals(scenario)) {
            return loginAppCode(loginParam);
        }
        return ApiResult.fail("场景错误");
    }


    private ApiResult<Object> loginPassword(UserLoginParam loginParam) throws Exception {
        String phone = loginParam.getPhone();
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if (user == null) {
            return ApiResult.fail("账户不存在!请使用验证码登录");
        }
        if (user.getDeleted()) {
            return ApiResult.fail("账户已封禁!请联系管理员");
        }
        String md5Password = EncryptUtils.desEncrypt(loginParam.getPassword().toUpperCase());
        if (md5Password.equals(user.getPassword())) {
            if (StrUtil.isNotBlank(loginParam.getWxCode())){
                String openId = getOpenId(loginParam.getWxCode());
                if (ObjectUtil.isNotEmpty(openId)){
                    update(new LambdaUpdateWrapper<User>()
                            .set(User::getOpenId,openId)
                            .eq(User::getId,user.getId()));
                    user.setOpenId(openId);
                }
            }
            JSONObject jsonObject = loginInfo(user);
            return ApiResult.ok(jsonObject);
        } else {
            return ApiResult.fail("密码错误");
        }
    }

    private ApiResult<Object> loginAppCode(UserLoginParam loginParam) {
        String phone = loginParam.getPhone();
        String code = loginParam.getCode();

        Object rCode= redisTemplate.opsForValue().get("login:msgCode:" + phone);
        System.out.printf(rCode+" "+code);

        if(ObjectUtil.isEmpty(rCode) || !code.equals(rCode.toString())){
            return ApiResult.fail("验证码错误或已过期");
        }

        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if (user == null) {
            user = new User();
            user.setName(phone.substring(0, 3) + "****" + phone.substring(7, 11));
            user.setAvatarUrl(loginParam.getAvatarUrl());
            user.setPhone(phone);
            user.setInviterId(loginParam.getInviterId());
            user.setDeleted(false);
            baseMapper.insert(user);
            NumberConfig numberConfig = numberConfigService.getOne(new LambdaQueryWrapper<NumberConfig>()
                    .eq(NumberConfig::getType, SystemConstant.NumberTypeEnum.TWO.getCode())
                    .eq(NumberConfig::getIsUse, true)
                    .last(" limit 1 "));
            if (ObjectUtil.isNotEmpty(numberConfig)){
                OrderNumber orderNumber = new OrderNumber()
                        .setAllNumber(numberConfig.getNumber())
                        .setOrderNo(RandomUtil.randomNumbers(20))
                        .setResidueNumber(numberConfig.getNumber())
                        .setOrderStatus(SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                        .setUserId(user.getId())
                        .setValidEndTime(UserUtil.getPermanentTime())
                        .setRemark("新人赠送")
                        .setType(SystemConstant.NumberTypeEnum.TWO.getCode());
                orderNumberService.save(orderNumber);
            }
            if (ObjectUtil.isNotEmpty(loginParam.getInviterId())){
                Date now = new Date();
                Date begin = DateUtil.beginOfDay(now);
                Date end = DateUtil.endOfDay(now);
                Integer inviteMaxNumber = Integer.valueOf(configService.getByKey("INVITE_MAX_NUMBER"));
                int count = orderNumberService.count(new LambdaQueryWrapper<OrderNumber>()
                        .eq(OrderNumber::getUserId, loginParam.getInviterId())
                        .eq(OrderNumber::getType, SystemConstant.NumberTypeEnum.THREE.getCode())
                        .between(OrderNumber::getCreateTime, begin, end));
                if (count < inviteMaxNumber){
                    numberConfig = numberConfigService.getOne(new LambdaQueryWrapper<NumberConfig>()
                            .eq(NumberConfig::getType, SystemConstant.NumberTypeEnum.THREE.getCode())
                            .eq(NumberConfig::getIsUse, true)
                            .last(" limit 1 "));
                    if (ObjectUtil.isNotEmpty(numberConfig)){
                        OrderNumber orderNumber = new OrderNumber()
                                .setAllNumber(numberConfig.getNumber())
                                .setOrderNo(RandomUtil.randomNumbers(20))
                                .setResidueNumber(numberConfig.getNumber())
                                .setOrderStatus(SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                                .setUserId(loginParam.getInviterId())
                                .setValidEndTime(UserUtil.getPermanentTime())
                                .setSourceUserId(user.getId())
                                .setRemark("邀请好友赠送")
                                .setType(SystemConstant.NumberTypeEnum.THREE.getCode());
                        orderNumberService.save(orderNumber);
                    }
                }
            }
        }
        if (user.getDeleted()) {
            return ApiResult.fail("账户已封禁!请联系管理员");
        }
        if (StrUtil.isNotBlank(loginParam.getWxCode())){
            String openId = getOpenId(loginParam.getWxCode());
            if (ObjectUtil.isNotEmpty(openId)){
                update(new LambdaUpdateWrapper<User>()
                        .set(User::getOpenId,openId)
                        .eq(User::getId,user.getId()));
                user.setOpenId(openId);
            }
        }
        JSONObject jsonObject = loginInfo(user);
        return ApiResult.ok(jsonObject);
    }

    // 发送短信
    public static JSONObject doPost(String url, JSONObject json) {
        JSONObject response;
        try {
            String result = HttpUtil.post(url, json.toString());// 返回json格式
            response = JSONObject.parseObject(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private JSONObject loginInfo(User user) {
        String token = "";
        if (redisService.hasKey("user:" + user.getId())) {
            token = ((User) redisService.getCacheObject("user:" + user.getId())).getToken();
        } else {
            token = TokenUtil.sign(user.getId(), time * 1000);
            user.setToken(token);
            redisService.saveCode("user:" + user.getId(), user, time);
        }

        // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
        /** 修改 ：返回用户数据字段*/
        UserDto userDTO = mapperFacade.map(user, UserDto.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Authorization", token);
        jsonObject.put("user", userDTO);
        return jsonObject;
    }

    private String getOpenId(String code){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        cn.hutool.json.JSONObject req=new cn.hutool.json.JSONObject();
        req.put("appid", configService.getByKey("wxAppId"));
        req.put("secret", configService.getByKey("wxAppSecret"));
        req.put("code", code);
        req.put("grant_type", "authorization_code");
        cn.hutool.json.JSONObject json = new cn.hutool.json.JSONObject(HttpUtil.post(url, req));
        System.out.println("openid"+json);
        return json.getStr("openid");
    }
}
