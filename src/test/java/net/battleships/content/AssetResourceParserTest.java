package net.battleships.content;

import net.battleships.content.assetResourceParser.AssetData;
import net.battleships.content.assetResourceParser.AssetResourceParser;
import net.battleships.content.assetResourceParser.tokens.*;
import net.battleships.datatypes.exceptions.InvalidKeyException;
import net.battleships.datatypes.exceptions.KeyAlreadyExistsException;
import net.battleships.datatypes.exceptions.MissingDataException;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssetResourceParserTest {
	@Test
	public void parserTest() {
		AssetResourceParser assetResourceParser = new AssetResourceParser();
		String testData1 = "name: atomic bomb\n" +
				"author: built-in\n" +
				"version: v1.0\n" +
				"health pattern:\n" +
				"  , 1, 1,  ,\n" +
				" 1, 2, 2, 1,\n" +
				" 1, 2, 2, 1,\n" +
				"  , 1, 1,  ,";
		String testData2 = "name: example\n" +
				"damage pattern:  ,-1,-1,-1,  ,\n" +
				"-1, 1, 1, 1,-1,\n" +
				"  ,-1,-1,-1,  ,";
		assertEquals("Equals test with minimal data", new AssetData("example", new int[][] {{0,-1,-1,-1,0}, {-1,1,1,1,-1}, {0,-1,-1,-1,0}}), new AssetData("example", new int[][] {{0,-1,-1,-1,0}, {-1,1,1,1,-1}, {0,-1,-1,-1,0}}));
		assertEquals("Equals test with maximal data", new AssetData("atomic bomb", "built-in", 1.0d, new int[][]{{0, 1, 1, 0}, {1, 2, 2, 1}, {1, 2, 2, 1}, {0, 1, 1, 0}}), new AssetData("atomic bomb", "built-in", 1.0d, new int[][]{{0, 1, 1, 0}, {1, 2, 2, 1}, {1, 2, 2, 1}, {0, 1, 1, 0}}));
		try {
			assertEquals("Parsing test with minimal data", new AssetData("example", new int[][] {{0,-1,-1,-1,0}, {-1,1,1,1,-1}, {0,-1,-1,-1,0}}), assetResourceParser.parse(testData2));
		} catch (InvalidKeyException | KeyAlreadyExistsException | MissingDataException e) {
			e.printStackTrace();
		}
		try {
			assertEquals("Parser test with maximal data", new AssetData("atomic bomb", "built-in", 1.0d, new int[][]{{0, 1, 1, 0}, {1, 2, 2, 1}, {1, 2, 2, 1}, {0, 1, 1, 0}}), assetResourceParser.parse(testData1));
		} catch (InvalidKeyException | KeyAlreadyExistsException | MissingDataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TokenTest() {
		TextToken textToken1 = new TextToken("name");
		TextToken textToken2 = new TextToken("name");
		TextToken textToken3 = new TextToken("bla");
		assertTrue(textToken1.equals(textToken2));
		assertFalse(textToken1.equals(textToken3));
		assertFalse(textToken2.equals(textToken3));
	}
}
