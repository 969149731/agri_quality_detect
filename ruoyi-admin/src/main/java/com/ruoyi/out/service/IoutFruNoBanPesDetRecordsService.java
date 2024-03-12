package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outFruNoBanPesDetRecords;
import com.ruoyi.out.domain.outReturnType;

/**
 * 水果非禁止使用农药检出及超标情况Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutFruNoBanPesDetRecordsService 
{
    /**
     * 查询水果非禁止使用农药检出及超标情况
     * 
     * @param fruNoBanPesDetRecordsId 水果非禁止使用农药检出及超标情况主键
     * @return 水果非禁止使用农药检出及超标情况
     */
    public outFruNoBanPesDetRecords selectoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(Long fruNoBanPesDetRecordsId);

    /**
     * 查询水果非禁止使用农药检出及超标情况列表
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 水果非禁止使用农药检出及超标情况集合
     */
    public List<outFruNoBanPesDetRecords> selectoutFruNoBanPesDetRecordsList(outFruNoBanPesDetRecords outFruNoBanPesDetRecords);

    /**
     * 新增水果非禁止使用农药检出及超标情况
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 结果
     */
    public int insertoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords outFruNoBanPesDetRecords);

    /**
     * 修改水果非禁止使用农药检出及超标情况
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 结果
     */
    public int updateoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords outFruNoBanPesDetRecords);

    /**
     * 批量删除水果非禁止使用农药检出及超标情况
     * 
     * @param fruNoBanPesDetRecordsIds 需要删除的水果非禁止使用农药检出及超标情况主键集合
     * @return 结果
     */
    public int deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsIds(Long[] fruNoBanPesDetRecordsIds);

    /**
     * 删除水果非禁止使用农药检出及超标情况信息
     * 
     * @param fruNoBanPesDetRecordsId 水果非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(Long fruNoBanPesDetRecordsId);

    public List<outReturnType> selectoutFruNoBanPesDetRecordsList2(outFruNoBanPesDetRecords outFruNoBanPesDetRecords);
}
