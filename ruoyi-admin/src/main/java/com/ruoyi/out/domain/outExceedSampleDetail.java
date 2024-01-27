package com.ruoyi.out.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 超标蔬菜水果样品明细对象 out_exceed_sample_detail
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outExceedSampleDetail extends BaseEntity
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

    /** 超标农药品种 */
    @Excel(name = "超标农药品种")
    private String exceedPesticideName;

    /** 超标农药超标值 */
    @Excel(name = "超标农药超标值")
    private Long exceedPesticideValue;

    /** 限量值（GB2763-2021） */
    @Excel(name = "限量值", readConverterExp = "G=B2763-2021")
    private Long limitValue;

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

    public void setExceedSampleDetailId(Long exceedSampleDetailId) 
    {
        this.exceedSampleDetailId = exceedSampleDetailId;
    }

    public Long getExceedSampleDetailId() 
    {
        return exceedSampleDetailId;
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
    public void setExceedPesticideName(String exceedPesticideName) 
    {
        this.exceedPesticideName = exceedPesticideName;
    }

    public String getExceedPesticideName() 
    {
        return exceedPesticideName;
    }
    public void setExceedPesticideValue(Long exceedPesticideValue) 
    {
        this.exceedPesticideValue = exceedPesticideValue;
    }

    public Long getExceedPesticideValue() 
    {
        return exceedPesticideValue;
    }
    public void setLimitValue(Long limitValue) 
    {
        this.limitValue = limitValue;
    }

    public Long getLimitValue() 
    {
        return limitValue;
    }
    public void setActionTaken(String actionTaken) 
    {
        this.actionTaken = actionTaken;
    }

    public String getActionTaken() 
    {
        return actionTaken;
    }
    public void setInvestigator(String investigator) 
    {
        this.investigator = investigator;
    }

    public String getInvestigator() 
    {
        return investigator;
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
            .append("exceedSampleDetailId", getExceedSampleDetailId())
            .append("detectUnit", getDetectUnit())
            .append("sampleCode", getSampleCode())
            .append("vegFruName", getVegFruName())
            .append("samplingLocation", getSamplingLocation())
            .append("exceedPesticideName", getExceedPesticideName())
            .append("exceedPesticideValue", getExceedPesticideValue())
            .append("limitValue", getLimitValue())
            .append("actionTaken", getActionTaken())
            .append("investigator", getInvestigator())
            .append("remarks", getRemarks())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
