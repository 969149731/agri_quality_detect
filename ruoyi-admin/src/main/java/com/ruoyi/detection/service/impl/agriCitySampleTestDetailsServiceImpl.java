package com.ruoyi.detection.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ruoyi.address.service.IAddressCityService;
import com.ruoyi.address.service.IAddressProvinceService;
import com.ruoyi.address.service.IAddressTownService;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.detection.domain.agriOut2CitySampleTestDetails;
import com.ruoyi.detection.domain.agriOutCitySampleTestDetails;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.service.IagriCitySampleTestDetailsService;

import javax.validation.Validator;

import static com.ruoyi.common.utils.PageUtils.startPage;
import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 各市样品检测结果详细Service业务层处理
 *
 * @author chenjie
 * @date 2024-01-24
 */
@Service
public class agriCitySampleTestDetailsServiceImpl implements IagriCitySampleTestDetailsService {
    @Autowired
    private agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;
    @Autowired
    private agriPesticideDetResultMapper agriPesticideDetResultMapper;
    @Autowired
    private ISysConfigService configService;
    @Autowired
    protected Validator validator;

    @Autowired
    private IAddressProvinceService addressProvinceService;
    @Autowired
    private IAddressCityService addressCityService;
    @Autowired
    private IAddressTownService addressTownService;


    /**
     * 查询各市样品检测结果详细
     *
     * @param citySampleTestDetailsId 各市样品检测结果详细主键
     * @return 各市样品检测结果详细
     */
    @Override
    public agriCitySampleTestDetails selectagriCitySampleTestDetailsByCitySampleTestDetailsId(Long citySampleTestDetailsId) {
        return agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsByCitySampleTestDetailsId(citySampleTestDetailsId);
    }

    /**
     * 查询各市样品检测结果详细列表
     *
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 各市样品检测结果详细
     */
    @Override
    public List<agriCitySampleTestDetails> selectagriCitySampleTestDetailsList(agriCitySampleTestDetails agriCitySampleTestDetails,String samplingType) {

//        startPage();
//        List<agriCitySampleTestDetails> agriCitySampleTestDetails1 = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取当前登录的用户名
        String username = authentication.getName();

        // 获取当前的用户信息
        LoginUser loginUser = SecurityUtils.getLoginUser();

        //用当前用户名查询当前用户所属角色，如果是属于监测机构，则只能查看自己上传过的数据
        List<SysRole> roles = loginUser.getUser().getRoles();
        String roleName = roles.get(0).getRoleName();
        if (roleName.equals("监测机构")) {
            agriCitySampleTestDetails.setUserName(username);
        }
        //如果是属于安监部门，则只能查看本辖区内的数据
        if (roleName.equals("安监部门")) {
            //获取当前安监部门 所在的部门
            SysDept sysDept = loginUser.getUser().getDept();
            String deptName = sysDept.getDeptName();
            //截取depName中的字符，从开头到市结尾的字符
            String substringDeptName = deptName.substring(0, deptName.indexOf("市") + 1);
            agriCitySampleTestDetails.setSamplingLocationCity(substringDeptName);
        }



        List<agriCitySampleTestDetails> agriCitySampleTestDetails1 = agriCitySampleTestDetailsMapper.selectAgriCitySampleList(agriCitySampleTestDetails,samplingType);
        for (agriCitySampleTestDetails agriCitySampleTestDetail : agriCitySampleTestDetails1) {
            //查询详细列表中的Id
            Long citySampleTestDetailsId = agriCitySampleTestDetail.getCitySampleTestDetailsId();
            //根据id查询农药检测结果表中的情况
            List<agriPesticideDetResult> agriPesticideDetResultList = agriCitySampleTestDetailsMapper.selectAgriCitySampleTestDetailsListById(citySampleTestDetailsId);
            agriCitySampleTestDetail.setAgriPesticideDetResultList(agriPesticideDetResultList);
        }


        try {
            //把抽样地址简化
            for (agriCitySampleTestDetails agriCitySampleTestDetail : agriCitySampleTestDetails1) {
                String samplingLocationCounty = agriCitySampleTestDetail.getSamplingLocationCounty();
                // 找到samplingLocationCounty在samplingLocation中的起始位置
                String samplingLocation = agriCitySampleTestDetail.getSamplingLocation();
                int startIndex = samplingLocation.indexOf(samplingLocationCounty);
                if (startIndex != -1) {
                    // 计算起始位置的结束索引
                    int endIndex = startIndex + samplingLocationCounty.length();
                    // 提取从endIndex开始到字符串末尾的子字符串
                    String result = samplingLocation.substring(endIndex);
                    agriCitySampleTestDetail.setSamplingLocation(result);
                } else {
                    agriCitySampleTestDetail.setSamplingLocation(samplingLocation);
                }
            }
            //同上，改溯源地
            for (agriCitySampleTestDetails agriCitySampleTestDetail : agriCitySampleTestDetails1) {
                String samplingTracingCounty = agriCitySampleTestDetail.getTracingCounty();
                // 找到samplingLocationCounty在samplingLocation中的起始位置
                String tracingArea = agriCitySampleTestDetail.getTracingArea();
                int startIndex = tracingArea.indexOf(samplingTracingCounty);
                if (startIndex != -1) {
                    // 计算起始位置的结束索引
                    int endIndex = startIndex + samplingTracingCounty.length();
                    // 提取从endIndex开始到字符串末尾的子字符串
                    String result = tracingArea.substring(endIndex);
                    agriCitySampleTestDetail.setTracingArea(result);
                } else {
                    agriCitySampleTestDetail.setTracingArea(tracingArea);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }



        return agriCitySampleTestDetails1;
        //下面的这个是原来的写法，直接返回对应的抽样地址和溯源地址
//        return agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
    }





    /**
     * 查询我的导入历史明细列表
     *
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 各市样品检测结果详细
     */
    @Override
    public List<agriCitySampleTestDetails> selectMyImportAgriCitySampleTestDetailsList(agriCitySampleTestDetails agriCitySampleTestDetails) {

//        startPage();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取当前登录的用户名
        String username = authentication.getName();
        agriCitySampleTestDetails.setUserName(username);

        List<agriCitySampleTestDetails> agriCitySampleTestDetails1 = agriCitySampleTestDetailsMapper.selectMyImportAgriCitySampleTestDetailsList(agriCitySampleTestDetails);

        try {
            //把抽样地址简化
            for (agriCitySampleTestDetails agriCitySampleTestDetail : agriCitySampleTestDetails1) {
                String samplingLocationCounty = agriCitySampleTestDetail.getSamplingLocationCounty();
                // 找到samplingLocationCounty在samplingLocation中的起始位置
                String samplingLocation = agriCitySampleTestDetail.getSamplingLocation();
                int startIndex = samplingLocation.indexOf(samplingLocationCounty);
                if (startIndex != -1) {
                    // 计算起始位置的结束索引
                    int endIndex = startIndex + samplingLocationCounty.length();
                    // 提取从endIndex开始到字符串末尾的子字符串
                    String result = samplingLocation.substring(endIndex);
                    agriCitySampleTestDetail.setSamplingLocation(result);
                } else {
                    agriCitySampleTestDetail.setSamplingLocation(samplingLocation);
                }
            }
            //同上，改溯源地
            for (agriCitySampleTestDetails agriCitySampleTestDetail : agriCitySampleTestDetails1) {
                String samplingTracingCounty = agriCitySampleTestDetail.getTracingCounty();
                // 找到samplingLocationCounty在samplingLocation中的起始位置
                String tracingArea = agriCitySampleTestDetail.getTracingArea();
                int startIndex = tracingArea.indexOf(samplingTracingCounty);
                if (startIndex != -1) {
                    // 计算起始位置的结束索引
                    int endIndex = startIndex + samplingTracingCounty.length();
                    // 提取从endIndex开始到字符串末尾的子字符串
                    String result = tracingArea.substring(endIndex);
                    agriCitySampleTestDetail.setTracingArea(result);
                } else {
                    agriCitySampleTestDetail.setTracingArea(tracingArea);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return agriCitySampleTestDetails1;
    }








    /**
     * 新增各市样品检测结果详细
     *
     * @param agriCitySampleTestDetails 各市样品检测结果详细
     * @return 结果
     */
    @Transactional
    @Override
    public int insertagriCitySampleTestDetails(agriCitySampleTestDetails agriCitySampleTestDetails) {
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
    public int updateagriCitySampleTestDetails(agriCitySampleTestDetails agriCitySampleTestDetails) {
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
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(Long[] citySampleTestDetailsIds) {
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
    public int deleteagriCitySampleTestDetailsByCitySampleTestDetailsId(Long citySampleTestDetailsId) {
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
                for (agriPesticideDetResult agriPesticideDetResults : agriPesticideDetResultList) {
                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResults);
                    System.out.println("---------------------------------------------------------");

                }

                successNum++;
                successMsg.append("<br/>" + "第" + successNum + "条" + "数据导入成功");

            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + "第" + failureNum + "条" + "数据导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0) {
            failureMsg.insert(0, "抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜，数据已全部导入成功！共 " + successNum + " 条，信息如下：");
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
        long mainTableId = 0;
        //要丢弃的主表主键值
        long noUseTableId = 0;
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
                if (samplingQuantity != null && samplingBase != null && samplingDate != null) {
                    //查询插入主表部分的ID  ID值更新到全集变量
                    mainTableId = agriCitySampleTestDetails.getCitySampleTestDetailsId();
//                    System.out.println(mainTableId+"----------------------------------");
                }
                agriPesticideDetResult agriPesticideDetResult = new agriPesticideDetResult();                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值
                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值,以及主表中的ID，以对应上
                agriPesticideDetResult.setCitySampleTestDetailsId(mainTableId);
                agriPesticideDetResult.setPesticideName(agriOutCitySampleTestDetails.getPesticideName());
                agriPesticideDetResult.setPesticideDetValue(String.valueOf(agriOutCitySampleTestDetails.getPesticideDetValue()));
                agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);

                //删除主表中没有信息数据的表
                if (samplingQuantity == null && samplingBase == null && samplingDate == null) {
                    noUseTableId = agriCitySampleTestDetails.getCitySampleTestDetailsId();
                    agriCitySampleTestDetailsMapper.deleteagriCitySampleTestDetailsByCitySampleTestDetailsId(noUseTableId);
                }
                successNum++;
                successMsg.append("<br/>" + "第" + successNum + "条" + "数据导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + "第" + failureNum + "条" + "数据导入失败：";
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

    //需求修改前的接口方法的实现，这边先保留着，但是先不用了
    @Override
    public String importAgriOut2CitySampleTestDetailsList_pre(List<agriOut2CitySampleTestDetails> agriOut2CitySampleTestDetailsList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(agriOut2CitySampleTestDetailsList) || agriOut2CitySampleTestDetailsList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy.M.d");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        int successNum = 0;
        int failureNum = 0;
        //要用到的主表主键值
        long mainTableId = 0;
        //要丢弃的主表主键值
        long noUseTableId = 0;
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
                mainTableId = agriCitySampleTestDetails.getCitySampleTestDetailsId();
//                    System.out.println(mainTableId+"----------------------------------");
//                }
                agriPesticideDetResult agriPesticideDetResult = new agriPesticideDetResult();                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值
                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值,以及主表中的ID，以对应上
                agriPesticideDetResult.setCitySampleTestDetailsId(mainTableId);
//                if(agriOut2CitySampleTestDetails.getNy1()!=null){
//                    agriPesticideDetResult.setPesticideName("甲胺磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy1());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy2()!=null){
//                    agriPesticideDetResult.setPesticideName("乙酰甲胺磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy2());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy3()!=null){
//                    agriPesticideDetResult.setPesticideName("甲拌磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy3());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy4()!=null){
//                    agriPesticideDetResult.setPesticideName("氧乐果");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy4());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy5()!=null){
//                    agriPesticideDetResult.setPesticideName("毒死蜱");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy5());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy6()!=null){
//                    agriPesticideDetResult.setPesticideName("特丁硫磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy6());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy7()!=null){
//                    agriPesticideDetResult.setPesticideName("三唑磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy7());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy8()!=null){
//                    agriPesticideDetResult.setPesticideName("水胺硫磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy8());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy9()!=null){
//                    agriPesticideDetResult.setPesticideName("治螟磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy9());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy10()!=null){
//                    agriPesticideDetResult.setPesticideName("乐果");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy10());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy11()!=null){
//                    agriPesticideDetResult.setPesticideName("甲基异柳磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy11());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy12()!=null){
//                    agriPesticideDetResult.setPesticideName("氟虫腈");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy12());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy13()!=null){
//                    agriPesticideDetResult.setPesticideName("克百威");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy13());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy14()!=null){
//                    agriPesticideDetResult.setPesticideName("联苯菊酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy14());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy15()!=null){
//                    agriPesticideDetResult.setPesticideName("氯氟氰菊酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy15());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy16()!=null){
//                    agriPesticideDetResult.setPesticideName("氟氯氰菊酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy16());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy17()!=null){
//                    agriPesticideDetResult.setPesticideName("氯氰菊酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy17());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy18()!=null){
//                    agriPesticideDetResult.setPesticideName("甲氰菊酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy18());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy19()!=null){
//                    agriPesticideDetResult.setPesticideName("溴氰菊酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy19());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy20()!=null){
//                    agriPesticideDetResult.setPesticideName("三唑酮");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy20());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy21()!=null){
//                    agriPesticideDetResult.setPesticideName("百菌清");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy21());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy22()!=null){
//                    agriPesticideDetResult.setPesticideName("腐霉利");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy22());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy23()!=null){
//                    agriPesticideDetResult.setPesticideName("丙溴磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy23());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy24()!=null){
//                    agriPesticideDetResult.setPesticideName("多菌灵");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy24());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy25()!=null){
//                    agriPesticideDetResult.setPesticideName("啶虫脒");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy25());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy26()!=null){
//                    agriPesticideDetResult.setPesticideName("苯醚甲环唑");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy26());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy27()!=null){
//                    agriPesticideDetResult.setPesticideName("阿维菌素");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy27());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy28()!=null){
//                    agriPesticideDetResult.setPesticideName("甲氨基阿维菌素苯甲酸盐");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy28());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy29()!=null){
//                    agriPesticideDetResult.setPesticideName("氟啶脲");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy29());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy30()!=null){
//                    agriPesticideDetResult.setPesticideName("灭幼脲");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy30());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy31()!=null){
//                    agriPesticideDetResult.setPesticideName("灭蝇胺");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy31());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy32()!=null){
//                    agriPesticideDetResult.setPesticideName("噻虫嗪");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy32());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy33()!=null){
//                    agriPesticideDetResult.setPesticideName("甲霜灵");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy33());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy34()!=null){
//                    agriPesticideDetResult.setPesticideName("霜霉威");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy34());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy35()!=null){
//                    agriPesticideDetResult.setPesticideName("吡唑醚菌酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy35());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy36()!=null){
//                    agriPesticideDetResult.setPesticideName("氯吡脲");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy36());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy37()!=null){
//                    agriPesticideDetResult.setPesticideName("嘧霉胺");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy37());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy38()!=null){
//                    agriPesticideDetResult.setPesticideName("吡虫啉");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy38());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy39()!=null){
//                    agriPesticideDetResult.setPesticideName("呋虫胺");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy39());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy40()!=null){
//                    agriPesticideDetResult.setPesticideName("虱螨脲");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy40());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy41()!=null){
//                    agriPesticideDetResult.setPesticideName("倍硫磷");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy41());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy42()!=null){
//                    agriPesticideDetResult.setPesticideName("抑霉唑");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy42());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy43()!=null){
//                    agriPesticideDetResult.setPesticideName("敌敌畏");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy43());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy44()!=null){
//                    agriPesticideDetResult.setPesticideName("甲基毒死蜱");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy44());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy45()!=null){
//                    agriPesticideDetResult.setPesticideName("氰戊菊酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy45());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy46()!=null){
//                    agriPesticideDetResult.setPesticideName("异菌脲");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy46());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy47()!=null){
//                    agriPesticideDetResult.setPesticideName("涕灭威");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy47());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy48()!=null){
//                    agriPesticideDetResult.setPesticideName("烯酰吗啉");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy48());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy49()!=null){
//                    agriPesticideDetResult.setPesticideName("炔螨特");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy49());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy50()!=null){
//                    agriPesticideDetResult.setPesticideName("乙螨唑");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy50());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy51()!=null){
//                    agriPesticideDetResult.setPesticideName("嘧菌酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy51());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy52()!=null){
//                    agriPesticideDetResult.setPesticideName("噻虫胺");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy52());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy53()!=null){
//                    agriPesticideDetResult.setPesticideName("丙环唑");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy53());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy54()!=null){
//                    agriPesticideDetResult.setPesticideName("螺虫乙酯");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy54());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy55()!=null){
//                    agriPesticideDetResult.setPesticideName("哒螨灵");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy55());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy56()!=null){
//                    agriPesticideDetResult.setPesticideName("四螨嗪");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy56());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy57()!=null){
//                    agriPesticideDetResult.setPesticideName("虫螨腈");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy57());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }
//                if(agriOut2CitySampleTestDetails.getNy58()!=null){
//                    agriPesticideDetResult.setPesticideName("咪鲜胺");
//                    agriPesticideDetResult.setPesticideDetValue(agriOut2CitySampleTestDetails.getNy58());
//                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
//                }

                successNum++;
                successMsg.append("<br/>" + "第" + successNum + "条" + "数据导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + "第" + failureNum + "条" + "数据导入失败：";
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

    //导入数据逻辑
    @Override
    public String importAgriOut2CitySampleTestDetailsList(List<Map<String, Object>> agriOut2CitySampleTestDetailsList, boolean updateSupport, String operName,String samplingType) {
        if (StringUtils.isNull(agriOut2CitySampleTestDetailsList) || agriOut2CitySampleTestDetailsList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        // 生成批次编号，可以使用UUID或当前时间戳
        String batchId = UUID.randomUUID().toString();
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy.M.d");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        int successNum = 0;
        int failureNum = 0;
        //要用到的主表主键值
        long mainTableId = 0;
        //要丢弃的主表主键值
        long noUseTableId = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (Map<String, Object> agriOut2CitySampleTestDetails : agriOut2CitySampleTestDetailsList) {
            try {
                // Long citySampleTestDetailsId =agriOut2CitySampleTestDetails.get("序号").toString();//?
//                String sampleCode = agriOut2CitySampleTestDetails.get("样品编号").toString();
//                String vegFruName = agriOut2CitySampleTestDetails.get("样品名称").toString();
//                String samplingStageType = agriOut2CitySampleTestDetails.get("抽样环节").toString();
//                String samplingLocationProvince  = agriOut2CitySampleTestDetails.get("抽样省").toString();
//                String samplingLocationCity  = agriOut2CitySampleTestDetails.get("抽样市").toString();
//                String samplingLocationCounty  = agriOut2CitySampleTestDetails.get("抽样县").toString();
//                String samplingLocation = agriOut2CitySampleTestDetails.get("抽样地址").toString();
                String sampleCode = null;
                try {
                    sampleCode = agriOut2CitySampleTestDetails.get("样品编号").toString();
                } catch (Exception e) {
                    try{
                        sampleCode = agriOut2CitySampleTestDetails.get("样品     编号").toString();
                    }catch (Exception e2){
                        sampleCode = null;
                    }
                }
                if (sampleCode!=null){
                    sampleCode=sampleCode.replace("-", "");
                }

                String vegFruName = null;
                try {
                    vegFruName = agriOut2CitySampleTestDetails.get("样品名称").toString();
                    if (vegFruName != null) {
                        vegFruName = vegFruName.replace(" ", "");  // 去除空格
                        vegFruName = vegFruName.replace("\n", "");  // 去除回车

                    }
                } catch (Exception e) {
                    vegFruName = null;
                }

                String allDistrictCode;
                try {
                    allDistrictCode = agriOut2CitySampleTestDetails.get("全区编号").toString();
                    if (vegFruName != null) {
                        allDistrictCode = allDistrictCode.replace(" ", "");  // 去除空格
                    }
                } catch (Exception e) {
                    allDistrictCode = null;
                }


                String samplingStageType = null;
                try {
                    samplingStageType = agriOut2CitySampleTestDetails.get("抽样环节").toString();
                } catch (Exception e) {
                    samplingStageType = null;
                }

                String samplingLocationProvince = null;
                try {
                    samplingLocationProvince = agriOut2CitySampleTestDetails.get("抽样省").toString();
                    if(samplingLocationProvince.equals("广西")){
                        samplingLocationProvince="广西壮族自治区";
                    }
                } catch (Exception e) {
                    samplingLocationProvince = null;
                }

                String samplingLocationCity = null;
                try {
                    samplingLocationCity = agriOut2CitySampleTestDetails.get("抽样市").toString();
                } catch (Exception e) {
                    samplingLocationCity = null;
                }

                String samplingLocationCounty = null;
                try {
                    samplingLocationCounty = agriOut2CitySampleTestDetails.get("抽样县").toString();
                } catch (Exception e) {
                    samplingLocationCounty = null;
                }

                String samplingLocation = null;
                try {
                    samplingLocation = agriOut2CitySampleTestDetails.get("抽样地址").toString();
                } catch (Exception e) {
                    samplingLocation = null;
                }


                String samplingDate = null;
                try {
                    samplingDate = agriOut2CitySampleTestDetails.get("抽样日期").toString();
                } catch (Exception e) {
                    samplingDate = "2024.5.1";
                }

                String enterpriseName = null;
                try {
                    enterpriseName = agriOut2CitySampleTestDetails.get("企业名称/散户").toString();
                } catch (Exception e) {
                    try {
                        enterpriseName = agriOut2CitySampleTestDetails.get("企业名称/农户").toString();
                    } catch (Exception e1) {
                        try {
                            enterpriseName = agriOut2CitySampleTestDetails.get("企业名称").toString();
                        } catch (Exception e2) {
                            enterpriseName = null;
                        }
                    }
                }

                String enterpriseAttribute = null;
                try {
                    enterpriseAttribute = agriOut2CitySampleTestDetails.get("企业属性（绿色/有机/地理标志/GAP)").toString();
                } catch (Exception e) {
                    enterpriseAttribute = null;
                }

                String enterpriseCreditIdCode = null;
                try {
                    enterpriseCreditIdCode = agriOut2CitySampleTestDetails.get("企业信用代码/身份证号").toString();
                } catch (Exception e) {
                    enterpriseCreditIdCode = null;
                }

                String tracingProvince = null;
                try {
                    tracingProvince = agriOut2CitySampleTestDetails.get("溯源省").toString();
                    if(tracingProvince.equals("广西")){
                        tracingProvince="广西壮族自治区";
                    }
                } catch (Exception e) {
                    tracingProvince = null;
                }

                String tracingCity = null;
                try {
                    tracingCity = agriOut2CitySampleTestDetails.get("溯源市").toString();
                } catch (Exception e) {
                    tracingCity = null;
                }

                String tracingCounty = null;
                try {
                    tracingCounty = agriOut2CitySampleTestDetails.get("溯源县").toString();
                } catch (Exception e) {
                    tracingCounty = null;
                }

                String tracingArea = null;
                try {
                    tracingArea = agriOut2CitySampleTestDetails.get("溯源产地").toString();
                } catch (Exception e) {
                    tracingArea = null;
                }

                String chinaStandard = null;
                try {
                    chinaStandard = agriOut2CitySampleTestDetails.get("判定结果").toString();
                } catch (Exception e) {
                    chinaStandard = null;
                }


//                String tracingCity  = agriOut2CitySampleTestDetails.get("溯源市").toString();
//                String tracingCounty  = agriOut2CitySampleTestDetails.get("溯源县").toString();
//                String tracingArea  = agriOut2CitySampleTestDetails.get("溯源产地").toString();
//                String chinaStandard = agriOut2CitySampleTestDetails.get("判定结果").toString();


                Date samplingDateUse = null;
                if (samplingDate != null) {
                    samplingDateUse = originalFormat.parse(samplingDate);//抽样日期
                }

//                Double samplingQuantity = agriOut2CitySampleTestDetails.getSamplingQuantity();
//                String samplingBase = agriOut2CitySampleTestDetails.getSamplingBase();

                agriCitySampleTestDetails agriCitySampleTestDetails = new agriCitySampleTestDetails();
//                agriCitySampleTestDetails.setCitySampleTestDetailsId(citySampleTestDetailsId);
                agriCitySampleTestDetails.setSamplingLocationProvince(samplingLocationProvince);
                agriCitySampleTestDetails.setSamplingLocationCity(samplingLocationCity);
                agriCitySampleTestDetails.setSamplingLocationCounty(samplingLocationCounty);
                agriCitySampleTestDetails.setEnterpriseName(enterpriseName);
                agriCitySampleTestDetails.setEnterpriseAttribute(enterpriseAttribute);
                agriCitySampleTestDetails.setEnterpriseCreditIdCode(enterpriseCreditIdCode);
                agriCitySampleTestDetails.setTracingProvince(tracingProvince);
                agriCitySampleTestDetails.setTracingCity(tracingCity);
                agriCitySampleTestDetails.setTracingCounty(tracingCounty);
                agriCitySampleTestDetails.setTracingArea(tracingArea);
                agriCitySampleTestDetails.setAllDistrictCode(allDistrictCode);
                agriCitySampleTestDetails.setSamplingType(samplingType);


                //如果sampleCode vegFruName  samplingLocation都是null的话，说明他们数据不规范，第二行开始才有数据，
                // 这边做一些健壮性处理，如果第一行数据是空的，直接跳过本次循环，进入到下一行数据
                assert sampleCode != null;
                if(sampleCode.isEmpty()) {
                    assert vegFruName != null;
                    if (vegFruName.isEmpty()) {
                        assert samplingLocation != null;
                        if (samplingLocation.isEmpty()) {
                            break;
                        }
                    }
                }

                agriCitySampleTestDetails.setSampleCode(sampleCode);
                agriCitySampleTestDetails.setVegFruName(vegFruName);
                agriCitySampleTestDetails.setSamplingLocation(samplingLocation);

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                //获取当前登录的用户名
                String username = authentication.getName();
                agriCitySampleTestDetails.setUserName(username);


                //通过当前登录的用户名查询到当前登录的用户所存在的部门
                String deptName = agriCitySampleTestDetailsMapper.selectUserDepByUserName(username);
                if (agriCitySampleTestDetails.getDetectLocation() == null) {
                    agriCitySampleTestDetails.setDetectLocation(deptName);
                }

//                agriCitySampleTestDetails.setSamplingQuantity(samplingQuantity);
//                agriCitySampleTestDetails.setSamplingBase(samplingBase);

                agriCitySampleTestDetails.setSamplingStageType(samplingStageType);
                agriCitySampleTestDetails.setSamplingDate(samplingDateUse);
                agriCitySampleTestDetails.setChinaStandard(chinaStandard);
                agriCitySampleTestDetails.setBatchId(batchId);

                //插入主表部分
                System.out.println("看看"+agriCitySampleTestDetails);
                agriCitySampleTestDetailsMapper.insertagriCitySampleTestDetails(agriCitySampleTestDetails);
//                System.out.println("-----------------------------"+agriCitySampleTestDetails.getCitySampleTestDetailsId()+"---------------------------------------------------");
                //这个判断固定真正有用的主键值，用来传给从表也就是检测表，（随便取了3个为null值的属性判断）,如果判断的字段是空的，说明这张表是为了添加从表字段时候多余添加进来的
//                if(samplingQuantity!=null&&samplingBase!=null&&samplingDate!=null){
                //查询插入主表部分的ID  ID值更新到全集变量
                mainTableId = agriCitySampleTestDetails.getCitySampleTestDetailsId();
//                    System.out.println(mainTableId+"----------------------------------");
//                }
                agriPesticideDetResult agriPesticideDetResult = new agriPesticideDetResult();                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值
                //有了插入主表部分的ID之后插入从表部分的检测农药和检测值,以及主表中的ID，以对应上
                agriPesticideDetResult.setCitySampleTestDetailsId(mainTableId);


                //插入从表，除了下面的other里面的属性之外的东西，就会被识别为是农药
                List<String> other = Arrays.asList(
                        "序号", "样品编号","样品 编号","样品     编号", "样品名称", "抽样环节",
                        "抽样省", "抽样市", "抽样县", "抽样地址",
                        "企业名称/农户","企业名称/散户", "企业属性（绿色/有机/地理标志/GAP)", "企业信用代码/身份证号",
                        "溯源省", "溯源市", "溯源县", "溯源产地", "判定结果", "抽样日期", "企业名称", "excelRowNum","依据国家标准");//合计在最后加入
                //遍历每一个map
                Set<Map.Entry<String, Object>> entrySet = agriOut2CitySampleTestDetails.entrySet();
                for (Map.Entry<String, Object> entryItem : entrySet) {//对于map里每一个内容
                    if (other.contains(entryItem.getKey())) {
                        continue;
                    }//不是农药跳过
                    agriPesticideDetResult.setPesticideName(entryItem.getKey());

                    Object value = entryItem.getValue();
                    if (value == null) {
                        continue;
                    }
                    if (value.equals("/") || value.equals("未检出")) {
                        continue;
                    }
                    if (value instanceof String) {
                        String strValue = (String) value;

                        // 正则表达式匹配中文字符的范围
                        String chineseRegex = "[\\u4e00-\\u9fa5]";
                        Pattern chinesePattern = Pattern.compile(chineseRegex);
                        Matcher chineseMatcher = chinesePattern.matcher(strValue);
                        // 将中文字符替换为空字符串
                        String result1 = chineseMatcher.replaceAll("");

                        // 替换全角下划线字符、半角下划线字符、半角减号和全角减号为空字符串
                        String specialCharsRegex = "[＿_-－]";
                        Pattern specialCharsPattern = Pattern.compile(specialCharsRegex);
                        Matcher specialCharsMatcher = specialCharsPattern.matcher(result1);
                        String result2 = specialCharsMatcher.replaceAll("");

                        // 只保留数字和小数点
                        String numbersAndDotsRegex = "[^0-9.]";
                        Pattern numbersAndDotsPattern = Pattern.compile(numbersAndDotsRegex);
                        Matcher numbersAndDotsMatcher = numbersAndDotsPattern.matcher(result2);
                        String result3 = numbersAndDotsMatcher.replaceAll("");

                        value = result3;
                    } else if (value instanceof Double) {
                        value = value.toString();
                    } else {
                        throw new IllegalArgumentException("Value must be either String or Double");
                    }

                    agriPesticideDetResult.setPesticideDetValue((String) value);

                    agriPesticideDetResultMapper.insertagriPesticideDetResult(agriPesticideDetResult);
                }

                successNum++;
                successMsg.append("<br/>" + "第" + successNum + "条" + "数据导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + "第" + failureNum + "条" + "数据导入失败---------：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0) {
//            failureMsg.insert(0, "抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            failureMsg.insert(0, "导入成功，有 " + failureNum + " 条数据因格式不正确导入失败：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜，数据已全部导入成功！本次数据的导入批次编号为： "+batchId+"    <br/>   共 " + successNum + " 条，情况如下：");
        }
        return successMsg.toString();

    }


    /**
     * 新增农药检测结果信息
     *
     * @param agriCitySampleTestDetails 各市样品检测结果详细对象
     */
    public void insertagriPesticideDetResult(agriCitySampleTestDetails agriCitySampleTestDetails) {
        List<agriPesticideDetResult> agriPesticideDetResultList = agriCitySampleTestDetails.getAgriPesticideDetResultList();
        Long citySampleTestDetailsId = agriCitySampleTestDetails.getCitySampleTestDetailsId();
        if (StringUtils.isNotNull(agriPesticideDetResultList)) {
            List<agriPesticideDetResult> list = new ArrayList<agriPesticideDetResult>();
            for (agriPesticideDetResult agriPesticideDetResult : agriPesticideDetResultList) {
                agriPesticideDetResult.setCitySampleTestDetailsId(citySampleTestDetailsId);
                list.add(agriPesticideDetResult);
            }
            if (list.size() > 0) {
                agriCitySampleTestDetailsMapper.batchagriPesticideDetResult(list);
            }
        }
    }
}
