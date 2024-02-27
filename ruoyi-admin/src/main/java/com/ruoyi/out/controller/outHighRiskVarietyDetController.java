package com.ruoyi.out.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.dlDetRecordSampleRes;
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
import com.ruoyi.out.domain.outHighRiskVarietyDet;
import com.ruoyi.out.service.IoutHighRiskVarietyDetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 高风险品种样品检出情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outHighRiskVarietyDet")
public class outHighRiskVarietyDetController extends BaseController
{
    @Autowired
    private IoutHighRiskVarietyDetService outHighRiskVarietyDetService;

    /**
     * 查询高风险品种样品检出情况列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(outHighRiskVarietyDet outHighRiskVarietyDet)
//    {
//        startPage();
//        List<outHighRiskVarietyDet> list = outHighRiskVarietyDetService.selectoutHighRiskVarietyDetList(outHighRiskVarietyDet);
//        return getDataTable(list);
//    }

    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        Map<String, List<outHighRiskVarietyDet>> stringHighRiskVarietyDetMap = outHighRiskVarietyDetService.selectOutHighRiskVarietyDetList(agriCitySampleTestDetails);
        return null;
//        return getDataTable(list);
    }

    /**
     * 导出高风险品种样品检出情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:export')")
    @Log(title = "高风险品种样品检出情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outHighRiskVarietyDet outHighRiskVarietyDet)
    {
        List<outHighRiskVarietyDet> list = outHighRiskVarietyDetService.selectoutHighRiskVarietyDetList(outHighRiskVarietyDet);
        ExcelUtil<outHighRiskVarietyDet> util = new ExcelUtil<outHighRiskVarietyDet>(outHighRiskVarietyDet.class);
        util.exportExcel(response, list, "高风险品种样品检出情况数据");
    }

    /**
     * 获取高风险品种样品检出情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:query')")
    @GetMapping(value = "/{outHighRiskVarietyDetId}")
    public AjaxResult getInfo(@PathVariable("outHighRiskVarietyDetId") Long outHighRiskVarietyDetId)
    {
        return success(outHighRiskVarietyDetService.selectoutHighRiskVarietyDetByOutHighRiskVarietyDetId(outHighRiskVarietyDetId));
    }

    /**
     * 新增高风险品种样品检出情况
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:add')")
    @Log(title = "高风险品种样品检出情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outHighRiskVarietyDet outHighRiskVarietyDet)
    {
        return toAjax(outHighRiskVarietyDetService.insertoutHighRiskVarietyDet(outHighRiskVarietyDet));
    }

    /**
     * 修改高风险品种样品检出情况
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:edit')")
    @Log(title = "高风险品种样品检出情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outHighRiskVarietyDet outHighRiskVarietyDet)
    {
        return toAjax(outHighRiskVarietyDetService.updateoutHighRiskVarietyDet(outHighRiskVarietyDet));
    }

    /**
     * 删除高风险品种样品检出情况
     */
    @PreAuthorize("@ss.hasPermi('out:outHighRiskVarietyDet:remove')")
    @Log(title = "高风险品种样品检出情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{outHighRiskVarietyDetIds}")
    public AjaxResult remove(@PathVariable Long[] outHighRiskVarietyDetIds)
    {
        return toAjax(outHighRiskVarietyDetService.deleteoutHighRiskVarietyDetByOutHighRiskVarietyDetIds(outHighRiskVarietyDetIds));
    }
}
