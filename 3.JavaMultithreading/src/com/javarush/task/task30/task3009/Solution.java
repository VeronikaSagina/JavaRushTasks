package com.javarush.task.task30.task3009;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        int original;
        try {
            original = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return Collections.emptySet();
        }
        Set<Integer> setRadix = new HashSet<>();

        for (int i = 2; i <= 36; i++) {
            ArrayList<Integer> digits = new ArrayList<>();
            int copy = original;
            while (copy > 0) {
                int digit = copy % i;
                digits.add(digit);
                copy = copy / i;
            }
            Collections.reverse(digits);
            if (isPalindrome(digits)) {
                setRadix.add(i);
            }
        }
        return setRadix;
    }

    public static boolean isPalindrome(ArrayList<Integer> given) {
        ArrayList<Integer> recd = new ArrayList<>();
        for (int i = given.size() - 1; i >= 0; i--) {
            recd.add(given.get(i));
        }
        return given.equals(recd);
    }
}