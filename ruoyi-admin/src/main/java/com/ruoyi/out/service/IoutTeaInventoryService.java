package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outTeaInventory;

/**
 * 茶叶种类及数量Service接口
 * 
 * @author chenjie
 * @date 2024-04-08
 */
public interface IoutTeaInventoryService 
{
    /**
     * 查询茶叶种类及数量
     * 
     * @param teaInventoryId 茶叶种类及数量主键
     * @return 茶叶种类及数量
     */
    public outTeaInventory selectoutTeaInventoryByTeaInventoryId(Long teaInventoryId);

    /**
     * 查询茶叶种类及数量列表
     * 
     * @param outTeaInventory 茶叶种类及数量
     * @return 茶叶种类及数量集合
     */
    public List<outTeaInventory> selectoutTeaInventoryList(outTeaInventory outTeaInventory);

    /**
     * 新增茶叶种类及数量
     * 
     * @param outTeaInventory 茶叶种类及数量
     * @return 结果
     */
    public int insertoutTeaInventory(outTeaInventory outTeaInventory);

    /**
     * 修改茶叶种类及数量
     * 
     * @param outTeaInventory 茶叶种类及数量
     * @return 结果
     */
    public int updateoutTeaInventory(outTeaInventory outTeaInventory);

    /**
     * 批量删除茶叶种类及数量
     * 
     * @param teaInventoryIds 需要删除的茶叶种类及数量主键集合
     * @return 结果
     */
    public int deleteoutTeaInventoryByTeaInventoryIds(Long[] teaInventoryIds);

    /**
     * 删除茶叶种类及数量信息
     * 
     * @param teaInventoryId 茶叶种类及数量主键
     * @return 结果
     */
    public int deleteoutTeaInventoryByTeaInventoryId(Long teaInventoryId);
}
