package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("D:\\temp\\33.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        if (is == null){
            return new StringWriter();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String s;
        StringWriter writer = new StringWriter();
        while ((s = reader.readLine()) != null){
            writer.write(s);
        }
        reader.close();
        return writer;
    }
}