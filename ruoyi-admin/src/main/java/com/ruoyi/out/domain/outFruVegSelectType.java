package com.ruoyi.out.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class outFruVegSelectType {
    public Long citySampleTestDetailsId;
    public String sampleCode;
    public String vegFruName;
    public String samplingLocation;
    public String detectLocation;
    public String samplingStageType;
    public Date samplingDate;
    public Long pesticideDetResultId;
    public String pesticideName;
    public String standardCategory;
    public Double pesticideDetValue;
    public Long vegFruId;
    public String vegFruType;
    public String detailType;

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

    public Long getPesticideDetResultId() {
        return pesticideDetResultId;
    }

    public void setPesticideDetResultId(Long pesticideDetResultId) {
        this.pesticideDetResultId = pesticideDetResultId;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getStandardCategory() {
        return standardCategory;
    }

    public void setStandardCategory(String standardCategory) {
        this.standardCategory = standardCategory;
    }

    public Double getPesticideDetValue() {
        return pesticideDetValue;
    }

    public void setPesticideDetValue(Double pesticideDetValue) {
        this.pesticideDetValue = pesticideDetValue;
    }

    public Long getVegFruId() {
        return vegFruId;
    }

    public void setVegFruId(Long vegFruId) {
        this.vegFruId = vegFruId;
    }

    public String getVegFruType() {
        return vegFruType;
    }

    public void setVegFruType(String vegFruType) {
        this.vegFruType = vegFruType;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }



    public boolean checkIsUseful(){
        if (this.vegFruName==null){//情况一，蔬菜名缺失，完全无法判断
            System.out.println("蔬菜名缺失："+"/r/n蔬果名:"+this.vegFruName+"样品编号"+this.sampleCode);
            return false;
        }
        if(this.samplingStageType==null){
            System.out.println("生产环节属性值缺失："+"/r/生产环节:"+this.samplingStageType+"样品编号"+this.sampleCode);
            return false;
        }
        if(this.pesticideName==null && this.pesticideDetValue==null){
            System.out.println("该条目下无检出农药"+"/r/n蔬果名:"+vegFruName+"样品编号"+this.sampleCode);
            return false;
        }
        if (this.pesticideName==null){
            System.out.println("农药名缺失："+"/r/农药名:"+this.pesticideName+"样品编号"+this.sampleCode);
            return false;
        }
        if(this.pesticideName.equals("") && this.pesticideDetValue!=null){
            System.out.println("该条目农药名缺失"+"/r/农药名:"+pesticideName+"样品编号"+this.sampleCode);
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("citySampleTestDetailsId", this.citySampleTestDetailsId)
                .append("sampleCode", this.sampleCode)
                .append("vegFruName", this.vegFruName)
                .append("samplingLocation", this.samplingLocation)
                .append("detectLocation",this.detectLocation)
                .append("samplingStageType", this.samplingStageType)
                .append("samplingDate", this.samplingDate)
                .append("pesticideDetResultId", this.pesticideDetResultId)
                .append("pesticideName", this.pesticideName)
                .append("standard_category", this.standardCategory)
                .append("pesticideDetValue", this.pesticideDetValue)
                .append("vegFruId", this.vegFruId)
                .append("vegFruType", this.vegFruType)
                .append("detailType", this.detailType)
                .toString();
    }
}
