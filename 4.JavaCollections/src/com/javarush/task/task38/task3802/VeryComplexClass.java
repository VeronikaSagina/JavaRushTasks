package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/


import java.io.BufferedReader;
import java.io.FileReader;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("D://temp//test.txt"));
        while (reader.ready()){
            System.out.println(reader.readLine());
        }
    }

    public static void main(String[] args) {
        VeryComplexClass complexClass = new VeryComplexClass();
        try {
            complexClass.veryComplexMethod();
            System.exit(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
