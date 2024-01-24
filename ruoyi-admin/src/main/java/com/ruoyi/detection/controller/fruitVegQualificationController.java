package com.ruoyi.detection.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
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
import com.ruoyi.detection.domain.fruitVegQualification;
import com.ruoyi.detection.service.IfruitVegQualificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 蔬果检测Controller
 * 
 * @author chenjie
 * @date 2024-01-19
 */
@RestController
@RequestMapping("/detection/fruitVegQualification")
public class fruitVegQualificationController extends BaseController
{
    @Autowired
    private IfruitVegQualificationService fruitVegQualificationService;


    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<fruitVegQualification> util = new ExcelUtil<fruitVegQualification>(fruitVegQualification.class);
        List<fruitVegQualification> fruitVegQualificationList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = fruitVegQualificationService.importFruitVegQualification(fruitVegQualificationList, updateSupport, operName);
        return success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<fruitVegQualification> util = new ExcelUtil<fruitVegQualification>(fruitVegQualification.class);
        util.importTemplateExcel(response, "各类蔬果合格率");
    }









    /**
     * 查询蔬果检测列表
     */
    @PreAuthorize("@ss.hasPermi('detection:fruitVegQualification:list')")
    @GetMapping("/list")
    public TableDataInfo list(fruitVegQualification fruitVegQualification)
    {
        startPage();
        List<fruitVegQualification> list = fruitVegQualificationService.selectfruitVegQualificationList(fruitVegQualification);
        return getDataTable(list);
    }



    /**
     * 导出蔬果检测列表
     */
    @PreAuthorize("@ss.hasPermi('detection:fruitVegQualification:export')")
    @Log(title = "蔬果检测", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, fruitVegQualification fruitVegQualification)
    {
        List<fruitVegQualification> list = fruitVegQualificationService.selectfruitVegQualificationList(fruitVegQualification);
        ExcelUtil<fruitVegQualification> util = new ExcelUtil<fruitVegQualification>(fruitVegQualification.class);
        util.exportExcel(response, list, "蔬果检测数据");
    }

    /**
     * 获取蔬果检测详细信息
     */
    @PreAuthorize("@ss.hasPermi('detection:fruitVegQualification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fruitVegQualificationService.selectfruitVegQualificationById(id));
    }

    /**
     * 新增蔬果检测
     */
    @PreAuthorize("@ss.hasPermi('detection:fruitVegQualification:add')")
    @Log(title = "蔬果检测", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody fruitVegQualification fruitVegQualification)
    {
        return toAjax(fruitVegQualificationService.insertfruitVegQualification(fruitVegQualification));
    }

    /**
     * 修改蔬果检测
     */
    @PreAuthorize("@ss.hasPermi('detection:fruitVegQualification:edit')")
    @Log(title = "蔬果检测", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody fruitVegQualification fruitVegQualification)
    {
        return toAjax(fruitVegQualificationService.updatefruitVegQualification(fruitVegQualification));
    }

    /**
     * 删除蔬果检测
     */
    @PreAuthorize("@ss.hasPermi('detection:fruitVegQualification:remove')")
    @Log(title = "蔬果检测", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fruitVegQualificationService.deletefruitVegQualificationByIds(ids));
    }
}
