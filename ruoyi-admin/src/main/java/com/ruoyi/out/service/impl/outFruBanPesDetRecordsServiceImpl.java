package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outFruBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outFruBanPesDetRecords;
import com.ruoyi.out.service.IoutFruBanPesDetRecordsService;

import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;

import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
/**
 * 水果禁用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outFruBanPesDetRecordsServiceImpl implements IoutFruBanPesDetRecordsService 
{
    @Autowired
    private outFruBanPesDetRecordsMapper outFruBanPesDetRecordsMapper;
    @Autowired
    private agriPesticideDetResultMapper agriPesticideDetResultMapper;
    @Autowired
    private agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;


    /**
     * 查询水果禁用农药检出及超标情况
     * 
     * @param fruBanPesDetRecordsId 水果禁用农药检出及超标情况主键
     * @return 水果禁用农药检出及超标情况
     */
    @Override
    public outFruBanPesDetRecords selectoutFruBanPesDetRecordsByFruBanPesDetRecordsId(Long fruBanPesDetRecordsId)
    {
        return outFruBanPesDetRecordsMapper.selectoutFruBanPesDetRecordsByFruBanPesDetRecordsId(fruBanPesDetRecordsId);
    }

    /**
     * 查询水果禁用农药检出及超标情况列表
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 水果禁用农药检出及超标情况
     */
    @Override
    public List<outFruBanPesDetRecords> selectoutFruBanPesDetRecordsList(outFruBanPesDetRecords outFruBanPesDetRecords)
    {
        return outFruBanPesDetRecordsMapper.selectoutFruBanPesDetRecordsList(outFruBanPesDetRecords);
    }

    /**
     * 新增水果禁用农药检出及超标情况
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutFruBanPesDetRecords(outFruBanPesDetRecords outFruBanPesDetRecords)
    {
        return outFruBanPesDetRecordsMapper.insertoutFruBanPesDetRecords(outFruBanPesDetRecords);
    }

    /**
     * 修改水果禁用农药检出及超标情况
     * 
     * @param outFruBanPesDetRecords 水果禁用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutFruBanPesDetRecords(outFruBanPesDetRecords outFruBanPesDetRecords)
    {
        return outFruBanPesDetRecordsMapper.updateoutFruBanPesDetRecords(outFruBanPesDetRecords);
    }

    /**
     * 批量删除水果禁用农药检出及超标情况
     * 
     * @param fruBanPesDetRecordsIds 需要删除的水果禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsIds(Long[] fruBanPesDetRecordsIds)
    {
        return outFruBanPesDetRecordsMapper.deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsIds(fruBanPesDetRecordsIds);
    }

    /**
     * 删除水果禁用农药检出及超标情况信息
     * 
     * @param fruBanPesDetRecordsId 水果禁用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsId(Long fruBanPesDetRecordsId)
    {
        return outFruBanPesDetRecordsMapper.deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsId(fruBanPesDetRecordsId);
    }
    @Override
    public List<outReturnType> selectoutFruBanPesDetRecordsList2(outReturnType outReturnTypeRecords) {
        List<outReturnType> returnResult = new ArrayList<outReturnType>();//生产原始返回值结果，农药名及全为0的其他值
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<String> pesticideList = outFruBanPesDetRecordsMapper.getFruBanPesticideList();//可以在此处设置农药列表//也可查询获取列表
        Map<String, outReturnType> pesticideResultMap = new TreeMap<String, outReturnType>();//使用字典存储
        for (String pesticideName : pesticideList) {//初始化
            pesticideResultMap.put(pesticideName, new outReturnType(pesticideName));
        }
        PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
        List<outFruVegSelectType> SelectList = outFruBanPesDetRecordsMapper.getFruVegDetResultList();//获取农药检测结果表

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
            PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
            List<agriPesticideResidueStandard> standardslist = outFruBanPesDetRecordsMapper.getagriPesticideResidueStandard(pesticidName, vegFruName);
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
