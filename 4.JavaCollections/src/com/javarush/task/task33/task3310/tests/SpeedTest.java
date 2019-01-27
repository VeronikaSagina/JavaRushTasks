package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        long startTime = new Date().getTime();
        for (String str : strings) {
            ids.add(shortener.getId(str));
        }
        long endTime = new Date().getTime();
        return endTime - startTime;
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        long startTime = new Date().getTime();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        long endTime = new Date().getTime();
        return endTime - startTime;
    }

    @Test
    public void testHashMapStorage(){
        HashMapStorageStrategy hms = new HashMapStorageStrategy();
        HashBiMapStorageStrategy hbms = new HashBiMapStorageStrategy();
        Shortener shortener1 = new Shortener(hms);
        Shortener shortener2 = new Shortener(hbms);

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids = new HashSet<>();
        long timeForHashMapIds = getTimeForGettingIds(shortener1, origStrings, ids);

        Set<Long> idBi = new HashSet<>();
        long timeForHashBiMapIds = getTimeForGettingIds(shortener2, origStrings, idBi);

        Assert.assertTrue(timeForHashBiMapIds < timeForHashMapIds);

        long timeForHashStr = getTimeForGettingStrings(shortener1, ids, new HashSet<>());
        long timeForHashBiStr = getTimeForGettingStrings(shortener2, idBi, new HashSet<>());

        Assert.assertEquals(timeForHashBiStr, timeForHashStr, 30);
    }
}
