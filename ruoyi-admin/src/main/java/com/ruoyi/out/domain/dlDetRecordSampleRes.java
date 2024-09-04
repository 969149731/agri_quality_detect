package com.ruoyi.out.domain;

import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;

//定量检测要用到的输出的实体类
public class dlDetRecordSampleRes {

    private String SamplingLocation;

    /** 蔬菜抽样数 */
    @Excel(name = "蔬菜抽样数")
    private Integer vegSamplingCount;

    /** 蔬菜合格数 */
    @Excel(name = "蔬菜合格数")
    private Integer vegPassCount;

    /** 蔬菜合格率 */
    @Excel(name = "蔬菜合格率")
    private Double vegPassRate;

    /** 水果抽样数 */
    @Excel(name = "水果抽样数")
    private Integer fruSamplingCount;

    /** 水果合格数 */
    @Excel(name = "水果合格数")
    private Integer fruPassCount;

    /** 水果合格率 */
    @Excel(name = "水果合格率")
    private Double fruPassRate;


    /** 茶叶抽样数 */
    private Integer teaSamplingCount;

    /** 茶叶合格数 */
    private Integer teaPassCount;

    /** 茶叶合格率 */
    private Double teaPassRate;




    /** 总抽样数 */
    @Excel(name = "总抽样数")
    private Integer allSamplingCount=0;

    /** 总合格数 */
    @Excel(name = "总合格数")
    private Integer allPassCount=0;

    /** 总合格率，百分比 */
    @Excel(name = "总合格率，百分比")
    private Double allPassRate;

    public dlDetRecordSampleRes() {
    }

    public dlDetRecordSampleRes(Integer vegSamplingCount, Integer vegPassCount, Double vegPassRate, Integer fruSamplingCount, Integer fruPassCount, Double fruPassRate, Integer teaSamplingCount, Integer teaPassCount, Double teaPassRate, Integer allSamplingCount, Integer allPassCount, Double allPassRate) {
        this.vegSamplingCount = vegSamplingCount;
        this.vegPassCount = vegPassCount;
        this.vegPassRate = vegPassRate;
        this.fruSamplingCount = fruSamplingCount;
        this.fruPassCount = fruPassCount;
        this.fruPassRate = fruPassRate;
        this.teaSamplingCount = teaSamplingCount;
        this.teaPassCount = teaPassCount;
        this.teaPassRate = teaPassRate;
        this.allSamplingCount = allSamplingCount;
        this.allPassCount = allPassCount;
        this.allPassRate = allPassRate;
    }

    public String getSamplingLocation() {
        return SamplingLocation;
    }

    public void setSamplingLocation(String samplingLocation) {
        SamplingLocation = samplingLocation;
    }

    public Integer getTeaSamplingCount() {
        return teaSamplingCount;
    }

    public void setTeaSamplingCount(Integer teaSamplingCount) {
        this.teaSamplingCount = teaSamplingCount;
    }

    public Integer getTeaPassCount() {
        return teaPassCount;
    }

    public void setTeaPassCount(Integer teaPassCount) {
        this.teaPassCount = teaPassCount;
    }

    public Double getTeaPassRate() {
        return teaPassRate;
    }

    public void setTeaPassRate(Double teaPassRate) {
        this.teaPassRate = teaPassRate;
    }

    public Integer getVegSamplingCount() {
        return vegSamplingCount;
    }

    public void setVegSamplingCount(Integer vegSamplingCount) {
        this.vegSamplingCount = vegSamplingCount;
    }

    public Integer getVegPassCount() {
        return vegPassCount;
    }

    public void setVegPassCount(Integer vegPassCount) {
        this.vegPassCount = vegPassCount;
    }

    public Double getVegPassRate() {
        return vegPassRate;
    }

    public void setVegPassRate(Double vegPassRate) {
        this.vegPassRate = vegPassRate;
    }

    public Integer getFruSamplingCount() {
        return fruSamplingCount;
    }

    public void setFruSamplingCount(Integer fruSamplingCount) {
        this.fruSamplingCount = fruSamplingCount;
    }

    public Integer getFruPassCount() {
        return fruPassCount;
    }

    public void setFruPassCount(Integer fruPassCount) {
        this.fruPassCount = fruPassCount;
    }

    public Double getFruPassRate() {
        return fruPassRate;
    }

    public void setFruPassRate(Double fruPassRate) {
        this.fruPassRate = fruPassRate;
    }

    public Integer getAllSamplingCount() {
        return allSamplingCount;
    }

    public void setAllSamplingCount(Integer allSamplingCount) {
        this.allSamplingCount = allSamplingCount;
    }

    public Integer getAllPassCount() {
        return allPassCount;
    }

    public void setAllPassCount(Integer allPassCount) {
        this.allPassCount = allPassCount;
    }

    public Double getAllPassRate() {
        return allPassRate;
    }

    public void setAllPassRate(Double allPassRate) {
        this.allPassRate = allPassRate;
    }


    @Override
    public String toString() {
        return "dlDetRecordSampleRes{" +
                "SamplingLocation='" + SamplingLocation + '\'' +
                ", vegSamplingCount=" + vegSamplingCount +
                ", vegPassCount=" + vegPassCount +
                ", vegPassRate=" + vegPassRate +
                ", fruSamplingCount=" + fruSamplingCount +
                ", fruPassCount=" + fruPassCount +
                ", fruPassRate=" + fruPassRate +
                ", teaSamplingCount=" + teaSamplingCount +
                ", teaPassCount=" + teaPassCount +
                ", teaPassRate=" + teaPassRate +
                ", allSamplingCount=" + allSamplingCount +
                ", allPassCount=" + allPassCount +
                ", allPassRate=" + allPassRate +
                '}';
    }
}
