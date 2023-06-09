package com.ts.mvc.service;

import com.ts.mvc.domain.SessionLog;
import com.ts.mvc.domain.SessionMsg;
import com.ts.mvc.domain.User;

/**
 * @author : tsai
 * @date : 2023/5/10
 */
public interface AIService {
    void create(SessionLog sessionLog, User user);

    SessionMsg sendMsg(SessionLog sessionLog, User user);
}
