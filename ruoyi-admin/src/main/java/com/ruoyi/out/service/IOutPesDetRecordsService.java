package com.ruoyi.out.service;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.vo.OutPesDetRecordsVo;

import java.util.List;

public interface IOutPesDetRecordsService {
    List<OutPesDetRecordsVo> selectOutPesDetRecords(agriCitySampleTestDetails agriCitySampleTestDetails,
                                                    StringBuilder feedBackMsg,String SampleType,String isPermit);
}
