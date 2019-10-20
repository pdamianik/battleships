package net.battleships.game;

public class GameBoard {
    private int[][] board = new int[10][10];
    private Ship[] ships;

    public GameBoard(int[][] board) {
        this.board = board;

    }
}
