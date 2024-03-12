package com.ruoyi.out.service;

import java.sql.Date;
import java.util.List;
import com.ruoyi.out.domain.outFruBanPesDetRecords;
import com.ruoyi.out.domain.outReturnType;

/**
 * 水果禁用农药检出及超标情况Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutFruBanPesDetRecordsService 
{
    /**
     * 查询水果禁用农药检出及超标情况
     * 
     * @param fruBanPesDetRecordsId 水果禁用农药检出及超标情况主键
     * @return 水果禁用农药检出及超标情况
     */
    public outFruBanPesDetRecords selectoutFruBanPesDetRecordsByFruBanPesDetRecordsId(Long fruBanPesDetRecordsId);

    /**
     * 查询水果禁用农药检出及超标情况列表
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 水果禁用农药检出及超标情况集合
     */
    public List<outFruBanPesDetRecords> selectoutFruBanPesDetRecordsList(outFruBanPesDetRecords outFruBanPesDetRecords);

    /**
     * 新增水果禁用农药检出及超标情况
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 结果
     */
    public int insertoutFruBanPesDetRecords(outFruBanPesDetRecords outFruBanPesDetRecords);

    /**
     * 修改水果禁用农药检出及超标情况
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 结果
     */
    public int updateoutFruBanPesDetRecords(outFruBanPesDetRecords outFruBanPesDetRecords);

    /**
     * 批量删除水果禁用农药检出及超标情况
     * 
     * @param fruBanPesDetRecordsIds 需要删除的水果禁用农药检出及超标情况主键集合
     * @return 结果
     */
    public int deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsIds(Long[] fruBanPesDetRecordsIds);

    /**
     * 删除水果禁用农药检出及超标情况信息
     * 
     * @param fruBanPesDetRecordsId 水果禁用农药检出及超标情况主键
     * @return 结果
     */
    public int deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsId(Long fruBanPesDetRecordsId);

    /*使用新方法获取列表
    */
    public List<outReturnType> selectoutFruBanPesDetRecordsList2(outReturnType outReturnTypeRecords, Date startDate,Date endDate);
}
