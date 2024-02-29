package com.ruoyi.detection.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
   这个类是为out2UndeterminedStandDet类中的超标农药品种及超标值泛型设计的
 */
public class agriPesticideDetResultForUndeterminedStandDet extends BaseEntity
{

    /** 农药名 */
    @Excel(name = "农药名")
    private String pesticideName;

    /** 检测值（mg/kg） */
    @Excel(name = "检测值")
    private Double pesticideDetValue;


    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public Double getPesticideDetValue() {
        return pesticideDetValue;
    }

    public void setPesticideDetValue(Double pesticideDetValue) {
        this.pesticideDetValue = pesticideDetValue;
    }


    @Override
    public String toString() {
        return "" +
                "'" + pesticideName + '\'' +
                "：" + pesticideDetValue +
                "mg/kg";
    }
}
