package com.ruoyi.out.domain.vo;


public class OutPesDetRecordsVo {
    private String pesticideName;
    private Integer totalDet = 0;
    private Integer totalEx = 0;
    private Integer productBase = 0;
    private Integer productBaseEx = 0;
    private Integer market = 0;
    private Integer marketEx = 0;
    private Integer vehicle = 0;
    private Integer vehicleEx = 0;
    private Integer retail = 0;
    private Integer retailEx = 0;
    private Integer other = 0;
    private Integer otherEx = 0;

    // getters and setters

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public Integer getTotalDet() {
        return totalDet;
    }

    public void setTotalDet(Integer totalDet) {
        this.totalDet = totalDet;
    }

    public Integer getTotalEx() {
        return totalEx;
    }

    public void setTotalEx(Integer totalEx) {
        this.totalEx = totalEx;
    }

    public Integer getProductBase() {
        return productBase;
    }

    public void setProductBase(Integer productBase) {
        this.productBase = productBase;
    }

    public Integer getProductBaseEx() {
        return productBaseEx;
    }

    public void setProductBaseEx(Integer productBaseEx) {
        this.productBaseEx = productBaseEx;
    }

    public Integer getMarket() {
        return market;
    }

    public void setMarket(Integer market) {
        this.market = market;
    }

    public Integer getMarketEx() {
        return marketEx;
    }

    public void setMarketEx(Integer marketEx) {
        this.marketEx = marketEx;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getVehicleEx() {
        return vehicleEx;
    }

    public void setVehicleEx(Integer vehicleEx) {
        this.vehicleEx = vehicleEx;
    }

    public Integer getRetail() {
        return retail;
    }

    public void setRetail(Integer retail) {
        this.retail = retail;
    }

    public Integer getRetailEx() {
        return retailEx;
    }

    public void setRetailEx(Integer retailEx) {
        this.retailEx = retailEx;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getOtherEx() {
        return otherEx;
    }

    public void setOtherEx(Integer otherEx) {
        this.otherEx = otherEx;
    }

    @Override
    public String toString() {
        return "OutPesDetRecordsVo{" +
                "pesticideName='" + pesticideName + '\'' +
                ", totalDet=" + totalDet +
                ", totalEx=" + totalEx +
                ", productBase=" + productBase +
                ", productBaseEx=" + productBaseEx +
                ", market=" + market +
                ", marketEx=" + marketEx +
                ", vehicle=" + vehicle +
                ", vehicleEx=" + vehicleEx +
                ", retail=" + retail +
                ", retailEx=" + retailEx +
                ", other=" + other +
                ", otherEx=" + otherEx +
                '}';
    }
}
