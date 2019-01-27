package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {// заказ
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        return isEmpty() ? "" : String.format("Your order: %s of %s", dishes, tablet);
    }

    public int getTotalCookingTime() {
        return dishes.stream()
                .mapToInt(Dish::getDuration)
                .sum();
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }
}
