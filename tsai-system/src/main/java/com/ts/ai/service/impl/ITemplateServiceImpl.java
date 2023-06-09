package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.ITemplateMapper;
import com.ts.ai.domain.ITemplate;
import com.ts.ai.service.ITemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 模板Service业务层处理
 *
 * @author tsai
 * @date 2023-05-15
 */
@Service
public class ITemplateServiceImpl extends ServiceImpl<ITemplateMapper, ITemplate> implements ITemplateService
{
    @Autowired
    private ITemplateMapper templateMapper;

    /**
     * 查询模板
     *
     * @param id 模板主键
     * @return 模板
     */
    @Override
    public ITemplate selectTemplateById(Long id)
    {
        return templateMapper.selectTemplateById(id);
    }

    /**
     * 查询模板列表
     *
     * @param template 模板
     * @return 模板
     */
    @Override
    public List<ITemplate> selectTemplateList(ITemplate template)
    {
        return templateMapper.selectTemplateList(template);
    }

    /**
     * 新增模板
     *
     * @param template 模板
     * @return 结果
     */
    @Override
    public int insertTemplate(ITemplate template)
    {
        template.setCreateTime(DateUtils.getNowDate());
        return templateMapper.insertTemplate(template);
    }

    /**
     * 修改模板
     *
     * @param template 模板
     * @return 结果
     */
    @Override
    public int updateTemplate(ITemplate template)
    {
        template.setUpdateTime(DateUtils.getNowDate());
        return templateMapper.updateTemplate(template);
    }

    /**
     * 批量删除模板
     *
     * @param ids 需要删除的模板主键
     * @return 结果
     */
    @Override
    public int deleteTemplateByIds(Long[] ids)
    {
        return templateMapper.deleteTemplateByIds(ids);
    }

    /**
     * 删除模板信息
     *
     * @param id 模板主键
     * @return 结果
     */
    @Override
    public int deleteTemplateById(Long id)
    {
        return templateMapper.deleteTemplateById(id);
    }
}
