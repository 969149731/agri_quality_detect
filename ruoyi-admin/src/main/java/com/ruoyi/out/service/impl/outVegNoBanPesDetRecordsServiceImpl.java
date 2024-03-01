package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outVegNoBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outVegNoBanPesDetRecords;
import com.ruoyi.out.service.IoutVegNoBanPesDetRecordsService;

/**
 * 蔬菜上非禁止使用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outVegNoBanPesDetRecordsServiceImpl implements IoutVegNoBanPesDetRecordsService 
{
    @Autowired
    private outVegNoBanPesDetRecordsMapper outVegNoBanPesDetRecordsMapper;

    /**
     * 查询蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param vegNoBanPesDetRecordsId 蔬菜上非禁止使用农药检出及超标情况主键
     * @return 蔬菜上非禁止使用农药检出及超标情况
     */
    @Override
    public outVegNoBanPesDetRecords selectoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(Long vegNoBanPesDetRecordsId)
    {
        return outVegNoBanPesDetRecordsMapper.selectoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(vegNoBanPesDetRecordsId);
    }

    /**
     * 查询蔬菜上非禁止使用农药检出及超标情况列表
     * 
     * @param outVegNoBanPesDetRecords 蔬菜上非禁止使用农药检出及超标情况
     * @return 蔬菜上非禁止使用农药检出及超标情况
     */
    @Override
    public List<outVegNoBanPesDetRecords> selectoutVegNoBanPesDetRecordsList(outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        return outVegNoBanPesDetRecordsMapper.selectoutVegNoBanPesDetRecordsList(outVegNoBanPesDetRecords);
    }

    /**
     * 新增蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param outVegNoBanPesDetRecords 蔬菜上非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        return outVegNoBanPesDetRecordsMapper.insertoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords);
    }

    /**
     * 修改蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param outVegNoBanPesDetRecords 蔬菜上非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        return outVegNoBanPesDetRecordsMapper.updateoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords);
    }

    /**
     * 批量删除蔬菜上非禁止使用农药检出及超标情况
     * 
     * @param vegNoBanPesDetRecordsIds 需要删除的蔬菜上非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsIds(Long[] vegNoBanPesDetRecordsIds)
    {
        return outVegNoBanPesDetRecordsMapper.deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsIds(vegNoBanPesDetRecordsIds);
    }

    /**
     * 删除蔬菜上非禁止使用农药检出及超标情况信息
     * 
     * @param vegNoBanPesDetRecordsId 蔬菜上非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(Long vegNoBanPesDetRecordsId)
    {
        return outVegNoBanPesDetRecordsMapper.deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(vegNoBanPesDetRecordsId);
    }
    public List<outReturnType> selectoutVegNoBanPesDetRecordsList2(){
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<outReturnType> returnResult = new ArrayList<outReturnType>();//生产原始返回值结果，农药名及全为0的其他值
        List<String> pesticideList = outVegNoBanPesDetRecordsMapper.getVegNoBanPesticideList();//可以在此处设置农药列表//也可查询获取列表
        Map<String, outReturnType> pesticideResultMap = new TreeMap<String, outReturnType>();//使用字典存储
        for (String pesticideName : pesticideList) {//初始化
            pesticideResultMap.put(pesticideName, new outReturnType(pesticideName));
        }
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<outFruVegSelectType> SelectList = outVegNoBanPesDetRecordsMapper.getFruVegDetResultList();//获取农药检测结果表

        //先遍历所有获取到的结果
        for (outFruVegSelectType item : SelectList) {
            /*
            * 样式为
            * pesticideDetResultId=2
              pesticideName=甲胺磷
              pesticideDetValue=11.0
              citySampleTestDetailsId=1
              createdAt=<null>
            */
            System.out.println("当前检测条目");
            System.out.println(item);
            //获取蔬菜名//用于获取标准
            String vegFruName = item.vegFruName;
            String pesticidName = item.pesticideName;
            String stageName = item.samplingStageType;
            agriPesticideResidueStandard firstStandard;

            if (!item.checkIsUseful()){
                continue;//没通过数据可用审查，跳过当前的检测条目
            }
            //获取对应标准//在这里可以获取多种标准
            PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
            List<agriPesticideResidueStandard> standardslist = outVegNoBanPesDetRecordsMapper.getagriPesticideResidueStandard(pesticidName, vegFruName);
            if(!standardslist.isEmpty()){
                firstStandard = standardslist.get(0);
            }else {
                System.out.println("没有对应标准"+"/n蔬果名:"+vegFruName+"/n农药名:"+pesticidName);
                continue;
            }

            //计算相应属性
            if(pesticideResultMap.get(pesticidName)!=null){//在检测的列表中
                pesticideResultMap.get(pesticidName).totalDet += 1;//总检出
                pesticideResultMap.get(pesticidName).addOneToStageName(stageName);//加到对应属性上
                if (item.pesticideDetValue > firstStandard.standardValue) {//此处先仅对比第一个标准值
                    pesticideResultMap.get(pesticidName).totalEx += 1;//总超标
                    pesticideResultMap.get(pesticidName).addOneToStageName(stageName + "Ex");//超标
                }
            }

        }

        //把Map里的东西装进去
        Set<Map.Entry<String, outReturnType>> entrySet=pesticideResultMap.entrySet();
        outReturnType total =new outReturnType("合计");
        for (Map.Entry<String, outReturnType> entryItem:entrySet) {
            returnResult.add(entryItem.getValue());
            total.addToTotal(entryItem.getValue());
        }
        returnResult.add(total);
        //返回结果
        return returnResult;
    }
}
