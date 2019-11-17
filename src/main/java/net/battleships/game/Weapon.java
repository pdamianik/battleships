package net.battleships.game;

import java.util.ArrayList;

public class Weapon {
    private String name;
    private ArrayList<ArrayList<Integer>> area= new ArrayList<>();
    private int cooldown;

    public Weapon (String name, ArrayList<ArrayList<Integer>> area, int cooldown){
        this.name = name;
        this.area = new ArrayList<>(area);
        this.cooldown = cooldown;
    }
}