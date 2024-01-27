package com.ruoyi.out.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outSampleQualityMapper;
import com.ruoyi.out.domain.outSampleQuality;
import com.ruoyi.out.service.IoutSampleQualityService;

/**
 * 各抽样环节合格率情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outSampleQualityServiceImpl implements IoutSampleQualityService 
{
    @Autowired
    private outSampleQualityMapper outSampleQualityMapper;

    /**
     * 查询各抽样环节合格率情况
     * 
     * @param sampleQualityId 各抽样环节合格率情况主键
     * @return 各抽样环节合格率情况
     */
    @Override
    public outSampleQuality selectoutSampleQualityBySampleQualityId(Long sampleQualityId)
    {
        return outSampleQualityMapper.selectoutSampleQualityBySampleQualityId(sampleQualityId);
    }

    /**
     * 查询各抽样环节合格率情况列表
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 各抽样环节合格率情况
     */
    @Override
    public List<outSampleQuality> selectoutSampleQualityList(outSampleQuality outSampleQuality)
    {
        return outSampleQualityMapper.selectoutSampleQualityList(outSampleQuality);
    }

    /**
     * 新增各抽样环节合格率情况
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 结果
     */
    @Override
    public int insertoutSampleQuality(outSampleQuality outSampleQuality)
    {
        return outSampleQualityMapper.insertoutSampleQuality(outSampleQuality);
    }

    /**
     * 修改各抽样环节合格率情况
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 结果
     */
    @Override
    public int updateoutSampleQuality(outSampleQuality outSampleQuality)
    {
        return outSampleQualityMapper.updateoutSampleQuality(outSampleQuality);
    }

    /**
     * 批量删除各抽样环节合格率情况
     * 
     * @param sampleQualityIds 需要删除的各抽样环节合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutSampleQualityBySampleQualityIds(Long[] sampleQualityIds)
    {
        return outSampleQualityMapper.deleteoutSampleQualityBySampleQualityIds(sampleQualityIds);
    }

    /**
     * 删除各抽样环节合格率情况信息
     * 
     * @param sampleQualityId 各抽样环节合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutSampleQualityBySampleQualityId(Long sampleQualityId)
    {
        return outSampleQualityMapper.deleteoutSampleQualityBySampleQualityId(sampleQualityId);
    }
}
