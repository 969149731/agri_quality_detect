package com.ruoyi.out.mapper;

import java.util.List;
import com.ruoyi.out.domain.outDlDetectRecords;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 定量监测结果汇总Mapper接口
 *
 * @author chenjie
 * @date 2024-01-27
 */
@Repository
public interface outDlDetectRecordsMapper
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
     * 删除定量监测结果汇总
     *
     * @param recordDlId 定量监测结果汇总主键
     * @return 结果
     */
    public int deleteoutDlDetectRecordsByRecordDlId(Long recordDlId);

    /**
     * 批量删除定量监测结果汇总
     *
     * @param recordDlIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutDlDetectRecordsByRecordDlIds(Long[] recordDlIds);

    public String checkSampleType(String sampleName);


    public Double selectLimitValueByPesticideName(@Param("pesticideName") String pesticideName, @Param("vegFruName") String vegFruName, @Param("standardCategory") String standardCategory);

}
