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
import com.ruoyi.address.domain.AddressCity;
import com.ruoyi.address.service.IAddressCityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 城市信息Controller
 * 
 * @author chenjie
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/address/city")
public class AddressCityController extends BaseController
{
    @Autowired
    private IAddressCityService addressCityService;

    /**
     * 查询城市信息列表
     */
    @PreAuthorize("@ss.hasPermi('address:city:list')")
    @GetMapping("/list")
    public TableDataInfo list(AddressCity addressCity)
    {
        startPage();
        List<AddressCity> list = addressCityService.selectAddressCityList(addressCity);
        return getDataTable(list);
    }

    /**
     * 导出城市信息列表
     */
    @PreAuthorize("@ss.hasPermi('address:city:export')")
    @Log(title = "城市信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AddressCity addressCity)
    {
        List<AddressCity> list = addressCityService.selectAddressCityList(addressCity);
        ExcelUtil<AddressCity> util = new ExcelUtil<AddressCity>(AddressCity.class);
        util.exportExcel(response, list, "城市信息数据");
    }

    /**
     * 获取城市信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('address:city:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(addressCityService.selectAddressCityById(id));
    }

    /**
     * 新增城市信息
     */
    @PreAuthorize("@ss.hasPermi('address:city:add')")
    @Log(title = "城市信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AddressCity addressCity)
    {
        return toAjax(addressCityService.insertAddressCity(addressCity));
    }

    /**
     * 修改城市信息
     */
    @PreAuthorize("@ss.hasPermi('address:city:edit')")
    @Log(title = "城市信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AddressCity addressCity)
    {
        return toAjax(addressCityService.updateAddressCity(addressCity));
    }

    /**
     * 删除城市信息
     */
    @PreAuthorize("@ss.hasPermi('address:city:remove')")
    @Log(title = "城市信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(addressCityService.deleteAddressCityByIds(ids));
    }
}
