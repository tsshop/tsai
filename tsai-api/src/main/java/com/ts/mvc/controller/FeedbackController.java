package com.ts.mvc.controller;

import com.ts.config.exception.ApiResult;
import com.ts.mvc.domain.Feedback;
import com.ts.mvc.domain.User;
import com.ts.mvc.service.FeedbackService;
import com.ts.mvc.service.UserService;
import com.ts.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 反馈表;(feedback)表控制层
 * @author :  tsai
 * @date : 2023-5-11
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController{
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private UserService userService;

    /**
     * 创建反馈
     * @param feedback
     * @param request
     * @return
     */
    @PostMapping("/create")
    public ApiResult create(@RequestBody  Feedback feedback, HttpServletRequest request){
        User user = userService.getById(userUtil.getUser(request).getId());
        feedback.setUserId(user.getId());
        feedback.setUserPhone(user.getPhone());
        feedbackService.save(feedback);
        return ApiResult.ok();
    }
}
