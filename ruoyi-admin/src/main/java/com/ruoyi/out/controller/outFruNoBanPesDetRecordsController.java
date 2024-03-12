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
import com.ruoyi.out.domain.outReturnType;
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
import com.ruoyi.out.domain.outFruNoBanPesDetRecords;
import com.ruoyi.out.service.IoutFruNoBanPesDetRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 水果非禁止使用农药检出及超标情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outFruNoBanPesDetRecords")
public class outFruNoBanPesDetRecordsController extends BaseController
{
    @Autowired
    private IoutFruNoBanPesDetRecordsService outFruNoBanPesDetRecordsService;

    /**
     * 查询水果非禁止使用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        startPage();
        List<outFruNoBanPesDetRecords> list = outFruNoBanPesDetRecordsService.selectoutFruNoBanPesDetRecordsList(outFruNoBanPesDetRecords);
        return getDataTable(list);
    }

    /**
     * 导出水果非禁止使用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:export')")
    @Log(title = "水果非禁止使用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        List<outReturnType> list = outFruNoBanPesDetRecordsService.selectoutFruNoBanPesDetRecordsList2(outFruNoBanPesDetRecords);
        TemplateExportParams params = new TemplateExportParams("ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/outFruBanPesDetRecords.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "4.水果上非禁止使用农药检出及超标情况");
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
     * 获取水果非禁止使用农药检出及超标情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:query')")
    @GetMapping(value = "/{fruNoBanPesDetRecordsId}")
    public AjaxResult getInfo(@PathVariable("fruNoBanPesDetRecordsId") Long fruNoBanPesDetRecordsId)
    {
        return success(outFruNoBanPesDetRecordsService.selectoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsId(fruNoBanPesDetRecordsId));
    }

    /**
     * 新增水果非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:add')")
    @Log(title = "水果非禁止使用农药检出及超标情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return toAjax(outFruNoBanPesDetRecordsService.insertoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords));
    }

    /**
     * 修改水果非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:edit')")
    @Log(title = "水果非禁止使用农药检出及超标情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        return toAjax(outFruNoBanPesDetRecordsService.updateoutFruNoBanPesDetRecords(outFruNoBanPesDetRecords));
    }

    /**
     * 删除水果非禁止使用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:remove')")
    @Log(title = "水果非禁止使用农药检出及超标情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fruNoBanPesDetRecordsIds}")
    public AjaxResult remove(@PathVariable Long[] fruNoBanPesDetRecordsIds)
    {
        return toAjax(outFruNoBanPesDetRecordsService.deleteoutFruNoBanPesDetRecordsByFruNoBanPesDetRecordsIds(fruNoBanPesDetRecordsIds));
    }



    @PreAuthorize("@ss.hasPermi('out:outFruNoBanPesDetRecords:list')")
    @GetMapping("/listNew")
    public TableDataInfo listNew(outFruNoBanPesDetRecords outFruNoBanPesDetRecords)
    {
        startPage();
        List<outReturnType> list = outFruNoBanPesDetRecordsService.selectoutFruNoBanPesDetRecordsList2(outFruNoBanPesDetRecords);
        return getDataTable(list);
    }
}
