package com.ruoyi.out.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 定量监测结果汇总对象 out_dl_detect_records
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outDlDetectRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long recordDlId;

    /** 抽样地点（被检单位） */
    @Excel(name = "抽样地点", readConverterExp = "被=检单位")
    private String samplingLocation;

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

    /** 蔬菜抽样数 */
    @Excel(name = "蔬菜抽样数")
    private Long vegSamplingCount;

    /** 蔬菜合格数 */
    @Excel(name = "蔬菜合格数")
    private Long vegQualifiedCount;

    /** 蔬菜合格率 */
    @Excel(name = "蔬菜合格率")
    private Double vegPassRate;

    /** 水果抽样数 */
    @Excel(name = "水果抽样数")
    private Long fruSamplingCount;

    /** 水果合格数 */
    @Excel(name = "水果合格数")
    private Long fruQualifiedCount;

    /** 水果合格率 */
    @Excel(name = "水果合格率")
    private Double fruPassRate;

    /** 总抽样数 */
    @Excel(name = "总抽样数")
    private Long allSamplingCount;

    /** 总合格数 */
    @Excel(name = "总合格数")
    private Long allQualifiedCount;

    /** 总合格率，百分比 */
    @Excel(name = "总合格率，百分比")
    private BigDecimal allPassRate;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public void setRecordDlId(Long recordDlId) 
    {
        this.recordDlId = recordDlId;
    }

    public Long getRecordDlId() 
    {
        return recordDlId;
    }
    public void setSamplingLocation(String samplingLocation) 
    {
        this.samplingLocation = samplingLocation;
    }

    public String getSamplingLocation() 
    {
        return samplingLocation;
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
    public void setVegSamplingCount(Long vegSamplingCount) 
    {
        this.vegSamplingCount = vegSamplingCount;
    }

    public Long getVegSamplingCount() 
    {
        return vegSamplingCount;
    }
    public void setVegQualifiedCount(Long vegQualifiedCount) 
    {
        this.vegQualifiedCount = vegQualifiedCount;
    }

    public Long getVegQualifiedCount() 
    {
        return vegQualifiedCount;
    }
    public void setVegPassRate(Double vegPassRate) 
    {
        this.vegPassRate = vegPassRate;
    }

    public Double getVegPassRate() 
    {
        return vegPassRate;
    }
    public void setFruSamplingCount(Long fruSamplingCount) 
    {
        this.fruSamplingCount = fruSamplingCount;
    }

    public Long getFruSamplingCount() 
    {
        return fruSamplingCount;
    }
    public void setFruQualifiedCount(Long fruQualifiedCount) 
    {
        this.fruQualifiedCount = fruQualifiedCount;
    }

    public Long getFruQualifiedCount() 
    {
        return fruQualifiedCount;
    }
    public void setFruPassRate(Double fruPassRate) 
    {
        this.fruPassRate = fruPassRate;
    }

    public Double getFruPassRate() 
    {
        return fruPassRate;
    }
    public void setAllSamplingCount(Long allSamplingCount) 
    {
        this.allSamplingCount = allSamplingCount;
    }

    public Long getAllSamplingCount() 
    {
        return allSamplingCount;
    }
    public void setAllQualifiedCount(Long allQualifiedCount) 
    {
        this.allQualifiedCount = allQualifiedCount;
    }

    public Long getAllQualifiedCount() 
    {
        return allQualifiedCount;
    }
    public void setAllPassRate(BigDecimal allPassRate) 
    {
        this.allPassRate = allPassRate;
    }

    public BigDecimal getAllPassRate() 
    {
        return allPassRate;
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
            .append("recordDlId", getRecordDlId())
            .append("samplingLocation", getSamplingLocation())
            .append("samplingDate", getSamplingDate())
            .append("samplingYear", getSamplingYear())
            .append("samplingMonth", getSamplingMonth())
            .append("vegSamplingCount", getVegSamplingCount())
            .append("vegQualifiedCount", getVegQualifiedCount())
            .append("vegPassRate", getVegPassRate())
            .append("fruSamplingCount", getFruSamplingCount())
            .append("fruQualifiedCount", getFruQualifiedCount())
            .append("fruPassRate", getFruPassRate())
            .append("allSamplingCount", getAllSamplingCount())
            .append("allQualifiedCount", getAllQualifiedCount())
            .append("allPassRate", getAllPassRate())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
