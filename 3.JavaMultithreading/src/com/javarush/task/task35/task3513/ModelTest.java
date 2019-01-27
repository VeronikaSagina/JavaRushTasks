package com.javarush.task.task35.task3513;

import org.junit.Test;

public class ModelTest {

    @Test
    public void canMove() {
        Tile[][] tile1 = {
                {new Tile(8), new Tile(2), new Tile(4), new Tile(2)},
                {new Tile(4), new Tile(4), new Tile(2), new Tile(8)},
                {new Tile(8), new Tile(8), new Tile(4), new Tile(4)},
                {new Tile(4), new Tile(2), new Tile(8), new Tile(8)},
        };
        Tile[][] tile2 = {
                {new Tile(4), new Tile(2), new Tile(0), new Tile(2)},
                {new Tile(2), new Tile(4), new Tile(0), new Tile(4)},
                {new Tile(4), new Tile(2), new Tile(0), new Tile(2)},
                {new Tile(2), new Tile(4), new Tile(2), new Tile(4)},
        };
        Tile[][] tile3 = {
                {new Tile(2), new Tile(4), new Tile(2), new Tile(4)},
                {new Tile(4), new Tile(2), new Tile(4), new Tile(2)},
                {new Tile(2), new Tile(4), new Tile(2), new Tile(4)},
                {new Tile(4), new Tile(2), new Tile(4), new Tile(2)},
        };
       /* Model model = new Model(tile1);
        Model mode2 = new Model(tile2);
        Model mode3 = new Model(tile3);
        Assert.assertTrue(model.canMove());
        Assert.assertTrue(mode2.canMove());
        Assert.assertFalse(mode3.canMove());*/
    }
}