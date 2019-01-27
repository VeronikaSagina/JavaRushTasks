package com.javarush.task.task39.task3905;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PhotoPaintTest {

    @Test
    public void paintFill() {
        PhotoPaint paint = new PhotoPaint();
        Color[][] colors = {
                        {Color.BLUE, Color.BLUE},
                        {Color.BLUE, Color.BLUE},
                        {Color.BLUE, Color.RED},
                        {Color.BLUE, Color.RED},
                        {Color.ORANGE, Color.BLUE},
                        {Color.BLUE, Color.BLUE}};
        Color[][] expectedColors = {
                        {Color.GREEN, Color.GREEN},
                        {Color.GREEN, Color.GREEN},
                        {Color.GREEN, Color.RED},
                        {Color.GREEN, Color.RED},
                        {Color.ORANGE, Color.BLUE},
                        {Color.BLUE, Color.BLUE}};
        Assert.assertTrue(paint.paintFill(colors, 1, 1, Color.GREEN));
        Assert.assertTrue(Arrays.deepEquals(expectedColors, colors));

    }

    @Test
    public void paintFill2() {
        PhotoPaint paint = new PhotoPaint();
        Color[][] colors = {
                        {Color.BLUE, Color.BLUE, Color.ORANGE},
                        {Color.BLUE, Color.BLUE, Color.ORANGE},
                        {Color.BLUE, Color.RED, Color.ORANGE},
                        {Color.BLUE, Color.ORANGE, Color.ORANGE},
                        {Color.ORANGE, Color.ORANGE, Color.BLUE},
                        {Color.BLUE, Color.BLUE, Color.ORANGE}};
        Color[][] expectedColors = {
                        {Color.BLUE, Color.BLUE, Color.INDIGO},
                        {Color.BLUE, Color.BLUE, Color.INDIGO},
                        {Color.BLUE, Color.RED, Color.INDIGO},
                        {Color.BLUE, Color.INDIGO, Color.INDIGO},
                        {Color.INDIGO, Color.INDIGO, Color.BLUE},
                        {Color.BLUE, Color.BLUE, Color.ORANGE}};
        Assert.assertTrue(paint.paintFill(colors, 1, 3, Color.INDIGO));
        Assert.assertTrue(Arrays.deepEquals(expectedColors, colors));

    }

    @Test
    public void paintFill3() {
        PhotoPaint paint = new PhotoPaint();
        Color[][] colors =
                {{Color.BLUE, Color.BLUE, Color.ORANGE},
                        {Color.BLUE, Color.BLUE, Color.ORANGE},
                        {Color.BLUE, Color.RED, Color.ORANGE},
                        {Color.BLUE, Color.ORANGE, Color.ORANGE},
                        {Color.ORANGE, Color.ORANGE, Color.BLUE},
                        {Color.BLUE, Color.BLUE, Color.ORANGE}};
        Color[][] expectedColors =
                {{Color.BLUE, Color.BLUE, Color.ORANGE},
                        {Color.BLUE, Color.BLUE, Color.ORANGE},
                        {Color.BLUE, Color.RED, Color.ORANGE},
                        {Color.BLUE, Color.ORANGE, Color.ORANGE},
                        {Color.ORANGE, Color.ORANGE, Color.BLUE},
                        {Color.BLUE, Color.BLUE, Color.ORANGE}};
        Assert.assertFalse(paint.paintFill(colors, 1, 2, Color.RED));
        Assert.assertTrue(Arrays.deepEquals(expectedColors, colors));
    }
}