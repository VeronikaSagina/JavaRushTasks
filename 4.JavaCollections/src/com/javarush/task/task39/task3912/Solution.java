package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static int maxSquare(int[][] matrix) {
        int[][] mask = new int[matrix.length + 1][matrix[0].length + 1];
        int max = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == 0) {
                    continue;
                }
                int sum = Math.min(Math.min(mask[y][x + 1], mask[y + 1][x]), mask[y][x]) + 1;
                mask[y + 1][x + 1] = sum;
                if (max < sum) {
                    max = sum;
                }
            }
        }
        return max * max;
    }
}
