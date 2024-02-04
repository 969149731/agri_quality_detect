package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.outVegBanPesDetRecords;

/**
 * 蔬菜禁用农药检出及超标情况Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutVegBanPesDetRecordsService 
{
    /**
     * 查询蔬菜禁用农药检出及超标情况
     * 
     * @param vegBanPesDetRecordsId 蔬菜禁用农药检出及超标情况主键
     * @return 蔬菜禁用农药检出及超标情况
     */
    public outVegBanPesDetRecords selectoutVegBanPesDetRecordsByVegBanPesDetRecordsId(Long vegBanPesDetRecordsId);

    /**
     * 查询蔬菜禁用农药检出及超标情况列表
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 蔬菜禁用农药检出及超标情况集合
     */
    public List<outVegBanPesDetRecords> selectoutVegBanPesDetRecordsList(outVegBanPesDetRecords outVegBanPesDetRecords);

    /**
     * 新增蔬菜禁用农药检出及超标情况
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 结果
     */
    public int insertoutVegBanPesDetRecords(outVegBanPesDetRecords outVegBanPesDetRecords);

    /**
     * 修改蔬菜禁用农药检出及超标情况
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 结果
     */
    public int updateoutVegBanPesDetRecords(outVegBanPesDetRecords outVegBanPesDetRecords);

    /**
     * 批量删除蔬菜禁用农药检出及超标情况
     * 
     * @param vegBanPesDetRecordsIds 需要删除的蔬菜禁用农药检出及超标情况主键集合
     * @return 结果
     */
    public int deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsIds(Long[] vegBanPesDetRecordsIds);

    /**
     * 删除蔬菜禁用农药检出及超标情况信息
     * 
     * @param vegBanPesDetRecordsId 蔬菜禁用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsId(Long vegBanPesDetRecordsId);


    //自建方法
    List<outReturnType> selectoutVegBanPesDetRecordsList2();
}
