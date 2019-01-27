package com.javarush.task.task29.task2907;

import java.math.BigDecimal;

/* 
Этот странный BigDecimal
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getValue(1.1d, 1.2d));
        System.out.println(new BigDecimal(1.0));
    }

    public static BigDecimal getValue(double v1, double v2) {
     BigDecimal decimal1 = new BigDecimal(String.valueOf(v1));
     BigDecimal decimal2 = new BigDecimal(String.valueOf(v2));
     return decimal1.add(decimal2);
    }
}