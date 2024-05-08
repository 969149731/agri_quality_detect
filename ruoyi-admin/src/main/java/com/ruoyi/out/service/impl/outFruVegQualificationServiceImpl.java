package com.ruoyi.out.service.impl;

import java.lang.reflect.Array;
import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.out.domain.*;
import javassist.compiler.SymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outFruVegQualificationMapper;
import com.ruoyi.out.service.IoutFruVegQualificationService;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 各类蔬菜水果合格率情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outFruVegQualificationServiceImpl implements IoutFruVegQualificationService 
{
    @Autowired
    private outFruVegQualificationMapper outFruVegQualificationMapper;
    ///
    List<outFruVegQualification> resultList;
    List<String> VegDetailType;
    List<String> FruDetailType;
    List<String> AllType;
    Map<String, outFruVegQualification> resultMap;
    returnMsgHandler MsgHandler = new returnMsgHandler();
    /**
     * 查询各类蔬菜水果合格率情况列表
     * 
     * @param agriCitySampleTestDetails 各类蔬菜水果合格率情况
     * @return 各类蔬菜水果合格率情况
     */
    @Override
    public List<outFruVegQualification> selectoutFruVegQualificationList(agriCitySampleTestDetails agriCitySampleTestDetails,StringBuilder feedBackMsg)
    {
        if (initModule(feedBackMsg));
        else return returnFinalList();


        //查询所有样本的检测结果
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<outFruVegSelectType> selectReturnList = outFruVegQualificationMapper.getFruVegDetResultList(agriCitySampleTestDetails);
        if(selectReturnList.isEmpty()){
            log.debug("查询出的样本列表为空");
            return returnFinalList();
            }
        //先遍历所有获取到的结果//以id标识一组检测结果（即一个样本）,所以默认id是必定存在的，事实上id由数据库生成，肯定存在
        List<outFruVegSelectType> itemList=new ArrayList<outFruVegSelectType>();//初始化
        Long sampleId = selectReturnList.get(0).citySampleTestDetailsId;//对于经过编译器生成的列表对象而言，其执行顺序的正确性是保证的，列表的第一个等同于for中执行的第一个
        for (outFruVegSelectType item : selectReturnList) {
            if (item.citySampleTestDetailsId.equals(sampleId)){
                itemList.add(item);
            }
            else{
                compute(itemList);
                itemList=new ArrayList<>();//重置
                itemList.add(item);//把当前item加入
                sampleId=item.citySampleTestDetailsId;
            }
        }
        compute(itemList);//当仅有一个样本，或是最后一个样本，没有下一个不同的id触发，compute，在此计算

        MsgHandler.turnToStr();
        return returnFinalList();
    }


    //工具方法
    public boolean initModule(StringBuilder feedBackMsg){
        try {
            //初始化
            MsgHandler.initReturnMsg(feedBackMsg);
//        List<String> AllType= Arrays.asList("瓜果类", "叶菜类", "豆类","根茎类","蔬菜其它类","蔬菜小计","柑橘类","浆果类","核果类","水果其它类","水果小计","合计");
//        List<String> VegDetailType= Arrays.asList("瓜果类", "叶菜类", "豆类","根茎类","蔬菜其它类");
//        List<String> FruDetailType= Arrays.asList("柑橘类","浆果类","核果类","水果其它类");


            VegDetailType= outFruVegQualificationMapper.getVegSubType();
            VegDetailType.remove("其它类");
            VegDetailType.add("蔬菜其它类");
            FruDetailType= outFruVegQualificationMapper.getFruSubType();
            FruDetailType.remove("其它类");
            FruDetailType.add("水果其它类");
            System.out.println("蔬菜子类列表"+VegDetailType);
            System.out.println("水果子类列表"+FruDetailType);
            AllType =new ArrayList<>();
            AllType.addAll(VegDetailType);
            AllType.add("蔬菜小计");
            AllType.addAll(FruDetailType);
            AllType.add("水果小计");
            AllType.add("茶叶");
            AllType.add("合计");
            System.out.println("所有子类列表"+AllType);

            resultMap = new TreeMap<String, outFruVegQualification>();//使用字典存储
            //结果初始化
            for (String Typename : AllType) {
                resultMap.put(Typename, new outFruVegQualification(Typename));
            }
            return true;
        }catch (Exception e){
            log.error("模块初始化时出错",e);
            return false;
        }

    }
    public List<outFruVegQualification> returnFinalList(){
        resultList = new ArrayList<>();
        //合计计算和比例计算
        outFruVegQualification vegTotal =resultMap.get("蔬菜小计");
        for(String vDetail:VegDetailType){
            outFruVegQualification child= resultMap.get(vDetail);
            vegTotal.addAllToSamplingNumber(child);
            vegTotal.addAllToPassNumber(child);
            child.computeSelfPassRate();//请记得返回分母为0异常信息
        }
        vegTotal.computeSelfPassRate();

        outFruVegQualification fruTotal =resultMap.get("水果小计");
        for(String fDetail:FruDetailType){
            outFruVegQualification child= resultMap.get(fDetail);
            fruTotal.addAllToSamplingNumber(child);
            fruTotal.addAllToPassNumber(child);
            child.computeSelfPassRate();
        }
        fruTotal.computeSelfPassRate();
        //茶叶
        outFruVegQualification TeaTotal =resultMap.get("茶叶");
        TeaTotal.computeSelfPassRate();
        //合计
        outFruVegQualification total =resultMap.get("合计");
        total.addAllToSamplingNumber(vegTotal);
        total.addAllToPassNumber(vegTotal);
        total.addAllToSamplingNumber(fruTotal);
        total.addAllToPassNumber(fruTotal);
        total.addAllToSamplingNumber(TeaTotal);
        total.addAllToPassNumber(TeaTotal);
        total.computeSelfPassRate();

        //装入
        for(String StageTypeName :AllType){
            resultList.add(resultMap.get(StageTypeName));
        }

        resultList.get(resultList.indexOf(resultMap.get("蔬菜其它类"))).setVegFruType("其他类");//按顺序修改其内容
        resultList.get(resultList.indexOf(resultMap.get("蔬菜小计"))).setVegFruType("小计");
        resultList.get(resultList.indexOf(resultMap.get("水果其它类"))).setVegFruType("其他类");
        resultList.get(resultList.indexOf(resultMap.get("水果小计"))).setVegFruType("小计");
        return resultList;
    }
    public int sampleCheck(List<outFruVegSelectType> itemList){
        if (itemList==null) return 1;
        if(itemList.size()==0) return 1;//为0是不正常的样本
        outFruVegSelectType firstitem =itemList.get(0);//样本整体审查，第一个的样本信息能够代表整个列表的样本信息

        fixDetailTypeData(firstitem);
        try{
            //情形一。子类名称为必须
            if(firstitem.detailType == null ){//必要条件不足，无法进行
                if("其他类".equals(firstitem.detailType)){firstitem.detailType=firstitem.vegFruType+firstitem.detailType;}//重命名其他类
                MsgHandler.addMsg("信息有误","样品编号:"+firstitem.sampleCode+" 当前子类名称："+firstitem.detailType+"(缺少蔬菜水果子类)");
                return 1;}
            //无检出农药，为合格
            if (itemList.size()==1 && (firstitem.pesticideName==null||firstitem.pesticideName.equals("")) && (firstitem.pesticideDetValue==null||firstitem.pesticideDetValue==0)){//无农药检出，必定合格
                if("其它类".equals(firstitem.detailType)){firstitem.detailType=firstitem.vegFruType+firstitem.detailType;}
                resultMap.get(firstitem.detailType).addOneToSamplingNumber(); //该类型抽样数+1
                resultMap.get(firstitem.detailType).addOneToPassNumber();//该类型合格数+1
                return 1;//退出
            }

            //情形二，子类为其它类，要求前置大类存在
            if("其它类".equals(firstitem.detailType)){
                if(firstitem.vegFruType==null){
                    MsgHandler.addMsg("信息有误"," 样品编号:"+firstitem.sampleCode+" 当前子类："+firstitem.detailType+" 当前大类："+firstitem.vegFruType+" 其它类需要配合大类使用(如蔬菜或水果)");
                    return 1;
                }
                firstitem.detailType=firstitem.vegFruType+firstitem.detailType;
            }//重命名其他类
            //情形三，当前子类不在检测范围内
            if(!resultMap.containsKey(firstitem.detailType)){//不在检测列表中
                //System.out.println("当前检测样本信息有误 该类别不在检测列表中"+" 当前子类："+firstitem.detailType+" 当前大类："+firstitem.vegFruType+" 样品编号:"+firstitem.sampleCode);
                return 1;}

            //情形四，无蔬菜名，无法查询标准
            if(firstitem.vegFruName==null ||firstitem.vegFruName.equals("") ){//没有对应蔬菜名，整个列表都无法进行超标判断
                resultMap.get(firstitem.detailType).addOneToSamplingNumber(); //该类型抽样数+1
                MsgHandler.addMsg("信息有误"," 样品编号:"+firstitem.sampleCode+" 蔬菜名："+firstitem.vegFruName+"（无蔬菜名无法判断）");
                return 1;
            }
            return 0;
        }
        catch (NullPointerException nullE){
            MsgHandler.addMsg("错误","出现空指针请在定量检测导入表中检查样本:"+" 样本id"+firstitem.citySampleTestDetailsId);
            log.error("出现空指针错误",nullE);
            return -1;
        }
        catch (Exception e){
            MsgHandler.addMsg("错误","出现未知错误请联系管理员"+" 样本id"+firstitem.citySampleTestDetailsId);
            log.error("出现未知错误",e);
            return -1;
        }
    }
    public int checkIsUseful(outFruVegSelectType item){
        try{
            fixDetailTypeData(item);
            if (item.pesticideName==null ||item.pesticideName.equals("")||item.pesticideDetValue==null){//单个条目无农药名或无检测值，无法判断是否超标
                MsgHandler.addMsgTitle("无农药检测值","无农药检测值,请在农药检测结果表中检查下列样本");
                MsgHandler.addMsg("无农药检测值"," 样品编号:"+item.sampleCode+" 农药名："+item.pesticideName+" 检测值："+item.pesticideDetValue);
                return 1;
            }
        }catch (Exception e){
            log.error("在单个检测结果数据审查时报错",e);
            return -1;//有异常
        }
        return 0;//到此说明数据可用
    }

    private void fixDetailTypeData(outFruVegSelectType item) {
        if (item.detailType==null)
            return;
        if (item.detailType.equals("茶叶类")){
            item.setDetailType("茶叶");
        }
    }

    public void compute(List<outFruVegSelectType> itemList){//按一个检测样本进行统计
        boolean isPass=true;//默认通过//仅存在超标时显示不合格
        switch (sampleCheck(itemList)){
            case 1://
                return;
            case 0:
                //通过审查
                break;
            case -1:
                return;//报错
        }

        outFruVegSelectType firstitem =itemList.get(0);//初步审查，由于整个农药结果列表是拼接到样本表生成的，第一个的样本信息即是整个列表的样本信息
        String vegFruDetailType =firstitem.detailType;if("其他类".equals(vegFruDetailType)){vegFruDetailType=firstitem.vegFruType+vegFruDetailType;}
        try{
            //遍历列表
            for (outFruVegSelectType item :itemList){
                switch (checkIsUseful(item)){
                    case 1:
                        continue;//下一个农药
                    case 0:
                        break;//通过
                    case -1:
                        continue;//报错
                }
                String vegFruName = item.vegFruName;
                String pesticideName = item.pesticideName;
                agriPesticideResidueStandard firstStandard;


                //获取对应标准//在这里可以获取多种标准
                PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
                List<agriPesticideResidueStandard> standardslist = outFruVegQualificationMapper.getagriPesticideResidueStandard(pesticideName, vegFruName);

                firstStandard=getUsefulStandard(standardslist,item);
                if (firstStandard==null){
                    //没有可用标准//输出错误信息
                    MsgHandler.addMsgTitle("没有任何标准","对应蔬果和对应农药下无任何标准，请在农药标准表中检查是否有对应标准，或样本的蔬果名和农药名是否正确");
                    MsgHandler.addMsg("没有任何标准"," 样本编号:" + item.sampleCode + " 蔬果名:" + item.vegFruName + " 农药名:" + item.pesticideName);
                    continue;//下一个农药条目
                }
                //超标记录
                if (item.pesticideDetValue > firstStandard.standardValue) {
                    isPass=false;
                    resultMap.get(vegFruDetailType).addInfoToexceedingSamples(item.vegFruName);//将超标样品写入格式如 空心菜（1）
                    resultMap.get(vegFruDetailType).addInfoToexceedingPesticides(item.pesticideName);//超标农药写入
                }
            }
            resultMap.get(firstitem.detailType).addOneToSamplingNumber(); //该类型抽样数+1
            if(isPass==true) {
                resultMap.get(vegFruDetailType).addOneToPassNumber(); //该类型合格数数+1
            }
        }catch (Exception E){
            log.error("在统计时捕获异常" ,E);
        }
    }
    /*返回一个可用的标准或返回空
     */
    public agriPesticideResidueStandard getUsefulStandard(List<agriPesticideResidueStandard> standardList,outFruVegSelectType item){
        try {//在数据审查阶段进行异常捕捉
            if (standardList.isEmpty()) {//无任何标准
                String msg = "没有对应国家标准" + "/r/n蔬果名:" + item.vegFruName + "/n农药名:" + item.pesticideName;
                log.debug(msg);
                MsgHandler.addMsg("没有对应国家标准"," 样本编号"+item.sampleCode+" 蔬果名:" + item.vegFruName + " 农药名:" + item.pesticideName);
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
            log.error("获取可用标准时出错",e);
            return null;//出错，返回空
        }
    }
}
