package com.karakal.excel.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.karakal.excel.annotation.Excel;
import com.karakal.excel.bean.ExcelSort;
import com.karakal.exception.BusinessException;
import com.karakal.util.LogUtil;

/**
 * excel文件上传分析与导出需要用到easypoi excel处理工具 ClassName: ExcelUtil <br/>
 * @author xt(di.xiao@karakal.com.cn)
 * @date 2016年10月21日
 * @version 1.0
 */
public class ExcelUtil {

    /**
     * 将excel文件输出流输出到response
     * @param list 输入的数据<List<map<String,String>>>
     * @param excelSorts excel文件 header List<ExcelSort> excelSort格式：{"key":"歌曲ID","value":"songId"}
     * @param fileName 文件名
     * @param response
     */
    public static void export(List<Map<String, String>> list, List<ExcelSort> excelSorts, String fileName, HttpServletResponse response) {
        @SuppressWarnings("resource")
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        /**
         * 设置表头样式
         */
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建居中格式
        HSSFRow row = sheet.createRow(0);
        int j = 0;
        HSSFCell cell;
        for (ExcelSort excelSort : excelSorts) {
            cell = row.createCell(j++);
            cell.setCellValue(excelSort.getKey());
            cell.setCellStyle(style);
        }
        /**
         * 设置数据样式
         */
        HSSFCellStyle style_color = wb.createCellStyle();
        style_color.setFillForegroundColor(HSSFColor.GREEN.index);
        /**
         * 设置字体样式
         */
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style_color.setFont(font);
        int i = 1;
        for (Map<String, String> map : list) {
            HSSFRow dataRow = sheet.createRow(i++);
            HSSFCell dataCell;
            int m = 0;
            for (ExcelSort excelSort : excelSorts) {
                dataCell = dataRow.createCell(m++);
                dataCell.setCellValue(String.valueOf(map.get(excelSort.getValue())));
                dataCell.setCellStyle(style_color);
                sheet.autoSizeColumn(m);// 宽度自适应
            }
        }
        /**
         * 输出到response
         */
        OutputStream out;
        try {
            out = response.getOutputStream();
            response.setContentType("application/x-download");
            // 设置导出文件名称
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
            wb.write(out);
        } catch (IOException e) {
            LogUtil.LOGEXCEPTION(e);
        }
    }

    /**
     * 解析excel文件 泛型T所对应的class必须与ExcelRequestBean.java定义一致
     * @param clazz
     * @param file
     * @return
     */
    public static <T> List<T> elxFile(MultipartFile file, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        try {
            Field[] fileds = clazz.getDeclaredFields();// 得到所有的字段，但是父类字段没有
            Workbook wkbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = wkbook.getSheetAt(0);// 读取sheet页
            int headerRow = -1;// 确定表头在那一行
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) { // 读取行
                Row row = sheet.getRow(i);
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) { // 读取列
                    Boolean isFoundHeader = false;
                    Cell cell = row.getCell(j);
                    for (Field field : fileds) {
                        Excel excle = field.getAnnotation(Excel.class);
                        if (headerRow == -1) {
                            if (excle.value().equals(String.valueOf(cell))) {
                                headerRow = i;// 确定表头在那一行
                                isFoundHeader = true;
                                break;
                            }
                        } else {
                            if (excle.value().equals(String.valueOf(sheet.getRow(headerRow).getCell(j)))) {
                                T object = (T) clazz.newInstance();
                                field.setAccessible(true);
                                field.set(object, (long) cell.getNumericCellValue());
                                list.add(object);
                                break;
                            }
                        }
                    }
                    if (isFoundHeader) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return list;
    }
}
