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
import cn.afterturn.easypoi.util.PoiMergeCellUtil;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.domain.agriPesticideDetResultForBanPesticideDetection;
import com.ruoyi.out.domain.out2BanPesticideDetection;
import com.ruoyi.out.domain.out2ExceedSampleDetail;
import com.ruoyi.out.domain.outExceedSampleDetail;
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
import com.ruoyi.out.domain.outBanPesticideDetection;
import com.ruoyi.out.service.IoutBanPesticideDetectionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蔬菜水果禁用农药检出样品明细Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outBanPesticideDetection")
public class outBanPesticideDetectionController extends BaseController
{
    @Autowired
    private IoutBanPesticideDetectionService outBanPesticideDetectionService;

    /**
     * 查询蔬菜水果禁用农药检出样品明细列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(outBanPesticideDetection outBanPesticideDetection)
//    {
//        startPage();
//        List<outBanPesticideDetection> list = outBanPesticideDetectionService.selectoutBanPesticideDetectionList(outBanPesticideDetection);
//        return getDataTable(list);
//    }

    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        List<out2BanPesticideDetection> list = outBanPesticideDetectionService.selectOutBanPesticideDetectionList(agriCitySampleTestDetails);
        return getDataTable(list);
    }

    /**
     * 导出蔬菜水果禁用农药检出样品明细列表
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:export')")
    @Log(title = "蔬菜水果禁用农药检出样品明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriCitySampleTestDetails agriCitySampleTestDetails) throws IOException {
        List<out2BanPesticideDetection> out2BanPesticideDetectionList = outBanPesticideDetectionService.selectOutBanPesticideDetectionList(agriCitySampleTestDetails);
        TemplateExportParams params = new TemplateExportParams("ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/outBanPesticideDetectionExcelTemplate.xlsx");
        List<outBanPesticideDetection> outBanPesticideDetectionLlist = new ArrayList<>();
        for (out2BanPesticideDetection out2BanPesticideDetection:out2BanPesticideDetectionList){
            outBanPesticideDetection outBanPesticideDet = new outBanPesticideDetection();
            outBanPesticideDet.setDetectUnit(out2BanPesticideDetection.getDetectUnit());
            outBanPesticideDet.setSampleCode(out2BanPesticideDetection.getSampleCode());
            outBanPesticideDet.setVegFruName(out2BanPesticideDetection.getVegFruName());
            outBanPesticideDet.setSamplingLocation(out2BanPesticideDetection.getSamplingLocation());

            List<agriPesticideDetResultForBanPesticideDetection> exceedPesticideNameAndPesticideValueAndlimitValue = out2BanPesticideDetection.getExceedPesticideNameAndPesticideValueAndlimitValue();
            //一个list中如果还有list中的话，包含的那个list要换行
            String result = exceedPesticideNameAndPesticideValueAndlimitValue.stream()
                    .map(Object::toString) // 将每个元素转换成字符串
                    .collect(Collectors.joining("\n")); // 使用换行符作为分隔符

            outBanPesticideDet.setExceedPesticideName(result);
            outBanPesticideDetectionLlist.add(outBanPesticideDet);
        }
        Map<String, Object> map = new HashMap<>();
//        map.put("date", System.currentTimeMillis());
        map.put("maplist", outBanPesticideDetectionLlist);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);

//Excel列相同时候自动合并
//参考https://blog.csdn.net/qq_44691484/article/details/129918543?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-129918543-blog-111531225.235%5Ev43%5Epc_blog_bottom_relevance_base5&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-3-129918543-blog-111531225.235%5Ev43%5Epc_blog_bottom_relevance_base5&utm_relevant_index=6
//        int[] relationColl = new int[]{0};
//        Map<Integer,int[]> mergeMap = new HashMap<>();
//        mergeMap.put(0,relationColl);
//        PoiMergeCellUtil.mergeCells(workbook.getSheetAt(0),mergeMap,1);

        workbook.write(response.getOutputStream());
        workbook.close();
    }






//原来的写法，没用
//    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:export')")
//    @Log(title = "蔬菜水果禁用农药检出样品明细", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, outBanPesticideDetection outBanPesticideDetection)
//    {
//        List<outBanPesticideDetection> list = outBanPesticideDetectionService.selectoutBanPesticideDetectionList(outBanPesticideDetection);
//        ExcelUtil<outBanPesticideDetection> util = new ExcelUtil<outBanPesticideDetection>(outBanPesticideDetection.class);
//        util.exportExcel(response, list, "蔬菜水果禁用农药检出样品明细数据");
//    }

    /**
     * 获取蔬菜水果禁用农药检出样品明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:query')")
    @GetMapping(value = "/{banPesticideDetectionId}")
    public AjaxResult getInfo(@PathVariable("banPesticideDetectionId") Long banPesticideDetectionId)
    {
        return success(outBanPesticideDetectionService.selectoutBanPesticideDetectionByBanPesticideDetectionId(banPesticideDetectionId));
    }

    /**
     * 新增蔬菜水果禁用农药检出样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:add')")
    @Log(title = "蔬菜水果禁用农药检出样品明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outBanPesticideDetection outBanPesticideDetection)
    {
        return toAjax(outBanPesticideDetectionService.insertoutBanPesticideDetection(outBanPesticideDetection));
    }

    /**
     * 修改蔬菜水果禁用农药检出样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:edit')")
    @Log(title = "蔬菜水果禁用农药检出样品明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outBanPesticideDetection outBanPesticideDetection)
    {
        return toAjax(outBanPesticideDetectionService.updateoutBanPesticideDetection(outBanPesticideDetection));
    }

    /**
     * 删除蔬菜水果禁用农药检出样品明细
     */
    @PreAuthorize("@ss.hasPermi('out:outBanPesticideDetection:remove')")
    @Log(title = "蔬菜水果禁用农药检出样品明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{banPesticideDetectionIds}")
    public AjaxResult remove(@PathVariable Long[] banPesticideDetectionIds)
    {
        return toAjax(outBanPesticideDetectionService.deleteoutBanPesticideDetectionByBanPesticideDetectionIds(banPesticideDetectionIds));
    }
}
