package net.battleships.content;

import net.battleships.content.assetResourceParser.AssetResourceParser;
import net.battleships.content.assetResourceParser.tokens.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AssetResourceParserTest {
	private ArrayList<Token> expectedTestData = new ArrayList<>() {
		{
			add(new TextToken("name"));
			add(new KeyValueSeparatorToken(":"));
			add(new WhitespaceToken(" "));
			add(new TextToken("atomic bomb"));
			add(new NewLineToken("\n"));
			add(new TextToken("author"));
			add(new KeyValueSeparatorToken(":"));
			add(new WhitespaceToken(" "));
			add(new TextToken("built-in"));
			add(new NewLineToken("\n"));
			add(new TextToken("version"));
			add(new KeyValueSeparatorToken(":"));
			add(new WhitespaceToken(" "));
			add(new TextToken("v"));
			add(new DecimalToken("1.0"));
			add(new NewLineToken("\n"));
			add(new TextToken("health pattern"));
			add(new KeyValueSeparatorToken(":"));
			add(new NewLineToken("\n"));
			add(new WhitespaceToken(" "));
			add(new WhitespaceToken(" "));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(1));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(1));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new WhitespaceToken(" "));
			add(new EnumerationSeparatorToken(","));
			add(new NewLineToken("\n"));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(1));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(2));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(2));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(1));
			add(new EnumerationSeparatorToken(","));
			add(new NewLineToken("\n"));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(1));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(2));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(2));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(1));
			add(new EnumerationSeparatorToken(","));
			add(new NewLineToken("\n"));
			add(new WhitespaceToken(" "));
			add(new WhitespaceToken(" "));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(1));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new IntegerToken(1));
			add(new EnumerationSeparatorToken(","));
			add(new WhitespaceToken(" "));
			add(new WhitespaceToken(" "));
			add(new EnumerationSeparatorToken(","));
			add(new NewLineToken("\n"));
			add(new TextToken("test"));
			add(new KeyValueSeparatorToken(":"));
			add(new WhitespaceToken(" "));
			add(new TextToken("hello world"));
		}
	};

	/*
	/**
	 * A test for the intern lexer
	 *//*
	@Test
	public void lexerTest() {
		AssetResourceLexer assetResourceLexer = new AssetResourceLexer();

		String testData = "name: atomic bomb\n" +
				"author: built-in\n" +
				"version: v1.0\n" +
				"health pattern:\n" +
				"  , 1, 1,  ,\n" +
				" 1, 2, 2, 1,\n" +
				" 1, 2, 2, 1,\n" +
				"  , 1, 1,  ,\n" +
				"test: hello world";

		// parsing (inclusive time tracking, for the caching system)
		/*
		long startTime = System.nanoTime();
		*//*
		ArrayList<Token> parsedTestData = assetResourceLexer.parse(testData);
		*//*
		long endTime = System.nanoTime();
		System.out.println("Execution Time: " + (endTime - startTime));
		System.out.println("Execution time in milliseconds : " + ((endTime - startTime) / 1000000.0));

		startTime = System.nanoTime();
		assetResourceLexer.parse(testData);
		endTime = System.nanoTime();
		System.out.println("Execution Time: " + (endTime - startTime));
		System.out.println("Execution time in milliseconds : " + ((endTime - startTime) / 1000000.0));
		*//*

		for (int i = 0; i < this.expectedTestData.size(); i++) {
			assertTrue("Lexing failed: " + this.expectedTestData.get(i) + " vs " + parsedTestData.get(i), this.expectedTestData.get(i).equals(parsedTestData.get(i)));
		}
	}
	*/

	@Test
	public void parserTest() {
		AssetResourceParser assetResourceParser = new AssetResourceParser();
	}

	@Test
	public void TokenTest() {
		KeyValueSeparatorToken keyValueSeparatorToken = new KeyValueSeparatorToken(":");
		TextToken textToken1 = new TextToken("name");
		TextToken textToken2 = new TextToken("name");
		TextToken textToken3 = new TextToken("bla");
		assertTrue(textToken1.equals(textToken2));
		assertFalse(textToken1.equals(textToken3));
		assertFalse(textToken2.equals(textToken3));
	}
}
