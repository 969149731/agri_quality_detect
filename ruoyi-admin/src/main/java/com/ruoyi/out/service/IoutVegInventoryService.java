package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outVegInventory;

/**
 * 蔬菜种类及数量Service接口
 * 
 * @author chenjie
 * @date 2024-03-12
 */
public interface IoutVegInventoryService 
{
    /**
     * 查询蔬菜种类及数量
     * 
     * @param vegFruInventoryId 蔬菜种类及数量主键
     * @return 蔬菜种类及数量
     */
    public outVegInventory selectoutVegInventoryByVegFruInventoryId(Long vegFruInventoryId);

    /**
     * 查询蔬菜种类及数量列表
     * 
     * @param outVegInventory 蔬菜种类及数量
     * @return 蔬菜种类及数量集合
     */
    public List<outVegInventory> selectoutVegInventoryList(outVegInventory outVegInventory);

    /**
     * 新增蔬菜种类及数量
     * 
     * @param outVegInventory 蔬菜种类及数量
     * @return 结果
     */
    public int insertoutVegInventory(outVegInventory outVegInventory);

    /**
     * 修改蔬菜种类及数量
     * 
     * @param outVegInventory 蔬菜种类及数量
     * @return 结果
     */
    public int updateoutVegInventory(outVegInventory outVegInventory);

    /**
     * 批量删除蔬菜种类及数量
     * 
     * @param vegFruInventoryIds 需要删除的蔬菜种类及数量主键集合
     * @return 结果
     */
    public int deleteoutVegInventoryByVegFruInventoryIds(Long[] vegFruInventoryIds);

    /**
     * 删除蔬菜种类及数量信息
     * 
     * @param vegFruInventoryId 蔬菜种类及数量主键
     * @return 结果
     */
    public int deleteoutVegInventoryByVegFruInventoryId(Long vegFruInventoryId);
}
