package com.ruoyi.out.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.detection.domain.agriPesticideDetResultForOutExceedSampleDetail;
import com.ruoyi.detection.domain.agriPesticideDetResultForUndeterminedStandDet;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 无判定标准检出值详细对象  这个实体类用来该模块前端的页面展示
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class out2UndeterminedStandDet extends BaseEntity
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

//    /** 检出农药品种 */
//    @Excel(name = "检出农药品种")
//    private String detPesticideName;
//
//    /** 检出农药值 */
//    @Excel(name = "检出农药值")
//    private Long detPesticideValue;

    @Excel(name = "检出农药品种 检出值")
    private List<agriPesticideDetResultForUndeterminedStandDet> detPesticideNameAndValue;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public Long getUndeterminedStandardDetectionId() {
        return undeterminedStandardDetectionId;
    }

    public void setUndeterminedStandardDetectionId(Long undeterminedStandardDetectionId) {
        this.undeterminedStandardDetectionId = undeterminedStandardDetectionId;
    }

    public String getDetectUnit() {
        return detectUnit;
    }

    public void setDetectUnit(String detectUnit) {
        this.detectUnit = detectUnit;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getVegFruName() {
        return vegFruName;
    }

    public void setVegFruName(String vegFruName) {
        this.vegFruName = vegFruName;
    }

    public String getSamplingLocation() {
        return samplingLocation;
    }

    public void setSamplingLocation(String samplingLocation) {
        this.samplingLocation = samplingLocation;
    }

    public List<agriPesticideDetResultForUndeterminedStandDet> getDetPesticideNameAndValue() {
        return detPesticideNameAndValue;
    }

    public void setDetPesticideNameAndValue(List<agriPesticideDetResultForUndeterminedStandDet> detPesticideNameAndValue) {
        this.detPesticideNameAndValue = detPesticideNameAndValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "out2UndeterminedStandDet{" +
                "undeterminedStandardDetectionId=" + undeterminedStandardDetectionId +
                ", detectUnit='" + detectUnit + '\'' +
                ", sampleCode='" + sampleCode + '\'' +
                ", vegFruName='" + vegFruName + '\'' +
                ", samplingLocation='" + samplingLocation + '\'' +
                ", detPesticideNameAndValue=" + detPesticideNameAndValue +
                ", remarks='" + remarks + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
