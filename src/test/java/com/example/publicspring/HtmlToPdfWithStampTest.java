package com.example.publicspring;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlToPdfWithStampTest {
    public static void main(String[] args) {
        try {
            // 转换后的PDF文件路径
            String inputPdfPath = "path/to/your/input.pdf";
            // 输出添加印章后的PDF文件路径
            String outputPdfPath = "path/to/your/output.pdf";
            // 印章图片路径
            String stampImagePath = "path/to/your/stamp.png";

            // 创建PdfReader对象读取原PDF文件
            PdfReader reader = new PdfReader(inputPdfPath);
            // 创建PdfStamper对象用于盖章（会创建新的PDF文件）
            PdfStamper stamper = new PdfStamper(reader, Files.newOutputStream(Paths.get(outputPdfPath)));

            // 获取印章图片对象
            com.itextpdf.text.Image stampImage = Image.getInstance(stampImagePath);
            // 设置印章图片的位置和大小（这里示例放置在页面右下角，可根据需求调整）
            float x = reader.getPageSize(1).getWidth() - stampImage.getScaledWidth() - 20;
            float y = reader.getPageSize(1).getHeight() - stampImage.getScaledHeight() - 20;

            // 循环遍历每一页添加印章
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                // 获取每一页的内容层
                PdfContentByte overContent = stamper.getOverContent(i);
                // 设置印章图片位置、大小等参数后添加到页面上
                stampImage.setAbsolutePosition(x, y);
                overContent.addImage(stampImage);
            }

            // 关闭资源
            stamper.close();
            reader.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
