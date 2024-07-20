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
import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.vo.OutPesDetRecordsVo;
import com.ruoyi.out.service.IOutPesDetRecordsService;
import com.ruoyi.out.service.IoutFruPesDetRecordsService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 水果非禁止使用农药检出及超标情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outFruNoBanPesDetRecords")
public class outFruNoBanPesDetRecordsController extends BaseController
{
    @Autowired
    private IoutFruPesDetRecordsService outFruPesDetRecordsService;
    @Autowired
    private IOutPesDetRecordsService OutPesDetRecordsService;

    /**
     * 查询水果非禁止使用农药检出及超标情况列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
//    {
//        startPage();
//        StringBuilder feedBackMsg =new StringBuilder();//用于返回一些信息
//        List<outReturnType> list = outFruPesDetRecordsService.selectoutFruPesDetRecordsList(agriCitySampleTestDetails,"非禁用",feedBackMsg);
//        TableDataInfo result = getDataTable(list);
//        result.setMsg(feedBackMsg.toString());
//        return result;
//    }


    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        StringBuilder feedBackMsg =new StringBuilder();//用于返回一些信息
        List<OutPesDetRecordsVo> list = OutPesDetRecordsService.selectOutPesDetRecords(agriCitySampleTestDetails,feedBackMsg,"水果","1");
        TableDataInfo result = getDataTable(list);
        result.setMsg(feedBackMsg.toString());
        return result;
    }
    /**
     * 导出水果非禁止使用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:export')")
    @Log(title = "水果非禁止使用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        StringBuilder feedBackMsg =new StringBuilder();//用于返回一些信息
//        List<outReturnType> list = outFruPesDetRecordsService.selectoutFruPesDetRecordsList(agriCitySampleTestDetails,"非禁用",feedBackMsg);
        List<OutPesDetRecordsVo> list = OutPesDetRecordsService.selectOutPesDetRecords(agriCitySampleTestDetails,feedBackMsg,"水果","1");

        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outFruBanPesDetRecords.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "4.水果上非禁止使用农药检出及超标情况");
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











}
