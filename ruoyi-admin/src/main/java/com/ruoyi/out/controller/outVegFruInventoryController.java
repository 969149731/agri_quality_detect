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
//        startPage();
//        List<outVegFruInventory> list = outVegFruInventoryService.selectoutVegFruInventoryList(outVegFruInventory);
        List<VegFruStatistic> list = outVegFruInventoryService.selectVegFruStatistic();

        return getDataTable(list);
    }

    /**
     * 导出蔬菜水果种类及数量列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegFruInventory:export')")
    @Log(title = "蔬菜水果种类及数量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outVegFruInventory outVegFruInventory) throws IOException {
//        List<outVegFruInventory> list = outVegFruInventoryService.selectoutVegFruInventoryList(outVegFruInventory);
//        ExcelUtil<outVegFruInventory> util = new ExcelUtil<outVegFruInventory>(outVegFruInventory.class);
//        util.exportExcel(response, list, "蔬菜水果种类及数量数据");
        Long index=1L;
        TemplateExportParams params = new TemplateExportParams("ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/outVegFruInventoryExcelTemplate.xlsx");
        Map<String, Object> map = new HashMap<>();
        List<VegFruStatistic> list = outVegFruInventoryService.selectVegFruStatistic();
        //这边开到
        List<outVegFruInventory> mergedList = new ArrayList<>();

        List<VegFruStatistic> listVeg = new ArrayList<>();
        List<VegFruStatistic> listFru = new ArrayList<>();

        for (VegFruStatistic statistic : list) {
            if ("蔬菜".equals(statistic.getType())) {
                listVeg.add(statistic);
            }
        }

        for (VegFruStatistic statistic : list) {
            if ("水果".equals(statistic.getType())) {
                listFru.add(statistic);
            }
        }
        listVeg.addAll(listFru);


        for (VegFruStatistic vegFruStatistic : listVeg) {
            // 在这里处理每个vegFruStatistic item
            outVegFruInventory VegFruInventory = new outVegFruInventory();
            VegFruInventory.setVegFruInventoryId(index++);
            if("蔬菜".equals(vegFruStatistic.getType())){
                VegFruInventory.setVegetableDetailName(vegFruStatistic.getName());
                VegFruInventory.setVegetableQuantity((long) vegFruStatistic.getQuantity());
            }

            if("水果".equals(vegFruStatistic.getType())){
                VegFruInventory.setFruitDetailName(vegFruStatistic.getName());
                VegFruInventory.setFruitQuantity((long) vegFruStatistic.getQuantity());
            }
            if (VegFruInventory.getFruitDetailName()!=null &&VegFruInventory.getVegetableDetailName()!=null ){
                mergedList.add(VegFruInventory);
            }
        }

//        map.put("maplist",mergedList);
//        map.put("index",index);
        map.put("maplistFru", listFru);
        map.put("maplistVeg", listVeg);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
        workbook.close();
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
