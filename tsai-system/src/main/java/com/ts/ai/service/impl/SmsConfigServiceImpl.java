package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.SmsConfigMapper;
import com.ts.ai.domain.SmsConfig;
import com.ts.ai.service.SmsConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 短信配置Service业务层处理
 *
 * @author tsai
 * @date 2023-06-08
 */
@Service
public class SmsConfigServiceImpl extends ServiceImpl<SmsConfigMapper, SmsConfig> implements SmsConfigService
{
    @Autowired
    private SmsConfigMapper smsConfigMapper;

    /**
     * 查询短信配置
     *
     * @param id 短信配置主键
     * @return 短信配置
     */
    @Override
    public SmsConfig selectSmsConfigById(Long id)
    {
        return smsConfigMapper.selectSmsConfigById(id);
    }

    /**
     * 查询短信配置列表
     *
     * @param smsConfig 短信配置
     * @return 短信配置
     */
    @Override
    public List<SmsConfig> selectSmsConfigList(SmsConfig smsConfig)
    {
        return smsConfigMapper.selectSmsConfigList(smsConfig);
    }

    /**
     * 新增短信配置
     *
     * @param smsConfig 短信配置
     * @return 结果
     */
    @Override
    public int insertSmsConfig(SmsConfig smsConfig)
    {
        smsConfig.setCreateTime(DateUtils.getNowDate());
        return smsConfigMapper.insertSmsConfig(smsConfig);
    }

    /**
     * 修改短信配置
     *
     * @param smsConfig 短信配置
     * @return 结果
     */
    @Override
    public int updateSmsConfig(SmsConfig smsConfig)
    {
        smsConfig.setUpdateTime(DateUtils.getNowDate());
        return smsConfigMapper.updateSmsConfig(smsConfig);
    }

    /**
     * 批量删除短信配置
     *
     * @param ids 需要删除的短信配置主键
     * @return 结果
     */
    @Override
    public int deleteSmsConfigByIds(Long[] ids)
    {
        return smsConfigMapper.deleteSmsConfigByIds(ids);
    }

    /**
     * 删除短信配置信息
     *
     * @param id 短信配置主键
     * @return 结果
     */
    @Override
    public int deleteSmsConfigById(Long id)
    {
        return smsConfigMapper.deleteSmsConfigById(id);
    }
}
