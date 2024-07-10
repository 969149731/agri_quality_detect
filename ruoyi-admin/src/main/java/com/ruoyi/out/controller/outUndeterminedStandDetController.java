package com.ruoyi.out.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.domain.agriPesticideDetResultForBanPesticideDetection;
import com.ruoyi.detection.domain.agriPesticideDetResultForUndeterminedStandDet;
import com.ruoyi.out.domain.out2BanPesticideDetection;
import com.ruoyi.out.domain.out2UndeterminedStandDet;
import com.ruoyi.out.domain.outBanPesticideDetection;
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
import com.ruoyi.out.domain.outUndeterminedStandDet;
import com.ruoyi.out.service.IoutUndeterminedStandDetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 无判定标准检出值详细Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outUndeterminedStandDet")
public class outUndeterminedStandDetController extends BaseController
{
    @Autowired
    private IoutUndeterminedStandDetService outUndeterminedStandDetService;

    /**
     * 查询无判定标准检出值详细列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(outUndeterminedStandDet outUndeterminedStandDet)
//    {
//        startPage();
//        List<outUndeterminedStandDet> list = outUndeterminedStandDetService.selectoutUndeterminedStandDetList(outUndeterminedStandDet);
//        return getDataTable(list);
//    }
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
//        startPage();
        List<out2UndeterminedStandDet> out2UndeterminedStandDeList = outUndeterminedStandDetService.selectOutUndeterminedStandDetList(agriCitySampleTestDetails);
        return getDataTable(out2UndeterminedStandDeList);
    }

    /**
     * 导出无判定标准检出值详细列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:export')")
//    @Log(title = "无判定标准检出值详细", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, outUndeterminedStandDet outUndeterminedStandDet)
//    {
//        List<outUndeterminedStandDet> list = outUndeterminedStandDetService.selectoutUndeterminedStandDetList(outUndeterminedStandDet);
//        ExcelUtil<outUndeterminedStandDet> util = new ExcelUtil<outUndeterminedStandDet>(outUndeterminedStandDet.class);
//        util.exportExcel(response, list, "无判定标准检出值详细数据");
//    }

    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:export')")
    @Log(title = "无判定标准检出值详细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriCitySampleTestDetails agriCitySampleTestDetails) throws IOException {
        List<out2UndeterminedStandDet> out2UndeterminedStandDeList = outUndeterminedStandDetService.selectOutUndeterminedStandDetList(agriCitySampleTestDetails);
        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outUndeterminedStandDetExcelTemplate.xlsx");
        List<outUndeterminedStandDet> outUndeterminedStandDetlist = new ArrayList<>();
        for (out2UndeterminedStandDet out2UndeterminedStandDet:out2UndeterminedStandDeList){
            outUndeterminedStandDet outUndeterminedStandDet = new outUndeterminedStandDet();
            outUndeterminedStandDet.setDetectUnit(out2UndeterminedStandDet.getDetectUnit());
            outUndeterminedStandDet.setSampleCode(out2UndeterminedStandDet.getSampleCode());
            outUndeterminedStandDet.setVegFruName(out2UndeterminedStandDet.getVegFruName());
            outUndeterminedStandDet.setSamplingLocation(out2UndeterminedStandDet.getSamplingLocation());


            //获取无判定标准检出值详细信息，包括检测出的农药和值
            List<agriPesticideDetResultForUndeterminedStandDet> detPesticideNameAndValue = out2UndeterminedStandDet.getDetPesticideNameAndValue();
            //一个list中如果还有list中的话，包含的那个list要换行
            String result = detPesticideNameAndValue.stream()
                    .map(Object::toString) // 将每个元素转换成字符串
                    .collect(Collectors.joining("\n")); // 使用换行符作为分隔符

            outUndeterminedStandDet.setDetPesticideName(result);
            outUndeterminedStandDetlist.add(outUndeterminedStandDet);
        }
        Map<String, Object> map = new HashMap<>();
//        map.put("date", System.currentTimeMillis());
        map.put("maplist", outUndeterminedStandDetlist);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }


    /**
     * 获取无判定标准检出值详细详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:query')")
    @GetMapping(value = "/{undeterminedStandardDetectionId}")
    public AjaxResult getInfo(@PathVariable("undeterminedStandardDetectionId") Long undeterminedStandardDetectionId)
    {
        return success(outUndeterminedStandDetService.selectoutUndeterminedStandDetByUndeterminedStandardDetectionId(undeterminedStandardDetectionId));
    }

    /**
     * 新增无判定标准检出值详细
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:add')")
    @Log(title = "无判定标准检出值详细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outUndeterminedStandDet outUndeterminedStandDet)
    {
        return toAjax(outUndeterminedStandDetService.insertoutUndeterminedStandDet(outUndeterminedStandDet));
    }

    /**
     * 修改无判定标准检出值详细
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:edit')")
    @Log(title = "无判定标准检出值详细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outUndeterminedStandDet outUndeterminedStandDet)
    {
        return toAjax(outUndeterminedStandDetService.updateoutUndeterminedStandDet(outUndeterminedStandDet));
    }

    /**
     * 删除无判定标准检出值详细
     */
    @PreAuthorize("@ss.hasPermi('out:outUndeterminedStandDet:remove')")
    @Log(title = "无判定标准检出值详细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{undeterminedStandardDetectionIds}")
    public AjaxResult remove(@PathVariable Long[] undeterminedStandardDetectionIds)
    {
        return toAjax(outUndeterminedStandDetService.deleteoutUndeterminedStandDetByUndeterminedStandardDetectionIds(undeterminedStandardDetectionIds));
    }
}
