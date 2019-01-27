package com.javarush.task.task22.task2213;

import org.junit.Assert;

public class FieldTest {

    @org.junit.Test
    public void removeFullLines() {
        int[][] game = {
                {1, 1, 0},                          //   X X
                {0, 1, 1},                          //     X X
                {1, 0, 0},                          //   X
                {1, 1, 1},                          //   X X
                {1, 1, 1}};
        int[][] expected = {
                {0, 0, 0},
                {0, 0, 0},
                {1, 1, 0},                          //   X X
                {0, 1, 1},                          //     X X
                {1, 0, 0}
        };
        Field field = new Field(5, 3);
        for (int i = 0; i < field.getMatrix().length; i++) {
            field.getMatrix()[i] = game[i];
        }
        field.removeFullLines();
        Assert.assertArrayEquals(expected, field.getMatrix());
    }
}