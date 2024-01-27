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
import com.ruoyi.out.domain.outStandCompliance;
import com.ruoyi.out.service.IoutStandComplianceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 参照国际组织或国家标准合格率情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outStandCompliance")
public class outStandComplianceController extends BaseController
{
    @Autowired
    private IoutStandComplianceService outStandComplianceService;

    /**
     * 查询参照国际组织或国家标准合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:list')")
    @GetMapping("/list")
    public TableDataInfo list(outStandCompliance outStandCompliance)
    {
        startPage();
        List<outStandCompliance> list = outStandComplianceService.selectoutStandComplianceList(outStandCompliance);
        return getDataTable(list);
    }

    /**
     * 导出参照国际组织或国家标准合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:export')")
    @Log(title = "参照国际组织或国家标准合格率情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outStandCompliance outStandCompliance)
    {
        List<outStandCompliance> list = outStandComplianceService.selectoutStandComplianceList(outStandCompliance);
        ExcelUtil<outStandCompliance> util = new ExcelUtil<outStandCompliance>(outStandCompliance.class);
        util.exportExcel(response, list, "参照国际组织或国家标准合格率情况数据");
    }

    /**
     * 获取参照国际组织或国家标准合格率情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:query')")
    @GetMapping(value = "/{interStandId}")
    public AjaxResult getInfo(@PathVariable("interStandId") Long interStandId)
    {
        return success(outStandComplianceService.selectoutStandComplianceByInterStandId(interStandId));
    }

    /**
     * 新增参照国际组织或国家标准合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:add')")
    @Log(title = "参照国际组织或国家标准合格率情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outStandCompliance outStandCompliance)
    {
        return toAjax(outStandComplianceService.insertoutStandCompliance(outStandCompliance));
    }

    /**
     * 修改参照国际组织或国家标准合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:edit')")
    @Log(title = "参照国际组织或国家标准合格率情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outStandCompliance outStandCompliance)
    {
        return toAjax(outStandComplianceService.updateoutStandCompliance(outStandCompliance));
    }

    /**
     * 删除参照国际组织或国家标准合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:remove')")
    @Log(title = "参照国际组织或国家标准合格率情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{interStandIds}")
    public AjaxResult remove(@PathVariable Long[] interStandIds)
    {
        return toAjax(outStandComplianceService.deleteoutStandComplianceByInterStandIds(interStandIds));
    }
}
