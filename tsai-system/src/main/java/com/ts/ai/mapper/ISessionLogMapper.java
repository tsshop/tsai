package com.ts.ai.mapper;

import java.util.List;
import com.ts.ai.domain.ISessionLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 会话记录Mapper接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface ISessionLogMapper extends BaseMapper<ISessionLog>
{
    /**
     * 查询会话记录
     *
     * @param id 会话记录主键
     * @return 会话记录
     */
    public ISessionLog selectSessionLogById(Long id);

    /**
     * 查询会话记录列表
     *
     * @param sessionLog 会话记录
     * @return 会话记录集合
     */
    public List<ISessionLog> selectSessionLogList(ISessionLog sessionLog);

    /**
     * 新增会话记录
     *
     * @param sessionLog 会话记录
     * @return 结果
     */
    public int insertSessionLog(ISessionLog sessionLog);

    /**
     * 修改会话记录
     *
     * @param sessionLog 会话记录
     * @return 结果
     */
    public int updateSessionLog(ISessionLog sessionLog);

    /**
     * 删除会话记录
     *
     * @param id 会话记录主键
     * @return 结果
     */
    public int deleteSessionLogById(Long id);

    /**
     * 批量删除会话记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSessionLogByIds(Long[] ids);
}
