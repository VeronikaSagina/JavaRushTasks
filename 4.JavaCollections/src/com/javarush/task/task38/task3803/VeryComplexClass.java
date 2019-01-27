package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.HashMap;
import java.util.Map;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object obj = new java.util.Date();
        Integer int1 = (Integer) obj;
    }

    public void methodThrowsNullPointerException() {
        Map<Integer, Integer> map = new HashMap<>();
        boolean equals = map.get(1).equals(1);
    }

    public static void main(String[] args) {
        VeryComplexClass v = new VeryComplexClass();
        //  v.methodThrowsNullPointerException();
        // v.methodThrowsClassCastException();
        System.out.println(v.returner());
    }

    private int returner() {
        int a = 1;
        try {
            return a + 3;
        } finally {
            return a + 4;
        }
    }
}
