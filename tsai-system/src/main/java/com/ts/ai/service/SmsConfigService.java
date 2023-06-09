package com.ts.ai.service;

import java.util.List;
import com.ts.ai.domain.SmsConfig;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 短信配置Service接口
 *
 * @author tsai
 * @date 2023-06-08
 */
public interface SmsConfigService extends IService<SmsConfig>
{
    /**
     * 查询短信配置
     *
     * @param id 短信配置主键
     * @return 短信配置
     */
    public SmsConfig selectSmsConfigById(Long id);

    /**
     * 查询短信配置列表
     *
     * @param smsConfig 短信配置
     * @return 短信配置集合
     */
    public List<SmsConfig> selectSmsConfigList(SmsConfig smsConfig);

    /**
     * 新增短信配置
     *
     * @param smsConfig 短信配置
     * @return 结果
     */
    public int insertSmsConfig(SmsConfig smsConfig);

    /**
     * 修改短信配置
     *
     * @param smsConfig 短信配置
     * @return 结果
     */
    public int updateSmsConfig(SmsConfig smsConfig);

    /**
     * 批量删除短信配置
     *
     * @param ids 需要删除的短信配置主键集合
     * @return 结果
     */
    public int deleteSmsConfigByIds(Long[] ids);

    /**
     * 删除短信配置信息
     *
     * @param id 短信配置主键
     * @return 结果
     */
    public int deleteSmsConfigById(Long id);
}
