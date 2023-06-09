package com.ts.ai.service;

import java.util.List;
import com.ts.ai.domain.IVipConfig;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * vip套餐Service接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface IVipConfigService extends IService<IVipConfig>
{
    /**
     * 查询vip套餐
     *
     * @param id vip套餐主键
     * @return vip套餐
     */
    public IVipConfig selectVipConfigById(Long id);

    /**
     * 查询vip套餐列表
     *
     * @param vipConfig vip套餐
     * @return vip套餐集合
     */
    public List<IVipConfig> selectVipConfigList(IVipConfig vipConfig);

    /**
     * 新增vip套餐
     *
     * @param vipConfig vip套餐
     * @return 结果
     */
    public int insertVipConfig(IVipConfig vipConfig);

    /**
     * 修改vip套餐
     *
     * @param vipConfig vip套餐
     * @return 结果
     */
    public int updateVipConfig(IVipConfig vipConfig);

    /**
     * 批量删除vip套餐
     *
     * @param ids 需要删除的vip套餐主键集合
     * @return 结果
     */
    public int deleteVipConfigByIds(Long[] ids);

    /**
     * 删除vip套餐信息
     *
     * @param id vip套餐主键
     * @return 结果
     */
    public int deleteVipConfigById(Long id);
}
