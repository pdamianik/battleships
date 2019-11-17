package net.battleships.content;

import net.battleships.content.parser.Lexer;
import net.battleships.content.parser.tokens.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LexerTest {
	/**
	 * A test for the intern lexer
	 */
	@Test
	public void parseTest() {
		Lexer lexer = new Lexer();

		lexer.addDataToken(new VersionToken());
		//lexer.addDataToken(new FloatToken());
		lexer.addDataToken(new IntegerToken());
		lexer.addDataToken(new TextToken());
		lexer.addDataToken(new GenericDataToken());

		lexer.addSeparatorToken(new NewLineToken());
		lexer.addSeparatorToken(new EnumerationSeparatorToken());
		lexer.addSeparatorToken(new KeyValueSeparatorToken());
		lexer.addSeparatorToken(new WhitespaceToken());
		lexer.addSeparatorToken(new GenericSymbolToken());

		String testData = "name: atomic bomb\n" +
				"author: built-in\n" +
				"version: v1.0\n" +
				"  , 1, 1,  ,\n" +
				" 1, 2, 2, 1,\n" +
				" 1, 2, 2, 1,\n" +
				"  , 1, 1,  ,";

		// parsing (inclusive time tracking, for the caching system)
		/*
		long startTime = System.nanoTime();*/
		ArrayList<Token> parsedTestData = lexer.parse(testData);/*
		long endTime = System.nanoTime();
		System.out.println("Execution Time: " + (endTime - startTime));
		System.out.println("Execution time in milliseconds : " + ((endTime - startTime) / 1000000.0));

		startTime = System.nanoTime();
		lexer.parse(testData);
		endTime = System.nanoTime();
		System.out.println("Execution Time: " + (endTime - startTime));
		System.out.println("Execution time in milliseconds : " + ((endTime - startTime) / 1000000.0));*/

		ArrayList<Token> expectedTestData = new ArrayList<>() {
			{
				add(new TextToken("name"));
				add(new KeyValueSeparatorToken(":"));
				add(new WhitespaceToken());
				add(new TextToken("atomic"));
				add(new WhitespaceToken());
				add(new TextToken("bomb"));
				add(new NewLineToken());

				add(new TextToken("author"));
				add(new KeyValueSeparatorToken(":"));
				add(new WhitespaceToken());
				add(new TextToken("built-in"));
				add(new NewLineToken());

				add(new TextToken("version"));
				add(new KeyValueSeparatorToken(":"));
				add(new WhitespaceToken());
				add(new VersionToken("v1.0"));
				add(new NewLineToken());

				add(new WhitespaceToken());
				add(new WhitespaceToken());
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("1"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("1"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new WhitespaceToken());
				add(new EnumerationSeparatorToken(","));
				add(new NewLineToken());

				add(new WhitespaceToken());
				add(new IntegerToken("1"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("2"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("2"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("1"));
				add(new EnumerationSeparatorToken(","));
				add(new NewLineToken());

				add(new WhitespaceToken());
				add(new IntegerToken("1"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("2"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("2"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("1"));
				add(new EnumerationSeparatorToken(","));
				add(new NewLineToken());

				add(new WhitespaceToken());
				add(new WhitespaceToken());
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("1"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new IntegerToken("1"));
				add(new EnumerationSeparatorToken(","));
				add(new WhitespaceToken());
				add(new WhitespaceToken());
				add(new EnumerationSeparatorToken(","));
			}
		};

		//System.out.println(parsedTestData);

		for (int i = 0; i < expectedTestData.size(); i++) {
			assertTrue("Lexing failed", expectedTestData.get(i).equals(parsedTestData.get(i)));
		}
	}
}
