package com.ts.mvc.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.ts.common.enums.SystemConstant;
import com.ts.config.exception.MyException;
import com.ts.mvc.domain.*;
import com.ts.mvc.service.*;
import com.ts.util.GptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author : tsai
 * @date : 2023/5/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AIServiceImpl implements AIService {

    @Autowired
    private GptUtil gptUtil;

    @Autowired
    private SessionLogService sessionLogService;

    @Autowired
    private SessionMsgService sessionMsgService;

    @Autowired
    private OrderNumberService orderNumberService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private TemplateService templateService;

    @Override
    public void create(SessionLog sessionLog, User user) {
        sessionLogService.update(new LambdaUpdateWrapper<SessionLog>()
                .set(SessionLog::getStatus, SystemConstant.SessionStatusEnum.OFF.getCode())
                .eq(SessionLog::getTemplateId, sessionLog.getTemplateId())
                .eq(SessionLog::getUserId, user.getId()));
        sessionLog.setTemplateId(0L);
        sessionLog.setUserId(user.getId());
        sessionLogService.save(sessionLog);
    }

    @Override
    public SessionMsg sendMsg(SessionLog sessionLog, User user) {
        Date now = new Date();
        SessionMsg sessionMsg = sessionLog.getSessionMsg();
        Long templateId = sessionLog.getTemplateId();

        if (ObjectUtil.isEmpty(user.getVipEndTime()) || user.getVipEndTime().getTime() < now.getTime()) {
            OrderNumber orderNumber = orderNumberService.getOne(new LambdaQueryWrapper<OrderNumber>()
                    .eq(OrderNumber::getUserId, user.getId())
                    .eq(OrderNumber::getOrderStatus,SystemConstant.OrderStatusEnum.SUCCESS.getCode())
                    .gt(OrderNumber::getValidEndTime, now)
                    .gt(OrderNumber::getResidueNumber, 0)
                    .last(" limit 1 "));
            if (ObjectUtil.isEmpty(orderNumber)) {
                throw new MyException("次数已用完");
            }
            //扣减次数
            orderNumberService.deductionNumber(orderNumber.getId());
        }

        sessionLog = sessionLogService.getOne(new LambdaQueryWrapper<SessionLog>()
                .eq(SessionLog::getStatus, SystemConstant.SessionStatusEnum.ON.getCode())
                .eq(SessionLog::getUserId, user.getId())
                .eq(SessionLog::getTemplateId, templateId)
                .orderByDesc(SessionLog::getId)
                .last(" limit 1"));
        if (ObjectUtil.isEmpty(sessionLog)) {
            sessionLog = new SessionLog();
            sessionLog.setUserId(user.getId());
            sessionLog.setTemplateId(templateId);
            sessionLog.setStatus(SystemConstant.SessionStatusEnum.ON.getCode());
            sessionLogService.save(sessionLog);
        }
        sessionMsg.setLogId(sessionLog.getId());
        sessionMsg.setIsFromUser(true);
        sessionMsg.setTemplateId(templateId);
        sessionMsgService.save(sessionMsg);
        String msg = "";
        if (templateId == 0) {
            Integer contextMaxNumber = Integer.valueOf(configService.getByKey("CONTEXT_MAX_NUMBER"));
            List<SessionMsg> sessionMsgList = sessionMsgService.list(new LambdaQueryWrapper<SessionMsg>()
                    .eq(SessionMsg::getLogId, sessionLog.getId())
                    .orderByDesc(SessionMsg::getSendTime)
                    .last( " limit " + contextMaxNumber ));
            List<ChatMessage> messageList = new ArrayList<>();
            Collections.reverse(sessionMsgList);
            sessionMsgList.forEach(item -> {
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setContent(item.getContent());
                if (item.getIsFromUser()) {
                    chatMessage.setRole(ChatMessageRole.USER.value());
                } else {
                    chatMessage.setRole(ChatMessageRole.ASSISTANT.value());
                }
                messageList.add(chatMessage);
            });
            System.out.println(messageList);
            msg = gptUtil.send(messageList, configService.getByKey("MODEL"), Integer.valueOf(configService.getByKey("MAX_TOKENS")));
        } else {
            List<ChatMessage> messageList = new ArrayList<>();
            ChatMessage chatMessage = new ChatMessage();
            Template template = templateService.getById(templateId);
            chatMessage.setContent(template.getContent().replace("$$$$",sessionMsg.getContent()));
            chatMessage.setRole(ChatMessageRole.SYSTEM.value());
            messageList.add(chatMessage);
            System.out.println(messageList);
            msg = gptUtil.send(messageList, configService.getByKey("MODEL"), Integer.valueOf(configService.getByKey("MAX_TOKENS")));
        }
        sessionMsg = new SessionMsg();
        sessionMsg.setContent(msg);
        sessionMsg.setLogId(sessionLog.getId());
        sessionMsg.setIsFromUser(false);
        sessionMsg.setTemplateId(templateId);
        sessionMsgService.save(sessionMsg);
        return sessionMsg;
    }
}
