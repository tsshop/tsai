package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.ISessionMsgMapper;
import com.ts.ai.domain.ISessionMsg;
import com.ts.ai.service.ISessionMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 会话聊天记录Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class ISessionMsgServiceImpl extends ServiceImpl<ISessionMsgMapper, ISessionMsg> implements ISessionMsgService
{
    @Autowired
    private ISessionMsgMapper sessionMsgMapper;

    /**
     * 查询会话聊天记录
     *
     * @param id 会话聊天记录主键
     * @return 会话聊天记录
     */
    @Override
    public ISessionMsg selectSessionMsgById(Long id)
    {
        return sessionMsgMapper.selectSessionMsgById(id);
    }

    /**
     * 查询会话聊天记录列表
     *
     * @param sessionMsg 会话聊天记录
     * @return 会话聊天记录
     */
    @Override
    public List<ISessionMsg> selectSessionMsgList(ISessionMsg sessionMsg)
    {
        return sessionMsgMapper.selectSessionMsgList(sessionMsg);
    }

    /**
     * 新增会话聊天记录
     *
     * @param sessionMsg 会话聊天记录
     * @return 结果
     */
    @Override
    public int insertSessionMsg(ISessionMsg sessionMsg)
    {
        return sessionMsgMapper.insertSessionMsg(sessionMsg);
    }

    /**
     * 修改会话聊天记录
     *
     * @param sessionMsg 会话聊天记录
     * @return 结果
     */
    @Override
    public int updateSessionMsg(ISessionMsg sessionMsg)
    {
        sessionMsg.setUpdateTime(DateUtils.getNowDate());
        return sessionMsgMapper.updateSessionMsg(sessionMsg);
    }

    /**
     * 批量删除会话聊天记录
     *
     * @param ids 需要删除的会话聊天记录主键
     * @return 结果
     */
    @Override
    public int deleteSessionMsgByIds(Long[] ids)
    {
        return sessionMsgMapper.deleteSessionMsgByIds(ids);
    }

    /**
     * 删除会话聊天记录信息
     *
     * @param id 会话聊天记录主键
     * @return 结果
     */
    @Override
    public int deleteSessionMsgById(Long id)
    {
        return sessionMsgMapper.deleteSessionMsgById(id);
    }
}
