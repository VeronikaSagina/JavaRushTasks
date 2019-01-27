package com.javarush.task.task40.task4004;

import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        int intersection = 0;
        for (int i = 0; i < polygon.size() - 1; i++) {
            Point point1 = polygon.get(i);
            Point point2 = polygon.get((i + 1) % polygon.size());
            if (point2.y == point1.y) {
                continue;
            }
            double alpha = (point.y - (double) point1.y) / (point2.y - point1.y);
            double beta = point1.x + alpha * (point2.x - point1.x) - point.x;
            if (alpha >= 0 && alpha <= 1 && beta >= 0) {
                intersection++;
            }
        }
        return intersection % 2 != 0;
    }

  /*  public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        int[] arrayX = new int[polygon.size()];
        int[] arrayY = new int[polygon.size()];
        for (int i = 0; i < polygon.size(); i++) {
            Point p = polygon.get(i);
            arrayX[i] = p.x;
            arrayY[i] = p.y;
        }
        Polygon poly = new Polygon(arrayX, arrayY, polygon.size());
        return poly.contains(point.x, point.y);
    }*/
}