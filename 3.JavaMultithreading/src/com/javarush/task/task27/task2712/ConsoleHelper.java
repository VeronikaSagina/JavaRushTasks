package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> list = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        writeMessage("Введите строку - название блюда.");
        String food = readString();
        while (!food.equalsIgnoreCase("exit")) {
            switch (food.toLowerCase()) {
                case "fish":
                    list.add(Dish.Fish);
                    break;
                case "steak":
                    list.add(Dish.Steak);
                    break;
                case "soup":
                    list.add(Dish.Soup);
                    break;
                case "juice":
                    list.add(Dish.Juice);
                    break;
                case "water":
                    list.add(Dish.Water);
                    break;
                default:
                    writeMessage("Такого блюда нет.");
            }
            food = readString();
        }
        return list;
    }
}
