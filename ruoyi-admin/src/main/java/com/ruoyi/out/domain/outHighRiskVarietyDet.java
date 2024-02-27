package com.ruoyi.out.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 高风险品种样品检出情况对象 out_high_risk_variety_det
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outHighRiskVarietyDet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

//    /** 唯一标识符，自增 */
//    private Long outHighRiskVarietyDetId;

    /** 抽样地点（被检单位） */
    @Excel(name = "抽样地点", readConverterExp = "被=检单位")
    private String samplingLocation;

    /** 检测单位（承检单位） */
    @Excel(name = "检测单位", readConverterExp = "承=检单位")
    private String detectLocation;

    /** 品种，蔬果具体名称 */
    @Excel(name = "品种，蔬果具体名称")
    private String vegFruName;

    /** 抽样总数 */
    @Excel(name = "抽样总数")
    private Long totalSamples;

    /** 合格数（个） */
    @Excel(name = "合格数", readConverterExp = "个=")
    private Long qualifiedNumber;

    /** 合格率（%） */
    @Excel(name = "合格率", readConverterExp = "%=")
    private BigDecimal qualificationRate;

    private String det_res;

    /** 禁用农药检出（可选字段） */
    @Excel(name = "禁用农药检出", readConverterExp = "可=选字段")
    private String prohibitedPesticideDetection;

    /** 常规农药超标（可选字段） */
    @Excel(name = "常规农药超标", readConverterExp = "可=选字段")
    private String routinePesticideExceedance;

    /** 备注（可选字段） */
    @Excel(name = "备注", readConverterExp = "可=选字段")
    private String remarks;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

//    public void setOutHighRiskVarietyDetId(Long outHighRiskVarietyDetId)
//    {
//        this.outHighRiskVarietyDetId = outHighRiskVarietyDetId;
//    }
//
//    public Long getOutHighRiskVarietyDetId()
//    {
//        return outHighRiskVarietyDetId;
//    }
    public void setSamplingLocation(String samplingLocation) 
    {
        this.samplingLocation = samplingLocation;
    }

    public String getSamplingLocation() 
    {
        return samplingLocation;
    }
    public void setDetectLocation(String detectLocation) 
    {
        this.detectLocation = detectLocation;
    }

    public String getDetectLocation() 
    {
        return detectLocation;
    }
    public void setVegFruName(String vegFruName) 
    {
        this.vegFruName = vegFruName;
    }

    public String getVegFruName() 
    {
        return vegFruName;
    }
    public void setTotalSamples(Long totalSamples) 
    {
        this.totalSamples = totalSamples;
    }

    public Long getTotalSamples() 
    {
        return totalSamples;
    }
    public void setQualifiedNumber(Long qualifiedNumber) 
    {
        this.qualifiedNumber = qualifiedNumber;
    }

    public Long getQualifiedNumber() 
    {
        return qualifiedNumber;
    }
    public void setQualificationRate(BigDecimal qualificationRate) 
    {
        this.qualificationRate = qualificationRate;
    }

    public BigDecimal getQualificationRate() 
    {
        return qualificationRate;
    }
    public void setProhibitedPesticideDetection(String prohibitedPesticideDetection) 
    {
        this.prohibitedPesticideDetection = prohibitedPesticideDetection;
    }

    public String getProhibitedPesticideDetection() 
    {
        return prohibitedPesticideDetection;
    }
    public void setRoutinePesticideExceedance(String routinePesticideExceedance)
    {
        this.routinePesticideExceedance = routinePesticideExceedance;
    }

    public String getDet_res() {
        return det_res;
    }

    public void setDet_res(String det_res) {
        this.det_res = det_res;
    }

    public String getRoutinePesticideExceedance()
    {
        return routinePesticideExceedance;
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
//            .append("outHighRiskVarietyDetId", getOutHighRiskVarietyDetId())
            .append("samplingLocation", getSamplingLocation())
            .append("detectLocation", getDetectLocation())
            .append("vegFruName", getVegFruName())
            .append("totalSamples", getTotalSamples())
            .append("qualifiedNumber", getQualifiedNumber())
            .append("qualificationRate", getQualificationRate())
            .append("prohibitedPesticideDetection", getProhibitedPesticideDetection())
//            .append("routinePesticideExceedance", getRoutinePesticideExceedance())
            .append("remarks", getRemarks())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
