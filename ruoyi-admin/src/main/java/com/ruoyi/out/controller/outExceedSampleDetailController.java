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
import com.ruoyi.out.domain.outExceedSampleDetail;
import com.ruoyi.out.service.IoutExceedSampleDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 超标蔬菜水果样品明细Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outExceedSampleDetail")
public class outExceedSampleDetailController extends BaseController
{
    @Autowired
    private IoutExceedSampleDetailService outExceedSampleDetailService;

    /**
     * 查询超标蔬菜水果样品明细列表
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(outExceedSampleDetail outExceedSampleDetail)
    {
        startPage();
        List<outExceedSampleDetail> list = outExceedSampleDetailService.selectoutExceedSampleDetailList(outExceedSampleDetail);
        return getDataTable(list);
    }

    /**
     * 导出超标蔬菜水果样品明细列表
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:export')")
    @Log(title = "超标蔬菜水果样品明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outExceedSampleDetail outExceedSampleDetail)
    {
        List<outExceedSampleDetail> list = outExceedSampleDetailService.selectoutExceedSampleDetailList(outExceedSampleDetail);
        ExcelUtil<outExceedSampleDetail> util = new ExcelUtil<outExceedSampleDetail>(outExceedSampleDetail.class);
        util.exportExcel(response, list, "超标蔬菜水果样品明细数据");
    }

    /**
     * 获取超标蔬菜水果样品明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:query')")
    @GetMapping(value = "/{exceedSampleDetailId}")
    public AjaxResult getInfo(@PathVariable("exceedSampleDetailId") Long exceedSampleDetailId)
    {
        return success(outExceedSampleDetailService.selectoutExceedSampleDetailByExceedSampleDetailId(exceedSampleDetailId));
    }

    /**
     * 新增超标蔬菜水果样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:add')")
    @Log(title = "超标蔬菜水果样品明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outExceedSampleDetail outExceedSampleDetail)
    {
        return toAjax(outExceedSampleDetailService.insertoutExceedSampleDetail(outExceedSampleDetail));
    }

    /**
     * 修改超标蔬菜水果样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:edit')")
    @Log(title = "超标蔬菜水果样品明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outExceedSampleDetail outExceedSampleDetail)
    {
        return toAjax(outExceedSampleDetailService.updateoutExceedSampleDetail(outExceedSampleDetail));
    }

    /**
     * 删除超标蔬菜水果样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:remove')")
    @Log(title = "超标蔬菜水果样品明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{exceedSampleDetailIds}")
    public AjaxResult remove(@PathVariable Long[] exceedSampleDetailIds)
    {
        return toAjax(outExceedSampleDetailService.deleteoutExceedSampleDetailByExceedSampleDetailIds(exceedSampleDetailIds));
    }
}
