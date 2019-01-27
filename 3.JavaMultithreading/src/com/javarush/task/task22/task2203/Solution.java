package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null || string.isEmpty()) {
            throw new TooShortStringException();
        }
        int firstTab = string.indexOf("\t");
        int lastTab = string.indexOf("\t", firstTab + 1);
        if (firstTab == -1 || lastTab== -1) {
            throw new TooShortStringException();
        }
        return string.substring(firstTab + 1, lastTab);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
        System.out.println(getPartOfString("avaRush - лучший сервис "));
    }
}
