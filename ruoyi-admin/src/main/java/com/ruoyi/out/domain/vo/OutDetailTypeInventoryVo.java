package com.ruoyi.out.domain.vo;

import java.util.List;

public class OutDetailTypeInventoryVo {

    private int seqNo;   //序号
    private String detailType;
    private int sampleCount;

    private List<OutVegFruNameInventoryVo> detailList;

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public List<OutVegFruNameInventoryVo> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<OutVegFruNameInventoryVo> detailList) {
        this.detailList = detailList;
    }

    @Override
    public String toString() {
        return "OutDetailTypeInventoryVo{" +
                "seqNo=" + seqNo +
                ", detailType='" + detailType + '\'' +
                ", sampleCount=" + sampleCount +
                ", detailList=" + detailList +
                '}';
    }
}
