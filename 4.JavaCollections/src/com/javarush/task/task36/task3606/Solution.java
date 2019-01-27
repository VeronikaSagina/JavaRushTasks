package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
/*
        Solution solution = new Solution(
                Solution.class.getProtectionDomain().getCodeSource()
                        .getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
*/
        Solution solution = new Solution("D:\\temp");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("Mu"));
/*
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
*/
    }

    public void scanFileSystem() throws ClassNotFoundException {
        MyLoader loader = new MyLoader(ClassLoader.getSystemClassLoader());
        final File fileFolder = new File(packageName);
        for (final File fileEntry : Objects.requireNonNull(fileFolder.listFiles())) {
            if (!fileEntry.getName().endsWith(".class")) {
                continue;
            }
            hiddenClasses.add(loader.load(Paths.get(fileEntry.getAbsolutePath())));
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        key = key.toLowerCase();
        for (Class clazz : hiddenClasses) {
            String className = clazz.getSimpleName().toLowerCase();
            if (className.startsWith(key)) {
                try {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    if (constructor.newInstance() instanceof HiddenClass) {
                        return (HiddenClass) constructor.newInstance();
                    }
                } catch (InstantiationException | IllegalAccessException |
                        NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private class MyLoader extends ClassLoader {
        MyLoader(ClassLoader parent) {
            super(parent);
        }

        Class<?> load(Path path) {
            try {
                byte[] b = Files.readAllBytes(path);
                return defineClass(null, b, 0, b.length); //here main magic
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

