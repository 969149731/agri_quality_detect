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
import com.ruoyi.out.domain.outSampleStageType;
import com.ruoyi.out.service.IoutSampleStageTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 被抽样环节数量统计Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outSampleStageType")
public class outSampleStageTypeController extends BaseController
{
    @Autowired
    private IoutSampleStageTypeService outSampleStageTypeService;

    /**
     * 查询被抽样环节数量统计列表
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleStageType:list')")
    @GetMapping("/list")
    public TableDataInfo list(outSampleStageType outSampleStageType)
    {
        startPage();
        List<outSampleStageType> list = outSampleStageTypeService.selectoutSampleStageTypeList(outSampleStageType);
        return getDataTable(list);
    }

    /**
     * 导出被抽样环节数量统计列表
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleStageType:export')")
    @Log(title = "被抽样环节数量统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outSampleStageType outSampleStageType)
    {
        List<outSampleStageType> list = outSampleStageTypeService.selectoutSampleStageTypeList(outSampleStageType);
        ExcelUtil<outSampleStageType> util = new ExcelUtil<outSampleStageType>(outSampleStageType.class);
        util.exportExcel(response, list, "被抽样环节数量统计数据");
    }

    /**
     * 获取被抽样环节数量统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleStageType:query')")
    @GetMapping(value = "/{sampleQualityId}")
    public AjaxResult getInfo(@PathVariable("sampleQualityId") Long sampleQualityId)
    {
        return success(outSampleStageTypeService.selectoutSampleStageTypeBySampleQualityId(sampleQualityId));
    }

    /**
     * 新增被抽样环节数量统计
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleStageType:add')")
    @Log(title = "被抽样环节数量统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outSampleStageType outSampleStageType)
    {
        return toAjax(outSampleStageTypeService.insertoutSampleStageType(outSampleStageType));
    }

    /**
     * 修改被抽样环节数量统计
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleStageType:edit')")
    @Log(title = "被抽样环节数量统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outSampleStageType outSampleStageType)
    {
        return toAjax(outSampleStageTypeService.updateoutSampleStageType(outSampleStageType));
    }

    /**
     * 删除被抽样环节数量统计
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleStageType:remove')")
    @Log(title = "被抽样环节数量统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sampleQualityIds}")
    public AjaxResult remove(@PathVariable Long[] sampleQualityIds)
    {
        return toAjax(outSampleStageTypeService.deleteoutSampleStageTypeBySampleQualityIds(sampleQualityIds));
    }
}
