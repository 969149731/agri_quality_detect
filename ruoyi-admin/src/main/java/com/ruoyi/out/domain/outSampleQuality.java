package com.ruoyi.out.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 各抽样环节合格率情况对象 out_sample_quality
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public class outSampleQuality extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识符 */
    private Long sampleQualityId;

    /** 样品来源，
抽样环节 */
    @Excel(name = "样品来源， 抽样环节")
    private String samplingStageType;

    private String stageIncludeType;//子类
    /** 无公害产品基地的描述或标识 */
    @Excel(name = "无公害产品基地的描述或标识")
    private String pollutionFreeBase;

    /** 地标产品基地的描述或标识 */
    @Excel(name = "地标产品基地的描述或标识")
    private String landmarkProductBase;

    /** 绿色产品基地的描述或标识 */
    @Excel(name = "绿色产品基地的描述或标识")
    private String greenProductBase;

    /** 有机产品基地的描述或标识 */
    @Excel(name = "有机产品基地的描述或标识")
    private String organicProductBase;

    /** 散户的描述或标识 */
    @Excel(name = "散户的描述或标识")
    private String individualHousehold;

    /** 其他基地的描述或标识 */
    @Excel(name = "其他基地的描述或标识")
    private String otherBase;

    /** 批发市场的描述或标识 */
    @Excel(name = "批发市场的描述或标识")
    private String wholesaleMarket;

    /** 运输车的描述或标识 */
    @Excel(name = "运输车的描述或标识")
    private String transportVehicle;

    /** 合计 */
    @Excel(name = "合计")
    private Long allCount;

    /** 蔬菜的抽样数 */
    @Excel(name = "蔬菜的抽样数")
    private Long vegSamplingCount;

    /** 蔬菜的合格数 */
    @Excel(name = "蔬菜的合格数")
    private Long vegQualifiedCount;

    /** 蔬菜的合格率，以百分比表示 */
    @Excel(name = "蔬菜的合格率，以百分比表示")
    private BigDecimal vegQualificationRate;

    /** 水果的抽样数 */
    @Excel(name = "水果的抽样数")
    private Long fruSamplingCount;

    /** 水果的合格数 */
    @Excel(name = "水果的合格数")
    private Long fruQualifiedCount;

    /** 水果的合格率，以百分比表示 */
    @Excel(name = "水果的合格率，以百分比表示")
    private BigDecimal fruQualificationRate;

    /** 总抽样数（蔬菜和水果的总和） */
    @Excel(name = "总抽样数", readConverterExp = "蔬=菜和水果的总和")
    private Long totalSamplingCount;

    /** 总合格数（蔬菜和水果的总和） */
    @Excel(name = "总合格数", readConverterExp = "蔬=菜和水果的总和")
    private Long totalQualifiedCount;

    /** 总合格率，以百分比表示 */
    @Excel(name = "总合格率，以百分比表示")
    private BigDecimal totalQualificationRate;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public outSampleQuality(){
        super();
    };
    public outSampleQuality(String typeName){
        super();
        this.samplingStageType=typeName;
        this.stageIncludeType="";
        this.vegSamplingCount=0L;
        this.vegQualifiedCount=0L;
        this.vegQualificationRate=new BigDecimal("0.0");;
        this.fruSamplingCount=0L;
        this.fruQualifiedCount=0L;
        this.fruQualificationRate=new BigDecimal("0.0");;
        this.totalSamplingCount=0L;
        this.totalQualifiedCount=0L;
        this.totalQualificationRate=new BigDecimal("0.0");;
    };
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
    public void setPollutionFreeBase(String pollutionFreeBase) 
    {
        this.pollutionFreeBase = pollutionFreeBase;
    }

    public String getPollutionFreeBase() 
    {
        return pollutionFreeBase;
    }
    public void setLandmarkProductBase(String landmarkProductBase) 
    {
        this.landmarkProductBase = landmarkProductBase;
    }

    public String getLandmarkProductBase() 
    {
        return landmarkProductBase;
    }
    public void setGreenProductBase(String greenProductBase) 
    {
        this.greenProductBase = greenProductBase;
    }

    public String getGreenProductBase() 
    {
        return greenProductBase;
    }
    public void setOrganicProductBase(String organicProductBase) 
    {
        this.organicProductBase = organicProductBase;
    }

    public String getOrganicProductBase() 
    {
        return organicProductBase;
    }
    public void setIndividualHousehold(String individualHousehold) 
    {
        this.individualHousehold = individualHousehold;
    }

    public String getIndividualHousehold() 
    {
        return individualHousehold;
    }
    public void setOtherBase(String otherBase) 
    {
        this.otherBase = otherBase;
    }

    public String getOtherBase() 
    {
        return otherBase;
    }
    public void setWholesaleMarket(String wholesaleMarket) 
    {
        this.wholesaleMarket = wholesaleMarket;
    }

    public String getWholesaleMarket() 
    {
        return wholesaleMarket;
    }
    public void setTransportVehicle(String transportVehicle) 
    {
        this.transportVehicle = transportVehicle;
    }

    public String getTransportVehicle() 
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
    public void setVegQualificationRate(BigDecimal vegQualificationRate) 
    {
        this.vegQualificationRate = vegQualificationRate;
    }

    public BigDecimal getVegQualificationRate() 
    {
        return vegQualificationRate;
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
    public void setFruQualificationRate(BigDecimal fruQualificationRate) 
    {
        this.fruQualificationRate = fruQualificationRate;
    }

    public BigDecimal getFruQualificationRate() 
    {
        return fruQualificationRate;
    }
    public void setTotalSamplingCount(Long totalSamplingCount) 
    {
        this.totalSamplingCount = totalSamplingCount;
    }

    public Long getTotalSamplingCount() 
    {
        return totalSamplingCount;
    }
    public void setTotalQualifiedCount(Long totalQualifiedCount) 
    {
        this.totalQualifiedCount = totalQualifiedCount;
    }

    public Long getTotalQualifiedCount() 
    {
        return totalQualifiedCount;
    }
    public void setTotalQualificationRate(BigDecimal totalQualificationRate) 
    {
        this.totalQualificationRate = totalQualificationRate;
    }

    public BigDecimal getTotalQualificationRate() 
    {
        return totalQualificationRate;
    }
    public void setCreatedDate(Date createdDate) 
    {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() 
    {
        return createdDate;
    }

    public String getStageIncludeType() {
        return stageIncludeType;
    }

    public void setStageIncludeType(String stageIncludeType) {
        this.stageIncludeType = stageIncludeType;
    }

    //自加接口
    public void vegSamplingCountAddOne(){
        this.vegSamplingCount+=1;
    }
    public void vegQualifiedCountAddOne(){
        this.vegQualifiedCount+=1;
    }
    public void fruSamplingCountAddOne(){
        this.fruSamplingCount+=1;
    }
    public void fruQualifiedCountAddOne(){
        this.fruQualifiedCount+=1;
    }
    public void totalSamplingCountAddOne(){
        this.totalSamplingCount+=1;
    }
    public void totalQualifiedCountAddOne(){
        this.totalQualifiedCount+=1;
    }
    public void addToTotal(outSampleQuality otherOne){
        this.vegSamplingCount+=otherOne.vegSamplingCount;
        this.fruSamplingCount+=otherOne.fruSamplingCount;
        this.vegQualifiedCount+=otherOne.vegQualifiedCount;
        this.fruQualifiedCount+=otherOne.fruQualifiedCount;
        this.totalSamplingCount+=otherOne.totalSamplingCount;
        this.totalQualifiedCount+=otherOne.totalQualifiedCount;
    }
    public void addToSelfTotal(){
        this.totalSamplingCount=this.fruSamplingCount+this.vegSamplingCount;
        this.totalQualifiedCount=this.fruQualifiedCount+this.vegQualifiedCount;
    }
    public void computeQualificationRate(){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if(this.vegSamplingCount >0){
            Double percentage=(double)((double)this.vegQualifiedCount/(double)this.vegSamplingCount)*100;
            this.vegQualificationRate= new BigDecimal(decimalFormat.format(percentage).toString());
        }
        if(this.fruSamplingCount !=0) {
            Double percentage=(double)((double)this.fruQualifiedCount/(double)this.fruSamplingCount)*100;
            this.fruQualificationRate = new BigDecimal(decimalFormat.format(percentage).toString());
        }
        if(this.totalSamplingCount !=0) {
            Double percentage=(double)((double)this.totalQualifiedCount/(double)this.totalSamplingCount)*100;
            this.totalQualificationRate= new BigDecimal(decimalFormat.format(percentage).toString());
        }

    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sampleQualityId", getSampleQualityId())
            .append("samplingStageType", getSamplingStageType())
            .append("pollutionFreeBase", getPollutionFreeBase())
            .append("landmarkProductBase", getLandmarkProductBase())
            .append("greenProductBase", getGreenProductBase())
            .append("organicProductBase", getOrganicProductBase())
            .append("individualHousehold", getIndividualHousehold())
            .append("otherBase", getOtherBase())
            .append("wholesaleMarket", getWholesaleMarket())
            .append("transportVehicle", getTransportVehicle())
            .append("allCount", getAllCount())
            .append("vegSamplingCount", getVegSamplingCount())
            .append("vegQualifiedCount", getVegQualifiedCount())
            .append("vegQualificationRate", getVegQualificationRate())
            .append("fruSamplingCount", getFruSamplingCount())
            .append("fruQualifiedCount", getFruQualifiedCount())
            .append("fruQualificationRate", getFruQualificationRate())
            .append("totalSamplingCount", getTotalSamplingCount())
            .append("totalQualifiedCount", getTotalQualifiedCount())
            .append("totalQualificationRate", getTotalQualificationRate())
            .append("createdDate", getCreatedDate())
            .append("stageIncludeType", getStageIncludeType())
            .toString();
    }
}
