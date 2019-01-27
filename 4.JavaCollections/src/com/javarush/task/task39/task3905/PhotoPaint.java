package com.javarush.task.task39.task3905;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (x >= image[0].length
                || x < 0 || y >= image.length
                || y < 0 || image[y][x] == desiredColor) {
            return false;
        }
        LinkedHashSet<Coordinate> queue = new LinkedHashSet<>();
        queue.add(new Coordinate(y, x));
        Color initialColor = image[y][x];
        while (!queue.isEmpty()) {
            Iterator<Coordinate> iterator = queue.iterator();
            Coordinate head = iterator.next();
            iterator.remove();
            y = head.y;
            x = head.x;
            if (head.x > 0) {
                add(queue, new Coordinate(y, x - 1), image, initialColor);
            }
            if (head.x < image[0].length - 1) {
                add(queue, new Coordinate(y, x + 1), image, initialColor);
            }
            if (head.y > 0) {
                add(queue, new Coordinate(y - 1, x), image, initialColor);
            }
            if (head.y < image.length - 1) {
                add(queue, new Coordinate(y + 1, x), image, initialColor);
            }
            image[head.y][head.x] = desiredColor;
        }

        return true;
    }

    private void add(Collection<Coordinate> coordinates, Coordinate coordinate, Color[][] image, Color initialColor) {
        if (image[coordinate.y][coordinate.x] == initialColor) {
            coordinates.add(coordinate);
        }
    }


    private static class Coordinate {
        private int x;
        private int y;

        public Coordinate(int y, int x) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }
    }
}
