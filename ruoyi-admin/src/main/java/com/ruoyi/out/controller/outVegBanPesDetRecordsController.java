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
import com.ruoyi.detection.domain.agriCitySampleTestDetails;
import com.ruoyi.out.domain.outReturnType;
import com.ruoyi.out.domain.vo.OutPesDetRecordsVo;
import com.ruoyi.out.service.IOutPesDetRecordsService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.out.domain.outVegBanPesDetRecords;
import com.ruoyi.out.service.IoutVegPesDetRecordsService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蔬菜禁用农药检出及超标情况Controller
 * 
 * @author chenjie
 * @date 2024-01-27
 */
@RestController
@RequestMapping("/out/outVegBanPesDetRecords")
public class outVegBanPesDetRecordsController extends BaseController
{
    @Autowired
    private IoutVegPesDetRecordsService outVegBanPesDetRecordsService;
    @Autowired
    private IOutPesDetRecordsService OutPesDetRecordsService;



    /**
     * 华创原来的写法
     * 查询蔬菜禁用农药检出及超标情况列表
     */
//    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
//    {
//        startPage();
//        StringBuilder feedBackMsg = new StringBuilder();
//        List<outReturnType> list = outVegBanPesDetRecordsService.selectoutVegPesDetRecordsList(agriCitySampleTestDetails,"禁用",feedBackMsg);
//        TableDataInfo result = getDataTable(list);
//        result.setMsg(feedBackMsg.toString());
//        return result;
//    }

    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        StringBuilder feedBackMsg = new StringBuilder();
        List<OutPesDetRecordsVo> list = OutPesDetRecordsService.selectOutPesDetRecords(agriCitySampleTestDetails,feedBackMsg,"蔬菜","0");
        TableDataInfo result = getDataTable(list);
        result.setMsg(feedBackMsg.toString());
        return result;
    }


    /**
     * 导出蔬菜禁用农药检出及超标情况列表
     */
    @PreAuthorize("@ss.hasPermi('out:outVegBanPesDetRecords:export')")
    @Log(title = "蔬菜禁用农药检出及超标情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response,agriCitySampleTestDetails agriCitySampleTestDetails)
    {
        //华创原来写法的list，这边不用了，用新的
        //List<outReturnType> list = outVegBanPesDetRecordsService.selectoutVegPesDetRecordsList(agriCitySampleTestDetails,"禁用",new StringBuilder());
        StringBuilder feedBackMsg = new StringBuilder();
        List<OutPesDetRecordsVo> list = OutPesDetRecordsService.selectOutPesDetRecords(agriCitySampleTestDetails,feedBackMsg,"蔬菜","0");
        TemplateExportParams params = new TemplateExportParams("excelOutTemplate/outFruBanPesDetRecords.xlsx");
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "1.蔬菜禁用农药检出及超标情况");
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

}
