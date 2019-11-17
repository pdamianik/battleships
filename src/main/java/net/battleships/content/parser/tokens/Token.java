package net.battleships.content.parser.tokens;

import java.util.regex.Pattern;

public interface Token {
	Pattern getValidSymbols();
	String getRawContent();
	String toString();
	boolean equals(Token token);
}
