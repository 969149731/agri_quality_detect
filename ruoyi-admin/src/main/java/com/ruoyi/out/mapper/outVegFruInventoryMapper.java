package com.ruoyi.out.mapper;

import java.util.List;

import com.ruoyi.out.domain.VegFruStatistic;
import com.ruoyi.out.domain.outVegFruInventory;
import org.springframework.stereotype.Repository;

/**
 * 蔬菜水果种类及数量Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */

@Repository
public interface outVegFruInventoryMapper 
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
     * 删除蔬菜水果种类及数量
     * 
     * @param vegFruInventoryId 蔬菜水果种类及数量主键
     * @return 结果
     */
    public int deleteoutVegFruInventoryByVegFruInventoryId(Long vegFruInventoryId);

    /**
     * 批量删除蔬菜水果种类及数量
     * 
     * @param vegFruInventoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutVegFruInventoryByVegFruInventoryIds(Long[] vegFruInventoryIds);

    List<VegFruStatistic> selectVegFruStatistic();
}
