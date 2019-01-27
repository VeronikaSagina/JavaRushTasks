package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String one = "Tests string one and three contains this line.";
        String two = "It's like another line";
        String three = "Tests string one and three contains this line.";
        Long oneId = shortener.getId(one);
        Long twoId = shortener.getId(two);
        Long threeId = shortener.getId(three);
        Assert.assertNotEquals(twoId, oneId);
        Assert.assertNotEquals(twoId, threeId);
        Assert.assertEquals(oneId, threeId);
        String resultOne = shortener.getString(oneId);
        String resultTwo = shortener.getString(twoId);
        String resultThree = shortener.getString(threeId);
        Assert.assertEquals(one, resultOne);
        Assert.assertEquals(two, resultTwo);
        Assert.assertEquals(three, resultThree);
    }

    @Test
    public void testHashMapStorageStrategy() {
        HashMapStorageStrategy storageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy() {
        OurHashMapStorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy() {
        FileStorageStrategy strategy = new  FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy() {
        HashBiMapStorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy() {
        DualHashBidiMapStorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy() {
        OurHashBiMapStorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}
