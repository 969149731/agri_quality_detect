package com.ruoyi.out.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.out.domain.outReturnType;
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
import com.ruoyi.out.domain.outVegBanPesDetRecords;
import com.ruoyi.out.service.IoutVegBanPesDetRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蔬菜禁用农药检出及超标情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outVegBanPesDetRecords")
public class outVegBanPesDetRecordsController extends BaseController
{
    @Autowired
    private IoutVegBanPesDetRecordsService outVegBanPesDetRecordsService;

    /**
     * 查询蔬菜禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        startPage();
        List<outVegBanPesDetRecords> list = outVegBanPesDetRecordsService.selectoutVegBanPesDetRecordsList(outVegBanPesDetRecords);
        return getDataTable(list);
    }

    /**
     * 导出蔬菜禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:export')")
    @Log(title = "蔬菜禁用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        List<outVegBanPesDetRecords> list = outVegBanPesDetRecordsService.selectoutVegBanPesDetRecordsList(outVegBanPesDetRecords);
        ExcelUtil<outVegBanPesDetRecords> util = new ExcelUtil<outVegBanPesDetRecords>(outVegBanPesDetRecords.class);
        util.exportExcel(response, list, "蔬菜禁用农药检出及超标情况数据");
    }

    /**
     * 获取蔬菜禁用农药检出及超标情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:query')")
    @GetMapping(value = "/{vegBanPesDetRecordsId}")
    public AjaxResult getInfo(@PathVariable("vegBanPesDetRecordsId") Long vegBanPesDetRecordsId)
    {
        return success(outVegBanPesDetRecordsService.selectoutVegBanPesDetRecordsByVegBanPesDetRecordsId(vegBanPesDetRecordsId));
    }

    /**
     * 新增蔬菜禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:add')")
    @Log(title = "蔬菜禁用农药检出及超标情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        return toAjax(outVegBanPesDetRecordsService.insertoutVegBanPesDetRecords(outVegBanPesDetRecords));
    }

    /**
     * 修改蔬菜禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:edit')")
    @Log(title = "蔬菜禁用农药检出及超标情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        return toAjax(outVegBanPesDetRecordsService.updateoutVegBanPesDetRecords(outVegBanPesDetRecords));
    }

    /**
     * 删除蔬菜禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:remove')")
    @Log(title = "蔬菜禁用农药检出及超标情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vegBanPesDetRecordsIds}")
    public AjaxResult remove(@PathVariable Long[] vegBanPesDetRecordsIds)
    {
        return toAjax(outVegBanPesDetRecordsService.deleteoutVegBanPesDetRecordsByVegBanPesDetRecordsIds(vegBanPesDetRecordsIds));
    }

    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:list')")
    @GetMapping("/listNew")
    public TableDataInfo listNew(outVegBanPesDetRecords outVegBanPesDetRecords)
    {
        startPage();
        List<outReturnType> list = outVegBanPesDetRecordsService.selectoutVegBanPesDetRecordsList2();
        return getDataTable(list);
    }
}
