package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            ByteArrayOutputStream password = getPassword(12);
            System.out.println(password.toString());
        }
    }

    private static ByteArrayOutputStream getPassword(int len) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final String every = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            SecureRandom random = new SecureRandom();
            int numR = random.nextInt(every.length());
            stringBuilder.append(every.charAt(numR));
        }
        if (!isValid(stringBuilder.toString().toCharArray())) {
            return getPassword(len);
        }
        try {
            outputStream.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream;
    }
/*fo2M7f0KBvgA plenki.net*/
    private static boolean isValid(char[] password) {
        int countDigit = 0;
        int countUp = 0;
        int countLower = 0;
        for (char ch : password) {
            if (Character.isDigit(ch)) {
                countDigit++;
            } else if (ch >= 'A' && ch <= 'Z') {
                countUp++;
            } else if (ch >= 'a' && ch <= 'z') {
                countLower++;
            }
        }
        return countDigit > 0 && countLower > 0 && countUp > 0;
    }
}