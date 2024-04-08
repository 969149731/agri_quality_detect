package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.outTeaBanPesDetRecords;

/**
 * 茶叶禁用农药检出及超标情况Service接口
 * 
 * @author chenjie
 * @date 2024-04-08
 */
public interface IoutTeaBanPesDetRecordsService 
{
    /**
     * 查询茶叶禁用农药检出及超标情况
     * 
     * @param teaBanPesDetRecordsId 茶叶禁用农药检出及超标情况主键
     * @return 茶叶禁用农药检出及超标情况
     */
    public outTeaBanPesDetRecords selectoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(Long teaBanPesDetRecordsId);

    /**
     * 查询茶叶禁用农药检出及超标情况列表
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 茶叶禁用农药检出及超标情况集合
     */
    public List<outReturnType> selectoutTeaBanPesDetRecordsList(outTeaBanPesDetRecords outTeaBanPesDetRecords);

    /**
     * 新增茶叶禁用农药检出及超标情况
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 结果
     */
    public int insertoutTeaBanPesDetRecords(outTeaBanPesDetRecords outTeaBanPesDetRecords);

    /**
     * 修改茶叶禁用农药检出及超标情况
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 结果
     */
    public int updateoutTeaBanPesDetRecords(outTeaBanPesDetRecords outTeaBanPesDetRecords);

    /**
     * 批量删除茶叶禁用农药检出及超标情况
     * 
     * @param teaBanPesDetRecordsIds 需要删除的茶叶禁用农药检出及超标情况主键集合
     * @return 结果
     */
    public int deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsIds(Long[] teaBanPesDetRecordsIds);

    /**
     * 删除茶叶禁用农药检出及超标情况信息
     * 
     * @param teaBanPesDetRecordsId 茶叶禁用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(Long teaBanPesDetRecordsId);
}
