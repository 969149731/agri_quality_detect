package com.ruoyi.out.domain;
//用来菜水果种类及数量模块 查询结果 的映射
public class VegFruStatistic {
    private int vegetableSeqNo;
    private  int fruitSeqNo;
    private  int teaSeqNo;

    private String name;
    private String type;
    private int quantity;

    public int getVegetableSeqNo() {
        return vegetableSeqNo;
    }

    public void setVegetableSeqNo(int vegetableSeqNo) {
        this.vegetableSeqNo = vegetableSeqNo;
    }

    public int getFruitSeqNo() {
        return fruitSeqNo;
    }

    public void setFruitSeqNo(int fruitSeqNo) {
        this.fruitSeqNo = fruitSeqNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTeaSeqNo() {
        return teaSeqNo;
    }

    public void setTeaSeqNo(int teaSeqNo) {
        this.teaSeqNo = teaSeqNo;
    }

    @Override
    public String toString() {
        return "VegFruStatistic{" +
                "vegetableSeqNo=" + vegetableSeqNo +
                ", fruitSeqNo=" + fruitSeqNo +
                ", teaSeqNo=" + teaSeqNo +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}