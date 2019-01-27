package com.javarush.task.task39.task3905;

/* 
Залей меня полностью
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        PhotoPaint paint = new PhotoPaint();
        Color[][] colors =
                        {{Color.BLUE, Color.BLUE},
                        {Color.BLUE, Color.BLUE},
                        {Color.BLUE, Color.RED},
                        {Color.BLUE, Color.RED},
                        {Color.ORANGE, Color.BLUE},
                        {Color.BLUE, Color.BLUE}};
        System.out.println(paint.paintFill(colors, 1, 1, Color.GREEN));
        for (Color[] colors1 : colors){
            System.out.println(Arrays.toString(colors1));
        }
    }
}
