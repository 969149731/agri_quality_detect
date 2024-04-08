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
import com.ruoyi.out.domain.outTeaBanPesDetRecords;
import com.ruoyi.out.service.IoutTeaBanPesDetRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 茶叶禁用农药检出及超标情况Controller
 * 
 * @author chenjie
 * @date 2024-04-08
 */
@RestController
@RequestMapping("/out/outTeaBanPesDetRecords")
public class outTeaBanPesDetRecordsController extends BaseController
{
    @Autowired
    private IoutTeaBanPesDetRecordsService outTeaBanPesDetRecordsService;

    /**
     * 查询茶叶禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        startPage();
        List<outTeaBanPesDetRecords> list = outTeaBanPesDetRecordsService.selectoutTeaBanPesDetRecordsList(outTeaBanPesDetRecords);
        return getDataTable(list);
    }

    /**
     * 导出茶叶禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:export')")
    @Log(title = "茶叶禁用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        List<outTeaBanPesDetRecords> list = outTeaBanPesDetRecordsService.selectoutTeaBanPesDetRecordsList(outTeaBanPesDetRecords);
        ExcelUtil<outTeaBanPesDetRecords> util = new ExcelUtil<outTeaBanPesDetRecords>(outTeaBanPesDetRecords.class);
        util.exportExcel(response, list, "茶叶禁用农药检出及超标情况数据");
    }

    /**
     * 获取茶叶禁用农药检出及超标情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:query')")
    @GetMapping(value = "/{teaBanPesDetRecordsId}")
    public AjaxResult getInfo(@PathVariable("teaBanPesDetRecordsId") Long teaBanPesDetRecordsId)
    {
        return success(outTeaBanPesDetRecordsService.selectoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(teaBanPesDetRecordsId));
    }

    /**
     * 新增茶叶禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:add')")
    @Log(title = "茶叶禁用农药检出及超标情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        return toAjax(outTeaBanPesDetRecordsService.insertoutTeaBanPesDetRecords(outTeaBanPesDetRecords));
    }

    /**
     * 修改茶叶禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:edit')")
    @Log(title = "茶叶禁用农药检出及超标情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        return toAjax(outTeaBanPesDetRecordsService.updateoutTeaBanPesDetRecords(outTeaBanPesDetRecords));
    }

    /**
     * 删除茶叶禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:remove')")
    @Log(title = "茶叶禁用农药检出及超标情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teaBanPesDetRecordsIds}")
    public AjaxResult remove(@PathVariable Long[] teaBanPesDetRecordsIds)
    {
        return toAjax(outTeaBanPesDetRecordsService.deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsIds(teaBanPesDetRecordsIds));
    }
}
