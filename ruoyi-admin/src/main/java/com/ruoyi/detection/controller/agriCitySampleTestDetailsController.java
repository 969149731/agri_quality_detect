package com.ruoyi.detection.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.address.domain.AddressUse;
import com.ruoyi.address.service.IAddressCityService;
import com.ruoyi.address.service.IAddressProvinceService;
import com.ruoyi.address.service.IAddressTownService;
import com.ruoyi.address.service.impl.AddressProvinceServiceImpl;
import com.ruoyi.detection.domain.agriOut2CitySampleTestDetails;
import com.ruoyi.detection.domain.agriOutCitySampleTestDetails;
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
 * 各市样品检测结果详细Controller
 * 
 * @author chenjie
 * @date 2024-01-24
 */
@RestController
@RequestMapping("/detection/detectionDetails")
public class agriCitySampleTestDetailsController extends BaseController
{
    @Autowired
    private IagriCitySampleTestDetailsService agriCitySampleTestDetailsService;
    @Autowired
    private IAddressProvinceService addressProvinceService;
    @Autowired
    private IAddressCityService addressCityService;
    @Autowired
    private IAddressTownService addressTownService;


//    改需求前用的导入
//    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
////    @PreAuthorize("@ss.hasPermi('system:user:import')")
//    @PostMapping("/importData")
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
//    {
//        ImportParams params = new ImportParams();
//        params.setTitleRows(1);  //表标题占用的行数
//        params.setHeadRows(2);  //表头所占用的行数
//        List<agriOut2CitySampleTestDetails> agriOut2CitySampleTestDetailsList = null;
//        try {
//            agriOut2CitySampleTestDetailsList = ExcelImportUtil.importExcel(file.getInputStream(), agriOut2CitySampleTestDetails.class, params);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }finally {
//            System.gc();//调用垃圾回收
//        }
//        String operName = getUsername();
//        String message = agriCitySampleTestDetailsService.importAgriOut2CitySampleTestDetailsList(agriOut2CitySampleTestDetailsList, updateSupport, operName);
//        return success(message);
//    }


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
        String message = agriCitySampleTestDetailsService.importAgriOut2CitySampleTestDetailsList(list, updateSupport, operName);
        return success(message);
    }
    /**
     * 查询各市样品检测结果详细列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionDetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        startPage();
        List<agriCitySampleTestDetails> list = agriCitySampleTestDetailsService.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
        return getDataTable(list);
    }

    /**
     * 导出各市样品检测结果详细列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionDetails:export')")
    @Log(title = "各市样品检测结果详细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, agriCitySampleTestDetails agriCitySampleTestDetails, AddressUse addressUse)
    {

        List<agriCitySampleTestDetails> list = agriCitySampleTestDetailsService.selectagriCitySampleTestDetailsList(agriCitySampleTestDetails);
        ExcelUtil<agriCitySampleTestDetails> util = new ExcelUtil<agriCitySampleTestDetails>(agriCitySampleTestDetails.class);
        util.exportExcel(response, list, "各市样品检测结果详细数据");
    }

    /**
     * 获取各市样品检测结果详细详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionDetails:query')")
    @GetMapping(value = "/{citySampleTestDetailsId}")
    public AjaxResult getInfo(@PathVariable("citySampleTestDetailsId") Long citySampleTestDetailsId)
    {
        return success(agriCitySampleTestDetailsService.selectagriCitySampleTestDetailsByCitySampleTestDetailsId(citySampleTestDetailsId));
    }

    /**
     * 新增各市样品检测结果详细
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionDetails:add')")
    @Log(title = "各市样品检测结果详细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        return toAjax(agriCitySampleTestDetailsService.insertagriCitySampleTestDetails(agriCitySampleTestDetails));
    }

    /**
     * 修改各市样品检测结果详细
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionDetails:edit')")
    @Log(title = "各市样品检测结果详细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        return toAjax(agriCitySampleTestDetailsService.updateagriCitySampleTestDetails(agriCitySampleTestDetails));
    }

    /**
     * 删除各市样品检测结果详细
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionDetails:remove')")
    @Log(title = "各市样品检测结果详细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{citySampleTestDetailsIds}")
    public AjaxResult remove(@PathVariable Long[] citySampleTestDetailsIds)
    {
        return toAjax(agriCitySampleTestDetailsService.deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(citySampleTestDetailsIds));
    }
}
