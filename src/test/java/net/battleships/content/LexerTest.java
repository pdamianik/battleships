package net.battleships.content;

import net.battleships.content.parser.Lexer;
import net.battleships.content.parser.tokens.IntegerToken;
import net.battleships.content.parser.tokens.NewLineToken;
import net.battleships.content.parser.tokens.TextToken;
import net.battleships.content.parser.tokens.Token;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LexerTest {
	@Test
	public void parseTest() {
		Lexer lexer = new Lexer();
		String testData = "atomic bomb\n" +
				"  , 1, 1,  ,\n" +
				" 1, 2, 2, 1,\n" +
				" 1, 2, 2, 1,\n" +
				"  , 1, 1,  ,";

		ArrayList<Token> parsedTestData = lexer.parse(testData);
		ArrayList<Token> expectedTestData = new ArrayList<>() {
			{
				add(new TextToken("atomic bomb"));
				add(new NewLineToken());
				add(new IntegerToken(0));
				add(new IntegerToken(1));
				add(new IntegerToken(1));
				add(new IntegerToken(0));
				add(new NewLineToken());
				add(new IntegerToken(1));
				add(new IntegerToken(2));
				add(new IntegerToken(2));
				add(new IntegerToken(1));
				add(new NewLineToken());
				add(new IntegerToken(1));
				add(new IntegerToken(2));
				add(new IntegerToken(2));
				add(new IntegerToken(1));
				add(new NewLineToken());
				add(new IntegerToken(0));
				add(new IntegerToken(1));
				add(new IntegerToken(1));
				add(new IntegerToken(0));
				add(new NewLineToken());
			}
		};

		for (int i = 0; i < expectedTestData.size(); i++) {
			assertTrue("Lexing failed", expectedTestData.get(i).equals(parsedTestData.get(i)));
		}
	}
}
