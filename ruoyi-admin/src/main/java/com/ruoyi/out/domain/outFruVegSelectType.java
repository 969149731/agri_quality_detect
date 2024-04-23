package com.ruoyi.out.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class outFruVegSelectType extends BaseEntity {
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



    public void fixData(){//数据预处理，主要是对生产环节进行纠正
        //注意生产基地不要放前面，否则先识别出来其他的生产基地子类就无法识别了
        List<String> StageType= Arrays.asList( "无公害产品基地","地标生产基地","绿色产品基地","有机产品基地","散户","其他基地","批发市场","运输车");//生产环节类型

        for (String item : StageType){
            if(this.samplingStageType!=null && this.samplingStageType.contains(item)){
                this.samplingStageType=item;//将所有数据清洗为规范格式
                return;//找到一个即可返回
            }
            if(this.samplingStageType!=null && this.samplingStageType.contains("生产基地")){//不是上述类型，但是包含生产基地，应为其他基地类型
                this.samplingStageType="其他基地";//将所有数据清洗为规范格式
                return;//找到一个即可返回
            }
        }
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
