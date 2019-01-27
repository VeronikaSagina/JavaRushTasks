package com.javarush.task.task23.task2312;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Layer extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.drawRect(0, 0, (Room.game.getHeight() * 10 + 3), (Room.game.getHeight() * 10 + 3));
        g.fillRect(0, 0, (Room.game.getHeight() * 10 + 3), (Room.game.getHeight() * 10 + 3));
        g.setColor(new Color(0, 215, 200));
        g.fillRect(1, 1, (Room.game.getHeight() * 10 - 1), (Room.game.getHeight() * 10 - 1));
        g.setColor(Color.YELLOW);
        g.fillOval(Room.game.getMouse().getX() * 10, Room.game.getMouse().getY() * 10, 10, 10);
        List<SnakeSection> section = Room.game.getSnake().getSections();
        for (SnakeSection aSection : section) {
            g.fillOval(aSection.getX() * 10, aSection.getY() * 10, 10, 10); //Рисуем по очереди секции змейки
        }
    }
}
