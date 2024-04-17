package com.ruoyi.out.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.outFruBanPesDetRecords;

import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 水果禁用农药检出及超标情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Repository
public interface outFruPesDetRecordsMapper
{
    //获取禁用农药列表
    public List<String> getFruBanPesticideList();
    public List<String> getFruNoBanPesticideList();

    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticideName, String vegFruName);

    public List<outFruVegSelectType> getFruVegDetResultList(agriCitySampleTestDetails agriCitySampleTestDetails);
}
