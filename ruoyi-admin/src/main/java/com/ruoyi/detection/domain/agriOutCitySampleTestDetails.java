package com.ruoyi.detection.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import java.util.Date;

/*
*
* excel导入的实体,动态农药name和value
* */
public class agriOutCitySampleTestDetails {

    // 主表的主键
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
    /** 农药名 */
    @Excel(name = "农药名")
    private String pesticideName;

    /** 检测值（mg/kg） */
    @Excel(name = "检测值")
    private Double pesticideDetValue;

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

    public String getSamplingStageType() {
        return samplingStageType;
    }

    public void setSamplingStageType(String samplingStageType) {
        this.samplingStageType = samplingStageType;
    }

    public Date getSamplingDate() {
        return samplingDate;
    }

    public void setSamplingDate(Date samplingDate) {
        this.samplingDate = samplingDate;
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

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public Double getPesticideDetValue() {
        return pesticideDetValue;
    }

    public void setPesticideDetValue(Double pesticideDetValue) {
        this.pesticideDetValue = pesticideDetValue;
    }

    @Override
    public String toString() {
        return "agriOutCitySampleTestDetails{" +
                "citySampleTestDetailsId=" + citySampleTestDetailsId +
                ", sampleCode='" + sampleCode + '\'' +
                ", vegFruName='" + vegFruName + '\'' +
                ", samplingLocation='" + samplingLocation + '\'' +
                ", detectLocation='" + detectLocation + '\'' +
                ", samplingQuantity=" + samplingQuantity +
                ", samplingBase=" + samplingBase +
                ", samplingStageType='" + samplingStageType + '\'' +
                ", samplingDate=" + samplingDate +
                ", chinaStandard='" + chinaStandard + '\'' +
                ", cacStandard='" + cacStandard + '\'' +
                ", japanStandard='" + japanStandard + '\'' +
                ", euStandard='" + euStandard + '\'' +
                ", usStandard='" + usStandard + '\'' +
                ", koreaStandard='" + koreaStandard + '\'' +
                ", pesticideName='" + pesticideName + '\'' +
                ", pesticideDetValue=" + pesticideDetValue +
                '}';
    }
}
