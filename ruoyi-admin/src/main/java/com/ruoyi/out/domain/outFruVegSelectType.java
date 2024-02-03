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
                .toString();
    }
}
