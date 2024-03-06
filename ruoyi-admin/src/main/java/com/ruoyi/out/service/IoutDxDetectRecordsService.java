package com.ruoyi.out.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.detection.domain.agriOut2CitySampleTestDetails;
import com.ruoyi.out.domain.out2DxDetectRecords;
import com.ruoyi.out.domain.outDxDetectRecords;
import org.springframework.web.multipart.MultipartFile;

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


    public String importDxDetectRecordsService(MultipartFile file,List<out2DxDetectRecords> outDxDetectRecords, boolean updateSupport, String operName) throws IOException;

    List<Map<String, out2DxDetectRecords>> getQuarterlyStatistics(Integer  year, Integer  month1, Integer  month2, Integer  month3);
}
