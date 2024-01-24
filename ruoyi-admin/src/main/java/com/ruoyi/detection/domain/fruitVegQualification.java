package com.ruoyi.detection.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 蔬果检测对象 qu_fruit_veg_qualificationas
 * 
 * @author chenjie
 * @date 2024-01-19
 */
public class fruitVegQualification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录编号 */
    private Long id;

    /** 蔬果种类 */
    @Excel(name = "蔬果种类")
    private String detailCategory;

    /** 抽样数 */
    @Excel(name = "抽样数")
    private Long samplingNumber;

    /** 合格数 */
    @Excel(name = "合格数")
    private Long qualifiedNumber;

    /** 合格率 */
    @Excel(name = "合格率")
    private Long qualificationRate;

    /** 超标样品 */
    @Excel(name = "超标样品")
    private String exceedingSamples;

    /** 超标农药 */
    @Excel(name = "超标农药")
    private String exceedingPesticides;

    /** 数据来源 */
    @Excel(name = "数据来源")
    private String sourceArea;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDetailCategory(String detailCategory) 
    {
        this.detailCategory = detailCategory;
    }

    public String getDetailCategory() 
    {
        return detailCategory;
    }
    public void setSamplingNumber(Long samplingNumber) 
    {
        this.samplingNumber = samplingNumber;
    }

    public Long getSamplingNumber() 
    {
        return samplingNumber;
    }
    public void setQualifiedNumber(Long qualifiedNumber) 
    {
        this.qualifiedNumber = qualifiedNumber;
    }

    public Long getQualifiedNumber() 
    {
        return qualifiedNumber;
    }
    public void setQualificationRate(Long qualificationRate) 
    {
        this.qualificationRate = qualificationRate;
    }

    public Long getQualificationRate() 
    {
        return qualificationRate;
    }
    public void setExceedingSamples(String exceedingSamples) 
    {
        this.exceedingSamples = exceedingSamples;
    }

    public String getExceedingSamples() 
    {
        return exceedingSamples;
    }
    public void setExceedingPesticides(String exceedingPesticides) 
    {
        this.exceedingPesticides = exceedingPesticides;
    }

    public String getExceedingPesticides() 
    {
        return exceedingPesticides;
    }
    public void setSourceArea(String sourceArea) 
    {
        this.sourceArea = sourceArea;
    }

    public String getSourceArea() 
    {
        return sourceArea;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("detailCategory", getDetailCategory())
            .append("samplingNumber", getSamplingNumber())
            .append("qualifiedNumber", getQualifiedNumber())
            .append("qualificationRate", getQualificationRate())
            .append("exceedingSamples", getExceedingSamples())
            .append("exceedingPesticides", getExceedingPesticides())
            .append("sourceArea", getSourceArea())
            .toString();
    }
}
