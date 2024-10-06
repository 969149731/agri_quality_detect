package com.ruoyi.out.service.impl;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.myUtils.AgriUtils;
import com.ruoyi.out.domain.dto.OutPesDetRecordsDto;
import com.ruoyi.out.domain.vo.OutPesDetRecordsVo;
import com.ruoyi.out.mapper.outPesDetRecordsMapper;
import com.ruoyi.out.service.IOutPesDetRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-07-16
 */
@Service
public class outPesDetRecordsServiceImpl implements IOutPesDetRecordsService
{
    @Autowired
    private outPesDetRecordsMapper outPesDetRecordsMapper;


    @Override
    public List<OutPesDetRecordsVo> selectOutPesDetRecords(agriCitySampleTestDetails agriCitySampleTestDetails,
                                                           StringBuilder feedBackMsg,String SampleType,String isPermit) {
        AgriUtils.permissionToDifferentiateData(agriCitySampleTestDetails);
        List<OutPesDetRecordsDto> outPesDetRecordsDtoList = outPesDetRecordsMapper.selectOutPesDetRecords(agriCitySampleTestDetails,feedBackMsg,SampleType,isPermit);
        // 创建一个Map来存储统计数据
        Map<String, OutPesDetRecordsVo> statsMap = new HashMap<>();
        // 因为最后有合计项，所以单独搞一个合计的OutPesDetRecordsVo对象，最后放入到list中
        OutPesDetRecordsVo totalStats = new OutPesDetRecordsVo();
        totalStats.setPesticideName("合计");

        for (OutPesDetRecordsDto record : outPesDetRecordsDtoList) {
            String pesticideName = record.getPesticideName();
            String stageType = record.getStdStageType();
            Integer chkNum = record.getChkNum();
            Integer notStdNum = record.getNotStdNum();

            // 获取statsMap中key为pesticideName的值，如果不存在，则创建一个新的OutPesDetRecordsVo对象，并将该对象存入statsMap中
            OutPesDetRecordsVo outPesDetRecordsVo = statsMap.computeIfAbsent(pesticideName, k -> new OutPesDetRecordsVo());
            outPesDetRecordsVo.setPesticideName(pesticideName);
            outPesDetRecordsVo.setTotalDet(outPesDetRecordsVo.getTotalDet() + chkNum);
            outPesDetRecordsVo.setTotalEx(outPesDetRecordsVo.getTotalEx() + notStdNum);

            totalStats.setTotalDet(totalStats.getTotalDet() + chkNum);
            totalStats.setTotalEx(totalStats.getTotalEx() + notStdNum);

            switch (stageType) {
                case "生产基地":
                    outPesDetRecordsVo.setProductBase(outPesDetRecordsVo.getProductBase() + chkNum);
                    outPesDetRecordsVo.setProductBaseEx(outPesDetRecordsVo.getProductBaseEx() + notStdNum);
                    totalStats.setProductBase(totalStats.getProductBase() + chkNum);
                    totalStats.setProductBaseEx(totalStats.getProductBaseEx() + notStdNum);
                    break;
                case "批发市场":
                    outPesDetRecordsVo.setMarket(outPesDetRecordsVo.getMarket() + chkNum);
                    outPesDetRecordsVo.setMarketEx(outPesDetRecordsVo.getMarketEx() + notStdNum);
                    totalStats.setMarket(totalStats.getMarket() + chkNum);
                    totalStats.setMarketEx(totalStats.getMarketEx() + notStdNum);
                    break;
                case "运输车":
                    outPesDetRecordsVo.setVehicle(outPesDetRecordsVo.getVehicle() + chkNum);
                    outPesDetRecordsVo.setVehicleEx(outPesDetRecordsVo.getVehicleEx() + notStdNum);
                    totalStats.setVehicle(totalStats.getVehicle() + chkNum);
                    totalStats.setVehicleEx(totalStats.getVehicleEx() + notStdNum);
                    break;
                case "其它":
                    outPesDetRecordsVo.setOther(outPesDetRecordsVo.getOther() + chkNum);
                    outPesDetRecordsVo.setOtherEx(outPesDetRecordsVo.getOtherEx() + notStdNum);
                    totalStats.setOther(totalStats.getOther() + chkNum);
                    totalStats.setOtherEx(totalStats.getOtherEx() + notStdNum);
                    break;
            }
        }

        //将statsMap中的所有值添加到resultList中,也就是把map转成list的操作
        List<OutPesDetRecordsVo> resultList = new ArrayList<>(statsMap.values());
        resultList.add(totalStats);
        return resultList;
    }

}
