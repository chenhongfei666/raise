package com.national.raise.web.utils;

import com.google.common.collect.Lists;
import com.national.raise.web.pojo.MonthData;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.List;

public class ExcelUtils {
    public static void main(String[] args) throws Exception {

//        List<MonthData> monthDatas = readExcel();
    }

    public static List<MonthData> readExcel(File file) throws Exception {
        Workbook workbook = WorkbookFactory.create(file);
        List<MonthData> monthDatas = Lists.newArrayList();
        System.out.println("sheets" + workbook.getNumberOfSheets());
        // 获取一张表
        Sheet sheet = workbook.getSheetAt(0);
        // 跳过第一行
        for (int i = 3; i <= sheet.getLastRowNum(); i++) {
            // 取得第i行数据
            Row row = sheet.getRow(i);
            MonthData monthData = new MonthData();
            String[] str = new String[row.getLastCellNum()];
            for (int j = 0; j < row.getLastCellNum(); j++) {
                // 取得第j列数据
                Cell cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                str[j] = cell.getStringCellValue().trim();
                System.out.print(str[j] + " ");
            }
            // 封装对象信息
            monthData.setSpmc(str[0]);
            monthData.setZzbm(str[1]);
            monthData.setJzxm(str[2]);
            monthData.setBbxm(str[3]);
            monthData.setFlzr(str[4]);
            monthData.setSzxz(str[5]);
            monthData.setSzc(str[6]);
            monthData.setYf(str[7]);

            monthDatas.add(monthData);
        }
        return monthDatas;

    }

}
