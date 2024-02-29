import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.out.domain.outHighRiskVarietyDet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Export1test {


    @Test
    public void test(){
        TemplateExportParams params = new TemplateExportParams("/Users/sunshuai/develop/aaaa/agri_quality_detect/ruoyi-admin/src/main/java/com/ruoyi/excelOutTemplate/high1.xlsx");
        List<outHighRiskVarietyDet> res = new ArrayList<outHighRiskVarietyDet>();
        res.add(new outHighRiskVarietyDet(
                "玉林市",
                "玉林AAA检测站",
                "西瓜",
                (long)20,
                (long)10,
                new BigDecimal(50),
                "农药A,\n农药B",
                "农药A:12,\n农药B:444"));
        res.add(new outHighRiskVarietyDet(
                "玉林市",
                "玉林AAA检测站",
                "香蕉",
                (long)20,
                (long)10,
                new BigDecimal(50),
                "农药A,\n农药B农药A,\n农药B农药A,\n农药B",
                "农药A:12,\n农药B:444"));
        res.add(new outHighRiskVarietyDet(
                "玉林市",
                "玉林AAA检测站",
                "西瓜",
                (long)20,
                (long)10,
                new BigDecimal(50),
                "农药A,\n农药B",
                "农药A:12,\n农药B:444"));
        res.add(new outHighRiskVarietyDet(
                "玉林市",
                "玉林BB检测站",
                "西瓜",
                (long)20,
                (long)10,
                new BigDecimal(50),
                "农药A,\n农药B",
                "农药A:12,\n农药B:444"));
        res.add(new outHighRiskVarietyDet(
                "玉林市",
                "玉林BB检测站",
                "西瓜",
                (long)20,
                (long)10,
                new BigDecimal(50),
                "农药A,\n农药B",
                "农药A:12,\n农药B:444"));
        res.add(new outHighRiskVarietyDet(
                "玉林市",
                "玉林CCC检测站",
                "西瓜",
                (long)20,
                (long)10,
                new BigDecimal(50),
                "农药A,\n农药B",
                "农药A:12,\n农药B:444"));
        res.add(new outHighRiskVarietyDet(
                "玉林市",
                "玉林DD检测站",
                "西瓜",
                (long)20,
                (long)10,
                new BigDecimal(50),
                "农药A,\n农药B",
                "农药A:12,\n农药B:444"));
        res.add(new outHighRiskVarietyDet(
                "玉林市",
                "玉林DD检测站",
                "西瓜",
                (long)20,
                (long)10,
                new BigDecimal(50),
                "农药A,\n农药B",
                "农药A:12,\n农药B:444"));
        Map<String, Object> map = new HashMap<>();
        map.put("maplist", res);
        try {
            // 生成 Excel 数据
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Workbook workbook = ExcelExportUtil.exportExcel(params, map);
            workbook.write(outputStream);

            // 读取 Excel 数据
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // 获取单元格内容，并将相同内容的单元格合并
            mergeCells(sheet);

            //workbook.write(response.getOutputStream());
            // 保存修改后的 Excel 数据
            ByteArrayOutputStream modifiedOutputStream = new ByteArrayOutputStream();
            workbook.write(modifiedOutputStream);
            // 保存修改后的 Excel 数据到文件
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/sunshuai/Downloads/out.xlsx");
            modifiedOutputStream.writeTo(fileOutputStream);
            fileOutputStream.close();

            // 最后关闭相关的流
            workbook.close();
            outputStream.close();
            modifiedOutputStream.close();

            System.out.println("Excel 合并完成。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mergeCells(Sheet sheet) {
        // 遍历每一行
        int rowStartA = 3;
        int endRowA = rowStartA;
        int rowStartB = 3;
        int endRowB = rowStartB;
        for (int rowIndex = 4; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                continue; // 跳过空行
            }

            Cell cellA = row.getCell(0);
            Cell cellB = row.getCell(1);
            String valueA = cellA.getStringCellValue();
            String valueB = cellB.getStringCellValue();

            // 从当前行的上一行
            Row preRow = sheet.getRow(rowIndex-1);
            if (preRow == null) {
                continue; // 跳过空行
            }

            Cell preCellA = preRow.getCell(0);
            Cell preCellB = preRow.getCell(1);
            String preValueA = preCellA.getStringCellValue();
            String preValueB = preCellB.getStringCellValue();

            if (valueA.equals(preValueA) ) {
                endRowA++;
            } else {

                if(rowStartA != endRowA){
                    System.out.println(rowStartA+"A合并"+endRowA);
                    CellRangeAddress range = new CellRangeAddress(rowStartA, endRowA, 0, 0);
                    sheet.addMergedRegion(range);
                }
                rowStartA = rowIndex;
                endRowA = rowStartA;
            }

            if (valueB.equals(preValueB) ) {
                endRowB++;
            } else {
                if(rowStartB != endRowB){
                    System.out.println(rowStartB+"B合并"+endRowB);
                    CellRangeAddress range = new CellRangeAddress(rowStartB, endRowB, 1, 1);
                    sheet.addMergedRegion(range);
                }
                rowStartB = rowIndex;
                endRowB = rowStartB;
            }


        }

        if(rowStartA != endRowA){
            CellRangeAddress range = new CellRangeAddress(rowStartA, endRowA, 0, 0);
            sheet.addMergedRegion(range);
        }
        if(rowStartB != endRowB){
            CellRangeAddress range = new CellRangeAddress(rowStartB, endRowB, 1, 1);
            sheet.addMergedRegion(range);
        }
    }
}
