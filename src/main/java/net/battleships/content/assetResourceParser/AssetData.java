package net.battleships.content.assetResourceParser;

import java.util.Arrays;

public class AssetData{
	private String name;
	private boolean hasName = false;
	private String author;
	private boolean hasAuthor = false;
	private double version = -1d;
	private boolean hasVersion = false;
	private int[][] damagePattern;
	private boolean hasDamagePattern = false;

	public AssetData(String name, String author, double version, int[][] damagePattern) {
		this.name = name;
		this.hasName = true;
		this.author = author;
		this.hasAuthor = true;
		this.version = version;
		this.hasVersion = true;
		this.damagePattern = damagePattern;
		this.hasDamagePattern = true;
	}

	public AssetData(String name, double version, int[][] damagePattern) {
		this.name = name;
		this.hasName = true;
		this.version = version;
		this.hasVersion = true;
		this.damagePattern = damagePattern;
		this.hasDamagePattern = true;
	}

	public AssetData(String name, String author, int[][] damagePattern) {
		this.name = name;
		this.hasName = true;
		this.author = author;
		this.hasAuthor = true;
		this.damagePattern = damagePattern;
		this.hasDamagePattern = true;
	}

	public AssetData(String name, int[][] damagePattern) {
		this.name = name;
		this.hasName = true;
		this.damagePattern = damagePattern;
		this.hasDamagePattern = true;
	}

	public AssetData() {

	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
		this.hasName = true;
	}

	public String getAuthor() {
		return author;
	}

	void setAuthor(String author) {
		this.author = author;
		this.hasAuthor = true;
	}

	public double getVersion() {
		return version;
	}

	void setVersion(double version) {
		this.version = version;
		this.hasVersion = true;
	}

	public int[][] getDamagePattern() {
		return damagePattern;
	}

	void setDamagePattern(int[][] damagePattern) {
		this.damagePattern = damagePattern;
		this.hasDamagePattern = true;
	}

	@Override
	public String toString() {
		return this.name + (this.hasAuthor ? " from " + this.author : "") + (this.hasVersion ? " version " + this.version : "") + " damage pattern: " + Arrays.deepToString(this.damagePattern);
	}

	public boolean equals(Object object) {
		if (object instanceof AssetData) {
			AssetData objectConverted = (AssetData) object;
			return this.name.equals(objectConverted.getName()) &&
					(this.hasAuthor && objectConverted.hasAuthor ? this.author.equals(objectConverted.getAuthor()) : this.hasAuthor == objectConverted.hasAuthor) &&
					(this.hasVersion && objectConverted.hasVersion ? this.version == objectConverted.getVersion() : this.hasVersion == objectConverted.hasVersion) &&
					Arrays.deepEquals(this.damagePattern, objectConverted.getDamagePattern());
		}
		return super.equals(object);
	}

	public boolean isComplete() {
		return this.hasName && this.hasDamagePattern;
	}
}
