package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.out.domain.outSampleQuality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outSampleStageTypeMapper;
import com.ruoyi.out.domain.outSampleStageType;
import com.ruoyi.out.service.IoutSampleStageTypeService;

/**
 * 被抽样环节数量统计Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outSampleStageTypeServiceImpl implements IoutSampleStageTypeService 
{
    @Autowired
    private outSampleStageTypeMapper outSampleStageTypeMapper;

    /**
     * 查询被抽样环节数量统计
     * 
     * @param sampleQualityId 被抽样环节数量统计主键
     * @return 被抽样环节数量统计
     */
    @Override
    public outSampleStageType selectoutSampleStageTypeBySampleQualityId(Long sampleQualityId)
    {
        return outSampleStageTypeMapper.selectoutSampleStageTypeBySampleQualityId(sampleQualityId);
    }

    /**
     * 查询被抽样环节数量统计列表
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 被抽样环节数量统计
     */
    @Override
    public List<outSampleStageType> selectoutSampleStageTypeList(outSampleStageType outSampleStageType)
    {
        List<outSampleStageType> resultList=new ArrayList<>();

        //结果列表Map
        List<String> StageType= Arrays.asList("生产基地", "无公害产品基地","地标生产基地","绿色产品基地","有机产品基地","散户","其他基地","批发市场","运输车");//合计在最后加入
        List<String> StageIncludingType= Arrays.asList("无公害产品基地", "地标生产基地","绿色产品基地","有机产品基地","散户","其他基地");
        Map<String, outSampleStageType> resultMap = new TreeMap<String, outSampleStageType>();//使用字典存储
        for(String typeName:StageType){
            outSampleStageType newOne=new outSampleStageType(typeName);
            if (StageIncludingType.contains(typeName)){
                newOne.setStageIncludeType("其中");
            }
            else{newOne.setStageIncludeType(typeName);}
            resultMap.put(typeName,newOne);
        }
//        private Long landmarkProductBase;
//        private Long greenProductBase;
//        private Long organicProductBase;
//        private Long individualHousehold;
//        private Long otherBase;

        //仅对不同的单位进行计数，所以要记录所有已出现的单位
        List<String> productionBaseList=new ArrayList<>();//生产基地逻辑上为各类基地之和
        List<String> pollutionFreeBaseList=new ArrayList<>();
        List<String> landmarkProductBaseList=new ArrayList<>();
        List<String> greenProductBaseList=new ArrayList<>();
        List<String> organicProductBaseList=new ArrayList<>();
        List<String> individualHouseholdList=new ArrayList<>();
        List<String> otherBaseList=new ArrayList<>();

        List<String> wholesaleMarketList=new ArrayList<>();
        List<String> transportVehicleList=new ArrayList<>();

        //获取所有样本
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<agriCitySampleTestDetails> SampleList= outSampleStageTypeMapper.getCitySampleResultList(outSampleStageType.getParams());
        if(SampleList.isEmpty()){System.out.println("样本列表为空");}
        for(agriCitySampleTestDetails sample:SampleList){
            if (sample.getSampleCode().equals("2024R1047")){
                System.out.println("我在崇左");
            }
            if (sample.getSamplingStageType()==null){
//                System.out.println("该条目没有相应生产环节信息 生产环节"+sample.getSamplingStageType());
                continue;
            }
            if (!StageType.contains(sample.getSamplingStageType())){
//                System.out.println("生产环节不在统计列表中 当前样本生产环节为："+sample.getSamplingStageType());
                continue;
            }
            //统计
            switch (sample.getSamplingStageType()){//分情况统计
                case "生产基地":
                    if (productionBaseList.contains(sample.getSamplingLocation())){}
                    else {resultMap.get("生产基地").addOneToUnitNum();productionBaseList.add(sample.getSamplingLocation());}
                    break;
                case "无公害产品基地":
                    if (pollutionFreeBaseList.contains(sample.getSamplingLocation())){}
                    else {resultMap.get("无公害产品基地").addOneToUnitNum();pollutionFreeBaseList.add(sample.getSamplingLocation());}
                    break;
                case "地标生产基地":
                    if (landmarkProductBaseList.contains(sample.getSamplingLocation())){}
                    else {resultMap.get("地标生产基地").addOneToUnitNum();landmarkProductBaseList.add(sample.getSamplingLocation());}
                    break;
                case "绿色产品基地":
                    if (greenProductBaseList.contains(sample.getSamplingLocation())){}
                    else {resultMap.get("无公害产品基地").addOneToUnitNum();greenProductBaseList.add(sample.getSamplingLocation());}
                    break;
                case "有机产品基地":
                    if (organicProductBaseList.contains(sample.getSamplingLocation())){}
                    else {resultMap.get("有机产品基地").addOneToUnitNum();organicProductBaseList.add(sample.getSamplingLocation());}
                    break;
                case "散户":
                    if (individualHouseholdList.contains(sample.getSamplingLocation())){}
                    else {resultMap.get("散户").addOneToUnitNum();individualHouseholdList.add(sample.getSamplingLocation());}
                    break;
                case "其他基地":
                    if (otherBaseList.contains(sample.getSamplingLocation())){}
                    else {resultMap.get("其他基地").addOneToUnitNum();otherBaseList.add(sample.getSamplingLocation());}
                    break;
                case "批发市场":
                    if (wholesaleMarketList.contains(sample.getSamplingLocation())){}
                    else {resultMap.get("批发市场").addOneToUnitNum();wholesaleMarketList.add(sample.getSamplingLocation());}
                    break;
                case "运输车":
                    if (transportVehicleList.contains(sample.getSamplingLocation())){}
                    else {resultMap.get("运输车").addOneToUnitNum();transportVehicleList.add(sample.getSamplingLocation());}
                    break;
            }
        }
        //Map的内容传入结果列表
        outSampleStageType allCount = new outSampleStageType("合计");
        allCount.setStageIncludeType("合计");
        for(String StageTypeName :StageType){
            resultList.add(resultMap.get(StageTypeName));
            allCount.addTotalTogether(resultMap.get(StageTypeName));
        }
        resultList.add(allCount);
//        System.out.println("打印结果列表"+resultList);
        return resultList;
    }

    /**
     * 新增被抽样环节数量统计
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 结果
     */
    @Override
    public int insertoutSampleStageType(outSampleStageType outSampleStageType)
    {
        return outSampleStageTypeMapper.insertoutSampleStageType(outSampleStageType);
    }

    /**
     * 修改被抽样环节数量统计
     * 
     * @param outSampleStageType 被抽样环节数量统计
     * @return 结果
     */
    @Override
    public int updateoutSampleStageType(outSampleStageType outSampleStageType)
    {
        return outSampleStageTypeMapper.updateoutSampleStageType(outSampleStageType);
    }

    /**
     * 批量删除被抽样环节数量统计
     * 
     * @param sampleQualityIds 需要删除的被抽样环节数量统计主键
     * @return 结果
     */
    @Override
    public int deleteoutSampleStageTypeBySampleQualityIds(Long[] sampleQualityIds)
    {
        return outSampleStageTypeMapper.deleteoutSampleStageTypeBySampleQualityIds(sampleQualityIds);
    }

    /**
     * 删除被抽样环节数量统计信息
     * 
     * @param sampleQualityId 被抽样环节数量统计主键
     * @return 结果
     */
    @Override
    public int deleteoutSampleStageTypeBySampleQualityId(Long sampleQualityId)
    {
        return outSampleStageTypeMapper.deleteoutSampleStageTypeBySampleQualityId(sampleQualityId);
    }
}
