package com.ruoyi.out.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.domain.agriPesticideDetResultForUndeterminedStandDet;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;
import com.ruoyi.myUtils.AgriUtils;
import com.ruoyi.out.domain.out2BanPesticideDetection;
import com.ruoyi.out.domain.out2UndeterminedStandDet;
import com.ruoyi.out.mapper.outDlDetectRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outUndeterminedStandDetMapper;
import com.ruoyi.out.domain.outUndeterminedStandDet;
import com.ruoyi.out.service.IoutUndeterminedStandDetService;

import static com.ruoyi.common.utils.PageUtils.myStartPage;
import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 无判定标准检出值详细Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outUndeterminedStandDetServiceImpl implements IoutUndeterminedStandDetService 
{
    @Autowired
    private outUndeterminedStandDetMapper outUndeterminedStandDetMapper;
    @Autowired
    private agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;
    @Autowired
    private outDlDetectRecordsMapper outDlDetectRecordsMapper;
    @Autowired
    private agriPesticideDetResultMapper agriPesticideDetResultMapper;

    /**
     * 查询无判定标准检出值详细
     * 
     * @param undeterminedStandardDetectionId 无判定标准检出值详细主键
     * @return 无判定标准检出值详细
     */
    @Override
    public outUndeterminedStandDet selectoutUndeterminedStandDetByUndeterminedStandardDetectionId(Long undeterminedStandardDetectionId)
    {
        return outUndeterminedStandDetMapper.selectoutUndeterminedStandDetByUndeterminedStandardDetectionId(undeterminedStandardDetectionId);
    }

    /**
     * 查询无判定标准检出值详细列表
     * 
     * @param outUndeterminedStandDet 无判定标准检出值详细
     * @return 无判定标准检出值详细
     */
    @Override
    public List<outUndeterminedStandDet> selectoutUndeterminedStandDetList(outUndeterminedStandDet outUndeterminedStandDet)
    {
        return outUndeterminedStandDetMapper.selectoutUndeterminedStandDetList(outUndeterminedStandDet);
    }

    /**
     * 新增无判定标准检出值详细
     * 
     * @param outUndeterminedStandDet 无判定标准检出值详细
     * @return 结果
     */
    @Override
    public int insertoutUndeterminedStandDet(outUndeterminedStandDet outUndeterminedStandDet)
    {
        return outUndeterminedStandDetMapper.insertoutUndeterminedStandDet(outUndeterminedStandDet);
    }

    /**
     * 修改无判定标准检出值详细
     * 
     * @param outUndeterminedStandDet 无判定标准检出值详细
     * @return 结果
     */
    @Override
    public int updateoutUndeterminedStandDet(outUndeterminedStandDet outUndeterminedStandDet)
    {
        return outUndeterminedStandDetMapper.updateoutUndeterminedStandDet(outUndeterminedStandDet);
    }

    /**
     * 批量删除无判定标准检出值详细
     * 
     * @param undeterminedStandardDetectionIds 需要删除的无判定标准检出值详细主键
     * @return 结果
     */
    @Override
    public int deleteoutUndeterminedStandDetByUndeterminedStandardDetectionIds(Long[] undeterminedStandardDetectionIds)
    {
        return outUndeterminedStandDetMapper.deleteoutUndeterminedStandDetByUndeterminedStandardDetectionIds(undeterminedStandardDetectionIds);
    }

    /**
     * 删除无判定标准检出值详细信息
     * 
     * @param undeterminedStandardDetectionId 无判定标准检出值详细主键
     * @return 结果
     */
    @Override
    public int deleteoutUndeterminedStandDetByUndeterminedStandardDetectionId(Long undeterminedStandardDetectionId)
    {
        return outUndeterminedStandDetMapper.deleteoutUndeterminedStandDetByUndeterminedStandardDetectionId(undeterminedStandardDetectionId);
    }

    //无判定标准检出值详列表
    @Override
    public List<out2UndeterminedStandDet> selectOutUndeterminedStandDetList(agriCitySampleTestDetails agriCitySampleTestDetails) {
        AgriUtils.permissionToDifferentiateData(agriCitySampleTestDetails);

        //最后要往out2BanPesticideDetectionList这里面add一个out2BanPesticideDetection对象
        List<out2UndeterminedStandDet> out2UndeterminedStandDetList = new ArrayList<>();
        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
        //遍历导入表中的每一条数据
        for (agriCitySampleTestDetails CitySampleTestDetail : agriCitySampleTestDetailsList) {
            //根据主表中的样品ID查询出其所对应检测到的农药名和农药值信息。
            List<agriPesticideDetResult> agriPesticideDetResults = agriPesticideDetResultMapper.selectagriPesticideDetResultByCitySampleTestDetailsId(CitySampleTestDetail.getCitySampleTestDetailsId());
            List<agriPesticideDetResultForUndeterminedStandDet> agriPesticideDetResultForUndeterminedStandDetList = new ArrayList<>();
            out2UndeterminedStandDet out2UndeterminedStandDet = new out2UndeterminedStandDet();
            for (agriPesticideDetResult agriPesticideDetResult : agriPesticideDetResults) {
                //获取上传的数据中有检测出有值的农药名
                String pesticideName = agriPesticideDetResult.getPesticideName();
                //获取上传的数据中有检测出有值的农药检测值
                double pesticideDetValue=0.0;
                if(!Objects.equals(agriPesticideDetResult.getPesticideDetValue(), "")){
                     pesticideDetValue = Double.parseDouble(agriPesticideDetResult.getPesticideDetValue());

                }

                //获取上传的数据中具体的样品名【蔬菜水果名】，用查询该样品农药限量值
                String vegFruName = CitySampleTestDetail.getVegFruName();
                //标准，默认依据国家标准
                String standardCategory = "国家标准";
                //根据上传的数据中有检测出有值的【农药名】和具体的【蔬菜水果名】获取农药标准表中的中国标准的该农药限量值
                Double limitValue = outDlDetectRecordsMapper.selectLimitValueByPesticideName(pesticideName, vegFruName, standardCategory);
                if (limitValue == null) {
                    out2UndeterminedStandDet.setDetectUnit(CitySampleTestDetail.getDetectLocation());
                    out2UndeterminedStandDet.setSampleCode(CitySampleTestDetail.getSampleCode());
                    out2UndeterminedStandDet.setVegFruName(CitySampleTestDetail.getVegFruName());
                    out2UndeterminedStandDet.setSamplingLocation(CitySampleTestDetail.getSamplingLocation());

                    agriPesticideDetResultForUndeterminedStandDet agriPesticideDetResultForUndeterminedStandDet = new agriPesticideDetResultForUndeterminedStandDet();
                    agriPesticideDetResultForUndeterminedStandDet.setPesticideName(pesticideName);
                    agriPesticideDetResultForUndeterminedStandDet.setPesticideDetValue(pesticideDetValue);
                    agriPesticideDetResultForUndeterminedStandDetList.add(agriPesticideDetResultForUndeterminedStandDet);

                    out2UndeterminedStandDet.setDetPesticideNameAndValue(agriPesticideDetResultForUndeterminedStandDetList);

                    //获取到的数据可能是重复的，这边进行判断，因为if语句里面是添加东西到list中，if又在检测结果的for循环里面，所以可能会有重复的结果数据，不重复的才添加到最后返回的list中
                    boolean contains = out2UndeterminedStandDetList.contains(out2UndeterminedStandDet);
                    //如果最终返回的list中没有包含马上要添加的数据，则进行添加
                    if(!contains){
                        out2UndeterminedStandDetList.add(out2UndeterminedStandDet);
                    }
                }
            }
        }
        return out2UndeterminedStandDetList;
    }
}
