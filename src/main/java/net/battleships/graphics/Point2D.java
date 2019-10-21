package net.battleships.graphics;

public class Point2D {
    private int x;
    private int y;

    public Point2D(){
        this.x = 0;
        this.y = 0;
    }

    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point2D distanceNull() {
        return this;
    }

    public boolean equals(Point2D punkt) {
        return this.x == punkt.x && this.y == punkt.y;
    }

    public Point2D distance(Point2D punkt) {
        return new Point2D(Math.abs(this.x - punkt.getX()), Math.abs(this.y - punkt.getY()));
    }
}
