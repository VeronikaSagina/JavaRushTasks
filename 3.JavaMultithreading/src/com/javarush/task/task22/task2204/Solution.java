package com.javarush.task.task22.task2204;

/* 
Форматирование строки
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(String.format(getFormattedString(), 20.0 / 7.0, 10.0 / 3.0).replace('.', ','));
        //должен быть вывод
        //20 / 7 = 2,86
        //Exp = 3,33e+00
    }

    public static String getFormattedString() {
        String s = "20 / 7 = %.2f%n";
        String a = "Exp = %.2e";
        return s + a;
    }
}
