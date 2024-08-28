package com.ruoyi.detection.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.address.domain.AddressUse;
import com.ruoyi.detection.service.IagriCitySampleTestDetailsService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
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
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.service.IagriCitySampleTestDetailsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 监督抽查详细Controller
 * 
 * @author chenjie
 * @date 2024-08-27
 */
@RestController
@RequestMapping("/detection/DetectionDetailsJDCC")
public class agriCitySampleTestDetailsJDCCController extends BaseController
{
    @Autowired
    private IagriCitySampleTestDetailsService agriCitySampleTestDetailsService;


    /**
     * 查询监督抽查详细列表
     */
    @PreAuthorize("@ss.hasPermi('detection:DetectionDetailsJDCC:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        startPage();
        String samplingType="监督抽查";
        List<agriCitySampleTestDetails> list = agriCitySampleTestDetailsService.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails,samplingType);
        return getDataTable(list);
    }

    /**
     * 导出监督抽查详细列表
     */
    @PreAuthorize("@ss.hasPermi('detection:DetectionDetailsJDCC:export')")
    @Log(title = "监督抽查详细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriCitySampleTestDetails agriCitySampleTestDetails, AddressUse addressUse) throws IOException {

//        List<agriCitySampleTestDetails> list = agriCitySampleTestDetailsService.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
//        ExcelUtil<agriCitySampleTestDetails> util = new ExcelUtil<agriCitySampleTestDetails>(agriCitySampleTestDetails.class);
//        util.exportExcel(response, list, "各市样品检测结果详细数据");
        String samplingType ="监督抽查";
        List<agriCitySampleTestDetails> list = agriCitySampleTestDetailsService.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails,samplingType);
        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/agriCitySampleTestDetailsTemplate.xlsx");

        Map<String, Object> map = new HashMap<>();
        map.put("maplist", list);
        map.put("jcfs",samplingType);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 获取监督抽查详细详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:DetectionDetailsJDCC:query')")
    @GetMapping(value = "/{citySampleTestDetailsId}")
    public AjaxResult getInfo(@PathVariable("citySampleTestDetailsId") Long citySampleTestDetailsId)
    {
        return success(agriCitySampleTestDetailsService.selectagriCitySampleTestDetailsByCitySampleTestDetailsId(citySampleTestDetailsId));
    }

    /**
     * 新增监督抽查详细
     */
    @PreAuthorize("@ss.hasPermi('detection:DetectionDetailsJDCC:add')")
    @Log(title = "监督抽查详细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        return toAjax(agriCitySampleTestDetailsService.insertagriCitySampleTestDetails(agriCitySampleTestDetails));
    }

    /**
     * 修改监督抽查详细
     */
    @PreAuthorize("@ss.hasPermi('detection:DetectionDetailsJDCC:edit')")
    @Log(title = "监督抽查详细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        return toAjax(agriCitySampleTestDetailsService.updateagriCitySampleTestDetails(agriCitySampleTestDetails));
    }

    /**
     * 删除监督抽查详细
     */
    @PreAuthorize("@ss.hasPermi('detection:DetectionDetailsJDCC:remove')")
    @Log(title = "监督抽查详细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{citySampleTestDetailsIds}")
    public AjaxResult remove(@PathVariable Long[] citySampleTestDetailsIds)
    {
        return toAjax(agriCitySampleTestDetailsService.deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(citySampleTestDetailsIds));
    }

    //新需求的导入
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
//    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);  //表头所占用的行数
        List<Map<String, Object>> list;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), Map.class, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.gc();//调用垃圾回收
        }
        String operName = getUsername();
        String samplingType="监督抽查";
        String message = agriCitySampleTestDetailsService.importAgriOut2CitySampleTestDetailsList(list, updateSupport, operName,samplingType);
        return success(message);
    }



    //导入模板下载
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        try {
            // 使用ClassPathResource获取文件
            ClassPathResource resource = new ClassPathResource("excelOutTemplate/importTemplate.xlsx");
            InputStream inputStream = resource.getInputStream();

            // 设置response
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=template.xlsx");

            // 读取文件内容并写入response
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
