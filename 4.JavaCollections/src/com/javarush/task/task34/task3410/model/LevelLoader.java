package com.javarush.task.task34.task3410.model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    /*public static void main(String[] args) {
        LevelLoader levelLoader = new LevelLoader(Paths.get("C:\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));
        levelLoader.getLevel(1);
    }*/

    public GameObjects getLevel(int level) {
        if (level > 60) {
            level = level % 60;
        }
        List<String> allFile = readFile();
        List<String> objForGame = createSubList(level, allFile);
        String[][] matrix = createMatrix(objForGame);
      /*  for (String[] s : matrix) {
            System.out.println(Arrays.toString(s));
        }*/
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        int x0 = Model.FIELD_CELL_SIZE / 2;
        int y0 = Model.FIELD_CELL_SIZE / 2;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String element = matrix[i][j];
                switch (element) {
                    case "X":
                        walls.add(new Wall(x0 + Model.FIELD_CELL_SIZE * j, y0 + Model.FIELD_CELL_SIZE * i));//wall
                        break;
                    case "*":
                        boxes.add(new Box(x0 + Model.FIELD_CELL_SIZE * j, y0 + Model.FIELD_CELL_SIZE * i));
                        break;//box
                    case ".":
                        homes.add(new Home(x0 + Model.FIELD_CELL_SIZE * j, y0 + Model.FIELD_CELL_SIZE * i));
                        break;//home
                    case "&":
                        boxes.add(new Box(x0 + Model.FIELD_CELL_SIZE * j, y0 + Model.FIELD_CELL_SIZE * i));
                        homes.add(new Home(x0 + Model.FIELD_CELL_SIZE * j, y0 + Model.FIELD_CELL_SIZE * i));
                        break;//box in home
                    case "@":
                        player = new Player(x0 + Model.FIELD_CELL_SIZE * j, y0 + Model.FIELD_CELL_SIZE * i);
                        break;//player
                }
            }
        }
        return new GameObjects(walls, boxes, homes, player);
    }

    private List<String> readFile() {
        try {
            List<String> list = Files.readAllLines(levels, Charset.defaultCharset());
            // System.out.println(list.size());
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<String> createSubList(int level, List<String> allFile) {
        String found = String.format("Maze: %d", level);
        int firstIndex = 0;
        int secondIndex = 0;
        while (!allFile.get(firstIndex).equals(found)) {
            firstIndex++;
        }
        for (int index = firstIndex; index < allFile.size(); index++) {
            if (allFile.get(index).equals("*************************************")) {
                secondIndex = index;
                break;
            }
        }
        return allFile.subList(firstIndex, secondIndex);
    }

    private String[][] createMatrix(List<String> objForGame) {
        int lenY = parseStringToInteger(objForGame.get(3));
        int lenX = parseStringToInteger(objForGame.get(2));
        String[][] matrix = new String[lenY][lenX];
        int countLine = 0;
        for (int i = 7; i < lenY + 7; i++) {//количество строк из листа
            String strList = objForGame.get(i);
            for (int j = 0; j < strList.length(); j++) {
                matrix[countLine][j] = String.valueOf(strList.charAt(j));
            }
            countLine++;
        }
        return matrix;
    }

    private int parseStringToInteger(String string) {
        StringBuilder num = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c)) {
                num.append(c);
            }
        }
        return Integer.parseInt(num.toString());
    }
}
