package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.List;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            String[] enamWheel = loadWheelNamesFromDB();
            wheels = new ArrayList<>();
            if (enamWheel.length != 4) {
                throw new IllegalArgumentException();
            }
            for (String wheel : enamWheel) {
                wheels.add(Wheel.valueOf(wheel));
            }
            //init wheels here
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        for (Wheel wheel : car.wheels) {
            System.out.println(wheel);
        }
    }
}
