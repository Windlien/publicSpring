package com.example.publicspring;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class PublicSpringApplicationTests {

    @Test
    void contextLoads() {

    }
    public static void main(String[] args) throws IOException {
        String n = "123456.78901";
        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = wb.createSheet();
        sheet.setDefaultColumnWidth(20);
        DataFormat format = wb.createDataFormat();
        String[] formats = BuiltinFormats.getAll();
        for (int i = 0; i < formats.length; i++) {
            String f = formats[i];
            System.out.println(f);
            CellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.RIGHT);
            style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
            style.setDataFormat(format.getFormat(f));
            SXSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(n);
            SXSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(Double.valueOf(n));
            cell1.setCellStyle(style);
            row.createCell(2).setCellValue(f);
        }
        File file = new File("C:\\Users\\CF\\Downloads\\aa.xlsx");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
        fos.flush();
        fos.close();
        wb.close();
        System.out.println("结束");
    }
}
