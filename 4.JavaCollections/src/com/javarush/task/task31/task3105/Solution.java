package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path fileName = Paths.get(args[0]);
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(args[1]));
        Map<String, ByteArrayOutputStream> map = new LinkedHashMap<>();
        unZip(map, zipInputStream);

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(args[1]));
        ZipEntry zipEntry = new ZipEntry("new\\" + fileName);
        for (Map.Entry<String, ByteArrayOutputStream> entry : map.entrySet()) {
            ZipEntry zipEntry1 = new ZipEntry(entry.getKey());
            zipOutputStream.putNextEntry(zipEntry1);
            zipOutputStream.write(entry.getValue().toByteArray());
        }
        zipOutputStream.putNextEntry(zipEntry);
        Files.copy(fileName, zipOutputStream);
        zipInputStream.close();
        zipOutputStream.closeEntry();
        zipOutputStream.close();
    }

    private static void unZip(Map<String, ByteArrayOutputStream> map, ZipInputStream zipInputStream) {
        try {
            String nextFile;
            ZipEntry zipEntry;
            int length;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                nextFile = zipEntry.getName();
                byte[] bytes = new byte[4024];
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                while ((length = zipInputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, length);
                }
                map.put(nextFile, outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class vera {
    public static void main(String[] args) {
        List<FileInputStream> list = new ArrayList<>();
        try {
            list.add(new FileInputStream("D:/pathToTest/test.zip.003"));
            list.add(new FileInputStream("D:/pathToTest/test.zip.001"));
            list.add(new FileInputStream("D:/pathToTest/test.zip.004"));
            list.add(new FileInputStream("D:/pathToTest/test.zip.002"));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}

