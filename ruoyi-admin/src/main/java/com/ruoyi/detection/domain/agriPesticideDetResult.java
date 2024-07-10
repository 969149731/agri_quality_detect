package com.ruoyi.detection.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 农药检测结果对象 agri_pesticide_det_result
 * 
 * @author chenjie
 * @date 2024-01-24
 */
public class agriPesticideDetResult extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long pesticideDetResultId;

    /** 农药名 */
    @Excel(name = "农药名")
    private String pesticideName;

    /** 检测值（mg/kg） */
    @Excel(name = "检测值")
    private String pesticideDetValue;

    /** 逻辑外键，用id关联各市样品检测结果详细表 */
    private Long citySampleTestDetailsId;

    /** 记录创建时间 */
    private Date createdAt;

    public void setPesticideDetResultId(Long pesticideDetResultId) 
    {
        this.pesticideDetResultId = pesticideDetResultId;
    }

    public Long getPesticideDetResultId() 
    {
        return pesticideDetResultId;
    }
    public void setPesticideName(String pesticideName) 
    {
        this.pesticideName = pesticideName;
    }

    public String getPesticideName() 
    {
        return pesticideName;
    }
    public void setPesticideDetValue(String pesticideDetValue)
    {
        this.pesticideDetValue = pesticideDetValue;
    }

    public String getPesticideDetValue()
    {
        return pesticideDetValue;
    }
    public void setCitySampleTestDetailsId(Long citySampleTestDetailsId) 
    {
        this.citySampleTestDetailsId = citySampleTestDetailsId;
    }

    public Long getCitySampleTestDetailsId() 
    {
        return citySampleTestDetailsId;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return " " +
                "农药名：" + pesticideName + ' ' +
                ", 检测值：" + pesticideDetValue + ' ' +
                ' ';
    }
}
