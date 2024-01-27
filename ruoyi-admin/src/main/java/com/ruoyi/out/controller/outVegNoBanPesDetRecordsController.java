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
import com.ruoyi.out.domain.outVegNoBanPesDetRecords;
import com.ruoyi.out.service.IoutVegNoBanPesDetRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蔬菜上非禁止使用农药检出及超标情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outVegNoBanPesDetRecords")
public class outVegNoBanPesDetRecordsController extends BaseController
{
    @Autowired
    private IoutVegNoBanPesDetRecordsService outVegNoBanPesDetRecordsService;

    /**
     * 查询蔬菜上非禁止使用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegNoBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        startPage();
        List<outVegNoBanPesDetRecords> list = outVegNoBanPesDetRecordsService.selectoutVegNoBanPesDetRecordsList(outVegNoBanPesDetRecords);
        return getDataTable(list);
    }

    /**
     * 导出蔬菜上非禁止使用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegNoBanPesDetRecords:export')")
    @Log(title = "蔬菜上非禁止使用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        List<outVegNoBanPesDetRecords> list = outVegNoBanPesDetRecordsService.selectoutVegNoBanPesDetRecordsList(outVegNoBanPesDetRecords);
        ExcelUtil<outVegNoBanPesDetRecords> util = new ExcelUtil<outVegNoBanPesDetRecords>(outVegNoBanPesDetRecords.class);
        util.exportExcel(response, list, "蔬菜上非禁止使用农药检出及超标情况数据");
    }

    /**
     * 获取蔬菜上非禁止使用农药检出及超标情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outVegNoBanPesDetRecords:query')")
    @GetMapping(value = "/{vegNoBanPesDetRecordsId}")
    public AjaxResult getInfo(@PathVariable("vegNoBanPesDetRecordsId") Long vegNoBanPesDetRecordsId)
    {
        return success(outVegNoBanPesDetRecordsService.selectoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsId(vegNoBanPesDetRecordsId));
    }

    /**
     * 新增蔬菜上非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outVegNoBanPesDetRecords:add')")
    @Log(title = "蔬菜上非禁止使用农药检出及超标情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        return toAjax(outVegNoBanPesDetRecordsService.insertoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords));
    }

    /**
     * 修改蔬菜上非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outVegNoBanPesDetRecords:edit')")
    @Log(title = "蔬菜上非禁止使用农药检出及超标情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outVegNoBanPesDetRecords outVegNoBanPesDetRecords)
    {
        return toAjax(outVegNoBanPesDetRecordsService.updateoutVegNoBanPesDetRecords(outVegNoBanPesDetRecords));
    }

    /**
     * 删除蔬菜上非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outVegNoBanPesDetRecords:remove')")
    @Log(title = "蔬菜上非禁止使用农药检出及超标情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vegNoBanPesDetRecordsIds}")
    public AjaxResult remove(@PathVariable Long[] vegNoBanPesDetRecordsIds)
    {
        return toAjax(outVegNoBanPesDetRecordsService.deleteoutVegNoBanPesDetRecordsByVegNoBanPesDetRecordsIds(vegNoBanPesDetRecordsIds));
    }
}
