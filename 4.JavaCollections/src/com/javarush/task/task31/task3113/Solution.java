package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();
        Path path = Paths.get(s);
        if (!Files.isDirectory(path)) {
            System.out.println(path.resolve(path) + " - не папка");
            return;
        }
        AtomicLong countDirectories = new AtomicLong();
        AtomicLong countFiles = new AtomicLong();
        AtomicLong byteOfDirectory = new AtomicLong();
        Files.walk(path)
                .forEach(f -> {
                    if (Files.isDirectory(f)) {
                        countDirectories.getAndIncrement();
                    } else {
                        countFiles.getAndIncrement();
                        try {
                            byteOfDirectory.addAndGet(Files.size(f));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        System.out.println("Всего папок - " + (countDirectories.decrementAndGet()));
        System.out.println("Всего файлов - " + countFiles);
        System.out.println("Общий размер - " + byteOfDirectory);
/*        File file = new File(s);
        Queue<File> fileTree = new PriorityQueue<>();
        Collections.addAll(fileTree, Objects.requireNonNull(file.listFiles()));
        int countDirectories = 0;
        int countFiles = 0;
        long byteOfDirectory = 0;
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, Objects.requireNonNull(currentFile.listFiles()));
                countDirectories++;
            } else {
                countFiles++;
                byteOfDirectory += currentFile.length();
            }
        }
        System.out.println("Всего папок - " + countDirectories);
        System.out.println("Всего файлов - " + countFiles);
        System.out.println("Общий размер - " + byteOfDirectory);
        countDir(path);*/
    }

    private static void countDir(Path path) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            int countDirectories = 0;
            int countFiles = 0;
            long byteOfDirectory = 0;
            for (Path entry : directoryStream) {
                BasicFileAttributes attributes = Files.readAttributes(entry, BasicFileAttributes.class);
                if (attributes.isDirectory()) {
                    countDirectories++;
                } else if (attributes.isRegularFile()) {
                    countFiles++;
                    byteOfDirectory += entry.toFile().length();
                }
            }
            System.out.println("Всего папок - " + countDirectories);
            System.out.println("Всего файлов - " + countFiles);
            System.out.println("Общий размер - " + byteOfDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
