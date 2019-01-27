package com.javarush.task.task37.task3714;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Stream;

/* 
Древний Рим
*/
public class Solution {
    private static HashMap<String, Integer> map = new HashMap<>();

    static {
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
    }

    public static void main(String[] args) throws IOException {
/*
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));

*/
        Stream.of("abc", "abcd")
                .flatMap(s -> Stream.of(s.split("")))
                .forEach(System.out::println);
    }

    private static int romanToInteger(String s) {
        String[] arrSt = s.split("");
        int result = 0;
        int count = 0;
        for (int i = 0; i < arrSt.length - 1; i++) {
            count++;
            int first = map.get(arrSt[i]);
            int sec = map.get(arrSt[i + 1]);
            if (first < sec) {
                result += sec - first;
                if (i + 1 < arrSt.length) {
                    i++;
                    count++;
                }
            } else {
                result += first;
            }
        }
        if (count < arrSt.length) {
            result += map.get(arrSt[arrSt.length - 1]);
        }
        return result;
    }
}
