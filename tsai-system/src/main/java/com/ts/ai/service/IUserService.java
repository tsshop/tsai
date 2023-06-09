package com.ts.ai.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.ai.domain.TsStatistics;
import com.ts.ai.domain.IUser;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户Service接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IUserService extends IService<IUser>
{
    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    public IUser selectUserById(Long id);

    /**
     * 查询用户列表
     *
     * @param user 用户
     * @return 用户集合
     */
    public List<IUser> selectUserList(IUser user);

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 结果
     */
    public int insertUser(IUser user);

    /**
     * 修改用户
     *
     * @param user 用户
     * @return 结果
     */
    public int updateUser(IUser user);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteUserByIds(Long[] ids);

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deleteUserById(Long id);

    List<TsStatistics> monthCount(LambdaQueryWrapper<IUser> wrapper);

    List<TsStatistics> dayCount(LambdaQueryWrapper<IUser> wrapper);

    List<TsStatistics> dayWeekCount(LambdaQueryWrapper<IUser> wrapper);

    List<TsStatistics> hourCount(LambdaQueryWrapper<IUser> wrapper);

    List<TsStatistics> dayMonthCount(LambdaQueryWrapper<IUser> wrapper);
}
