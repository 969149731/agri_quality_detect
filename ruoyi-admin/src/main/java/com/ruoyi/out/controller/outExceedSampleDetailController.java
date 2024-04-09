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
import com.ruoyi.detection.domain.agriPesticideDetResultForOutExceedSampleDetail;
import com.ruoyi.out.domain.out2ExceedSampleDetail;
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
import com.ruoyi.out.domain.outExceedSampleDetail;
import com.ruoyi.out.service.IoutExceedSampleDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 超标蔬菜水果样品明细Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outExceedSampleDetail")
public class outExceedSampleDetailController extends BaseController
{
    @Autowired
    private IoutExceedSampleDetailService outExceedSampleDetailService;

    /**
     * 查询超标蔬菜水果样品明细列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(outExceedSampleDetail outExceedSampleDetail)
//    {
//        startPage();
//        List<outExceedSampleDetail> list = outExceedSampleDetailService.selectoutExceedSampleDetailList(outExceedSampleDetail);
//        return getDataTable(list);
//    }


    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
//        List<outExceedSampleDetail> list = outExceedSampleDetailService.selectoutExceedSampleDetailList(agriCitySampleTestDetails);
        List<out2ExceedSampleDetail> list = outExceedSampleDetailService.selectOutExceedSampleDetailList(agriCitySampleTestDetails);
        return getDataTable(list);
    }


    /**
     * 导出超标蔬菜水果样品明细列表
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:export')")
    @Log(title = "超标蔬菜水果样品明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriCitySampleTestDetails agriCitySampleTestDetails) throws IOException {
        //从out2ExceedSampleDetail里面获取值放到从outExceedSampleDetail中，out2ExceedSampleDetail用来前端展示，outExceedSampleDetail用来导出
        List<out2ExceedSampleDetail> out2ExceedSampleDetaiLlist = outExceedSampleDetailService.selectOutExceedSampleDetailList(agriCitySampleTestDetails);
        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outExceedSampleDetailExcelTemplate.xlsx");
        List<outExceedSampleDetail> outExceedSampleDetaiLlist = new ArrayList<>();
        for (out2ExceedSampleDetail out2ExceedSampleDetai:out2ExceedSampleDetaiLlist){
            outExceedSampleDetail outExceedSampleDetail = new outExceedSampleDetail();
            outExceedSampleDetail.setDetectUnit(out2ExceedSampleDetai.getDetectUnit());
            outExceedSampleDetail.setSampleCode(out2ExceedSampleDetai.getSampleCode());
            outExceedSampleDetail.setVegFruName(out2ExceedSampleDetai.getVegFruName());
            outExceedSampleDetail.setSamplingLocation(out2ExceedSampleDetai.getSamplingLocation());

            List<agriPesticideDetResultForOutExceedSampleDetail> exceedPesticideNameAndPesticideValueAndlimitValue = out2ExceedSampleDetai.getExceedPesticideNameAndPesticideValueAndlimitValue();
            //一个list中如果还有list中的话，包含的那个list要换行
            String result = exceedPesticideNameAndPesticideValueAndlimitValue.stream()
                    .map(Object::toString) // 将每个元素转换成字符串
                    .collect(Collectors.joining("\n")); // 使用换行符作为分隔符

            outExceedSampleDetail.setExceedPesticideName(result);
            outExceedSampleDetaiLlist.add(outExceedSampleDetail);
        }
        Map<String, Object> map = new HashMap<>();
//        map.put("date", System.currentTimeMillis());
        map.put("maplist", outExceedSampleDetaiLlist);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 获取超标蔬菜水果样品明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:query')")
    @GetMapping(value = "/{exceedSampleDetailId}")
    public AjaxResult getInfo(@PathVariable("exceedSampleDetailId") Long exceedSampleDetailId)
    {
        return success(outExceedSampleDetailService.selectoutExceedSampleDetailByExceedSampleDetailId(exceedSampleDetailId));
    }

    /**
     * 新增超标蔬菜水果样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:add')")
    @Log(title = "超标蔬菜水果样品明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outExceedSampleDetail outExceedSampleDetail)
    {
        return toAjax(outExceedSampleDetailService.insertoutExceedSampleDetail(outExceedSampleDetail));
    }

    /**
     * 修改超标蔬菜水果样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:edit')")
    @Log(title = "超标蔬菜水果样品明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outExceedSampleDetail outExceedSampleDetail)
    {
        return toAjax(outExceedSampleDetailService.updateoutExceedSampleDetail(outExceedSampleDetail));
    }

    /**
     * 删除超标蔬菜水果样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outExceedSampleDetail:remove')")
    @Log(title = "超标蔬菜水果样品明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{exceedSampleDetailIds}")
    public AjaxResult remove(@PathVariable Long[] exceedSampleDetailIds)
    {
        return toAjax(outExceedSampleDetailService.deleteoutExceedSampleDetailByExceedSampleDetailIds(exceedSampleDetailIds));
    }
}
