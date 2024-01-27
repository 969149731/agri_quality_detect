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
import com.ruoyi.out.domain.outVegFruInventory;
import com.ruoyi.out.service.IoutVegFruInventoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蔬菜水果种类及数量Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outVegFruInventory")
public class outVegFruInventoryController extends BaseController
{
    @Autowired
    private IoutVegFruInventoryService outVegFruInventoryService;

    /**
     * 查询蔬菜水果种类及数量列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegFruInventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(outVegFruInventory outVegFruInventory)
    {
        startPage();
        List<outVegFruInventory> list = outVegFruInventoryService.selectoutVegFruInventoryList(outVegFruInventory);
        return getDataTable(list);
    }

    /**
     * 导出蔬菜水果种类及数量列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegFruInventory:export')")
    @Log(title = "蔬菜水果种类及数量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outVegFruInventory outVegFruInventory)
    {
        List<outVegFruInventory> list = outVegFruInventoryService.selectoutVegFruInventoryList(outVegFruInventory);
        ExcelUtil<outVegFruInventory> util = new ExcelUtil<outVegFruInventory>(outVegFruInventory.class);
        util.exportExcel(response, list, "蔬菜水果种类及数量数据");
    }

    /**
     * 获取蔬菜水果种类及数量详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outVegFruInventory:query')")
    @GetMapping(value = "/{vegFruInventoryId}")
    public AjaxResult getInfo(@PathVariable("vegFruInventoryId") Long vegFruInventoryId)
    {
        return success(outVegFruInventoryService.selectoutVegFruInventoryByVegFruInventoryId(vegFruInventoryId));
    }

    /**
     * 新增蔬菜水果种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outVegFruInventory:add')")
    @Log(title = "蔬菜水果种类及数量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outVegFruInventory outVegFruInventory)
    {
        return toAjax(outVegFruInventoryService.insertoutVegFruInventory(outVegFruInventory));
    }

    /**
     * 修改蔬菜水果种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outVegFruInventory:edit')")
    @Log(title = "蔬菜水果种类及数量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outVegFruInventory outVegFruInventory)
    {
        return toAjax(outVegFruInventoryService.updateoutVegFruInventory(outVegFruInventory));
    }

    /**
     * 删除蔬菜水果种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outVegFruInventory:remove')")
    @Log(title = "蔬菜水果种类及数量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vegFruInventoryIds}")
    public AjaxResult remove(@PathVariable Long[] vegFruInventoryIds)
    {
        return toAjax(outVegFruInventoryService.deleteoutVegFruInventoryByVegFruInventoryIds(vegFruInventoryIds));
    }
}
