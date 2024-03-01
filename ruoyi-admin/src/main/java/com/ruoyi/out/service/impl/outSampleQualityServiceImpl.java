package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outSampleQualityMapper;
import com.ruoyi.out.service.IoutSampleQualityService;
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

    /**
     * 查询各抽样环节合格率情况
     * 
     * @param sampleQualityId 各抽样环节合格率情况主键
     * @return 各抽样环节合格率情况
     */
    @Override
    public outSampleQuality selectoutSampleQualityBySampleQualityId(Long sampleQualityId)
    {
        return outSampleQualityMapper.selectoutSampleQualityBySampleQualityId(sampleQualityId);
    }

    /**
     * 查询各抽样环节合格率情况列表
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 各抽样环节合格率情况
     */
    @Override
    public List<outSampleQuality> selectoutSampleQualityList(outSampleQuality outSampleQuality)
    {
        //初始化列表
        List<outSampleQuality> resultList = new ArrayList<>();
        List<String> StageType= Arrays.asList("生产基地", "无公害产品基地","地标生产基地","绿色产品基地","有机产品基地","散户","其他基地","批发市场","运输车");//合计在最后加入
        List<String> StageIncludingType= Arrays.asList("无公害产品基地", "地标生产基地","绿色产品基地","有机产品基地","散户","其他基地");
        Map<String, outSampleQuality> resultMap = new TreeMap<String, outSampleQuality>();//使用字典存储
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
        List<outFruVegSelectType2> SelectList = outSampleQualityMapper.getFruVegDetResultList();//获取农药检测结果表
        System.out.println("列表大小"+SelectList.size());
        for (outFruVegSelectType2 item : SelectList) {//遍历结果表
            //获取蔬菜名//用于获取标准
            String vegFruName = item.vegFruName;
            String vegFruType = item.vegFruType;
            String pesticidName = item.pesticideName;
            String stageName = item.samplingStageType;


            //计算相应属性
            if ("水果".equals(vegFruType)){
                resultMap.get(item.samplingStageType).fruSamplingCountAddOne();
                if ("合格".equals(item.chinaStandard)){
                    resultMap.get(item.samplingStageType).fruQualifiedCountAddOne();
                }
            }

            else if ("蔬菜".equals(vegFruType)){
                resultMap.get(item.samplingStageType).vegSamplingCountAddOne();
                if ("合格".equals(item.chinaStandard)){
                    resultMap.get(item.samplingStageType).vegQualifiedCountAddOne();
                }
            }

        }

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

    /**
     * 新增各抽样环节合格率情况
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 结果
     */
    @Override
    public int insertoutSampleQuality(outSampleQuality outSampleQuality)
    {
        return outSampleQualityMapper.insertoutSampleQuality(outSampleQuality);
    }

    /**
     * 修改各抽样环节合格率情况
     * 
     * @param outSampleQuality 各抽样环节合格率情况
     * @return 结果
     */
    @Override
    public int updateoutSampleQuality(outSampleQuality outSampleQuality)
    {
        return outSampleQualityMapper.updateoutSampleQuality(outSampleQuality);
    }

    /**
     * 批量删除各抽样环节合格率情况
     * 
     * @param sampleQualityIds 需要删除的各抽样环节合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutSampleQualityBySampleQualityIds(Long[] sampleQualityIds)
    {
        return outSampleQualityMapper.deleteoutSampleQualityBySampleQualityIds(sampleQualityIds);
    }

    /**
     * 删除各抽样环节合格率情况信息
     * 
     * @param sampleQualityId 各抽样环节合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutSampleQualityBySampleQualityId(Long sampleQualityId)
    {
        return outSampleQualityMapper.deleteoutSampleQualityBySampleQualityId(sampleQualityId);
    }
    //私有工具方法
    private agriPesticideResidueStandard findChineseStandard(List<agriPesticideResidueStandard> standardList){
        for (agriPesticideResidueStandard standard:standardList){
            if ("国家标准".equals(standard.standardCategory)){
                return standard;
            }
        }
        return null;
    }

    public List<outSampleQuality> selectoutSampleQualityList2(outSampleQuality outSampleQuality)
    {//统计方法2，备用
        //初始化列表
        List<outSampleQuality> resultList = new ArrayList<>();
        List<String> StageType= Arrays.asList("生产基地", "无公害产品基地","地标生产基地","绿色产品基地","有机产品基地","散户","其他基地","批发市场","运输车");//合计在最后加入
        List<String> StageIncludingType= Arrays.asList("无公害产品基地", "地标生产基地","绿色产品基地","有机产品基地","散户","其他基地");
        Map<String, outSampleQuality> resultMap = new TreeMap<String, outSampleQuality>();//使用字典存储
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
        List<outFruVegSelectType2> SelectList = outSampleQualityMapper.getFruVegDetResultList();//获取农药检测结果表
        System.out.println("列表大小"+SelectList.size());
        for (outFruVegSelectType2 item : SelectList) {//遍历结果表
            //获取蔬菜名//用于获取标准
            String vegFruName = item.vegFruName;
            String vegFruType = item.vegFruType;
            String pesticidName = item.pesticideName;
            String stageName = item.samplingStageType;
            agriPesticideResidueStandard chineseStandard=null;
            //获取对应标准//在这里可以获取多种标准
            PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
            List<agriPesticideResidueStandard> standardslist = outSampleQualityMapper.getagriPesticideResidueStandard(pesticidName, vegFruName);
            if(!standardslist.isEmpty() && this.findChineseStandard(standardslist)!=null){//在工具方法中判断了是否为国家标准
                chineseStandard =this.findChineseStandard(standardslist);//获取国家标准
            }else {
                System.out.println("没有对应标准 国家标准"+"/n蔬果名:"+vegFruName+"/n农药名:"+pesticidName);//报错
            }

            //计算相应属性
            if ("水果".equals(vegFruType)){
                resultMap.get(item.samplingStageType).fruSamplingCountAddOne();
                if (chineseStandard !=null && item.pesticideDetValue<chineseStandard.standardValue){
                    resultMap.get(item.samplingStageType).fruQualifiedCountAddOne();
                }
            }

            else if ("蔬菜".equals(vegFruType)){
                resultMap.get(item.samplingStageType).vegSamplingCountAddOne();
                if (chineseStandard !=null && item.pesticideDetValue<chineseStandard.standardValue){
                    resultMap.get(item.samplingStageType).vegQualifiedCountAddOne();
                }
            }

        }

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
}
