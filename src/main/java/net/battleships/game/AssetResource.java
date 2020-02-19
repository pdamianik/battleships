package net.battleships.game;

import net.battleships.content.Factory;

public abstract class AssetResource {
	private String name;
	private int[][] pattern;
	boolean alive;
	int id;
	Factory<AssetResource> resourceFactory;

	public AssetResource(String name, int[][] pattern, int id, Factory<AssetResource> resourceFactory) {
		this.name = name;
		this.pattern = pattern;
		this.id = id;
		this.resourceFactory = resourceFactory;
		this.alive = true;
	}

	public AssetResource(String name, int[][] pattern, boolean alive, int id, Factory<AssetResource> resourceFactory) {
		this.name = name;
		this.pattern = pattern;
		this.id = id;
		this.resourceFactory = resourceFactory;
		this.alive = alive;
	}

	public String getName() {
		return this.name;
	}

	public int[][] getPattern() {
		return pattern;
	}

	public int getId() {
		return this.id;
	}

	public boolean isAlive() {
		return alive;
	}

	public Factory<AssetResource> getResourceFactory() {
		return resourceFactory;
	}
}
