package com.ruoyi.out.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 蔬菜种类及数量对象 out_veg_inventory
 * 
 * @author chenjie
 * @date 2024-03-12
 */
public class outVegInventory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识符 */
    private Long vegFruInventoryId;

    /** 蔬菜类序号，用于标识蔬菜类别 */
    @Excel(name = "蔬菜类序号，用于标识蔬菜类别")
    private Long vegetableSeqNo;

    /** 蔬菜类详细名 */
    @Excel(name = "蔬菜类详细名")
    private String vegetableDetailName;

    /** 蔬菜类数量 */
    @Excel(name = "蔬菜类数量")
    private Long vegetableQuantity;

    /** 水果类序号，用于标识水果类别 */
    @Excel(name = "水果类序号，用于标识水果类别")
    private Long fruitSeqNo;

    /** 水果类详细名 */
    @Excel(name = "水果类详细名")
    private String fruitDetailName;

    /** 水果类数量 */
    @Excel(name = "水果类数量")
    private Long fruitQuantity;

    /** 记录创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdDate;

    public void setVegFruInventoryId(Long vegFruInventoryId) 
    {
        this.vegFruInventoryId = vegFruInventoryId;
    }

    public Long getVegFruInventoryId() 
    {
        return vegFruInventoryId;
    }
    public void setVegetableSeqNo(Long vegetableSeqNo) 
    {
        this.vegetableSeqNo = vegetableSeqNo;
    }

    public Long getVegetableSeqNo() 
    {
        return vegetableSeqNo;
    }
    public void setVegetableDetailName(String vegetableDetailName) 
    {
        this.vegetableDetailName = vegetableDetailName;
    }

    public String getVegetableDetailName() 
    {
        return vegetableDetailName;
    }
    public void setVegetableQuantity(Long vegetableQuantity) 
    {
        this.vegetableQuantity = vegetableQuantity;
    }

    public Long getVegetableQuantity() 
    {
        return vegetableQuantity;
    }
    public void setFruitSeqNo(Long fruitSeqNo) 
    {
        this.fruitSeqNo = fruitSeqNo;
    }

    public Long getFruitSeqNo() 
    {
        return fruitSeqNo;
    }
    public void setFruitDetailName(String fruitDetailName) 
    {
        this.fruitDetailName = fruitDetailName;
    }

    public String getFruitDetailName() 
    {
        return fruitDetailName;
    }
    public void setFruitQuantity(Long fruitQuantity) 
    {
        this.fruitQuantity = fruitQuantity;
    }

    public Long getFruitQuantity() 
    {
        return fruitQuantity;
    }
    public void setCreatedDate(Date createdDate) 
    {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() 
    {
        return createdDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vegFruInventoryId", getVegFruInventoryId())
            .append("vegetableSeqNo", getVegetableSeqNo())
            .append("vegetableDetailName", getVegetableDetailName())
            .append("vegetableQuantity", getVegetableQuantity())
            .append("fruitSeqNo", getFruitSeqNo())
            .append("fruitDetailName", getFruitDetailName())
            .append("fruitQuantity", getFruitQuantity())
            .append("createdDate", getCreatedDate())
            .toString();
    }
}
