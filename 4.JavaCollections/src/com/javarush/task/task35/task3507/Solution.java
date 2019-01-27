package com.javarush.task.task35.task3507;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(
                Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                        Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");

        System.out.println(allAnimals);
    }

/*    public static Set<? extends Animal> getAllAnimals(String pathToAnimals){
        Set<Animal> set = new HashSet<>();
        File[] list = new File(pathToAnimals).listFiles();
        assert list != null;
        for(File file : list){
            if (file.isFile() && file.getName().endsWith(".class")){
                String packageName = Solution.class.getPackage().getName() + ".data";
            }
        }
    }*/
    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> animalsSet = new HashSet<>();
        List<Class> classes = getClasses(pathToAnimals);
        for (Class clazz : classes) {
            try {
                if (clazz.getConstructor().newInstance() instanceof Animal) {
                    Animal animal = (Animal) clazz.getConstructor().newInstance();
                    animalsSet.add(animal);
                }
            } catch (NoSuchMethodException | IllegalAccessException |
                    InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
                continue;
            }
        }
        return animalsSet;
    }


    private static List<Class> getClasses(String pathToAnimals) {
        List<Class> classes = new ArrayList<>();
        ClassLoader classLoader = AnimalLoader.class.getClassLoader();
        AnimalLoader animalLoader = new AnimalLoader(classLoader);
        String packageName = Solution.class.getPackage().getName() + ".data"; //some bed solution (Hardcore data)
        try {
            final File fileFolder = new File(pathToAnimals);
            for (final File fileEntry : Objects.requireNonNull(fileFolder.listFiles())) {
                if (!fileEntry.getName().endsWith(".class")) {
                    continue;
                }
                Class clazz = animalLoader.load(Paths.get(fileEntry.getAbsolutePath()), packageName);
                classes.add(clazz);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return classes;
    }
}
