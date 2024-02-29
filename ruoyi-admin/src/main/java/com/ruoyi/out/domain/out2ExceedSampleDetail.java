package com.ruoyi.out.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.domain.agriPesticideDetResultForOutExceedSampleDetail;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 超标蔬菜水果样品明细对象  这个实体类用来该模块前端的页面展示
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class out2ExceedSampleDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识符，自增 */
    private Long exceedSampleDetailId;

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

//    /** 超标农药品种 */
//    @Excel(name = "超标农药品种")
//    private String exceedPesticideName;
//
//    /** 超标农药超标值 */
//    @Excel(name = "超标农药超标值")
//    private Double exceedPesticideValue;

    @Excel(name = "超标农药品种及超标值 限量值")
    private List<agriPesticideDetResultForOutExceedSampleDetail> exceedPesticideNameAndPesticideValueAndlimitValue;

//    /** 限量值（GB2763-2021） */
//    @Excel(name = "限量值", readConverterExp = "G=B2763-2021")
//        private Double limitValue;

    /** 查处情况 */
    @Excel(name = "查处情况")
    private String actionTaken;

    /** 查处人 */
    @Excel(name = "查处人")
    private String investigator;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public Long getExceedSampleDetailId() {
        return exceedSampleDetailId;
    }

    public void setExceedSampleDetailId(Long exceedSampleDetailId) {
        this.exceedSampleDetailId = exceedSampleDetailId;
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

    public List<agriPesticideDetResultForOutExceedSampleDetail> getExceedPesticideNameAndPesticideValueAndlimitValue() {
        return exceedPesticideNameAndPesticideValueAndlimitValue;
    }

    public void setExceedPesticideNameAndPesticideValueAndlimitValue(List<agriPesticideDetResultForOutExceedSampleDetail> exceedPesticideNameAndPesticideValueAndlimitValue) {
        this.exceedPesticideNameAndPesticideValueAndlimitValue = exceedPesticideNameAndPesticideValueAndlimitValue;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getInvestigator() {
        return investigator;
    }

    public void setInvestigator(String investigator) {
        this.investigator = investigator;
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
        return "out2ExceedSampleDetail{" +
                "exceedSampleDetailId=" + exceedSampleDetailId +
                ", detectUnit='" + detectUnit + '\'' +
                ", sampleCode='" + sampleCode + '\'' +
                ", vegFruName='" + vegFruName + '\'' +
                ", samplingLocation='" + samplingLocation + '\'' +
                ", exceedPesticideNameAndPesticideValueAndlimitValue=" + exceedPesticideNameAndPesticideValueAndlimitValue +
                ", actionTaken='" + actionTaken + '\'' +
                ", investigator='" + investigator + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
