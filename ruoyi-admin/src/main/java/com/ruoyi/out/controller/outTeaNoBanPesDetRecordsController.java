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
import com.ruoyi.out.domain.outTeaNoBanPesDetRecords;
import com.ruoyi.out.service.IoutTeaNoBanPesDetRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 茶叶上非禁止使用农药检出及超标情况Controller
 * 
 * @author chenjie
 * @date 2024-04-08
 */
@RestController
@RequestMapping("/out/outTeaNoBanPesDetRecords")
public class outTeaNoBanPesDetRecordsController extends BaseController
{
    @Autowired
    private IoutTeaNoBanPesDetRecordsService outTeaNoBanPesDetRecordsService;

    /**
     * 查询茶叶上非禁止使用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaNoBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords)
    {
        startPage();
        List<outTeaNoBanPesDetRecords> list = outTeaNoBanPesDetRecordsService.selectoutTeaNoBanPesDetRecordsList(outTeaNoBanPesDetRecords);
        return getDataTable(list);
    }

    /**
     * 导出茶叶上非禁止使用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaNoBanPesDetRecords:export')")
    @Log(title = "茶叶上非禁止使用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords)
    {
        List<outTeaNoBanPesDetRecords> list = outTeaNoBanPesDetRecordsService.selectoutTeaNoBanPesDetRecordsList(outTeaNoBanPesDetRecords);
        ExcelUtil<outTeaNoBanPesDetRecords> util = new ExcelUtil<outTeaNoBanPesDetRecords>(outTeaNoBanPesDetRecords.class);
        util.exportExcel(response, list, "茶叶上非禁止使用农药检出及超标情况数据");
    }

    /**
     * 获取茶叶上非禁止使用农药检出及超标情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaNoBanPesDetRecords:query')")
    @GetMapping(value = "/{teaNoBanPesDetRecordsId}")
    public AjaxResult getInfo(@PathVariable("teaNoBanPesDetRecordsId") Long teaNoBanPesDetRecordsId)
    {
        return success(outTeaNoBanPesDetRecordsService.selectoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsId(teaNoBanPesDetRecordsId));
    }

    /**
     * 新增茶叶上非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaNoBanPesDetRecords:add')")
    @Log(title = "茶叶上非禁止使用农药检出及超标情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords)
    {
        return toAjax(outTeaNoBanPesDetRecordsService.insertoutTeaNoBanPesDetRecords(outTeaNoBanPesDetRecords));
    }

    /**
     * 修改茶叶上非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaNoBanPesDetRecords:edit')")
    @Log(title = "茶叶上非禁止使用农药检出及超标情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outTeaNoBanPesDetRecords outTeaNoBanPesDetRecords)
    {
        return toAjax(outTeaNoBanPesDetRecordsService.updateoutTeaNoBanPesDetRecords(outTeaNoBanPesDetRecords));
    }

    /**
     * 删除茶叶上非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaNoBanPesDetRecords:remove')")
    @Log(title = "茶叶上非禁止使用农药检出及超标情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teaNoBanPesDetRecordsIds}")
    public AjaxResult remove(@PathVariable Long[] teaNoBanPesDetRecordsIds)
    {
        return toAjax(outTeaNoBanPesDetRecordsService.deleteoutTeaNoBanPesDetRecordsByTeaNoBanPesDetRecordsIds(teaNoBanPesDetRecordsIds));
    }
}
