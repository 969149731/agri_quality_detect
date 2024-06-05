package com.ruoyi.out.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outFruVegSelectType2;
import com.ruoyi.out.domain.outSampleQuality;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 各抽样环节合格率情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Repository
public interface outSampleQualityMapper 
{
    /**
     * 查询各抽样环节合格率情况
     * 
     * @param sampleQualityId 各抽样环节合格率情况主键
     * @return 各抽样环节合格率情况
     */
    public outSampleQuality selectoutSampleQualityBySampleQualityId(Long sampleQualityId);

    /**
     * 查询各抽样环节合格率情况列表
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 各抽样环节合格率情况集合
     */
    public List<outSampleQuality> selectoutSampleQualityList(outSampleQuality outSampleQuality);

    /**
     * 新增各抽样环节合格率情况
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 结果
     */
    public int insertoutSampleQuality(outSampleQuality outSampleQuality);

    /**
     * 修改各抽样环节合格率情况
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 结果
     */
    public int updateoutSampleQuality(outSampleQuality outSampleQuality);

    /**
     * 删除各抽样环节合格率情况
     * 
     * @param sampleQualityId 各抽样环节合格率情况主键
     * @return 结果
     */
    public int deleteoutSampleQualityBySampleQualityId(Long sampleQualityId);

    /**
     * 批量删除各抽样环节合格率情况
     * 
     * @param sampleQualityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoutSampleQualityBySampleQualityIds(Long[] sampleQualityIds);

    public List<outFruVegSelectType2> getFruVegDetResultList2(agriCitySampleTestDetails agriCitySampleTestDetails);//与其他获取农药结果表不同的是，不限定蔬菜或者水果

    public List<outFruVegSelectType2> getFruVegDetResultList(agriCitySampleTestDetails agriCitySampleTestDetails);//与其他获取农药结果表不同的是，不限定蔬菜或者水果
    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticideName, String vegFruName);
}
