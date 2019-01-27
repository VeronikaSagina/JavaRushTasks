package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuilder builder = readFile(new File(args[0]));
        TreeSet<String> treeSet = getTreeSet(builder);
        print(treeSet);
    }

    private static TreeSet<String> getTreeSet(StringBuilder builder) {
        TreeSet<String> treeSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < builder.length(); i++) {
            char charAt = builder.charAt(i);
            if (!Character.isLetter(charAt)) {
                continue;
            }
            if (!treeSet.contains(String.valueOf(Character.toUpperCase(charAt))) &&
                    !treeSet.contains(String.valueOf(Character.toLowerCase(charAt)))) {
                treeSet.add(String.valueOf(charAt));
            }
        }
        return treeSet;
    }

    private static void print(TreeSet<String> treeSet) {
        Iterator<String> iterator = treeSet.iterator();
        if (treeSet.size() <= 5) {
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
            }
        } else {
            for (int i = 0; i < 5; i++) {
                System.out.print(iterator.next());
            }
        }
    }

    private static StringBuilder readFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                builder.append(reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }
}
