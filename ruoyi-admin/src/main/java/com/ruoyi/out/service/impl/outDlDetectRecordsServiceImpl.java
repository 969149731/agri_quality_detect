package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outDlDetectRecordsMapper;
import com.ruoyi.out.domain.outDlDetectRecords;
import com.ruoyi.out.service.IoutDlDetectRecordsService;

/**
 * 定量监测结果汇总Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outDlDetectRecordsServiceImpl implements IoutDlDetectRecordsService 
{
    @Autowired
    private outDlDetectRecordsMapper outDlDetectRecordsMapper;

    /**
     * 查询定量监测结果汇总
     * 
     * @param recordDlId 定量监测结果汇总主键
     * @return 定量监测结果汇总
     */
    @Override
    public outDlDetectRecords selectoutDlDetectRecordsByRecordDlId(Long recordDlId)
    {
        return outDlDetectRecordsMapper.selectoutDlDetectRecordsByRecordDlId(recordDlId);
    }

    /**
     * 查询定量监测结果汇总列表
     * 
     * @param outDlDetectRecords 定量监测结果汇总
     * @return 定量监测结果汇总
     */
    @Override
    public List<outDlDetectRecords> selectoutDlDetectRecordsList(outDlDetectRecords outDlDetectRecords)
    {
        return outDlDetectRecordsMapper.selectoutDlDetectRecordsList(outDlDetectRecords);
    }

    /**
     * 新增定量监测结果汇总
     * 
     * @param outDlDetectRecords 定量监测结果汇总
     * @return 结果
     */
    @Override
    public int insertoutDlDetectRecords(outDlDetectRecords outDlDetectRecords)
    {
        return outDlDetectRecordsMapper.insertoutDlDetectRecords(outDlDetectRecords);
    }

    /**
     * 修改定量监测结果汇总
     * 
     * @param outDlDetectRecords 定量监测结果汇总
     * @return 结果
     */
    @Override
    public int updateoutDlDetectRecords(outDlDetectRecords outDlDetectRecords)
    {
        return outDlDetectRecordsMapper.updateoutDlDetectRecords(outDlDetectRecords);
    }

    /**
     * 批量删除定量监测结果汇总
     * 
     * @param recordDlIds 需要删除的定量监测结果汇总主键
     * @return 结果
     */
    @Override
    public int deleteoutDlDetectRecordsByRecordDlIds(Long[] recordDlIds)
    {
        return outDlDetectRecordsMapper.deleteoutDlDetectRecordsByRecordDlIds(recordDlIds);
    }

    /**
     * 删除定量监测结果汇总信息
     * 
     * @param recordDlId 定量监测结果汇总主键
     * @return 结果
     */
    @Override
    public int deleteoutDlDetectRecordsByRecordDlId(Long recordDlId)
    {
        return outDlDetectRecordsMapper.deleteoutDlDetectRecordsByRecordDlId(recordDlId);
    }
}
