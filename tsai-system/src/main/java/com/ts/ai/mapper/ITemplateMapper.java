package com.ts.ai.mapper;

import java.util.List;
import com.ts.ai.domain.ITemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 模板Mapper接口
 *
 * @author tsai
 * @date 2023-05-15
 */
public interface ITemplateMapper extends BaseMapper<ITemplate>
{
    /**
     * 查询模板
     *
     * @param id 模板主键
     * @return 模板
     */
    public ITemplate selectTemplateById(Long id);

    /**
     * 查询模板列表
     *
     * @param template 模板
     * @return 模板集合
     */
    public List<ITemplate> selectTemplateList(ITemplate template);

    /**
     * 新增模板
     *
     * @param template 模板
     * @return 结果
     */
    public int insertTemplate(ITemplate template);

    /**
     * 修改模板
     *
     * @param template 模板
     * @return 结果
     */
    public int updateTemplate(ITemplate template);

    /**
     * 删除模板
     *
     * @param id 模板主键
     * @return 结果
     */
    public int deleteTemplateById(Long id);

    /**
     * 批量删除模板
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplateByIds(Long[] ids);
}
