package net.battleships.graphics;

import org.junit.Test;

import static org.junit.Assert.*;

public class Point2dTest {
    @Test
    public void testDistanceNull() {
        Point2D point2D = new Point2D(2, 2);
        assertEquals(new Point2D(2, 2).getX(), point2D.distanceNull().getX());
        assertEquals(new Point2D(2, 2).getY(), point2D.distanceNull().getY());
    }

    @Test
    public void testDistance() {
        Point2D point1 = new Point2D(2, 2);
        Point2D point2 = new Point2D(4, 4);
        assertEquals(2, point1.distance(point2).getX());
        assertEquals(2, point1.distance(point2).getY());
    }

    @Test
    public void testEquals(){
        Point2D point1 = new Point2D(2, 2);
        Point2D point2 = new Point2D(2, 2);
        assertEquals(true, point1.equals(point2));
    }
}
