package com.ruoyi.agriDictionary.controller;

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
import com.ruoyi.agriDictionary.domain.agriPesticideResidueStandardDictionary;
import com.ruoyi.agriDictionary.service.IagriPesticideResidueStandardDictionaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 农药最大残留限量参考标准Controller
 * 
 * @author chenjie
 * @date 2024-03-02
 */
@RestController
@RequestMapping("/agriDictionary/agriPesResStandDictionary")
public class agriPesticideResidueStandardDictionaryController extends BaseController
{
    @Autowired
    private IagriPesticideResidueStandardDictionaryService agriPesticideResidueStandardDictionaryService;

    /**
     * 查询农药最大残留限量参考标准列表
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesResStandDictionary:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary)
    {
        startPage();
        List<agriPesticideResidueStandardDictionary> list = agriPesticideResidueStandardDictionaryService.selectagriPesticideResidueStandardDictionaryList(agriPesticideResidueStandardDictionary);
        return getDataTable(list);
    }

    /**
     * 导出农药最大残留限量参考标准列表
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesResStandDictionary:export')")
    @Log(title = "农药最大残留限量参考标准", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary)
    {
        List<agriPesticideResidueStandardDictionary> list = agriPesticideResidueStandardDictionaryService.selectagriPesticideResidueStandardDictionaryList(agriPesticideResidueStandardDictionary);
        ExcelUtil<agriPesticideResidueStandardDictionary> util = new ExcelUtil<agriPesticideResidueStandardDictionary>(agriPesticideResidueStandardDictionary.class);
        util.exportExcel(response, list, "农药最大残留限量参考标准数据");
    }

    /**
     * 获取农药最大残留限量参考标准详细信息
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesResStandDictionary:query')")
    @GetMapping(value = "/{pesticideResidueStandardId}")
    public AjaxResult getInfo(@PathVariable("pesticideResidueStandardId") Long pesticideResidueStandardId)
    {
        return success(agriPesticideResidueStandardDictionaryService.selectagriPesticideResidueStandardDictionaryByPesticideResidueStandardId(pesticideResidueStandardId));
    }

    /**
     * 新增农药最大残留限量参考标准
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesResStandDictionary:add')")
    @Log(title = "农药最大残留限量参考标准", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary)
    {
        return toAjax(agriPesticideResidueStandardDictionaryService.insertagriPesticideResidueStandardDictionary(agriPesticideResidueStandardDictionary));
    }

    /**
     * 修改农药最大残留限量参考标准
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesResStandDictionary:edit')")
    @Log(title = "农药最大残留限量参考标准", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody agriPesticideResidueStandardDictionary agriPesticideResidueStandardDictionary)
    {
        return toAjax(agriPesticideResidueStandardDictionaryService.updateagriPesticideResidueStandardDictionary(agriPesticideResidueStandardDictionary));
    }

    /**
     * 删除农药最大残留限量参考标准
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesResStandDictionary:remove')")
    @Log(title = "农药最大残留限量参考标准", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pesticideResidueStandardIds}")
    public AjaxResult remove(@PathVariable Long[] pesticideResidueStandardIds)
    {
        return toAjax(agriPesticideResidueStandardDictionaryService.deleteagriPesticideResidueStandardDictionaryByPesticideResidueStandardIds(pesticideResidueStandardIds));
    }
}
