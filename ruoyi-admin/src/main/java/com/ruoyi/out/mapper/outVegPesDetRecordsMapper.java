package com.ruoyi.out.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outVegBanPesDetRecords;
import org.apache.ibatis.annotations.Param;

/**
 * 蔬菜禁用农药检出及超标情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface outVegPesDetRecordsMapper
{

    public List<String> getVegBanPesticideList();
    public List<String> getVegBanNoPesticideList();
    public List<outFruVegSelectType> getFruVegDetResultList(agriCitySampleTestDetails agriCitySampleTestDetails);
    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticidName, String vegFruName);
}
