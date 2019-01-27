package com.javarush.task.task34.task3410.model;


import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader =
            new LevelLoader(Paths.get("C:\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        Set<Wall> walls = gameObjects.getWalls();
        for (Wall wall : walls) {
            if (gameObject.isCollision(wall, direction)) {
                return true;//если столкнется
            }
        }
        return false;//все норм
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) {
            return true;
        }
        Set<Box> boxes = gameObjects.getBoxes();
        for (Box box : boxes) {
            if (player.isCollision(box, direction)) {//если столкнулся с ящиком
                if (checkWallCollision(box, direction)) {//проверяем есть ли за ним стена
                    return true;//есть стена, уходим
                }
                for (Box box1 : boxes) {//стены нет, есть ли другой ящик
                    if (box.isCollision(box1, direction)) {
                        return true;//есть ящик, уходим
                    }
                }
                //ящик есть, преград нет.Тащим ящик.
                int[] xy = moveObject(direction);
                box.move(xy[0], xy[1]);
                return false;
            }
        }
        return false;
    }

    private int[] moveObject(Direction direction) {
        switch (direction) {
            case UP:
                return new int[]{0, -FIELD_CELL_SIZE};
            case DOWN:
                return new int[]{0, FIELD_CELL_SIZE};
            case LEFT:
                return new int[]{-FIELD_CELL_SIZE, 0};
            case RIGHT:
                return new int[]{FIELD_CELL_SIZE, 0};
        }
        return new int[]{0, 0};
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction) ||
                checkBoxCollisionAndMoveIfAvaliable(direction)) {
            return;
        }
        int[] xy = moveObject(direction);
        player.move(xy[0], xy[1]);
        checkCompletion();
    }

    public void checkCompletion() {
        Set<Home> tempHomes = new HashSet<>();
        for (Home home : gameObjects.getHomes()) {
            int homeX = home.getX();
            int homeY = home.getY();
            for (Box box : gameObjects.getBoxes()) {
                if (box.getY() == homeY && box.getX() == homeX) {
                    tempHomes.add(home);
                }
            }
        }
        if (tempHomes.size() == gameObjects.getHomes().size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }
}