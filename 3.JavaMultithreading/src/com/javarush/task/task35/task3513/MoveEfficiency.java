package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        int firstCompare = numberOfEmptyTiles - o.numberOfEmptyTiles;
        if (firstCompare != 0) {
            return firstCompare > 0 ? 1 : -1;
        }
        int supScore = score - o.score;
        if (supScore != 0) {
            return supScore > 0 ? 1 : -1;
        }
        return 0;
    }
}
