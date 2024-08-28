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
 * 各市样品检测结果详细对象 agri_city_sample_test_details  和数据库字段对应
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

    /** 抽样环节 */
    @Excel(name = "抽样环节")
    private String samplingStageType;

    @Excel(name = "抽样省")
    private String samplingLocationProvince;

    @Excel(name = "抽样市")
    private String samplingLocationCity;

    @Excel(name = "抽样县")
    private String samplingLocationCounty;

    /** 抽样地点 */
    @Excel(name = "抽样地址")
    private String samplingLocation;

    /** 检测单位 */
    @Excel(name = "检测单位")
    private String detectLocation;

    /** 抽样日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "抽样日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date samplingDate;

    @Excel(name = "企业名称/农户")
    private String enterpriseName;
    @Excel(name = "企业属性（绿色/有机/地理标志/GAP)")
    private String enterpriseAttribute;
    @Excel(name = "企业信用代码/身份证号")
    private String enterpriseCreditIdCode;
    @Excel(name = "溯源省")
    private String tracingProvince;
    @Excel(name = "溯源市")
    private String tracingCity;
    @Excel(name = "溯源县")
    private String tracingCounty;
    @Excel(name = "溯源产地")
    private String tracingArea;

    //新表中取消了
    /** 抽样数量（kg/个数） */
    @Excel(name = "抽样数量")
    private Double samplingQuantity;
    //新表中取消了
    /** 抽样基数 */
    @Excel(name = "抽样基数")
    private String samplingBase;


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




   //新增的字段，放在搜索框那里的
    /** 蔬果类型（蔬菜或水果） */
    private String vegFruType;


    // 上传数据的用户名
    private String userName;

    // 记录上传的批次编号
    private String batchId;

    //7月28日新加的需求，要求加入全区编号
    private String allDistrictCode;

    //7月31日新加的需求，要求加入农药名查询
    private String pesticideName;
    //8月15日新加的需求，要求将检测分为三个类型
    private String samplingType;

    public String getSamplingType() {
        return samplingType;
    }

    public void setSamplingType(String samplingType) {
        this.samplingType = samplingType;
    }






    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getAllDistrictCode() {
        return allDistrictCode;
    }

    public void setAllDistrictCode(String allDistrictCode) {
        this.allDistrictCode = allDistrictCode;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getVegFruType() {
        return vegFruType;
    }

    public void setVegFruType(String vegFruType) {
        this.vegFruType = vegFruType;
    }

    public Long getCitySampleTestDetailsId() {
        return citySampleTestDetailsId;
    }

    public void setCitySampleTestDetailsId(Long citySampleTestDetailsId) {
        this.citySampleTestDetailsId = citySampleTestDetailsId;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getVegFruName() {
        return vegFruName;
    }

    public void setVegFruName(String vegFruName) {
        this.vegFruName = vegFruName;
    }

    public String getSamplingStageType() {
        return samplingStageType;
    }

    public void setSamplingStageType(String samplingStageType) {
        this.samplingStageType = samplingStageType;
    }

    public String getSamplingLocationProvince() {
        return samplingLocationProvince;
    }

    public void setSamplingLocationProvince(String samplingLocationProvince) {
        this.samplingLocationProvince = samplingLocationProvince;
    }

    public String getSamplingLocationCity() {
        return samplingLocationCity;
    }

    public void setSamplingLocationCity(String samplingLocationCity) {
        this.samplingLocationCity = samplingLocationCity;
    }

    public String getSamplingLocationCounty() {
        return samplingLocationCounty;
    }

    public void setSamplingLocationCounty(String samplingLocationCounty) {
        this.samplingLocationCounty = samplingLocationCounty;
    }

    public String getSamplingLocation() {
        return samplingLocation;
    }

    public void setSamplingLocation(String samplingLocation) {
        this.samplingLocation = samplingLocation;
    }

    public String getDetectLocation() {
        return detectLocation;
    }

    public void setDetectLocation(String detectLocation) {
        this.detectLocation = detectLocation;
    }

    public Date getSamplingDate() {
        return samplingDate;
    }

    public void setSamplingDate(Date samplingDate) {
        this.samplingDate = samplingDate;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseAttribute() {
        return enterpriseAttribute;
    }

    public void setEnterpriseAttribute(String enterpriseAttribute) {
        this.enterpriseAttribute = enterpriseAttribute;
    }

    public String getEnterpriseCreditIdCode() {
        return enterpriseCreditIdCode;
    }

    public void setEnterpriseCreditIdCode(String enterpriseCreditIdCode) {
        this.enterpriseCreditIdCode = enterpriseCreditIdCode;
    }

    public String getTracingProvince() {
        return tracingProvince;
    }

    public void setTracingProvince(String tracingProvince) {
        this.tracingProvince = tracingProvince;
    }

    public String getTracingCity() {
        return tracingCity;
    }

    public void setTracingCity(String tracingCity) {
        this.tracingCity = tracingCity;
    }

    public String getTracingCounty() {
        return tracingCounty;
    }

    public void setTracingCounty(String tracingCounty) {
        this.tracingCounty = tracingCounty;
    }

    public String getTracingArea() {
        return tracingArea;
    }

    public void setTracingArea(String tracingArea) {
        this.tracingArea = tracingArea;
    }

    public Double getSamplingQuantity() {
        return samplingQuantity;
    }

    public void setSamplingQuantity(Double samplingQuantity) {
        this.samplingQuantity = samplingQuantity;
    }

    public String getSamplingBase() {
        return samplingBase;
    }

    public void setSamplingBase(String samplingBase) {
        this.samplingBase = samplingBase;
    }

    public String getChinaStandard() {
        return chinaStandard;
    }

    public void setChinaStandard(String chinaStandard) {
        this.chinaStandard = chinaStandard;
    }

    public String getCacStandard() {
        return cacStandard;
    }

    public void setCacStandard(String cacStandard) {
        this.cacStandard = cacStandard;
    }

    public String getJapanStandard() {
        return japanStandard;
    }

    public void setJapanStandard(String japanStandard) {
        this.japanStandard = japanStandard;
    }

    public String getEuStandard() {
        return euStandard;
    }

    public void setEuStandard(String euStandard) {
        this.euStandard = euStandard;
    }

    public String getUsStandard() {
        return usStandard;
    }

    public void setUsStandard(String usStandard) {
        this.usStandard = usStandard;
    }

    public String getKoreaStandard() {
        return koreaStandard;
    }

    public void setKoreaStandard(String koreaStandard) {
        this.koreaStandard = koreaStandard;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<agriPesticideDetResult> getAgriPesticideDetResultList() {
        return agriPesticideDetResultList;
    }

    public void setAgriPesticideDetResultList(List<agriPesticideDetResult> agriPesticideDetResultList) {
        this.agriPesticideDetResultList = agriPesticideDetResultList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean IsPassUnderTheStandard(String standardName){
        switch (standardName){
            case "国家标准":
                if (this.chinaStandard!=null && this.chinaStandard.equals("合格")) return true;
                else return false;
            case "CAC":
                if (this.cacStandard!=null &&this.cacStandard.equals("合格")) return true;
                else return false;
            case "欧盟":
                if (this.euStandard!=null &&this.euStandard.equals("合格")) return true;
                else return false;
            case "美国":
                if (this.usStandard!=null &&this.usStandard.equals("合格")) return true;
                else return false;
            case "韩国":
                if (this.koreaStandard!=null &&this.koreaStandard.equals("合格")) return true;
                else return false;
            case "日本":
                if (this.japanStandard!=null &&this.japanStandard.equals("合格")) return true;
                else return false;
        }
        return true;//合格
    }

    @Override
    public String toString() {
        return "agriCitySampleTestDetails{" +
                "citySampleTestDetailsId=" + citySampleTestDetailsId +
                ", sampleCode='" + sampleCode + '\'' +
                ", vegFruName='" + vegFruName + '\'' +
                ", samplingStageType='" + samplingStageType + '\'' +
                ", samplingLocationProvince='" + samplingLocationProvince + '\'' +
                ", samplingLocationCity='" + samplingLocationCity + '\'' +
                ", samplingLocationCounty='" + samplingLocationCounty + '\'' +
                ", samplingLocation='" + samplingLocation + '\'' +
                ", detectLocation='" + detectLocation + '\'' +
                ", samplingDate=" + samplingDate +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseAttribute='" + enterpriseAttribute + '\'' +
                ", enterpriseCreditIdCode='" + enterpriseCreditIdCode + '\'' +
                ", tracingProvince='" + tracingProvince + '\'' +
                ", tracingCity='" + tracingCity + '\'' +
                ", tracingCounty='" + tracingCounty + '\'' +
                ", tracingArea='" + tracingArea + '\'' +
                ", samplingQuantity=" + samplingQuantity +
                ", samplingBase='" + samplingBase + '\'' +
                ", chinaStandard='" + chinaStandard + '\'' +
                ", cacStandard='" + cacStandard + '\'' +
                ", japanStandard='" + japanStandard + '\'' +
                ", euStandard='" + euStandard + '\'' +
                ", usStandard='" + usStandard + '\'' +
                ", koreaStandard='" + koreaStandard + '\'' +
                ", createdAt=" + createdAt +
                ", agriPesticideDetResultList=" + agriPesticideDetResultList +
                ", vegFruType='" + vegFruType + '\'' +
                ", userName='" + userName + '\'' +
                ", batchId='" + batchId + '\'' +
                ", allDistrictCode='" + allDistrictCode + '\'' +
                ", pesticideName='" + pesticideName + '\'' +
                ", samplingType='" + samplingType + '\'' +
                '}';
    }
}
