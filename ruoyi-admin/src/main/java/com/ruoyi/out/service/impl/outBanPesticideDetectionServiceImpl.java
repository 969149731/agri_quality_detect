package com.ruoyi.out.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.domain.agriPesticideDetResultForBanPesticideDetection;
import com.ruoyi.detection.domain.agriPesticideDetResultForOutExceedSampleDetail;
import com.ruoyi.myUtils.AgriUtils;
import com.ruoyi.out.domain.out2BanPesticideDetection;
import com.ruoyi.out.domain.out2ExceedSampleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outBanPesticideDetectionMapper;
import com.ruoyi.out.domain.outBanPesticideDetection;
import com.ruoyi.out.service.IoutBanPesticideDetectionService;
import com.ruoyi.detection.mapper.agriCitySampleTestDetailsMapper;
import com.ruoyi.out.mapper.outDlDetectRecordsMapper;
import com.ruoyi.detection.mapper.agriPesticideDetResultMapper;


/**
 * 蔬菜水果禁用农药检出样品明细Service业务层处理
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outBanPesticideDetectionServiceImpl implements IoutBanPesticideDetectionService 
{
    @Autowired
    private outBanPesticideDetectionMapper outBanPesticideDetectionMapper;
    @Autowired
    private agriCitySampleTestDetailsMapper agriCitySampleTestDetailsMapper;
    @Autowired
    private outDlDetectRecordsMapper outDlDetectRecordsMapper;
    @Autowired
    private agriPesticideDetResultMapper agriPesticideDetResultMapper;

    /**
     * 查询蔬菜水果禁用农药检出样品明细
     * 
     * @param banPesticideDetectionId 蔬菜水果禁用农药检出样品明细主键
     * @return 蔬菜水果禁用农药检出样品明细
     */
    @Override
    public outBanPesticideDetection selectoutBanPesticideDetectionByBanPesticideDetectionId(Long banPesticideDetectionId)
    {
        return outBanPesticideDetectionMapper.selectoutBanPesticideDetectionByBanPesticideDetectionId(banPesticideDetectionId);
    }

    /**
     * 查询蔬菜水果禁用农药检出样品明细列表
     * 
     * @param outBanPesticideDetection 蔬菜水果禁用农药检出样品明细
     * @return 蔬菜水果禁用农药检出样品明细
     */
    @Override
    public List<outBanPesticideDetection> selectoutBanPesticideDetectionList(outBanPesticideDetection outBanPesticideDetection)
    {
        return outBanPesticideDetectionMapper.selectoutBanPesticideDetectionList(outBanPesticideDetection);
    }

    /**
     * 新增蔬菜水果禁用农药检出样品明细
     * 
     * @param outBanPesticideDetection 蔬菜水果禁用农药检出样品明细
     * @return 结果
     */
    @Override
    public int insertoutBanPesticideDetection(outBanPesticideDetection outBanPesticideDetection)
    {
        return outBanPesticideDetectionMapper.insertoutBanPesticideDetection(outBanPesticideDetection);
    }

    /**
     * 修改蔬菜水果禁用农药检出样品明细
     * 
     * @param outBanPesticideDetection 蔬菜水果禁用农药检出样品明细
     * @return 结果
     */
    @Override
    public int updateoutBanPesticideDetection(outBanPesticideDetection outBanPesticideDetection)
    {
        return outBanPesticideDetectionMapper.updateoutBanPesticideDetection(outBanPesticideDetection);
    }

    /**
     * 批量删除蔬菜水果禁用农药检出样品明细
     * 
     * @param banPesticideDetectionIds 需要删除的蔬菜水果禁用农药检出样品明细主键
     * @return 结果
     */
    @Override
    public int deleteoutBanPesticideDetectionByBanPesticideDetectionIds(Long[] banPesticideDetectionIds)
    {
        return outBanPesticideDetectionMapper.deleteoutBanPesticideDetectionByBanPesticideDetectionIds(banPesticideDetectionIds);
    }

    /**
     * 删除蔬菜水果禁用农药检出样品明细信息
     * 
     * @param banPesticideDetectionId 蔬菜水果禁用农药检出样品明细主键
     * @return 结果
     */
    @Override
    public int deleteoutBanPesticideDetectionByBanPesticideDetectionId(Long banPesticideDetectionId)
    {
        return outBanPesticideDetectionMapper.deleteoutBanPesticideDetectionByBanPesticideDetectionId(banPesticideDetectionId);
    }


    //判断样品是蔬菜还是水果函数   标记，一下这边要改，少了茶叶的判断
    public int check_type(String sampleName) {
        String type = outDlDetectRecordsMapper.checkSampleType(sampleName);
        if ("蔬菜".equals(type)) {
            return 0;
        } else if("水果".equals(type)) {
            return 1;
        }else if("茶叶".equals(type)) {
            return 2;
        }else{
            System.out.println("样品："+sampleName+"，未知是蔬菜还是水果，请在蔬果字典中添加该样品是属于蔬菜还是水果。");
            // 下面的代码是把不知道是水果还是蔬菜的样本输出到C盘中，方便查看添加。
            List<String> messages = new ArrayList<>();
            messages.add("样品（veg_fru_name）："+sampleName+"，种类（veg_fru_type）：未知，请分析出该样品是蔬菜还是水果还是茶叶");
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

    //蔬菜水果禁用农药检出明细
    @Override
    public List<out2BanPesticideDetection> selectOutBanPesticideDetectionList(agriCitySampleTestDetails agriCitySampleTestDetails) {
        AgriUtils.permissionToDifferentiateData(agriCitySampleTestDetails);

        //最后要往out2BanPesticideDetectionList这里面add一个out2BanPesticideDetection对象
        List<out2BanPesticideDetection> out2BanPesticideDetectionList = new ArrayList<>();
        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
        //遍历导入表中的每一条数据
        for (agriCitySampleTestDetails CitySampleTestDetail : agriCitySampleTestDetailsList) {
            //创建一个out2ExceedSampleDetail对象，最后add到要返回的List<out2BanPesticideDetection>中
            out2BanPesticideDetection out2BanPesticideDetection = new out2BanPesticideDetection();
            out2BanPesticideDetection.setDetectUnit(CitySampleTestDetail.getDetectLocation());
            out2BanPesticideDetection.setSampleCode(CitySampleTestDetail.getSampleCode());
            out2BanPesticideDetection.setVegFruName(CitySampleTestDetail.getVegFruName());
            out2BanPesticideDetection.setSamplingLocation(CitySampleTestDetail.getSamplingLocation());
            //在当前遍历的数据中，检查一下是当前的数据中的样本是蔬菜还是水果,如果i=0为蔬菜 ， i=1为水果，i=2是茶叶。
            int i = check_type(CitySampleTestDetail.getVegFruName());
            //如果是蔬菜
            if(i==0){
                List<agriPesticideDetResult> agriPesticideDetResults = agriPesticideDetResultMapper.selectagriPesticideDetResultByCitySampleTestDetailsId(CitySampleTestDetail.getCitySampleTestDetailsId());
                List<agriPesticideDetResultForBanPesticideDetection> agriPesticideDetResultList = new ArrayList<>();
                for (agriPesticideDetResult agriPesticideDetResult : agriPesticideDetResults) {
                    String pesticideName = agriPesticideDetResult.getPesticideName();
                    //如果是蔬菜禁用农药
                    if("甲胺磷".equals(pesticideName)||"乙酰甲胺磷".equals(pesticideName)||"甲拌磷".equals(pesticideName)||"氧乐果".equals(pesticideName)||"毒死蜱".equals(pesticideName)||"特丁硫磷".equals(pesticideName)||"三唑磷".equals(pesticideName)||"水胺硫磷".equals(pesticideName)||"治螟磷".equals(pesticideName)||"乐果".equals(pesticideName)||"甲基异柳磷".equals(pesticideName)||"氟虫腈".equals(pesticideName)||"克百威".equals(pesticideName)){
                        agriPesticideDetResultForBanPesticideDetection agriPesticideDetResultForBanPesticideDetection = new agriPesticideDetResultForBanPesticideDetection();
                        agriPesticideDetResultForBanPesticideDetection.setPesticideName(pesticideName);
                        agriPesticideDetResultForBanPesticideDetection.setPesticideDetValue(Double.valueOf(agriPesticideDetResult.getPesticideDetValue()));
                        //获取上传的数据中具体的样品名【蔬菜水果名】，用查询该样品农药限量值
                        String vegFruName = CitySampleTestDetail.getVegFruName();
                        //标准，默认依据国家标准
                        String standardCategory = "国家标准";
                        //根据上传的数据中有检测出有值的【农药名】和具体的【蔬菜水果名】获取农药标准表中的中国标准的该农药限量值
                        Double limitValue = outDlDetectRecordsMapper.selectLimitValueByPesticideName(pesticideName, vegFruName, standardCategory);
                        agriPesticideDetResultForBanPesticideDetection.setLimitValue(limitValue);
                        agriPesticideDetResultList.add(agriPesticideDetResultForBanPesticideDetection);
                        out2BanPesticideDetection.setExceedPesticideNameAndPesticideValueAndlimitValue(agriPesticideDetResultList);
                        //获取到的数据可能是重复的，这边进行判断，因为if语句里面是添加东西到list中，if又在检测结果的for循环里面，所以可能会有重复的结果数据，不重复的才添加到最后返回的list中
                        boolean contains = out2BanPesticideDetectionList.contains(out2BanPesticideDetection);
                        //如果最终返回的list中没有包含马上要添加的数据，则进行添加
                        if(!contains){
                            out2BanPesticideDetectionList.add(out2BanPesticideDetection);
                        }
                    }
                }
            }
            //如果是水果
            if(i==1){
                List<agriPesticideDetResult> agriPesticideDetResults = agriPesticideDetResultMapper.selectagriPesticideDetResultByCitySampleTestDetailsId(CitySampleTestDetail.getCitySampleTestDetailsId());
                List<agriPesticideDetResultForBanPesticideDetection> agriPesticideDetResultList = new ArrayList<>();
                for (agriPesticideDetResult agriPesticideDetResult : agriPesticideDetResults) {
                    String pesticideName = agriPesticideDetResult.getPesticideName();
                    //如果是水果禁用农药
                    //也可以这么写  简单
                    // Set<String> pesticides = new HashSet<>(Arrays.asList("甲胺磷", "乙酰甲胺磷", "甲拌磷", "氧乐果", "毒死蜱", "特丁硫磷", "三唑磷", "水胺硫磷", "治螟磷", "乐果", "甲基异柳磷", "氟虫腈", "克百威"));
                    //if (pesticides.contains(pesticideName)) {
                    // 你的逻辑代码
                    //}
                    if("甲胺磷".equals(pesticideName)||"乙酰甲胺磷".equals(pesticideName)||"甲拌磷".equals(pesticideName)||"氧乐果".equals(pesticideName)||"水胺硫磷".equals(pesticideName)||"乐果".equals(pesticideName)||"甲基异柳磷".equals(pesticideName)||"氟虫腈".equals(pesticideName)||"克百威".equals(pesticideName)||"涕灭威".equals(pesticideName)){
                        agriPesticideDetResultForBanPesticideDetection agriPesticideDetResultForBanPesticideDetection = new agriPesticideDetResultForBanPesticideDetection();
                        agriPesticideDetResultForBanPesticideDetection.setPesticideName(pesticideName);
                        agriPesticideDetResultForBanPesticideDetection.setPesticideDetValue(Double.valueOf(agriPesticideDetResult.getPesticideDetValue()));
                        //获取上传的数据中具体的样品名【蔬菜水果名】，用查询该样品农药限量值
                        String vegFruName = CitySampleTestDetail.getVegFruName();
                        //标准，默认依据国家标准
                        String standardCategory = "国家标准";
                        //根据上传的数据中有检测出有值的【农药名】和具体的【蔬菜水果名】获取农药标准表中的中国标准的该农药限量值
                        Double limitValue = outDlDetectRecordsMapper.selectLimitValueByPesticideName(pesticideName, vegFruName, standardCategory);
                        //如果限定值为空，前端或者导出的excel不显示限定值的话不好看，先搞成0.01，这个数据也比较好
                        if(limitValue==null){
                            limitValue=0.01;
                        }
                        agriPesticideDetResultForBanPesticideDetection.setLimitValue(limitValue);
                        agriPesticideDetResultList.add(agriPesticideDetResultForBanPesticideDetection);
                        out2BanPesticideDetection.setExceedPesticideNameAndPesticideValueAndlimitValue(agriPesticideDetResultList);
                        //获取到的数据可能是重复的，这边进行判断，因为if语句里面是添加东西到list中，if又在检测结果的for循环里面，所以可能会有重复的结果数据，不重复的才添加到最后返回的list中
                        boolean contains = out2BanPesticideDetectionList.contains(out2BanPesticideDetection);
                        //如果最终返回的list中没有包含马上要添加的数据，则进行添加
                        if(!contains){
                            out2BanPesticideDetectionList.add(out2BanPesticideDetection);
                        }
                    }
                }
            }

            //如果是茶叶
            if(i==2){
                List<agriPesticideDetResult> agriPesticideDetResults = agriPesticideDetResultMapper.selectagriPesticideDetResultByCitySampleTestDetailsId(CitySampleTestDetail.getCitySampleTestDetailsId());
                List<agriPesticideDetResultForBanPesticideDetection> agriPesticideDetResultList = new ArrayList<>();
                for (agriPesticideDetResult agriPesticideDetResult : agriPesticideDetResults) {
                    String pesticideName = agriPesticideDetResult.getPesticideName();

                    if("六六六".equals(pesticideName)||"滴滴涕".equals(pesticideName)||"三氯杀螨醇".equals(pesticideName)||"甲胺磷".equals(pesticideName)||"乙酰甲胺磷".equals(pesticideName)||"灭多威".equals(pesticideName)||"氰戊菊酯".equals(pesticideName)){
                        agriPesticideDetResultForBanPesticideDetection agriPesticideDetResultForBanPesticideDetection = new agriPesticideDetResultForBanPesticideDetection();
                        agriPesticideDetResultForBanPesticideDetection.setPesticideName(pesticideName);
                        agriPesticideDetResultForBanPesticideDetection.setPesticideDetValue(Double.valueOf(agriPesticideDetResult.getPesticideDetValue()));
                        //获取上传的数据中具体的样品名【蔬菜水果名】，用查询该样品农药限量值
                        String vegFruName = CitySampleTestDetail.getVegFruName();
                        //标准，默认依据国家标准
                        String standardCategory = "国家标准";
                        //根据上传的数据中有检测出有值的【农药名】和具体的【蔬菜水果名】获取农药标准表中的中国标准的该农药限量值
                        Double limitValue = outDlDetectRecordsMapper.selectLimitValueByPesticideName(pesticideName, vegFruName, standardCategory);
                        //如果限定值为空，前端或者导出的excel不显示限定值的话不好看，先搞成0.01，这个数据也比较好
                        if(limitValue==null){
                            limitValue=0.01;
                        }
                        agriPesticideDetResultForBanPesticideDetection.setLimitValue(limitValue);
                        agriPesticideDetResultList.add(agriPesticideDetResultForBanPesticideDetection);
                        out2BanPesticideDetection.setExceedPesticideNameAndPesticideValueAndlimitValue(agriPesticideDetResultList);
                        //获取到的数据可能是重复的，这边进行判断，因为if语句里面是添加东西到list中，if又在检测结果的for循环里面，所以可能会有重复的结果数据，不重复的才添加到最后返回的list中
                        boolean contains = out2BanPesticideDetectionList.contains(out2BanPesticideDetection);
                        //如果最终返回的list中没有包含马上要添加的数据，则进行添加
                        if(!contains){
                            out2BanPesticideDetectionList.add(out2BanPesticideDetection);
                        }
                    }
                }
            }



        }
        return out2BanPesticideDetectionList;
    }
}
