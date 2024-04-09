package com.ruoyi.out.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.github.pagehelper.PageHelper;
import com.ruoyi.detection.domain.agriOut2CitySampleTestDetails;
import com.ruoyi.out.domain.out2DxDetectRecords;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.out.domain.outDxDetectRecords;
import com.ruoyi.out.service.IoutDxDetectRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 定性监测结果汇总Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outDxDetectRecords")
public class outDxDetectRecordsController extends BaseController
{
    @Autowired
    private IoutDxDetectRecordsService outDxDetectRecordsService;

    /**
     * 查询定性监测结果汇总列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(outDxDetectRecords outDxDetectRecords)
//    {
//        startPage();
//        List<outDxDetectRecords> list = outDxDetectRecordsService.selectoutDxDetectRecordsList(outDxDetectRecords);
//        return getDataTable(list);
//    }


    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outDxDetectRecords outDxDetectRecords,Integer year,Integer season )
    {
        int month1 = 0;
        int month2 = 0;
        int month3 = 0;
        if(season==1){
            month1 = 1;month2 = 2;month3 = 3;
        }else if(season==2){
            month1 = 4;month2 = 5;month3 = 6;
        }else if(season==3){
            month1 = 7;month2 = 8;month3 = 9;
        }else if(season==4){
            month1 = 10;month2 = 11;month3 = 12;
        }
        List<Map<String, out2DxDetectRecords>> statistics = outDxDetectRecordsService.getQuarterlyStatistics(year, month1, month2, month3);
//        System.out.println(statistics+"qwer");
        return getDataTable(statistics);
    }

    /**
     * 导出定性监测结果汇总列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:export')")
//    @Log(title = "定性监测结果汇总", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, outDxDetectRecords outDxDetectRecords)
//    {
//        List<outDxDetectRecords> list = outDxDetectRecordsService.selectoutDxDetectRecordsList(outDxDetectRecords);
//        ExcelUtil<outDxDetectRecords> util = new ExcelUtil<outDxDetectRecords>(outDxDetectRecords.class);
//        util.exportExcel(response, list, "定性监测结果汇总数据");
//    }


    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:export')")
    @Log(title = "定性监测结果汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outDxDetectRecords outDxDetectRecords,Integer year,Integer season) throws IOException {

        int month1 = 0;
        int month2 = 0;
        int month3 = 0;
        if(season==1){
            month1 = 1;month2 = 2;month3 = 3;
        }else if(season==2){
            month1 = 4;month2 = 5;month3 = 6;
        }else if(season==3){
            month1 = 7;month2 = 8;month3 = 9;
        }else if(season==4){
            month1 = 10;month2 = 11;month3 = 12;
        }

        List<Map<String, out2DxDetectRecords>> statistics = outDxDetectRecordsService.getQuarterlyStatistics(year, month1, month2, month3);



//        System.out.println(statistics+"导出");
        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/dxjc.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("month1", month1);
        map.put("month2", month2);
        map.put("month3", month3);
        map.put("season", season);

        map.put("maplist", statistics);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }



    /**
     * 获取定性监测结果汇总详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:query')")
    @GetMapping(value = "/{recordDxId}")
    public AjaxResult getInfo(@PathVariable("recordDxId") Long recordDxId)
    {
        return success(outDxDetectRecordsService.selectoutDxDetectRecordsByRecordDxId(recordDxId));
    }

    /**
     * 新增定性监测结果汇总
     */
    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:add')")
    @Log(title = "定性监测结果汇总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outDxDetectRecords outDxDetectRecords)
    {
        return toAjax(outDxDetectRecordsService.insertoutDxDetectRecords(outDxDetectRecords));
    }

    /**
     * 修改定性监测结果汇总
     */
    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:edit')")
    @Log(title = "定性监测结果汇总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outDxDetectRecords outDxDetectRecords)
    {
        return toAjax(outDxDetectRecordsService.updateoutDxDetectRecords(outDxDetectRecords));
    }

    /**
     * 删除定性监测结果汇总
     */
    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:remove')")
    @Log(title = "定性监测结果汇总", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordDxIds}")
    public AjaxResult remove(@PathVariable Long[] recordDxIds)
    {
        return toAjax(outDxDetectRecordsService.deleteoutDxDetectRecordsByRecordDxIds(recordDxIds));
    }

    @Log(title = "定性检测导入", businessType = BusinessType.IMPORT)
//    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //表标题占用的行数
        params.setHeadRows(2);  //表头所占用的行数
        List<out2DxDetectRecords> outDxDetectRecords = null;
        try {
            outDxDetectRecords = ExcelImportUtil.importExcel(file.getInputStream(), out2DxDetectRecords.class, params);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        System.out.println(("*****解析出来的数据:" + JSON.toJSONString(agriOut2CitySampleTestDetailsList)));
        String operName = getUsername();
        String message = outDxDetectRecordsService.importDxDetectRecordsService(file,outDxDetectRecords, updateSupport, operName);
        return success(message);
    }

}
