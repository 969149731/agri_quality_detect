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
import com.ruoyi.out.domain.outSampleQuality;
import com.ruoyi.out.service.IoutSampleQualityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 各抽样环节合格率情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outSampleQuality")
public class outSampleQualityController extends BaseController
{
    @Autowired
    private IoutSampleQualityService outSampleQualityService;

    /**
     * 查询各抽样环节合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleQuality:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        startPage();
        StringBuilder feedBackMsg =new StringBuilder();
        List<outSampleQuality> list = outSampleQualityService.selectoutSampleQualityList(agriCitySampleTestDetails,feedBackMsg);
        TableDataInfo result = getDataTable(list);
        if (feedBackMsg!=null)
            result.setMsg(feedBackMsg.toString());
        return result;
    }

    /**
     * 导出各抽样环节合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleQuality:export')")
    @Log(title = "各抽样环节合格率情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        StringBuilder feedBack =new StringBuilder();
        List<outSampleQuality> list = outSampleQualityService.selectoutSampleQualityList(agriCitySampleTestDetails,feedBack);
        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outSampleQuality.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "6.各抽样环节合格率情况");
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

    //工具方法合并表格
    private static void mergeCells(Sheet sheet) {
        //生产基地、批发市场、运输车、合计
        CellRangeAddress range1 = new CellRangeAddress(3, 3, 0, 1);
        CellRangeAddress range2 = new CellRangeAddress(10, 10, 0, 1);
        CellRangeAddress range3 = new CellRangeAddress(11, 11, 0, 1);
        CellRangeAddress range4 = new CellRangeAddress(12, 12, 0, 1);

        //其中
        CellRangeAddress range5 = new CellRangeAddress(4, 9, 0, 0);
        sheet.addMergedRegion(range1);
        sheet.addMergedRegion(range2);
        sheet.addMergedRegion(range3);
        sheet.addMergedRegion(range4);
        sheet.addMergedRegion(range5);
    }
}
