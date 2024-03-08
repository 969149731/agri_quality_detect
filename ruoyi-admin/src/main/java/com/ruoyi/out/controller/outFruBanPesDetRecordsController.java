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
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.out.domain.outHighRiskVarietyDet;
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
import com.ruoyi.out.domain.outFruBanPesDetRecords;
import com.ruoyi.out.service.IoutFruBanPesDetRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
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
    private IoutFruBanPesDetRecordsService outFruBanPesDetRecordsService;

    /**
     * 查询水果禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outFruBanPesDetRecords outFruBanPesDetRecords)
    {
        startPage();
        List<outFruBanPesDetRecords> list = outFruBanPesDetRecordsService.selectoutFruBanPesDetRecordsList(outFruBanPesDetRecords);
        return getDataTable(list);
    }

    /**
     * 导出水果禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruBanPesDetRecords:export')")
    @Log(title = "水果禁用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outFruBanPesDetRecords outFruBanPesDetRecords)
    {
//        List<outFruBanPesDetRecords> list = outFruBanPesDetRecordsService.selectoutFruBanPesDetRecordsList(outFruBanPesDetRecords);
//        ExcelUtil<outFruBanPesDetRecords> util = new ExcelUtil<outFruBanPesDetRecords>(outFruBanPesDetRecords.class);
//        util.exportExcel(response, list, "水果禁用农药检出及超标情况数据");
        outReturnType outReturnTypeRecords=new outReturnType();
        List<outReturnType> list = outFruBanPesDetRecordsService.selectoutFruBanPesDetRecordsList2(outReturnTypeRecords);
        TemplateExportParams params = new TemplateExportParams("ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/outFruBanPesDetRecords.xlsx");
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

    /**
     * 获取水果禁用农药检出及超标情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outFruBanPesDetRecords:query')")
    @GetMapping(value = "/{fruBanPesDetRecordsId}")
    public AjaxResult getInfo(@PathVariable("fruBanPesDetRecordsId") Long fruBanPesDetRecordsId)
    {
        return success(outFruBanPesDetRecordsService.selectoutFruBanPesDetRecordsByFruBanPesDetRecordsId(fruBanPesDetRecordsId));
    }

    /**
     * 新增水果禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruBanPesDetRecords:add')")
    @Log(title = "水果禁用农药检出及超标情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outFruBanPesDetRecords outFruBanPesDetRecords)
    {
        return toAjax(outFruBanPesDetRecordsService.insertoutFruBanPesDetRecords(outFruBanPesDetRecords));
    }

    /**
     * 修改水果禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruBanPesDetRecords:edit')")
    @Log(title = "水果禁用农药检出及超标情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outFruBanPesDetRecords outFruBanPesDetRecords)
    {
        return toAjax(outFruBanPesDetRecordsService.updateoutFruBanPesDetRecords(outFruBanPesDetRecords));
    }

    /**
     * 删除水果禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruBanPesDetRecords:remove')")
    @Log(title = "水果禁用农药检出及超标情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fruBanPesDetRecordsIds}")
    public AjaxResult remove(@PathVariable Long[] fruBanPesDetRecordsIds)
    {
        return toAjax(outFruBanPesDetRecordsService.deleteoutFruBanPesDetRecordsByFruBanPesDetRecordsIds(fruBanPesDetRecordsIds));
    }

    @PreAuthorize("@ss.hasPermi('out:outFruBanPesDetRecords:list')")
    @GetMapping("/listNew")
    public TableDataInfo listNew(outReturnType outReturnTypeRecords)
    {//水果禁用表新表的接口
        startPage();
        List<outReturnType> list = outFruBanPesDetRecordsService.selectoutFruBanPesDetRecordsList2(outReturnTypeRecords);
        return getDataTable(list);
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

