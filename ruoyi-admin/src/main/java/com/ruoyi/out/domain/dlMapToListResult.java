package com.ruoyi.out.domain;

public class dlMapToListResult {
    public String samplingLocation;
    public dlDetRecordSampleRes sampleRes;

    public dlMapToListResult(String samplingLocation, dlDetRecordSampleRes sampleRes) {
        this.samplingLocation = samplingLocation;
        this.sampleRes = sampleRes;
    }

    public String getSamplingLocation() {
        return samplingLocation;
    }

    public void setSamplingLocation(String samplingLocation) {
        this.samplingLocation = samplingLocation;
    }

    public dlDetRecordSampleRes getSampleRes() {
        return sampleRes;
    }

    public void setSampleRes(dlDetRecordSampleRes sampleRes) {
        this.sampleRes = sampleRes;
    }

    @Override
    public String toString() {
        return "dlMapToListResult{" +
                "samplingLocation='" + samplingLocation + '\'' +
                ", sampleRes=" + sampleRes +
                '}';
    }
}