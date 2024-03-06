package com.ruoyi.out.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.out.domain.out2DxDetectRecords;
import com.ruoyi.out.domain.outDxDetectRecords;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 定性监测结果汇总Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Repository
public interface outDxDetectRecordsMapper 
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
     * 删除定性监测结果汇总
     * 
     * @param recordDxId 定性监测结果汇总主键
     * @return 结果
     */
    public int deleteoutDxDetectRecordsByRecordDxId(Long recordDxId);

    /**
     * 批量删除定性监测结果汇总
     * 
     * @param recordDxIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutDxDetectRecordsByRecordDxIds(Long[] recordDxIds);

    public int insertOutDxDetectRecords(out2DxDetectRecords outDxDetectRecords);

    @MapKey("detect_location")    //Key应该是结果对象中的一个属性，它将用作Map的键。使用数据库列明
    List<Map<String, out2DxDetectRecords>> getQuarterlyStatistics(
            @Param("year") Integer  year,
            @Param("month1") Integer  month1,
            @Param("month2") Integer  month2,
            @Param("month3") Integer  month3
    );
}
