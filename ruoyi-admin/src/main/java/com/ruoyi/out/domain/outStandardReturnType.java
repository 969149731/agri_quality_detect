package com.ruoyi.out.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

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
        super();
        this.pesticideName=pesticideName;
    }
    public outStandardReturnType(){
        super();
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

    public void addOne(String StandardName){
        switch (StandardName){
            case "国家标准":this.CN+=1;break;
            case "CAC":this.CAC+=1;break;
            case "日本":this.JPN+=1;break;
            case "欧盟":this.EU+=1;break;
            case "美国":this.US+=1;break;
            case "韩国":this.KR+=1;break;
        }
    }
    public void setOne(String StandardName,int setTonum){
        switch (StandardName){
            case "国家标准":this.CN=setTonum;break;
            case "CAC":this.CAC=setTonum;break;
            case "日本":this.JPN=setTonum;break;
            case "欧盟":this.EU=setTonum;break;
            case "美国":this.US=setTonum;break;
            case "韩国":this.KR=setTonum;break;
        }
    }
    public void addAll(outStandardReturnType OneOther){
        this.CN+=OneOther.CN;
        this.CAC+=OneOther.CAC;
        this.JPN+=OneOther.JPN;
        this.US+=OneOther.US;
        this.EU+=OneOther.EU;
        this.KR+=OneOther.KR;
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
    //工具方法
    public void setAll(int SampleNum){
        this.CN=SampleNum;
        this.CAC=SampleNum;
        this.JPN=SampleNum;
        this.US=SampleNum;
        this.EU=SampleNum;
        this.KR=SampleNum;
    }
    public void computePassNum(outStandardReturnType SampleNum,outStandardReturnType ExNum){
        this.CN=SampleNum.CN-ExNum.CN;
        this.CAC=SampleNum.CAC-ExNum.CAC;
        this.JPN=SampleNum.JPN-ExNum.JPN;
        this.US=SampleNum.US-ExNum.US;
        this.EU=SampleNum.EU-ExNum.EU;
        this.KR=SampleNum.KR-ExNum.KR;
    }
}
