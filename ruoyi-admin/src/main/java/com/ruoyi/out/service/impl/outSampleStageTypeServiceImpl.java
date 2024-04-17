package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType2;
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
     * 查询被抽样环节数量统计列表
     * 
     * @param agriCitySampleTestDetails 被抽样环节数量统计
     * @return 被抽样环节数量统计
     */
    @Override
    public List<outSampleStageType> selectoutSampleStageTypeList(agriCitySampleTestDetails agriCitySampleTestDetails)
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

        //仅对不同的单位进行计数，所以要记录所有已出现的单位
        Map<String, List<String>> StageTypeUnitMap =new TreeMap<>();//存放各个类型生产环节的表格
        for (String type : StageType){
            StageTypeUnitMap.put(type,new ArrayList<>());
        }

        //获取所有样本
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<agriCitySampleTestDetails> SampleList= outSampleStageTypeMapper.getCitySampleResultList(agriCitySampleTestDetails);
        //查出来就是一个样本而不是一个农药条目
        if(SampleList.isEmpty()){
            System.out.println("样本列表为空");
            return returnFinalList(resultMap,resultList,StageType);
            }
        for(agriCitySampleTestDetails sample:SampleList){
            compute(resultMap,sample,StageType,StageTypeUnitMap);
        }

        //Map的内容传入结果列表
        return returnFinalList(resultMap,resultList,StageType);
    }
    public void compute(Map<String, outSampleStageType>  resultMap,agriCitySampleTestDetails sample,List<String> StageType, Map<String, List<String>> StageTypeUnitMap){//按一个检测样本进行统计
        try {
                switch (checkIsUseful(sample,StageType)){
                    case 1://必要条件不足
                        return;//下一个样本
                    case 2://生产环节不在检测范围中
                        return;
                    case -1://错误
                        return;
                    case 0:
                        break;//通过
                }
                //统计
                String itemStageTyep = sample.getSamplingStageType();
                String unitName = sample.getSamplingLocation();
                if(!StageTypeUnitMap.get(itemStageTyep).contains(unitName)){
                    resultMap.get(itemStageTyep).addOneToUnitNum();//对应生产环节单位数目+1
                    StageTypeUnitMap.get(itemStageTyep).add(unitName);//将新的单位计入
                }
        }catch (Exception e){

            System.out.println("捕获异常"+e.getMessage());
        }

    }
    public int checkIsUseful(agriCitySampleTestDetails sample,List<String> StageType){
        try{
            if (sample.getSamplingStageType()==null){
                System.out.println("该条目没有相应生产环节信息 生产环节"+sample.getSamplingStageType()+" 样本编号:"+sample.getSampleCode());
                return 1;
            }

            String StageName = sample.getSamplingStageType();
            //先数据预处理
            for (String Name : StageType){
                if (StageName.contains(Name)){
                    sample.setSamplingStageType(Name);//规范一下输入
                }
            }

            if (!StageType.contains(sample.getSamplingStageType())){
                System.out.println("生产环节不在统计列表中 当前样本生产环节为："+sample.getSamplingStageType()+" 样本编号:"+sample.getSampleCode());
                return 2;
            }
        }catch (Exception e){
            return -1;//有异常
        }
        return 0;//到此说明数据可用
    }

    public List<outSampleStageType> returnFinalList(Map<String, outSampleStageType> resultMap,List<outSampleStageType> resultList,
                                                  List<String> StageType){
        //把Map里的东西装进去
        outSampleStageType allCount = new outSampleStageType("合计");
        allCount.setStageIncludeType("合计");
        for(String StageTypeName :StageType){
            resultList.add(resultMap.get(StageTypeName));
            allCount.addTotalTogether(resultMap.get(StageTypeName));
        }
        resultList.add(allCount);
        System.out.println("打印结果列表"+resultList);
        return resultList;
    }
}
