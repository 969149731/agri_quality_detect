package com.ruoyi.agriDictionary.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 农药最大残留限量参考标准对象 agri_pesticide_residue_standard
 * 
 * @author chenjie
 * @date 2024-03-02
 */
public class agriPesticideResidueStandardDictionary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键，自增长 */
    private Long pesticideResidueStandardId;

    /** 农药名字，逻辑外键，关联农药表 */
    @Excel(name = "农药名")
    private String pesticideName;

    /** 蔬菜或水果名字，逻辑外键，关联蔬果表 */
    @Excel(name = "样品名")
    private String vegFruName;

    /** 标准分类（国家标准，CAC，日本，欧盟，美国，韩国） */
    @Excel(name = "标准分类")
    private String standardCategory;

    /** 标准值（mg/kg） */
    @Excel(name = "标准值")
    private BigDecimal standardValue;

    /** 记录创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setPesticideResidueStandardId(Long pesticideResidueStandardId) 
    {
        this.pesticideResidueStandardId = pesticideResidueStandardId;
    }

    public Long getPesticideResidueStandardId() 
    {
        return pesticideResidueStandardId;
    }
    public void setPesticideName(String pesticideName) 
    {
        this.pesticideName = pesticideName;
    }

    public String getPesticideName() 
    {
        return pesticideName;
    }
    public void setVegFruName(String vegFruName) 
    {
        this.vegFruName = vegFruName;
    }

    public String getVegFruName() 
    {
        return vegFruName;
    }
    public void setStandardCategory(String standardCategory) 
    {
        this.standardCategory = standardCategory;
    }

    public String getStandardCategory() 
    {
        return standardCategory;
    }
    public void setStandardValue(BigDecimal standardValue) 
    {
        this.standardValue = standardValue;
    }

    public BigDecimal getStandardValue() 
    {
        return standardValue;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pesticideResidueStandardId", getPesticideResidueStandardId())
            .append("pesticideName", getPesticideName())
            .append("vegFruName", getVegFruName())
            .append("standardCategory", getStandardCategory())
            .append("standardValue", getStandardValue())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
