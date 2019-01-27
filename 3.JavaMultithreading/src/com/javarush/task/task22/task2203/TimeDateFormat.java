package com.javarush.task.task22.task2203;

import java.util.Calendar;
import java.util.Formatter;

public class TimeDateFormat {
    public static void main(String args[]) {
        Formatter fmt = new Formatter();
        Calendar cal = Calendar.getInstance();
// Отобразить стандартный 12-часовой формат,
        fmt.format("%tr", cal);
        System.out.println(fmt);
// Отобразить полную информацию о дате и времени,
        fmt = new Formatter();
        fmt.format("%tc", cal);
        System.out.println(fmt);
// Отобразить только часы и минуты.
        fmt = new Formatter();
        fmt.format("%tk:%tM ", cal, cal);
        System.out.println(fmt);
// Отобразить название и номер месяца,
        fmt = new Formatter();
        fmt.format("%tB %tb %tm", cal, cal, cal);
        System.out.println(fmt);
        fmt.close();
    }
}