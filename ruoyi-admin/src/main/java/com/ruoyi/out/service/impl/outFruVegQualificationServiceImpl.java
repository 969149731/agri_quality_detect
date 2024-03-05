package com.ruoyi.out.service.impl;

import java.lang.reflect.Array;
import java.util.*;

import com.github.pagehelper.PageHelper;
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

    /**
     * 查询各类蔬菜水果合格率情况
     * 
     * @param fruVegQualificationId 各类蔬菜水果合格率情况主键
     * @return 各类蔬菜水果合格率情况
     */
    @Override
    public outFruVegQualification selectoutFruVegQualificationByFruVegQualificationId(Long fruVegQualificationId)
    {
        return outFruVegQualificationMapper.selectoutFruVegQualificationByFruVegQualificationId(fruVegQualificationId);
    }

    /**
     * 查询各类蔬菜水果合格率情况列表
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 各类蔬菜水果合格率情况
     */
    @Override
    public List<outFruVegQualification> selectoutFruVegQualificationList(outFruVegQualification outFruVegQualification)
    {
        //初始化
        List<outFruVegQualification> resultList = new ArrayList<>();
        List<String> FruVegType= Arrays.asList("瓜果类", "叶菜类", "豆类","根茎类","蔬菜其它类","蔬菜小计","柑橘类","浆果类","核果类","水果其它类","水果小计","合计");
        List<String> VegDetailType= Arrays.asList("瓜果类", "叶菜类", "豆类","根茎类","蔬菜其它类");
        List<String> FruDetailType= Arrays.asList("柑橘类","浆果类","核果类","水果其它类");

        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<outFruVegSelectType> selectReturnList = outFruVegQualificationMapper.getFruVegDetResultList();
        if(selectReturnList.isEmpty()){System.out.println("查询出的结果列表为空");return null;}
        Map<String, outFruVegQualification> resultMap = new TreeMap<String, outFruVegQualification>();//使用字典存储

        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (String Typename : FruVegType) {//结果初始化
            resultMap.put(Typename, new outFruVegQualification(Typename));
        }
        //先遍历所有获取到的结果
        for (outFruVegSelectType item : selectReturnList) {
            /*
            * 样式为
            * pesticideDetResultId=2
              pesticideName=甲胺磷
              pesticideDetValue=11.0
              citySampleTestDetailsId=1
              createdAt=<null>
            */
            //获取蔬菜名//用于获取标准
            if(item.detailType ==null){//必要条件不足，无法进行
                System.out.println("当前检测样本信息不足 缺少蔬菜水果子类 如柑橘类"+" 当前："+item.detailType+" 样品编号:"+item.sampleCode);
                continue;}
            if(item.pesticideName==null && item.pesticideDetValue==null){//无农药检出，必定合格
                if("其它类".equals(item.detailType)){item.detailType=item.vegFruType+item.detailType;}
                System.out.println(item.detailType);
                resultMap.get(item.detailType).addOneToSamplingNumber(); //该类型抽样数+1
                resultMap.get(item.detailType).addOneToPassNumber();//该类型合格数+1
                System.out.println("当前检测样本无农药检出"+" 样品编号:"+item.sampleCode);
                continue;}
            String vegFruName = item.vegFruName;
            String vegFruDetailType = item.detailType;if("其他类".equals(vegFruDetailType)){vegFruDetailType=item.vegFruType+vegFruDetailType;}
            String pesticideName = item.pesticideName;
            String stageName = item.samplingStageType;
            agriPesticideResidueStandard firstStandard;

            if (!isDataUseful(item)){
                String msg = "<br/>" + "第"+ item.citySampleTestDetailsId +"条"+ "数据无法判断";
                failureMsg.append(msg);
                log.error(msg);
                continue;//没通过数据可用审查，跳过当前的检测条目
            }

            //获取对应标准//在这里可以获取多种标准
            PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
            List<agriPesticideResidueStandard> standardslist = outFruVegQualificationMapper.getagriPesticideResidueStandard(pesticideName, vegFruName);

            if(!standardslist.isEmpty()){
                firstStandard = standardslist.get(0);
            }else {
                String msg ="没有对应标准"+"/r/n蔬果名:"+vegFruName+"/n农药名:"+pesticideName;
                failureMsg.append(msg);
                log.error(msg);
                continue;
            }
            if(resultMap.get(vegFruDetailType)!=null){//在检测的列表中
                resultMap.get(vegFruDetailType).addOneToSamplingNumber(); //该类型抽样数+1
                //超标记录
                if(item.vegFruName.equals("番茄")){
                    System.out.println("我是番茄");
                }
                if (item.pesticideDetValue > firstStandard.standardValue) {//此处先仅对比第一个标准值
                    System.out.println("超标记录");
                    resultMap.get(vegFruDetailType).addInfoToexceedingSamples(item.vegFruName);//将超标样品写入格式如 空心菜（1）
                    resultMap.get(vegFruDetailType).addInfoToexceedingPesticides(item.pesticideName);//超标农药写入
                }else {
                    resultMap.get(vegFruDetailType).addOneToPassNumber(); //该类型合格数数+1
                }
            }
        }

        //合计计算和比例计算
        outFruVegQualification vegTotal =resultMap.get("蔬菜小计");
        for(String vDetail:VegDetailType){
            outFruVegQualification child= resultMap.get(vDetail);
            vegTotal.addAllToSamplingNumber(child);
            vegTotal.addAllToPassNumber(child);
            child.computeSelfPassRate();
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
        for(String StageTypeName :FruVegType){
            resultList.add(resultMap.get(StageTypeName));
        }
        resultList.get(4).setVegFruType("其他类");
        resultList.get(5).setVegFruType("小计");
        resultList.get(9).setVegFruType("其他类");
        resultList.get(10).setVegFruType("小计");
        return resultList;
    }

    /**
     * 新增各类蔬菜水果合格率情况
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 结果
     */
    @Override
    public int insertoutFruVegQualification(outFruVegQualification outFruVegQualification)
    {
        return outFruVegQualificationMapper.insertoutFruVegQualification(outFruVegQualification);
    }

    /**
     * 修改各类蔬菜水果合格率情况
     * 
     * @param outFruVegQualification 各类蔬菜水果合格率情况
     * @return 结果
     */
    @Override
    public int updateoutFruVegQualification(outFruVegQualification outFruVegQualification)
    {
        return outFruVegQualificationMapper.updateoutFruVegQualification(outFruVegQualification);
    }

    /**
     * 批量删除各类蔬菜水果合格率情况
     * 
     * @param fruVegQualificationIds 需要删除的各类蔬菜水果合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruVegQualificationByFruVegQualificationIds(Long[] fruVegQualificationIds)
    {
        return outFruVegQualificationMapper.deleteoutFruVegQualificationByFruVegQualificationIds(fruVegQualificationIds);
    }

    /**
     * 删除各类蔬菜水果合格率情况信息
     * 
     * @param fruVegQualificationId 各类蔬菜水果合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruVegQualificationByFruVegQualificationId(Long fruVegQualificationId)
    {
        return outFruVegQualificationMapper.deleteoutFruVegQualificationByFruVegQualificationId(fruVegQualificationId);
    }

    //工具方法
    public boolean isDataUseful(outFruVegSelectType data){
        if(data.detailType==null||data.vegFruName==null||data.pesticideName==null){
            System.out.println("信息不足："+" 蔬果种类:"+data.vegFruName+" 蔬果子类:"+data.detailType+" 农药名称:"+data.pesticideName);
            return false;
        }
        return true;
    };
}
