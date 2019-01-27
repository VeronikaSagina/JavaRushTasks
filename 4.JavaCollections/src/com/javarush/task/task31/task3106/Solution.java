package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File resultFile = new File(args[0]);
        String[] str = Arrays.copyOfRange(args, 1, args.length);
        Arrays.sort(str);
        List<FileInputStream> fileInputStreamList = new ArrayList<>();
        for (String aStr : str) {
            fileInputStreamList.add(new FileInputStream(aStr));
        }
        try (ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(fileInputStreamList)));
             FileOutputStream outputStream = new FileOutputStream(resultFile)) {
            while (zipInputStream.getNextEntry() != null) {
                streamTransfer(zipInputStream, outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void streamTransfer(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int countOfBytes;
        while ((countOfBytes = in.read(buffer)) > 0) {
            out.write(buffer, 0, countOfBytes);
            out.flush();
        }
    }
}

class readFile {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\temp\\22.txt"));
            List<String> list = new ArrayList<>();
            reader.mark(134);
            while (reader.ready()) {
                list.add(reader.readLine());
            }
            if (reader.markSupported()) {
                List<String> list1 = new ArrayList<>();
                reader.reset();
                while (reader.ready()) {
                    list1.add(reader.readLine());
                }
                System.out.println(list1);
            }
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}