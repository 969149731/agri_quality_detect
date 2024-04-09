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
import com.ruoyi.out.domain.outTeaBanPesDetRecords;
import com.ruoyi.out.service.IoutTeaBanPesDetRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 茶叶禁用农药检出及超标情况Controller
 * 
 * @author chenjie
 * @date 2024-04-08
 */
@RestController
@RequestMapping("/out/outTeaBanPesDetRecords")
public class outTeaBanPesDetRecordsController extends BaseController
{
    @Autowired
    private IoutTeaBanPesDetRecordsService outTeaBanPesDetRecordsService;

    /**
     * 查询茶叶禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        startPage();
        List<outReturnType> list = outTeaBanPesDetRecordsService.selectoutTeaBanPesDetRecordsList(outTeaBanPesDetRecords);
        return getDataTable(list);
    }

    /**
     * 导出茶叶禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:export')")
    @Log(title = "茶叶禁用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
//        util.exportExcel(response, list, "茶叶禁用农药检出及超标情况数据");

        List<outReturnType> list = outTeaBanPesDetRecordsService.selectoutTeaBanPesDetRecordsList(outTeaBanPesDetRecords);

        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outFruBanPesDetRecords.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "茶叶禁用农药检出及超标情况表");
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
     * 获取茶叶禁用农药检出及超标情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:query')")
    @GetMapping(value = "/{teaBanPesDetRecordsId}")
    public AjaxResult getInfo(@PathVariable("teaBanPesDetRecordsId") Long teaBanPesDetRecordsId)
    {
        return success(outTeaBanPesDetRecordsService.selectoutTeaBanPesDetRecordsByTeaBanPesDetRecordsId(teaBanPesDetRecordsId));
    }

    /**
     * 新增茶叶禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:add')")
    @Log(title = "茶叶禁用农药检出及超标情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        return toAjax(outTeaBanPesDetRecordsService.insertoutTeaBanPesDetRecords(outTeaBanPesDetRecords));
    }

    /**
     * 修改茶叶禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:edit')")
    @Log(title = "茶叶禁用农药检出及超标情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outTeaBanPesDetRecords outTeaBanPesDetRecords)
    {
        return toAjax(outTeaBanPesDetRecordsService.updateoutTeaBanPesDetRecords(outTeaBanPesDetRecords));
    }

    /**
     * 删除茶叶禁用农药检出及超标情况
     */
    @PreAuthorize("@ss.hasPermi('out:outTeaBanPesDetRecords:remove')")
    @Log(title = "茶叶禁用农药检出及超标情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teaBanPesDetRecordsIds}")
    public AjaxResult remove(@PathVariable Long[] teaBanPesDetRecordsIds)
    {
        return toAjax(outTeaBanPesDetRecordsService.deleteoutTeaBanPesDetRecordsByTeaBanPesDetRecordsIds(teaBanPesDetRecordsIds));
    }
}
