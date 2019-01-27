package com.javarush.task.task35.task3511;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
    }

    public static Double sum(List<? extends Number> list) {
        Double result = 0.0;
        for (Number aList : list) {
            result += (aList).doubleValue();
        }
        return result;
    }

    public static Double multiply(List<? extends Number> list) {
        Double result = 1.0;
        for (Number aList : list) {
            result *= (aList).doubleValue();
        }
        return result;
    }

    public static String concat(List<?> list) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : list) {
            builder.append(obj);
        }
        return builder.toString();
    }

    public static List combine(List<? extends Collection> list) {
        ArrayList<? extends Collection> result = new ArrayList<>();
        for (Collection aList : list) {
            result.addAll(aList);
        }
        return result;
    }
}
