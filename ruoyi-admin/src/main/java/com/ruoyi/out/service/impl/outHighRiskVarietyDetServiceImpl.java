package com.ruoyi.out.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;
import com.ruoyi.out.domain.dlDetRecordSampleRes;
import com.ruoyi.out.mapper.outDlDetectRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outHighRiskVarietyDetMapper;
import com.ruoyi.out.domain.outHighRiskVarietyDet;
import com.ruoyi.out.service.IoutHighRiskVarietyDetService;

/**
 * 高风险品种样品检出情况Service业务层处理
 *
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outHighRiskVarietyDetServiceImpl implements IoutHighRiskVarietyDetService {
    @Autowired
    private outHighRiskVarietyDetMapper outHighRiskVarietyDetMapper;
    @Autowired
    private com.ruoyi.detection.mapper.agriPesticideDetResultMapper agriPesticideDetResultMapper;
    @Autowired
    private com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;
    @Autowired
    private com.ruoyi.out.mapper.outDlDetectRecordsMapper outDlDetectRecordsMapper;


    /**
     * 查询高风险品种样品检出情况
     *
     * @param outHighRiskVarietyDetId 高风险品种样品检出情况主键
     * @return 高风险品种样品检出情况
     */
    @Override
    public outHighRiskVarietyDet selectoutHighRiskVarietyDetByOutHighRiskVarietyDetId(Long outHighRiskVarietyDetId) {
        return outHighRiskVarietyDetMapper.selectoutHighRiskVarietyDetByOutHighRiskVarietyDetId(outHighRiskVarietyDetId);
    }

    /**
     * 查询高风险品种样品检出情况列表
     *
     * @param outHighRiskVarietyDet 高风险品种样品检出情况
     * @return 高风险品种样品检出情况
     */
    @Override
    public List<outHighRiskVarietyDet> selectoutHighRiskVarietyDetList(outHighRiskVarietyDet outHighRiskVarietyDet) {
        return outHighRiskVarietyDetMapper.selectoutHighRiskVarietyDetList(outHighRiskVarietyDet);
    }

    /**
     * 新增高风险品种样品检出情况
     *
     * @param outHighRiskVarietyDet 高风险品种样品检出情况
     * @return 结果
     */
    @Override
    public int insertoutHighRiskVarietyDet(outHighRiskVarietyDet outHighRiskVarietyDet) {
        return outHighRiskVarietyDetMapper.insertoutHighRiskVarietyDet(outHighRiskVarietyDet);
    }

    /**
     * 修改高风险品种样品检出情况
     *
     * @param outHighRiskVarietyDet 高风险品种样品检出情况
     * @return 结果
     */
    @Override
    public int updateoutHighRiskVarietyDet(outHighRiskVarietyDet outHighRiskVarietyDet) {
        return outHighRiskVarietyDetMapper.updateoutHighRiskVarietyDet(outHighRiskVarietyDet);
    }

    /**
     * 批量删除高风险品种样品检出情况
     *
     * @param outHighRiskVarietyDetIds 需要删除的高风险品种样品检出情况主键
     * @return 结果
     */
    @Override
    public int deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetIds(Long[] outHighRiskVarietyDetIds) {
        return outHighRiskVarietyDetMapper.deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetIds(outHighRiskVarietyDetIds);
    }

    /**
     * 删除高风险品种样品检出情况信息
     *
     * @param outHighRiskVarietyDetId 高风险品种样品检出情况主键
     * @return 结果
     */
    @Override
    public int deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetId(Long outHighRiskVarietyDetId) {
        return outHighRiskVarietyDetMapper.deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetId(outHighRiskVarietyDetId);
    }

//    @Override
//    public Map<String, List<outHighRiskVarietyDet>> selectOutHighRiskVarietyDetList(agriCitySampleTestDetails agriCitySampleTestDetails) {
//
//        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
////        Map<String, List<outHighRiskVarietyDet>> res = new HashMap<>();
//        Map<String, Map<String,List<outHighRiskVarietyDet>>> resMap = new HashMap<>();
//        Map<String, List<outHighRiskVarietyDet>> internalMap = new HashMap<>();
//        //开始遍历每一条导入表中的详细记录
//        for (agriCitySampleTestDetails CitySampleTestDetail : agriCitySampleTestDetailsList) {
//            List<outHighRiskVarietyDet> highRiskVarietyDetList = new ArrayList<>();
//            //如果承检单位是第一次出现
//            if(!resMap.containsKey(CitySampleTestDetail.getDetectLocation())){
//                //通过样品名称查看该样品是否为高风险样品
//                String highRiskSample = outHighRiskVarietyDetMapper.selectHighRiskSampleByVegFruName(CitySampleTestDetail.getVegFruName());
//                //判断该样品是否为高风险样品
//                if("高风险".equals(highRiskSample)){
//                    System.out.println(CitySampleTestDetail.getVegFruName()+"是高风险的");
//                    internalMap.put(CitySampleTestDetail.getVegFruName(),highRiskVarietyDetList);
//                    resMap.put(CitySampleTestDetail.getDetectLocation(),internalMap);
//                    // 检查键是否存在于任何一个内层Map中
//                    boolean keyExists = resMap.values().stream() // 获取外层Map的所有值（即内层Maps）
//                            .anyMatch(innerMap -> innerMap.containsKey(CitySampleTestDetail.getVegFruName())); // 检查内层Map是否包含指定的键
//                    if(keyExists){
//                        System.out.println("qqqqqqqqqqq"+CitySampleTestDetail.getVegFruName());
//                    }
//                }
//            }else {
//                //通过样品名称查看该样品是否为高风险样品
//                String highRiskSample = outHighRiskVarietyDetMapper.selectHighRiskSampleByVegFruName(CitySampleTestDetail.getVegFruName());
//                //判断该样品是否为高风险样品
//                if("高风险".equals(highRiskSample)){
//                    System.out.println(CitySampleTestDetail.getVegFruName()+"是高风险的");
//                    internalMap.put(CitySampleTestDetail.getVegFruName(),highRiskVarietyDetList);
//                    resMap.put(CitySampleTestDetail.getDetectLocation(),internalMap);
//                    // 检查键是否存在于任何一个内层Map中
//                    boolean keyExists = resMap.values().stream() // 获取外层Map的所有值（即内层Maps）
//                            .anyMatch(innerMap -> innerMap.containsKey(CitySampleTestDetail.getVegFruName())); // 检查内层Map是否包含指定的键
//                    if(keyExists){
//                        System.out.println("qqqqqqqqqqq"+CitySampleTestDetail.getVegFruName());
//                    }
//                }
//            }
//
//
//        }
//
//
//        return null;
//    }


//    @Override
//    public Map<String, List<outHighRiskVarietyDet>> selectOutHighRiskVarietyDetList(agriCitySampleTestDetails agriCitySampleTestDetails) {
//        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
//        Map<String, Map<String, List<outHighRiskVarietyDet>>> resMap = new HashMap<>();
//
//        // 遍历每一条导入表中的详细记录
//        for (agriCitySampleTestDetails detail : agriCitySampleTestDetailsList) {
//            // 检查样品是否为高风险样品
//            String highRiskSample = outHighRiskVarietyDetMapper.selectHighRiskSampleByVegFruName(detail.getVegFruName());
//            if (!"高风险".equals(highRiskSample)) {
//                continue; // 如果不是高风险样品，跳过当前循环
//            }
//
//            // 使用computeIfAbsent来确保每个承检单位有一个对应的Map
//            Map<String, List<outHighRiskVarietyDet>> varietyMap = resMap.computeIfAbsent(detail.getDetectLocation(), k -> new HashMap<>());
//
//            // 对于每个品种，获取其对应的List，如果不存在则创建
//            List<outHighRiskVarietyDet> varietyList = varietyMap.computeIfAbsent(detail.getVegFruName(), k -> new ArrayList<>());
//
//            // 创建一个新的outHighRiskVarietyDet对象并设置其属性
//            outHighRiskVarietyDet varietyDet = new outHighRiskVarietyDet();
//            varietyDet.setTotalSamples(16L);
//            varietyDet.setQualificationRate(BigDecimal.valueOf(3.14));
//            // 假设你有一个方法来填充varietyDet的属性，例如：
//            // varietyDet.setSampleCount(detail.getSampleCount());
//            // 注意：这里的setSampleCount方法是假设的，你需要根据实际的方法名来调用
//
//            // 将新的outHighRiskVarietyDet对象添加到列表中
//            varietyList.add(varietyDet);
//            int sampleCount = varietyList.size();
//            System.out.println("样品样品"+detail.getVegFruName()+sampleCount);
//
//            // 将更新后的varietyList放回varietyMap中
//            varietyMap.put(detail.getVegFruName(), varietyList);
//        }
////        System.out.println("最后要看的"+resMap);
//
//        return null;
//    }

//    @Override
//    public Map<String, List<outHighRiskVarietyDet>> selectOutHighRiskVarietyDetList(agriCitySampleTestDetails agriCitySampleTestDetails) {
//        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
//        Map<String, Map<String, List<outHighRiskVarietyDet>>> resMap = new HashMap<>();
//        Map<String, List<outHighRiskVarietyDet>> varietyMap = new HashMap<>();
//        Map<String, Integer> varietySampleCountMap = new HashMap<>(); // 辅助Map来跟踪每个品种的sampleCount
//
//        // 遍历每一条导入表中的详细记录
//        for (agriCitySampleTestDetails detail : agriCitySampleTestDetailsList) {
//            // 检查样品是否为高风险样品
//            String highRiskSample = outHighRiskVarietyDetMapper.selectHighRiskSampleByVegFruName(detail.getVegFruName());
//            if (!"高风险".equals(highRiskSample)) {
//                continue; // 如果不是高风险样品，跳过当前循环
//            }
//            // 使用computeIfAbsent来确保每个承检单位有一个对应的Map
//            varietyMap = resMap.computeIfAbsent(detail.getDetectLocation(), k -> new HashMap<>());
//
//            // 对于每个品种，获取其对应的List，如果不存在则创建
//            List<outHighRiskVarietyDet> varietyList = varietyMap.computeIfAbsent(detail.getVegFruName(), k -> new ArrayList<>());
//
//            // 更新品种的sampleCount
//            varietySampleCountMap.merge(detail.getVegFruName(), 1, Integer::sum); // 假设每次遍历增加1，根据实际情况调整
//
//            // 检查是否需要创建新的outHighRiskVarietyDet对象
//            if (varietyList.isEmpty()) {
//                // 创建一个新的outHighRiskVarietyDet对象并设置其属性
//                outHighRiskVarietyDet varietyDet = new outHighRiskVarietyDet();
//                varietyDet.setTotalSamples((long) varietySampleCountMap.get(detail.getVegFruName())); // 设置sampleCount
//                varietyDet.setQualificationRate(BigDecimal.valueOf(3.14));
//                // 添加到列表中
//                varietyList.add(varietyDet);
//            } else {
//                // 如果List已经有对象，更新sampleCount
//                outHighRiskVarietyDet existingDet = varietyList.get(0);
//                existingDet.setTotalSamples((long) varietySampleCountMap.get(detail.getVegFruName()));
//            }
//
//            // 将更新后的varietyList放回varietyMap中
//            varietyMap.put(detail.getVegFruName(), varietyList);
//        }
//        System.out.println(varietySampleCountMap+"看看");
//
//        return null;
//    }


//    @Override
//    public Map<String, List<outHighRiskVarietyDet>> selectOutHighRiskVarietyDetList(agriCitySampleTestDetails agriCitySampleTestDetails) {
//
//        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
//        Map<String, Map<String,List<outHighRiskVarietyDet>>> resMap = new HashMap<>();
//        Map<String, List<outHighRiskVarietyDet>> internalMap = new HashMap<>();
//        //开始遍历每一条导入表中的详细记录
//        for (agriCitySampleTestDetails CitySampleTestDetail : agriCitySampleTestDetailsList) {
//            List<outHighRiskVarietyDet> highRiskVarietyDetList = new ArrayList<>();
//            //如果承检单位是第一次出现
//            if(!resMap.containsKey(CitySampleTestDetail.getDetectLocation())){
//                //通过样品名称查看该样品是否为高风险样品
//                String highRiskSample = outHighRiskVarietyDetMapper.selectHighRiskSampleByVegFruName(CitySampleTestDetail.getVegFruName());
//                //判断该样品是否为高风险样品
//                if("高风险".equals(highRiskSample)){
//                    System.out.println(CitySampleTestDetail.getVegFruName()+"是高风险的");
//                    internalMap.put(CitySampleTestDetail.getVegFruName(),highRiskVarietyDetList);
//                    resMap.put(CitySampleTestDetail.getDetectLocation(),internalMap);
//                    // 检查键是否存在于任何一个内层Map中
//                    boolean keyExists = resMap.values().stream() // 获取外层Map的所有值（即内层Maps）
//                            .anyMatch(innerMap -> innerMap.containsKey(CitySampleTestDetail.getVegFruName())); // 检查内层Map是否包含指定的键
//                    if(keyExists){
//                        System.out.println("qqqqqqqqqqq"+CitySampleTestDetail.getVegFruName());
//                    }
//                }
//            }else {
//                //通过样品名称查看该样品是否为高风险样品
//                String highRiskSample = outHighRiskVarietyDetMapper.selectHighRiskSampleByVegFruName(CitySampleTestDetail.getVegFruName());
//                //判断该样品是否为高风险样品
//                if("高风险".equals(highRiskSample)){
//                    System.out.println(CitySampleTestDetail.getVegFruName()+"是高风险的");
//                    internalMap.put(CitySampleTestDetail.getVegFruName(),highRiskVarietyDetList);
//                    resMap.put(CitySampleTestDetail.getDetectLocation(),internalMap);
//                    // 检查键是否存在于任何一个内层Map中
//                    boolean keyExists = resMap.values().stream() // 获取外层Map的所有值（即内层Maps）
//                            .anyMatch(innerMap -> innerMap.containsKey(CitySampleTestDetail.getVegFruName())); // 检查内层Map是否包含指定的键
//                    if(keyExists){
//                        System.out.println("qqqqqqqqqqq"+CitySampleTestDetail.getVegFruName());
//                    }
//                }
//            }
//
//
//        }
//
//
//        return null;
//    }


    //判断某样品对应某标准的某农药是否超标函数   true-超标       false-不超标    输入农药名称，残留值，样品名称
    public boolean checkPesticideIsPass(String pesticideName, double pesticideDetValue, String vegFruName) {
        // 超标和不超标的标识，刚刚开始超标和不超标都是为0
        int flagPass = 0;
        int flagNoPass = 0;
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
        }
        //如果传的数据中有检测出有值的农药值 大于 该农药限量值 ,说明超标了
        if (pesticideDetValue > limitValue) {
            flagNoPass = flagNoPass + 1;
        }
        //没有通过的数量大于0，说明超标了 返回true
        if (flagNoPass > 0) {
            return true;
        }
        return false;
    }


    //判断样品是蔬菜还是水果函数   返回0是蔬菜   1是水果
    public int check_type(String sampleName) {
        String type = outDlDetectRecordsMapper.checkSampleType(sampleName);
        if ("蔬菜".equals(type)) {
            return 0;
        } else if ("水果".equals(type)) {
            return 1;
        } else {
            System.out.println("样品：" + sampleName + "，未知是蔬菜还是水果，请在蔬果字典中添加该样品是属于蔬菜还是水果。");
            // 下面的代码是把不知道是水果还是蔬菜的样本输出到C盘中，方便查看添加。
            List<String> messages = new ArrayList<>();
            messages.add("样品（veg_fru_name）：" + sampleName + "，种类（veg_fru_type）：未知，请分析出该样品是蔬菜还是水果");
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

    //判断是不是禁用农药：输入农药名称 ，样品名称，输出true-是禁用农药 false-不是禁用农药，为常规农药
    public boolean checkPesticideIsBan(String pesticideName, String vegFruName) {
        //看看当前输入的样品是蔬菜还是水果     返回0是蔬菜   1是水果
        int i = check_type(vegFruName);
        //如果是蔬菜
        if (i == 0) {
            if ("甲胺磷".equals(pesticideName) || "乙酰甲胺磷".equals(pesticideName) || "甲拌磷".equals(pesticideName) || "氧乐果".equals(pesticideName) || "毒死蜱".equals(pesticideName) || "特丁硫磷".equals(pesticideName) || "三唑磷".equals(pesticideName) || "水胺硫磷".equals(pesticideName) || "治螟磷".equals(pesticideName) || "乐果".equals(pesticideName) || "甲基异柳磷".equals(pesticideName) || "氟虫腈".equals(pesticideName) || "克百威".equals(pesticideName)) {
                return true;
            }
        }
        //如果是水果
        if (i == 1) {
            if ("甲胺磷".equals(pesticideName) || "乙酰甲胺磷".equals(pesticideName) || "甲拌磷".equals(pesticideName) || "氧乐果".equals(pesticideName) || "水胺硫磷".equals(pesticideName) || "乐果".equals(pesticideName) || "甲基异柳磷".equals(pesticideName) || "氟虫腈".equals(pesticideName) || "克百威".equals(pesticideName) || "涕灭威".equals(pesticideName)) {
                return true;
            }
        }
        return false;
    }


    //孙帅开始写的代码   方法返回值和参数可以先不管1
    public Map<String, List<outHighRiskVarietyDet>> selectOutHighRiskVarietyDetList(agriCitySampleTestDetails agriCitySampleTestDetails) {
        List<outHighRiskVarietyDet> outHighRiskVarietyDets = outHighRiskVarietyDetMapper.selectHighRiskSampleList();
        //开始遍历获取到的outHighRiskVarietyDets
        for (outHighRiskVarietyDet highRiskVarietyDet : outHighRiskVarietyDets) {
            //从det_res中获取信息进行禁用农药和农药超标的判断
            // 按逗号分割数据
            String[] entries = highRiskVarietyDet.getDet_res().split(",");
            Map<String, List<Map<String, Double>>> formattedData = new HashMap<>();
            for (String entry : entries) {

                // 分割每个数据项，得到编号、农药名称和农药值
                String[] parts = entry.split("-");
                String id = parts[0];
                String[] pesticideData = parts[1].split(":");
                String pesticideName = pesticideData[0];
                double pesticideValue = Double.parseDouble(pesticideData[1]);

                // 创建一个新的农药数据 map
                Map<String, Double> pesticideMap = new HashMap<>();
                pesticideMap.put(pesticideName, pesticideValue);
                // 将该农药数据 map 放入相应的编号列表中
                List<Map<String, Double>> pesticideList = formattedData.getOrDefault(id, new ArrayList<>());
                pesticideList.add(pesticideMap);
                formattedData.put(id, pesticideList);
            }

            //判断是否合格
            StringBuffer jinyong = new StringBuffer();
            StringBuffer chaobiao = new StringBuffer();
            int hege = formattedData.entrySet().size();
            for (Map.Entry<String, List<Map<String, Double>>> entry : formattedData.entrySet()) {
                System.out.println("编号: " + entry.getKey());
                boolean is1 = true;
                boolean is2 = true;
                for (Map<String, Double> pesticide : entry.getValue()) {
                    for (Map.Entry<String, Double> item : pesticide.entrySet()) {
                        System.out.println("农药名称: " + item.getKey() + ", 农药值: " + item.getValue());
                        //判断是否是禁用农药
                        if(true){
                            //是禁用农药
                            is1=false;
                            jinyong.append(item.getKey());
                            jinyong.append("\n");
                        }
                        //判断农药是否超标
                        if(true){
                            is2=false;
                            chaobiao.append(item.getKey());
                            chaobiao.append(" : ");
                            chaobiao.append(item.getValue());
                            chaobiao.append("\n");
                        }
                    }
                }
                if(!is1 || !is2){
                    //说明不合格
                    hege = hege - 1;
                }
            }
            //更新总数，合格数，合格率，检测结果
            highRiskVarietyDet.setTotalSamples(Long.valueOf(formattedData.entrySet().size()));
            highRiskVarietyDet.setQualifiedNumber(Long.valueOf(hege));
            highRiskVarietyDet.setQualificationRate(new BigDecimal(highRiskVarietyDet.getQualifiedNumber()/highRiskVarietyDet.getTotalSamples()));
            highRiskVarietyDet.setProhibitedPesticideDetection(jinyong.toString());
            highRiskVarietyDet.setRoutinePesticideExceedance(chaobiao.toString());
        }
        System.out.println("得到的list" + outHighRiskVarietyDets);
        return null;
    }

}
