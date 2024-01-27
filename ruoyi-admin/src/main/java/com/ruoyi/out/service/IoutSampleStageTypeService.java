package com.ruoyi.out.service;

import java.util.List;
import com.ruoyi.out.domain.outSampleStageType;

/**
 * 被抽样环节数量统计Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutSampleStageTypeService 
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
     * 批量删除被抽样环节数量统计
     * 
     * @param sampleQualityIds 需要删除的被抽样环节数量统计主键集合
     * @return 结果
     */
    public int deleteoutSampleStageTypeBySampleQualityIds(Long[] sampleQualityIds);

    /**
     * 删除被抽样环节数量统计信息
     * 
     * @param sampleQualityId 被抽样环节数量统计主键
     * @return 结果
     */
    public int deleteoutSampleStageTypeBySampleQualityId(Long sampleQualityId);
}
