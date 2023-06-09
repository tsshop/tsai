package com.ts.ai.service;

import java.util.List;
import com.ts.ai.domain.ITemplateType;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 模板类型Service接口
 *
 * @author tsai
 * @date 2023-05-16
 */
public interface ITemplateTypeService extends IService<ITemplateType>
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
     * 批量删除模板类型
     *
     * @param ids 需要删除的模板类型主键集合
     * @return 结果
     */
    public int deleteTemplateTypeByIds(Long[] ids);

    /**
     * 删除模板类型信息
     *
     * @param id 模板类型主键
     * @return 结果
     */
    public int deleteTemplateTypeById(Long id);
}
