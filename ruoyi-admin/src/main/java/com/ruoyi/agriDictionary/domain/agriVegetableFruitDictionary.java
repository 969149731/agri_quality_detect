package com.ruoyi.agriDictionary.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 蔬果表对象 agri_vegetable_fruit
 * 
 * @author chenjie
 * @date 2024-03-02
 */
public class agriVegetableFruitDictionary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键，自增长 */
    private Long vegetableFruitId;

    /** 蔬果类型（蔬菜或水果） */
    @Excel(name = "蔬果类型", readConverterExp = "蔬=菜或水果")
    private String vegFruType;

    /** 细分种类(瓜果类，叶菜类，豆类，根茎类等) */
    @Excel(name = "细分种类(瓜果类，叶菜类，豆类，根茎类等)")
    private String detailType;

    /** 蔬果具体名称 */
    @Excel(name = "蔬果具体名称")
    private String vegFruName;

    /** 是否为高风险品种样品，如果为高风险，在属性中填入高风险，若为空，就说明不是高风险品种样品 */
    @Excel(name = "是否为高风险品种样品，如果为高风险，在属性中填入高风险，若为空，就说明不是高风险品种样品")
    private String highRiskSample;

    /** 记录创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setVegetableFruitId(Long vegetableFruitId) 
    {
        this.vegetableFruitId = vegetableFruitId;
    }

    public Long getVegetableFruitId() 
    {
        return vegetableFruitId;
    }
    public void setVegFruType(String vegFruType) 
    {
        this.vegFruType = vegFruType;
    }

    public String getVegFruType() 
    {
        return vegFruType;
    }
    public void setDetailType(String detailType) 
    {
        this.detailType = detailType;
    }

    public String getDetailType() 
    {
        return detailType;
    }
    public void setVegFruName(String vegFruName) 
    {
        this.vegFruName = vegFruName;
    }

    public String getVegFruName() 
    {
        return vegFruName;
    }
    public void setHighRiskSample(String highRiskSample) 
    {
        this.highRiskSample = highRiskSample;
    }

    public String getHighRiskSample() 
    {
        return highRiskSample;
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
            .append("vegetableFruitId", getVegetableFruitId())
            .append("vegFruType", getVegFruType())
            .append("detailType", getDetailType())
            .append("vegFruName", getVegFruName())
            .append("highRiskSample", getHighRiskSample())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
