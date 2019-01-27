package com.javarush.task.task26.task2601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] arrayForSort = new Integer[]{13, 8, 15, 5, 17};// 13, 15, 17, 8, 5
        System.out.println(Arrays.toString(sort(arrayForSort)));
        Integer[] arrayForSort1 = new Integer[]{13, 8, 15, 5, 17, 13};// 13, 15, 17, 8, 5
        System.out.println(Arrays.toString(sort(arrayForSort1)));
    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        int center = array.length / 2;
        Integer[] untitled;

        int median;
        if (array.length % 2 != 0) {
            median = array[center];
            untitled = new Integer[array.length - 1];
            for (int i = 0; i < center; i++) {
                untitled[i] = array[i];
            }
            for (int i = center + 1; i < array.length; i++) {
                untitled[i - 1] = array[i];
            }
        } else {
            median = (array[center -1] + array[center]) / 2;
            untitled = array;
        }
       return myBeautifulSort(array, median);
       /* Arrays.sort(array,(x,y)->Integer.compare(Math.abs(median-x), Math.abs(median-y)));
        return array;*/
    }

    private static Integer[] myBeautifulSort(Integer[] array, int median) {
        List<Integer> list = new ArrayList<>();
       // list.add(median);
        int numberForArray = 0;
        int min = Integer.MAX_VALUE;
        do {
            for (Integer in : array) {
                if (list.contains(in)) {
                    continue;
                }
                int abc = Math.abs(median - in);
                if (min > abc) {
                    min = abc;
                    numberForArray = in;
                }
            }
            list.add(numberForArray);
            min = Integer.MAX_VALUE;
        } while (list.size() != array.length);
       // list.add(0, median);
        return list.toArray(new Integer[0]);
    }
}
/*        Comparator<Integer> myComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1 - o2);
            }
        };*/