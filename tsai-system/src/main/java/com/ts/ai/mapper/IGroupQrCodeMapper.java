package com.ts.ai.mapper;

import java.util.List;
import com.ts.ai.domain.IGroupQrCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 群二维码Mapper接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IGroupQrCodeMapper extends BaseMapper<IGroupQrCode>
{
    /**
     * 查询群二维码
     *
     * @param id 群二维码主键
     * @return 群二维码
     */
    public IGroupQrCode selectGroupQrCodeById(Long id);

    /**
     * 查询群二维码列表
     *
     * @param groupQrCode 群二维码
     * @return 群二维码集合
     */
    public List<IGroupQrCode> selectGroupQrCodeList(IGroupQrCode groupQrCode);

    /**
     * 新增群二维码
     *
     * @param groupQrCode 群二维码
     * @return 结果
     */
    public int insertGroupQrCode(IGroupQrCode groupQrCode);

    /**
     * 修改群二维码
     *
     * @param groupQrCode 群二维码
     * @return 结果
     */
    public int updateGroupQrCode(IGroupQrCode groupQrCode);

    /**
     * 删除群二维码
     *
     * @param id 群二维码主键
     * @return 结果
     */
    public int deleteGroupQrCodeById(Long id);

    /**
     * 批量删除群二维码
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGroupQrCodeByIds(Long[] ids);
}
