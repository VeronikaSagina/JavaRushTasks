package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        Color oldC = Color.YELLOW;
        graphics.setColor(oldC);
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
      //  graphics.fillRect(getX()+1, getY()+1, getWidth()-1, getHeight()-1);
        graphics.drawLine( getX(), getY(), getX() + Model.FIELD_CELL_SIZE, getY() + Model.FIELD_CELL_SIZE);//r
        graphics.drawLine(getX() + Model.FIELD_CELL_SIZE, getY(), getX(), getY() + Model.FIELD_CELL_SIZE);

    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
