package com.ruoyi.out.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class outStandardReturnType extends BaseEntity {
    private static final long serialVersionUID = 1L;
    Long id;
    String pesticideName;
    public int CN=0;
    public int CAC=0;
    public int US=0;
    public int EU=0;
    public int JPN=0;
    public int KR=0;

    public outStandardReturnType(String pesticideName){
        this.pesticideName=pesticideName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public int getCN() {
        return CN;
    }

    public void setCN(int CN) {
        this.CN = CN;
    }

    public int getCAC() {
        return CAC;
    }

    public void setCAC(int CAC) {
        this.CAC = CAC;
    }

    public int getUS() {
        return US;
    }

    public void setUS(int US) {
        this.US = US;
    }

    public int getEU() {
        return EU;
    }

    public void setEU(int EU) {
        this.EU = EU;
    }

    public int getJPN() {
        return JPN;
    }

    public void setJPN(int JPN) {
        this.JPN = JPN;
    }

    public int getKR() {
        return KR;
    }

    public void setKR(int KR) {
        this.KR = KR;
    }




    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("CN", this.CN)
                .append("CAC", this.CAC)
                .append("US", this.US)
                .append("EU", this.EU)
                .append("JPN",this.JPN)
                .append("KR", this.KR)
                .toString();
    }
}
