package com.ruoyi.out.service;

import java.util.List;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.outReturnType;

/**
 * 茶叶禁用农药检出及超标情况Service接口
 * 
 * @author chenjie
 * @date 2024-04-08
 */
public interface IoutTeaPesDetRecordsService
{
    /**
     * 查询茶叶禁用农药检出及超标情况列表
     * 
     * @param outTeaBanPesDetRecords 茶叶禁用农药检出及超标情况
     * @return 茶叶禁用农药检出及超标情况集合
     */
    public List<outReturnType> selectoutTeaPesDetRecordsList(agriCitySampleTestDetails agriCitySampleTestDetails, String type);

}
