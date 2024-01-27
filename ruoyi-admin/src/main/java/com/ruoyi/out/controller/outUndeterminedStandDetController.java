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
import com.ruoyi.out.domain.outUndeterminedStandDet;
import com.ruoyi.out.service.IoutUndeterminedStandDetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 无判定标准检出值详细Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outUndeterminedStandDet")
public class outUndeterminedStandDetController extends BaseController
{
    @Autowired
    private IoutUndeterminedStandDetService outUndeterminedStandDetService;

    /**
     * 查询无判定标准检出值详细列表
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:list')")
    @GetMapping("/list")
    public TableDataInfo list(outUndeterminedStandDet outUndeterminedStandDet)
    {
        startPage();
        List<outUndeterminedStandDet> list = outUndeterminedStandDetService.selectoutUndeterminedStandDetList(outUndeterminedStandDet);
        return getDataTable(list);
    }

    /**
     * 导出无判定标准检出值详细列表
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:export')")
    @Log(title = "无判定标准检出值详细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outUndeterminedStandDet outUndeterminedStandDet)
    {
        List<outUndeterminedStandDet> list = outUndeterminedStandDetService.selectoutUndeterminedStandDetList(outUndeterminedStandDet);
        ExcelUtil<outUndeterminedStandDet> util = new ExcelUtil<outUndeterminedStandDet>(outUndeterminedStandDet.class);
        util.exportExcel(response, list, "无判定标准检出值详细数据");
    }

    /**
     * 获取无判定标准检出值详细详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:query')")
    @GetMapping(value = "/{undeterminedStandardDetectionId}")
    public AjaxResult getInfo(@PathVariable("undeterminedStandardDetectionId") Long undeterminedStandardDetectionId)
    {
        return success(outUndeterminedStandDetService.selectoutUndeterminedStandDetByUndeterminedStandardDetectionId(undeterminedStandardDetectionId));
    }

    /**
     * 新增无判定标准检出值详细
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:add')")
    @Log(title = "无判定标准检出值详细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outUndeterminedStandDet outUndeterminedStandDet)
    {
        return toAjax(outUndeterminedStandDetService.insertoutUndeterminedStandDet(outUndeterminedStandDet));
    }

    /**
     * 修改无判定标准检出值详细
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:edit')")
    @Log(title = "无判定标准检出值详细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outUndeterminedStandDet outUndeterminedStandDet)
    {
        return toAjax(outUndeterminedStandDetService.updateoutUndeterminedStandDet(outUndeterminedStandDet));
    }

    /**
     * 删除无判定标准检出值详细
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:remove')")
    @Log(title = "无判定标准检出值详细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{undeterminedStandardDetectionIds}")
    public AjaxResult remove(@PathVariable Long[] undeterminedStandardDetectionIds)
    {
        return toAjax(outUndeterminedStandDetService.deleteoutUndeterminedStandDetByUndeterminedStandardDetectionIds(undeterminedStandardDetectionIds));
    }
}
