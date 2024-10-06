package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.myUtils.AgriUtils;
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
    @Autowired
    private agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;
    //////////////////////////////业务功能全局变量（类全局）
    List<outSampleQuality> resultList;//存放结果
    List<String> StageType= Arrays.asList( "生产基地","无公害产品基地","地标生产基地","绿色产品基地","有机产品基地","散户","其它基地","批发市场","运输车","其它");//合计在最后加入
    List<String> StageIncludingType= Arrays.asList("无公害产品基地", "地标生产基地","绿色产品基地","有机产品基地","散户","其它基地");
    List<String> StageTypeForCount = Arrays.asList( "生产基地","批发市场","运输车","其它");
    Map<String, outSampleQuality> resultMap;//结果字典
    returnMsgHandler MsgHandler = new returnMsgHandler();//信息反馈的控制器
    /**
     * 查询各抽样环节合格率情况列表
     * 
     * @param agriCitySampleTestDetails 各抽样环节合格率情况
     * @return 各抽样环节合格率情况
     */
    @Override
    public List<outSampleQuality> selectoutSampleQualityList(agriCitySampleTestDetails agriCitySampleTestDetails,StringBuilder feedBackMsg)
    {

        AgriUtils.permissionToDifferentiateData(agriCitySampleTestDetails);

        //////////////////////初始化
        initModle(feedBackMsg);

        ///////////////////////统计
        //获取检测结果列表
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<agriCitySampleTestDetails> SelectList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);//获取农药检测结果表
        if(SelectList.isEmpty()){
            log.debug("查询出的样本列表为空");
            return returnFinalList();
        }

        //先遍历所有获取到的结果//以id标识一组检测结果（即一个样本）,所以默认id是存在的，事实上id由数据库生成，肯定存在
        List<outFruVegSelectType2> itemList=new ArrayList<>();//初始化
        for (agriCitySampleTestDetails item : SelectList) {
                PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
                itemList=outSampleQualityMapper.getFruVegDetResultList2(item);
                compute(itemList);
        }

        /////////////////////////////结果返回
        MsgHandler.turnToStr();//返回消息
        return returnFinalList();//返回结果
    }
    //模块初始化
    public boolean initModle(StringBuilder feedBackMsg){
        //消息控制器初始化
        MsgHandler.initReturnMsg(feedBackMsg);

        //结果内容初始化
        resultList = new ArrayList<>();//返回结果初始化
        resultMap = new TreeMap<>();//使用字典存储
        for(String typeName:StageType){
            outSampleQuality newOne=new outSampleQuality(typeName);
            if (StageIncludingType.contains(typeName)){
                newOne.setStageIncludeType("其中");
            }
            else{newOne.setStageIncludeType(typeName);}
            resultMap.put(typeName,newOne);
        }
        return true;//即初始化成功
    }

    //工具方法
    //样本整体检查
    public int sampleCheck(List<outFruVegSelectType2> itemList){
        if (itemList==null) return 1;
        if(itemList.size()==0) return 1;//为0是不正常的样本
        outFruVegSelectType2 firstitem =itemList.get(0);//样本整体审查，第一个的样本信息能够代表整个列表的样本信息

        fixData(firstitem);
        try{
            //情形一。生产环节和蔬果类型称为必须
            if(firstitem.samplingStageType ==null ||firstitem.vegFruType==null){//缺失生产环节或蔬果类型,必要条件不足，无法进行
                MsgHandler.addMsgTitle("信息有误","当前检测样本信息有误,请在定量检测导入表中检查对应内容");
                MsgHandler.addMsg("信息有误"," 样品编号:"+firstitem.sampleCode+" 当前生产环节："+firstitem.samplingStageType+" 当前蔬果类型："+firstitem.vegFruType+"(缺少生产环节或蔬果类型)");
                return 1;}
            //情形二，生产环节不在检测范围内
            if(!resultMap.containsKey(firstitem.samplingStageType)){//不在检测列表中
                MsgHandler.addMsg("信息有误"," 样品编号:"+firstitem.sampleCode+" 当前生产环节类型："+firstitem.samplingStageType+"(当前生产类型不在检查范围中)");
                return 1;}
            //情形四，无蔬菜名，无法查询标准
            if(firstitem.vegFruName==null ||firstitem.vegFruName.equals("") ){//没有对应蔬菜名，整个列表都无法进行超标判断
                MsgHandler.addMsg("信息有误"," 样品编号:"+firstitem.sampleCode+" 蔬菜名:"+firstitem.vegFruName+"(缺少蔬菜名)");
                return 1;
            }
            //情形五 无检出农药视为合格
            if (itemList.size()==1 && (firstitem.pesticideName==null||firstitem.pesticideName.equals("")) && (firstitem.pesticideDetValue==null||firstitem.pesticideDetValue==0)){//无农药检出，必定合格
                if (resultMap.get(firstitem.samplingStageType)!=null){//生产环节不能为空
                    if (firstitem.samplingStageType!=null && firstitem.vegFruType !=null){
                        resultMap.get(firstitem.samplingStageType).SamplingCountAddOne(firstitem.vegFruType); //该类型抽样数+1
                        resultMap.get(firstitem.samplingStageType).QualifiedCountAddOne(firstitem.vegFruType); //该类型合格数+1
                        return 1;
                    }else
                        log.error("样本的信息有误"+" 生产环节"+firstitem.samplingStageType+" 蔬果类型"+firstitem.vegFruType);
                    return 1;
                }
            }
            return 0;//通过
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
    public int checkIsUseful(outFruVegSelectType2 item,Map<String, outSampleQuality> resultMap){
        try{
            if(item.pesticideName==null ||item.pesticideName.equals("") ){//没有对应农药名
                MsgHandler.addMsg("信息有误"," 样品编号:"+item.sampleCode+" 农药名:"+item.pesticideName+"(缺少农药名)");
                return 1;
            }
        }catch (Exception e){
            log.error("在数据检查时产生异常",e);
            return -1;//有异常
        }
        return 0;//到此说明数据可用
    }
    public void compute(List<outFruVegSelectType2> itemList){//按一个检测样本进行统计
        try {
            boolean isPass=true;//默认通过//仅存在超标时显示不合格
            switch (sampleCheck(itemList)){
                case 1:
                    return;//不通过
                case -1:
                    return;//错误
                case 0:
                    break;//通过检查
            };//样本整体检查


            outFruVegSelectType2 firstitem =itemList.get(0);//样本整体审查，第一个的样本信息能够代表整个列表的样本信息
            for (outFruVegSelectType2 item:itemList){
                //预处理
                fixData(item);//数据修正，主要是修正生产基地名称
                //数据审查
                switch (checkIsUseful(item,resultMap)){
                    case 1://缺失农药名
                        continue;//报错，注意返回提示信息
                    case-1:
                        return;//报错，注意返回提示信息
                    case 0:
                        break;
                }//脱离swtch表示其信息齐全

                String stageName=item.samplingStageType;
                String vegFruType=item.vegFruType;
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

    public List<outSampleQuality> returnFinalList(){
        //计算生产基地
        outSampleQuality ProduceBasementCount = resultMap.get("生产基地");
        for(String StageTypeName :StageIncludingType){
            ProduceBasementCount.addToTotal(resultMap.get(StageTypeName));
        }

        //计算合计
        outSampleQuality allCount = new outSampleQuality("合计");
        allCount.setStageIncludeType("合计");
        for(String StageTypeName :StageTypeForCount){
            allCount.addToTotal(resultMap.get(StageTypeName));
        }

        //写入返回列表
        for (String StageTypeName :StageType){
            resultList.add(resultMap.get(StageTypeName));
        }
        resultList.add(allCount);//加入合计

        System.out.println("打印结果"+resultList);
        for(outSampleQuality each:resultList){
            each.addToSelfTotal();
            each.computeQualificationRate();//计算
        }
        return resultList;
    }
    public void fixData(outFruVegSelectType2 item){
        //数据预处理，目前主要是对生产环节进行纠正
        //注意生产基地不要放前面，否则先识别出来其他的生产基地子类就无法识别了
        List<String> StageType= Arrays.asList( "无公害产品基地","地标生产基地","绿色产品基地","有机产品基地","散户","其它基地");//生产基地的子类
        for (String type : StageType){
            if(item.samplingStageType!=null && item.samplingStageType.contains(type)){
                item.samplingStageType=type;//将所有数据清洗为规范格式
                return;//找到一个即可返回
            }
        }
        List<String> ProduceBaseStageType= Arrays.asList("公司","农户","合作社");//输出的表中没有它们，归为其它基地
        for (String type : ProduceBaseStageType){
            if(item.samplingStageType!=null && item.samplingStageType.contains(type)){
                item.samplingStageType="其它基地";//将所有数据清洗为规范格式
                return;//找到一个即可返回
            }
        }


        if(item.samplingStageType!=null && item.samplingStageType.equals("基地")){//不是上述类型，但是包含生产基地，应为其他基地类型
            item.samplingStageType="其它基地";//将所有数据清洗为规范格式
            return;//找到一个即可返回
        }
        if(item.samplingStageType!=null && item.samplingStageType.contains("生产基地")){//不是上述类型，但是包含生产基地，应为其他基地类型
            item.samplingStageType="其它基地";//将所有数据清洗为规范格式
            return;//找到一个即可返回
        }
        if(item.samplingStageType!=null && item.samplingStageType.contains("市场")){//不是上述类型，但是包含生产基地，应为其他基地类型
            item.samplingStageType="批发市场";//将所有数据清洗为规范格式
            return;//找到一个即可返回
        }

        if(item.samplingStageType!=null && item.samplingStageType.contains("车")){//不是上述类型，但是包含生产基地，应为其他基地类型
            item.samplingStageType="运输车";//将所有数据清洗为规范格式
            return;//找到一个即可返回
        }


        item.samplingStageType="其它";//以上都不是，则为其它
        return;
    }
}
