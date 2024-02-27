package com.ruoyi.out.service.impl;

import java.util.List;

import com.ruoyi.out.domain.VegFruStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outVegFruInventoryMapper;
import com.ruoyi.out.domain.outVegFruInventory;
import com.ruoyi.out.service.IoutVegFruInventoryService;

/**
 * 蔬菜水果种类及数量Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outVegFruInventoryServiceImpl implements IoutVegFruInventoryService 
{
    @Autowired
    private outVegFruInventoryMapper outVegFruInventoryMapper;

    /**
     * 查询蔬菜水果种类及数量
     * 
     * @param vegFruInventoryId 蔬菜水果种类及数量主键
     * @return 蔬菜水果种类及数量
     */
    @Override
    public outVegFruInventory selectoutVegFruInventoryByVegFruInventoryId(Long vegFruInventoryId)
    {
        return outVegFruInventoryMapper.selectoutVegFruInventoryByVegFruInventoryId(vegFruInventoryId);
    }

    /**
     * 查询蔬菜水果种类及数量列表
     * 
     * @param outVegFruInventory 蔬菜水果种类及数量
     * @return 蔬菜水果种类及数量
     */
    @Override
    public List<outVegFruInventory> selectoutVegFruInventoryList(outVegFruInventory outVegFruInventory)
    {
        return outVegFruInventoryMapper.selectoutVegFruInventoryList(outVegFruInventory);
    }

    /**
     * 新增蔬菜水果种类及数量
     * 
     * @param outVegFruInventory 蔬菜水果种类及数量
     * @return 结果
     */
    @Override
    public int insertoutVegFruInventory(outVegFruInventory outVegFruInventory)
    {
        return outVegFruInventoryMapper.insertoutVegFruInventory(outVegFruInventory);
    }

    /**
     * 修改蔬菜水果种类及数量
     * 
     * @param outVegFruInventory 蔬菜水果种类及数量
     * @return 结果
     */
    @Override
    public int updateoutVegFruInventory(outVegFruInventory outVegFruInventory)
    {
        return outVegFruInventoryMapper.updateoutVegFruInventory(outVegFruInventory);
    }

    /**
     * 批量删除蔬菜水果种类及数量
     * 
     * @param vegFruInventoryIds 需要删除的蔬菜水果种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutVegFruInventoryByVegFruInventoryIds(Long[] vegFruInventoryIds)
    {
        return outVegFruInventoryMapper.deleteoutVegFruInventoryByVegFruInventoryIds(vegFruInventoryIds);
    }

    /**
     * 删除蔬菜水果种类及数量信息
     * 
     * @param vegFruInventoryId 蔬菜水果种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutVegFruInventoryByVegFruInventoryId(Long vegFruInventoryId)
    {
        return outVegFruInventoryMapper.deleteoutVegFruInventoryByVegFruInventoryId(vegFruInventoryId);
    }

    @Override
    public List<VegFruStatistic> selectVegFruStatistic() {
        return outVegFruInventoryMapper.selectVegFruStatistic();
    }
}
