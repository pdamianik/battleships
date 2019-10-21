package net.battleships.game;

import net.battleships.graphics.Point2D;

import java.util.ArrayList;

public class Ship {
    private String name;
    private int[][] area;
    private Point2D startPoint;
    private boolean alive = true;

    public Ship(String name, int[][] area, boolean alive) {
        this.name = name;
        this.area = area;
        this.alive = alive;
    }

    public int getHp() {
        int sum = 0;
        for(int i = 0; i < area.length; i++) {
            for(int j = 0; j < area[i].length; j++) {
                sum += area[i][j];
            }
        }
        return sum;
    }

    public boolean hit(Point2D point) {
        if(area[point.getX()][point.getY()] > 0) {
            area[point.getX()][point.getY()] -= 1;
            if (getHp()<=0) {
                this.alive = false;
            }
            return true;
        }
        return false;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public boolean getAlive() {
        return this.alive;
    }
}
