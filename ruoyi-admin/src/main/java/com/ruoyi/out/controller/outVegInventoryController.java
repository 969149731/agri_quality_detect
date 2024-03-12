package com.ruoyi.out.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.out.domain.VegFruStatistic;
import com.ruoyi.out.domain.outVegFruInventory;
import com.ruoyi.out.service.IoutVegFruInventoryService;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.ruoyi.out.domain.outVegInventory;
import com.ruoyi.out.service.IoutVegInventoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蔬菜种类及数量Controller
 * 
 * @author chenjie
 * @date 2024-03-12
 */
@RestController
@RequestMapping("/out/outVegInventory")
public class outVegInventoryController extends BaseController
{
    @Autowired
    private IoutVegInventoryService outVegInventoryService;
    @Autowired
    private IoutVegFruInventoryService outVegFruInventoryService;
    /**
     * 查询蔬菜种类及数量列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegInventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(outVegInventory outVegInventory)
    {
//        startPage();
//        List<outVegInventory> list = outVegInventoryService.selectoutVegInventoryList(outVegInventory);
//        return getDataTable(list);

        List<VegFruStatistic> list = outVegFruInventoryService.selectVegFruStatistic();
        return getDataTable(list);

    }

    /**
     * 导出蔬菜种类及数量列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegInventory:export')")
    @Log(title = "蔬菜种类及数量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outVegInventory outVegInventory) throws IOException {
        int vegetableSeqNo=1;
        int fruitSeqNo=1;

        TemplateExportParams params = new TemplateExportParams("ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/outVegInventoryExcelTemplate.xlsx");
        Map<String, Object> map = new HashMap<>();
        List<VegFruStatistic> list = outVegFruInventoryService.selectVegFruStatistic();

        List<VegFruStatistic> listVeg = new ArrayList<>();

        for (VegFruStatistic statistic : list) {
            if ("蔬菜".equals(statistic.getType())) {
                statistic.setVegetableSeqNo(vegetableSeqNo++);
                listVeg.add(statistic);
            }
        }

        map.put("maplistVeg", listVeg);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 获取蔬菜种类及数量详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outVegInventory:query')")
    @GetMapping(value = "/{vegFruInventoryId}")
    public AjaxResult getInfo(@PathVariable("vegFruInventoryId") Long vegFruInventoryId)
    {
        return success(outVegInventoryService.selectoutVegInventoryByVegFruInventoryId(vegFruInventoryId));
    }

    /**
     * 新增蔬菜种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outVegInventory:add')")
    @Log(title = "蔬菜种类及数量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outVegInventory outVegInventory)
    {
        return toAjax(outVegInventoryService.insertoutVegInventory(outVegInventory));
    }

    /**
     * 修改蔬菜种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outVegInventory:edit')")
    @Log(title = "蔬菜种类及数量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outVegInventory outVegInventory)
    {
        return toAjax(outVegInventoryService.updateoutVegInventory(outVegInventory));
    }

    /**
     * 删除蔬菜种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outVegInventory:remove')")
    @Log(title = "蔬菜种类及数量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vegFruInventoryIds}")
    public AjaxResult remove(@PathVariable Long[] vegFruInventoryIds)
    {
        return toAjax(outVegInventoryService.deleteoutVegInventoryByVegFruInventoryIds(vegFruInventoryIds));
    }
}
