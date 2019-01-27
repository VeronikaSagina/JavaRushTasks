package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        HashMapStorageStrategy storageStrategy = new HashMapStorageStrategy();
        testStrategy(storageStrategy, 10000);
        OurHashMapStorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
        testStrategy(ourHashMapStorageStrategy, 10000);
        FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
        testStrategy(fileStorageStrategy, 100);
        OurHashBiMapStorageStrategy biMapStorageStrategy = new OurHashBiMapStorageStrategy();
        testStrategy(biMapStorageStrategy, 10000);
        HashBiMapStorageStrategy mapStorageStrategy = new HashBiMapStorageStrategy();
        testStrategy(mapStorageStrategy, 10000);
        DualHashBidiMapStorageStrategy dual = new DualHashBidiMapStorageStrategy();
        testStrategy(dual, 10000);
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long entryLong : keys) {
            set.add(shortener.getString(entryLong));
        }
        return set;
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> idSet = new HashSet<>();
        for (String entryStr : strings) {
            idSet.add(shortener.getId(entryStr));
        }
        return idSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> generateStringsSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            generateStringsSet.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Date date = new Date();
        long firstTime = date.getTime();
        Set<Long> ids = getIds(shortener, generateStringsSet);
        long firstTime2 = new Date().getTime();
        long firstResult = firstTime2 - firstTime;
        Helper.printMessage(String.valueOf(firstResult));

        long secondTime = new Date().getTime();
        Set<String> strings = getStrings(shortener, ids);
        long secondTime2 = new Date().getTime();
        long secondResult = secondTime2 - secondTime;
        Helper.printMessage(String.valueOf(secondResult));

        if (generateStringsSet.equals(strings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
