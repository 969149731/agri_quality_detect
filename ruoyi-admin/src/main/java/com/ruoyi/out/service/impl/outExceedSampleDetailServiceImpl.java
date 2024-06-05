package com.ruoyi.out.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.domain.agriPesticideDetResultForOutExceedSampleDetail;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;
import com.ruoyi.out.domain.out2ExceedSampleDetail;
import com.ruoyi.out.mapper.outDlDetectRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outExceedSampleDetailMapper;
import com.ruoyi.out.domain.outExceedSampleDetail;
import com.ruoyi.out.service.IoutExceedSampleDetailService;

/**
 * 超标蔬菜水果样品明细Service业务层处理
 *
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outExceedSampleDetailServiceImpl implements IoutExceedSampleDetailService {
    @Autowired
    private outExceedSampleDetailMapper outExceedSampleDetailMapper;
    @Autowired
    private com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;
    @Autowired
    private com.ruoyi.out.mapper.outDlDetectRecordsMapper outDlDetectRecordsMapper;
    @Autowired
    private com.ruoyi.detection.mapper.agriPesticideDetResultMapper agriPesticideDetResultMapper;

    /**
     * 查询超标蔬菜水果样品明细
     *
     * @param exceedSampleDetailId 超标蔬菜水果样品明细主键
     * @return 超标蔬菜水果样品明细
     */
    @Override
    public outExceedSampleDetail selectoutExceedSampleDetailByExceedSampleDetailId(Long exceedSampleDetailId) {
        return outExceedSampleDetailMapper.selectoutExceedSampleDetailByExceedSampleDetailId(exceedSampleDetailId);
    }

    /**
     * 查询超标蔬菜水果样品明细列表
     *
     * @param outExceedSampleDetail 超标蔬菜水果样品明细
     * @return 超标蔬菜水果样品明细
     */
    @Override
    public List<outExceedSampleDetail> selectoutExceedSampleDetailList(outExceedSampleDetail outExceedSampleDetail) {
        return outExceedSampleDetailMapper.selectoutExceedSampleDetailList(outExceedSampleDetail);
    }

    /**
     * 新增超标蔬菜水果样品明细
     *
     * @param outExceedSampleDetail 超标蔬菜水果样品明细
     * @return 结果
     */
    @Override
    public int insertoutExceedSampleDetail(outExceedSampleDetail outExceedSampleDetail) {
        return outExceedSampleDetailMapper.insertoutExceedSampleDetail(outExceedSampleDetail);
    }

    /**
     * 修改超标蔬菜水果样品明细
     *
     * @param outExceedSampleDetail 超标蔬菜水果样品明细
     * @return 结果
     */
    @Override
    public int updateoutExceedSampleDetail(outExceedSampleDetail outExceedSampleDetail) {
        return outExceedSampleDetailMapper.updateoutExceedSampleDetail(outExceedSampleDetail);
    }

    /**
     * 批量删除超标蔬菜水果样品明细
     *
     * @param exceedSampleDetailIds 需要删除的超标蔬菜水果样品明细主键
     * @return 结果
     */
    @Override
    public int deleteoutExceedSampleDetailByExceedSampleDetailIds(Long[] exceedSampleDetailIds) {
        return outExceedSampleDetailMapper.deleteoutExceedSampleDetailByExceedSampleDetailIds(exceedSampleDetailIds);
    }

    /**
     * 删除超标蔬菜水果样品明细信息
     *
     * @param exceedSampleDetailId 超标蔬菜水果样品明细主键
     * @return 结果
     */
    @Override
    public int deleteoutExceedSampleDetailByExceedSampleDetailId(Long exceedSampleDetailId) {
        return outExceedSampleDetailMapper.deleteoutExceedSampleDetailByExceedSampleDetailId(exceedSampleDetailId);
    }

    //判断某样品对应某标准的某农药是否超标函数
    public boolean checkPesticideIsPass(agriCitySampleTestDetails sample) {
        // 超标和不超标的标识，刚刚开始超标和不超标都是为0
        int flagPass = 0;
        int flagNoPass = 0;
        //根据主表中的样品ID查询出其所对应检测到的农药名和农药值信息。
        List<agriPesticideDetResult> agriPesticideDetResults = agriPesticideDetResultMapper.selectagriPesticideDetResultByCitySampleTestDetailsId(sample.getCitySampleTestDetailsId());
        for (agriPesticideDetResult agriPesticideDetResult : agriPesticideDetResults) {
            //获取上传的数据中有检测出有值的农药名
            String pesticideName = agriPesticideDetResult.getPesticideName();
            //获取上传的数据中有检测出有值的农药值
            Double pesticideDetValue = Double.valueOf(agriPesticideDetResult.getPesticideDetValue());
            //获取上传的数据中具体的样品名【蔬菜水果名】，用查询该样品农药限量值
            String vegFruName = sample.getVegFruName();
            //标准，默认依据国家标准
            String standardCategory = "国家标准";
            //根据上传的数据中有检测出有值的【农药名】和具体的【蔬菜水果名】获取农药标准表中的中国标准的该农药限量值
            Double limitValue = outDlDetectRecordsMapper.selectLimitValueByPesticideName(pesticideName, vegFruName, standardCategory);
            if (limitValue == null) {
                // 如果limitValue结果是null，就将其设置为0，并提醒用户添加农药限量值表中的数据
                // 这边设为999是因为不超标的比较多，在用户没有更新字典时候就默认农药不超标先运行程序
                limitValue = 999.0;
                System.out.println("注意！请在农药残留字典表中添加如下字典。【农药名字：["
                        + pesticideName + "],蔬菜名字：[" + vegFruName + "],标准：[" + standardCategory + "]】");
                //因为limitValue是==null的，说明没有该字典，先尝试用 用户上传的中国标准的数据进行农药是否超标的判断
                String chinaStandard = sample.getChinaStandard();
                if (chinaStandard != null) {
                    if (chinaStandard.equals("合格")) {
                        return true;
                    }
                    if (chinaStandard.equals("不合格")) {
                        return false;
                    }
                }
            }
            if (pesticideDetValue == null) {
                pesticideDetValue=0.0;//设为不超标
                System.out.println("注意！该样本的该农药没有对应检测值，请确认为0或者其他。【农药名字：["
                        + pesticideName + "],蔬菜名字：[" + vegFruName + "],样本ID：[" + agriPesticideDetResult.getPesticideDetResultId() + "]】");
            }
            //如果传的数据中有检测出有值的农药值 大于 该农药限量值 ,说明超标了
            if (pesticideDetValue > limitValue) {
                flagNoPass = flagNoPass + 1;
            } else {
                flagPass = flagPass + 1;
            }
            if (flagNoPass > 0) {
                return false;
            }
//            System.out.println("样本名字："+sample.getVegFruName()+"检测出的农药："+pesticideName+"检测出的值："+pesticideDetValue);
        }
        return true;
    }


    //超标蔬菜水果样品明细表
    @Override
    public List<out2ExceedSampleDetail> selectOutExceedSampleDetailList(agriCitySampleTestDetails agriCitySampleTestDetails) {
        List<out2ExceedSampleDetail> outExceedSampleDetailList = new ArrayList<>();
        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
        for (agriCitySampleTestDetails CitySampleTestDetail : agriCitySampleTestDetailsList) {
            //如果不合格
            if (!checkPesticideIsPass(CitySampleTestDetail)) {
                out2ExceedSampleDetail outExceedSampleDetail = new out2ExceedSampleDetail();
                outExceedSampleDetail.setDetectUnit(CitySampleTestDetail.getDetectLocation());
                outExceedSampleDetail.setSampleCode(CitySampleTestDetail.getSampleCode());
                outExceedSampleDetail.setVegFruName(CitySampleTestDetail.getVegFruName());
                outExceedSampleDetail.setSamplingLocation(CitySampleTestDetail.getSamplingLocation());
                //根据主表中的样品ID查询出其所对应检测到的农药名和农药值信息。
                List<agriPesticideDetResult> agriPesticideDetResults = agriPesticideDetResultMapper.selectagriPesticideDetResultByCitySampleTestDetailsId(CitySampleTestDetail.getCitySampleTestDetailsId());
                List<agriPesticideDetResultForOutExceedSampleDetail> agriPesticideDetResultList = new ArrayList<>();
                for (agriPesticideDetResult agriPesticideDetResult : agriPesticideDetResults) {
                    agriPesticideDetResultForOutExceedSampleDetail agriPesticideDetResultForOutExceedSampleDetail = new agriPesticideDetResultForOutExceedSampleDetail();
                    //获取上传的数据中有检测出有值的农药名
                    String pesticideName = agriPesticideDetResult.getPesticideName();
                    //获取上传的数据中有检测出有值的农药值
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
                    agriPesticideDetResultForOutExceedSampleDetail.setPesticideName(pesticideName);
                    agriPesticideDetResultForOutExceedSampleDetail.setPesticideDetValue(pesticideDetValue);
                    if(limitValue==null){
                        //这边在后台给出提示，后期添加对应的标准
                        System.out.println("！！！需要添加检测限量值信息，如下："+"农药："+pesticideName+"。 样品："+vegFruName+"。 标准："+standardCategory);
                        limitValue=999.0;
                    }
                    //说明超标
                    if(pesticideDetValue>limitValue){
                        agriPesticideDetResultForOutExceedSampleDetail.setLimitValue(limitValue);
                        agriPesticideDetResultList.add(agriPesticideDetResultForOutExceedSampleDetail);
                        outExceedSampleDetail.setExceedPesticideNameAndPesticideValueAndlimitValue(agriPesticideDetResultList);
                        outExceedSampleDetailList.add(outExceedSampleDetail);

                    }

                }
//                outExceedSampleDetailList.add(outExceedSampleDetail);

            }
        }
        return outExceedSampleDetailList;
    }
}
