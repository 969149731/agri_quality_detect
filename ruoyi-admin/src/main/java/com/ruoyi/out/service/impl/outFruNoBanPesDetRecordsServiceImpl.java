package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.out.domain.agriPesticideResidueStandard;
import com.ruoyi.out.domain.outFruVegSelectType;
import com.ruoyi.out.domain.outReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outFruNoBanPesDetRecordsMapper;
import com.ruoyi.out.domain.outFruNoBanPesDetRecords;
import com.ruoyi.out.service.IoutFruNoBanPesDetRecordsService;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 水果非禁止使用农药检出及超标情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outFruNoBanPesDetRecordsServiceImpl implements IoutFruNoBanPesDetRecordsService 
{
    @Autowired
    private outFruNoBanPesDetRecordsMapper outFruNoBanPesDetRecordsMapper;

    /**
     * 查询水果非禁止使用农药检出及超标情况
     * 
     * @param fruNoBanPesDetRecordsId 水果非禁止使用农药检出及超标情况主键
     * @return 水果非禁止使用农药检出及超标情况
     */
    @Override
    public outFruNoBanPesDetRecords selectoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(Long fruNoBanPesDetRecordsId)
    {
        return outFruNoBanPesDetRecordsMapper.selectoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(fruNoBanPesDetRecordsId);
    }

    /**
     * 查询水果非禁止使用农药检出及超标情况列表
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 水果非禁止使用农药检出及超标情况
     */
    @Override
    public List<outFruNoBanPesDetRecords> selectoutFruNoBanPesDetRecordsList(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return outFruNoBanPesDetRecordsMapper.selectoutFruNoBanPesDetRecordsList(outFruNoBanPesDetRecords);
    }

    /**
     * 新增水果非禁止使用农药检出及超标情况
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int insertoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return outFruNoBanPesDetRecordsMapper.insertoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords);
    }

    /**
     * 修改水果非禁止使用农药检出及超标情况
     * 
     * @param outFruNoBanPesDetRecords 水果非禁止使用农药检出及超标情况
     * @return 结果
     */
    @Override
    public int updateoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return outFruNoBanPesDetRecordsMapper.updateoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords);
    }

    /**
     * 批量删除水果非禁止使用农药检出及超标情况
     * 
     * @param fruNoBanPesDetRecordsIds 需要删除的水果非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsIds(Long[] fruNoBanPesDetRecordsIds)
    {
        return outFruNoBanPesDetRecordsMapper.deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsIds(fruNoBanPesDetRecordsIds);
    }

    /**
     * 删除水果非禁止使用农药检出及超标情况信息
     * 
     * @param fruNoBanPesDetRecordsId 水果非禁止使用农药检出及超标情况主键
     * @return 结果
     */
    @Override
    public int deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(Long fruNoBanPesDetRecordsId)
    {
        return outFruNoBanPesDetRecordsMapper.deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(fruNoBanPesDetRecordsId);
    }

    public List<outReturnType> selectoutFruNoBanPesDetRecordsList2() {
        //初始化模块
        List<outReturnType> resultList = new ArrayList<outReturnType>();//生成原始返回值结果，农药名及全为0的其他值
        PageHelper.startPage(0, 0, false, false, true);//分页方法，仅对之后第一个查询生效
        List<String> pesticideList = outFruNoBanPesDetRecordsMapper.getFruNoBanPesticideList();//可以在此处设置农药列表//也可查询获取列表
        if (pesticideList.isEmpty()) {
            System.out.println("查询出的农药列表为空");
            return resultList;
        }
        Map<String, outReturnType> pesticideResultMap = new TreeMap<String, outReturnType>();//使用字典存储
        for (String pesticideName : pesticideList) {//初始化
            pesticideResultMap.put(pesticideName, new outReturnType(pesticideName));
        }

        PageHelper.startPage(0, 0, false, false, true);//分页方法，仅对之后第一个查询生效
        List<outFruVegSelectType> SelectList = outFruNoBanPesDetRecordsMapper.getFruVegDetResultList();//获取所有符合条件的农药检测结果表//在此处进行各类条件查询
        if (SelectList.isEmpty()) {
            System.out.println("查询出的检测结果列表为空");
            return resultList;
        }

        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
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
            String pesticideName = item.pesticideName;
            String stageName = item.samplingStageType;
            agriPesticideResidueStandard firstStandard;

            if (!item.checkIsUseful()) {//对蔬果名、农药名、生产环节进行数据审查
                String msg = "<br/>" + "第" + item.citySampleTestDetailsId + "条" + "数据无法判断";
                failureMsg.append(msg);
                log.error(msg);
                continue;//没通过数据可用审查，跳过当前的检测条目
            }
            //获取对应标准//在这里可以获取多种标准
            PageHelper.startPage(0, 0, false, false, true);//分页方法，仅对之后第一个查询生效
            List<agriPesticideResidueStandard> standardslist = outFruNoBanPesDetRecordsMapper.getagriPesticideResidueStandard(pesticideName, vegFruName);
            if (!standardslist.isEmpty()) {
                firstStandard = standardslist.get(0);
                if (firstStandard.standardValue == null) {
                    System.out.println("该标准值为空");
                    continue;
                }
            } else {
                String msg = "没有对应标准" + "/r/n蔬果名:" + vegFruName + "/n农药名:" + pesticideName;
                failureMsg.append(msg);
                log.error(msg);
                continue;
            }

            //计算相应属性//计算的逻辑可能还需要更改
            if (pesticideResultMap.get(pesticideName) != null) {//在检测的列表中
                pesticideResultMap.get(pesticideName).totalDet += 1;//总检出
                pesticideResultMap.get(pesticideName).addOneToStageName(stageName);//加到对应属性上
                if (item.pesticideDetValue > firstStandard.standardValue) {//此处先仅对比第一个标准值
                    pesticideResultMap.get(pesticideName).totalEx += 1;//总超标
                    pesticideResultMap.get(pesticideName).addOneToStageName(stageName + "Ex");//超标
                }
            }
        }

        //把Map里的东西装进去
        Set<Map.Entry<String, outReturnType>> entrySet = pesticideResultMap.entrySet();
        outReturnType total = new outReturnType("合计");
        for (Map.Entry<String, outReturnType> entryItem : entrySet) {
            resultList.add(entryItem.getValue());
            total.addToTotal(entryItem.getValue());
        }

        resultList.add(total);
        //返回结果
        return resultList;
    }
}
