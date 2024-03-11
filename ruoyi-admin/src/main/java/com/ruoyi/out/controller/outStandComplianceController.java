package com.ruoyi.out.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.out.domain.outStandardReturnType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
import com.ruoyi.out.domain.outStandCompliance;
import com.ruoyi.out.service.IoutStandComplianceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 参照国际组织或国家标准合格率情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outStandCompliance")
public class outStandComplianceController extends BaseController
{
    @Autowired
    private IoutStandComplianceService outStandComplianceService;

    /**
     * 查询参照国际组织或国家标准合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:list')")
    @GetMapping("/list")
    public TableDataInfo list(outStandCompliance outStandCompliance)
    {
        startPage();
        List<outStandCompliance> list = outStandComplianceService.selectoutStandComplianceList(outStandCompliance);
        return getDataTable(list);
    }

    /**
     * 导出参照国际组织或国家标准合格率情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:export')")
    @Log(title = "参照国际组织或国家标准合格率情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, outStandCompliance outStandCompliance)
    {
        outStandardReturnType NewOne=new outStandardReturnType();//可能后续会用到传参的，目前传空的
        List<outStandardReturnType> list = outStandComplianceService.selectoutStandComplianceList2(NewOne);
        //计算合格率
        outStandardReturnType SampleNum=list.get(list.size()-2);
        outStandardReturnType passNum=list.get(list.size()-1);

        System.out.println("抽样数"+SampleNum);
        System.out.println("合格数"+passNum);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        BigDecimal CNpassRate=computePassRate(SampleNum.CN,passNum.CN);
        BigDecimal CACpassRate=computePassRate(SampleNum.CAC,passNum.CAC);
        BigDecimal USpassRate=computePassRate(SampleNum.US,passNum.US);
        BigDecimal EUpassRate=computePassRate(SampleNum.EU,passNum.EU);
        BigDecimal JPNpassRate=computePassRate(SampleNum.JPN,passNum.JPN);
        BigDecimal KRpassRate=computePassRate(SampleNum.KR,passNum.KR);




        TemplateExportParams params = new TemplateExportParams("ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/outStandCompliance.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "3.水果禁用农药检出及超标情况表");
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

            //追加合格率数据
            addPassRate(sheet,list.size()-2,
                    CNpassRate,
                    CACpassRate,
                    USpassRate,
                    EUpassRate,
                    JPNpassRate,
                    KRpassRate);
            // 获取单元格内容，并将相同内容的单元格合并
            mergeCells(sheet,list.size()-2);//-3是因为抽样数量、合格数、合格率与前面格式不同



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
     * 获取参照国际组织或国家标准合格率情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:query')")
    @GetMapping(value = "/{interStandId}")
    public AjaxResult getInfo(@PathVariable("interStandId") Long interStandId)
    {
        return success(outStandComplianceService.selectoutStandComplianceByInterStandId(interStandId));
    }

    /**
     * 新增参照国际组织或国家标准合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:add')")
    @Log(title = "参照国际组织或国家标准合格率情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody outStandCompliance outStandCompliance)
    {
        return toAjax(outStandComplianceService.insertoutStandCompliance(outStandCompliance));
    }

    /**
     * 修改参照国际组织或国家标准合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:edit')")
    @Log(title = "参照国际组织或国家标准合格率情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody outStandCompliance outStandCompliance)
    {
        return toAjax(outStandComplianceService.updateoutStandCompliance(outStandCompliance));
    }

    /**
     * 删除参照国际组织或国家标准合格率情况
     */
    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:remove')")
    @Log(title = "参照国际组织或国家标准合格率情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{interStandIds}")
    public AjaxResult remove(@PathVariable Long[] interStandIds)
    {
        return toAjax(outStandComplianceService.deleteoutStandComplianceByInterStandIds(interStandIds));
    }

    @PreAuthorize("@ss.hasPermi('out:outStandCompliance:list')")
    @GetMapping("/listNew")
    public TableDataInfo listNew(outStandardReturnType outStandardReturnType)
    {
        startPage();
        List<outStandardReturnType> list = outStandComplianceService.selectoutStandComplianceList2(outStandardReturnType);
        return getDataTable(list);
    }
    //工具方法
    private static void mergeCells(Sheet sheet,int pesticideNum) {//合并表头单元格
        //农药超标数
        CellRangeAddress range1 = new CellRangeAddress(1, 1, 1, pesticideNum);

        //抽检数量、合格数、合格率
        CellRangeAddress range2 = new CellRangeAddress(1, 2, pesticideNum+1, pesticideNum+1);
        CellRangeAddress range3 = new CellRangeAddress(1, 2, pesticideNum+2, pesticideNum+2);
        CellRangeAddress range4 = new CellRangeAddress(1, 2, pesticideNum+3, pesticideNum+3);

        sheet.addMergedRegion(range1);
        sheet.addMergedRegion(range2);
        sheet.addMergedRegion(range3);
        sheet.addMergedRegion(range4);


    }

    private static BigDecimal computePassRate(int sampleNum,int passNum) {//计算合格率
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Double percentage=(double)((double)passNum/(double)sampleNum)*100;
        return new BigDecimal(decimalFormat.format(percentage).toString());
    }

    private static void addPassRate(Sheet sheet,int pesticideNum,
                                    BigDecimal CNpassRate,
                                    BigDecimal CACpassRate,
                                    BigDecimal USpassRate,
                                    BigDecimal EUpassRate,
                                    BigDecimal JPNpassRate,
                                    BigDecimal KRpassRate
                                    ) {//合并表头单元格
        Cell newCell=sheet.getRow(3).getCell(pesticideNum+3);
        CellStyle cellStyle = newCell.getCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN); // 薄边框
        cellStyle.setBorderLeft(BorderStyle.THIN);// 薄边框
        cellStyle.setBorderRight(BorderStyle.THIN); // 双边框
        cellStyle.setBorderTop(BorderStyle.THIN);// 薄边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平布局：居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直布局：居中

        Font font = sheet.getWorkbook().createFont();
        font.setBold(false); // 加粗
        font.setFontName("宋体"); // 字体
        font.setFontHeightInPoints((short) 12); // 字体高度
        font.setColor(IndexedColors.BLACK.getIndex()); // 字体颜色：黑色
        cellStyle.setFont(font);

        Row rowCN =sheet.getRow(3);
        newCell =rowCN.getCell(pesticideNum+3); //设置特定单元格的数据
        newCell.setCellValue(CNpassRate.toString());
        newCell.setCellStyle(cellStyle);

        Row rowCAC =sheet.getRow(4);
        newCell =rowCAC.getCell(pesticideNum+3);
        newCell.setCellValue(CACpassRate.toString());
        newCell.setCellStyle(cellStyle);

        Row rowUS =sheet.getRow(5);
        newCell =rowUS.getCell(pesticideNum+3);
        newCell.setCellValue(USpassRate.toString());
        newCell.setCellStyle(cellStyle);

        Row rowEU =sheet.getRow(6);
        newCell =rowEU.getCell(pesticideNum+3);
        newCell.setCellValue(EUpassRate.toString());
        newCell.setCellStyle(cellStyle);

        Row rowJPN =sheet.getRow(7);
        newCell =rowJPN.getCell(pesticideNum+3);
        newCell.setCellValue(JPNpassRate.toString());
        newCell.setCellStyle(cellStyle);

        Row rowKR =sheet.getRow(8);
        newCell =rowKR.getCell(pesticideNum+3);
        newCell.setCellValue(KRpassRate.toString());
        newCell.setCellStyle(cellStyle);

        //表头
        Row rowHeader =sheet.getRow(1);
        Cell headerCell=sheet.getRow(1).createCell(pesticideNum+1);
        font.setBold(true); // 加粗
        cellStyle.setFont(font);
        headerCell =rowHeader.createCell(pesticideNum+1);
        headerCell.setCellValue("抽样数");
        headerCell.setCellStyle(cellStyle);

        headerCell =rowHeader.createCell(pesticideNum+2);
        headerCell.setCellValue("合格数");
        headerCell.setCellStyle(cellStyle);

        headerCell =rowHeader.createCell(pesticideNum+3);
        headerCell.setCellValue("合格率");
        headerCell.setCellStyle(cellStyle);

        //对农药超标数这个表头加边框
        headerCell=sheet.getRow(2).getCell(2);
        CellStyle headerCellStyle = headerCell.getCellStyle();
        headerCellStyle.setBorderBottom(BorderStyle.THIN); // 薄边框
        headerCellStyle.setBorderLeft(BorderStyle.THIN);// 薄边框
        headerCellStyle.setBorderRight(BorderStyle.THIN); // 双边框
        headerCellStyle.setBorderTop(BorderStyle.THIN);// 薄边框
        font.setBold(false); // 加粗
        cellStyle.setFont(font);
    }
}
