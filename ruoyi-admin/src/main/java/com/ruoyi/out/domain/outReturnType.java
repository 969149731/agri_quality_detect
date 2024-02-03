package com.ruoyi.out.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class outReturnType extends BaseEntity {
    private static final long serialVersionUID = 1L;
    String pesticideName;
    Long id;
    public int totalDet=0;
    public int totalEx=0;
    public int productBase=0;
    public int productBaseEx=0;
    public int market=0;
    public int marketEx=0;
    public int vehicle=0;
    public int vehicleEx=0;
    public int retail=0;
    public int retailEx=0;

    public outReturnType(String pesticideName){
        this.pesticideName=pesticideName;
    }
    //getterAndSetter
    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalDet() {
        return totalDet;
    }

    public void setTotalDet(int totalDet) {
        this.totalDet = totalDet;
    }

    public int getTotalEx() {
        return totalEx;
    }

    public void setTotalEx(int totalEx) {
        this.totalEx = totalEx;
    }

    public int getProductBase() {
        return productBase;
    }

    public void setProductBase(int productBase) {
        this.productBase = productBase;
    }

    public int getProductBaseEx() {
        return productBaseEx;
    }

    public void setProductBaseEx(int productBaseEx) {
        this.productBaseEx = productBaseEx;
    }

    public int getMarket() {
        return market;
    }

    public void setMarket(int market) {
        this.market = market;
    }

    public int getMarketEx() {
        return marketEx;
    }

    public void setMarketEx(int marketEx) {
        this.marketEx = marketEx;
    }

    public int getVehicle() {
        return vehicle;
    }

    public void setVehicle(int vehicle) {
        this.vehicle = vehicle;
    }

    public int getVehicleEx() {
        return vehicleEx;
    }

    public void setVehicleEx(int vehicleEx) {
        this.vehicleEx = vehicleEx;
    }

    public int getRetail() {
        return retail;
    }

    public void setRetail(int retail) {
        this.retail = retail;
    }

    public int getRetailEx() {
        return retailEx;
    }

    public void setRetailEx(int retailEx) {
        this.retailEx = retailEx;
    }


    //
    public void addOneToStageName(String StageName){
        switch (StageName){
            case "生产基地":this.productBase+=1;break;
            case "生产基地Ex":this.productBaseEx+=1;break;
            case "各类市场":this.market+=1;break;
            case "各类市场Ex":this.market+=1;break;
            case "运输车":this.vehicle+=1;break;
            case "运输车Ex":this.vehicleEx+=1;break;
            case "散户":this.retail+=1;break;
            case "散户Ex":this.retailEx+=1;break;
        }
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pesticideName", this.pesticideName)
                .append("totalDet", this.totalDet)
                .append("totalEx", this.totalEx)
                .append("productBase", this.productBase)
                .append("productBaseEx",this.productBaseEx)
                .append("market", this.market)
                .append("marketEx", this.marketEx)
                .append("vehicle", this.vehicle)
                .append("vehicleEx", this.vehicleEx)
                .append("retail", this.retail)
                .append("retail", this.retailEx)
                .toString();
    }
}
