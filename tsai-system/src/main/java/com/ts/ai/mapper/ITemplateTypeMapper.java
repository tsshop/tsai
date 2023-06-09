package com.ts.ai.mapper;

import java.util.List;
import com.ts.ai.domain.ITemplateType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 模板类型Mapper接口
 *
 * @author tsai
 * @date 2023-05-16
 */
public interface ITemplateTypeMapper extends BaseMapper<ITemplateType>
{
    /**
     * 查询模板类型
     *
     * @param id 模板类型主键
     * @return 模板类型
     */
    public ITemplateType selectTemplateTypeById(Long id);

    /**
     * 查询模板类型列表
     *
     * @param templateType 模板类型
     * @return 模板类型集合
     */
    public List<ITemplateType> selectTemplateTypeList(ITemplateType templateType);

    /**
     * 新增模板类型
     *
     * @param templateType 模板类型
     * @return 结果
     */
    public int insertTemplateType(ITemplateType templateType);

    /**
     * 修改模板类型
     *
     * @param templateType 模板类型
     * @return 结果
     */
    public int updateTemplateType(ITemplateType templateType);

    /**
     * 删除模板类型
     *
     * @param id 模板类型主键
     * @return 结果
     */
    public int deleteTemplateTypeById(Long id);

    /**
     * 批量删除模板类型
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplateTypeByIds(Long[] ids);
}
