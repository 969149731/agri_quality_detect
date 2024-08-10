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
import com.ruoyi.out.domain.outTeaInventory;
import com.ruoyi.out.service.IoutTeaInventoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 茶叶种类及数量Controller
 * 
 * @author chenjie
 * @date 2024-04-08
 */
@RestController
@RequestMapping("/out/outTeaInventory")
public class outTeaInventoryController extends BaseController
{
    @Autowired
    private IoutTeaInventoryService outTeaInventoryService;
    @Autowired
    private IoutVegFruInventoryService outVegFruInventoryService;

    /**
     * 查询茶叶种类及数量列表
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaInventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(outTeaInventory outTeaInventory, agriCitySampleTestDetails agriCitySampleTestDetails)
    {
//        List<VegFruStatistic> list = outVegFruInventoryService.selectVegFruStatistic(agriCitySampleTestDetails);
//        return getDataTable(list);
        // 2024.8.5  按新的需求改
        String vegFruType="茶叶";
        List<OutDetailTypeInventoryVo> list = outVegFruInventoryService.selectSampleDetailTypeInventory(agriCitySampleTestDetails, vegFruType);
        return getDataTable(list);
    }

    /**
     * 导出茶叶种类及数量列表
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaInventory:export')")
    @Log(title = "茶叶种类及数量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outTeaInventory outTeaInventory,agriCitySampleTestDetails agriCitySampleTestDetails) throws IOException {
        int setTeaSeqNo=1;
//        TemplateExportParams params = new TemplateExportParams("ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/outTeaInventoryExcelTemplate.xlsx");
        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outTeaInventoryExcelTemplate.xlsx");
        Map<String, Object> map = new HashMap<>();
        List<VegFruStatistic> list = outVegFruInventoryService.selectVegFruStatistic(agriCitySampleTestDetails);

        List<VegFruStatistic> listTea = new ArrayList<>();

        for (VegFruStatistic statistic : list) {
            if ("茶叶".equals(statistic.getType())) {
                statistic.setTeaSeqNo(setTeaSeqNo++);
                listTea.add(statistic);
            }
        }

        map.put("maplistTea", listTea);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 获取茶叶种类及数量详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaInventory:query')")
    @GetMapping(value = "/{teaInventoryId}")
    public AjaxResult getInfo(@PathVariable("teaInventoryId") Long teaInventoryId)
    {
        return success(outTeaInventoryService.selectoutTeaInventoryByTeaInventoryId(teaInventoryId));
    }

    /**
     * 新增茶叶种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaInventory:add')")
    @Log(title = "茶叶种类及数量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outTeaInventory outTeaInventory)
    {
        return toAjax(outTeaInventoryService.insertoutTeaInventory(outTeaInventory));
    }

    /**
     * 修改茶叶种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaInventory:edit')")
    @Log(title = "茶叶种类及数量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outTeaInventory outTeaInventory)
    {
        return toAjax(outTeaInventoryService.updateoutTeaInventory(outTeaInventory));
    }

    /**
     * 删除茶叶种类及数量
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaInventory:remove')")
    @Log(title = "茶叶种类及数量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teaInventoryIds}")
    public AjaxResult remove(@PathVariable Long[] teaInventoryIds)
    {
        return toAjax(outTeaInventoryService.deleteoutTeaInventoryByTeaInventoryIds(teaInventoryIds));
    }
}
