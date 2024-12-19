package com.example.publicspring;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class SearchStrInFileTest {
    public static void main(String[] args) {
        String path = "W:\\work\\work"; // 指定要搜索的目录路径
        String str = "itext";
        searchForPomFiles(new File(path), str);

    }

    private static void searchForPomFiles(File directory, String str) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchForPomFiles(file, str); // 递归搜索子目录
                } else if (file.getName().endsWith("pom.xml")) {
                    checkPomFileForStr(file, str);
                }
            }
        }
    }

    private static void checkPomFileForStr(File pomFile, String str) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pomFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(str)) {
                    System.out.println("Found " + str + " in: " + pomFile.getAbsolutePath());
                    return;
                }
            }
        } catch (IOException e) {
            log.error("Error reading file: " + pomFile.getAbsolutePath(), e);
        }
    }
}
