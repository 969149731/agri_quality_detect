package com.ruoyi.out.domain.vo;

public class OutVegFruNameInventoryVo {

    private String vegFruName;
    private int sampleDetailCount;

    public String getVegFruName() {
        return vegFruName;
    }

    public void setVegFruName(String vegFruName) {
        this.vegFruName = vegFruName;
    }

    public int getSampleDetailCount() {
        return sampleDetailCount;
    }

    public void setSampleDetailCount(int sampleDetailCount) {
        this.sampleDetailCount = sampleDetailCount;
    }

    @Override
    public String toString() {
        return "OutVegFruNameInventoryVo{" +
                "vegFruName='" + vegFruName + '\'' +
                ", sampleDetailCount=" + sampleDetailCount +
                '}';
    }
}
