package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private int minSize;
    private int maxSize;
    private String partOfName;
    private String partOfContent;
    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContext) {
        this.partOfContent = partOfContext;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (checkFile(file)) {
            foundFiles.add(file);
        }
        return super.visitFile(file, attrs);
    }

    private boolean checkFile(Path file) throws IOException {
        if (partOfName != null) {
            if (!file.getFileName().toString().contains(partOfName)) {
                return false;
            }
        }
        if (maxSize != 0 || minSize != 0) {
            long size = Files.size(file);
            if (size > maxSize || size < minSize) {
                return false;
            }
        }
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        String str = new String(content, "UTF-8");
        //noinspection RedundantIfStatement
        if (partOfContent != null && !str.contains(partOfContent)) {
            return false;
        }
        return true;
    }
}
