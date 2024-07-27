package com.ruoyi.detection.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.address.domain.AddressUse;
import com.ruoyi.address.service.IAddressCityService;
import com.ruoyi.address.service.IAddressProvinceService;
import com.ruoyi.address.service.IAddressTownService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.detection.service.IagriCitySampleTestDetailsService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各市样品检测结果详细Controller
 *
 * @author chenjie
 * @date 2024-01-24
 */
@RestController
@RequestMapping("/detection/myDetectionDetails")
public class MyImportAgriCitySampleTestDetailsController extends BaseController {
    @Autowired
    private IagriCitySampleTestDetailsService agriCitySampleTestDetailsService;
    @Autowired
    private IAddressProvinceService addressProvinceService;
    @Autowired
    private IAddressCityService addressCityService;
    @Autowired
    private IAddressTownService addressTownService;


    /**
     * 查询我的导入的各市样品检测结果详细列表
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionDetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails) {
        startPage();
        List<agriCitySampleTestDetails> list = agriCitySampleTestDetailsService.selectMyImportAgriCitySampleTestDetailsList(agriCitySampleTestDetails);
        return getDataTable(list);
    }


    /**
     * 删除各市样品检测结果详细
     */
    @PreAuthorize("@ss.hasPermi('detection:detectionDetails:remove')")
    @Log(title = "各市样品检测结果详细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{citySampleTestDetailsIds}")
    public AjaxResult remove(@PathVariable Long[] citySampleTestDetailsIds) {
        return toAjax(agriCitySampleTestDetailsService.deleteagriCitySampleTestDetailsByCitySampleTestDetailsIds(citySampleTestDetailsIds));
    }




}

