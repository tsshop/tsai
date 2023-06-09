package com.ts.ai.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ts.ai.domain.TsStatistics;
import com.ts.ai.domain.IUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户Mapper接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IUserMapper extends BaseMapper<IUser>
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
     * 删除用户
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deleteUserById(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserByIds(Long[] ids);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%m' ) AS unitNum, " +
            " COUNT( 1 ) AS count  " +
            "FROM " +
            " user  " +
            " ${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsStatistics> monthCount(@Param(Constants.WRAPPER) LambdaQueryWrapper<IUser> wrapper);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%Y%m%d' ) AS unitNum, " +
            " COUNT( 1 ) AS count  " +
            "FROM " +
            " user  " +
            " ${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsStatistics> dayCount(@Param(Constants.WRAPPER) LambdaQueryWrapper<IUser> wrapper);

    @Select("SELECT " +
            " WEEKDAY(create_time)+1 AS unitNum, " +
            " COUNT( 1 ) AS count  " +
            "FROM " +
            " user  " +
            " ${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsStatistics> dayWeekCount(@Param(Constants.WRAPPER) LambdaQueryWrapper<IUser> wrapper);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%H' ) AS unitNum, " +
            " COUNT( 1 ) AS count  " +
            "FROM " +
            " user  " +
            " ${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsStatistics> hourCount(@Param(Constants.WRAPPER) LambdaQueryWrapper<IUser> wrapper);

    @Select("SELECT " +
            " DATE_FORMAT( create_time, '%d' ) AS unitNum, " +
            " COUNT( 1 ) AS count  " +
            "FROM " +
            " user  " +
            " ${ew.customSqlSegment}  " +
            "GROUP BY " +
            " unitNum")
    List<TsStatistics> dayMonthCount(@Param(Constants.WRAPPER) LambdaQueryWrapper<IUser> wrapper);
}
