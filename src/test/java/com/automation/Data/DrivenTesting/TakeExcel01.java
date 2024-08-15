package com.automation.Data.DrivenTesting;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class TakeExcel01 {
    public static void main (String[] args) throws IOException {

         FileInputStream inputStream = new FileInputStream("src/test/resources/DATA.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet Sheet1 = workbook.getSheetAt(0);


        Iterator<Row> rowIterator = (Iterator<Row>) Sheet1.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();


            Iterator<Cell> cellIterator = (Iterator<Cell>) row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType() == CellType.NUMERIC) {
                    System.out.println(cell.getNumericCellValue());
                }
                if (cell.getCellType() == CellType.STRING) {
                    System.out.println(cell.getStringCellValue());
                }
            }
        }
    }
}




