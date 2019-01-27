package com.javarush.task.task22.task2211;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/*
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];//Windows-1251
        String fileName2 = args[1];// UTF-8
        FileInputStream inputStream = new FileInputStream(fileName1);
        FileOutputStream outputStream = new FileOutputStream(fileName2);
        Charset windows1251 = Charset.forName("Windows-1251");
        Charset koi8 = Charset.forName("UTF-8");
        byte[] buffer = new byte[4000];
        while (inputStream.available() > 0) {
            int read = inputStream.read(buffer);
        }
        String s = new String(buffer, windows1251);
        buffer = s.getBytes(koi8);
        outputStream.write(buffer);
        inputStream.close();
        outputStream.close();
    }
}
