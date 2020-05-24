package com.national.raise.web.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;

public class PoiTest {

    public static void main(String[] args) throws Exception {

        Workbook book = new HSSFWorkbook();

        Sheet sheet = book.createSheet();

        Row row = sheet.createRow(1);  //从0开始计数

        Cell cell = row.createCell(1);

        cell.setCellValue("poi写数据成功！！！");

        Font font = book.createFont();

        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 20);

        CellStyle style = book.createCellStyle();

        style.setFont(font);

        cell.setCellStyle(style);

        book.write(new FileOutputStream("e:/poi.xls"));

        book.close();
    }
}
