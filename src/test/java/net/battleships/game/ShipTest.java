package net.battleships.game;

import net.battleships.graphics.Point2D;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShipTest {
    @Test
    public void testGetHp() {
        int[][] classic = new int[1][2];
        classic[0][0] = 1;
        classic[0][1] = 1;
        Ship ship = new Ship("test", classic, true);
        assertEquals(2, ship.getHp());
    }

    @Test
    public void testHit() {
        int[][] classic = new int[1][2];
        classic[0][0] = 1;
        classic[0][1] = 1;
        Ship ship = new Ship("test", classic, true);
        assertEquals(true, ship.hit(new Point2D(0, 1)));
    }

    @Test
    public void testIsAlive() {

    }
}
