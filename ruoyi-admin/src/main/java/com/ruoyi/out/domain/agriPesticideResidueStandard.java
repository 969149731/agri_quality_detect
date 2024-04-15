package com.ruoyi.out.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class agriPesticideResidueStandard {
    public Long standardID;
    public String pesticideName;
    public String vegFruName;
    public String standardCategory;
    public Double standardValue;

    public agriPesticideResidueStandard(){
        this.standardValue=0.0;
    }
    public Long getStandardID() {
        return standardID;
    }

    public void setStandardID(Long standardID) {
        this.standardID = standardID;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getVegFruName() {
        return vegFruName;
    }

    public void setVegFruName(String vegFruName) {
        this.vegFruName = vegFruName;
    }

    public String getStandardCategory() {
        return standardCategory;
    }

    public void setStandardCategory(String standardCategory) {
        this.standardCategory = standardCategory;
    }

    public Double getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(Double standardValue) {
        this.standardValue = standardValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("standardID", this.standardID)
                .append("pesticideName", this.pesticideName)
                .append("vegFruName", this.vegFruName)
                .append("standardCategory", this.standardCategory)
                .append("standardValue",this.standardValue)
                .toString();
    }
}
