package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.out.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outStandComplianceMapper;
import com.ruoyi.out.service.IoutStandComplianceService;

/**
 * 参照国际组织或国家标准合格率情况Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outStandComplianceServiceImpl implements IoutStandComplianceService 
{
    @Autowired
    private outStandComplianceMapper outStandComplianceMapper;

    /**
     * 查询参照国际组织或国家标准合格率情况
     * 
     * @param interStandId 参照国际组织或国家标准合格率情况主键
     * @return 参照国际组织或国家标准合格率情况
     */
    @Override
    public outStandCompliance selectoutStandComplianceByInterStandId(Long interStandId)
    {
        return outStandComplianceMapper.selectoutStandComplianceByInterStandId(interStandId);
    }

    /**
     * 查询参照国际组织或国家标准合格率情况列表
     * 
     * @param outStandCompliance 参照国际组织或国家标准合格率情况
     * @return 参照国际组织或国家标准合格率情况
     */
    @Override
    public List<outStandCompliance> selectoutStandComplianceList(outStandCompliance outStandCompliance)
    {
        return outStandComplianceMapper.selectoutStandComplianceList(outStandCompliance);
    }

    /**
     * 新增参照国际组织或国家标准合格率情况
     * 
     * @param outStandCompliance 参照国际组织或国家标准合格率情况
     * @return 结果
     */
    @Override
    public int insertoutStandCompliance(outStandCompliance outStandCompliance)
    {
        return outStandComplianceMapper.insertoutStandCompliance(outStandCompliance);
    }

    /**
     * 修改参照国际组织或国家标准合格率情况
     * 
     * @param outStandCompliance 参照国际组织或国家标准合格率情况
     * @return 结果
     */
    @Override
    public int updateoutStandCompliance(outStandCompliance outStandCompliance)
    {
        return outStandComplianceMapper.updateoutStandCompliance(outStandCompliance);
    }

    /**
     * 批量删除参照国际组织或国家标准合格率情况
     * 
     * @param interStandIds 需要删除的参照国际组织或国家标准合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutStandComplianceByInterStandIds(Long[] interStandIds)
    {
        return outStandComplianceMapper.deleteoutStandComplianceByInterStandIds(interStandIds);
    }

    /**
     * 删除参照国际组织或国家标准合格率情况信息
     * 
     * @param interStandId 参照国际组织或国家标准合格率情况主键
     * @return 结果
     */
    @Override
    public int deleteoutStandComplianceByInterStandId(Long interStandId)
    {
        return outStandComplianceMapper.deleteoutStandComplianceByInterStandId(interStandId);
    }

    public List<outStandardReturnType> selectoutStandComplianceList2(outStandardReturnType outStandardReturnType)
    {//为避免多一次交互,将合格率计算放到前端进行

        List<outStandardReturnType> resultList = new ArrayList<outStandardReturnType>();//生产原始返回值结果，农药名及全为0的其他值
        outStandardReturnType sampleNum= new outStandardReturnType("抽样数");//不放入结果
        outStandardReturnType passNum= new outStandardReturnType("合格数");//不放入结果

        //结果初始化
        PageHelper.startPage(0,0,false,false,true);//解除分页方法，仅对之后第一个查询生效
        List<String> pesticideList = outStandComplianceMapper.getAllPesticideList();//可以在此处设置农药列表//也可查询获取列表
        Map<String, outStandardReturnType> resultMap = new TreeMap<String, outStandardReturnType>();//使用字典存储
        for (String pesticideName : pesticideList) {//初始化
            resultMap.put(pesticideName, new outStandardReturnType(pesticideName));
        }

        //获取所有检测结果
        PageHelper.startPage(0,0,false,false,true);//解除分页方法，仅对之后第一个查询生效
        List<agriCitySampleTestDetails> SampleList = outStandComplianceMapper.getFruVegDetSample();//获取所有样本
        if (SampleList.isEmpty()){System.out.println("样本查询结果为空");return resultList;}

        //遍历所有获取到的样本
        for (agriCitySampleTestDetails sample : SampleList) {
            //判断该样本是否合格并在内部统计
            outStandardReturnType answer = SamplePasscCheck(resultMap,sample.getSampleCode());
            passNum.addAll(answer);//使用方法传回的该样本在不同标准下的合格情况，农药的超标情况在内部统计
            //System.out.println("看看answer的变化"+answer);
        }

        //先打包
        for (String pesticideName : pesticideList) {//初始化
            resultList.add(resultMap.get(pesticideName));
        }

        //计算抽样数合格数，合格率在前端计算，因为合格率要保留小数，但是传去的类型为int
        outStandardReturnType totalEx= new outStandardReturnType("各国超标数");//不放入结果
        for (outStandardReturnType oneOfList : resultList) {//初始化
            totalEx.addAll(oneOfList);
        }

        sampleNum.setAll(SampleList.size());//直接用样本的大小
        //passNum自身就可用

        //末尾加2个，前端会将其删除
        resultList.add(sampleNum);
        resultList.add(passNum);
        System.out.println("当前结果数"+SampleList.size());
        System.out.println("当前农药数数"+pesticideList.size());
        return resultList;
    }

    //工具方法
    boolean checkIsUseful(outFruVegSelectType data){
        if (data.vegFruName==null){
            System.out.println("蔬菜名缺失："+"/r/n蔬果名:"+data.vegFruName);
            return false;
        }
        return true;
    };
    //判断样本是否合格，1为合格，0为不合格
    outStandardReturnType SamplePasscCheck(Map<String, outStandardReturnType> resultMap,String SampleCode){
        outStandardReturnType result =new outStandardReturnType();
        result.setAll(0);//初始均为不合格

        //获取该样本下所有农药的检测结果
        List<outFruVegSelectType> SelectList = outStandComplianceMapper.getFruVegDetResultList(SampleCode);
        System.out.println("打印对应样本及其检测结果  样本编号："+SampleCode+" 农药列表："+SelectList);
        if (SelectList.isEmpty()){
            //防止返回空的情况，一般不会为空
            result.setAll(1);//将所有值设为1，表示该样本在所有标准下均合格
            return result;//检测合格
        }
        for (outFruVegSelectType item: SelectList){
            //获取蔬菜名//用于获取标准
            String vegFruName = item.vegFruName;
            String pesticideName = item.pesticideName;
            if(pesticideName==null && item.pesticideDetValue==null){
                System.out.println("该条目下无检出农药"+"/r/n蔬果名:"+vegFruName+"样品编号"+item.sampleCode);
                result.setAll(1);//将所有值设为1，表示该样本在所有标准下均合格
                return result;//检测合格
            }
            if (!checkIsUseful(item)){
                return result;//没通过数据可用审查,此样本均不合格
            }

            //获取对应标准//可能取得多个标准
            PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
            List<agriPesticideResidueStandard> standardslist = outStandComplianceMapper.getagriPesticideResidueStandard(pesticideName, vegFruName);
            if(!standardslist.isEmpty()){//有就啥也不做，在下面进行计算，没有就全不通过
            }else {
                System.out.println("没有任何对应标准"+"/n蔬果名:"+vegFruName+"/n农药名:"+pesticideName);
                return result;//没有标准，均不合格
            }

            //计算相应属性
            List<String> standardType= Arrays.asList("国家标准", "CAC","欧盟","美国","日本","韩国");//有农药检出就一定要看所有标准
            Map<String, agriPesticideResidueStandard> standardMap = new TreeMap<String, agriPesticideResidueStandard>();//使用字典存储
            for (agriPesticideResidueStandard standard:standardslist) {//初始化Map用于遍历所有类型的标准
                standardMap.put(standard.standardCategory, standard);
            }

            result.setAll(1);//到此默认均合格，若有任意不合格情况，将设为0
            for (String standardName:standardType){//开始依次判断所有标准
                agriPesticideResidueStandard nowStandard = standardMap.get(standardName);//当前标准
                if(nowStandard==null){//对应的标准不存在，不合格
                    result.setOne(standardName,0);
                    continue;
                }
                if(resultMap.get(pesticideName)!=null){//在检测的列表中//不在检测列表中的农药不考察
                    outStandardReturnType pesticide = resultMap.get(pesticideName);
                    if (item.pesticideDetValue > nowStandard.standardValue) {//超标
                        result.setOne(standardName,0);
                        pesticide.addOne(standardName);//对应农药的对应标准超标数+1
                    }else{//在该标准下该种农药合格//但样品合格要看所有农药
                        //已默认合格，无操作
                    }
                }
            }
        }
        return result;//正常退出//该样本在所有标准下的结果均体现在其中
    }
}
