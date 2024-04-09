package com.ruoyi.out.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.ruoyi.out.domain.outFruVegQualification;
import com.ruoyi.out.service.IoutFruVegQualificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 各类蔬菜水果合格率情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outFruVegQualification")
public class outFruVegQualificationController extends BaseController
{
    @Autowired
    private IoutFruVegQualificationService outFruVegQualificationService;

    /**
     * 查询各类蔬菜水果合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:list')")
    @GetMapping("/list")
    public TableDataInfo list(outFruVegQualification outFruVegQualification)
    {
        startPage();
        List<outFruVegQualification> list = outFruVegQualificationService.selectoutFruVegQualificationList(outFruVegQualification);
        if (list==null) return null;//奇怪的报错，有空注意一下//可能是其他模块的返回值是带参数的，这个貌似不带
        return getDataTable(list);
    }

    /**
     * 导出各类蔬菜水果合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:export')")
    @Log(title = "各类蔬菜水果合格率情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outFruVegQualification outFruVegQualification)
    {
        outFruVegQualification outReturnTypeRecords=new outFruVegQualification();
        List<outFruVegQualification> list = outFruVegQualificationService.selectoutFruVegQualificationList(outFruVegQualification);
        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outFruVegQualification.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "5. 各类蔬菜水果合格率情况");
        map.put("maplist", list);
        try {
            // 生成 Excel 数据
            params.setColForEach(true);
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);

            // 将 Workbook 写入 ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            // 从 ByteArrayOutputStream 创建 ByteArrayInputStream
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            // 重新读取 Excel 数据
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // 获取单元格内容，并将相同内容的单元格合并
            //mergeCells(sheet);

            // 设置响应头
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("out.xlsx", "UTF-8"));

            // 将修改后的 Excel 数据写入响应输出流
            workbook.write(response.getOutputStream());

            // 最后关闭相关的流和 Workbook
            workbook.close();
            outputStream.close();
            inputStream.close();

            System.out.println("Excel 导出并合并完成。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取各类蔬菜水果合格率情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:query')")
    @GetMapping(value = "/{fruVegQualificationId}")
    public AjaxResult getInfo(@PathVariable("fruVegQualificationId") Long fruVegQualificationId)
    {
        return success(outFruVegQualificationService.selectoutFruVegQualificationByFruVegQualificationId(fruVegQualificationId));
    }

    /**
     * 新增各类蔬菜水果合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:add')")
    @Log(title = "各类蔬菜水果合格率情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outFruVegQualification outFruVegQualification)
    {
        return toAjax(outFruVegQualificationService.insertoutFruVegQualification(outFruVegQualification));
    }

    /**
     * 修改各类蔬菜水果合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:edit')")
    @Log(title = "各类蔬菜水果合格率情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outFruVegQualification outFruVegQualification)
    {
        return toAjax(outFruVegQualificationService.updateoutFruVegQualification(outFruVegQualification));
    }

    /**
     * 删除各类蔬菜水果合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruVegQualification:remove')")
    @Log(title = "各类蔬菜水果合格率情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fruVegQualificationIds}")
    public AjaxResult remove(@PathVariable Long[] fruVegQualificationIds)
    {
        return toAjax(outFruVegQualificationService.deleteoutFruVegQualificationByFruVegQualificationIds(fruVegQualificationIds));
    }
}
