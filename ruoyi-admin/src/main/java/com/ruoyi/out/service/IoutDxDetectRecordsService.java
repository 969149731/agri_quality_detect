package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outDxDetectRecords;

/**
 * 定性监测结果汇总Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutDxDetectRecordsService 
{
    /**
     * 查询定性监测结果汇总
     * 
     * @param recordDxId 定性监测结果汇总主键
     * @return 定性监测结果汇总
     */
    public outDxDetectRecords selectoutDxDetectRecordsByRecordDxId(Long recordDxId);

    /**
     * 查询定性监测结果汇总列表
     * 
     * @param outDxDetectRecords 定性监测结果汇总
     * @return 定性监测结果汇总集合
     */
    public List<outDxDetectRecords> selectoutDxDetectRecordsList(outDxDetectRecords outDxDetectRecords);

    /**
     * 新增定性监测结果汇总
     * 
     * @param outDxDetectRecords 定性监测结果汇总
     * @return 结果
     */
    public int insertoutDxDetectRecords(outDxDetectRecords outDxDetectRecords);

    /**
     * 修改定性监测结果汇总
     * 
     * @param outDxDetectRecords 定性监测结果汇总
     * @return 结果
     */
    public int updateoutDxDetectRecords(outDxDetectRecords outDxDetectRecords);

    /**
     * 批量删除定性监测结果汇总
     * 
     * @param recordDxIds 需要删除的定性监测结果汇总主键集合
     * @return 结果
     */
    public int deleteoutDxDetectRecordsByRecordDxIds(Long[] recordDxIds);

    /**
     * 删除定性监测结果汇总信息
     * 
     * @param recordDxId 定性监测结果汇总主键
     * @return 结果
     */
    public int deleteoutDxDetectRecordsByRecordDxId(Long recordDxId);
}
