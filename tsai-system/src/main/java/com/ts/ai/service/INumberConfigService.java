package com.ts.ai.service;

import java.util.List;
import com.ts.ai.domain.INumberConfig;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 使用次数套餐Service接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface INumberConfigService extends IService<INumberConfig>
{
    /**
     * 查询使用次数套餐
     *
     * @param id 使用次数套餐主键
     * @return 使用次数套餐
     */
    public INumberConfig selectNumberConfigById(Long id);

    /**
     * 查询使用次数套餐列表
     *
     * @param numberConfig 使用次数套餐
     * @return 使用次数套餐集合
     */
    public List<INumberConfig> selectNumberConfigList(INumberConfig numberConfig);

    /**
     * 新增使用次数套餐
     *
     * @param numberConfig 使用次数套餐
     * @return 结果
     */
    public int insertNumberConfig(INumberConfig numberConfig);

    /**
     * 修改使用次数套餐
     *
     * @param numberConfig 使用次数套餐
     * @return 结果
     */
    public int updateNumberConfig(INumberConfig numberConfig);

    /**
     * 批量删除使用次数套餐
     *
     * @param ids 需要删除的使用次数套餐主键集合
     * @return 结果
     */
    public int deleteNumberConfigByIds(Long[] ids);

    /**
     * 删除使用次数套餐信息
     *
     * @param id 使用次数套餐主键
     * @return 结果
     */
    public int deleteNumberConfigById(Long id);
}
