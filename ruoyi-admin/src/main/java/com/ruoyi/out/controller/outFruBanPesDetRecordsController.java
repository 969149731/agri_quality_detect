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
        List<outFruBanPesDetRecords> list = outFruBanPesDetRecordsService.selectoutFruBanPesDetRecordsList(outFruBanPesDetRecords);
        ExcelUtil<outFruBanPesDetRecords> util = new ExcelUtil<outFruBanPesDetRecords>(outFruBanPesDetRecords.class);
        util.exportExcel(response, list, "水果禁用农药检出及超标情况数据");
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
}
