package net.battleships.game;

import java.util.ArrayList;

public class GameBoard {
    private int[][] board = new int[10][10];
    private ArrayList<Ship> ships = new ArrayList <> ();
    private ArrayList<Weapon> weapons = new ArrayList <> ();

    public GameBoard(int[][] board, ArrayList<Ship> ships, ArrayList<Weapon> weapons) {
        this.board = board;
        this.ships = ships;
        this.weapons = weapons;
    }
}
