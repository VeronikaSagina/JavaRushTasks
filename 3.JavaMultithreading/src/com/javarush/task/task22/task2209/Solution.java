package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (fileReader.ready()) {
                stringBuilder.append(fileReader.readLine());
            }
            String[] str = stringBuilder.toString().split(" ");
            StringBuilder result = getLine(str);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder getLine(String... words) {

        if (words.length == 0) {
            return new StringBuilder();
        }

        boolean[] used = new boolean[words.length];
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            result.add(words[i]);
            used[i] = true;
            if (hasSolution(words, used, result)) {
                return new StringBuilder(result.stream()
                        .collect(Collectors.joining(" ")));
            }
            result.remove(result.size() - 1);
            used[i] = false;
        }
        return null;
    }

    private static boolean hasSolution(String[] words, boolean[] used, ArrayList<String> result) {
        if (words.length == result.size()) {
            return true;
        }

        final String previous = result.get(result.size() - 1).toLowerCase();
        char endLetter = previous.charAt(previous.length() - 1);

        for (int i = 0; i < words.length; i++) {
            if (endLetter != words[i].toLowerCase().charAt(0) || used[i]) {
                continue;
            }
            result.add(words[i]);
            used[i] = true;

            if (hasSolution(words, used, result)) {
                return true;
            }
            result.remove(result.size() - 1);
            used[i] = false;
        }
        return false;
    }
}
