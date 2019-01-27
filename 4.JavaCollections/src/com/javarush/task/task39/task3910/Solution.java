package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
    }

/*    public static boolean isPowerOfThree2(int n) {
        if (n <= 0){
            return false;
        }
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n = n / 3;
        }
        return true;
    }*/

    public static boolean isPowerOfThree(int n){
        return Integer
                .toString(n, 3)
                .matches("1(0)*");
    }
}
