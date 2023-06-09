package com.ts.mvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.mvc.domain.Feedback;
import org.apache.ibatis.annotations.Mapper;

/**
 * 反馈表;(feedback)表数据库访问层
 * @author :  tsai
 * @date : 2023-5-11
 */
@Mapper
public interface FeedbackMapper  extends BaseMapper<Feedback>{
}
