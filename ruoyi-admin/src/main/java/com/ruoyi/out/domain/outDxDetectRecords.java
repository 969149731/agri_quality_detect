package com.ruoyi.out.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 定性监测结果汇总对象 out_dx_detect_records
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outDxDetectRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long recordDxId;

    /** 检测单位（承检单位） */
    @Excel(name = "检测单位", readConverterExp = "承=检单位")
    private String detectLocation;

    /** 抽样日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "抽样日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date samplingDate;

    /** 抽样年份 */
    @Excel(name = "抽样年份")
    private Long samplingYear;

    /** 抽样月份 */
    @Excel(name = "抽样月份")
    private Long samplingMonth;

    /** 抽样数 */
    @Excel(name = "抽样数")
    private Long samplingCount;

    /** 合格数 */
    @Excel(name = "合格数")
    private Long qualifiedCount;

    /** 合格率 */
    @Excel(name = "合格率")
    private BigDecimal passRate;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public void setRecordDxId(Long recordDxId) 
    {
        this.recordDxId = recordDxId;
    }

    public Long getRecordDxId() 
    {
        return recordDxId;
    }
    public void setDetectLocation(String detectLocation) 
    {
        this.detectLocation = detectLocation;
    }

    public String getDetectLocation() 
    {
        return detectLocation;
    }
    public void setSamplingDate(Date samplingDate) 
    {
        this.samplingDate = samplingDate;
    }

    public Date getSamplingDate() 
    {
        return samplingDate;
    }
    public void setSamplingYear(Long samplingYear) 
    {
        this.samplingYear = samplingYear;
    }

    public Long getSamplingYear() 
    {
        return samplingYear;
    }
    public void setSamplingMonth(Long samplingMonth) 
    {
        this.samplingMonth = samplingMonth;
    }

    public Long getSamplingMonth() 
    {
        return samplingMonth;
    }
    public void setSamplingCount(Long samplingCount) 
    {
        this.samplingCount = samplingCount;
    }

    public Long getSamplingCount() 
    {
        return samplingCount;
    }
    public void setQualifiedCount(Long qualifiedCount) 
    {
        this.qualifiedCount = qualifiedCount;
    }

    public Long getQualifiedCount() 
    {
        return qualifiedCount;
    }
    public void setPassRate(BigDecimal passRate) 
    {
        this.passRate = passRate;
    }

    public BigDecimal getPassRate() 
    {
        return passRate;
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
            .append("recordDxId", getRecordDxId())
            .append("detectLocation", getDetectLocation())
            .append("samplingDate", getSamplingDate())
            .append("samplingYear", getSamplingYear())
            .append("samplingMonth", getSamplingMonth())
            .append("samplingCount", getSamplingCount())
            .append("qualifiedCount", getQualifiedCount())
            .append("passRate", getPassRate())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
