package com.ruoyi.detection.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 例行检测详细对象 agri_city_sample_test_details
 * 
 * @author chenjie
 * @date 2024-08-27
 */
public class agriCitySampleTestDetailsLXJC extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键，自增长 */
    private Long citySampleTestDetailsId;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleCode;

    /** 全区编号 */
    @Excel(name = "全区编号")
    private String allDistrictCode;

    /** 检测类型（例行检测、专项检测、监督抽查） */
    @Excel(name = "检测类型", readConverterExp = "例=行检测、专项检测、监督抽查")
    private String samplingType;

    /** 逻辑外键，样品名称，关联蔬果表（vegetable_fruit_id） */
    @Excel(name = "逻辑外键，样品名称，关联蔬果表", readConverterExp = "v=egetable_fruit_id")
    private String vegFruName;

    /** 抽样地点（被检单位） */
    @Excel(name = "抽样地点", readConverterExp = "被=检单位")
    private String samplingLocation;

    /** 抽样地点-省份 */
    @Excel(name = "抽样地点-省份")
    private String samplingLocationProvince;

    /** 抽样地点-市 */
    @Excel(name = "抽样地点-市")
    private String samplingLocationCity;

    /** 抽样地点-县 */
    @Excel(name = "抽样地点-县")
    private String samplingLocationCounty;

    /** 检测单位（承检单位） */
    @Excel(name = "检测单位", readConverterExp = "承=检单位")
    private String detectLocation;

    /** 抽样数量（kg/个数） */
    @Excel(name = "抽样数量", readConverterExp = "k=g/个数")
    private BigDecimal samplingQuantity;

    /** 抽样基数 */
    @Excel(name = "抽样基数")
    private String samplingBase;

    /** 抽样环节（如无公害产品基地、地标产品基地、绿色产品基地、有机产品基地、散户、其他基地、批发市场、运输车等） */
    @Excel(name = "抽样环节", readConverterExp = "如=无公害产品基地、地标产品基地、绿色产品基地、有机产品基地、散户、其他基地、批发市场、运输车等")
    private String samplingStageType;

    /** 抽样日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "抽样日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date samplingDate;

    /** 企业名称/农户 */
    @Excel(name = "企业名称/农户")
    private String enterpriseName;

    /** 企业属性（绿色/有机/地理标志/GAP) */
    @Excel(name = "企业属性", readConverterExp = "企业属性（绿色/有机/地理标志/GAP)")
    private String enterpriseAttribute;

    /** 企业信用代码/身份证号 */
    @Excel(name = "企业信用代码/身份证号")
    private String enterpriseCreditIdCode;

    /** 溯源省 */
    @Excel(name = "溯源省")
    private String tracingProvince;

    /** 溯源市 */
    @Excel(name = "溯源市")
    private String tracingCity;

    /** 溯源县 */
    @Excel(name = "溯源县")
    private String tracingCounty;

    /** 溯源场地 */
    @Excel(name = "溯源场地")
    private String tracingArea;

    /** 国家标准（判定标准，合格or不合格） */
    @Excel(name = "国家标准", readConverterExp = "判=定标准，合格or不合格")
    private String chinaStandard;

    /** CAC标准 */
    @Excel(name = "CAC标准")
    private String cacStandard;

    /** 日本标准 */
    @Excel(name = "日本标准")
    private String japanStandard;

    /** 欧盟标准 */
    @Excel(name = "欧盟标准")
    private String euStandard;

    /** 美国标准 */
    @Excel(name = "美国标准")
    private String usStandard;

    /** 韩国标准 */
    @Excel(name = "韩国标准")
    private String koreaStandard;

    /** 上传数据用户名 */
    @Excel(name = "上传数据用户名")
    private String userName;

    /** 记录上传的批次编号 */
    @Excel(name = "记录上传的批次编号")
    private String batchId;

    /** 记录创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 农药检测结果信息 */
    private List<agriPesticideDetResult> agriPesticideDetResultList;

    public void setCitySampleTestDetailsId(Long citySampleTestDetailsId) 
    {
        this.citySampleTestDetailsId = citySampleTestDetailsId;
    }

    public Long getCitySampleTestDetailsId() 
    {
        return citySampleTestDetailsId;
    }
    public void setSampleCode(String sampleCode) 
    {
        this.sampleCode = sampleCode;
    }

    public String getSampleCode() 
    {
        return sampleCode;
    }
    public void setAllDistrictCode(String allDistrictCode) 
    {
        this.allDistrictCode = allDistrictCode;
    }

    public String getAllDistrictCode() 
    {
        return allDistrictCode;
    }
    public void setSamplingType(String samplingType) 
    {
        this.samplingType = samplingType;
    }

    public String getSamplingType() 
    {
        return samplingType;
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
    public void setSamplingLocationProvince(String samplingLocationProvince) 
    {
        this.samplingLocationProvince = samplingLocationProvince;
    }

    public String getSamplingLocationProvince() 
    {
        return samplingLocationProvince;
    }
    public void setSamplingLocationCity(String samplingLocationCity) 
    {
        this.samplingLocationCity = samplingLocationCity;
    }

    public String getSamplingLocationCity() 
    {
        return samplingLocationCity;
    }
    public void setSamplingLocationCounty(String samplingLocationCounty) 
    {
        this.samplingLocationCounty = samplingLocationCounty;
    }

    public String getSamplingLocationCounty() 
    {
        return samplingLocationCounty;
    }
    public void setDetectLocation(String detectLocation) 
    {
        this.detectLocation = detectLocation;
    }

    public String getDetectLocation() 
    {
        return detectLocation;
    }
    public void setSamplingQuantity(BigDecimal samplingQuantity) 
    {
        this.samplingQuantity = samplingQuantity;
    }

    public BigDecimal getSamplingQuantity() 
    {
        return samplingQuantity;
    }
    public void setSamplingBase(String samplingBase) 
    {
        this.samplingBase = samplingBase;
    }

    public String getSamplingBase() 
    {
        return samplingBase;
    }
    public void setSamplingStageType(String samplingStageType) 
    {
        this.samplingStageType = samplingStageType;
    }

    public String getSamplingStageType() 
    {
        return samplingStageType;
    }
    public void setSamplingDate(Date samplingDate) 
    {
        this.samplingDate = samplingDate;
    }

    public Date getSamplingDate() 
    {
        return samplingDate;
    }
    public void setEnterpriseName(String enterpriseName) 
    {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() 
    {
        return enterpriseName;
    }
    public void setEnterpriseAttribute(String enterpriseAttribute) 
    {
        this.enterpriseAttribute = enterpriseAttribute;
    }

    public String getEnterpriseAttribute() 
    {
        return enterpriseAttribute;
    }
    public void setEnterpriseCreditIdCode(String enterpriseCreditIdCode) 
    {
        this.enterpriseCreditIdCode = enterpriseCreditIdCode;
    }

    public String getEnterpriseCreditIdCode() 
    {
        return enterpriseCreditIdCode;
    }
    public void setTracingProvince(String tracingProvince) 
    {
        this.tracingProvince = tracingProvince;
    }

    public String getTracingProvince() 
    {
        return tracingProvince;
    }
    public void setTracingCity(String tracingCity) 
    {
        this.tracingCity = tracingCity;
    }

    public String getTracingCity() 
    {
        return tracingCity;
    }
    public void setTracingCounty(String tracingCounty) 
    {
        this.tracingCounty = tracingCounty;
    }

    public String getTracingCounty() 
    {
        return tracingCounty;
    }
    public void setTracingArea(String tracingArea) 
    {
        this.tracingArea = tracingArea;
    }

    public String getTracingArea() 
    {
        return tracingArea;
    }
    public void setChinaStandard(String chinaStandard) 
    {
        this.chinaStandard = chinaStandard;
    }

    public String getChinaStandard() 
    {
        return chinaStandard;
    }
    public void setCacStandard(String cacStandard) 
    {
        this.cacStandard = cacStandard;
    }

    public String getCacStandard() 
    {
        return cacStandard;
    }
    public void setJapanStandard(String japanStandard) 
    {
        this.japanStandard = japanStandard;
    }

    public String getJapanStandard() 
    {
        return japanStandard;
    }
    public void setEuStandard(String euStandard) 
    {
        this.euStandard = euStandard;
    }

    public String getEuStandard() 
    {
        return euStandard;
    }
    public void setUsStandard(String usStandard) 
    {
        this.usStandard = usStandard;
    }

    public String getUsStandard() 
    {
        return usStandard;
    }
    public void setKoreaStandard(String koreaStandard) 
    {
        this.koreaStandard = koreaStandard;
    }

    public String getKoreaStandard() 
    {
        return koreaStandard;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setBatchId(String batchId) 
    {
        this.batchId = batchId;
    }

    public String getBatchId() 
    {
        return batchId;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public List<agriPesticideDetResult> getagriPesticideDetResultList()
    {
        return agriPesticideDetResultList;
    }

    public void setagriPesticideDetResultList(List<agriPesticideDetResult> agriPesticideDetResultList)
    {
        this.agriPesticideDetResultList = agriPesticideDetResultList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("citySampleTestDetailsId", getCitySampleTestDetailsId())
            .append("sampleCode", getSampleCode())
            .append("allDistrictCode", getAllDistrictCode())
            .append("samplingType", getSamplingType())
            .append("vegFruName", getVegFruName())
            .append("samplingLocation", getSamplingLocation())
            .append("samplingLocationProvince", getSamplingLocationProvince())
            .append("samplingLocationCity", getSamplingLocationCity())
            .append("samplingLocationCounty", getSamplingLocationCounty())
            .append("detectLocation", getDetectLocation())
            .append("samplingQuantity", getSamplingQuantity())
            .append("samplingBase", getSamplingBase())
            .append("samplingStageType", getSamplingStageType())
            .append("samplingDate", getSamplingDate())
            .append("enterpriseName", getEnterpriseName())
            .append("enterpriseAttribute", getEnterpriseAttribute())
            .append("enterpriseCreditIdCode", getEnterpriseCreditIdCode())
            .append("tracingProvince", getTracingProvince())
            .append("tracingCity", getTracingCity())
            .append("tracingCounty", getTracingCounty())
            .append("tracingArea", getTracingArea())
            .append("chinaStandard", getChinaStandard())
            .append("cacStandard", getCacStandard())
            .append("japanStandard", getJapanStandard())
            .append("euStandard", getEuStandard())
            .append("usStandard", getUsStandard())
            .append("koreaStandard", getKoreaStandard())
            .append("userName", getUserName())
            .append("batchId", getBatchId())
            .append("createdAt", getCreatedAt())
            .append("agriPesticideDetResultList", getagriPesticideDetResultList())
            .toString();
    }
}
