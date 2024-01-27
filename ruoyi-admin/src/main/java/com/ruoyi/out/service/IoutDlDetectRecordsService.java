package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outDlDetectRecords;

/**
 * 定量监测结果汇总Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutDlDetectRecordsService 
{
    /**
     * 查询定量监测结果汇总
     * 
     * @param recordDlId 定量监测结果汇总主键
     * @return 定量监测结果汇总
     */
    public outDlDetectRecords selectoutDlDetectRecordsByRecordDlId(Long recordDlId);

    /**
     * 查询定量监测结果汇总列表
     * 
     * @param outDlDetectRecords 定量监测结果汇总
     * @return 定量监测结果汇总集合
     */
    public List<outDlDetectRecords> selectoutDlDetectRecordsList(outDlDetectRecords outDlDetectRecords);

    /**
     * 新增定量监测结果汇总
     * 
     * @param outDlDetectRecords 定量监测结果汇总
     * @return 结果
     */
    public int insertoutDlDetectRecords(outDlDetectRecords outDlDetectRecords);

    /**
     * 修改定量监测结果汇总
     * 
     * @param outDlDetectRecords 定量监测结果汇总
     * @return 结果
     */
    public int updateoutDlDetectRecords(outDlDetectRecords outDlDetectRecords);

    /**
     * 批量删除定量监测结果汇总
     * 
     * @param recordDlIds 需要删除的定量监测结果汇总主键集合
     * @return 结果
     */
    public int deleteoutDlDetectRecordsByRecordDlIds(Long[] recordDlIds);

    /**
     * 删除定量监测结果汇总信息
     * 
     * @param recordDlId 定量监测结果汇总主键
     * @return 结果
     */
    public int deleteoutDlDetectRecordsByRecordDlId(Long recordDlId);
}
