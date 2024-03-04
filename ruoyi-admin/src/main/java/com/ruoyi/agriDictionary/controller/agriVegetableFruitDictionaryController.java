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
import com.ruoyi.agriDictionary.domain.agriVegetableFruitDictionary;
import com.ruoyi.agriDictionary.service.IagriVegetableFruitDictionaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蔬果表Controller
 * 
 * @author chenjie
 * @date 2024-03-02
 */
@RestController
@RequestMapping("/agriDictionary/agriVegetableFruitDictionary")
public class agriVegetableFruitDictionaryController extends BaseController
{
    @Autowired
    private IagriVegetableFruitDictionaryService agriVegetableFruitDictionaryService;

    /**
     * 查询蔬果表列表
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriVegetableFruitDictionary:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriVegetableFruitDictionary agriVegetableFruitDictionary)
    {
        startPage();
        List<agriVegetableFruitDictionary> list = agriVegetableFruitDictionaryService.selectagriVegetableFruitDictionaryList(agriVegetableFruitDictionary);
        return getDataTable(list);
    }

    /**
     * 导出蔬果表列表
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriVegetableFruitDictionary:export')")
    @Log(title = "蔬果表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriVegetableFruitDictionary agriVegetableFruitDictionary)
    {
        List<agriVegetableFruitDictionary> list = agriVegetableFruitDictionaryService.selectagriVegetableFruitDictionaryList(agriVegetableFruitDictionary);
        ExcelUtil<agriVegetableFruitDictionary> util = new ExcelUtil<agriVegetableFruitDictionary>(agriVegetableFruitDictionary.class);
        util.exportExcel(response, list, "蔬果表数据");
    }

    /**
     * 获取蔬果表详细信息
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriVegetableFruitDictionary:query')")
    @GetMapping(value = "/{vegetableFruitId}")
    public AjaxResult getInfo(@PathVariable("vegetableFruitId") Long vegetableFruitId)
    {
        return success(agriVegetableFruitDictionaryService.selectagriVegetableFruitDictionaryByVegetableFruitId(vegetableFruitId));
    }

    /**
     * 新增蔬果表
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriVegetableFruitDictionary:add')")
    @Log(title = "蔬果表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody agriVegetableFruitDictionary agriVegetableFruitDictionary)
    {
        return toAjax(agriVegetableFruitDictionaryService.insertagriVegetableFruitDictionary(agriVegetableFruitDictionary));
    }

    /**
     * 修改蔬果表
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriVegetableFruitDictionary:edit')")
    @Log(title = "蔬果表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody agriVegetableFruitDictionary agriVegetableFruitDictionary)
    {
        return toAjax(agriVegetableFruitDictionaryService.updateagriVegetableFruitDictionary(agriVegetableFruitDictionary));
    }

    /**
     * 删除蔬果表
     */
    @PreAuthorize("@ss.hasPermi('agriDictionary:agriVegetableFruitDictionary:remove')")
    @Log(title = "蔬果表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vegetableFruitIds}")
    public AjaxResult remove(@PathVariable Long[] vegetableFruitIds)
    {
        return toAjax(agriVegetableFruitDictionaryService.deleteagriVegetableFruitDictionaryByVegetableFruitIds(vegetableFruitIds));
    }
}
