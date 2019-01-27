package com.javarush.task.task22.task2202;

import java.util.ArrayList;
import java.util.List;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        List<String> list = new ArrayList<>();
       list.add("JavaRush - лучший сервис обучения Java.");
        list.add("Амиго и Диего лучшие друзья!");
        list.add("Моя задача не работает, как требует условие");
        list.add("Тесты для слабаков");
        list.add("День числа пи — неофициальный праздник, который отмечается любителями математики 14 марта в 1:59:26 в честь математической константы — числа пи.");
        list.add("День был отмечен в 1988 году в научно-популярном музее Эксплораториум");
        list.add("Тут меньше четырех пробелов");
        list.add(null);
        list.add("       ");
        for (int i = 0; i < list.size(); i++) {
            try {
                System.out.println(getPartOfString1(list.get(i)));
            } catch (TooShortStringException e) {
                e.printStackTrace();
            }
        }

    }

    public static class TooShortStringException extends RuntimeException {
    }

    public static String getPartOfString1(String string) {
        if (string == null) {
            throw new TooShortStringException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String[] strings = string.split(" ", -1);
            for (int i = 1; i < 5; i++) {
                stringBuilder.append(strings[i]).append(" ");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TooShortStringException();
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public static String getPartOfString(String string) {
        if (string == null || string.isEmpty()) {
            throw new TooShortStringException();
        }
        int forSpase = 0;//четвертый пробел
        int count = 0;
        for (char c : string.toCharArray()) {
            if (c == ' ') {
                count++;
            }
            forSpase++;
            if (count == 4) {
                break;
            }
        }
        if (count < 4) {
            throw new TooShortStringException();
        }
        int indexLastSpace = string.indexOf(" ", forSpase);//следующий после четвертого пробел
        int lengthOfWord;
        if (indexLastSpace == -1) {
            lengthOfWord = string.substring(forSpase).length();
        } else {
            lengthOfWord = string.substring(forSpase, indexLastSpace).length();// длина слова до пробела
        }
        return string.substring(string.indexOf(" ") + 1, forSpase + lengthOfWord);
    }


}
