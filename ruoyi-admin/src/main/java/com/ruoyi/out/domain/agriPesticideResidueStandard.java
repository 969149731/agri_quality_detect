package com.ruoyi.out.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class agriPesticideResidueStandard {
    public Long standardID;
    public String pesticideName;
    public String vegFruName;
    public String standardCategory;
    public Double standardValue;

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
