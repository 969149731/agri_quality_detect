package com.ruoyi.out.mapper;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.dto.OutPesDetRecordsDto;
import com.ruoyi.out.domain.outFruVegSelectType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 蔬菜禁用农药检出及超标情况Mapper接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Repository
public interface outPesDetRecordsMapper
{

    List<OutPesDetRecordsDto> selectOutPesDetRecords(@Param("agriCitySampleTestDetails") agriCitySampleTestDetails agriCitySampleTestDetails,
                                                     @Param("feedBackMsg") StringBuilder feedBackMsg,
                                                     @Param("sampleType") String sampleType,
                                                     @Param("isPermit") String isPermit);

}
