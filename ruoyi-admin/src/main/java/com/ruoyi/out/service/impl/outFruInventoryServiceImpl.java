package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outFruInventoryMapper;
import com.ruoyi.out.domain.outFruInventory;
import com.ruoyi.out.service.IoutFruInventoryService;

/**
 * 水果种类及数量Service业务层处理
 * 
 * @author chenjie
 * @date 2024-03-12
 */
@Service
public class outFruInventoryServiceImpl implements IoutFruInventoryService 
{
    @Autowired
    private outFruInventoryMapper outFruInventoryMapper;

    /**
     * 查询水果种类及数量
     * 
     * @param vegFruInventoryId 水果种类及数量主键
     * @return 水果种类及数量
     */
    @Override
    public outFruInventory selectoutFruInventoryByVegFruInventoryId(Long vegFruInventoryId)
    {
        return outFruInventoryMapper.selectoutFruInventoryByVegFruInventoryId(vegFruInventoryId);
    }

    /**
     * 查询水果种类及数量列表
     * 
     * @param outFruInventory 水果种类及数量
     * @return 水果种类及数量
     */
    @Override
    public List<outFruInventory> selectoutFruInventoryList(outFruInventory outFruInventory)
    {
        return outFruInventoryMapper.selectoutFruInventoryList(outFruInventory);
    }

    /**
     * 新增水果种类及数量
     * 
     * @param outFruInventory 水果种类及数量
     * @return 结果
     */
    @Override
    public int insertoutFruInventory(outFruInventory outFruInventory)
    {
        return outFruInventoryMapper.insertoutFruInventory(outFruInventory);
    }

    /**
     * 修改水果种类及数量
     * 
     * @param outFruInventory 水果种类及数量
     * @return 结果
     */
    @Override
    public int updateoutFruInventory(outFruInventory outFruInventory)
    {
        return outFruInventoryMapper.updateoutFruInventory(outFruInventory);
    }

    /**
     * 批量删除水果种类及数量
     * 
     * @param vegFruInventoryIds 需要删除的水果种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutFruInventoryByVegFruInventoryIds(Long[] vegFruInventoryIds)
    {
        return outFruInventoryMapper.deleteoutFruInventoryByVegFruInventoryIds(vegFruInventoryIds);
    }

    /**
     * 删除水果种类及数量信息
     * 
     * @param vegFruInventoryId 水果种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutFruInventoryByVegFruInventoryId(Long vegFruInventoryId)
    {
        return outFruInventoryMapper.deleteoutFruInventoryByVegFruInventoryId(vegFruInventoryId);
    }
}
