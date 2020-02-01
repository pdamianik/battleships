package net.battleships.content;

import java.io.File;

public class Loadable {
	private File srcFile;
	private ContentLoader contentLoader;
	Loadable(File srcFile, ContentLoader contentLoader) {
		this.srcFile = srcFile;
		this.contentLoader = contentLoader;
	}
	public String getName() {
		return "";
	}
	public String getAuthor() {
		return "";
	}
	public String getVersion() {
		return "";
	}

	public Factory load() {
		return null;
	}
}
