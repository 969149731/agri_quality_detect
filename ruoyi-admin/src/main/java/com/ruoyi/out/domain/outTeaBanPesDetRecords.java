package com.ruoyi.out.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 茶叶禁用农药检出及超标情况对象 out_tea_ban_pes_det_records
 * 
 * @author chenjie
 * @date 2024-04-08
 */
public class outTeaBanPesDetRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识每条检测记录 */
    private Long teaBanPesDetRecordsId;

    /** 农药名称 */
    @Excel(name = "农药名称")
    private String pesticideName;

    /** 甲胺磷 */
    @Excel(name = "甲胺磷")
    private String methamidophos;

    /** 乙酰甲胺磷 */
    @Excel(name = "乙酰甲胺磷")
    private String acephate;

    /** 甲拌磷 */
    @Excel(name = "甲拌磷")
    private String phorate;

    /** 氧乐果 */
    @Excel(name = "氧乐果")
    private String dimethoate;

    /** 毒死蜱 */
    @Excel(name = "毒死蜱")
    private String chlorpyrifos;

    /** 特丁硫磷 */
    @Excel(name = "特丁硫磷")
    private String fenthion;

    /** 三唑磷 */
    @Excel(name = "三唑磷")
    private String triazophos;

    /** 水胺硫磷 */
    @Excel(name = "水胺硫磷")
    private String isocarbophos;

    /** 治螟磷 */
    @Excel(name = "治螟磷")
    private String trichlorfon;

    /** 乐果 */
    @Excel(name = "乐果")
    private String dichlorvos;

    /** 甲基异柳磷 */
    @Excel(name = "甲基异柳磷")
    private String methylParathion;

    /** 氟虫腈 */
    @Excel(name = "氟虫腈")
    private String flucythrinate;

    /** 克百威 */
    @Excel(name = "克百威")
    private String carbaryl;

    /** 检出次数 */
    @Excel(name = "检出次数")
    private Long deteNum;

    /** 超标次数 */
    @Excel(name = "超标次数")
    private Long exDetNum;

    /** 记录生产基地的检查次数 */
    @Excel(name = "记录生产基地的检查次数")
    private Long productionInspectCount;

    /** 记录生产基地的超标次数 */
    @Excel(name = "记录生产基地的超标次数")
    private Long productionExceedCount;

    /** 记录批发市场的检查次数 */
    @Excel(name = "记录批发市场的检查次数")
    private Long wholesaleInspectCount;

    /** 记录批发市场的超标次数 */
    @Excel(name = "记录批发市场的超标次数")
    private Long wholesaleExceedCount;

    /** 记录运输车的检查次数 */
    @Excel(name = "记录运输车的检查次数")
    private Long vehicleInspectCount;

    /** 记录运输车的超标次数 */
    @Excel(name = "记录运输车的超标次数")
    private Long vehicleExceedCount;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public void setTeaBanPesDetRecordsId(Long teaBanPesDetRecordsId) 
    {
        this.teaBanPesDetRecordsId = teaBanPesDetRecordsId;
    }

    public Long getTeaBanPesDetRecordsId() 
    {
        return teaBanPesDetRecordsId;
    }
    public void setPesticideName(String pesticideName) 
    {
        this.pesticideName = pesticideName;
    }

    public String getPesticideName() 
    {
        return pesticideName;
    }
    public void setMethamidophos(String methamidophos) 
    {
        this.methamidophos = methamidophos;
    }

    public String getMethamidophos() 
    {
        return methamidophos;
    }
    public void setAcephate(String acephate) 
    {
        this.acephate = acephate;
    }

    public String getAcephate() 
    {
        return acephate;
    }
    public void setPhorate(String phorate) 
    {
        this.phorate = phorate;
    }

    public String getPhorate() 
    {
        return phorate;
    }
    public void setDimethoate(String dimethoate) 
    {
        this.dimethoate = dimethoate;
    }

    public String getDimethoate() 
    {
        return dimethoate;
    }
    public void setChlorpyrifos(String chlorpyrifos) 
    {
        this.chlorpyrifos = chlorpyrifos;
    }

    public String getChlorpyrifos() 
    {
        return chlorpyrifos;
    }
    public void setFenthion(String fenthion) 
    {
        this.fenthion = fenthion;
    }

    public String getFenthion() 
    {
        return fenthion;
    }
    public void setTriazophos(String triazophos) 
    {
        this.triazophos = triazophos;
    }

    public String getTriazophos() 
    {
        return triazophos;
    }
    public void setIsocarbophos(String isocarbophos) 
    {
        this.isocarbophos = isocarbophos;
    }

    public String getIsocarbophos() 
    {
        return isocarbophos;
    }
    public void setTrichlorfon(String trichlorfon) 
    {
        this.trichlorfon = trichlorfon;
    }

    public String getTrichlorfon() 
    {
        return trichlorfon;
    }
    public void setDichlorvos(String dichlorvos) 
    {
        this.dichlorvos = dichlorvos;
    }

    public String getDichlorvos() 
    {
        return dichlorvos;
    }
    public void setMethylParathion(String methylParathion) 
    {
        this.methylParathion = methylParathion;
    }

    public String getMethylParathion() 
    {
        return methylParathion;
    }
    public void setFlucythrinate(String flucythrinate) 
    {
        this.flucythrinate = flucythrinate;
    }

    public String getFlucythrinate() 
    {
        return flucythrinate;
    }
    public void setCarbaryl(String carbaryl) 
    {
        this.carbaryl = carbaryl;
    }

    public String getCarbaryl() 
    {
        return carbaryl;
    }
    public void setDeteNum(Long deteNum) 
    {
        this.deteNum = deteNum;
    }

    public Long getDeteNum() 
    {
        return deteNum;
    }
    public void setExDetNum(Long exDetNum) 
    {
        this.exDetNum = exDetNum;
    }

    public Long getExDetNum() 
    {
        return exDetNum;
    }
    public void setProductionInspectCount(Long productionInspectCount) 
    {
        this.productionInspectCount = productionInspectCount;
    }

    public Long getProductionInspectCount() 
    {
        return productionInspectCount;
    }
    public void setProductionExceedCount(Long productionExceedCount) 
    {
        this.productionExceedCount = productionExceedCount;
    }

    public Long getProductionExceedCount() 
    {
        return productionExceedCount;
    }
    public void setWholesaleInspectCount(Long wholesaleInspectCount) 
    {
        this.wholesaleInspectCount = wholesaleInspectCount;
    }

    public Long getWholesaleInspectCount() 
    {
        return wholesaleInspectCount;
    }
    public void setWholesaleExceedCount(Long wholesaleExceedCount) 
    {
        this.wholesaleExceedCount = wholesaleExceedCount;
    }

    public Long getWholesaleExceedCount() 
    {
        return wholesaleExceedCount;
    }
    public void setVehicleInspectCount(Long vehicleInspectCount) 
    {
        this.vehicleInspectCount = vehicleInspectCount;
    }

    public Long getVehicleInspectCount() 
    {
        return vehicleInspectCount;
    }
    public void setVehicleExceedCount(Long vehicleExceedCount) 
    {
        this.vehicleExceedCount = vehicleExceedCount;
    }

    public Long getVehicleExceedCount() 
    {
        return vehicleExceedCount;
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
            .append("teaBanPesDetRecordsId", getTeaBanPesDetRecordsId())
            .append("pesticideName", getPesticideName())
            .append("methamidophos", getMethamidophos())
            .append("acephate", getAcephate())
            .append("phorate", getPhorate())
            .append("dimethoate", getDimethoate())
            .append("chlorpyrifos", getChlorpyrifos())
            .append("fenthion", getFenthion())
            .append("triazophos", getTriazophos())
            .append("isocarbophos", getIsocarbophos())
            .append("trichlorfon", getTrichlorfon())
            .append("dichlorvos", getDichlorvos())
            .append("methylParathion", getMethylParathion())
            .append("flucythrinate", getFlucythrinate())
            .append("carbaryl", getCarbaryl())
            .append("deteNum", getDeteNum())
            .append("exDetNum", getExDetNum())
            .append("productionInspectCount", getProductionInspectCount())
            .append("productionExceedCount", getProductionExceedCount())
            .append("wholesaleInspectCount", getWholesaleInspectCount())
            .append("wholesaleExceedCount", getWholesaleExceedCount())
            .append("vehicleInspectCount", getVehicleInspectCount())
            .append("vehicleExceedCount", getVehicleExceedCount())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
