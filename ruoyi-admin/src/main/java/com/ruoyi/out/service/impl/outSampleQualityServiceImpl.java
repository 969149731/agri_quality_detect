package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outSampleQualityMapper;
import com.ruoyi.out.service.IoutSampleQualityService;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 各抽样环节合格率情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outSampleQualityServiceImpl implements IoutSampleQualityService 
{
    @Autowired
    private outSampleQualityMapper outSampleQualityMapper;
    returnMsgHandler MsgHandler = new returnMsgHandler();
    /**
     * 查询各抽样环节合格率情况列表
     * 
     * @param agriCitySampleTestDetails 各抽样环节合格率情况
     * @return 各抽样环节合格率情况
     */
    @Override
    public List<outSampleQuality> selectoutSampleQualityList(agriCitySampleTestDetails agriCitySampleTestDetails,StringBuilder feedBackMsg)
    {
        MsgHandler.initReturnMsg(feedBackMsg);
        //初始化列表
        List<outSampleQuality> resultList = new ArrayList<>();
        List<String> StageType= Arrays.asList("生产基地", "无公害产品基地","地标生产基地","绿色产品基地","有机产品基地","散户","其他基地","批发市场","运输车");//合计在最后加入
        List<String> StageIncludingType= Arrays.asList("无公害产品基地", "地标生产基地","绿色产品基地","有机产品基地","散户","其他基地");
        Map<String, outSampleQuality> resultMap = new TreeMap<>();//使用字典存储
        for(String typeName:StageType){
            outSampleQuality newOne=new outSampleQuality(typeName);
            if (StageIncludingType.contains(typeName)){
                newOne.setStageIncludeType("其中");
            }
            else{newOne.setStageIncludeType(typeName);}
            resultMap.put(typeName,newOne);
        }

        //获取检测结果列表
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<outFruVegSelectType2> SelectList = outSampleQualityMapper.getFruVegDetResultList(agriCitySampleTestDetails);//获取农药检测结果表
        if(SelectList.isEmpty()){
            log.debug("查询出的样本列表为空");
            return returnFinalList(resultMap,resultList,StageType);
        }
        //先遍历所有获取到的结果//以id标识一组检测结果（即一个样本）,所以默认id是存在的，事实上id由数据库生成，肯定存在
        List<outFruVegSelectType2> itemList=new ArrayList<>();//初始化
        Long sampleId = SelectList.get(0).citySampleTestDetailsId;//对于经过编译器生成的列表对象而言，其执行顺序的正确性是保证的，列表的第一个等同于for中执行的第一个
        for (outFruVegSelectType2 item : SelectList) {
            if (item.citySampleTestDetailsId.equals(sampleId)){
                itemList.add(item);
            }
            else{
                compute(resultMap,itemList);
                itemList=new ArrayList<>();//重置
                itemList.add(item);//把当前item加入
                sampleId=item.citySampleTestDetailsId;
            }
        }
        compute(resultMap,itemList);//当仅有一个样本，或是最后一个样本，没有下一个不同的id触发，compute，在此计算

        MsgHandler.turnToStr();
        return returnFinalList(resultMap,resultList,StageType);
    }

    //工具方法
    public List<outSampleQuality> returnFinalList(Map<String, outSampleQuality> resultMap,List<outSampleQuality> resultList,
                                                        List<String> StageType){
        //把Map里的东西装进去
        //合计在此加入
        outSampleQuality allCount = new outSampleQuality("合计");
        allCount.setStageIncludeType("合计");
        for(String StageTypeName :StageType){
            resultList.add(resultMap.get(StageTypeName));
            allCount.addToTotal(resultMap.get(StageTypeName));
        }
        resultList.add(allCount);
        System.out.println("打印结果"+resultList);
        for(outSampleQuality each:resultList){
            each.addToSelfTotal();
            each.computeQualificationRate();//计算
        }
        return resultList;
    }
    public int checkIsUseful(outFruVegSelectType2 item,Map<String, outSampleQuality> resultMap){
        try{
            //情形一。生产环节和蔬果类型称为必须
            if(item.samplingStageType ==null ||item.vegFruType==null){//缺失生产环节或蔬果类型,必要条件不足，无法进行
                MsgHandler.addMsgTitle("信息有误","当前检测样本信息有误,请在定量检测导入表中检查对应内容");
                MsgHandler.addMsg("信息有误"," 样品编号:"+item.sampleCode+" 当前生产环节："+item.samplingStageType+" 当前蔬果类型："+item.vegFruType+"(缺少生产环节或蔬果类型)");
                return 1;}
            //情形二，生产环节不在检测范围内
            if(!resultMap.containsKey(item.samplingStageType)){//不在检测列表中
                MsgHandler.addMsg("信息有误"," 样品编号:"+item.sampleCode+" 当前生产环节类型："+item.samplingStageType+"(当前生产类型不在检查范围中)");
                return 1;}
            //情形四，无蔬菜名，无法查询标准
            if(item.vegFruName==null ||item.vegFruName.equals("") ){//没有对应蔬菜名，整个列表都无法进行超标判断
                MsgHandler.addMsg("信息有误"," 样品编号:"+item.sampleCode+" 蔬菜名:"+item.vegFruName+"(缺少蔬菜名)");
                return 2;
            }
            if(item.pesticideName==null ||item.pesticideName.equals("") ){//没有对应农药名
                MsgHandler.addMsg("信息有误"," 样品编号:"+item.sampleCode+" 农药名:"+item.pesticideName+"(缺少农药名)");
                return 3;
            }
        }catch (Exception e){
            log.error("在数据检查时产生异常",e);
            return -1;//有异常
        }
        return 0;//到此说明数据可用
    }
    public void compute(Map<String, outSampleQuality>  resultMap,List<outFruVegSelectType2> itemList){//按一个检测样本进行统计
        try {
            boolean isPass=true;//默认通过//仅存在超标时显示不合格
            if(itemList.size()==0)
                return;//为0是不正常的样本
            outFruVegSelectType2 firstitem =itemList.get(0);//初步审查，由于整个农药结果列表是拼接到样本表生成的，第一个的样本信息即是整个列表的样本信息
            for (outFruVegSelectType2 item:itemList){
                //预处理
                item.fixData();//数据修正，主要是修正生产基地名称
                //数据审查
                switch (checkIsUseful(item,resultMap)){
                    case 1://缺少重要信息//该样本无法参与统计
                        return;
                    case 2://缺少蔬果名称，整个样本无法进行合格判断//但可以记录抽样数
                        resultMap.get(firstitem.detailType).SamplingCountAddOne(firstitem.vegFruType); //该类型抽样数+1
                        if ("合格".equals(item.chinaStandard)) {
                            resultMap.get(firstitem.detailType).QualifiedCountAddOne(firstitem.vegFruType); //该类型合格数+1
                        }//如果合格则保持该样本的isPass
                        return;
                    case 3://缺失农药名
                        continue;//报错，注意返回提示信息
                    case-1:
                        return;//报错，注意返回提示信息
                    case 0:
                        break;
                }//脱离swtch表示其信息齐全
                String stageName=firstitem.samplingStageType;
                String vegFruType=firstitem.vegFruType;
                //获取对应标准//在这里可以获取多种标准
                PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
                List<agriPesticideResidueStandard> standardslist = outSampleQualityMapper.getagriPesticideResidueStandard(item.pesticideName, item.vegFruName);
                agriPesticideResidueStandard chineseStandard =getUsefulStandard(standardslist,item);


                if(chineseStandard!=null){//在工具方法中判断了是否为国家标准
                    //计算相应属性
                    if (item.pesticideDetValue>chineseStandard.standardValue){
                        isPass=false;
                        break;//产生一个不合格后，该样本不合格后续农药无需考察
                    }

                }else{
                    //没有国家标准,按它自带的合格信息
                    //计算相应属性
                    if ("合格".equals(item.chinaStandard)) {}//如果合格则保持该样本的isPass
                    else if ("不合格".equals(item.chinaStandard)){
                        isPass=false;
                        break;//产生一个不合格后，该样本不合格后续农药无需考察
                    }
                    continue;
                }
            }

            String stageName=firstitem.samplingStageType;
            String vegFruType=firstitem.vegFruType;
            resultMap.get(stageName).SamplingCountAddOne(vegFruType); //该类型抽样数+1
            if (isPass==true){
                resultMap.get(stageName).QualifiedCountAddOne(vegFruType); //该类型合格数+1
            }

        }catch (Exception e){
            log.error("在统计时产生异常",e);
            System.out.println("捕获异常"+e.getMessage());
        }

    }
    /*返回一个可用的标准或返回空
     */
    public agriPesticideResidueStandard getUsefulStandard(List<agriPesticideResidueStandard> standardList,outFruVegSelectType2 item) {
        try {//在数据审查阶段进行异常捕捉
            if (standardList.isEmpty()) {//无任何标准
                String msg = "没有对应国家标准" + "/r/n蔬果名:" + item.vegFruName + "/n农药名:" + item.pesticideName;
                log.error(msg);
                return null;//没有任何标准返回空
            }
            for (agriPesticideResidueStandard standard : standardList) {
                if (standard.standardCategory != null && standard.standardCategory.equals("国家标准") && standard.standardValue != null) {//首先是有国家标准
                        return standard;
                }
            }
            //没有国家标准
            return null;//返回空
        } catch (Exception e) {
            return null;//出错，返回空
        }
    }

}
