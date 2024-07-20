package com.ruoyi.out.domain.dto;

public class OutPesDetRecordsDto {
    private String stdStageType;
    private String pesticideName;
    private Integer chkNum;
    private Integer notStdNum;

    public String getStdStageType() {
        return stdStageType;
    }

    public void setStdStageType(String stdStageType) {
        this.stdStageType = stdStageType;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public Integer getChkNum() {
        return chkNum;
    }

    public void setChkNum(Integer chkNum) {
        this.chkNum = chkNum;
    }

    public Integer getNotStdNum() {
        return notStdNum;
    }

    public void setNotStdNum(Integer notStdNum) {
        this.notStdNum = notStdNum;
    }

    @Override
    public String toString() {
        return "OutPesDetRecordsDto{" +
                "stdStageType='" + stdStageType + '\'' +
                ", pesticideName='" + pesticideName + '\'' +
                ", chkNum=" + chkNum +
                ", notStdNum=" + notStdNum +
                '}';
    }
}
