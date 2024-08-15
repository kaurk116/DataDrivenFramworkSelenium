package com.automation.Data.DrivenTesting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcel01 {
    public static void main(String[] args) throws IOException {
        //workbook
        //sheet
        //rom cell
        //XSSFWorkBook --xslx -2008
        //HSSFWorkBook -xls -2004

        //Create an  Excel file and create a data

        Map<String, Object> data = new TreeMap<>();
        data.put("1", new Object[]{"LoginId", "Email", "Password"});
        data.put("2", new Object[]{"1000", "admin@admin.com", "Password123"});
        data.put("3", new Object[]{"1001", "admi1n@admin.com", "Password1234"});


        Set<String> keyset = data.keySet();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Main");

        int rownum = 0;
        for (String key : keyset) {
            Row r = sheet.createRow(rownum++);
            Object[] objects = (Object[]) data.get(key);
            int cellnum = 0;

            for (Object o : objects) {
                Cell cell = r.createCell(cellnum++);
                cell.setCellValue(key);
            }
        }

        FileOutputStream outputStream = new FileOutputStream(new File("src/test/resources/CTS.xlsx"));
        System.out.println(outputStream);
        workbook.write(outputStream);
        outputStream.close();
    }
}


