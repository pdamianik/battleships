package net.battleships;

import net.battleships.content.ContentLoader;
import net.battleships.content.assetResourceParser.AssetResourceParser;

import java.nio.file.AccessDeniedException;

public class DataManager {
	private ContentLoader generalContentLoader;
	private AssetResourceParser generalAssetParser;
	public DataManager() {
		try {
			this.generalContentLoader = new ContentLoader();
		} catch (AccessDeniedException e) {
			e.printStackTrace();
		}
		this.generalAssetParser = new AssetResourceParser();
	}

	public ContentLoader getGeneralContentLoader() {
		return generalContentLoader;
	}
}
