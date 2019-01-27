package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
        try {
            String letter = args[0];
            int result = trt(letter);
            if (result == -1) {
                System.out.println("incorrect");
            } else {
                System.out.println(result);
            }
        }catch (Exception e){
            //
        }
    }

    private static int trt(String str) {
        for (int i = 2; i <= 36; i++) {
            try {
                BigInteger integer = new BigInteger(str, i);
                return i;
            } catch (Exception e) {//
            }
        }
        return -1;
    }
}