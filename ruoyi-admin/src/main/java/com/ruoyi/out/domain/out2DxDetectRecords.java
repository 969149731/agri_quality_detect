package com.ruoyi.out.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 定性监测结果汇总对象 out_dx_detect_records        这个实体类用来定性检测汇总的导入
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class out2DxDetectRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "序号")
    private int recordDxId;

    /** 检测单位（承检单位） */
    @Excel(name = "检测单位名称")
    private String detectLocation;

    /** 抽样年份 */
    @Excel(name = "抽样年份")
    private String samplingYear;

//    @Excel(name = "抽样月份")
    private String samplingMonth;

    private String samplingCount;
    private String qualifiedCount;
    private String passRate;


    /** 抽样月份 */
//    @Excel(name = "抽样数(个)",groupName = "7月")   orderNum属性用于指定字段在Excel表格中的列顺序。
    @Excel(name = "抽样数(个)",fixedIndex = 2)       //fixedIndex直接指定了字段对应的列位置，而不是根据字段声明的顺序来确定列位置。
    private String month1SamplingCount;

    @Excel(name = "合格数(个)",fixedIndex = 3)
    private String month1QualifiedCount;

    @Excel(name = "合格率(%)",fixedIndex = 4)
    private String month1PassRate;

    @Excel(name = "抽样数(个)",fixedIndex = 5)       //fixedIndex直接指定了字段对应的列位置，而不是根据字段声明的顺序来确定列位置。
    private String month2SamplingCount;

    @Excel(name = "合格数(个)",fixedIndex = 6)
    private String month2QualifiedCount;

    @Excel(name = "合格率(%)",fixedIndex = 7)
    private String month2PassRate;


    @Excel(name = "抽样数(个)",fixedIndex = 8)       //fixedIndex直接指定了字段对应的列位置，而不是根据字段声明的顺序来确定列位置。
    private String month3SamplingCount;

    @Excel(name = "合格数(个)",fixedIndex = 9)
    private String month3QualifiedCount;

    @Excel(name = "合格率(%)",fixedIndex = 10)
    private String month3PassRate;


    /** 抽样数 */
    @Excel(name = "抽样数")
    private int allSamplingCount;

    /** 合格数 */
    @Excel(name = "合格数")
    private int allQualifiedCount;

    /** 合格率 */
    @Excel(name = "合格率")
    private BigDecimal allPassRate;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;


    public int getRecordDxId() {
        return recordDxId;
    }

    public void setRecordDxId(int recordDxId) {
        this.recordDxId = recordDxId;
    }

    public String getDetectLocation() {
        return detectLocation;
    }

    public void setDetectLocation(String detectLocation) {
        this.detectLocation = detectLocation;
    }

    public String getSamplingYear() {
        return samplingYear;
    }

    public void setSamplingYear(String samplingYear) {
        this.samplingYear = samplingYear;
    }

    public String getSamplingMonth() {
        return samplingMonth;
    }

    public void setSamplingMonth(String samplingMonth) {
        this.samplingMonth = samplingMonth;
    }

    public String getSamplingCount() {
        return samplingCount;
    }

    public void setSamplingCount(String samplingCount) {
        this.samplingCount = samplingCount;
    }

    public String getQualifiedCount() {
        return qualifiedCount;
    }

    public void setQualifiedCount(String qualifiedCount) {
        this.qualifiedCount = qualifiedCount;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public String getMonth1SamplingCount() {
        return month1SamplingCount;
    }

    public void setMonth1SamplingCount(String month1SamplingCount) {
        this.month1SamplingCount = month1SamplingCount;
    }

    public String getMonth1QualifiedCount() {
        return month1QualifiedCount;
    }

    public void setMonth1QualifiedCount(String month1QualifiedCount) {
        this.month1QualifiedCount = month1QualifiedCount;
    }

    public String getMonth1PassRate() {
        return month1PassRate;
    }

    public void setMonth1PassRate(String month1PassRate) {
        this.month1PassRate = month1PassRate;
    }

    public String getMonth2SamplingCount() {
        return month2SamplingCount;
    }

    public void setMonth2SamplingCount(String month2SamplingCount) {
        this.month2SamplingCount = month2SamplingCount;
    }

    public String getMonth2QualifiedCount() {
        return month2QualifiedCount;
    }

    public void setMonth2QualifiedCount(String month2QualifiedCount) {
        this.month2QualifiedCount = month2QualifiedCount;
    }

    public String getMonth2PassRate() {
        return month2PassRate;
    }

    public void setMonth2PassRate(String month2PassRate) {
        this.month2PassRate = month2PassRate;
    }

    public String getMonth3SamplingCount() {
        return month3SamplingCount;
    }

    public void setMonth3SamplingCount(String month3SamplingCount) {
        this.month3SamplingCount = month3SamplingCount;
    }

    public String getMonth3QualifiedCount() {
        return month3QualifiedCount;
    }

    public void setMonth3QualifiedCount(String month3QualifiedCount) {
        this.month3QualifiedCount = month3QualifiedCount;
    }

    public String getMonth3PassRate() {
        return month3PassRate;
    }

    public void setMonth3PassRate(String month3PassRate) {
        this.month3PassRate = month3PassRate;
    }

    public int getAllSamplingCount() {
        return allSamplingCount;
    }

    public void setAllSamplingCount(int allSamplingCount) {
        this.allSamplingCount = allSamplingCount;
    }

    public int getAllQualifiedCount() {
        return allQualifiedCount;
    }

    public void setAllQualifiedCount(int allQualifiedCount) {
        this.allQualifiedCount = allQualifiedCount;
    }

    public BigDecimal getAllPassRate() {
        return allPassRate;
    }

    public void setAllPassRate(BigDecimal allPassRate) {
        this.allPassRate = allPassRate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
