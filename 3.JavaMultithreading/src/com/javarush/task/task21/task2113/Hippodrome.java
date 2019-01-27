package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) {
        List<Horse> horseList = new ArrayList<>();
        Horse marta = new Horse("Marta", 3, 0);
        Horse rojer = new Horse("Rojer", 3, 0);
        Horse selesta = new Horse("Selesta", 3, 0);
        horseList.add(marta);
        horseList.add(rojer);
        horseList.add(selesta);
        game = new Hippodrome(horseList);
        game.run();
        game.printWinner();
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Horse getWinner() {
        Horse winner = null;
        double max = 0;
        for (Horse horse : horses) {
            double s = horse.getDistance();
            if (s > max) {
                max = s;
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
