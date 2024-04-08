package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outTeaInventoryMapper;
import com.ruoyi.out.domain.outTeaInventory;
import com.ruoyi.out.service.IoutTeaInventoryService;

/**
 * 茶叶种类及数量Service业务层处理
 * 
 * @author chenjie
 * @date 2024-04-08
 */
@Service
public class outTeaInventoryServiceImpl implements IoutTeaInventoryService 
{
    @Autowired
    private outTeaInventoryMapper outTeaInventoryMapper;

    /**
     * 查询茶叶种类及数量
     * 
     * @param teaInventoryId 茶叶种类及数量主键
     * @return 茶叶种类及数量
     */
    @Override
    public outTeaInventory selectoutTeaInventoryByTeaInventoryId(Long teaInventoryId)
    {
        return outTeaInventoryMapper.selectoutTeaInventoryByTeaInventoryId(teaInventoryId);
    }

    /**
     * 查询茶叶种类及数量列表
     * 
     * @param outTeaInventory 茶叶种类及数量
     * @return 茶叶种类及数量
     */
    @Override
    public List<outTeaInventory> selectoutTeaInventoryList(outTeaInventory outTeaInventory)
    {
        return outTeaInventoryMapper.selectoutTeaInventoryList(outTeaInventory);
    }

    /**
     * 新增茶叶种类及数量
     * 
     * @param outTeaInventory 茶叶种类及数量
     * @return 结果
     */
    @Override
    public int insertoutTeaInventory(outTeaInventory outTeaInventory)
    {
        return outTeaInventoryMapper.insertoutTeaInventory(outTeaInventory);
    }

    /**
     * 修改茶叶种类及数量
     * 
     * @param outTeaInventory 茶叶种类及数量
     * @return 结果
     */
    @Override
    public int updateoutTeaInventory(outTeaInventory outTeaInventory)
    {
        return outTeaInventoryMapper.updateoutTeaInventory(outTeaInventory);
    }

    /**
     * 批量删除茶叶种类及数量
     * 
     * @param teaInventoryIds 需要删除的茶叶种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutTeaInventoryByTeaInventoryIds(Long[] teaInventoryIds)
    {
        return outTeaInventoryMapper.deleteoutTeaInventoryByTeaInventoryIds(teaInventoryIds);
    }

    /**
     * 删除茶叶种类及数量信息
     * 
     * @param teaInventoryId 茶叶种类及数量主键
     * @return 结果
     */
    @Override
    public int deleteoutTeaInventoryByTeaInventoryId(Long teaInventoryId)
    {
        return outTeaInventoryMapper.deleteoutTeaInventoryByTeaInventoryId(teaInventoryId);
    }
}
