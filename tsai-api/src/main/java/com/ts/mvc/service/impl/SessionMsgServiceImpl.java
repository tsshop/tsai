package com.ts.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.SessionMsg;
import com.ts.mvc.mapper.SessionMsgMapper;
import com.ts.mvc.service.SessionMsgService;
import org.springframework.stereotype.Service;

/**
 * 会话聊天记录;(session_msg)表服务实现类
 * @author : tsai
 * @date : 2023-5-8
 */
@Service
public class SessionMsgServiceImpl extends ServiceImpl<SessionMsgMapper, SessionMsg> implements SessionMsgService {

}