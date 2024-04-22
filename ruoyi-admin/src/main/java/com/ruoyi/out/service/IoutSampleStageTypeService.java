package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
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
     * 查询被抽样环节数量统计列表
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 被抽样环节数量统计集合
     */
    public List<outSampleStageType> selectoutSampleStageTypeList(agriCitySampleTestDetails agriCitySampleTestDetails,StringBuilder feedBackMsg);

}
