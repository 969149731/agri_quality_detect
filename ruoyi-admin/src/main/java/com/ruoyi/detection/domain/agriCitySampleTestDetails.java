package com.ruoyi.detection.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.context.annotation.Bean;

/**
 * 各市样品检测结果详细对象 agri_city_sample_test_details
 * 
 * @author chenjie
 * @date 2024-01-24
 */

public class agriCitySampleTestDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long citySampleTestDetailsId;

    /** 样品编号 */
    @Excel(name = "样品编号")
    private String sampleCode;

    /** 样品名称 */
    @Excel(name = "样品名称")
    private String vegFruName;

    /** 抽样地点 */
    @Excel(name = "抽样地点")
    private String samplingLocation;

    /** 检测单位 */
    @Excel(name = "检测单位")
    private String detectLocation;

    /** 抽样数量（kg/个数） */
    @Excel(name = "抽样数量")
    private Double samplingQuantity;

    /** 抽样基数 */
    @Excel(name = "抽样基数")
    private String samplingBase;

    /** 抽样环节 */
    @Excel(name = "抽样环节")
    private String samplingStageType;

    /** 抽样日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "抽样日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date samplingDate;

    /** 国家标准 */
    @Excel(name = "国家标准")
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

    /** 记录创建时间 */
    private Date createdAt;

    /** 农药检测结果信息 */
    @Excel(name = "农药检测结果信息")
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
    public void setDetectLocation(String detectLocation) 
    {
        this.detectLocation = detectLocation;
    }

    public String getDetectLocation() 
    {
        return detectLocation;
    }
    public void setSamplingQuantity(Double samplingQuantity) 
    {
        this.samplingQuantity = samplingQuantity;
    }

    public Double getSamplingQuantity() 
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
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    public List<agriPesticideDetResult> getAgriPesticideDetResultList()
    {
        return agriPesticideDetResultList;
    }

    public void setAgriPesticideDetResultList(List<agriPesticideDetResult> agriPesticideDetResultList)
    {
        this.agriPesticideDetResultList = agriPesticideDetResultList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("citySampleTestDetailsId", getCitySampleTestDetailsId())
            .append("sampleCode", getSampleCode())
            .append("vegFruName", getVegFruName())
            .append("samplingLocation", getSamplingLocation())
            .append("detectLocation", getDetectLocation())
            .append("samplingQuantity", getSamplingQuantity())
            .append("samplingBase", getSamplingBase())
            .append("samplingStageType", getSamplingStageType())
            .append("samplingDate", getSamplingDate())
            .append("chinaStandard", getChinaStandard())
            .append("cacStandard", getCacStandard())
            .append("japanStandard", getJapanStandard())
            .append("euStandard", getEuStandard())
            .append("usStandard", getUsStandard())
            .append("koreaStandard", getKoreaStandard())
            .append("createdAt", getCreatedAt())
            .append("agriPesticideDetResultList", getAgriPesticideDetResultList())
            .toString();
    }
}
