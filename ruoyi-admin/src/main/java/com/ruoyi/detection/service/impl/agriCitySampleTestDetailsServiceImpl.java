package com.ruoyi.detection.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.detection.domain.agriOutCitySampleTestDetails;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
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
        long mainTableId =0;
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
                Long samplingBase = agriOutCitySampleTestDetails.getSamplingBase();
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
