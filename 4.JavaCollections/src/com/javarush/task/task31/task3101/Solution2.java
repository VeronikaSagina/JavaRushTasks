package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution2 {


    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("D:\\temp");
        Path newFile = Paths.get("D:\\temp\\allFilesContent.txt");

        Set<Path> set = Files.walk(folder)
                .filter(path -> {
                    try {
                        return Files.size(path) <= 50;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .collect(Collectors.toSet());

        try (OutputStream outputStream = Files.newOutputStream(newFile)) {
            Files.walk(folder)
                    .filter(path -> !path.equals(newFile ))
                    .filter(path -> {
                        try {
                            return Files.size(path) <= 50;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return false;
                        }
                    })
                    .sorted(Comparator.comparing(Path::getFileName))
                    .map(Path::toFile)
                    .forEach(file -> copyFileContent(outputStream, file));
        }
    }

    private static void copyFileContent(OutputStream outputStream, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            outputStream.write(bytes);
            outputStream.write("\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
