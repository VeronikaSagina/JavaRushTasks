package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    Map<String, List<String>> map = new HashMap<>();
    public static void main(String[] args) {
        int count = 3;
        moveRing('A', 'B', 'C', count);
    }

    public static void moveRing(char a, char b, char c, int count) {
        System.out.println(String.format("%s %s %s %d", a, b, c, count));
        if (count == 1) {
            //System.out.println("from " + a + " to " + b);
            return;
        }
        System.out.println("moveRing(a, c, b, count - 1)");
        moveRing(a, c, b, count - 1);
        System.out.println("moveRing(a, c, b, 1)");
        moveRing(a, b, c, 1);
        System.out.println("moveRing(a, c, b, count - 1)2");
        moveRing(c, b, a, count - 1);
    }
}