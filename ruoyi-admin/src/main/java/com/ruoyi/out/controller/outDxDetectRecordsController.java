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
import com.ruoyi.out.domain.outDxDetectRecords;
import com.ruoyi.out.service.IoutDxDetectRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outDxDetectRecords outDxDetectRecords)
    {
        startPage();
        List<outDxDetectRecords> list = outDxDetectRecordsService.selectoutDxDetectRecordsList(outDxDetectRecords);
        return getDataTable(list);
    }

    /**
     * 导出定性监测结果汇总列表
     */
    @PreAuthorize("@ss.hasPermi('out:outDxDetectRecords:export')")
    @Log(title = "定性监测结果汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outDxDetectRecords outDxDetectRecords)
    {
        List<outDxDetectRecords> list = outDxDetectRecordsService.selectoutDxDetectRecordsList(outDxDetectRecords);
        ExcelUtil<outDxDetectRecords> util = new ExcelUtil<outDxDetectRecords>(outDxDetectRecords.class);
        util.exportExcel(response, list, "定性监测结果汇总数据");
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
}
