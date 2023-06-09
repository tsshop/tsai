package com.ts.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.SessionLog;
import com.ts.mvc.mapper.SessionLogMapper;
import com.ts.mvc.service.SessionLogService;
import org.springframework.stereotype.Service;

/**
 * 会话记录;(session_log)表服务实现类
 * @author : tsai
 * @date : 2023-5-8
 */
@Service
public class SessionLogServiceImpl extends ServiceImpl<SessionLogMapper, SessionLog> implements SessionLogService {

}