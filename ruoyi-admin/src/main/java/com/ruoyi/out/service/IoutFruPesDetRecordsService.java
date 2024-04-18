package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.outReturnType;

/**
 * 水果禁用农药检出及超标情况Service接口
 * 
 * @author chenjie
 * @date 2024-01-27
 */
public interface IoutFruPesDetRecordsService
{

    /*使用新方法获取列表
    */
    public List<outReturnType> selectoutFruPesDetRecordsList(agriCitySampleTestDetails agriCitySampleTestDetails, String type);
}
