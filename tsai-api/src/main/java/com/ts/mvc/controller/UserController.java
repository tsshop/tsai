package com.ts.mvc.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ts.common.enums.SystemConstant;
import com.ts.common.utils.QRCodeUtil;
import com.ts.config.exception.ApiResult;
import com.ts.config.token.PassToken;
import com.ts.mvc.domain.OrderNumber;
import com.ts.mvc.domain.User;
import com.ts.mvc.domain.dto.UserDto;
import com.ts.mvc.domain.po.UserLoginParam;
import com.ts.common.core.redis.RedisCache;
import com.ts.common.utils.test.EncryptUtils;
import com.ts.mvc.service.OrderNumberService;
import com.ts.util.PhoneUtil;
import com.ts.util.UserUtil;
import com.ts.mvc.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xu
 * @since 2022-11-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserUtil userUtil;
    @Autowired
    private OrderNumberService orderNumberService;
    @Autowired
    private RedisCache redisService;
    @Value("${jwt.token-validity-in-seconds}")
    private Long time;

    /**
     * 用户信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/info")
    public ApiResult<Object> info(HttpServletRequest httpServletRequest)  {
        Long userId= userUtil.getUser(httpServletRequest).getId();
        User user = userService.getById(userId);
        UserDto userDTO = new UserDto();
        BeanUtils.copyProperties(user, userDTO);
        Map<String, Object> map = orderNumberService.getMap(new QueryWrapper<OrderNumber>()
                .select(" ifNull(sum(residue_number),0) number ")
                .eq("user_id", user.getId())
                .eq("order_status", SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                .gt("valid_end_time", new Date()));
        BigDecimal number = (BigDecimal) map.get("number");
        userDTO.setNumber(number.intValue());
        return ApiResult.ok(userDTO);
    }

    /**
     * 修改用户信息
     * @param dto
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/updateInfo")
    public ApiResult<Object> updateInfo(@RequestBody UserLoginParam dto, HttpServletRequest httpServletRequest)  {
        User user= userUtil.getUser(httpServletRequest);
        User userU=new User();
        userU.setId(user.getId());
        userU.setName(dto.getName());
        userU.setAvatarUrl(dto.getAvatarUrl());
        userService.updateById(userU);
        redisService.saveCode("user:"+user.getId(), user ,time );
        return ApiResult.ok();
    }

    /**
     * 修改密码
     * @param dto
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping ("/updatePassword")
    public ApiResult<Object> updatePassword(@RequestBody UserLoginParam dto,HttpServletRequest httpServletRequest) throws Exception {
        User user= userUtil.getUser(httpServletRequest);
        String phone=dto.getPhone();
        String code=dto.getCode();
        Object rCode = redisService.getCacheObject("password:msgCode:" + phone);
        String password = dto.getPassword();
        if(password.length() > 20 || password.length() < 6){
            return ApiResult.fail("密码应在6到20位之间");
        }
        if(ObjectUtil.isEmpty(rCode) || !code.equals(rCode.toString())){
            return ApiResult.fail("验证码错误或已过期");
        }
        String md5Password = EncryptUtils.desEncrypt(password.toUpperCase());
        User uUpdate=new User();
        uUpdate.setId(user.getId());
        uUpdate.setPassword(md5Password);
        uUpdate.setVisiblePassword(password);
        userService.updateById(uUpdate);
        return ApiResult.ok();
    }

    /**
     * 获取短信验证码
     * @param phone
     * @return
     */
    @GetMapping(value = "/getCode")
    public ApiResult<Object> getCode(@RequestParam("phone") String phone) {
        if (!PhoneUtil.isPhoneLegal(phone)) {
            return ApiResult.fail("请输入正确的手机号");
        }
        return userService.sendMessage(phone,"password:");
    }

    /**
     * 获取二维码
     * @param param
     * @param request
     * @return
     */
    @PostMapping("/getQrCode")
    public ApiResult getQrCode(@RequestBody UserLoginParam param, HttpServletRequest request){
        String code = QRCodeUtil.creatRrCode(param.getContent(), 300, 300);
        return ApiResult.ok(code);
    }
}
