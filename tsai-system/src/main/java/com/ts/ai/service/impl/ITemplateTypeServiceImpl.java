package com.ts.ai.service.impl;

import java.util.List;
import com.ts.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.ai.mapper.ITemplateTypeMapper;
import com.ts.ai.domain.ITemplateType;
import com.ts.ai.service.ITemplateTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * 模板类型Service业务层处理
 *
 * @author tsai
 * @date 2023-05-16
 */
@Service
public class ITemplateTypeServiceImpl extends ServiceImpl<ITemplateTypeMapper, ITemplateType> implements ITemplateTypeService
{
    @Autowired
    private ITemplateTypeMapper templateTypeMapper;

    /**
     * 查询模板类型
     *
     * @param id 模板类型主键
     * @return 模板类型
     */
    @Override
    public ITemplateType selectTemplateTypeById(Long id)
    {
        return templateTypeMapper.selectTemplateTypeById(id);
    }

    /**
     * 查询模板类型列表
     *
     * @param templateType 模板类型
     * @return 模板类型
     */
    @Override
    public List<ITemplateType> selectTemplateTypeList(ITemplateType templateType)
    {
        return templateTypeMapper.selectTemplateTypeList(templateType);
    }

    /**
     * 新增模板类型
     *
     * @param templateType 模板类型
     * @return 结果
     */
    @Override
    public int insertTemplateType(ITemplateType templateType)
    {
        templateType.setCreateTime(DateUtils.getNowDate());
        return templateTypeMapper.insertTemplateType(templateType);
    }

    /**
     * 修改模板类型
     *
     * @param templateType 模板类型
     * @return 结果
     */
    @Override
    public int updateTemplateType(ITemplateType templateType)
    {
        templateType.setUpdateTime(DateUtils.getNowDate());
        return templateTypeMapper.updateTemplateType(templateType);
    }

    /**
     * 批量删除模板类型
     *
     * @param ids 需要删除的模板类型主键
     * @return 结果
     */
    @Override
    public int deleteTemplateTypeByIds(Long[] ids)
    {
        return templateTypeMapper.deleteTemplateTypeByIds(ids);
    }

    /**
     * 删除模板类型信息
     *
     * @param id 模板类型主键
     * @return 结果
     */
    @Override
    public int deleteTemplateTypeById(Long id)
    {
        return templateTypeMapper.deleteTemplateTypeById(id);
    }
}
