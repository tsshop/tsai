package com.ts.system.service.impl;

import java.util.List;

import com.ts.system.domain.SysDistrict;
import com.ts.system.mapper.SysDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.system.service.ISysDistrictService;

/**
 * 中国省市区数据Service业务层处理
 *
 * @author : tsai
 * @date 2022-11-11
 */
@Service
public class SysDistrictServiceImpl implements ISysDistrictService
{
    @Autowired
    private SysDistrictMapper sysDistrictMapper;

    /**
     * 查询中国省市区数据
     *
     * @param id 中国省市区数据主键
     * @return 中国省市区数据
     */
    @Override
    public SysDistrict selectSysDistrictById(Integer id)
    {
        return sysDistrictMapper.selectSysDistrictById(id);
    }

    /**
     * 查询中国省市区数据列表
     *
     * @param sysDistrict 中国省市区数据
     * @return 中国省市区数据
     */
    @Override
    public List<SysDistrict> selectSysDistrictList(SysDistrict sysDistrict)
    {
        return sysDistrictMapper.selectSysDistrictList(sysDistrict);
    }

    /**
     * 新增中国省市区数据
     *
     * @param sysDistrict 中国省市区数据
     * @return 结果
     */
    @Override
    public int insertSysDistrict(SysDistrict sysDistrict)
    {
        return sysDistrictMapper.insertSysDistrict(sysDistrict);
    }

    /**
     * 修改中国省市区数据
     *
     * @param sysDistrict 中国省市区数据
     * @return 结果
     */
    @Override
    public int updateSysDistrict(SysDistrict sysDistrict)
    {
        return sysDistrictMapper.updateSysDistrict(sysDistrict);
    }

    /**
     * 批量删除中国省市区数据
     *
     * @param ids 需要删除的中国省市区数据主键
     * @return 结果
     */
    @Override
    public int deleteSysDistrictByIds(Integer[] ids)
    {
        return sysDistrictMapper.deleteSysDistrictByIds(ids);
    }

    /**
     * 删除中国省市区数据信息
     *
     * @param id 中国省市区数据主键
     * @return 结果
     */
    @Override
    public int deleteSysDistrictById(Integer id)
    {
        return sysDistrictMapper.deleteSysDistrictById(id);
    }
}
