package com.ts.mvc.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ts.common.enums.SystemConstant;
import com.ts.config.exception.ApiResult;
import com.ts.mvc.domain.SessionLog;
import com.ts.mvc.domain.SessionMsg;
import com.ts.mvc.domain.User;
import com.ts.mvc.service.AIService;
import com.ts.mvc.service.SessionLogService;
import com.ts.mvc.service.SessionMsgService;
import com.ts.mvc.service.UserService;
import com.ts.util.GptUtil;
import com.ts.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : tsai
 * @date : 2023/5/10
 */
@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionLogService sessionLogService;

    @Autowired
    private SessionMsgService sessionMsgService;


    /**
     * 创建会话
     * @param sessionLog
     * @param request
     * @return
     */
    @PostMapping("/create")
    public ApiResult create(@Validated @RequestBody SessionLog sessionLog, HttpServletRequest request) {
        User user = userUtil.getUser(request);
        aiService.create(sessionLog,user);
        return ApiResult.ok(sessionLog);
    }

    /**
     * 发送消息
     * @param sessionLog
     * @param request
     * @return
     */
    @PostMapping("/sendMsg")
    public ApiResult sendMsg(@Validated @RequestBody SessionLog sessionLog, HttpServletRequest request) {
        User user = userUtil.getUser(request);
        user = userService.getById(user.getId());
        SessionMsg sessionMsg = aiService.sendMsg(sessionLog, user);
        return ApiResult.ok(sessionMsg);
    }

    /**
     * 根据模板获取消息记录
     * @param templateId
     * @param request
     * @return
     */
    @GetMapping("/getMsg")
    public ApiResult getMsg(Long templateId, HttpServletRequest request) {
        User user = userUtil.getUser(request);
        SessionLog sessionLog = sessionLogService.getOne(new LambdaQueryWrapper<SessionLog>()
                .eq(SessionLog::getUserId, user.getId())
                .eq(SessionLog::getStatus, SystemConstant.SessionStatusEnum.ON.getCode())
                .eq(SessionLog::getTemplateId, templateId)
                .orderByDesc(SessionLog::getId)
                .last(" limit 1 "));
        List<SessionMsg> sessionMsgList = null;
        if (ObjectUtil.isNotEmpty(sessionLog)){
            sessionMsgList = sessionMsgService.list(new LambdaQueryWrapper<SessionMsg>()
                    .eq(SessionMsg::getLogId, sessionLog.getId())
                    .orderByAsc(SessionMsg::getSendTime));
        }
        return ApiResult.ok(sessionMsgList);
    }

    /**
     * 点赞
     * @param id
     * @param score
     * @return
     */
    @GetMapping("/score")
    public ApiResult score(Long id,Integer score) {
        SessionMsg msg = sessionMsgService.getById(id);
        msg.setScore(score);
        sessionMsgService.updateById(msg);
        return ApiResult.ok();
    }
}
