package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.out.domain.VegFruStatistic;
import com.ruoyi.out.domain.outVegFruInventory;

/**
 * 蔬菜水果种类及数量Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutVegFruInventoryService 
{
    /**
     * 查询蔬菜水果种类及数量
     * 
     * @param vegFruInventoryId 蔬菜水果种类及数量主键
     * @return 蔬菜水果种类及数量
     */
    public outVegFruInventory selectoutVegFruInventoryByVegFruInventoryId(Long vegFruInventoryId);

    /**
     * 查询蔬菜水果种类及数量列表
     * 
     * @param outVegFruInventory 蔬菜水果种类及数量
     * @return 蔬菜水果种类及数量集合
     */
    public List<outVegFruInventory> selectoutVegFruInventoryList(outVegFruInventory outVegFruInventory);

    /**
     * 新增蔬菜水果种类及数量
     * 
     * @param outVegFruInventory 蔬菜水果种类及数量
     * @return 结果
     */
    public int insertoutVegFruInventory(outVegFruInventory outVegFruInventory);

    /**
     * 修改蔬菜水果种类及数量
     * 
     * @param outVegFruInventory 蔬菜水果种类及数量
     * @return 结果
     */
    public int updateoutVegFruInventory(outVegFruInventory outVegFruInventory);

    /**
     * 批量删除蔬菜水果种类及数量
     * 
     * @param vegFruInventoryIds 需要删除的蔬菜水果种类及数量主键集合
     * @return 结果
     */
    public int deleteoutVegFruInventoryByVegFruInventoryIds(Long[] vegFruInventoryIds);

    /**
     * 删除蔬菜水果种类及数量信息
     * 
     * @param vegFruInventoryId 蔬菜水果种类及数量主键
     * @return 结果
     */
    public int deleteoutVegFruInventoryByVegFruInventoryId(Long vegFruInventoryId);

    List<VegFruStatistic> selectVegFruStatistic();
}
