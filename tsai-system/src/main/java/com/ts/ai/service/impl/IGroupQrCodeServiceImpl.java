package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.IGroupQrCodeMapper;
import com.ts.ai.domain.IGroupQrCode;
import com.ts.ai.service.IGroupQrCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 群二维码Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class IGroupQrCodeServiceImpl extends ServiceImpl<IGroupQrCodeMapper, IGroupQrCode> implements IGroupQrCodeService
{
    @Autowired
    private IGroupQrCodeMapper groupQrCodeMapper;

    /**
     * 查询群二维码
     *
     * @param id 群二维码主键
     * @return 群二维码
     */
    @Override
    public IGroupQrCode selectGroupQrCodeById(Long id)
    {
        return groupQrCodeMapper.selectGroupQrCodeById(id);
    }

    /**
     * 查询群二维码列表
     *
     * @param groupQrCode 群二维码
     * @return 群二维码
     */
    @Override
    public List<IGroupQrCode> selectGroupQrCodeList(IGroupQrCode groupQrCode)
    {
        return groupQrCodeMapper.selectGroupQrCodeList(groupQrCode);
    }

    /**
     * 新增群二维码
     *
     * @param groupQrCode 群二维码
     * @return 结果
     */
    @Override
    public int insertGroupQrCode(IGroupQrCode groupQrCode)
    {
        groupQrCode.setCreateTime(DateUtils.getNowDate());
        return groupQrCodeMapper.insertGroupQrCode(groupQrCode);
    }

    /**
     * 修改群二维码
     *
     * @param groupQrCode 群二维码
     * @return 结果
     */
    @Override
    public int updateGroupQrCode(IGroupQrCode groupQrCode)
    {
        groupQrCode.setUpdateTime(DateUtils.getNowDate());
        return groupQrCodeMapper.updateGroupQrCode(groupQrCode);
    }

    /**
     * 批量删除群二维码
     *
     * @param ids 需要删除的群二维码主键
     * @return 结果
     */
    @Override
    public int deleteGroupQrCodeByIds(Long[] ids)
    {
        return groupQrCodeMapper.deleteGroupQrCodeByIds(ids);
    }

    /**
     * 删除群二维码信息
     *
     * @param id 群二维码主键
     * @return 结果
     */
    @Override
    public int deleteGroupQrCodeById(Long id)
    {
        return groupQrCodeMapper.deleteGroupQrCodeById(id);
    }
}
