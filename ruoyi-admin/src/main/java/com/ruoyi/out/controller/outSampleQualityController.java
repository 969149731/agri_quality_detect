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
import com.ruoyi.out.domain.outSampleQuality;
import com.ruoyi.out.service.IoutSampleQualityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 各抽样环节合格率情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outSampleQuality")
public class outSampleQualityController extends BaseController
{
    @Autowired
    private IoutSampleQualityService outSampleQualityService;

    /**
     * 查询各抽样环节合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleQuality:list')")
    @GetMapping("/list")
    public TableDataInfo list(outSampleQuality outSampleQuality)
    {
        startPage();
        List<outSampleQuality> list = outSampleQualityService.selectoutSampleQualityList(outSampleQuality);
        return getDataTable(list);
    }

    /**
     * 导出各抽样环节合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleQuality:export')")
    @Log(title = "各抽样环节合格率情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outSampleQuality outSampleQuality)
    {
        List<outSampleQuality> list = outSampleQualityService.selectoutSampleQualityList(outSampleQuality);
        ExcelUtil<outSampleQuality> util = new ExcelUtil<outSampleQuality>(outSampleQuality.class);
        util.exportExcel(response, list, "各抽样环节合格率情况数据");
    }

    /**
     * 获取各抽样环节合格率情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleQuality:query')")
    @GetMapping(value = "/{sampleQualityId}")
    public AjaxResult getInfo(@PathVariable("sampleQualityId") Long sampleQualityId)
    {
        return success(outSampleQualityService.selectoutSampleQualityBySampleQualityId(sampleQualityId));
    }

    /**
     * 新增各抽样环节合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleQuality:add')")
    @Log(title = "各抽样环节合格率情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outSampleQuality outSampleQuality)
    {
        return toAjax(outSampleQualityService.insertoutSampleQuality(outSampleQuality));
    }

    /**
     * 修改各抽样环节合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleQuality:edit')")
    @Log(title = "各抽样环节合格率情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outSampleQuality outSampleQuality)
    {
        return toAjax(outSampleQualityService.updateoutSampleQuality(outSampleQuality));
    }

    /**
     * 删除各抽样环节合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outSampleQuality:remove')")
    @Log(title = "各抽样环节合格率情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sampleQualityIds}")
    public AjaxResult remove(@PathVariable Long[] sampleQualityIds)
    {
        return toAjax(outSampleQualityService.deleteoutSampleQualityBySampleQualityIds(sampleQualityIds));
    }
}
