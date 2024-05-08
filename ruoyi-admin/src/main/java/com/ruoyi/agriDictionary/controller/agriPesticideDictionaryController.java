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
import com.ruoyi.agriDictionary.domain.agriPesticideDictionary;
import com.ruoyi.agriDictionary.service.IagriPesticideDictionaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 农药字典Controller
 * 
 * @author chenjie
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/agriDictionary/agriPesticideDictionary")
public class agriPesticideDictionaryController extends BaseController
{
    @Autowired
    private IagriPesticideDictionaryService agriPesticideDictionaryService;

    /**
     * 查询农药字典列表
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesticideDictionary:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriPesticideDictionary agriPesticideDictionary)
    {
        startPage();
        List<agriPesticideDictionary> list = agriPesticideDictionaryService.selectagriPesticideDictionaryList(agriPesticideDictionary);
        return getDataTable(list);
    }

    /**
     * 导出农药字典列表
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesticideDictionary:export')")
    @Log(title = "农药字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriPesticideDictionary agriPesticideDictionary)
    {
        List<agriPesticideDictionary> list = agriPesticideDictionaryService.selectagriPesticideDictionaryList(agriPesticideDictionary);
        ExcelUtil<agriPesticideDictionary> util = new ExcelUtil<agriPesticideDictionary>(agriPesticideDictionary.class);
        util.exportExcel(response, list, "农药字典数据");
    }

    /**
     * 获取农药字典详细信息
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesticideDictionary:query')")
    @GetMapping(value = "/{pesticideId}")
    public AjaxResult getInfo(@PathVariable("pesticideId") Long pesticideId)
    {
        return success(agriPesticideDictionaryService.selectagriPesticideDictionaryByPesticideId(pesticideId));
    }

    /**
     * 新增农药字典
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesticideDictionary:add')")
    @Log(title = "农药字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody agriPesticideDictionary agriPesticideDictionary)
    {
        return toAjax(agriPesticideDictionaryService.insertagriPesticideDictionary(agriPesticideDictionary));
    }

    /**
     * 修改农药字典
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesticideDictionary:edit')")
    @Log(title = "农药字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody agriPesticideDictionary agriPesticideDictionary)
    {
        return toAjax(agriPesticideDictionaryService.updateagriPesticideDictionary(agriPesticideDictionary));
    }

    /**
     * 删除农药字典
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriPesticideDictionary:remove')")
    @Log(title = "农药字典", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pesticideIds}")
    public AjaxResult remove(@PathVariable Long[] pesticideIds)
    {
        return toAjax(agriPesticideDictionaryService.deleteagriPesticideDictionaryByPesticideIds(pesticideIds));
    }
}
