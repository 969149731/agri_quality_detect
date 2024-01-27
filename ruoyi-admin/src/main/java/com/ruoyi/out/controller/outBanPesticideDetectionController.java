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
import com.ruoyi.out.domain.outBanPesticideDetection;
import com.ruoyi.out.service.IoutBanPesticideDetectionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蔬菜水果禁用农药检出样品明细Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outBanPesticideDetection")
public class outBanPesticideDetectionController extends BaseController
{
    @Autowired
    private IoutBanPesticideDetectionService outBanPesticideDetectionService;

    /**
     * 查询蔬菜水果禁用农药检出样品明细列表
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:list')")
    @GetMapping("/list")
    public TableDataInfo list(outBanPesticideDetection outBanPesticideDetection)
    {
        startPage();
        List<outBanPesticideDetection> list = outBanPesticideDetectionService.selectoutBanPesticideDetectionList(outBanPesticideDetection);
        return getDataTable(list);
    }

    /**
     * 导出蔬菜水果禁用农药检出样品明细列表
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:export')")
    @Log(title = "蔬菜水果禁用农药检出样品明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outBanPesticideDetection outBanPesticideDetection)
    {
        List<outBanPesticideDetection> list = outBanPesticideDetectionService.selectoutBanPesticideDetectionList(outBanPesticideDetection);
        ExcelUtil<outBanPesticideDetection> util = new ExcelUtil<outBanPesticideDetection>(outBanPesticideDetection.class);
        util.exportExcel(response, list, "蔬菜水果禁用农药检出样品明细数据");
    }

    /**
     * 获取蔬菜水果禁用农药检出样品明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:query')")
    @GetMapping(value = "/{banPesticideDetectionId}")
    public AjaxResult getInfo(@PathVariable("banPesticideDetectionId") Long banPesticideDetectionId)
    {
        return success(outBanPesticideDetectionService.selectoutBanPesticideDetectionByBanPesticideDetectionId(banPesticideDetectionId));
    }

    /**
     * 新增蔬菜水果禁用农药检出样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:add')")
    @Log(title = "蔬菜水果禁用农药检出样品明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outBanPesticideDetection outBanPesticideDetection)
    {
        return toAjax(outBanPesticideDetectionService.insertoutBanPesticideDetection(outBanPesticideDetection));
    }

    /**
     * 修改蔬菜水果禁用农药检出样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:edit')")
    @Log(title = "蔬菜水果禁用农药检出样品明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outBanPesticideDetection outBanPesticideDetection)
    {
        return toAjax(outBanPesticideDetectionService.updateoutBanPesticideDetection(outBanPesticideDetection));
    }

    /**
     * 删除蔬菜水果禁用农药检出样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:remove')")
    @Log(title = "蔬菜水果禁用农药检出样品明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{banPesticideDetectionIds}")
    public AjaxResult remove(@PathVariable Long[] banPesticideDetectionIds)
    {
        return toAjax(outBanPesticideDetectionService.deleteoutBanPesticideDetectionByBanPesticideDetectionIds(banPesticideDetectionIds));
    }
}
