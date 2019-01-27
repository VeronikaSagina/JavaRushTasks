package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    private static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.isEmpty()) {
            return false;
        }
        int firstBKT = telNumber.indexOf("(");
        int lastBKT = telNumber.indexOf(")");
        if (!(Character.isDigit(telNumber.charAt(telNumber.length() - 1)))) {
            return false;
        }
        if (telNumber.charAt(0) != '+' && telNumber.charAt(0) != '(' && !Character.isDigit(telNumber.charAt(0))) {
            return false;
        }
        int dijitBKT = 0;
        for (char c : telNumber.toCharArray()) {
            if (Character.isDigit(c)) {
                dijitBKT++;
            }
        }
        if (telNumber.charAt(0) == '+') {
            if (dijitBKT != 12) {
                return false;
            }
        }
        if (telNumber.charAt(0) == '(' || Character.isDigit(telNumber.charAt(0))) {
            if (dijitBKT != 10) {
                return false;
            }
        }
        for (int i = 0; i < telNumber.length(); i++) {
            if (Character.isLetter(telNumber.charAt(i))) {
                return false;
            }
        }
        if (lastBKT < firstBKT) {
            return false;
        }
        if (lastBKT != -1 && firstBKT != -1) {
            if (lastBKT - firstBKT != 4) {
                return false;
            }
            if (telNumber.substring(0, firstBKT).contains("-")) {
                return false;
            }
        }
        int count = 0;
        for (int i = 0; i < telNumber.length() - 1; i++) {
            if (telNumber.charAt(i) == '-') {
                count++;
                if (count > 2) {
                    return false;
                }
                if (telNumber.charAt(i + 1) == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean checkTelNumber1(String telNumber) {

        return telNumber.matches("((?:\\+\\d{2})?\\(?\\d{3}\\)?\\d{3}-?\\d{2}-?\\d{2})");
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("050123--4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(checkTelNumber1("+380501234567"));
        System.out.println(checkTelNumber1("38(050)1234567"));
        System.out.println(checkTelNumber1("+38050123-45-67"));
        System.out.println(checkTelNumber1("050123-4567"));
        System.out.println(checkTelNumber1("050123--4567"));
        System.out.println(checkTelNumber1("+38)050(1234567"));
        System.out.println(checkTelNumber1("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber1("050ххх4567"));
        System.out.println(checkTelNumber1("050123456"));
        System.out.println(checkTelNumber1("(0)501234567"));

    }
}
