package com.ruoyi.out.service.impl;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.out.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outStandComplianceMapper;
import com.ruoyi.out.service.IoutStandComplianceService;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

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
    @Autowired
    private com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;
    /////////查询时使用的全局变量
    List<String> pesticideList;//可以在此处设置农药列表//也可查询获取列表
    Map<String, outStandardReturnType> resultMap;//使用字典存储
    List<outStandardReturnType> resultList;//生成原始返回值结果，农药名及全为0的其他值
    outStandardReturnType sampleNum;//最后才放入结果
    outStandardReturnType passNum;//最后才放入结果
    returnMsgHandler MsgHandler = new returnMsgHandler();

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

    public List<outStandardReturnType> selectoutStandComplianceList2(agriCitySampleTestDetails agriCitySampleTestDetails,StringBuilder feedBackMsg)
    {//为避免多一次交互,将合格率计算放到前端进行1
        if (initModule(feedBackMsg));
        else return returnFinalList();

        //获取所有检测结果
        PageHelper.startPage(0,0,false,false,true);//解除分页方法，仅对之后第一个查询生效
        List<agriCitySampleTestDetails> SelectList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);//获取农药检测结果表
        if (SelectList.isEmpty()){System.out.println("样本查询结果为空");}
        if(SelectList.isEmpty()){
            log.debug("查询出的样本列表为空");
            return returnFinalList();
        }

        //先遍历所有获取到的结果//以id标识一组检测结果（即一个样本）,所以默认id是存在的，事实上id由数据库生成，肯定存在
        List<outFruVegSelectType2> itemList=new ArrayList<>();//初始化
        Long sampleId = SelectList.get(0).getCitySampleTestDetailsId();//对于经过编译器生成的列表对象而言，其执行顺序的正确性是保证的，列表的第一个等同于for中执行的第一个
        for (agriCitySampleTestDetails item : SelectList) {
            PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
            itemList=outStandComplianceMapper.getFruVegDetResultList(item);
            SamplePasscCheck(itemList);
        }
        MsgHandler.turnToStr();
        return returnFinalList();
    }

    //工具方法
    //判断样本是否合格，1为合格，0为不合格
    void SamplePasscCheck(List<outFruVegSelectType2> itemList){
        outStandardReturnType result =new outStandardReturnType();
        result.setAll(1);//初始均为合格
        List<String> standardType= Arrays.asList("国家标准", "CAC","欧盟","美国","日本","韩国");//用于遍历,国家标准比较特殊单独判断
        sampleNum.AlladdOne();
        switch (sampleCheck(itemList)){
            case 1:
                return;
            case 0:
                break;
            case -1:
                return;
        }

        for (outFruVegSelectType2 item: itemList){
            //遍历所有检出的农药
            //获取蔬菜名//用于获取标准
            String vegFruName = item.vegFruName;
            String pesticideName = item.pesticideName;
            boolean CNPassInfoByUser =item.chinaStandard==null?false: item.chinaStandard.equals("合格");//初始设定
            switch (checkIsUseful(item)){
                case 1://缺少必要信息
                   continue;//默认合格？，返回初始均为合格的情况
                case 2:
                    log.debug("该农药不在检测范围中");
                    continue;//该农药不在检测范围中，请检查
                case 0:
                    break;
                case -1://错误
                    continue;
            }

            //获取对应标准//可能取得多个标准
            PageHelper.startPage(0,0,false,false,true);//分页方法，仅对之后第一个查询生效
            List<agriPesticideResidueStandard> standardslist = outStandComplianceMapper.getagriPesticideResidueStandard(pesticideName, vegFruName);
            if (standardslist.isEmpty()){
                MsgHandler.addMsgTitle("没有任何标准","没有任何标准，请在农药标准字典中添加或检查样本的农药名和蔬菜名");
                MsgHandler.addMsg("没有任何标准", " 样本编号:" + item.sampleCode + " 蔬果名:" + item.vegFruName + " 农药名:" + item.pesticideName);
                //国家标准判断
                if (IsPassUnderUserInfo("国家标准",item,CNPassInfoByUser)){//使用用户输入的信息，为空时也视为不合格
                    CNPassInfoByUser = true;
                }else {
                    CNPassInfoByUser = false;
                    outStandardReturnType pesticide = resultMap.get(pesticideName);
                    pesticide.addOne("国家标准");//对应农药的对应标准超标数+1
                    result.setOne("国家标准",0);
                }
                //其它标准判断
                for (String standardName:standardType){
                    if (IsPassUnderUserInfo(standardName,item,CNPassInfoByUser)){//使用用户输入的信息，为空时也视为不合格
                    }else {
                        outStandardReturnType pesticide = resultMap.get(pesticideName);
                        pesticide.addOne(standardName);//对应农药的对应标准超标数+1
                        result.setOne(standardName,0);
                    }
                }
                continue;//该检测结果没有对应标准,直接下一条
            }
            //计算相应属性
            Map<String, agriPesticideResidueStandard> standardMap = new TreeMap<>();//使用字典存储
            for (agriPesticideResidueStandard standard:standardslist) {//初始化Map，用于对所有现有的参考标准进行查询
                standardMap.put(standard.standardCategory, standard);
            }
            agriPesticideResidueStandard cnStandard = standardMap.get("国家标准");//国家标准
            //对应的标准存在且相应标准值存在，对农药进行判断和统计
            //国家标准
            if(IsPassUnderChinaStandard(item,cnStandard)){//对应的标准不存在，即查询标准返回的结果列表中没有
                CNPassInfoByUser=true;
            }else {
                outStandardReturnType pesticide = resultMap.get(pesticideName);
                CNPassInfoByUser=false;
                result.setOne("国家标准",0);//不合格
                pesticide.addOne("国家标准");//对应农药的对应标准超标数+1
            }
            outStandardReturnType pesticide = resultMap.get(pesticideName);
            for (String standardName:standardType){
                //开始依次判断其它标准
                agriPesticideResidueStandard nowStandard = standardMap.get(standardName);//当前标准
                if(nowStandard==null || nowStandard.standardValue==null){//对应的标准不存在，即查询标准返回的结果列表中没有
                    System.out.println("没有相应"+standardName+"标准");
                    if (IsPassUnderUserInfo(standardName,item,CNPassInfoByUser)){//使用用户输入的信息，为空时也视为不合格

                    }else {
                        result.setOne(standardName,0);
                    }
                    continue;
                }
                //对应的标准存在且相应标准值存在，对农药进行判断和统计
                if (item.pesticideDetValue > nowStandard.standardValue) {//超标
                    result.setOne(standardName,0);//不合格
                    pesticide.addOne(standardName);//对应农药的对应标准超标数+1
                }else{//在该标准下该种农药合格//但样品合格要看所有农药
                    //已默认合格，无操作
                }
            }
        }
        passNum.addAll(result);//合格数//该样本在所有标准下的结果均体现在其中
        return ;//正常退出
    }
    public boolean initModule(StringBuilder feedBackMsg){
        try {
            MsgHandler.initReturnMsg(feedBackMsg);

            resultList = new ArrayList<>();//生成原始返回值结果，农药名及全为0的其他值
            sampleNum= new outStandardReturnType("抽样数");//最后才放入结果
            passNum= new outStandardReturnType("合格数");//最后才放入结果

            //结果初始化
            PageHelper.startPage(0,0,false,false,true);//解除分页方法，仅对之后第一个查询生效
            pesticideList = outStandComplianceMapper.getAllPesticideList();//可以在此处设置农药列表//也可查询获取列表
            resultMap = new TreeMap<>();//使用字典存储
            for (String pesticideName : pesticideList) {//初始化
                resultMap.put(pesticideName, new outStandardReturnType(pesticideName));
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public int sampleCheck(List<outFruVegSelectType2> itemList){
        if (itemList==null) return 1;
        if(itemList.size()==0) return 1;//为0是不正常的样本
        outFruVegSelectType2 firstitem =itemList.get(0);//样本整体审查，第一个的样本信息能够代表整个列表的样本信息

        firstitem.fixData();
        try{
            if (itemList.size()==1 && firstitem.pesticideName==null && firstitem.pesticideDetValue==null){//无农药检出，必定合格
                log.debug("该条目下无检出农药"+"/r/n蔬果名:"+firstitem.vegFruName+"样品编号"+firstitem.sampleCode);
                passNum.AlladdOne();
                return 1;//退出
            }
            if (firstitem.vegFruName==null||firstitem.vegFruName.equals("")){//缺少蔬菜名，整个样本无法进行超标判断
                    MsgHandler.addMsg("部分信息有误,请在定量检测导入表中检查下列样本的信息:"," 样品编号:"+firstitem.sampleCode+" 蔬果名:"+firstitem.vegFruName+"(蔬菜名缺失)");
                    return 1;
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
    public int checkIsUseful(outFruVegSelectType2 item){
        try{
            if(item.pesticideName==null||item.pesticideName.equals("") || item.pesticideDetValue==null){//农药名
                MsgHandler.addMsgTitle("信息缺失","该检测结果缺少必要信息，请在定量检测导入表中检查样本的信息是否正确:");
                MsgHandler.addMsg("信息缺失"," 样品编号"+item.sampleCode+" 蔬果名:"+item.vegFruName+" 农药名:"+item.pesticideName+" 农药检出值:"+item.pesticideDetValue+"(信息缺失)");
                return 1;
            }
            if(resultMap.get(item.pesticideName)==null){
                log.debug("改农药不在当前检测范围内"+item.pesticideName);
                return 1;//不在检测范围内
            }
        }catch (Exception e){
            log.error("捕获异常",e);
            return -1;//有异常
        }
        return 0;//到此说明数据可用
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

    public boolean IsPassUnderChinaStandard(outFruVegSelectType2 sample,agriPesticideResidueStandard cnStandard){//判断是否在中国标准下合格
        if (cnStandard==null || cnStandard.standardValue==null){//标准不存在
            System.out.println("没有相应国家标准");
            //使用用户输入的信息，为空时也视为不合格
            if (sample.chinaStandard != null && sample.chinaStandard.equals("合格"))
                return true;//用户判断合格
            else return false;
        }
        else {
            //对应的标准存在且相应标准值存在，对农药进行判断和统计
            if (sample.pesticideDetValue > cnStandard.standardValue) {//超标
                return false;//超标
            }else{//在该标准下该种农药合格//但样品合格要看所有农药
                return true;
            }
        }
    }
    //直接使用用户输入信息进行合格判断
    public boolean IsPassUnderUserInfo(String standardName, outFruVegSelectType2 sample,boolean CNPassInfo){
        String isPass = sample.getStandardByName(standardName);
        if (isPass !=null && isPass.equals("合格")){
            return true;
        }
        else if (isPass !=null && isPass.equals("不合格")){
            return false;
        }
        else if (isPass ==null){
            if (CNPassInfo){
                return true;
            }
            else return false;
        }
        else return false;
    }
    public outStandardReturnType IsPassUnderAllStandard(outFruVegSelectType2 sample){
        outStandardReturnType answer = new outStandardReturnType();
        answer.setAll(1);
                if (sample.chinaStandard!=null && sample.chinaStandard.equals("合格"))  ;
                    else answer.setOne("国家标准",0);
                if (sample.cacStandard!=null &&sample.cacStandard.equals("合格")) ;
                    else answer.setOne("CAC",0);
                if (sample.euStandard!=null &&sample.euStandard.equals("合格")) ;
                else answer.setOne("欧盟",0);
                if (sample.usStandard!=null &&sample.usStandard.equals("合格")) ;
                else answer.setOne("美国",0);
                if (sample.koreaStandard!=null &&sample.koreaStandard.equals("合格")) ;
                else answer.setOne("韩国",0);
                if (sample.japanStandard!=null &&sample.japanStandard.equals("合格")) ;
                else answer.setOne("日本",0);
        return answer;
    }
    public List<outStandardReturnType> returnFinalList(){

        //先打包
        for (String pesticideName : pesticideList) {//初始化
            resultList.add(resultMap.get(pesticideName));
        }

        //计算抽样数合格数，合格率在前端计算，因为合格率要保留小数，但是传去的类型为int
        outStandardReturnType totalEx= new outStandardReturnType("各国超标数");//不放入结果
        for (outStandardReturnType oneOfList : resultList) {//初始化
            totalEx.addAll(oneOfList);
        }

        //末尾加2个，前端会将其删除
        resultList.add(sampleNum);//抽样数
        resultList.add(passNum);//合格数
//        System.out.println("当前农药数数"+pesticideList.size());
        return resultList;
    }
}
