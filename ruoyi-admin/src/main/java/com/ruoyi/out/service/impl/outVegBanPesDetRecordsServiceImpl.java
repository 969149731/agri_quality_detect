package com.ruoyi.out.service.impl;

import java.util.*;

import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outVegBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outVegBanPesDetRecords;
import com.ruoyi.out.service.IoutVegBanPesDetRecordsService;

/**
 * 蔬菜禁用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outVegBanPesDetRecordsServiceImpl implements IoutVegBanPesDetRecordsService 
{
    @Autowired
    private outVegBanPesDetRecordsMapper outVegBanPesDetRecordsMapper;

    /**
     * 查询蔬菜禁用农药检出及超标情况
     * 
     * @param vegBanPesDetRecordsId 蔬菜禁用农药检出及超标情况主键
     * @return 蔬菜禁用农药检出及超标情况
     */
    @Override
    public outVegBanPesDetRecords selectoutVegBanPesDetRecordsByVegBanPesDetRecordsId(Long vegBanPesDetRecordsId)
    {
        return outVegBanPesDetRecordsMapper.selectoutVegBanPesDetRecordsByVegBanPesDetRecordsId(vegBanPesDetRecordsId);
    }

    /**
     * 查询蔬菜禁用农药检出及超标情况列表
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 蔬菜禁用农药检出及超标情况
     */
    @Override
    public List<outVegBanPesDetRecords> selectoutVegBanPesDetRecordsList(outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        return outVegBanPesDetRecordsMapper.selectoutVegBanPesDetRecordsList(outVegBanPesDetRecords);
    }

    /**
     * 新增蔬菜禁用农药检出及超标情况
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutVegBanPesDetRecords(outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        return outVegBanPesDetRecordsMapper.insertoutVegBanPesDetRecords(outVegBanPesDetRecords);
    }

    /**
     * 修改蔬菜禁用农药检出及超标情况
     * 
     * @param outVegBanPesDetRecords 蔬菜禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutVegBanPesDetRecords(outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        return outVegBanPesDetRecordsMapper.updateoutVegBanPesDetRecords(outVegBanPesDetRecords);
    }

    /**
     * 批量删除蔬菜禁用农药检出及超标情况
     * 
     * @param vegBanPesDetRecordsIds 需要删除的蔬菜禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsIds(Long[] vegBanPesDetRecordsIds)
    {
        return outVegBanPesDetRecordsMapper.deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsIds(vegBanPesDetRecordsIds);
    }

    /**
     * 删除蔬菜禁用农药检出及超标情况信息
     * 
     * @param vegBanPesDetRecordsId 蔬菜禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsId(Long vegBanPesDetRecordsId)
    {
        return outVegBanPesDetRecordsMapper.deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsId(vegBanPesDetRecordsId);
    }



    public List<outReturnType> selectoutVegBanPesDetRecordsList2(){
        List<outReturnType> returnResult = new ArrayList<outReturnType>();//生产原始返回值结果，农药名及全为0的其他值
        List<String> pesticideList = outVegBanPesDetRecordsMapper.getVegBanPesticideList();//可以在此处设置农药列表//也可查询获取列表
        Map<String, outReturnType> pesticideResultMap = new TreeMap<String, outReturnType>();//使用字典存储
        for (String pesticideName : pesticideList) {//初始化
            pesticideResultMap.put(pesticideName, new outReturnType(pesticideName));
        }
        List<outFruVegSelectType> SelectList = outVegBanPesDetRecordsMapper.getFruVegDetResultList();//获取农药检测结果表

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

            //获取蔬菜名//用于获取标准
            String vegFruName = item.vegFruName;
            String pesticidName = item.pesticideName;
            String stageName = item.samplingStageType;
            agriPesticideResidueStandard firstStandard;
            //获取对应标准//在这里可以获取多种标准
            List<agriPesticideResidueStandard> standardslist = outVegBanPesDetRecordsMapper.getagriPesticideResidueStandard(pesticidName, vegFruName);
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
