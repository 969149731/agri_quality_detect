package com.ruoyi.out.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 无判定标准检出值详细对象 out_undetermined_stand_det
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outUndeterminedStandDet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识符，自增 */
    private Long undeterminedStandardDetectionId;

    /** 检测单位 */
    @Excel(name = "检测单位")
    private String detectUnit;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleCode;

    /** 样品名称 */
    @Excel(name = "样品名称")
    private String vegFruName;

    /** 抽样地点（被检单位） */
    @Excel(name = "抽样地点", readConverterExp = "被=检单位")
    private String samplingLocation;

    /** 检出农药品种 */
    @Excel(name = "检出农药品种")
    private String detPesticideName;

    /** 检出农药值 */
    @Excel(name = "检出农药值")
    private Long detPesticideValue;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public void setUndeterminedStandardDetectionId(Long undeterminedStandardDetectionId) 
    {
        this.undeterminedStandardDetectionId = undeterminedStandardDetectionId;
    }

    public Long getUndeterminedStandardDetectionId() 
    {
        return undeterminedStandardDetectionId;
    }
    public void setDetectUnit(String detectUnit) 
    {
        this.detectUnit = detectUnit;
    }

    public String getDetectUnit() 
    {
        return detectUnit;
    }
    public void setSampleCode(String sampleCode) 
    {
        this.sampleCode = sampleCode;
    }

    public String getSampleCode() 
    {
        return sampleCode;
    }
    public void setVegFruName(String vegFruName) 
    {
        this.vegFruName = vegFruName;
    }

    public String getVegFruName() 
    {
        return vegFruName;
    }
    public void setSamplingLocation(String samplingLocation) 
    {
        this.samplingLocation = samplingLocation;
    }

    public String getSamplingLocation() 
    {
        return samplingLocation;
    }
    public void setDetPesticideName(String detPesticideName) 
    {
        this.detPesticideName = detPesticideName;
    }

    public String getDetPesticideName() 
    {
        return detPesticideName;
    }
    public void setDetPesticideValue(Long detPesticideValue) 
    {
        this.detPesticideValue = detPesticideValue;
    }

    public Long getDetPesticideValue() 
    {
        return detPesticideValue;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("undeterminedStandardDetectionId", getUndeterminedStandardDetectionId())
            .append("detectUnit", getDetectUnit())
            .append("sampleCode", getSampleCode())
            .append("vegFruName", getVegFruName())
            .append("samplingLocation", getSamplingLocation())
            .append("detPesticideName", getDetPesticideName())
            .append("detPesticideValue", getDetPesticideValue())
            .append("remarks", getRemarks())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
