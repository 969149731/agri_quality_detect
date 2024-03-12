package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outVegInventoryMapper;
import com.ruoyi.out.domain.outVegInventory;
import com.ruoyi.out.service.IoutVegInventoryService;

/**
 * 蔬菜种类及数量Service业务层处理
 * 
 * @author chenjie
 * @date 2024-03-12
 */
@Service
public class outVegInventoryServiceImpl implements IoutVegInventoryService 
{
    @Autowired
    private outVegInventoryMapper outVegInventoryMapper;

    /**
     * 查询蔬菜种类及数量
     * 
     * @param vegFruInventoryId 蔬菜种类及数量主键
     * @return 蔬菜种类及数量
     */
    @Override
    public outVegInventory selectoutVegInventoryByVegFruInventoryId(Long vegFruInventoryId)
    {
        return outVegInventoryMapper.selectoutVegInventoryByVegFruInventoryId(vegFruInventoryId);
    }

    /**
     * 查询蔬菜种类及数量列表
     * 
     * @param outVegInventory 蔬菜种类及数量
     * @return 蔬菜种类及数量
     */
    @Override
    public List<outVegInventory> selectoutVegInventoryList(outVegInventory outVegInventory)
    {
        return outVegInventoryMapper.selectoutVegInventoryList(outVegInventory);
    }

    /**
     * 新增蔬菜种类及数量
     * 
     * @param outVegInventory 蔬菜种类及数量
     * @return 结果
     */
    @Override
    public int insertoutVegInventory(outVegInventory outVegInventory)
    {
        return outVegInventoryMapper.insertoutVegInventory(outVegInventory);
    }

    /**
     * 修改蔬菜种类及数量
     * 
     * @param outVegInventory 蔬菜种类及数量
     * @return 结果
     */
    @Override
    public int updateoutVegInventory(outVegInventory outVegInventory)
    {
        return outVegInventoryMapper.updateoutVegInventory(outVegInventory);
    }

    /**
     * 批量删除蔬菜种类及数量
     * 
     * @param vegFruInventoryIds 需要删除的蔬菜种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutVegInventoryByVegFruInventoryIds(Long[] vegFruInventoryIds)
    {
        return outVegInventoryMapper.deleteoutVegInventoryByVegFruInventoryIds(vegFruInventoryIds);
    }

    /**
     * 删除蔬菜种类及数量信息
     * 
     * @param vegFruInventoryId 蔬菜种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutVegInventoryByVegFruInventoryId(Long vegFruInventoryId)
    {
        return outVegInventoryMapper.deleteoutVegInventoryByVegFruInventoryId(vegFruInventoryId);
    }
}
