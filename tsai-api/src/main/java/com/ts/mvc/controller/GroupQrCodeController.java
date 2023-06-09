package com.ts.mvc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.config.exception.ApiResult;
import com.ts.config.token.PassToken;
import com.ts.mvc.domain.GroupQrCode;
import com.ts.mvc.service.GroupQrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 群二维码;(group_qr_code)表控制层
 * @author :  tsai
 * @date : 2023-5-12
 */
@RestController
@RequestMapping("/groupQrCode")
public class GroupQrCodeController{
    @Autowired
    private GroupQrCodeService groupQrCodeService;

    /**
     * 群二维码
     * @return
     */
    @GetMapping
    @PassToken
    public ApiResult view(){
        return ApiResult.ok(groupQrCodeService.getOne(new LambdaQueryWrapper<GroupQrCode>()
                .eq(GroupQrCode::getIsUse,true)
                .last(" limit 1 ")));
    }
}