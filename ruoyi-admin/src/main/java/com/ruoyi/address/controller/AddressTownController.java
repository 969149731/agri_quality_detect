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
import com.ruoyi.address.domain.AddressTown;
import com.ruoyi.address.service.IAddressTownService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 区县信息Controller
 * 
 * @author chenjie
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/address/town")
public class AddressTownController extends BaseController
{
    @Autowired
    private IAddressTownService addressTownService;

    /**
     * 查询区县信息列表
     */
    @PreAuthorize("@ss.hasPermi('address:town:list')")
    @GetMapping("/list")
    public TableDataInfo list(AddressTown addressTown)
    {
        startPage();
        List<AddressTown> list = addressTownService.selectAddressTownList(addressTown);
        return getDataTable(list);
    }

    /**
     * 导出区县信息列表
     */
    @PreAuthorize("@ss.hasPermi('address:town:export')")
    @Log(title = "区县信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AddressTown addressTown)
    {
        List<AddressTown> list = addressTownService.selectAddressTownList(addressTown);
        ExcelUtil<AddressTown> util = new ExcelUtil<AddressTown>(AddressTown.class);
        util.exportExcel(response, list, "区县信息数据");
    }

    /**
     * 获取区县信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('address:town:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(addressTownService.selectAddressTownById(id));
    }

    /**
     * 新增区县信息
     */
    @PreAuthorize("@ss.hasPermi('address:town:add')")
    @Log(title = "区县信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AddressTown addressTown)
    {
        return toAjax(addressTownService.insertAddressTown(addressTown));
    }

    /**
     * 修改区县信息
     */
    @PreAuthorize("@ss.hasPermi('address:town:edit')")
    @Log(title = "区县信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AddressTown addressTown)
    {
        return toAjax(addressTownService.updateAddressTown(addressTown));
    }

    /**
     * 删除区县信息
     */
    @PreAuthorize("@ss.hasPermi('address:town:remove')")
    @Log(title = "区县信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(addressTownService.deleteAddressTownByIds(ids));
    }
}
