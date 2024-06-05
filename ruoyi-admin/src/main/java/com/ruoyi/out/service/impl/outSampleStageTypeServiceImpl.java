package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.out.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outSampleStageTypeMapper;
import com.ruoyi.out.service.IoutSampleStageTypeService;
import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;
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
    //////业务模块全局变量

    List<outSampleStageType> resultList;
    Map<String, outSampleStageType> resultMap;
    List<String> StageType= Arrays.asList( "生产基地","无公害产品基地","地标生产基地","绿色产品基地","有机产品基地","散户","其它基地","批发市场","运输车","其它");//合计在最后加入
    List<String> StageIncludingType= Arrays.asList("无公害产品基地", "地标生产基地","绿色产品基地","有机产品基地","散户","其它基地");
    List<String> StageTypeForCount = Arrays.asList( "生产基地","批发市场","运输车","其它");
    Map<String, List<String>> StageTypeUnitMap;
    returnMsgHandler MsgHandler = new returnMsgHandler();
    /**
     * 查询被抽样环节数量统计列表
     * 
     * @param agriCitySampleTestDetails 被抽样环节数量统计
     * @return 被抽样环节数量统计
     */
    @Override
    public List<outSampleStageType> selectoutSampleStageTypeList(agriCitySampleTestDetails agriCitySampleTestDetails,StringBuilder feedBackMsg)
    {
        if (initModule(feedBackMsg));
        else return returnFinalList();


        //获取所有样本
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<agriCitySampleTestDetails> SampleList= outSampleStageTypeMapper.getCitySampleResultList(agriCitySampleTestDetails);
        //查出来就是一个样本而不是一个农药条目
        if(SampleList.isEmpty()){
            log.debug("样本列表为空");
            return returnFinalList();
            }
        for(agriCitySampleTestDetails sample:SampleList){
            compute(sample);
        }

        //Map的内容传入结果列表
        return returnFinalList();
    }
    public boolean initModule(StringBuilder feedBackMsg){
        try {
            MsgHandler.initReturnMsg(feedBackMsg);
            resultList=new ArrayList<>();
            //结果列表Map
            resultMap = new TreeMap<>();//使用字典存储
            for(String typeName:StageType){
                outSampleStageType newOne=new outSampleStageType(typeName);
                if (StageIncludingType.contains(typeName)){
                    newOne.setStageIncludeType("其中");
                }
                else{newOne.setStageIncludeType(typeName);}
                resultMap.put(typeName,newOne);
            }

            //仅对不同的单位进行计数，所以要记录所有已出现的单位
            StageTypeUnitMap =new TreeMap<>();//存放各个类型生产环节的表格
            for (String type : StageType){
                StageTypeUnitMap.put(type,new ArrayList<>());
            }
            return true;
        }catch (Exception e)
        {
            MsgHandler.addMsg("错误:初始化失败","在初始化模块时遇到未知错误");
            log.error("初始化时遇到错误",e);
            return false;
        }
    }
    public void compute(agriCitySampleTestDetails sample){//按一个检测样本进行统计
        try {
                switch (checkIsUseful(sample)){
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
            log.error("捕获异常",e);
        }

    }
    public int checkIsUseful(agriCitySampleTestDetails sample){
        try{
            //注意生产基地不要放前面，否则先识别出来其他的生产基地子类就无法识别了
            //与全局的不同，生产基地比较特殊，要先去除
            List<String> StageType= Arrays.asList( "无公害产品基地","地标生产基地","绿色产品基地","有机产品基地","散户","其他基地","批发市场","运输车");//生产环节类型
            if (sample.getSamplingStageType()==null){
                MsgHandler.addMsg("下列样本没有相应生产环节信息"+" 样本编号:"+sample.getSampleCode()+"生产环节"+sample.getSamplingStageType());
                return 1;//为空无法操作，后续进行检查时会报告错误
            }
            String StageName = sample.getSamplingStageType();
            if(StageName.equals("生产基地")){ //生产基地比较特殊//有很多会直接用生产基地
                return 0;
            }
            //////////循环判断
            for (String item : StageType){
                if(StageName.contains(item)){
                    sample.setSamplingStageType(item);//将所有数据清洗为规范格式
                    return 0;//找到一个即可返回
                }
            }
            //////////
            if(StageName.contains("生产基地")){//不是上述类型，但是包含生产基地，应为其它基地类型
                sample.setSamplingStageType("其它基地");
                return 0;
            }
            sample.setSamplingStageType("其它");//以上都不是，则为其它
            return 0;//到此说明数据可用
        }catch (Exception e){
            log.error("捕获异常",e);
            return -1;//有异常
        }
    }

    public List<outSampleStageType> returnFinalList(){
        //计算生产基地
        outSampleStageType ProduceBasementCount = resultMap.get("生产基地");
        for(String StageTypeName :StageIncludingType){
            ProduceBasementCount.addTotalTogether(resultMap.get(StageTypeName));
        }

        //计算合计
        outSampleStageType allCount = new outSampleStageType("合计");
        allCount.setStageIncludeType("合计");
        for(String StageTypeName :StageTypeForCount){
            allCount.addTotalTogether(resultMap.get(StageTypeName));
        }

        //写入返回列表
        for (String StageTypeName :StageType){
            resultList.add(resultMap.get(StageTypeName));
        }
        resultList.add(allCount);
        MsgHandler.turnToStr();
        return resultList;
    }
}
