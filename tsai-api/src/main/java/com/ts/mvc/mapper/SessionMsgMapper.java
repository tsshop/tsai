package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.SessionMsg;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会话聊天记录;(session_msg)表数据库访问层
 * @author : tsai
 * @date : 2023-5-8
 */
@Mapper
public interface SessionMsgMapper  extends BaseMapper<SessionMsg>{
}