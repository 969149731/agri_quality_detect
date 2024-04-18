package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.outVegBanPesDetRecords;

/**
 * 蔬菜禁用农药检出及超标情况Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutVegPesDetRecordsService
{
    /*
    * 获取经过处理后的农药返回值列表
    * */
    List<outReturnType> selectoutVegPesDetRecordsList(agriCitySampleTestDetails agriCitySampleTestDetails,String type,StringBuilder feedBackMsg);
}
