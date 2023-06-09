package com.ts.ai.mapper;

import java.util.List;
import com.ts.ai.domain.ISessionMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 会话聊天记录Mapper接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface ISessionMsgMapper extends BaseMapper<ISessionMsg>
{
    /**
     * 查询会话聊天记录
     *
     * @param id 会话聊天记录主键
     * @return 会话聊天记录
     */
    public ISessionMsg selectSessionMsgById(Long id);

    /**
     * 查询会话聊天记录列表
     *
     * @param sessionMsg 会话聊天记录
     * @return 会话聊天记录集合
     */
    public List<ISessionMsg> selectSessionMsgList(ISessionMsg sessionMsg);

    /**
     * 新增会话聊天记录
     *
     * @param sessionMsg 会话聊天记录
     * @return 结果
     */
    public int insertSessionMsg(ISessionMsg sessionMsg);

    /**
     * 修改会话聊天记录
     *
     * @param sessionMsg 会话聊天记录
     * @return 结果
     */
    public int updateSessionMsg(ISessionMsg sessionMsg);

    /**
     * 删除会话聊天记录
     *
     * @param id 会话聊天记录主键
     * @return 结果
     */
    public int deleteSessionMsgById(Long id);

    /**
     * 批量删除会话聊天记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSessionMsgByIds(Long[] ids);
}
