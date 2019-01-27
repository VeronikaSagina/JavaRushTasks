package com.javarush.task.task40.task4007;

import java.util.Arrays;
import java.util.Calendar;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        int[] s = Arrays.stream(date.replaceAll("\\.", " ").replaceAll(":", " ").split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        if (s.length == 6) {
            fullData(s);
        } else {
            if (s[2] > 60) {
                data(s);
            } else {
                time(s);
            }
        }
    }

    private static void fullData(int[] data) {
        data(new int[]{data[0], data[1], data[2]});
        time(new int[]{data[3], data[4], data[5]});
    }

    private static void time(int[] data) {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.DECEMBER, 1, data[0], data[1], data[2]);
        System.out.println("AM или PM: " + (data[0] < 12 ? "AM" : "PM"));
        System.out.println("Часы: " + cal.get(Calendar.HOUR));
        System.out.println("Часы дня: " + cal.get(Calendar.HOUR_OF_DAY));
        System.out.println("Минуты: " + cal.get(Calendar.MINUTE));
        System.out.println("Секунды: " + cal.get(Calendar.SECOND));
    }

    private static void data(int[] data) {
        Calendar cal = Calendar.getInstance();
        cal.set(data[2], data[1], data[0]);
        System.out.println("День: " + cal.get(Calendar.DATE));
        System.out.println("День недели: " + cal.get(Calendar.DAY_OF_WEEK));
        System.out.println("День месяца: " + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("День года: " + cal.get(Calendar.DAY_OF_YEAR));
        System.out.println("Неделя месяца: " + cal.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Неделя года: " + cal.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Месяц: " + cal.get(Calendar.MONTH));
        System.out.println("Год: " + cal.get(Calendar.YEAR));
    }
}
