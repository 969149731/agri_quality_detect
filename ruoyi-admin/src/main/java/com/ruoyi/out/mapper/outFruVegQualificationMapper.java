package com.ruoyi.out.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegQualification;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 各类蔬菜水果合格率情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Repository
public interface outFruVegQualificationMapper 
{

    public List<outFruVegSelectType> getFruVegDetResultList(agriCitySampleTestDetails agriCitySampleTestDetails);

    public List<agriPesticideResidueStandard> getagriPesticideResidueStandard(String pesticideName, String vegFruName);

    public List<String> getFruSubType();
    public List<String> getVegSubType();

}
