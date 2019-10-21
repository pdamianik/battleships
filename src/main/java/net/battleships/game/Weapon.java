package net.battleships.game;

import java.util.ArrayList;

public class Weapon {
    private String name;
    private int[][] area;
    private int cooldown;

    public Weapon (String name, int[][] area, int cooldown){
        this.name = name;
        this.area = area;
        this.cooldown = cooldown;
    }
}