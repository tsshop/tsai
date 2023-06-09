package com.ts.mvc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.mvc.domain.Feedback;
import com.ts.mvc.mapper.FeedbackMapper;
import com.ts.mvc.service.FeedbackService;
import org.springframework.stereotype.Service;

/**
 * 反馈表;(feedback)表服务实现类
 * @author :  tsai
 * @date : 2023-5-11
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

}