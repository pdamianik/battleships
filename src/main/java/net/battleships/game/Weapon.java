package net.battleships.game;

public class Weapon implements AssetResource {
    private String name;
    private int[][] area;
    private int cooldown;
    private int id;
    private int resourceId;

    public Weapon (String name, int[][] area, int cooldown, int id, int resourceId){
        this.name = name;
        this.area = area;
        this.cooldown = cooldown;
        this.id = id;
        this.resourceId = resourceId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getResourceId() {
        return this.resourceId;
    }

    @Override
    public int getId() {
        return this.id;
    }
}