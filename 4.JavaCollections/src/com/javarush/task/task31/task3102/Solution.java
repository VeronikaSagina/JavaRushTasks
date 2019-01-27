package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File rootDirectory = new File(root);
        List<String> result = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();
        Collections.addAll(fileTree, rootDirectory.listFiles());
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, currentFile.listFiles());
            } else {
                result.add(currentFile.getAbsolutePath());
            }
        }
        return result;

    }

    public static void main(String[] args) {
        List<Integer>list = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        final Integer max = Collections.max(list);
        Collections.swap(list, 3, 7);
        String s = "tt";
        final List<String> strings = Collections.nCopies(4, s);
        for(Integer integer : list){
            System.out.print(integer);
        }
        System.out.println("\n" + strings);
        System.out.println(max);
        System.out.println(Collections.frequency(list, 20));
    }
}
