package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(240, 200, 0));
        graphics.drawOval(getX(), getY(), getWidth(), getHeight());
        //graphics.fillRect(getX(), getY(), getWidth()-5, getHeight()-5);
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
