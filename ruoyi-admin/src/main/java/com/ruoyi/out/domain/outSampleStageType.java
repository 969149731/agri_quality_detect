package com.ruoyi.out.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 被抽样环节数量统计对象 out_sample_stage_type
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outSampleStageType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识符 */
    private Long sampleQualityId;

    /** 样品来源，抽样环节 */
    @Excel(name = "样品来源，抽样环节")
    private String samplingStageType;

    private String stageIncludeType;


    /** 无公害产品基地的数量或标识 */
    @Excel(name = "无公害产品基地的数量或标识")
    private Long unitNum=0L;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public outSampleStageType(String stageName){
        super();
        this.samplingStageType=stageName;
    }
    public outSampleStageType(){
        super();
    }
    public void setSampleQualityId(Long sampleQualityId)
    {
        this.sampleQualityId = sampleQualityId;
    }

    public Long getSampleQualityId()
    {
        return sampleQualityId;
    }
    public void setSamplingStageType(String samplingStageType)
    {
        this.samplingStageType = samplingStageType;
    }
    public String getSamplingStageType()
    {
        return samplingStageType;
    }
    public Long getUnitNum() {
        return unitNum;
    }
    public void setUnitNum(Long unitNum) {
        this.unitNum = unitNum;
    }
    public String getStageIncludeType() {
        return stageIncludeType;
    }

    public void setStageIncludeType(String stageIncludeType) {
        this.stageIncludeType = stageIncludeType;
    }

    public void setCreatedDate(Date createdDate) 
    {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() 
    {
        return createdDate;
    }
    public void addOneToUnitNum(){
        this.unitNum+=1;
    }
    public void addTotalTogether(outSampleStageType otherOne){
        this.unitNum+=otherOne.unitNum;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sampleQualityId", getSampleQualityId())
            .append("samplingStageType", getSamplingStageType())
            .append("unitNum", getUnitNum())
            .append("stageIncludeType", getStageIncludeType())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
