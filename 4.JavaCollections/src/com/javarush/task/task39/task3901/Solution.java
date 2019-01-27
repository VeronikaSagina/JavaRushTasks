package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        int currentLength = 0;
        Queue<Character> stringAnalise = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (!stringAnalise.contains(ch)) {
                stringAnalise.add(ch);
                currentLength++;
            } else {
                if (maxLength < currentLength) {
                    maxLength = currentLength;
                }
                while (true) {
                    char extracted = stringAnalise.poll();
                    if (ch == extracted) {
                        break;
                    }
                }
                stringAnalise.add(ch);
                currentLength = stringAnalise.size();
            }
        }
        if (maxLength < currentLength) {
            maxLength = currentLength;
        }
        return maxLength;
    }
}
