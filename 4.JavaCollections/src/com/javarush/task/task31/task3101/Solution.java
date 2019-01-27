package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File fileForRename = new File(args[1]);
        File folder = new File(args[0]);
        Set<File> setFiles = new HashSet<>();

        final String pathname = fileForRename.getParent() + File.separator + "allFilesContent.txt";
        File newFile = new File(pathname);
        FileUtils.renameFile(fileForRename, newFile);
        mapPut(folder, setFiles);

       try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
           setFiles.stream()
                   .sorted(Comparator.comparing(File::getName))
                   .forEach(file -> copyFileContent(fileOutputStream, file));
        }
    }

    private static void copyFileContent(FileOutputStream fileOutputStream, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            fileOutputStream.write(bytes);
            fileOutputStream.write("\n".getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void mapPut(File file, Set<File> set) {
        if (file.isDirectory()) {
            for (File file1 : Objects.requireNonNull(file.listFiles())) {
                mapPut(file1, set);
            }
            return;
        }
        if (file.isFile()) {
            if (file.length() > 50) {
                return;
            }
            set.add(file);
        }
//        File[] files1 = file.listFiles();
    }
}