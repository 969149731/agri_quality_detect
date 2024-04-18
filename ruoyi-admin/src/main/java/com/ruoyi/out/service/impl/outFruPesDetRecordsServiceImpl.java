package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outFruPesDetRecordsMapper;
import com.ruoyi.out.service.IoutFruPesDetRecordsService;

import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.agriPesticideResidueStandard;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;
/**
 * 水果禁用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outFruPesDetRecordsServiceImpl implements IoutFruPesDetRecordsService
{
    @Autowired
    private outFruPesDetRecordsMapper outFruPesDetRecordsMapper;
    StringBuilder failureMsg = new StringBuilder();

    @Override
    public List<outReturnType> selectoutFruPesDetRecordsList(agriCitySampleTestDetails agriCitySampleTestDetails, String type,StringBuilder feedBackMsg)
    {
        clearMsg();//对failureMsg进行清空
        //初始化模块
        List<outReturnType> resultList = new ArrayList<outReturnType>();//用于存放结果的列表，当前为空
        //反馈信息
//        StringBuilder successMsg = new StringBuilder();
//        StringBuilder failureMsg = new StringBuilder();

        //查询农药列表
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<String> pesticideList = getPesticideList(type);//可以在此处设置农药列表//也可查询获取列表
        if(pesticideList.isEmpty()){
            addMsg("查询出的农药列表为空");
            System.out.println("查询出的农药列表为空");return resultList;}
        Map<String, outReturnType> pesticideResultMap = new TreeMap<String, outReturnType>();//使用字典存储
        for (String pesticideName : pesticideList) {//初始化
            pesticideResultMap.put(pesticideName, new outReturnType(pesticideName));
        }

        //查询结果列表
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<outFruVegSelectType> SelectList = outFruPesDetRecordsMapper.getFruVegDetResultList(agriCitySampleTestDetails);//获取所有符合条件的农药检测结果表//在此处进行各类条件查询
        if(SelectList.isEmpty()){
            System.out.println("查询出的检测结果列表为空");
            log.debug("查询出的检测结果列表为空",SelectList);
            return returnFinalList(pesticideResultMap);
        }


        //遍历所有获取到的结果
        for (outFruVegSelectType item : SelectList) {
            /*
            * 样式为
            * pesticideDetResultId=2
              pesticideName=甲胺磷
              pesticideDetValue=11.0
              citySampleTestDetailsId=1
              createdAt=<null>
            */
            //预设标准
            agriPesticideResidueStandard firstStandard =new agriPesticideResidueStandard();//默认限值设定为0.0，无对应标准名
            //检测结果可用性检查
            switch (checkIsUseful(item)){
                //对蔬果名、农药名、生产环节进行数据审查
                //审查不通过
                case (1)://缺少农药名和生产环节，无法填入数据
                    continue;//没通过数据可用审查，跳过当前的检测条目
                case (2)://蔬菜名或检测值缺失，无法进行超标判断//但仍可以进行检出判断
                    if(pesticideResultMap.get(item.pesticideName)!=null){//在检测的列表中
                        pesticideResultMap.get(item.pesticideName).totalDet += 1;//总检出
                        pesticideResultMap.get(item.pesticideName).addOneToStageName(item.samplingStageType);//加到对应属性上
                    }
                    continue;
                case (-1)://异常检出
                    continue;
                case (0):
                    //通过
                    item.fixData();//数据修正，主要是修正生产基地名称
                    break;
            }
            if(!pesticideList.contains(item.pesticideName)){//对应农药是否在要求检测的列表内
                continue;
            }

            //获取对应标准//在这里可以获取多种标准
            PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
            List<agriPesticideResidueStandard> standardslist = outFruPesDetRecordsMapper.getagriPesticideResidueStandard(item.pesticideName, item.vegFruName);
            //对标准可用性的审查并返回可用的标准
            firstStandard=getUsefulStandard(standardslist,item);//当前要求仅能对比国家标准
            if (firstStandard==null){
                //没有可用标准,但可以记录检出
                if(pesticideResultMap.get(item.pesticideName)!=null){//在检测的列表中
                    pesticideResultMap.get(item.pesticideName).totalDet += 1;//总检出
                    pesticideResultMap.get(item.pesticideName).addOneToStageName(item.samplingStageType);//加到对应属性上
                }
                continue;
            }
            //计算相应属性//计算的逻辑可能还需要更改
            compute(pesticideResultMap,item,firstStandard);
        }
        feedBackMsg.append(failureMsg.toString());
        clearMsg();//对failureMsg进行清空
        //返回结果
        return returnFinalList(pesticideResultMap);
    }


    //工具方法
    public List<outReturnType> returnFinalList(Map<String, outReturnType> pesticideResultMap){
        List<outReturnType> resultList = new ArrayList<outReturnType>();
        Set<Map.Entry<String, outReturnType>> entrySet=pesticideResultMap.entrySet();
        outReturnType total =new outReturnType("合计");
        for (Map.Entry<String, outReturnType> entryItem:entrySet) {
            resultList.add(entryItem.getValue());
            total.addToTotal(entryItem.getValue());
        }
        resultList.add(total);//合计
        //返回结果
        return resultList;
    }
    public int checkIsUseful(outFruVegSelectType item){
        try{
            //生产环节和农药名为必须 最终展示时由这两个属性确定在表格中的位置
            if (item.samplingStageType==null || item.pesticideName==null || item.samplingStageType.equals("") || item.pesticideName.equals("")){
                addMsg("农药名或生产环节缺失： "+" 农药名:"+item.pesticideName+" 生产环节:"+item.samplingStageType+" 样品编号:"+item.sampleCode);
                return 1;
            }
            if (item.vegFruName==null || item.pesticideDetValue==null){//蔬菜名或检测值缺失，无法进行超标判断
                addMsg("蔬菜名缺失： "+" 蔬果名:"+item.vegFruName+" 检测值:"+item.pesticideDetValue+" 样品编号:"+item.sampleCode);
                return 2;
            }
        }catch (Exception e){
            log.error("在检查环节出现异常",e);
            return -1;//错误
        }
        return 0;//到此说明数据可用
    }
    public void compute(Map<String, outReturnType> pesticideResultMap,outFruVegSelectType item,agriPesticideResidueStandard standard){
        //计算相应属性//计算的逻辑可能还需要更改
        if(pesticideResultMap.get(item.pesticideName)!=null){//在检测的列表中
            pesticideResultMap.get(item.pesticideName).totalDet += 1;//总检出
            pesticideResultMap.get(item.pesticideName).addOneToStageName(item.samplingStageType);//加到对应属性上
            if (item.pesticideDetValue > standard.standardValue) {//此处对比标准值和限量值
                pesticideResultMap.get(item.pesticideName).totalEx += 1;//总超标
                pesticideResultMap.get(item.pesticideName).addOneToStageName(item.samplingStageType + "Ex");//超标
            }
        }
    }
    /*返回一个可用的标准或返回空
     */
    public agriPesticideResidueStandard getUsefulStandard(List<agriPesticideResidueStandard> standardList,outFruVegSelectType item){
        try {//在数据审查阶段进行异常捕捉
            if (standardList.isEmpty()) {//无任何标准
                String msg = "没有对应国家标准"+ "/r/n样品编号:" + item.sampleCode + "/r/n蔬果名:" + item.vegFruName + "/n农药名:" + item.pesticideName;
                log.error(msg);
                addMsg(msg);
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
            log.error("标准数据审查时出错");
            return null;//出错，返回空
        }
    }
    public List<String> getPesticideList(String type){
        if(type.equals("禁用"))
            return outFruPesDetRecordsMapper.getFruBanPesticideList();//禁用
        else return outFruPesDetRecordsMapper.getFruNoBanPesticideList();//非禁用
    }
    public void clearMsg(){
        failureMsg=new StringBuilder();
    }
    public void addMsg(String inputMsg){//目前仅仅是为了增加”<br/>“
        String msg = "<br/>"+inputMsg;
        failureMsg.append(msg);
    }
}
