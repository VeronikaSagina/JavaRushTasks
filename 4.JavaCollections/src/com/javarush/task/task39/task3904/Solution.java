package com.javarush.task.task39.task3904;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static int n = 70;
    private static Map<Integer, Long> map = new HashMap<>();

    static {
        map.put(0, 1L);
        map.put(1, 1L);
        map.put(2, 2L);
        map.put(3, 4L);
    }

    public static void main(String[] args) {
     System.out.println("result " + recursiveCountPossible(7));
    }

    public static long countPossibleRunups(int n) {
        if (n < 0) {
            return 0;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        for (int i = 4; i <= n; i++) {
            if (!map.containsKey(i)) {
                map.put(i, map.get(i - 1) + map.get(i - 2) + map.get(i - 3));
            }
        }
        return map.get(n);
    }

    public static long recursiveCountPossible(int n) {
        if (n < 0) {
            return 0;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        System.out.println(n);
        long result = recursiveCountPossible(n - 1) + recursiveCountPossible(n - 2) + recursiveCountPossible(n - 3);
        map.put(n, result);
        System.out.println(n + " " + result);
        return result;
    }

    public static long countPossible(int n) {
        long b = 1;
        long c = 2;
        long d = 4;
        long e = 0;
        for (int i = 0; i < n - 3; i++) {
            e = b + c + d;
            b = c;
            c = d;
            d = e;
        }
        return e;
    }
}

