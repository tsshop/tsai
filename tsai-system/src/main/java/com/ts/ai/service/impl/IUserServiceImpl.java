package com.ts.ai.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ts.ai.domain.TsStatistics;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.IUserMapper;
import com.ts.ai.domain.IUser;
import com.ts.ai.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 用户Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class IUserServiceImpl extends ServiceImpl<IUserMapper, IUser> implements IUserService
{
    @Autowired
    private IUserMapper userMapper;

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public IUser selectUserById(Long id)
    {
        return userMapper.selectUserById(id);
    }

    /**
     * 查询用户列表
     *
     * @param user 用户
     * @return 用户
     */
    @Override
    public List<IUser> selectUserList(IUser user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 结果
     */
    @Override
    public int insertUser(IUser user)
    {
        user.setCreateTime(DateUtils.getNowDate());
        return userMapper.insertUser(user);
    }

    /**
     * 修改用户
     *
     * @param user 用户
     * @return 结果
     */
    @Override
    public int updateUser(IUser user)
    {
        user.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteUserByIds(Long[] ids)
    {
        return userMapper.deleteUserByIds(ids);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id)
    {
        return userMapper.deleteUserById(id);
    }

    @Override
    public List<TsStatistics> monthCount(LambdaQueryWrapper<IUser> wrapper) {
        return userMapper.monthCount(wrapper);
    }

    @Override
    public List<TsStatistics> dayCount(LambdaQueryWrapper<IUser> wrapper) {
        return userMapper.dayCount(wrapper);
    }

    @Override
    public List<TsStatistics> dayWeekCount(LambdaQueryWrapper<IUser> wrapper) {
        return userMapper.dayWeekCount(wrapper);
    }

    @Override
    public List<TsStatistics> hourCount(LambdaQueryWrapper<IUser> wrapper) {
        return userMapper.hourCount(wrapper);
    }

    @Override
    public List<TsStatistics> dayMonthCount(LambdaQueryWrapper<IUser> wrapper) {
        return userMapper.dayMonthCount(wrapper);
    }
}
