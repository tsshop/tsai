package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.IFeedbackMapper;
import com.ts.ai.domain.IFeedback;
import com.ts.ai.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 反馈Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class IFeedbackServiceImpl extends ServiceImpl<IFeedbackMapper, IFeedback> implements IFeedbackService
{
    @Autowired
    private IFeedbackMapper feedbackMapper;

    /**
     * 查询反馈
     *
     * @param id 反馈主键
     * @return 反馈
     */
    @Override
    public IFeedback selectFeedbackById(Long id)
    {
        return feedbackMapper.selectFeedbackById(id);
    }

    /**
     * 查询反馈列表
     *
     * @param feedback 反馈
     * @return 反馈
     */
    @Override
    public List<IFeedback> selectFeedbackList(IFeedback feedback)
    {
        return feedbackMapper.selectFeedbackList(feedback);
    }

    /**
     * 新增反馈
     *
     * @param feedback 反馈
     * @return 结果
     */
    @Override
    public int insertFeedback(IFeedback feedback)
    {
        feedback.setCreateTime(DateUtils.getNowDate());
        return feedbackMapper.insertFeedback(feedback);
    }

    /**
     * 修改反馈
     *
     * @param feedback 反馈
     * @return 结果
     */
    @Override
    public int updateFeedback(IFeedback feedback)
    {
        feedback.setUpdateTime(DateUtils.getNowDate());
        return feedbackMapper.updateFeedback(feedback);
    }

    /**
     * 批量删除反馈
     *
     * @param ids 需要删除的反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackByIds(Long[] ids)
    {
        return feedbackMapper.deleteFeedbackByIds(ids);
    }

    /**
     * 删除反馈信息
     *
     * @param id 反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackById(Long id)
    {
        return feedbackMapper.deleteFeedbackById(id);
    }
}
