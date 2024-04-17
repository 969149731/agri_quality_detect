package com.ruoyi.out.mapper;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import org.springframework.stereotype.Repository;

/**
 * 茶叶禁用农药检出及超标情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-04-08
 */
@Repository
public interface outTeaPesDetRecordsMapper
{

    public List<String> getTeaBanPesticideList();//禁用农药
    public List<String> getTeaNoBanPesticideList();//非禁用农药
    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticideName, String vegFruName);

    public List<outFruVegSelectType> getDetResultList(agriCitySampleTestDetails agriCitySampleTestDetails);
}
