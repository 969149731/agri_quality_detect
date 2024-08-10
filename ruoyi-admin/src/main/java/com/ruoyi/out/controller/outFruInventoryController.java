package com.ruoyi.out.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.VegFruStatistic;
import com.ruoyi.out.domain.vo.OutDetailTypeInventoryVo;
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
import com.ruoyi.out.domain.outFruInventory;
import com.ruoyi.out.service.IoutFruInventoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 水果种类及数量Controller
 * 
 * @author chenjie
 * @date 2024-03-12
 */
@RestController
@RequestMapping("/out/outFruInventory")
public class outFruInventoryController extends BaseController
{
    @Autowired
    private IoutFruInventoryService outFruInventoryService;
    @Autowired
    private IoutVegFruInventoryService outVegFruInventoryService;
    /**
     * 查询水果种类及数量列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruInventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(outFruInventory outFruInventory, agriCitySampleTestDetails agriCitySampleTestDetails)
    {
//        List<VegFruStatistic> list = outVegFruInventoryService.selectVegFruStatistic(agriCitySampleTestDetails);
//        return getDataTable(list);

        // 2024.8.5  按新的需求改
        String vegFruType="水果";
        List<OutDetailTypeInventoryVo> list = outVegFruInventoryService.selectSampleDetailTypeInventory(agriCitySampleTestDetails, vegFruType);
        return getDataTable(list);
    }

    /**
     * 导出水果种类及数量列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruInventory:export')")
    @Log(title = "水果种类及数量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outFruInventory outFruInventory, agriCitySampleTestDetails agriCitySampleTestDetails) throws IOException {
        int fruitSeqNo=1;

        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outFruInventoryExcelTemplate.xlsx");
        Map<String, Object> map = new HashMap<>();
        List<VegFruStatistic> list = outVegFruInventoryService.selectVegFruStatistic(agriCitySampleTestDetails);

        List<VegFruStatistic> listFru = new ArrayList<>();

        for (VegFruStatistic statistic : list) {
            if ("水果".equals(statistic.getType())) {
                statistic.setFruitSeqNo(fruitSeqNo++);
                listFru.add(statistic);
            }
        }

        map.put("maplistVeg", listFru);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 获取水果种类及数量详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outFruInventory:query')")
    @GetMapping(value = "/{vegFruInventoryId}")
    public AjaxResult getInfo(@PathVariable("vegFruInventoryId") Long vegFruInventoryId)
    {
        return success(outFruInventoryService.selectoutFruInventoryByVegFruInventoryId(vegFruInventoryId));
    }

    /**
     * 新增水果种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outFruInventory:add')")
    @Log(title = "水果种类及数量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outFruInventory outFruInventory)
    {
        return toAjax(outFruInventoryService.insertoutFruInventory(outFruInventory));
    }

    /**
     * 修改水果种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outFruInventory:edit')")
    @Log(title = "水果种类及数量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outFruInventory outFruInventory)
    {
        return toAjax(outFruInventoryService.updateoutFruInventory(outFruInventory));
    }

    /**
     * 删除水果种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outFruInventory:remove')")
    @Log(title = "水果种类及数量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vegFruInventoryIds}")
    public AjaxResult remove(@PathVariable Long[] vegFruInventoryIds)
    {
        return toAjax(outFruInventoryService.deleteoutFruInventoryByVegFruInventoryIds(vegFruInventoryIds));
    }
}
