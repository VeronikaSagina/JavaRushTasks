package com.javarush.task.task39.task3908;

import java.util.HashSet;
import java.util.Set;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("aba"));
        System.out.println(isPalindromePermutation("palindrome"));
    }

    public static boolean isPalindromePermutation(String s) {
        s = s.toLowerCase();
        Set<String> strings = new HashSet<>();
       /* Map<String, Integer> map = new HashMap<>();
        map.entrySet().stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() % 2 > 0)
                .count();*/
        for (String str : s.split("")) {
            if (!strings.contains(str)) {
                strings.add(str);
            } else {
                strings.remove(str);
            }
        }
        return strings.size() <= 1;
    }
}
