package com.ruoyi.out.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.out.service.IoutFruPesDetRecordsService;
import com.ruoyi.common.core.page.TableDataInfo;

import com.ruoyi.out.domain.outReturnType;

/**
 * 水果禁用农药检出及超标情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outFruBanPesDetRecords")
public class outFruBanPesDetRecordsController extends BaseController
{
    @Autowired
    private IoutFruPesDetRecordsService outFruBanPesDetRecordsService;

    /**
     * 查询水果禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails, Integer year, Integer season)
    {
        startPage();
//        int month1 = 0;
//        int month2 = 0;
//        int month3 = 0;
//        if(season==1){
//            month1 = 1;month2 = 2;month3 = 3;
//        }else if(season==2){
//            month1 = 4;month2 = 5;month3 = 6;
//        }else if(season==3){
//            month1 = 7;month2 = 8;month3 = 9;
//        }else if(season==4){
//            month1 = 10;month2 = 11;month3 = 12;
//        }
//        Date startDate=new Date(2022,month1,1);
//        Date endDate=new Date(2022,month3,0);//获取月份最后一天
        List<outReturnType> list = outFruBanPesDetRecordsService.selectoutFruBPesDetRecordsList(agriCitySampleTestDetails,"禁用");
        return getDataTable(list);
    }

    /**
     * 导出水果禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruBanPesDetRecords:export')")
    @Log(title = "水果禁用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response,agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        List<outReturnType> list = outFruBanPesDetRecordsService.selectoutFruBPesDetRecordsList(agriCitySampleTestDetails,"禁用");

        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outFruBanPesDetRecords.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "3.水果禁用农药检出及超标情况表");
        map.put("maplist", list);
        try {
            // 生成 Excel 数据
            params.setColForEach(true);
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);

            // 将 Workbook 写入 ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            // 从 ByteArrayOutputStream 创建 ByteArrayInputStream
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            // 重新读取 Excel 数据
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // 获取单元格内容，并将相同内容的单元格合并
            //mergeCells(sheet);

            // 设置响应头
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("out.xlsx", "UTF-8"));

            // 将修改后的 Excel 数据写入响应输出流
            workbook.write(response.getOutputStream());

            // 最后关闭相关的流和 Workbook
            workbook.close();
            outputStream.close();
            inputStream.close();

            System.out.println("Excel 导出并合并完成。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //工具方法
    private static void mergeCells(Sheet sheet) {
//        int lastNum = sheet.getLastRowNum();
//        int rowIndex = 3;
        // 遍历每一行
        int rowStartA = 3;
        int endRowA = rowStartA;
        int rowStartB = 3;
        int endRowB = rowStartB;
        for (int rowIndex = 4; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                continue; // 跳过空行
            }

            Cell cellA = row.getCell(0);
            Cell cellB = row.getCell(1);
            String valueA = cellA.getStringCellValue();
            String valueB = cellB.getStringCellValue();

            // 从当前行的上一行
            Row preRow = sheet.getRow(rowIndex-1);
            if (preRow == null) {
                continue; // 跳过空行
            }

            Cell preCellA = preRow.getCell(0);
            Cell preCellB = preRow.getCell(1);
            String preValueA = preCellA.getStringCellValue();
            String preValueB = preCellB.getStringCellValue();

            if (valueA.equals(preValueA) ) {
                endRowA++;
            } else {

                if(rowStartA != endRowA){
                    System.out.println(rowStartA+"A合并"+endRowA);
                    CellRangeAddress range = new CellRangeAddress(rowStartA, endRowA, 0, 0);
                    sheet.addMergedRegion(range);
                }
                rowStartA = rowIndex;
                endRowA = rowStartA;
            }

            if (valueB.equals(preValueB) ) {
                endRowB++;
            } else {
                if(rowStartB != endRowB){
                    System.out.println(rowStartB+"B合并"+endRowB);
                    CellRangeAddress range = new CellRangeAddress(rowStartB, endRowB, 1, 1);
                    sheet.addMergedRegion(range);
                }
                rowStartB = rowIndex;
                endRowB = rowStartB;
            }


        }

        if(rowStartA != endRowA){
            CellRangeAddress range = new CellRangeAddress(rowStartA, endRowA, 0, 0);
            sheet.addMergedRegion(range);
        }
        if(rowStartB != endRowB){
            CellRangeAddress range = new CellRangeAddress(rowStartB, endRowB, 1, 1);
            sheet.addMergedRegion(range);
        }
    }
}

