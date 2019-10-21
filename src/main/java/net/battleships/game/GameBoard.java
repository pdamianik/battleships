package net.battleships.game;

import java.util.ArrayList;

public class GameBoard {
    private int[][] board = new int[10][10];
    private ArrayList<Ship> ships = new ArrayList <> ();
    private ArrayList<Weapon> weapons = new ArrayList <> ();

    public GameBoard(int[][] board, ArrayList<Ship> ships, ArrayList<Weapon> weapons) {
        this.board = board;
        this.ships = ships;
        ArrayList<ArrayList<Integer>> classic = new ArrayList<ArrayList<Integer>>();
        classic.get(0).add(1);
        Weapon weapon = new Weapon("classic", classic, 1);
        this.weapons = weapons;
        this.weapons.add(weapon);
    }
}
