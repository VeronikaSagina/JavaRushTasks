package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        byte[] bytes = text.getBytes();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");
            if (randomAccessFile.length() < number) {
                randomAccessFile.seek(randomAccessFile.length());
            } else {
                randomAccessFile.seek(number);
            }
            randomAccessFile.write(bytes);
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
