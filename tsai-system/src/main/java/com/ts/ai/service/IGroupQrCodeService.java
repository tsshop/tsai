package com.ts.ai.service;

import java.util.List;
import com.ts.ai.domain.IGroupQrCode;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 群二维码Service接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IGroupQrCodeService extends IService<IGroupQrCode>
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
     * 批量删除群二维码
     *
     * @param ids 需要删除的群二维码主键集合
     * @return 结果
     */
    public int deleteGroupQrCodeByIds(Long[] ids);

    /**
     * 删除群二维码信息
     *
     * @param id 群二维码主键
     * @return 结果
     */
    public int deleteGroupQrCodeById(Long id);
}
