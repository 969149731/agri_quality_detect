package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outFruInventory;

/**
 * 水果种类及数量Service接口
 * 
 * @author chenjie
 * @date 2024-03-12
 */
public interface IoutFruInventoryService 
{
    /**
     * 查询水果种类及数量
     * 
     * @param vegFruInventoryId 水果种类及数量主键
     * @return 水果种类及数量
     */
    public outFruInventory selectoutFruInventoryByVegFruInventoryId(Long vegFruInventoryId);

    /**
     * 查询水果种类及数量列表
     * 
     * @param outFruInventory 水果种类及数量
     * @return 水果种类及数量集合
     */
    public List<outFruInventory> selectoutFruInventoryList(outFruInventory outFruInventory);

    /**
     * 新增水果种类及数量
     * 
     * @param outFruInventory 水果种类及数量
     * @return 结果
     */
    public int insertoutFruInventory(outFruInventory outFruInventory);

    /**
     * 修改水果种类及数量
     * 
     * @param outFruInventory 水果种类及数量
     * @return 结果
     */
    public int updateoutFruInventory(outFruInventory outFruInventory);

    /**
     * 批量删除水果种类及数量
     * 
     * @param vegFruInventoryIds 需要删除的水果种类及数量主键集合
     * @return 结果
     */
    public int deleteoutFruInventoryByVegFruInventoryIds(Long[] vegFruInventoryIds);

    /**
     * 删除水果种类及数量信息
     * 
     * @param vegFruInventoryId 水果种类及数量主键
     * @return 结果
     */
    public int deleteoutFruInventoryByVegFruInventoryId(Long vegFruInventoryId);
}
