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
import com.ruoyi.out.domain.outFruNoBanPesDetRecords;
import com.ruoyi.out.service.IoutFruNoBanPesDetRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
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
    private IoutFruNoBanPesDetRecordsService outFruNoBanPesDetRecordsService;

    /**
     * 查询水果非禁止使用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        startPage();
        List<outFruNoBanPesDetRecords> list = outFruNoBanPesDetRecordsService.selectoutFruNoBanPesDetRecordsList(outFruNoBanPesDetRecords);
        return getDataTable(list);
    }

    /**
     * 导出水果非禁止使用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:export')")
    @Log(title = "水果非禁止使用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        List<outFruNoBanPesDetRecords> list = outFruNoBanPesDetRecordsService.selectoutFruNoBanPesDetRecordsList(outFruNoBanPesDetRecords);
        ExcelUtil<outFruNoBanPesDetRecords> util = new ExcelUtil<outFruNoBanPesDetRecords>(outFruNoBanPesDetRecords.class);
        util.exportExcel(response, list, "水果非禁止使用农药检出及超标情况数据");
    }

    /**
     * 获取水果非禁止使用农药检出及超标情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:query')")
    @GetMapping(value = "/{fruNoBanPesDetRecordsId}")
    public AjaxResult getInfo(@PathVariable("fruNoBanPesDetRecordsId") Long fruNoBanPesDetRecordsId)
    {
        return success(outFruNoBanPesDetRecordsService.selectoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(fruNoBanPesDetRecordsId));
    }

    /**
     * 新增水果非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:add')")
    @Log(title = "水果非禁止使用农药检出及超标情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return toAjax(outFruNoBanPesDetRecordsService.insertoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords));
    }

    /**
     * 修改水果非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:edit')")
    @Log(title = "水果非禁止使用农药检出及超标情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return toAjax(outFruNoBanPesDetRecordsService.updateoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords));
    }

    /**
     * 删除水果非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:remove')")
    @Log(title = "水果非禁止使用农药检出及超标情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fruNoBanPesDetRecordsIds}")
    public AjaxResult remove(@PathVariable Long[] fruNoBanPesDetRecordsIds)
    {
        return toAjax(outFruNoBanPesDetRecordsService.deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsIds(fruNoBanPesDetRecordsIds));
    }



    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:list')")
    @GetMapping("/listNew")
    public TableDataInfo listNew(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        startPage();
        List<outReturnType> list = outFruNoBanPesDetRecordsService.selectoutFruNoBanPesDetRecordsList2();
        return getDataTable(list);
    }
}
