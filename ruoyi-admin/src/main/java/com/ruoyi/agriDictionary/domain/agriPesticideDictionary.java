package com.ruoyi.agriDictionary.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 农药字典对象 agri_pesticide
 * 
 * @author chenjie
 * @date 2024-05-08
 */
public class agriPesticideDictionary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键，自增长 */
    private Long pesticideId;

    /** 农药名称 */
    @Excel(name = "农药名称")
    private String pesticideName;

    /** 检测标准（GB 23200.8-2016、GB 23200.10-2016、NY/T 1652-2008、GB 23200.121-2021等，详细看word，若一行有多个标准，用英文逗号分隔） */
    @Excel(name = "检测标准", readConverterExp = "G=B,2=3200.8-2016、GB,2=3200.10-2016、NY/T,1=652-2008、GB,2=3200.121-2021等，详细看word，若一行有多个标准，用英文逗号分隔")
    private String standard;

    /** 蔬菜农药是否禁用，0禁用，1不禁用 */
    @Excel(name = "蔬菜农药是否禁用，0禁用，1不禁用")
    private String vegPetPermit;

    /** 水果农药是否禁用，0禁用，1不禁用 */
    @Excel(name = "水果农药是否禁用，0禁用，1不禁用")
    private String fruPetPermit;

    /** 茶叶农药是否禁用，0禁用，1不禁用 */
    @Excel(name = "茶叶农药是否禁用，0禁用，1不禁用")
    private String teaPetPermit;

    /** 记录创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setPesticideId(Long pesticideId) 
    {
        this.pesticideId = pesticideId;
    }

    public Long getPesticideId() 
    {
        return pesticideId;
    }
    public void setPesticideName(String pesticideName) 
    {
        this.pesticideName = pesticideName;
    }

    public String getPesticideName() 
    {
        return pesticideName;
    }
    public void setStandard(String standard) 
    {
        this.standard = standard;
    }

    public String getStandard() 
    {
        return standard;
    }
    public void setVegPetPermit(String vegPetPermit) 
    {
        this.vegPetPermit = vegPetPermit;
    }

    public String getVegPetPermit() 
    {
        return vegPetPermit;
    }
    public void setFruPetPermit(String fruPetPermit) 
    {
        this.fruPetPermit = fruPetPermit;
    }

    public String getFruPetPermit() 
    {
        return fruPetPermit;
    }
    public void setTeaPetPermit(String teaPetPermit) 
    {
        this.teaPetPermit = teaPetPermit;
    }

    public String getTeaPetPermit() 
    {
        return teaPetPermit;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pesticideId", getPesticideId())
            .append("pesticideName", getPesticideName())
            .append("standard", getStandard())
            .append("vegPetPermit", getVegPetPermit())
            .append("fruPetPermit", getFruPetPermit())
            .append("teaPetPermit", getTeaPetPermit())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
