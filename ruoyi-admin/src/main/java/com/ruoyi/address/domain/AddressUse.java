package com.ruoyi.address.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用来封装各个城市编码的实体类
 * 
 * @author chenjie
 * @date 2024-04-10
 */
public class AddressUse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 抽样省份编码 */
    private String samplingProvinceCode;

    /** 抽样城市编码 */
    private String samplingCityCode;

    /** 抽样县编码 */
    private String samplingTownCode;

    public String getSamplingProvinceCode() {
        return samplingProvinceCode;
    }

    public void setSamplingProvinceCode(String samplingProvinceCode) {
        this.samplingProvinceCode = samplingProvinceCode;
    }

    public String getSamplingCityCode() {
        return samplingCityCode;
    }

    public void setSamplingCityCode(String samplingCityCode) {
        this.samplingCityCode = samplingCityCode;
    }

    public String getSamplingTownCode() {
        return samplingTownCode;
    }

    public void setSamplingTownCode(String samplingTownCode) {
        this.samplingTownCode = samplingTownCode;
    }

    @Override
    public String toString() {
        return "AddressUse{" +
                "samplingProvinceCode='" + samplingProvinceCode + '\'' +
                ", samplingCityCode='" + samplingCityCode + '\'' +
                ", samplingTownCode='" + samplingTownCode + '\'' +
                '}';
    }
}
