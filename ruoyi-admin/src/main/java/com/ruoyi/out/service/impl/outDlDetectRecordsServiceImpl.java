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
        System.out.println("看看123 ");
        String type = outDlDetectRecordsMapper.checkSampleType(sampleName);
        System.out.println("看看 "+type);
        if ("蔬菜".equals(type)) {
            return 0;
        } else if("水果".equals(type)) {
            return 1;
        }else{
//            System.out.println("样品："+sampleName+"，未知是蔬菜还是水果，请在蔬果字典中添加该样品是属于蔬菜还是水果。");
//            // 下面的代码是把不知道是水果还是蔬菜的样本输出到C盘中，方便查看添加。
//            List<String> messages = new ArrayList<>();
//            messages.add("样品（veg_fru_name）："+sampleName+"，种类（veg_fru_type）：未知，请分析出该样品是蔬菜还是水果");
//            // 指定输出文件的路径
//            String filePath = "C:\\check_fru_or_veg.txt";
//            // 读取文件内容到集合中，以便后续检查
//            Set<String> existingLines = new HashSet<>();
//            try {
//                existingLines.addAll(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
//                for (String message : messages) {
//                    if (!existingLines.contains(message)) { // 如果文件中不存在该消息，则追加
//                        writer.write(message);
//                        writer.newLine();
//                    }
//                }
//                System.out.println("文件写入成功，重复数据已去除！");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
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
//    @Override
//    public Map<String, List<dlDetRecordSampleRes>> selectOutDlDetectRecordsList(agriCitySampleTestDetails agriCitySampleTestDetails) throws ParseException {
////        agriCitySampleTestDetails agriCitySampleTestDetail = new agriCitySampleTestDetails();
////        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
////        agriCitySampleTestDetail.setSamplingDate(targetFormat.parse("2022-06-02"));
//        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
//
//        System.out.println("所有的检测详细列表!!!!:"+agriCitySampleTestDetailsList);
//        Map<String, List<dlDetRecordSampleRes>> res = new HashMap<>();
//        //开始遍历每一条导入表中的详细记录
//        for (agriCitySampleTestDetails CitySampleTestDetail : agriCitySampleTestDetailsList) {
//            List<dlDetRecordSampleRes> current_res = new ArrayList<>();
//            //如果被检测单位是第一次出现
//            if(!res.containsKey(CitySampleTestDetail.getSamplingLocation())){
//                dlDetRecordSampleRes vegOrFruRes = new dlDetRecordSampleRes(0,0,0.0,0,0,0.0,0,0,0.0);
//                //计算蔬菜结果
//                //如果是蔬菜 按照样本名称 从类型表中判断该样本是蔬菜还是水果，调用check_type(样品名称) --> 0-蔬菜 1-水果
//                if(0 == check_type(CitySampleTestDetail.getVegFruName())){
//                    vegOrFruRes.setVegSamplingCount(1);
//                    vegOrFruRes.setAllSamplingCount(vegOrFruRes.getAllSamplingCount()+1);
//                    //检测是否超标（ture不超标，false超标）
//                    //如果不超标，也就是ture，进入if语句，被检测单位是第一次出现，合格数就是1
//                    if(checkPesticideIsPass(CitySampleTestDetail)){
//                        vegOrFruRes.setVegPassCount(1);
//                    }
//                    //设置蔬菜的合格率
//                    vegOrFruRes.setVegPassRate((double) (vegOrFruRes.getVegPassCount()/vegOrFruRes.getVegSamplingCount()));
//                }
//                //如果是水果 按照样本名称 从类型表中判断该样本是蔬菜还是水果，调用check_type(样品名称) --> 0-蔬菜 1-水果
//                else if(1 == check_type(CitySampleTestDetail.getVegFruName())){
//                    vegOrFruRes.setFruSamplingCount(1);
//                    vegOrFruRes.setAllSamplingCount(vegOrFruRes.getAllSamplingCount()+1);
//                    if(checkPesticideIsPass(CitySampleTestDetail)){
//                        vegOrFruRes.setFruPassCount(1);
//                    }
//                    vegOrFruRes.setFruPassRate((double) (vegOrFruRes.getFruPassCount()/vegOrFruRes.getFruSamplingCount()));
//                }else{
//                    System.out.println("到这里的话说明获取到样本的具体名字没有找到其是蔬菜还是水果的类型，提醒用户加入对应字典");
//                }
//                vegOrFruRes.setAllPassCount(vegOrFruRes.getFruPassCount()+ vegOrFruRes.getVegPassCount());
//                vegOrFruRes.setAllPassRate((double) (vegOrFruRes.getAllPassCount()/vegOrFruRes.getAllSamplingCount()));
//                current_res.add(vegOrFruRes);
////                System.out.println("current_res的list里面的值："+current_res);
////                System.out.println("current_res.get(0)的值："+current_res.get(0));
//                res.put(CitySampleTestDetail.getSamplingLocation(),current_res);
//            }
//            //如果检测单位名称已经存在
//            else{
//                // 从结果集中获取当前检测单位的结果列表
//                List<dlDetRecordSampleRes> existingRes = res.get(CitySampleTestDetail.getSamplingLocation());
//                dlDetRecordSampleRes vegOrFruRes = existingRes.get(0); // 假设每个检测单位只有一个结果对象
//                System.out.println("dlDetRecordSampleRes vegOrFruRes = existingRes.get(0)"+vegOrFruRes);
//                // 计算蔬菜或水果的结果
//                if(0 == check_type(CitySampleTestDetail.getVegFruName())){
//                    // 更新蔬菜采样数和总采样数
//                    vegOrFruRes.setVegSamplingCount(vegOrFruRes.getVegSamplingCount() + 1);
//                    vegOrFruRes.setAllSamplingCount(vegOrFruRes.getAllSamplingCount() + 1);
//
//                    // 检测是否超标
//                    if(checkPesticideIsPass(CitySampleTestDetail)){
//                        // 更新蔬菜合格数
//                        vegOrFruRes.setVegPassCount(vegOrFruRes.getVegPassCount() + 1);
//                    }
//                    // 更新蔬菜合格率
//                    vegOrFruRes.setVegPassRate((double) vegOrFruRes.getVegPassCount() / vegOrFruRes.getVegSamplingCount());
//                }
//                else if(1 == check_type(CitySampleTestDetail.getVegFruName())){
//                    // 更新水果采样数和总采样数
//                    vegOrFruRes.setFruSamplingCount(vegOrFruRes.getFruSamplingCount() + 1);
//                    vegOrFruRes.setAllSamplingCount(vegOrFruRes.getAllSamplingCount() + 1);
//
//                    // 检测是否超标
//                    if(checkPesticideIsPass(CitySampleTestDetail)){
//                        // 更新水果合格数
//                        vegOrFruRes.setFruPassCount(vegOrFruRes.getFruPassCount() + 1);
//                    }
//                    // 更新水果合格率
//                    vegOrFruRes.setFruPassRate((double) vegOrFruRes.getFruPassCount() / vegOrFruRes.getFruSamplingCount());
//                }else {
//                    System.out.println("到这里的话说明获取到样本的具体名字没有找到其是蔬菜还是水果的类型，提醒用户加入对应字典");
//                }
//
//                // 更新总合格数和总合格率
//                vegOrFruRes.setAllPassCount(vegOrFruRes.getFruPassCount() + vegOrFruRes.getVegPassCount());
//                vegOrFruRes.setAllPassRate((double) vegOrFruRes.getAllPassCount() / vegOrFruRes.getAllSamplingCount());
//
//                // 由于existingRes是引用类型，更新vegOrFruRes后，existingRes中的内容也会更新，所以不需要再次put到map中
//                System.out.println("更新后的current_res的list里面的值：" + existingRes);
//                System.out.println("更新后的current_res.get(0)的值：" + existingRes.get(0));
//            }
//        }
//        return res;
//    }


    //定量检测汇总表List
    @Override
    public Map<String, List<dlDetRecordSampleRes>> selectOutDlDetectRecordsList(agriCitySampleTestDetails agriCitySampleTestDetails) throws ParseException {
        List<agriCitySampleTestDetails> agriCitySampleTestDetailsList = agriCitySampleTestDetailsMapper.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);

        System.out.println("所有的检测详细列表!!!!:"+agriCitySampleTestDetailsList);
        Map<String, List<dlDetRecordSampleRes>> res = new HashMap<>();
        //开始遍历每一条导入表中的详细记录
        for (agriCitySampleTestDetails CitySampleTestDetail : agriCitySampleTestDetailsList) {
            List<dlDetRecordSampleRes> current_res = new ArrayList<>();

            //玉林市
            if(CitySampleTestDetail.getSamplingLocation().contains("北流")){
                CitySampleTestDetail.setSamplingLocation("北流市");
            }
            else if(CitySampleTestDetail.getSamplingLocation().contains("玉州")||CitySampleTestDetail.getSamplingLocation().contains("玉东")){
                CitySampleTestDetail.setSamplingLocation("玉州区");
            }
            else if(CitySampleTestDetail.getSamplingLocation().contains("福绵")){
                CitySampleTestDetail.setSamplingLocation("福绵区");
            }
            else if(CitySampleTestDetail.getSamplingLocation().contains("容县")){
                CitySampleTestDetail.setSamplingLocation("容县");
            }
            else if(CitySampleTestDetail.getSamplingLocation().contains("陆川")){
                CitySampleTestDetail.setSamplingLocation("陆川县");
            }
            else if(CitySampleTestDetail.getSamplingLocation().contains("博白")){
                CitySampleTestDetail.setSamplingLocation("博白县");
            }
            else if(CitySampleTestDetail.getSamplingLocation().contains("兴业")){
                CitySampleTestDetail.setSamplingLocation("兴业县");
            }

            // 南宁市
            else if (CitySampleTestDetail.getSamplingLocation().contains("兴宁")) {
                CitySampleTestDetail.setSamplingLocation("兴宁区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("青秀")) {
                CitySampleTestDetail.setSamplingLocation("青秀区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("江南")) {
                CitySampleTestDetail.setSamplingLocation("江南区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("西乡塘")) {
                CitySampleTestDetail.setSamplingLocation("西乡塘区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("良庆")) {
                CitySampleTestDetail.setSamplingLocation("良庆区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("邕宁")) {
                CitySampleTestDetail.setSamplingLocation("邕宁区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("武鸣")) {
                CitySampleTestDetail.setSamplingLocation("武鸣区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("横州")) {
                CitySampleTestDetail.setSamplingLocation("横州市");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("隆安")) {
                CitySampleTestDetail.setSamplingLocation("隆安县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("马山")) {
                CitySampleTestDetail.setSamplingLocation("马山县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("上林")) {
                CitySampleTestDetail.setSamplingLocation("上林县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("宾阳")) {
                CitySampleTestDetail.setSamplingLocation("宾阳县");
            }


            // 柳州市
            else if (CitySampleTestDetail.getSamplingLocation().contains("城中")) {
                CitySampleTestDetail.setSamplingLocation("城中区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("鱼峰")) {
                CitySampleTestDetail.setSamplingLocation("鱼峰区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("柳南")) {
                CitySampleTestDetail.setSamplingLocation("柳南区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("柳北")) {
                CitySampleTestDetail.setSamplingLocation("柳北区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("柳江")) {
                CitySampleTestDetail.setSamplingLocation("柳江区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("柳城")) {
                CitySampleTestDetail.setSamplingLocation("柳城县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("鹿寨")) {
                CitySampleTestDetail.setSamplingLocation("鹿寨县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("融安")) {
                CitySampleTestDetail.setSamplingLocation("融安县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("融水")) {
                CitySampleTestDetail.setSamplingLocation("融水苗族自治县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("三江")) {
                CitySampleTestDetail.setSamplingLocation("三江侗族自治县");
            }

            // 桂林市
            else if (CitySampleTestDetail.getSamplingLocation().contains("秀峰")) {
                CitySampleTestDetail.setSamplingLocation("秀峰区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("叠彩")) {
                CitySampleTestDetail.setSamplingLocation("叠彩区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("象山")) {
                CitySampleTestDetail.setSamplingLocation("象山区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("七星")) {
                CitySampleTestDetail.setSamplingLocation("七星区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("雁山")) {
                CitySampleTestDetail.setSamplingLocation("雁山区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("临桂")) {
                CitySampleTestDetail.setSamplingLocation("临桂区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("荔浦")) {
                CitySampleTestDetail.setSamplingLocation("荔浦市");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("阳朔")) {
                CitySampleTestDetail.setSamplingLocation("阳朔县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("灵川")) {
                CitySampleTestDetail.setSamplingLocation("灵川县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("全州")) {
                CitySampleTestDetail.setSamplingLocation("全州县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("兴安")) {
                CitySampleTestDetail.setSamplingLocation("兴安县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("永福")) {
                CitySampleTestDetail.setSamplingLocation("永福县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("灌阳")) {
                CitySampleTestDetail.setSamplingLocation("灌阳县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("资源")) {
                CitySampleTestDetail.setSamplingLocation("资源县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("平乐")) {
                CitySampleTestDetail.setSamplingLocation("平乐县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("龙胜")) {
                CitySampleTestDetail.setSamplingLocation("龙胜各族自治县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("恭城")) {
                CitySampleTestDetail.setSamplingLocation("恭城瑶族自治县");
            }

            // 梧州市
            else if (CitySampleTestDetail.getSamplingLocation().contains("万秀")) {
                CitySampleTestDetail.setSamplingLocation("万秀区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("长洲")) {
                CitySampleTestDetail.setSamplingLocation("长洲区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("龙圩")) {
                CitySampleTestDetail.setSamplingLocation("龙圩区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("岑溪")) {
                CitySampleTestDetail.setSamplingLocation("岑溪市");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("苍梧")) {
                CitySampleTestDetail.setSamplingLocation("苍梧县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("藤县")) {
                CitySampleTestDetail.setSamplingLocation("藤县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("蒙山")) {
                CitySampleTestDetail.setSamplingLocation("蒙山县");
            }

            // 北海市
            else if (CitySampleTestDetail.getSamplingLocation().contains("海城")) {
                CitySampleTestDetail.setSamplingLocation("海城区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("银海")) {
                CitySampleTestDetail.setSamplingLocation("银海区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("铁山港")) {
                CitySampleTestDetail.setSamplingLocation("铁山港区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("合浦")) {
                CitySampleTestDetail.setSamplingLocation("合浦县");
            }

            // 崇左市
            else if (CitySampleTestDetail.getSamplingLocation().contains("江州")) {
                CitySampleTestDetail.setSamplingLocation("江州区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("凭祥")) {
                CitySampleTestDetail.setSamplingLocation("凭祥市");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("扶绥")) {
                CitySampleTestDetail.setSamplingLocation("扶绥县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("宁明")) {
                CitySampleTestDetail.setSamplingLocation("宁明县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("龙州")) {
                CitySampleTestDetail.setSamplingLocation("龙州县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("大新")) {
                CitySampleTestDetail.setSamplingLocation("大新县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("天等")) {
                CitySampleTestDetail.setSamplingLocation("天等县");
            }

            // 来宾市
            else if (CitySampleTestDetail.getSamplingLocation().contains("兴宾")) {
                CitySampleTestDetail.setSamplingLocation("兴宾区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("合山")) {
                CitySampleTestDetail.setSamplingLocation("合山市");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("忻城")) {
                CitySampleTestDetail.setSamplingLocation("忻城县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("象州")) {
                CitySampleTestDetail.setSamplingLocation("象州县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("武宣")) {
                CitySampleTestDetail.setSamplingLocation("武宣县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("金秀")) {
                CitySampleTestDetail.setSamplingLocation("金秀瑶族自治县");
            }

            // 贺州市
            else if (CitySampleTestDetail.getSamplingLocation().contains("八步")) {
                CitySampleTestDetail.setSamplingLocation("八步区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("平桂")) {
                CitySampleTestDetail.setSamplingLocation("平桂区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("昭平")) {
                CitySampleTestDetail.setSamplingLocation("昭平县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("钟山")) {
                CitySampleTestDetail.setSamplingLocation("钟山县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("富川")) {
                CitySampleTestDetail.setSamplingLocation("富川瑶族自治县");
            }

            // 百色市
            else if (CitySampleTestDetail.getSamplingLocation().contains("右江")) {
                CitySampleTestDetail.setSamplingLocation("右江区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("田阳")) {
                CitySampleTestDetail.setSamplingLocation("田阳区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("靖西")) {
                CitySampleTestDetail.setSamplingLocation("靖西市");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("平果")) {
                CitySampleTestDetail.setSamplingLocation("平果市");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("田东")) {
                CitySampleTestDetail.setSamplingLocation("田东县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("德保")) {
                CitySampleTestDetail.setSamplingLocation("德保县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("那坡")) {
                CitySampleTestDetail.setSamplingLocation("那坡县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("凌云")) {
                CitySampleTestDetail.setSamplingLocation("凌云县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("乐业")) {
                CitySampleTestDetail.setSamplingLocation("乐业县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("田林")) {
                CitySampleTestDetail.setSamplingLocation("田林县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("西林")) {
                CitySampleTestDetail.setSamplingLocation("西林县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("隆林")) {
                CitySampleTestDetail.setSamplingLocation("隆林各族自治县");
            }

            // 河池市
            else if (CitySampleTestDetail.getSamplingLocation().contains("金城江")) {
                CitySampleTestDetail.setSamplingLocation("金城江区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("宜州")) {
                CitySampleTestDetail.setSamplingLocation("宜州区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("南丹")) {
                CitySampleTestDetail.setSamplingLocation("南丹县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("天峨")) {
                CitySampleTestDetail.setSamplingLocation("天峨县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("凤山")) {
                CitySampleTestDetail.setSamplingLocation("凤山县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("东兰")) {
                CitySampleTestDetail.setSamplingLocation("东兰县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("罗城")) {
                CitySampleTestDetail.setSamplingLocation("罗城仫佬族自治县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("环江")) {
                CitySampleTestDetail.setSamplingLocation("环江毛南族自治县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("巴马")) {
                CitySampleTestDetail.setSamplingLocation("巴马瑶族自治县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("都安")) {
                CitySampleTestDetail.setSamplingLocation("都安瑶族自治县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("大化")) {
                CitySampleTestDetail.setSamplingLocation("大化瑶族自治县");
            }

            // 钦州市
            else if (CitySampleTestDetail.getSamplingLocation().contains("钦南")) {
                CitySampleTestDetail.setSamplingLocation("钦南区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("钦北")) {
                CitySampleTestDetail.setSamplingLocation("钦北区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("灵山")) {
                CitySampleTestDetail.setSamplingLocation("灵山县");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("浦北")) {
                CitySampleTestDetail.setSamplingLocation("浦北县");
            }

            // 防城港市
            else if (CitySampleTestDetail.getSamplingLocation().contains("港口")) {
                CitySampleTestDetail.setSamplingLocation("港口区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("防城")) {
                CitySampleTestDetail.setSamplingLocation("防城区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("东兴")) {
                CitySampleTestDetail.setSamplingLocation("东兴市");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("上思")) {
                CitySampleTestDetail.setSamplingLocation("上思县");
            }

            // 贵港市
            else if (CitySampleTestDetail.getSamplingLocation().contains("港北")) {
                CitySampleTestDetail.setSamplingLocation("港北区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("港南")) {
                CitySampleTestDetail.setSamplingLocation("港南区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("覃塘")) {
                CitySampleTestDetail.setSamplingLocation("覃塘区");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("桂平")) {
                CitySampleTestDetail.setSamplingLocation("桂平市");
            }
            else if (CitySampleTestDetail.getSamplingLocation().contains("平南")) {
                CitySampleTestDetail.setSamplingLocation("平南县");
            }
            else
            {CitySampleTestDetail.setSamplingLocation("运输车");}















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
