package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outDxDetectRecordsMapper;
import com.ruoyi.out.domain.outDxDetectRecords;
import com.ruoyi.out.service.IoutDxDetectRecordsService;

/**
 * 定性监测结果汇总Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outDxDetectRecordsServiceImpl implements IoutDxDetectRecordsService 
{
    @Autowired
    private outDxDetectRecordsMapper outDxDetectRecordsMapper;

    /**
     * 查询定性监测结果汇总
     * 
     * @param recordDxId 定性监测结果汇总主键
     * @return 定性监测结果汇总
     */
    @Override
    public outDxDetectRecords selectoutDxDetectRecordsByRecordDxId(Long recordDxId)
    {
        return outDxDetectRecordsMapper.selectoutDxDetectRecordsByRecordDxId(recordDxId);
    }

    /**
     * 查询定性监测结果汇总列表
     * 
     * @param outDxDetectRecords 定性监测结果汇总
     * @return 定性监测结果汇总
     */
    @Override
    public List<outDxDetectRecords> selectoutDxDetectRecordsList(outDxDetectRecords outDxDetectRecords)
    {
        return outDxDetectRecordsMapper.selectoutDxDetectRecordsList(outDxDetectRecords);
    }

    /**
     * 新增定性监测结果汇总
     * 
     * @param outDxDetectRecords 定性监测结果汇总
     * @return 结果
     */
    @Override
    public int insertoutDxDetectRecords(outDxDetectRecords outDxDetectRecords)
    {
        return outDxDetectRecordsMapper.insertoutDxDetectRecords(outDxDetectRecords);
    }

    /**
     * 修改定性监测结果汇总
     * 
     * @param outDxDetectRecords 定性监测结果汇总
     * @return 结果
     */
    @Override
    public int updateoutDxDetectRecords(outDxDetectRecords outDxDetectRecords)
    {
        return outDxDetectRecordsMapper.updateoutDxDetectRecords(outDxDetectRecords);
    }

    /**
     * 批量删除定性监测结果汇总
     * 
     * @param recordDxIds 需要删除的定性监测结果汇总主键
     * @return 结果
     */
    @Override
    public int deleteoutDxDetectRecordsByRecordDxIds(Long[] recordDxIds)
    {
        return outDxDetectRecordsMapper.deleteoutDxDetectRecordsByRecordDxIds(recordDxIds);
    }

    /**
     * 删除定性监测结果汇总信息
     * 
     * @param recordDxId 定性监测结果汇总主键
     * @return 结果
     */
    @Override
    public int deleteoutDxDetectRecordsByRecordDxId(Long recordDxId)
    {
        return outDxDetectRecordsMapper.deleteoutDxDetectRecordsByRecordDxId(recordDxId);
    }
}
