package com.ruoyi.detection.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.detection.domain.agriOut2CitySampleTestDetails;
import com.ruoyi.detection.domain.agriOutCitySampleTestDetails;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.service.IagriCitySampleTestDetailsService;

import javax.validation.Validator;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 各市样品检测结果详细Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-24
 */
@Service
public class agriCitySampleTestDetailsServiceImpl implements IagriCitySampleTestDetailsService 
{
    @Autowired
    private agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;
    @Autowired
    private agriPesticideDetResultMapper agriPesticideDetResultMapper;
    @Autowired
    private ISysConfigService configService;
    @Autowired
    protected Validator validator;

    /**
     * 查询各市样品检测结果详细
     * 
     * @param citySampleTestDetailsId 各市样品检测结果详细主键
     * @return 各市样品检测结果详细
     */
    @Override
    public agriCitySampleTestDetails selectagriCitySampleTestDetailsByCitySampleTestDetailsId(Long citySampleTestDetailsId)
    {
        return agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsByCitySampleTestDetailsId(citySampleTestDetailsId);
    }

    /**
     * 查询各市样品检测结果详细列表
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 各市样品检测结果详细
     */
    @Override
    public List<agriCitySampleTestDetails> selectagriCitySampleTestDetailsList(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        return agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
    }

    /**
     * 新增各市样品检测结果详细
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 结果
     */
    @Transactional
    @Override
    public int insertagriCitySampleTestDetails(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        int rows = agriCitySampleTestDetailsMapper.insertagriCitySampleTestDetails(agriCitySampleTestDetails);
        insertagriPesticideDetResult(agriCitySampleTestDetails);
        return rows;
    }

    /**
     * 修改各市样品检测结果详细
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 结果
     */
    @Transactional
    @Override
    public int updateagriCitySampleTestDetails(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        agriCitySampleTestDetailsMapper.deleteagriPesticideDetResultByCitySampleTestDetailsId(agriCitySampleTestDetails.getCitySampleTestDetailsId());
        insertagriPesticideDetResult(agriCitySampleTestDetails);
        return agriCitySampleTestDetailsMapper.updateagriCitySampleTestDetails(agriCitySampleTestDetails);
    }

    /**
     * 批量删除各市样品检测结果详细
     * 
     * @param citySampleTestDetailsIds 需要删除的各市样品检测结果详细主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(Long[] citySampleTestDetailsIds)
    {
        agriCitySampleTestDetailsMapper.deleteagriPesticideDetResultByCitySampleTestDetailsIds(citySampleTestDetailsIds);
        return agriCitySampleTestDetailsMapper.deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(citySampleTestDetailsIds);
    }

    /**
     * 删除各市样品检测结果详细信息
     * 
     * @param citySampleTestDetailsId 各市样品检测结果详细主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsId(Long citySampleTestDetailsId)
    {
        agriCitySampleTestDetailsMapper.deleteagriPesticideDetResultByCitySampleTestDetailsId(citySampleTestDetailsId);
        return agriCitySampleTestDetailsMapper.deleteagriCitySampleTestDetailsByCitySampleTestDetailsId(citySampleTestDetailsId);
    }

    @Override
    public String importAgriCitySampleTestDetails(List<agriCitySampleTestDetails> agriCitySampleTestDetailsList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(agriCitySampleTestDetailsList) || agriCitySampleTestDetailsList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (agriCitySampleTestDetails agriCitySampleTestDetails : agriCitySampleTestDetailsList) {
            try {
//                agriCitySampleTestDetailsMapper.insertfruitVegQualification(agriCitySampleTestDetails);
                  agriCitySampleTestDetailsMapper.insertagriCitySampleTestDetails(agriCitySampleTestDetails);

                  List<agriPesticideDetResult> agriPesticideDetResultList = agriCitySampleTestDetails.getAgriPesticideDetResultList();
                  for (agriPesticideDetResult agriPesticideDetResults:agriPesticideDetResultList){
                      agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResults);
                      System.out.println("---------------------------------------------------------");

                  }

                successNum++;
                successMsg.append("<br/>" +"第"+ successNum +"条"+ "数据导入成功");

            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + "第"+ failureNum +"条"+ "数据导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0) {
            failureMsg.insert(0, "抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public String importAgriOutCitySampleTestDetailsList(List<agriOutCitySampleTestDetails> agriOutCitySampleTestDetailsList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(agriOutCitySampleTestDetailsList) || agriOutCitySampleTestDetailsList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        //要用到的主表主键值
        long mainTableId =0;
        //要丢弃的主表主键值
        long noUseTableId =0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (agriOutCitySampleTestDetails agriOutCitySampleTestDetails : agriOutCitySampleTestDetailsList) {
            try {
                Long citySampleTestDetailsId = agriOutCitySampleTestDetails.getCitySampleTestDetailsId();
                String sampleCode = agriOutCitySampleTestDetails.getSampleCode();
                String vegFruName = agriOutCitySampleTestDetails.getVegFruName();
                String samplingLocation = agriOutCitySampleTestDetails.getSamplingLocation();
                String detectLocation = agriOutCitySampleTestDetails.getDetectLocation();
                Double samplingQuantity = agriOutCitySampleTestDetails.getSamplingQuantity();
                String samplingBase = agriOutCitySampleTestDetails.getSamplingBase();
                String samplingStageType = agriOutCitySampleTestDetails.getSamplingStageType();
                Date samplingDate = agriOutCitySampleTestDetails.getSamplingDate();
                String chinaStandard = agriOutCitySampleTestDetails.getChinaStandard();
                String cacStandard = agriOutCitySampleTestDetails.getCacStandard();
                String japanStandard = agriOutCitySampleTestDetails.getJapanStandard();
                String euStandard = agriOutCitySampleTestDetails.getEuStandard();
                String usStandard = agriOutCitySampleTestDetails.getUsStandard();
                String koreaStandard = agriOutCitySampleTestDetails.getKoreaStandard();

                agriCitySampleTestDetails agriCitySampleTestDetails = new agriCitySampleTestDetails();
                agriCitySampleTestDetails.setCitySampleTestDetailsId(citySampleTestDetailsId);
                agriCitySampleTestDetails.setSampleCode(sampleCode);
                agriCitySampleTestDetails.setVegFruName(vegFruName);
                agriCitySampleTestDetails.setSamplingLocation(samplingLocation);
                agriCitySampleTestDetails.setDetectLocation(detectLocation);
                agriCitySampleTestDetails.setSamplingQuantity(samplingQuantity);
                agriCitySampleTestDetails.setSamplingBase(samplingBase);
                agriCitySampleTestDetails.setSamplingStageType(samplingStageType);
                agriCitySampleTestDetails.setSamplingDate(samplingDate);
                agriCitySampleTestDetails.setChinaStandard(chinaStandard);
                agriCitySampleTestDetails.setCacStandard(cacStandard);
                agriCitySampleTestDetails.setJapanStandard(japanStandard);
                agriCitySampleTestDetails.setEuStandard(euStandard);
                agriCitySampleTestDetails.setUsStandard(usStandard);
                agriCitySampleTestDetails.setKoreaStandard(koreaStandard);

                //插入主表部分
                agriCitySampleTestDetailsMapper.insertagriCitySampleTestDetails(agriCitySampleTestDetails);
//                System.out.println("-----------------------------"+agriCitySampleTestDetails.getCitySampleTestDetailsId()+"---------------------------------------------------");
                //这个判断固定真正有用的主键值，用来传给从表也就是检测表，（随便取了3个为null值的属性判断）,如果判断的字段是空的，说明这张表是为了添加从表字段时候多余添加进来的
                if(samplingQuantity!=null&&samplingBase!=null&&samplingDate!=null){
                    //查询插入主表部分的ID  ID值更新到全集变量
                    mainTableId=agriCitySampleTestDetails.getCitySampleTestDetailsId();
//                    System.out.println(mainTableId+"----------------------------------");
                }
                agriPesticideDetResult agriPesticideDetResult= new agriPesticideDetResult();                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值
                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值,以及主表中的ID，以对应上
                agriPesticideDetResult.setCitySampleTestDetailsId(mainTableId);
                agriPesticideDetResult.setPesticideName(agriOutCitySampleTestDetails.getPesticideName());
                agriPesticideDetResult.setPesticideDetValue(agriOutCitySampleTestDetails.getPesticideDetValue());
                agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);

                //删除主表中没有信息数据的表
                if(samplingQuantity==null&&samplingBase==null&&samplingDate==null){
                    noUseTableId = agriCitySampleTestDetails.getCitySampleTestDetailsId();
                    agriCitySampleTestDetailsMapper.deleteagriCitySampleTestDetailsByCitySampleTestDetailsId(noUseTableId);
                }
                successNum++;
                successMsg.append("<br/>" +"第"+ successNum +"条"+ "数据导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + "第"+ failureNum +"条"+ "数据导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0) {
            failureMsg.insert(0, "抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public String importAgriOut2CitySampleTestDetailsList(List<agriOut2CitySampleTestDetails> agriOut2CitySampleTestDetailsList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(agriOut2CitySampleTestDetailsList) || agriOut2CitySampleTestDetailsList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy.M.d");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        int successNum = 0;
        int failureNum = 0;
        //要用到的主表主键值
        long mainTableId =0;
        //要丢弃的主表主键值
        long noUseTableId =0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (agriOut2CitySampleTestDetails agriOut2CitySampleTestDetails : agriOut2CitySampleTestDetailsList) {
            try {
                Long citySampleTestDetailsId = agriOut2CitySampleTestDetails.getCitySampleTestDetailsId();
                String sampleCode = agriOut2CitySampleTestDetails.getSampleCode();
                String vegFruName = agriOut2CitySampleTestDetails.getVegFruName();
                String samplingLocation = agriOut2CitySampleTestDetails.getSamplingLocation();
                Double samplingQuantity = agriOut2CitySampleTestDetails.getSamplingQuantity();
                String samplingBase = agriOut2CitySampleTestDetails.getSamplingBase();
                String samplingStageType = agriOut2CitySampleTestDetails.getSamplingStageType();

                Date samplingDate = originalFormat.parse(agriOut2CitySampleTestDetails.getSamplingDate());

                String chinaStandard = agriOut2CitySampleTestDetails.getChinaStandard();
                String cacStandard = agriOut2CitySampleTestDetails.getCacStandard();
                String japanStandard = agriOut2CitySampleTestDetails.getJapanStandard();
                String euStandard = agriOut2CitySampleTestDetails.getEuStandard();
                String usStandard = agriOut2CitySampleTestDetails.getUsStandard();
                String koreaStandard = agriOut2CitySampleTestDetails.getKoreaStandard();

                agriCitySampleTestDetails agriCitySampleTestDetails = new agriCitySampleTestDetails();
                agriCitySampleTestDetails.setCitySampleTestDetailsId(citySampleTestDetailsId);
                agriCitySampleTestDetails.setSampleCode(sampleCode);
                agriCitySampleTestDetails.setVegFruName(vegFruName);
                agriCitySampleTestDetails.setSamplingLocation(samplingLocation);


                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                //获取当前登录的用户名
                String username = authentication.getName();
                //通过当前登录的用户名查询到当前登录的用户所存在的部门
                String deptName = agriCitySampleTestDetailsMapper.selectUserDepByUserName(username);
                agriCitySampleTestDetails.setDetectLocation(deptName);

//                System.out.println("---------"+username);

                agriCitySampleTestDetails.setSamplingQuantity(samplingQuantity);
                agriCitySampleTestDetails.setSamplingBase(samplingBase);
                agriCitySampleTestDetails.setSamplingStageType(samplingStageType);
                agriCitySampleTestDetails.setSamplingDate(samplingDate);
                agriCitySampleTestDetails.setChinaStandard(chinaStandard);
                agriCitySampleTestDetails.setCacStandard(cacStandard);
                agriCitySampleTestDetails.setJapanStandard(japanStandard);
                agriCitySampleTestDetails.setEuStandard(euStandard);
                agriCitySampleTestDetails.setUsStandard(usStandard);
                agriCitySampleTestDetails.setKoreaStandard(koreaStandard);
                //插入主表部分
                agriCitySampleTestDetailsMapper.insertagriCitySampleTestDetails(agriCitySampleTestDetails);
//                System.out.println("-----------------------------"+agriCitySampleTestDetails.getCitySampleTestDetailsId()+"---------------------------------------------------");
                //这个判断固定真正有用的主键值，用来传给从表也就是检测表，（随便取了3个为null值的属性判断）,如果判断的字段是空的，说明这张表是为了添加从表字段时候多余添加进来的
//                if(samplingQuantity!=null&&samplingBase!=null&&samplingDate!=null){
                    //查询插入主表部分的ID  ID值更新到全集变量
                    mainTableId=agriCitySampleTestDetails.getCitySampleTestDetailsId();
//                    System.out.println(mainTableId+"----------------------------------");
//                }
                agriPesticideDetResult agriPesticideDetResult= new agriPesticideDetResult();                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值
                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值,以及主表中的ID，以对应上
                agriPesticideDetResult.setCitySampleTestDetailsId(mainTableId);
                if(agriOut2CitySampleTestDetails.getNy1()!=null){
                    agriPesticideDetResult.setPesticideName("甲胺磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy1());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy2()!=null){
                    agriPesticideDetResult.setPesticideName("乙酰甲胺磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy2());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy3()!=null){
                    agriPesticideDetResult.setPesticideName("甲拌磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy3());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy4()!=null){
                    agriPesticideDetResult.setPesticideName("氧乐果");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy4());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy5()!=null){
                    agriPesticideDetResult.setPesticideName("毒死蜱");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy5());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy6()!=null){
                    agriPesticideDetResult.setPesticideName("特丁硫磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy6());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy7()!=null){
                    agriPesticideDetResult.setPesticideName("三唑磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy7());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy8()!=null){
                    agriPesticideDetResult.setPesticideName("水胺硫磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy8());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy9()!=null){
                    agriPesticideDetResult.setPesticideName("治螟磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy9());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy10()!=null){
                    agriPesticideDetResult.setPesticideName("乐果");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy10());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy11()!=null){
                    agriPesticideDetResult.setPesticideName("甲基异柳磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy11());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy12()!=null){
                    agriPesticideDetResult.setPesticideName("氟虫腈");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy12());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy13()!=null){
                    agriPesticideDetResult.setPesticideName("克百威");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy13());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy14()!=null){
                    agriPesticideDetResult.setPesticideName("联苯菊酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy14());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy15()!=null){
                    agriPesticideDetResult.setPesticideName("氯氟氰菊酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy15());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy16()!=null){
                    agriPesticideDetResult.setPesticideName("氟氯氰菊酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy16());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy17()!=null){
                    agriPesticideDetResult.setPesticideName("氯氰菊酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy17());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy18()!=null){
                    agriPesticideDetResult.setPesticideName("甲氰菊酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy18());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy19()!=null){
                    agriPesticideDetResult.setPesticideName("溴氰菊酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy19());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy20()!=null){
                    agriPesticideDetResult.setPesticideName("三唑酮");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy20());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy21()!=null){
                    agriPesticideDetResult.setPesticideName("百菌清");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy21());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy22()!=null){
                    agriPesticideDetResult.setPesticideName("腐霉利");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy22());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy23()!=null){
                    agriPesticideDetResult.setPesticideName("丙溴磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy23());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy24()!=null){
                    agriPesticideDetResult.setPesticideName("多菌灵");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy24());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy25()!=null){
                    agriPesticideDetResult.setPesticideName("啶虫脒");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy25());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy26()!=null){
                    agriPesticideDetResult.setPesticideName("苯醚甲环唑");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy26());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy27()!=null){
                    agriPesticideDetResult.setPesticideName("阿维菌素");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy27());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy28()!=null){
                    agriPesticideDetResult.setPesticideName("甲氨基阿维菌素苯甲酸盐");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy28());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy29()!=null){
                    agriPesticideDetResult.setPesticideName("氟啶脲");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy29());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy30()!=null){
                    agriPesticideDetResult.setPesticideName("灭幼脲");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy30());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy31()!=null){
                    agriPesticideDetResult.setPesticideName("灭蝇胺");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy31());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy32()!=null){
                    agriPesticideDetResult.setPesticideName("噻虫嗪");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy32());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy33()!=null){
                    agriPesticideDetResult.setPesticideName("甲霜灵");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy33());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy34()!=null){
                    agriPesticideDetResult.setPesticideName("霜霉威");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy34());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy35()!=null){
                    agriPesticideDetResult.setPesticideName("吡唑醚菌酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy35());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy36()!=null){
                    agriPesticideDetResult.setPesticideName("氯吡脲");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy36());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy37()!=null){
                    agriPesticideDetResult.setPesticideName("嘧霉胺");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy37());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy38()!=null){
                    agriPesticideDetResult.setPesticideName("吡虫啉");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy38());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy39()!=null){
                    agriPesticideDetResult.setPesticideName("呋虫胺");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy39());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy40()!=null){
                    agriPesticideDetResult.setPesticideName("虱螨脲");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy40());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy41()!=null){
                    agriPesticideDetResult.setPesticideName("倍硫磷");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy41());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy42()!=null){
                    agriPesticideDetResult.setPesticideName("抑霉唑");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy42());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy43()!=null){
                    agriPesticideDetResult.setPesticideName("敌敌畏");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy43());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy44()!=null){
                    agriPesticideDetResult.setPesticideName("甲基毒死蜱");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy44());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy45()!=null){
                    agriPesticideDetResult.setPesticideName("氰戊菊酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy45());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy46()!=null){
                    agriPesticideDetResult.setPesticideName("异菌脲");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy46());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy47()!=null){
                    agriPesticideDetResult.setPesticideName("涕灭威");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy47());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy48()!=null){
                    agriPesticideDetResult.setPesticideName("烯酰吗啉");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy48());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy49()!=null){
                    agriPesticideDetResult.setPesticideName("炔螨特");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy49());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy50()!=null){
                    agriPesticideDetResult.setPesticideName("乙螨唑");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy50());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy51()!=null){
                    agriPesticideDetResult.setPesticideName("嘧菌酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy51());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy52()!=null){
                    agriPesticideDetResult.setPesticideName("噻虫胺");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy52());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy53()!=null){
                    agriPesticideDetResult.setPesticideName("丙环唑");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy53());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy54()!=null){
                    agriPesticideDetResult.setPesticideName("螺虫乙酯");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy54());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy55()!=null){
                    agriPesticideDetResult.setPesticideName("哒螨灵");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy55());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy56()!=null){
                    agriPesticideDetResult.setPesticideName("四螨嗪");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy56());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy57()!=null){
                    agriPesticideDetResult.setPesticideName("虫螨腈");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy57());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }
                if(agriOut2CitySampleTestDetails.getNy58()!=null){
                    agriPesticideDetResult.setPesticideName("咪鲜胺");
                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy58());
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }

                successNum++;
                successMsg.append("<br/>" +"第"+ successNum +"条"+ "数据导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + "第"+ failureNum +"条"+ "数据导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0) {
            failureMsg.insert(0, "抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }


    /**
     * 新增农药检测结果信息
     * 
     * @param agriCitySampleTestDetails 各市样品检测结果详细对象
     */
    public void insertagriPesticideDetResult(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        List<agriPesticideDetResult> agriPesticideDetResultList = agriCitySampleTestDetails.getAgriPesticideDetResultList();
        Long citySampleTestDetailsId = agriCitySampleTestDetails.getCitySampleTestDetailsId();
        if (StringUtils.isNotNull(agriPesticideDetResultList))
        {
            List<agriPesticideDetResult> list = new ArrayList<agriPesticideDetResult>();
            for (agriPesticideDetResult agriPesticideDetResult : agriPesticideDetResultList)
            {
                agriPesticideDetResult.setCitySampleTestDetailsId(citySampleTestDetailsId);
                list.add(agriPesticideDetResult);
            }
            if (list.size() > 0)
            {
                agriCitySampleTestDetailsMapper.batchagriPesticideDetResult(list);
            }
        }
    }
}
