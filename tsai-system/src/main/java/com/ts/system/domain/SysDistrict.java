package com.ts.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ts.common.annotation.Excel;
import com.ts.common.core.domain.BaseEntity;

/**
 * 中国省市区数据对象 sys_district
 *
 * @author : tsai
 * @date 2022-11-11
 */
public class SysDistrict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 省市区 */
    @Excel(name = "省市区")
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 层级 */
    @Excel(name = "层级")
    private Integer level;

    /** 父级 */
    @Excel(name = "父级")
    private Integer upid;

    /** 首字母 */
    @Excel(name = "首字母")
    private String en;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getLevel()
    {
        return level;
    }
    public void setUpid(Integer upid)
    {
        this.upid = upid;
    }

    public Integer getUpid()
    {
        return upid;
    }
    public void setEn(String en)
    {
        this.en = en;
    }

    public String getEn()
    {
        return en;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("level", getLevel())
            .append("upid", getUpid())
            .append("en", getEn())
            .toString();
    }
}
