package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.outSampleQuality;

/**
 * 各抽样环节合格率情况Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutSampleQualityService 
{
    /**
     * 查询各抽样环节合格率情况列表
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 各抽样环节合格率情况集合
     */
    public List<outSampleQuality> selectoutSampleQualityList(agriCitySampleTestDetails agriCitySampleTestDetails);
}
