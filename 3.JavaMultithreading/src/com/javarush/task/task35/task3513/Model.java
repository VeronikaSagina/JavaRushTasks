package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;//текущий счет
    int maxTile;//максимальный вес плитки
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();

    public Model() {
        resetGameTiles();
        score = 0;
        maxTile = 0;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] newArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[i].length; j++) {
                newArray[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(newArray);
        previousScores.push(score);
    }

    public void rollback() {
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }
/*    public Model(Tile[][] t) {
        gameTiles = t;
    }*/

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) {
            return true;
        }
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value && gameTiles[i][j].value != 0) {
                    return true;
                }
                if (i < gameTiles.length - 1) {
                    if (gameTiles[i][j].value == gameTiles[i + 1][j].value && gameTiles[i][j].value != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.isEmpty()) {
            return;
        }
        Tile randomTile = emptyTiles.get((int) (emptyTiles.size() * Math.random()));
        randomTile.value = Math.random() < 0.9 ? 2 : 4;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    tiles.add(gameTiles[i][j]);
                }
            }
        }
        return tiles;
    }

    private boolean compressTiles(Tile[] tiles) {//сжатие {4, 2, 0, 4} -> {4, 2, 4, 0}
        boolean isChange = false;
        Tile[] compress = new Tile[tiles.length];
        int count = 0;
        for (Tile t : tiles) {
            if (t.value != 0) {
                compress[count] = t;
                count++;
            }
        }
        for (int i = 0; i < compress.length; i++) {
            if (compress[i] == null) {
                compress[i] = new Tile();
            }
        }
        if (!Arrays.equals(compress, tiles)) {
            isChange = true;
            System.arraycopy(compress, 0, tiles, 0, tiles.length);
        }
        return isChange;
    }

    private boolean mergeTiles(Tile[] tiles) {// слияние {4, 4, 4, 4} -> {8, 8, 0, 0},{4, 4, 4, 0} -> {8, 4, 0, 0}
        boolean isChange = false;
        int max = 0;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value && tiles[i].value != 0) {
                tiles[i].value = tiles[i].value + tiles[i].value;
                tiles[i + 1].value = 0;
                score += tiles[i].value;
                if (max < tiles[i].value) {
                    max = tiles[i].value;
                }
                isChange = true;
                i++;
            }
        }
        compressTiles(tiles);
        if (max > maxTile) {
            maxTile = max;
        }
        return isChange;
    }

    private boolean hasBoardChanged() {
        int weightTilesGame = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                weightTilesGame += gameTiles[i][j].value;
            }
        }
        int stackWeightTiles = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                stackWeightTiles += previousStates.peek()[i][j].value;
            }
        }
        return weightTilesGame != stackWeightTiles;
    }

    void autoMove(){
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this::left));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));
        Objects.requireNonNull(queue.poll()).getMove().move();
    }

    private MoveEfficiency getMoveEfficiency(Move move) {
        int aScore = score;
        move.move();
        int bScore = score;
        if (hasBoardChanged() && aScore < bScore) {
            rollback();
            return new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        rollback();
        return new MoveEfficiency(-1, 0, move);
    }

    void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    public void left() {
        saveState(gameTiles);
        shift();
    }

    private void shift() {
        boolean c = false;
        for (Tile[] gameTile : gameTiles) {
            boolean a = compressTiles(gameTile);
            boolean b = mergeTiles(gameTile);
            c = a || c;
            c = c || b;
        }
        if (c) {
            addTile();
        }
    }

    public void up() {
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        shift();
        rotate();
    }

    public void right() {
        saveState(gameTiles);
        rotate();
        rotate();
        shift();
        rotate();
        rotate();
    }

    public void down() {
        saveState(gameTiles);
        rotate();
        shift();
        rotate();
        rotate();
        rotate();
    }

    private void rotate() {
        Tile[][] rotate = new Tile[gameTiles.length][gameTiles.length];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                rotate[i][j] = gameTiles[gameTiles.length - j - 1][i];
            }
        }
        System.arraycopy(rotate, 0, gameTiles, 0, gameTiles.length);
    }
}
