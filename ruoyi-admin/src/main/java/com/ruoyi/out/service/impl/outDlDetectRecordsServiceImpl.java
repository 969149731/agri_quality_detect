package com.ruoyi.out.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;
import com.ruoyi.detection.service.impl.agriCitySampleTestDetailsServiceImpl;
import com.ruoyi.out.domain.dlDetRecordSampleRes;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outDlDetectRecordsMapper;
import com.ruoyi.out.domain.outDlDetectRecords;
import com.ruoyi.out.service.IoutDlDetectRecordsService;

/**
 * 定量监测结果汇总Service业务层处理
 *
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outDlDetectRecordsServiceImpl implements IoutDlDetectRecordsService {
    @Autowired
    private outDlDetectRecordsMapper outDlDetectRecordsMapper;
    @Autowired
    private agriCitySampleTestDetailsServiceImpl agriCitySampleTestDetailsServiceImpl;
    @Autowired
    private agriPesticideDetResultMapper agriPesticideDetResultMapper;
    @Autowired
    private agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;

    /**
     * 查询定量监测结果汇总
     *
     * @param recordDlId 定量监测结果汇总主键
     * @return 定量监测结果汇总
     */
    @Override
    public outDlDetectRecords selectoutDlDetectRecordsByRecordDlId(Long recordDlId) {
        return outDlDetectRecordsMapper.selectoutDlDetectRecordsByRecordDlId(recordDlId);
    }

    /**
     * 查询定量监测结果汇总列表
     *
     * @param outDlDetectRecords 定量监测结果汇总
     * @return 定量监测结果汇总
     */
    @Override
    public List<outDlDetectRecords> selectoutDlDetectRecordsList(outDlDetectRecords outDlDetectRecords) {
        return outDlDetectRecordsMapper.selectoutDlDetectRecordsList(outDlDetectRecords);
    }


    //判断样品是蔬菜还是水果函数
    public int check_type(String sampleName) {
        String type = outDlDetectRecordsMapper.checkSampleType(sampleName);
        if ("蔬菜".equals(type)) {
            return 0;
        } else if("水果".equals(type)) {
            return 1;
        }else{
            System.out.println("样品："+sampleName+"，未知是蔬菜还是水果，请在蔬果字典中添加该样品是属于蔬菜还是水果。");
            // 下面的代码是把不知道是水果还是蔬菜的样本输出到C盘中，方便查看添加。
            List<String> messages = new ArrayList<>();
            messages.add("样品（veg_fru_name）："+sampleName+"，种类（veg_fru_type）：未知，请分析出该样品是蔬菜还是水果");
            // 指定输出文件的路径
            String filePath = "C:\\check_fru_or_veg.txt";
            // 读取文件内容到集合中，以便后续检查
            Set<String> existingLines = new HashSet<>();
            try {
                existingLines.addAll(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                for (String message : messages) {
                    if (!existingLines.contains(message)) { // 如果文件中不存在该消息，则追加
                        writer.write(message);
                        writer.newLine();
                    }
                }
                System.out.println("文件写入成功，重复数据已去除！");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }
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
            Double pesticideDetValue = agriPesticideDetResult.getPesticideDetValue();
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
                if(chinaStandard!=null){
                    if (chinaStandard.equals("合格")){
                        return true;
                    }
                    if (chinaStandard.equals("不合格")){
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
            if (pesticideDetValue!=null && pesticideDetValue > limitValue) {
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

    //定量检测汇总表List
    @Override
    public Map<String, List<dlDetRecordSampleRes>> selectOutDlDetectRecordsList(agriCitySampleTestDetails agriCitySampleTestDetails) throws ParseException {
//        agriCitySampleTestDetails agriCitySampleTestDetail = new agriCitySampleTestDetails();
//        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
//        agriCitySampleTestDetail.setSamplingDate(targetFormat.parse("2022-06-02"));
        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);

        System.out.println("所有的检测详细列表!!!!:"+agriCitySampleTestDetailsList);
        Map<String, List<dlDetRecordSampleRes>> res = new HashMap<>();
        //开始遍历每一条导入表中的详细记录
        for (agriCitySampleTestDetails CitySampleTestDetail : agriCitySampleTestDetailsList) {
            List<dlDetRecordSampleRes> current_res = new ArrayList<>();
            //如果被检测单位是第一次出现
            if(!res.containsKey(CitySampleTestDetail.getSamplingLocation())){
                dlDetRecordSampleRes vegOrFruRes = new dlDetRecordSampleRes(0,0,0.0,0,0,0.0,0,0,0.0);
                //计算蔬菜结果
                //如果是蔬菜 按照样本名称 从类型表中判断该样本是蔬菜还是水果，调用check_type(样品名称) --> 0-蔬菜 1-水果
                if(0 == check_type(CitySampleTestDetail.getVegFruName())){
                    vegOrFruRes.setVegSamplingCount(1);
                    vegOrFruRes.setAllSamplingCount(vegOrFruRes.getAllSamplingCount()+1);
                    //检测是否超标（ture不超标，false超标）
                    //如果不超标，也就是ture，进入if语句，被检测单位是第一次出现，合格数就是1
                    if(checkPesticideIsPass(CitySampleTestDetail)){
                        vegOrFruRes.setVegPassCount(1);
                    }
                    //设置蔬菜的合格率
                    vegOrFruRes.setVegPassRate((double) (vegOrFruRes.getVegPassCount()/vegOrFruRes.getVegSamplingCount()));
                }
                //如果是水果 按照样本名称 从类型表中判断该样本是蔬菜还是水果，调用check_type(样品名称) --> 0-蔬菜 1-水果
                else if(1 == check_type(CitySampleTestDetail.getVegFruName())){
                    vegOrFruRes.setFruSamplingCount(1);
                    vegOrFruRes.setAllSamplingCount(vegOrFruRes.getAllSamplingCount()+1);
                    if(checkPesticideIsPass(CitySampleTestDetail)){
                        vegOrFruRes.setFruPassCount(1);
                    }
                    vegOrFruRes.setFruPassRate((double) (vegOrFruRes.getFruPassCount()/vegOrFruRes.getFruSamplingCount()));
                }else{
                    System.out.println("到这里的话说明获取到样本的具体名字没有找到其是蔬菜还是水果的类型，提醒用户加入对应字典");
                }
                vegOrFruRes.setAllPassCount(vegOrFruRes.getFruPassCount()+ vegOrFruRes.getVegPassCount());
                vegOrFruRes.setAllPassRate((double) (vegOrFruRes.getAllPassCount()/vegOrFruRes.getAllSamplingCount()));
                current_res.add(vegOrFruRes);
//                System.out.println("current_res的list里面的值："+current_res);
//                System.out.println("current_res.get(0)的值："+current_res.get(0));
                res.put(CitySampleTestDetail.getSamplingLocation(),current_res);
            }
            //如果检测单位名称已经存在
            else{
                // 从结果集中获取当前检测单位的结果列表
                List<dlDetRecordSampleRes> existingRes = res.get(CitySampleTestDetail.getSamplingLocation());
                dlDetRecordSampleRes vegOrFruRes = existingRes.get(0); // 假设每个检测单位只有一个结果对象
                System.out.println("dlDetRecordSampleRes vegOrFruRes = existingRes.get(0)"+vegOrFruRes);
                // 计算蔬菜或水果的结果
                if(0 == check_type(CitySampleTestDetail.getVegFruName())){
                    // 更新蔬菜采样数和总采样数
                    vegOrFruRes.setVegSamplingCount(vegOrFruRes.getVegSamplingCount() + 1);
                    vegOrFruRes.setAllSamplingCount(vegOrFruRes.getAllSamplingCount() + 1);

                    // 检测是否超标
                    if(checkPesticideIsPass(CitySampleTestDetail)){
                        // 更新蔬菜合格数
                        vegOrFruRes.setVegPassCount(vegOrFruRes.getVegPassCount() + 1);
                    }
                    // 更新蔬菜合格率
                    vegOrFruRes.setVegPassRate((double) vegOrFruRes.getVegPassCount() / vegOrFruRes.getVegSamplingCount());
                }
                else if(1 == check_type(CitySampleTestDetail.getVegFruName())){
                    // 更新水果采样数和总采样数
                    vegOrFruRes.setFruSamplingCount(vegOrFruRes.getFruSamplingCount() + 1);
                    vegOrFruRes.setAllSamplingCount(vegOrFruRes.getAllSamplingCount() + 1);

                    // 检测是否超标
                    if(checkPesticideIsPass(CitySampleTestDetail)){
                        // 更新水果合格数
                        vegOrFruRes.setFruPassCount(vegOrFruRes.getFruPassCount() + 1);
                    }
                    // 更新水果合格率
                    vegOrFruRes.setFruPassRate((double) vegOrFruRes.getFruPassCount() / vegOrFruRes.getFruSamplingCount());
                }else {
                    System.out.println("到这里的话说明获取到样本的具体名字没有找到其是蔬菜还是水果的类型，提醒用户加入对应字典");
                }

                // 更新总合格数和总合格率
                vegOrFruRes.setAllPassCount(vegOrFruRes.getFruPassCount() + vegOrFruRes.getVegPassCount());
                vegOrFruRes.setAllPassRate((double) vegOrFruRes.getAllPassCount() / vegOrFruRes.getAllSamplingCount());

                // 由于existingRes是引用类型，更新vegOrFruRes后，existingRes中的内容也会更新，所以不需要再次put到map中
                System.out.println("更新后的current_res的list里面的值：" + existingRes);
                System.out.println("更新后的current_res.get(0)的值：" + existingRes.get(0));
            }
        }
        return res;
    }

    /**
     * 新增定量监测结果汇总
     *
     * @param outDlDetectRecords 定量监测结果汇总
     * @return 结果
     */
    @Override
    public int insertoutDlDetectRecords(outDlDetectRecords outDlDetectRecords) {
        return outDlDetectRecordsMapper.insertoutDlDetectRecords(outDlDetectRecords);
    }

    /**
     * 修改定量监测结果汇总
     *
     * @param outDlDetectRecords 定量监测结果汇总
     * @return 结果
     */
    @Override
    public int updateoutDlDetectRecords(outDlDetectRecords outDlDetectRecords) {
        return outDlDetectRecordsMapper.updateoutDlDetectRecords(outDlDetectRecords);
    }

    /**
     * 批量删除定量监测结果汇总
     *
     * @param recordDlIds 需要删除的定量监测结果汇总主键
     * @return 结果
     */
    @Override
    public int deleteoutDlDetectRecordsByRecordDlIds(Long[] recordDlIds) {
        return outDlDetectRecordsMapper.deleteoutDlDetectRecordsByRecordDlIds(recordDlIds);
    }

    /**
     * 删除定量监测结果汇总信息
     *
     * @param recordDlId 定量监测结果汇总主键
     * @return 结果
     */
    @Override
    public int deleteoutDlDetectRecordsByRecordDlId(Long recordDlId) {
        return outDlDetectRecordsMapper.deleteoutDlDetectRecordsByRecordDlId(recordDlId);
    }
}
