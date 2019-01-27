package com.javarush.task.task32.task3210;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        long number = Long.parseLong(args[1]);
        String text = args[2];
        System.out.println(text);
        byte[] buffer = new byte[text.getBytes().length];
        System.out.println(buffer.length);
        try {
            RandomAccessFile accessFile = new RandomAccessFile(args[0], "rw");
            accessFile.seek(number);
            accessFile.read(buffer, 0, buffer.length);
            String readText = new String(buffer);
            System.out.println(Arrays.toString(buffer));
            System.out.println(readText);
            accessFile.seek(accessFile.length());
            if (text.equals(readText)) {
                accessFile.write("true".getBytes());
            } else {
                accessFile.write("false".getBytes());
            }
            accessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
