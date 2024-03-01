package com.ruoyi.out.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.dlDetRecordSampleRes;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.out.domain.outHighRiskVarietyDet;
import com.ruoyi.out.service.IoutHighRiskVarietyDetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 高风险品种样品检出情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outHighRiskVarietyDet")
public class outHighRiskVarietyDetController extends BaseController
{
    @Autowired
    private IoutHighRiskVarietyDetService outHighRiskVarietyDetService;

    /**
     * 查询高风险品种样品检出情况列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(outHighRiskVarietyDet outHighRiskVarietyDet)
//    {
//        startPage();
//        List<outHighRiskVarietyDet> list = outHighRiskVarietyDetService.selectoutHighRiskVarietyDetList(outHighRiskVarietyDet);
//        return getDataTable(list);
//    }

    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
//        Map<String, List<outHighRiskVarietyDet>> stringHighRiskVarietyDetMap = outHighRiskVarietyDetService.selectOutHighRiskVarietyDetList(agriCitySampleTestDetails);
        List<outHighRiskVarietyDet> outHighRiskVarietyDets = outHighRiskVarietyDetService.selectOutHighRiskVarietyDetList(agriCitySampleTestDetails);

        return getDataTable(outHighRiskVarietyDets);
    }





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




    /**
     * 导出高风险品种样品检出情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:export')")
    @Log(title = "高风险品种样品检出情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outHighRiskVarietyDet outHighRiskVarietyDet,agriCitySampleTestDetails agriCitySampleTestDetails)
    {
//        List<outHighRiskVarietyDet> list = outHighRiskVarietyDetService.selectoutHighRiskVarietyDetList(outHighRiskVarietyDet);
//        ExcelUtil<outHighRiskVarietyDet> util = new ExcelUtil<outHighRiskVarietyDet>(outHighRiskVarietyDet.class);
//        util.exportExcel(response, list, "高风险品种样品检出情况数据");

//        List<outHighRiskVarietyDet> res = new ArrayList<outHighRiskVarietyDet>();
        List<outHighRiskVarietyDet> outHighRiskVarietyDets = outHighRiskVarietyDetService.selectOutHighRiskVarietyDetList(agriCitySampleTestDetails);
        TemplateExportParams params = new TemplateExportParams("ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/high1.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("maplist", outHighRiskVarietyDets);
        try {
            // 生成 Excel 数据
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
            mergeCells(sheet);

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

    /**
     * 获取高风险品种样品检出情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:query')")
    @GetMapping(value = "/{outHighRiskVarietyDetId}")
    public AjaxResult getInfo(@PathVariable("outHighRiskVarietyDetId") Long outHighRiskVarietyDetId)
    {
        return success(outHighRiskVarietyDetService.selectoutHighRiskVarietyDetByOutHighRiskVarietyDetId(outHighRiskVarietyDetId));
    }

    /**
     * 新增高风险品种样品检出情况
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:add')")
    @Log(title = "高风险品种样品检出情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outHighRiskVarietyDet outHighRiskVarietyDet)
    {
        return toAjax(outHighRiskVarietyDetService.insertoutHighRiskVarietyDet(outHighRiskVarietyDet));
    }

    /**
     * 修改高风险品种样品检出情况
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:edit')")
    @Log(title = "高风险品种样品检出情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outHighRiskVarietyDet outHighRiskVarietyDet)
    {
        return toAjax(outHighRiskVarietyDetService.updateoutHighRiskVarietyDet(outHighRiskVarietyDet));
    }

    /**
     * 删除高风险品种样品检出情况
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:remove')")
    @Log(title = "高风险品种样品检出情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{outHighRiskVarietyDetIds}")
    public AjaxResult remove(@PathVariable Long[] outHighRiskVarietyDetIds)
    {
        return toAjax(outHighRiskVarietyDetService.deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetIds(outHighRiskVarietyDetIds));
    }
}
