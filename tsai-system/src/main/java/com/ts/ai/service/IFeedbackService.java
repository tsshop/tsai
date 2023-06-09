package com.ts.ai.service;

import java.util.List;
import com.ts.ai.domain.IFeedback;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 反馈Service接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IFeedbackService extends IService<IFeedback>
{
    /**
     * 查询反馈
     *
     * @param id 反馈主键
     * @return 反馈
     */
    public IFeedback selectFeedbackById(Long id);

    /**
     * 查询反馈列表
     *
     * @param feedback 反馈
     * @return 反馈集合
     */
    public List<IFeedback> selectFeedbackList(IFeedback feedback);

    /**
     * 新增反馈
     *
     * @param feedback 反馈
     * @return 结果
     */
    public int insertFeedback(IFeedback feedback);

    /**
     * 修改反馈
     *
     * @param feedback 反馈
     * @return 结果
     */
    public int updateFeedback(IFeedback feedback);

    /**
     * 批量删除反馈
     *
     * @param ids 需要删除的反馈主键集合
     * @return 结果
     */
    public int deleteFeedbackByIds(Long[] ids);

    /**
     * 删除反馈信息
     *
     * @param id 反馈主键
     * @return 结果
     */
    public int deleteFeedbackById(Long id);
}
