package com.example.publicspring;

import cn.hutool.core.io.FileUtil;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.security.*;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main1(String[] args) throws IOException, InterruptedException {
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

    public  void main2(String[] args) {
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

    //PDF电子签章
    public static class PDFSigner {
        public static final String KEYSTORE = "path/to/keystore";
        public static final char[] PASSWORD = "password".toCharArray();
        public static final String ALIAS = "alias";
        public static final String CERTIFICATE = "path/to/certificate";

        public static void main(String[] args) throws Exception {
            // 加载密钥库
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(KEYSTORE), PASSWORD);
            PrivateKey pk = (PrivateKey) ks.getKey(ALIAS, PASSWORD);
            Certificate[] chain = ks.getCertificateChain(ALIAS);

            // 创建PdfReader和PdfStamper
            PdfReader reader = new PdfReader("input.pdf");
            FileOutputStream os = new FileOutputStream("output.pdf");
            PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0', null, true);

            // 创建签名区域
            com.itextpdf.text.Rectangle rect = new com.itextpdf.text.Rectangle(36, 648, 144, 780);
            PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
            appearance.setVisibleSignature(rect, 1, "signature");

            // 设置签名属性
//            appearance.setCrypto(null, chain, null, PdfSignatureAppearance.WINCER_SIGNED);
            appearance.setReason("reason");
            appearance.setLocation("location");
            appearance.setSignDate(Calendar.getInstance());

            // 创建外部摘要对象
            ExternalDigest digest = new BouncyCastleDigest();

            // 创建签名对象
//            PrivateKeySignature signature = new PrivateKeySignature(pk, DigestAlgorithms.SHA256, BouncyCastleProvider.PROVIDER_NAME);

            // 对PDF文件进行签名
//            MakeSignature.signDetached(appearance, digest, signature, chain, null, null, 0, MakeSignature.CryptoStandard.CMS);


            // 关闭stamper和reader
            stamper.close();
            reader.close();
        }

        public void addImage(Document document, PdfWriter pdfWriter) throws DocumentException, IOException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            String imageFileName = "SIGN_SEAL_IMAGE_FILE";
            byte[] imageByte = FileUtil.readBytes(new File(imageFileName));

            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(imageByte);
            image.scalePercent(62);

            float x = document.right();

            float currentHeight = currentHeight(pdfWriter);
            float v = x - image.getScaledWidth();
            image.setAbsolutePosition(v, currentHeight - 20f);
            image.setAlignment(com.itextpdf.text.Image.MIDDLE);
            PdfContentByte directContent = pdfWriter.getDirectContent();
            directContent.addImage(image);
        }
    }
    public static float currentHeight(PdfWriter writer) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        Method getPdfDocument = writer.getClass().getDeclaredMethod("getPdfDocument");
        getPdfDocument.setAccessible(true);
        PdfDocument pdfD = (PdfDocument) getPdfDocument.invoke(writer);
        Field getHeight = pdfD.getClass().getDeclaredField("currentHeight");
        getHeight.setAccessible(true);
        Object o = getHeight.get(pdfD);
        return Float.valueOf(o.toString());
    }
    public static void Test2() {
       System.out.println("test2");
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(ints);
    }

}
