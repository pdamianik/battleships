package net.battleships.content.assetResourceParser;

public class AssetData{
	private String name;
	private String author;
	private double version;
	private int[][] damagePattern;

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	void setAuthor(String author) {
		this.author = author;
	}

	public double getVersion() {
		return version;
	}

	void setVersion(double version) {
		this.version = version;
	}

	public int[][] getDamagePattern() {
		return damagePattern;
	}

	void setDamagePattern(int[][] damagePattern) {
		this.damagePattern = damagePattern;
	}
}
