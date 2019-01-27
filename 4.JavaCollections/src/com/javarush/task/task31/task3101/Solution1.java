package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution1 extends SimpleFileVisitor<Path> {
    private Set<Path> set = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        if (Files.size(path) <= 50) {
            set.add(path);
        }
        return super.visitFile(path, attrs);
    }

    public static void main(String[] args) throws IOException {
//        File fileForRename = new File(args[1]);
        File folder = new File("D:\\temp");
        File newFile = new File("D:\\temp\\allFilesContent.txt");
//
//        final String pathname = fileForRename.getParent() + File.separator + "allFilesContent.txt";
//        FileUtils.renameFile(fileForRename, newFile);

        Solution1 solution1 = new Solution1();

        Files.walkFileTree(folder.toPath(), solution1);

        try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
            solution1.set.stream()
                    .filter(path -> !"allFilesContent.txt".equals(path.getFileName().toString()))
                    .sorted(Comparator.comparing(Path::getFileName))
                    .map(Path::toFile)
                    .forEach(file -> copyFileContent(fileOutputStream, file));
        }
    }

    private static void copyFileContent(FileOutputStream fileOutputStream, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            fileOutputStream.write(bytes);
            fileOutputStream.write("\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}