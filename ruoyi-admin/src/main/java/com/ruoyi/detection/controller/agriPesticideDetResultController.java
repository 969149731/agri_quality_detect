package com.ruoyi.detection.controller;

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
import com.ruoyi.detection.domain.agriPesticideDetResult;
import com.ruoyi.detection.service.IagriPesticideDetResultService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 农药检测结果Controller
 * 
 * @author chenjie
 * @date 2024-01-24
 */
@RestController
@RequestMapping("/detection/detectionValue")
public class agriPesticideDetResultController extends BaseController
{
    @Autowired
    private IagriPesticideDetResultService agriPesticideDetResultService;

    /**
     * 查询农药检测结果列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionValue:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriPesticideDetResult agriPesticideDetResult)
    {
        startPage();
        List<agriPesticideDetResult> list = agriPesticideDetResultService.selectagriPesticideDetResultList(agriPesticideDetResult);
        return getDataTable(list);
    }

    /**
     * 导出农药检测结果列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionValue:export')")
    @Log(title = "农药检测结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriPesticideDetResult agriPesticideDetResult)
    {
        List<agriPesticideDetResult> list = agriPesticideDetResultService.selectagriPesticideDetResultList(agriPesticideDetResult);
        ExcelUtil<agriPesticideDetResult> util = new ExcelUtil<agriPesticideDetResult>(agriPesticideDetResult.class);
        util.exportExcel(response, list, "农药检测结果数据");
    }

    /**
     * 获取农药检测结果详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionValue:query')")
    @GetMapping(value = "/{pesticideDetResultId}")
    public AjaxResult getInfo(@PathVariable("pesticideDetResultId") Long pesticideDetResultId)
    {
        return success(agriPesticideDetResultService.selectagriPesticideDetResultByPesticideDetResultId(pesticideDetResultId));
    }

    /**
     * 新增农药检测结果
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionValue:add')")
    @Log(title = "农药检测结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody agriPesticideDetResult agriPesticideDetResult)
    {
        return toAjax(agriPesticideDetResultService.insertagriPesticideDetResult(agriPesticideDetResult));
    }

    /**
     * 修改农药检测结果
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionValue:edit')")
    @Log(title = "农药检测结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody agriPesticideDetResult agriPesticideDetResult)
    {
        return toAjax(agriPesticideDetResultService.updateagriPesticideDetResult(agriPesticideDetResult));
    }

    /**
     * 删除农药检测结果
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionValue:remove')")
    @Log(title = "农药检测结果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pesticideDetResultIds}")
    public AjaxResult remove(@PathVariable Long[] pesticideDetResultIds)
    {
        return toAjax(agriPesticideDetResultService.deleteagriPesticideDetResultByPesticideDetResultIds(pesticideDetResultIds));
    }
}
