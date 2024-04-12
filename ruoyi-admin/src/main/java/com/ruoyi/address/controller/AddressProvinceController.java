package com.ruoyi.address.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.address.domain.AddressProvince;
import com.ruoyi.address.service.IAddressProvinceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 省份信息Controller
 * 
 * @author chenjie
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/address/province")
public class AddressProvinceController extends BaseController
{
    @Autowired
    private IAddressProvinceService addressProvinceService;

    /**
     * 查询省份信息列表
     */
    @PreAuthorize("@ss.hasPermi('address:province:list')")
    @GetMapping("/list")
    public TableDataInfo list(AddressProvince addressProvince)
    {
        startPage();
        List<AddressProvince> list = addressProvinceService.selectAddressProvinceList(addressProvince);
        return getDataTable(list);
    }

    /**
     * 导出省份信息列表
     */
    @PreAuthorize("@ss.hasPermi('address:province:export')")
    @Log(title = "省份信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AddressProvince addressProvince)
    {
        List<AddressProvince> list = addressProvinceService.selectAddressProvinceList(addressProvince);
        ExcelUtil<AddressProvince> util = new ExcelUtil<AddressProvince>(AddressProvince.class);
        util.exportExcel(response, list, "省份信息数据");
    }

    /**
     * 获取省份信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('address:province:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(addressProvinceService.selectAddressProvinceById(id));
    }

    /**
     * 新增省份信息
     */
    @PreAuthorize("@ss.hasPermi('address:province:add')")
    @Log(title = "省份信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AddressProvince addressProvince)
    {
        return toAjax(addressProvinceService.insertAddressProvince(addressProvince));
    }

    /**
     * 修改省份信息
     */
    @PreAuthorize("@ss.hasPermi('address:province:edit')")
    @Log(title = "省份信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AddressProvince addressProvince)
    {
        return toAjax(addressProvinceService.updateAddressProvince(addressProvince));
    }

    /**
     * 删除省份信息
     */
    @PreAuthorize("@ss.hasPermi('address:province:remove')")
    @Log(title = "省份信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(addressProvinceService.deleteAddressProvinceByIds(ids));
    }
}
