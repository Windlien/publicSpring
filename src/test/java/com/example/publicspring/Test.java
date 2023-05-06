package com.example.publicspring;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        moveFiles();
        //delDirNotEndWith();
    }

    private static void extracted() {
        List<Integer> list = Arrays.asList(2, 3, 1, 5);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println("1："+list);

        list = Arrays.asList(2, 3, 1, 5);
        list.sort(((i1,i2)-> i1 - i2));
        System.out.println("2："+list);

        list = Arrays.asList(2, 3, 1, 5);
        list.sort(Comparator.comparingInt(i -> i));
        System.out.println("2："+list);
        list.forEach(i -> System.out.print("元素："+i));
        //实例3：把Lambda赋值给变量
        Runnable task = ()->{
            System.out.println("\nhello lambda!");
        };
        new Thread(task).start();
    }

    public  void main1(String[] args) {
        String s1 = "111111111111111111111111111";
        int i1 = 1000000000;
        int i = 1;
        List<String> list1 = new ArrayList<>();
        while (i < i1) {
            i++;
            list1.add(s1);
            System.out.println("list1个数" + list1.size());
        }
        list1.forEach(System.out::println);
    }
    public static void moveFiles() throws IOException, InterruptedException {
        String srcDir = "";
        String destDir = "";
        Collection<File> srcFiles = FileUtils.listFiles(new File(srcDir),null,true).stream().sorted().collect(Collectors.toList());
        for (File srcFfile : srcFiles) {
            if (srcFfile.exists()&&srcFfile.getName().toUpperCase().endsWith(".MP4")) {
                File destFile = new File(destDir+ File.separator+srcFfile.getName());
                if (destFile.exists()) {
                    destFile.delete();
                    FileUtils.copyFileToDirectory(srcFfile,new File(destDir),true);
                }else {
                    System.out.println(srcFfile.getName());
                    FileUtils.copyFileToDirectory(srcFfile,new File(destDir),true);
                }
                Thread.sleep(300);
            }
        }
        System.out.println("结束");
    }
    public static void delDirNotEndWith() throws IOException {
        String srcDir = "";
        String suffix=".mp4";
        File[] srcD = new File(srcDir).listFiles();
        for (File file : srcD) {
            Collection<File> files = FileUtils.listFiles(file, new String[]{suffix}, false);
            if (files.isEmpty()){
                System.out.println(file.getName());
                FileUtils.deleteDirectory(file);
            }
        }
    }
}
