package net.battleships.game;

import net.battleships.content.Factory;
import net.battleships.graphics.Point2D;

public class Ship extends AssetResource {
    private Point2D startPoint;

    public Ship(String name, int[][] pattern, int id, Factory<AssetResource> resourceFactory) {
        super(name, pattern, id, resourceFactory);
    }

    public Ship(String name, int[][] pattern, boolean alive, int id, Factory<AssetResource> resourceFactory) {
        super(name, pattern, alive, id, resourceFactory);
    }

    public int getHp() {
        int sum = 0;
        for(int i = 0; i < this.getPattern().length; i++) {
            for(int j = 0; j < this.getPattern()[i].length; j++) {
                sum += this.getPattern()[i][j];
            }
        }
        return sum;
    }

    public boolean hit(Point2D point) {
        if(this.getPattern()[point.getX()][point.getY()] > 0) {
            this.getPattern()[point.getX()][point.getY()] -= 1;
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
}
