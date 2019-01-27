package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/* 
Обращенные слова
*/
public class Solution {
    public static Set<Pair> result = new LinkedHashSet<>();

    public static void main(String[] args) {
/*        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()))) {
            List<String> list = new ArrayList<>();
            while (fileReader.ready()) {
                String[] strings = fileReader.readLine().split(" ");
                Collections.addAll(list, strings);
            }
            for (int i = 0; i < list.size(); i++) {
                StringBuilder stringBuilder = new StringBuilder(list.get(i));
                for (int j = i + 1; j < list.size(); j++) {
                    if (stringBuilder.reverse().toString().equals(list.get(j))) {
                        Pair pair = new Pair(stringBuilder.reverse().toString(), list.get(j));
                        if (!result.contains(pair)) {
                            result.add(pair);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        vrf();
        for (Pair pair : result) {
            System.out.println(pair);
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

    private static void vrf() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()))) {
            Set<String> set = new HashSet<>();
            while (fileReader.ready()) {
                String[] s = fileReader.readLine().split(" ");
                for (String str : s) {
                    String sb = new StringBuilder(str).reverse().toString();
                    if (set.contains(sb)) {
                            result.add(new Pair(sb, str));
                    }
                    set.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
