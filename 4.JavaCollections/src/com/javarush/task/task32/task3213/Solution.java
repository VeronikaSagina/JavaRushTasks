package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        StringReader readere = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
        System.out.println(decode(readere, 0));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null) {
            return "";
        }
        BufferedReader reader1 = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        String s;
        while ((s = reader1.readLine()) != null) {
            builder.append(s);
        }
        if (key == 0) {
            return builder.toString();
        }
        char[] chars = builder.toString().toCharArray();
        char[] newCh = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int ww = chars[i];
            newCh[i] = (char)(ww + key);
        }
        return new String(newCh);
    }
}
