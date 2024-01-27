package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outSampleStageTypeMapper;
import com.ruoyi.out.domain.outSampleStageType;
import com.ruoyi.out.service.IoutSampleStageTypeService;

/**
 * 被抽样环节数量统计Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outSampleStageTypeServiceImpl implements IoutSampleStageTypeService 
{
    @Autowired
    private outSampleStageTypeMapper outSampleStageTypeMapper;

    /**
     * 查询被抽样环节数量统计
     * 
     * @param sampleQualityId 被抽样环节数量统计主键
     * @return 被抽样环节数量统计
     */
    @Override
    public outSampleStageType selectoutSampleStageTypeBySampleQualityId(Long sampleQualityId)
    {
        return outSampleStageTypeMapper.selectoutSampleStageTypeBySampleQualityId(sampleQualityId);
    }

    /**
     * 查询被抽样环节数量统计列表
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 被抽样环节数量统计
     */
    @Override
    public List<outSampleStageType> selectoutSampleStageTypeList(outSampleStageType outSampleStageType)
    {
        return outSampleStageTypeMapper.selectoutSampleStageTypeList(outSampleStageType);
    }

    /**
     * 新增被抽样环节数量统计
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 结果
     */
    @Override
    public int insertoutSampleStageType(outSampleStageType outSampleStageType)
    {
        return outSampleStageTypeMapper.insertoutSampleStageType(outSampleStageType);
    }

    /**
     * 修改被抽样环节数量统计
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 结果
     */
    @Override
    public int updateoutSampleStageType(outSampleStageType outSampleStageType)
    {
        return outSampleStageTypeMapper.updateoutSampleStageType(outSampleStageType);
    }

    /**
     * 批量删除被抽样环节数量统计
     * 
     * @param sampleQualityIds 需要删除的被抽样环节数量统计主键
     * @return 结果
     */
    @Override
    public int deleteoutSampleStageTypeBySampleQualityIds(Long[] sampleQualityIds)
    {
        return outSampleStageTypeMapper.deleteoutSampleStageTypeBySampleQualityIds(sampleQualityIds);
    }

    /**
     * 删除被抽样环节数量统计信息
     * 
     * @param sampleQualityId 被抽样环节数量统计主键
     * @return 结果
     */
    @Override
    public int deleteoutSampleStageTypeBySampleQualityId(Long sampleQualityId)
    {
        return outSampleStageTypeMapper.deleteoutSampleStageTypeBySampleQualityId(sampleQualityId);
    }
}
