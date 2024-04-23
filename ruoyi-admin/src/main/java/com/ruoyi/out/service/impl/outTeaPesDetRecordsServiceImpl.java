package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.returnMsgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outTeaPesDetRecordsMapper;
import com.ruoyi.out.service.IoutTeaPesDetRecordsService;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 茶叶禁用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-04-08
 */
@Service
public class outTeaPesDetRecordsServiceImpl implements IoutTeaPesDetRecordsService
{
    @Autowired
    private outTeaPesDetRecordsMapper outTeaPesDetRecordsMapper;
    ///////////业务模块全局变量
    List<outReturnType> resultList;//用于存放结果的列表，当前为空
    Map<String, outReturnType> pesticideResultMap;//各个农药检出情况
    List<String> pesticideList;//农药列表
    returnMsgHandler MsgHandler = new returnMsgHandler();

    /**
     * 查询茶叶禁用农药检出及超标情况列表
     * 
     * @param agriCitySampleTestDetails 茶叶禁用农药检出及超标情况
     * @return 茶叶禁用农药检出及超标情况
     */
    @Override
    public List<outReturnType> selectoutTeaPesDetRecordsList(agriCitySampleTestDetails agriCitySampleTestDetails, String type,StringBuilder feedBackMsg)
    {
        /////////////模块初始化
        if (initModule(feedBackMsg,type));
        else return returnFinalList();

        /////////////统计
        //查询结果列表
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<outFruVegSelectType> SelectList = outTeaPesDetRecordsMapper.getDetResultList(agriCitySampleTestDetails);//获取所有符合条件的农药检测结果表//在此处进行各类条件查询
        if(SelectList.isEmpty()){System.out.println("查询出的检测结果列表为空");
            return returnFinalList();
        }


        //遍历所有获取到的结果
        for (outFruVegSelectType item : SelectList) {
            //预设标准
            agriPesticideResidueStandard firstStandard =new agriPesticideResidueStandard();//默认限值设定为0.0，无对应标准名
            item.fixData();//数据修正，主要是修正生产基地名称
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
                    break;
            }
                if(!pesticideList.contains(item.pesticideName)){//对应农药是否在要求检测的列表内
//                    System.out.println("该农药不在检测列表中");
                    continue;
                }

            //获取对应标准//在这里可以获取多种标准
            PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
            List<agriPesticideResidueStandard> standardslist = outTeaPesDetRecordsMapper.getagriPesticideResidueStandard(item.pesticideName, item.vegFruName);
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
            compute(item,firstStandard);
        }
        //返回结果
        return returnFinalList();
    }


    //工具方法
    public boolean initModule(StringBuilder feedBackMsg,String type){
        MsgHandler.initReturnMsg(feedBackMsg);
        //初始化模块
        resultList = new ArrayList<outReturnType>();//用于存放结果的列表，当前为空

        //查询农药列表
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        pesticideList = getPesticideList(type);//可以在此处设置农药列表//也可查询获取列表

        pesticideResultMap = new TreeMap<String, outReturnType>();//使用字典存储
        for (String pesticideName : pesticideList) {//初始化
            pesticideResultMap.put(pesticideName, new outReturnType(pesticideName));
        }
        if(pesticideList.isEmpty()){
            MsgHandler.addMsg("错误","没有水果"+type+"农药，请检查农药字典中是否有水果"+type+"类型的农药");//在农药表中没有水果禁用/非禁用农药
            log.error("查询出的农药列表为空");
            return false;//返回空表
        }
        return true;
    }
    public List<outReturnType> returnFinalList(){
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
                MsgHandler.addMsgTitle("信息缺失","下列样本存在信息缺失，请在定量检测导入表中检查样本信息");
                MsgHandler.addMsg("信息缺失",item.sampleCode+" 农药名:"+item.pesticideName+" 生产环节:"+item.samplingStageType+" 样品编号:"+"农药名或生产环节缺失");
                return 1;
            }
            if (item.vegFruName==null || item.pesticideDetValue==null){//蔬菜名或检测值缺失，无法进行超标判断
                MsgHandler.addMsg("信息缺失"," 样品编号:"+item.sampleCode+" 蔬果名:"+item.vegFruName+" 检测值:"+item.pesticideDetValue+"(蔬菜名或检测值缺失)");
                return 2;
            }
        }catch (Exception e){
            log.error("在检查环节出现异常",e);
            return -1;//错误
        }
        return 0;//到此说明数据可用
    }
    public void compute(outFruVegSelectType item,agriPesticideResidueStandard standard){
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
                String msg = "没有对应国家标准" + "/r/n蔬果名:" + item.vegFruName + "/n农药名:" + item.pesticideName;
                log.error(msg);
                MsgHandler.addMsgTitle("没有国家标准","下列对应蔬果对应农药没有对应国家标准，请在标准中添加");
                MsgHandler.addMsg("没有国家标准", " 蔬果名:" + item.vegFruName + " 农药名:" + item.pesticideName+" 样品编号:" + item.sampleCode);
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

    public List<String> getPesticideList(String type){
        if(type.equals("禁用"))
            return outTeaPesDetRecordsMapper.getTeaBanPesticideList();//禁用
        else return outTeaPesDetRecordsMapper.getTeaNoBanPesticideList();//非禁用
    }

}
