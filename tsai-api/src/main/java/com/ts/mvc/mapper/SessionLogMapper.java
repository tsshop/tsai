package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.SessionLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会话记录;(session_log)表数据库访问层
 * @author : tsai
 * @date : 2023-5-8
 */
@Mapper
public interface SessionLogMapper  extends BaseMapper<SessionLog>{
}
