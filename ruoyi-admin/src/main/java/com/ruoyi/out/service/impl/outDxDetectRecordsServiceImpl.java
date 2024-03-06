package com.ruoyi.out.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.out.domain.out2DxDetectRecords;
import com.ruoyi.system.service.ISysConfigService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.out.mapper.outDxDetectRecordsMapper;
import com.ruoyi.out.domain.outDxDetectRecords;
import com.ruoyi.out.service.IoutDxDetectRecordsService;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 定性监测结果汇总Service业务层处理
 *
 * @author chenjie
 * @date 2024-01-27
 */
@Service
public class outDxDetectRecordsServiceImpl implements IoutDxDetectRecordsService {
    @Autowired
    private outDxDetectRecordsMapper outDxDetectRecordsMapper;
    @Autowired
    private ISysConfigService configService;

    /**
     * 查询定性监测结果汇总
     *
     * @param recordDxId 定性监测结果汇总主键
     * @return 定性监测结果汇总
     */
    @Override
    public outDxDetectRecords selectoutDxDetectRecordsByRecordDxId(Long recordDxId) {
        return outDxDetectRecordsMapper.selectoutDxDetectRecordsByRecordDxId(recordDxId);
    }

    /**
     * 查询定性监测结果汇总列表
     *
     * @param outDxDetectRecords 定性监测结果汇总
     * @return 定性监测结果汇总
     */
    @Override
    public List<outDxDetectRecords> selectoutDxDetectRecordsList(outDxDetectRecords outDxDetectRecords) {
        return outDxDetectRecordsMapper.selectoutDxDetectRecordsList(outDxDetectRecords);
    }

    /**
     * 新增定性监测结果汇总
     *
     * @param outDxDetectRecords 定性监测结果汇总
     * @return 结果
     */
    @Override
    public int insertoutDxDetectRecords(outDxDetectRecords outDxDetectRecords) {
        return outDxDetectRecordsMapper.insertoutDxDetectRecords(outDxDetectRecords);
    }

    /**
     * 修改定性监测结果汇总
     *
     * @param outDxDetectRecords 定性监测结果汇总
     * @return 结果
     */
    @Override
    public int updateoutDxDetectRecords(outDxDetectRecords outDxDetectRecords) {
        return outDxDetectRecordsMapper.updateoutDxDetectRecords(outDxDetectRecords);
    }

    /**
     * 批量删除定性监测结果汇总
     *
     * @param recordDxIds 需要删除的定性监测结果汇总主键
     * @return 结果
     */
    @Override
    public int deleteoutDxDetectRecordsByRecordDxIds(Long[] recordDxIds) {
        return outDxDetectRecordsMapper.deleteoutDxDetectRecordsByRecordDxIds(recordDxIds);
    }

    /**
     * 删除定性监测结果汇总信息
     *
     * @param recordDxId 定性监测结果汇总主键
     * @return 结果
     */
    @Override
    public int deleteoutDxDetectRecordsByRecordDxId(Long recordDxId) {
        return outDxDetectRecordsMapper.deleteoutDxDetectRecordsByRecordDxId(recordDxId);
    }

    @Override
    public String importDxDetectRecordsService(MultipartFile file, List<out2DxDetectRecords> outDxDetectRecords, boolean updateSupport, String operName) throws IOException {
        if (StringUtils.isNull(outDxDetectRecords) || outDxDetectRecords.size() == 0) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        String getYearcCellValueUse = null;
        String getMonth1cellValueUse = null;
        String getMonth2cellValueUse = null;
        String getMonth3cellValueUse = null;

        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);  // 假设数据在第一个工作表

            Row getYearRow = sheet.getRow(0);  //获取excel第一行的数据，也就是年份所以在的行
            Cell getYearcell = getYearRow.getCell(0);  // 获取年份所在的列
            String getYearcCellValue = getYearcell.getStringCellValue(); //获取年份所在的完整字符串
            getYearcCellValueUse = getYearcCellValue.substring(0, 4);

            Row getMonthRow = sheet.getRow(1);  //获取excel第二行的数据，也就是月份所以在的行
            Cell getMonth1cell = getMonthRow.getCell(2);  // 获取excel第二行第3列的数据  也就是月份1
            String getMonth1cellValue = getMonth1cell.getStringCellValue();
            getMonth1cellValueUse = getMonth1cellValue.substring(0, 1);

            Cell getMonth2cell = getMonthRow.getCell(5);  // 获取excel第二行第6列的数据  也就是月份2
            String getMonth2cellValue = getMonth2cell.getStringCellValue();
            getMonth2cellValueUse = getMonth2cellValue.substring(0, 1);


            Cell getMonth3cell = getMonthRow.getCell(8);  // 获取excel第二行第9列的数据  也就是月份3
            String getMonth3cellValue = getMonth3cell.getStringCellValue();
            getMonth3cellValueUse = getMonth3cellValue.substring(0, 1);
//            System.out.println(outDxDetectRecords+"qwerr");
//            System.out.println(getMonth1cellValue+getMonth2cellValue+getMonth3cellValue+"asd"+getYearcCellValueUse+getMonth1cellValueUse+getMonth2cellValueUse+getMonth3cellValueUse);
        } catch (Exception E) {
            if (workbook != null) {
                workbook.close();
            }
        } finally {
            IOUtils.closeQuietly(workbook);
        }

        for (out2DxDetectRecords recordsItem : outDxDetectRecords) {
            try {
                //写入第一个月份的数据
                out2DxDetectRecords out2DxDetectRecords1 = new out2DxDetectRecords();
                out2DxDetectRecords1.setSamplingYear(getYearcCellValueUse);
                out2DxDetectRecords1.setSamplingMonth(getMonth1cellValueUse);
                out2DxDetectRecords1.setDetectLocation(recordsItem.getDetectLocation());
                out2DxDetectRecords1.setSamplingCount(recordsItem.getMonth1SamplingCount());
                out2DxDetectRecords1.setQualifiedCount(recordsItem.getMonth1QualifiedCount());
                out2DxDetectRecords1.setPassRate(recordsItem.getMonth1PassRate());
                outDxDetectRecordsMapper.insertOutDxDetectRecords(out2DxDetectRecords1);

                //写入第二个月份的数据
                out2DxDetectRecords out2DxDetectRecords2 = new out2DxDetectRecords();
                out2DxDetectRecords2.setSamplingYear(getYearcCellValueUse);
                out2DxDetectRecords2.setSamplingMonth(getMonth2cellValueUse);
                out2DxDetectRecords2.setDetectLocation(recordsItem.getDetectLocation());
                out2DxDetectRecords2.setSamplingCount(recordsItem.getMonth2SamplingCount());
                out2DxDetectRecords2.setQualifiedCount(recordsItem.getMonth2QualifiedCount());
                out2DxDetectRecords2.setPassRate(recordsItem.getMonth2PassRate());
                outDxDetectRecordsMapper.insertOutDxDetectRecords(out2DxDetectRecords2);

                //写入第三个月份的数据
                out2DxDetectRecords out2DxDetectRecords3 = new out2DxDetectRecords();
                out2DxDetectRecords3.setSamplingYear(getYearcCellValueUse);
                out2DxDetectRecords3.setSamplingMonth(getMonth3cellValueUse);
                out2DxDetectRecords3.setDetectLocation(recordsItem.getDetectLocation());
                out2DxDetectRecords3.setSamplingCount(recordsItem.getMonth3SamplingCount());
                out2DxDetectRecords3.setQualifiedCount(recordsItem.getMonth3QualifiedCount());
                out2DxDetectRecords3.setPassRate(recordsItem.getMonth3PassRate());
                outDxDetectRecordsMapper.insertOutDxDetectRecords(out2DxDetectRecords3);


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
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

//    @Override
//    public List<Map<String, out2DxDetectRecords>> getQuarterlyStatistics(Integer  year, Integer  month1, Integer  month2, Integer  month3) {
//        List<Map<String, out2DxDetectRecords>> statistics = outDxDetectRecordsMapper.getQuarterlyStatistics(year, month1, month2, month3);
//        return statistics;
//    }

    @Override
    public List<Map<String, out2DxDetectRecords>> getQuarterlyStatistics(Integer year, Integer month1, Integer month2, Integer month3) {
        List<Map<String, out2DxDetectRecords>> statistics = outDxDetectRecordsMapper.getQuarterlyStatistics(year, month1, month2, month3);


        return statistics;
    }

}
