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
    public List<agriCitySampleTestDetails> getCitySampleResultList(agriCitySampleTestDetails agriCitySampleTestDetails);
}
