package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.outVegNoBanPesDetRecords;

/**
 * 蔬菜上非禁止使用农药检出及超标情况Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutVegNoBanPesDetRecordsService 
{
    /**
     * 查询蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param vegNoBanPesDetRecordsId 蔬菜上非禁止使用农药检出及超标情况主键
     * @return 蔬菜上非禁止使用农药检出及超标情况
     */
    public outVegNoBanPesDetRecords selectoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(Long vegNoBanPesDetRecordsId);

    /**
     * 查询蔬菜上非禁止使用农药检出及超标情况列表
     * 
     * @param outVegNoBanPesDetRecords 蔬菜上非禁止使用农药检出及超标情况
     * @return 蔬菜上非禁止使用农药检出及超标情况集合
     */
    public List<outVegNoBanPesDetRecords> selectoutVegNoBanPesDetRecordsList(outVegNoBanPesDetRecords outVegNoBanPesDetRecords);

    /**
     * 新增蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param outVegNoBanPesDetRecords 蔬菜上非禁止使用农药检出及超标情况
     * @return 结果
     */
    public int insertoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords outVegNoBanPesDetRecords);

    /**
     * 修改蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param outVegNoBanPesDetRecords 蔬菜上非禁止使用农药检出及超标情况
     * @return 结果
     */
    public int updateoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords outVegNoBanPesDetRecords);

    /**
     * 批量删除蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param vegNoBanPesDetRecordsIds 需要删除的蔬菜上非禁止使用农药检出及超标情况主键集合
     * @return 结果
     */
    public int deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsIds(Long[] vegNoBanPesDetRecordsIds);

    /**
     * 删除蔬菜上非禁止使用农药检出及超标情况信息
     * 
     * @param vegNoBanPesDetRecordsId 蔬菜上非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(Long vegNoBanPesDetRecordsId);

    public List<outReturnType> selectoutVegNoBanPesDetRecordsList2(outVegNoBanPesDetRecords outVegNoBanPesDetRecords);
}
