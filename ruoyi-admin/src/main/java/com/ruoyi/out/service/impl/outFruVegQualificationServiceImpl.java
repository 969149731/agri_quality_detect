package com.ruoyi.out.service.impl;

import java.lang.reflect.Array;
import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import javassist.compiler.SymbolTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outFruVegQualificationMapper;
import com.ruoyi.out.domain.outFruVegQualification;
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
    StringBuilder feedBack =new StringBuilder();
    /**
     * 查询各类蔬菜水果合格率情况列表
     * 
     * @param agriCitySampleTestDetails 各类蔬菜水果合格率情况
     * @return 各类蔬菜水果合格率情况
     */
    @Override
    public List<outFruVegQualification> selectoutFruVegQualificationList(agriCitySampleTestDetails agriCitySampleTestDetails,StringBuilder feedBackMsg)
    {
        //初始化
        List<outFruVegQualification> resultList = new ArrayList<>();
//        List<String> AllType= Arrays.asList("瓜果类", "叶菜类", "豆类","根茎类","蔬菜其它类","蔬菜小计","柑橘类","浆果类","核果类","水果其它类","水果小计","合计");
//        List<String> VegDetailType= Arrays.asList("瓜果类", "叶菜类", "豆类","根茎类","蔬菜其它类");
//        List<String> FruDetailType= Arrays.asList("柑橘类","浆果类","核果类","水果其它类");


        List<String> VegDetailType= outFruVegQualificationMapper.getVegSubType();
        VegDetailType.remove("其它类");
        VegDetailType.add("蔬菜其它类");
        List<String> FruDetailType= outFruVegQualificationMapper.getFruSubType();
        FruDetailType.remove("其它类");
        FruDetailType.add("水果其它类");
        System.out.println("蔬菜子类列表"+VegDetailType);
        System.out.println("水果子类列表"+FruDetailType);
        List<String> AllType =new ArrayList<>();
        AllType.addAll(VegDetailType);
        AllType.add("蔬菜小计");
        AllType.addAll(FruDetailType);
        AllType.add("水果小计");
        AllType.add("合计");
        System.out.println("所有子类列表"+AllType);

        Map<String, outFruVegQualification> resultMap = new TreeMap<String, outFruVegQualification>();//使用字典存储
        //结果初始化
        for (String Typename : AllType) {
            resultMap.put(Typename, new outFruVegQualification(Typename));
        }


        //查询所有样本的检测结果
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<outFruVegSelectType> selectReturnList = outFruVegQualificationMapper.getFruVegDetResultList(agriCitySampleTestDetails);
        if(selectReturnList.isEmpty()){
            log.debug("查询出的样本列表为空");
            return returnFinalList(resultMap,resultList,AllType,VegDetailType,FruDetailType);
            }
        //先遍历所有获取到的结果//以id标识一组检测结果（即一个样本）,所以默认id是必定存在的，事实上id由数据库生成，肯定存在
        List<outFruVegSelectType> itemList=new ArrayList<outFruVegSelectType>();//初始化
        Long sampleId = selectReturnList.get(0).citySampleTestDetailsId;//对于经过编译器生成的列表对象而言，其执行顺序的正确性是保证的，列表的第一个等同于for中执行的第一个
        for (outFruVegSelectType item : selectReturnList) {
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

        feedBackMsg.append(feedBack);
        return returnFinalList(resultMap,resultList,AllType,VegDetailType,FruDetailType);
    }


    //工具方法
    public List<outFruVegQualification> returnFinalList(Map<String, outFruVegQualification> resultMap,List<outFruVegQualification> resultList,
                                                        List<String> AllType,List<String> VegDetailType,List<String> FruDetailType){
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
        //合计
        outFruVegQualification total =resultMap.get("合计");
        total.addAllToSamplingNumber(vegTotal);
        total.addAllToPassNumber(vegTotal);
        total.addAllToSamplingNumber(fruTotal);
        total.addAllToPassNumber(fruTotal);
        total.computeSelfPassRate();

        //装入
        for(String StageTypeName :AllType){
            resultList.add(resultMap.get(StageTypeName));
        }
        resultList.get(4).setVegFruType("其他类");
        resultList.get(5).setVegFruType("小计");
        resultList.get(9).setVegFruType("其他类");
        resultList.get(10).setVegFruType("小计");
        return resultList;
    }
    public int checkIsUseful(outFruVegSelectType item,Map<String, outFruVegQualification> resultMap){
        try{
            //情形一。子类名称为必须
            if(item.detailType ==null ){//必要条件不足，无法进行
                if("其他类".equals(item.detailType)){item.detailType=item.vegFruType+item.detailType;}//重命名其他类
                addMsg("当前检测样本信息有误 缺少蔬菜水果子类 如柑橘类"+" 样品编号:"+item.sampleCode+" 当前："+item.detailType);
                return 1;}

            //情形二，子类为其它类，要求前置大类存在
            if("其它类".equals(item.detailType)){
                if(item.vegFruType==null){
                    addMsg("当前检测样本信息有误 其它类需要配合大类使用(如蔬菜或水果)"+" 样品编号:"+item.sampleCode+" 当前子类："+item.detailType+" 当前大类："+item.vegFruType);
                    return 1;
                }
                item.detailType=item.vegFruType+item.detailType;
            }//重命名其他类
            //情形三，当前子类不在检测范围内
            if(!resultMap.containsKey(item.detailType)){//不在检测列表中
                //System.out.println("当前检测样本信息有误 该类别不在检测列表中"+" 当前子类："+item.detailType+" 当前大类："+item.vegFruType+" 样品编号:"+item.sampleCode);
                return 1;}

            //情形四，无蔬菜名，无法查询标准
            if(item.vegFruName==null ||item.vegFruName.equals("") ){//没有对应蔬菜名，整个列表都无法进行超标判断
                addMsg("当前检测样本信息有误 无对应蔬菜名"+" 样品编号:"+item.sampleCode+" 蔬菜名："+item.vegFruName);
                return 2;
            }

        }catch (Exception e){
            return -1;//有异常
        }
        return 0;//到此说明数据可用
    }
    public void compute(Map<String, outFruVegQualification> resultMap,List<outFruVegSelectType> itemList){//按一个检测样本进行统计
        boolean isPass=true;//默认通过//仅存在超标时显示不合格
        if(itemList.size()==0)
            return;
        String vegFruDetailType;
        outFruVegSelectType firstitem =itemList.get(0);//初步审查，由于整个农药结果列表是拼接到样本表生成的，第一个的样本信息即是整个列表的样本信息
        switch (checkIsUseful(firstitem,resultMap)){
            case 1://必要条件不足，注意输出提示信息
                return;//没有子类信息或不在检测范围中，无法填入表格
            case 2://必要条件存在（子类存在），但蔬菜名不存在，整个列表无法进行超标判断
                resultMap.get(firstitem.detailType).addOneToSamplingNumber(); //该类型抽样数+1
                return;
            case 0://农药名，蔬菜名，子类名，检测值均通过，可以检测是否超标
                break;
        }
        if (itemList.size()==1 && firstitem.pesticideName==null && firstitem.pesticideDetValue==null){//无农药检出，必定合格
            if("其它类".equals(firstitem.detailType)){firstitem.detailType=firstitem.vegFruType+firstitem.detailType;}
            resultMap.get(firstitem.detailType).addOneToSamplingNumber(); //该类型抽样数+1
            resultMap.get(firstitem.detailType).addOneToPassNumber();//该类型合格数+1
            return;//退出
        }

        //这个句子放在switch里会报错无法编译，故放在此
        vegFruDetailType =firstitem.detailType;if("其他类".equals(vegFruDetailType)){vegFruDetailType=firstitem.vegFruType+vegFruDetailType;}
        try{
            //遍历列表
            for (outFruVegSelectType item :itemList){
                if (item.pesticideName==null ||item.pesticideName.equals("")||item.pesticideDetValue==null){//单个条目无农药名或无检测值，无法判断是否超标
                    addMsg("当前检测样本信息有误,无法判断"+" 样品编号:"+item.sampleCode+" 农药名："+item.pesticideName+" 检测值："+item.pesticideDetValue);
                    continue;//下一个农药
                }
                String vegFruName = item.pesticideName;
                String pesticideName = item.pesticideName;
                agriPesticideResidueStandard firstStandard;


                //获取对应标准//在这里可以获取多种标准
                PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
                List<agriPesticideResidueStandard> standardslist = outFruVegQualificationMapper.getagriPesticideResidueStandard(pesticideName, vegFruName);

                firstStandard=getUsefulStandard(standardslist,item);
                if (firstStandard==null){
                    //没有可用标准//输出错误信息
                    addMsg("该农药没有任何标准"+ "/r/n样品编号:" + item.sampleCode + "/r/n蔬果名:" + item.vegFruName + "/n农药名:" + item.pesticideName);
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
    public void clearMsg(){
        feedBack=new StringBuilder();
    }
    public void addMsg(String inputMsg){//目前仅仅是为了增加”<br/>“
        String msg = "<br/>"+inputMsg;
        feedBack.append(msg);
    }
}
