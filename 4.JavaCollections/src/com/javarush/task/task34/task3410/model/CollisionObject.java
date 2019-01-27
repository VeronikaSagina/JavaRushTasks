package com.javarush.task.task34.task3410.model;

public abstract class  CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int x = getX();
        int y = getY();
        if (direction == Direction.LEFT){
            return gameObject.getX() == x - Model.FIELD_CELL_SIZE && gameObject.getY() == y;
        }if (direction == Direction.RIGHT){
            return gameObject.getX() == x + Model.FIELD_CELL_SIZE && gameObject.getY() == y;
        }if (direction == Direction.UP){
            return gameObject.getX() == x && gameObject.getY() == y - Model.FIELD_CELL_SIZE;
        }if (direction == Direction.DOWN){
            return gameObject.getX() == x && gameObject.getY() == y + Model.FIELD_CELL_SIZE;
        }
        return false;
    }
}
