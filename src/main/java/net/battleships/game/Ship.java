package net.battleships.game;

import net.battleships.graphics.Point2D;

public class Ship {
    private String name;
    private int[][] area;
    private Point2D startPoint;

    public Ship(String name, int[][] area) {
        this.name = name;
        this.area = area;
    }

    public int getHp() {
        return -1;
    }

    public boolean hit(Point2D point) {
        return true;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public boolean isAlive() {
        return getHp() <= 0;
    }
}
