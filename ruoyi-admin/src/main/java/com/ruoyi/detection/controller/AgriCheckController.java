package com.ruoyi.detection.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.detection.domain.AgriCheck;
import com.ruoyi.detection.service.IAgriCheckService;
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
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-09-04
 */
@RestController
@RequestMapping("/system/check")
public class AgriCheckController extends BaseController
{
    @Autowired
    private IAgriCheckService agriCheckService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AgriCheck argiCheck)
    {
        startPage();
        List<AgriCheck> list = agriCheckService.selectAgriCheckList(argiCheck);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AgriCheck argiCheck)
    {
        List<AgriCheck> list = agriCheckService.selectAgriCheckList(argiCheck);
        ExcelUtil<AgriCheck> util = new ExcelUtil<AgriCheck>(AgriCheck.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(agriCheckService.selectAgriCheckById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AgriCheck argiCheck)
    {
        return toAjax(agriCheckService.insertAgriCheck(argiCheck));
    }

    /**
     * 修改【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AgriCheck argiCheck)
    {
        return toAjax(agriCheckService.updateAgriCheck(argiCheck));
    }


    /**
     *  修改审核状态
     * @param id
     * @return
     */
    @GetMapping("/updateFlag/{id}")
    public AjaxResult updateFlag(@PathVariable("id") Long id){
        return agriCheckService.updateCheck(id);
    }

    /**
     * 删除【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(agriCheckService.deleteAgriCheckByIds(ids));
    }
}
