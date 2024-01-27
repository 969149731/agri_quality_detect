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

    /** 无公害产品基地的数量或标识 */
    @Excel(name = "无公害产品基地的数量或标识")
    private Long ollutionFreeBase;

    /** 地标产品基地的数量或标识 */
    @Excel(name = "地标产品基地的数量或标识")
    private Long landmarkProductBase;

    /** 绿色产品基地的数量或标识 */
    @Excel(name = "绿色产品基地的数量或标识")
    private Long greenProductBase;

    /** 有机产品基地的数量或标识 */
    @Excel(name = "有机产品基地的数量或标识")
    private Long organicProductBase;

    /** 散户的数量或标识 */
    @Excel(name = "散户的数量或标识")
    private Long individualHousehold;

    /** 其他基地的数量或标识 */
    @Excel(name = "其他基地的数量或标识")
    private Long otherBase;

    /** 批发市场的数量或标识 */
    @Excel(name = "批发市场的数量或标识")
    private Long wholesaleMarket;

    /** 运输车的数量或标识 */
    @Excel(name = "运输车的数量或标识")
    private Long transportVehicle;

    /** 合计 */
    @Excel(name = "合计")
    private Long allCount;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

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
    public void setOllutionFreeBase(Long ollutionFreeBase) 
    {
        this.ollutionFreeBase = ollutionFreeBase;
    }

    public Long getOllutionFreeBase() 
    {
        return ollutionFreeBase;
    }
    public void setLandmarkProductBase(Long landmarkProductBase) 
    {
        this.landmarkProductBase = landmarkProductBase;
    }

    public Long getLandmarkProductBase() 
    {
        return landmarkProductBase;
    }
    public void setGreenProductBase(Long greenProductBase) 
    {
        this.greenProductBase = greenProductBase;
    }

    public Long getGreenProductBase() 
    {
        return greenProductBase;
    }
    public void setOrganicProductBase(Long organicProductBase) 
    {
        this.organicProductBase = organicProductBase;
    }

    public Long getOrganicProductBase() 
    {
        return organicProductBase;
    }
    public void setIndividualHousehold(Long individualHousehold) 
    {
        this.individualHousehold = individualHousehold;
    }

    public Long getIndividualHousehold() 
    {
        return individualHousehold;
    }
    public void setOtherBase(Long otherBase) 
    {
        this.otherBase = otherBase;
    }

    public Long getOtherBase() 
    {
        return otherBase;
    }
    public void setWholesaleMarket(Long wholesaleMarket) 
    {
        this.wholesaleMarket = wholesaleMarket;
    }

    public Long getWholesaleMarket() 
    {
        return wholesaleMarket;
    }
    public void setTransportVehicle(Long transportVehicle) 
    {
        this.transportVehicle = transportVehicle;
    }

    public Long getTransportVehicle() 
    {
        return transportVehicle;
    }
    public void setAllCount(Long allCount) 
    {
        this.allCount = allCount;
    }

    public Long getAllCount() 
    {
        return allCount;
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
            .append("sampleQualityId", getSampleQualityId())
            .append("samplingStageType", getSamplingStageType())
            .append("ollutionFreeBase", getOllutionFreeBase())
            .append("landmarkProductBase", getLandmarkProductBase())
            .append("greenProductBase", getGreenProductBase())
            .append("organicProductBase", getOrganicProductBase())
            .append("individualHousehold", getIndividualHousehold())
            .append("otherBase", getOtherBase())
            .append("wholesaleMarket", getWholesaleMarket())
            .append("transportVehicle", getTransportVehicle())
            .append("allCount", getAllCount())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
