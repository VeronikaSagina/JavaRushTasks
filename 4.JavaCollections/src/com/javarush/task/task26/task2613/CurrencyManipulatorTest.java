package com.javarush.task.task26.task2613;

public class CurrencyManipulatorTest {
  /*  CurrencyManipulator currencyManipulator = new CurrencyManipulator("USD");
    *//*  currentMap.put(500, 2); 1650
        currentMap.put(100, 5);
        currentMap.put(50, 3);*//*
    @Test
    public void withdrawAmount() {
        Map<Integer, Integer> expectedMap = new HashMap<>();//950
        expectedMap.put(500, 2);
        expectedMap.put(100, 5);
        expectedMap.put(50, 2);
        Map<Integer, Integer> actual = currencyManipulator.withdrawAmount(1600);
        Assert.assertEquals(expectedMap, actual);
    }
    @Test
    public void moreThen(){
        Assert.assertEquals(new HashMap<>(), currencyManipulator.withdrawAmount(1700));
    }
    @Test
    public void test(){
        Map<Integer, Integer> expectedMap = new HashMap<>();//950
        expectedMap.put(500, 1);
        expectedMap.put(100, 4);
        expectedMap.put(50, 1);
        Assert.assertEquals(expectedMap, currencyManipulator.withdrawAmount(950));
    }
    @Test
    public void test2(){
        Map<Integer, Integer> expectedMap = new HashMap<>();//100
        expectedMap.put(100, 1);
        Assert.assertEquals(expectedMap, currencyManipulator.withdrawAmount(100));
    }*/
}