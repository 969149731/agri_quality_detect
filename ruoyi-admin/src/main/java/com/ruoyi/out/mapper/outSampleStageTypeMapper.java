package com.ruoyi.out.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outSampleStageType;
import org.apache.ibatis.annotations.Param;

/**
 * 被抽样环节数量统计Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface outSampleStageTypeMapper 
{
    /**
     * 查询被抽样环节数量统计
     * 
     * @param sampleQualityId 被抽样环节数量统计主键
     * @return 被抽样环节数量统计
     */
    public outSampleStageType selectoutSampleStageTypeBySampleQualityId(Long sampleQualityId);

    /**
     * 查询被抽样环节数量统计列表
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 被抽样环节数量统计集合
     */
    public List<outSampleStageType> selectoutSampleStageTypeList(outSampleStageType outSampleStageType);

    /**
     * 新增被抽样环节数量统计
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 结果
     */
    public int insertoutSampleStageType(outSampleStageType outSampleStageType);

    /**
     * 修改被抽样环节数量统计
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 结果
     */
    public int updateoutSampleStageType(outSampleStageType outSampleStageType);

    /**
     * 删除被抽样环节数量统计
     * 
     * @param sampleQualityId 被抽样环节数量统计主键
     * @return 结果
     */
    public int deleteoutSampleStageTypeBySampleQualityId(Long sampleQualityId);

    /**
     * 批量删除被抽样环节数量统计
     * 
     * @param sampleQualityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutSampleStageTypeBySampleQualityIds(Long[] sampleQualityIds);

    public List<agriCitySampleTestDetails> getCitySampleResultList(@Param("params") Map<String, Object> params);
}
