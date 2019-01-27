package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);
       System.out.println(getQuery(map));
        Map<String, String> map1 = new HashMap<>();
        map.put("name", null);
        map.put("country", null);
        map.put("city", null);
        map.put("age", null);
        System.out.println(getQuery(map1));
    }

    public static String getQuery(Map<String, String> params) {
        if (params == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> pair : params.entrySet()) {
            if (!(pair.getValue() == null)) {
                sb.append(pair.getKey()).append(" = '")
                        .append(pair.getValue())
                        .append("' and ");

            }
        }
        if (sb.length() > 0) {
            return sb.delete(sb.length() - 5, sb.length()).toString();
        }
        return "";
    }
}
