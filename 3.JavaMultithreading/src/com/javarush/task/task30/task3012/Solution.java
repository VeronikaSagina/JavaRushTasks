package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;


public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
        solution.createExpression(1234);
        solution.createExpression(2);
        double d = Double.POSITIVE_INFINITY;
        double q = Double.POSITIVE_INFINITY;
        double e = Double.NEGATIVE_INFINITY;
        double w = Double.NEGATIVE_INFINITY;
        System.out.println(d + e);
        System.out.println(d - q);
        System.out.println(e - w);
        System.out.println(e + w);
        System.out.println(0 * d);
        System.out.println(0 * w);
    }

    public void createExpression(int number) {
        int[] list = {1, 3, 9, 27, 81, 243, 729, 2187};
        List<String> remainder = new ArrayList<>();
        int a;
        int b;
        int num2 = number;
        while (num2 > 0) {
            a = num2 / 3;
            b = num2 % 3;
            if (b == 0) {
                remainder.add("0");
            } else if (b == 2) {
                remainder.add("-");
                a += 1;
            } else {
                remainder.add("+");
            }
            num2 = a;
        }
        StringBuilder s = new StringBuilder(number + " =");
        for (int i = 0; i < remainder.size(); i++) {
            String getS = remainder.get(i);
            if (getS.equals("0")) {
                continue;
            }
            if (getS.equals("-")) {
                s.append(" - ").append(list[i]);
            } else {
                s.append(" + ").append(list[i]);
            }
        }
        System.out.println(s.toString().trim());
    }
}