package com.ruoyi.out.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 各类蔬菜水果合格率情况对象 out_fru_veg_qualification
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outFruVegQualification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识每条记录 */
    private Long fruVegQualificationId;

    /** 蔬菜水果的种类 */
    @Excel(name = "蔬菜水果的种类")
    private String vegFruType;

    /** 抽样的总数 */
    @Excel(name = "抽样的总数")
    private Long samplingNumber;

    /** 合格的样本数 */
    @Excel(name = "合格的样本数")
    private Long passNumber;

    /** 总合格率，百分比 */
    @Excel(name = "总合格率，百分比")
    private BigDecimal passRate;

    /** 超标的样品名称 */
    @Excel(name = "超标的样品名称")
    private String exceedingSamples;

    /** 超标的农药名称 */
    @Excel(name = "超标的农药名称")
    private String exceedingPesticides;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public void setFruVegQualificationId(Long fruVegQualificationId) 
    {
        this.fruVegQualificationId = fruVegQualificationId;
    }

    public Long getFruVegQualificationId() 
    {
        return fruVegQualificationId;
    }
    public void setVegFruType(String vegFruType) 
    {
        this.vegFruType = vegFruType;
    }

    public String getVegFruType() 
    {
        return vegFruType;
    }
    public void setSamplingNumber(Long samplingNumber) 
    {
        this.samplingNumber = samplingNumber;
    }

    public Long getSamplingNumber() 
    {
        return samplingNumber;
    }
    public void setPassNumber(Long passNumber) 
    {
        this.passNumber = passNumber;
    }

    public Long getPassNumber() 
    {
        return passNumber;
    }
    public void setPassRate(BigDecimal passRate) 
    {
        this.passRate = passRate;
    }

    public BigDecimal getPassRate() 
    {
        return passRate;
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
    public void setCreatedDate(Date createdDate) 
    {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() 
    {
        return createdDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fruVegQualificationId", getFruVegQualificationId())
            .append("vegFruType", getVegFruType())
            .append("samplingNumber", getSamplingNumber())
            .append("passNumber", getPassNumber())
            .append("passRate", getPassRate())
            .append("exceedingSamples", getExceedingSamples())
            .append("exceedingPesticides", getExceedingPesticides())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
