package com.ruoyi.out.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
     */
    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outDlDetectRecords outDlDetectRecords)
    {
        startPage();
        List<outDlDetectRecords> list = outDlDetectRecordsService.selectoutDlDetectRecordsList(outDlDetectRecords);
        return getDataTable(list);
    }

    /**
     * 导出定量监测结果汇总列表
     */
    @PreAuthorize("@ss.hasPermi('out:outDlDetectRecords:export')")
    @Log(title = "定量监测结果汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outDlDetectRecords outDlDetectRecords)
    {
        List<outDlDetectRecords> list = outDlDetectRecordsService.selectoutDlDetectRecordsList(outDlDetectRecords);
        ExcelUtil<outDlDetectRecords> util = new ExcelUtil<outDlDetectRecords>(outDlDetectRecords.class);
        util.exportExcel(response, list, "定量监测结果汇总数据");
    }

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
