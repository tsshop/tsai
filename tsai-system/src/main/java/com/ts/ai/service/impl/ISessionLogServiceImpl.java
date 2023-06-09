package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.ISessionLogMapper;
import com.ts.ai.domain.ISessionLog;
import com.ts.ai.service.ISessionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 会话记录Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class ISessionLogServiceImpl extends ServiceImpl<ISessionLogMapper, ISessionLog> implements ISessionLogService
{
    @Autowired
    private ISessionLogMapper sessionLogMapper;

    /**
     * 查询会话记录
     *
     * @param id 会话记录主键
     * @return 会话记录
     */
    @Override
    public ISessionLog selectSessionLogById(Long id)
    {
        return sessionLogMapper.selectSessionLogById(id);
    }

    /**
     * 查询会话记录列表
     *
     * @param sessionLog 会话记录
     * @return 会话记录
     */
    @Override
    public List<ISessionLog> selectSessionLogList(ISessionLog sessionLog)
    {
        return sessionLogMapper.selectSessionLogList(sessionLog);
    }

    /**
     * 新增会话记录
     *
     * @param sessionLog 会话记录
     * @return 结果
     */
    @Override
    public int insertSessionLog(ISessionLog sessionLog)
    {
        sessionLog.setCreateTime(DateUtils.getNowDate());
        return sessionLogMapper.insertSessionLog(sessionLog);
    }

    /**
     * 修改会话记录
     *
     * @param sessionLog 会话记录
     * @return 结果
     */
    @Override
    public int updateSessionLog(ISessionLog sessionLog)
    {
        sessionLog.setUpdateTime(DateUtils.getNowDate());
        return sessionLogMapper.updateSessionLog(sessionLog);
    }

    /**
     * 批量删除会话记录
     *
     * @param ids 需要删除的会话记录主键
     * @return 结果
     */
    @Override
    public int deleteSessionLogByIds(Long[] ids)
    {
        return sessionLogMapper.deleteSessionLogByIds(ids);
    }

    /**
     * 删除会话记录信息
     *
     * @param id 会话记录主键
     * @return 结果
     */
    @Override
    public int deleteSessionLogById(Long id)
    {
        return sessionLogMapper.deleteSessionLogById(id);
    }
}
