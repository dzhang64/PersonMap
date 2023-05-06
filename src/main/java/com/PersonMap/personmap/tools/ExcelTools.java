package com.PersonMap.personmap.tools;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class ExcelTools {

    public static List<List<String>> readExcel(String path) {
        List<List<String>> result = new ArrayList<>();
        // 读取Excel文件
        try {
            InputStream inputStream = new FileInputStream(new File("/res/xls/1.xls"));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                List<String> rowList = new ArrayList<>();
                for (Cell cell : row) {
                    String value = cell.getStringCellValue();
                    rowList.add(value);
                }
                result.add(rowList);
            }
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
