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
import com.ruoyi.out.domain.outFruVegQualification;
import com.ruoyi.out.service.IoutFruVegQualificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 各类蔬菜水果合格率情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outFruVegQualification")
public class outFruVegQualificationController extends BaseController
{
    @Autowired
    private IoutFruVegQualificationService outFruVegQualificationService;

    /**
     * 查询各类蔬菜水果合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:list')")
    @GetMapping("/list")
    public TableDataInfo list(outFruVegQualification outFruVegQualification)
    {
        startPage();
        List<outFruVegQualification> list = outFruVegQualificationService.selectoutFruVegQualificationList(outFruVegQualification);
        return getDataTable(list);
    }

    /**
     * 导出各类蔬菜水果合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:export')")
    @Log(title = "各类蔬菜水果合格率情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outFruVegQualification outFruVegQualification)
    {
        List<outFruVegQualification> list = outFruVegQualificationService.selectoutFruVegQualificationList(outFruVegQualification);
        ExcelUtil<outFruVegQualification> util = new ExcelUtil<outFruVegQualification>(outFruVegQualification.class);
        util.exportExcel(response, list, "各类蔬菜水果合格率情况数据");
    }

    /**
     * 获取各类蔬菜水果合格率情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:query')")
    @GetMapping(value = "/{fruVegQualificationId}")
    public AjaxResult getInfo(@PathVariable("fruVegQualificationId") Long fruVegQualificationId)
    {
        return success(outFruVegQualificationService.selectoutFruVegQualificationByFruVegQualificationId(fruVegQualificationId));
    }

    /**
     * 新增各类蔬菜水果合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:add')")
    @Log(title = "各类蔬菜水果合格率情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outFruVegQualification outFruVegQualification)
    {
        return toAjax(outFruVegQualificationService.insertoutFruVegQualification(outFruVegQualification));
    }

    /**
     * 修改各类蔬菜水果合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:edit')")
    @Log(title = "各类蔬菜水果合格率情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outFruVegQualification outFruVegQualification)
    {
        return toAjax(outFruVegQualificationService.updateoutFruVegQualification(outFruVegQualification));
    }

    /**
     * 删除各类蔬菜水果合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:remove')")
    @Log(title = "各类蔬菜水果合格率情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fruVegQualificationIds}")
    public AjaxResult remove(@PathVariable Long[] fruVegQualificationIds)
    {
        return toAjax(outFruVegQualificationService.deleteoutFruVegQualificationByFruVegQualificationIds(fruVegQualificationIds));
    }
}
