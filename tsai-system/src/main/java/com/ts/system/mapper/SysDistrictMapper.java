package com.ts.system.mapper;

import java.util.List;

import com.ts.system.domain.SysDistrict;

/**
 * 中国省市区数据Mapper接口
 *
 * @author : tsai
 * @date 2022-11-11
 */
public interface SysDistrictMapper
{
    /**
     * 查询中国省市区数据
     *
     * @param id 中国省市区数据主键
     * @return 中国省市区数据
     */
    public SysDistrict selectSysDistrictById(Integer id);

    /**
     * 查询中国省市区数据列表
     *
     * @param sysDistrict 中国省市区数据
     * @return 中国省市区数据集合
     */
    public List<SysDistrict> selectSysDistrictList(SysDistrict sysDistrict);

    /**
     * 新增中国省市区数据
     *
     * @param sysDistrict 中国省市区数据
     * @return 结果
     */
    public int insertSysDistrict(SysDistrict sysDistrict);

    /**
     * 修改中国省市区数据
     *
     * @param sysDistrict 中国省市区数据
     * @return 结果
     */
    public int updateSysDistrict(SysDistrict sysDistrict);

    /**
     * 删除中国省市区数据
     *
     * @param id 中国省市区数据主键
     * @return 结果
     */
    public int deleteSysDistrictById(Integer id);

    /**
     * 批量删除中国省市区数据
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDistrictByIds(Integer[] ids);
}
