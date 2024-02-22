package com.ruoyi.out.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.dlMapToListResult;
import com.ruoyi.out.domain.dlDetRecordSampleRes;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.ruoyi.out.domain.outDlDetectRecords;
import com.ruoyi.out.service.IoutDlDetectRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 定量监测结果汇总Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outDlDetectRecords")
public class outDlDetectRecordsController extends BaseController
{
    @Autowired
    private IoutDlDetectRecordsService outDlDetectRecordsService;

    /**
     * 查询定量监测结果汇总列表
     * 这边注释掉的代码是原来的写法
     */
//    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:list')")
//    @GetMapping("/list1")
//    public TableDataInfo list1(outDlDetectRecords outDlDetectRecords)
//    {
//        startPage();
//        List<outDlDetectRecords> list = outDlDetectRecordsService.selectoutDlDetectRecordsList(outDlDetectRecords);
//        System.out.println("后端放回的数据:/n"+getDataTable(list));
//        return getDataTable(list);
//    }


    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails) throws ParseException {
        Map<String, List<dlDetRecordSampleRes>> stringSampleResMap = outDlDetectRecordsService.selectOutDlDetectRecordsList(agriCitySampleTestDetails);
        List<dlMapToListResult> resultList = new ArrayList<>();
        for (Map.Entry<String, List<dlDetRecordSampleRes>> entry : stringSampleResMap.entrySet()) {
            for (dlDetRecordSampleRes sampleRes : entry.getValue()) {
                resultList.add(new dlMapToListResult(entry.getKey(), sampleRes));
            }
        }
        List<outDlDetectRecords> formResultList = new ArrayList<>();
        for (dlMapToListResult result : resultList) {
            outDlDetectRecords outDlDetectRecords=new outDlDetectRecords();
            outDlDetectRecords.setSamplingLocation(result.getSamplingLocation());
            outDlDetectRecords.setVegSamplingCount((long) result.getSampleRes().getVegSamplingCount());
            outDlDetectRecords.setVegQualifiedCount((long) result.getSampleRes().getVegPassCount());
            outDlDetectRecords.setVegPassRate(result.getSampleRes().getVegPassRate()*100);
            outDlDetectRecords.setFruSamplingCount((long)result.getSampleRes().getFruSamplingCount() );
            outDlDetectRecords.setFruQualifiedCount((long)result.getSampleRes().getFruPassCount());
            outDlDetectRecords.setFruPassRate(result.getSampleRes().getFruPassRate()*100);
            outDlDetectRecords.setAllSamplingCount((long)result.getSampleRes().getAllSamplingCount());
            outDlDetectRecords.setAllQualifiedCount((long)result.getSampleRes().getAllPassCount());
            outDlDetectRecords.setAllPassRate(result.getSampleRes().getAllPassRate()*100);
            formResultList.add(outDlDetectRecords);
        }
        return getDataTable(formResultList);
    }



    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:export')")
    @Log(title = "定量监测结果汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriCitySampleTestDetails agriCitySampleTestDetails) throws ParseException, IOException {
        Map<String, List<dlDetRecordSampleRes>> stringSampleResMap = outDlDetectRecordsService.selectOutDlDetectRecordsList(agriCitySampleTestDetails);
        List<dlMapToListResult> resultList = new ArrayList<>();
        for (Map.Entry<String, List<dlDetRecordSampleRes>> entry : stringSampleResMap.entrySet()) {
            for (dlDetRecordSampleRes sampleRes : entry.getValue()) {
                resultList.add(new dlMapToListResult(entry.getKey(), sampleRes));
            }
        }
        List<outDlDetectRecords> formResultList = new ArrayList<>();
        //这个属性在导出的excel中是序号，从1开始
        Long recordDlId =1L;
        for (dlMapToListResult result : resultList) {
            outDlDetectRecords outDlDetectRecords=new outDlDetectRecords();
            outDlDetectRecords.setRecordDlId(recordDlId++);
            outDlDetectRecords.setSamplingLocation(result.getSamplingLocation());
            outDlDetectRecords.setVegSamplingCount((long) result.getSampleRes().getVegSamplingCount());
            outDlDetectRecords.setVegQualifiedCount((long) result.getSampleRes().getVegPassCount());
            outDlDetectRecords.setVegPassRate(result.getSampleRes().getVegPassRate()*100);
            outDlDetectRecords.setFruSamplingCount((long)result.getSampleRes().getFruSamplingCount() );
            outDlDetectRecords.setFruQualifiedCount((long)result.getSampleRes().getFruPassCount());
            outDlDetectRecords.setFruPassRate(result.getSampleRes().getFruPassRate()*100);
            outDlDetectRecords.setAllSamplingCount((long)result.getSampleRes().getAllSamplingCount());
            outDlDetectRecords.setAllQualifiedCount((long)result.getSampleRes().getAllPassCount());
            outDlDetectRecords.setAllPassRate(result.getSampleRes().getAllPassRate()*100);
            formResultList.add(outDlDetectRecords);
        }
//        ExcelUtil<outDlDetectRecords> util = new ExcelUtil<outDlDetectRecords>(outDlDetectRecords.class);
//        util.exportExcel(response, formResultList, "定量监测结果汇总数据");
        TemplateExportParams params = new TemplateExportParams("ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/outDlDetectRecorsExcelTemplate.xlsx");
        Map<String, Object> map = new HashMap<>();
//        map.put("date", System.currentTimeMillis());
        map.put("maplist", formResultList);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 导出定量监测结果汇总列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:export')")
//    @Log(title = "定量监测结果汇总", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, outDlDetectRecords outDlDetectRecords)
//    {
//        List<outDlDetectRecords> list = outDlDetectRecordsService.selectoutDlDetectRecordsList(outDlDetectRecords);
//        ExcelUtil<outDlDetectRecords> util = new ExcelUtil<outDlDetectRecords>(outDlDetectRecords.class);
//        util.exportExcel(response, list, "定量监测结果汇总数据");
//    }

    /**
     * 获取定量监测结果汇总详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:query')")
    @GetMapping(value = "/{recordDlId}")
    public AjaxResult getInfo(@PathVariable("recordDlId") Long recordDlId)
    {
        return success(outDlDetectRecordsService.selectoutDlDetectRecordsByRecordDlId(recordDlId));
    }

    /**
     * 新增定量监测结果汇总
     */
    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:add')")
    @Log(title = "定量监测结果汇总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outDlDetectRecords outDlDetectRecords)
    {
        return toAjax(outDlDetectRecordsService.insertoutDlDetectRecords(outDlDetectRecords));
    }

    /**
     * 修改定量监测结果汇总
     */
    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:edit')")
    @Log(title = "定量监测结果汇总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outDlDetectRecords outDlDetectRecords)
    {
        return toAjax(outDlDetectRecordsService.updateoutDlDetectRecords(outDlDetectRecords));
    }

    /**
     * 删除定量监测结果汇总
     */
    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:remove')")
    @Log(title = "定量监测结果汇总", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordDlIds}")
    public AjaxResult remove(@PathVariable Long[] recordDlIds)
    {
        return toAjax(outDlDetectRecordsService.deleteoutDlDetectRecordsByRecordDlIds(recordDlIds));
    }
}
