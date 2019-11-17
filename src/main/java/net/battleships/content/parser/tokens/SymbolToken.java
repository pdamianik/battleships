package net.battleships.content.parser.tokens;

import java.util.regex.Pattern;

public interface SymbolToken extends Token {
	String getSymbol();
	void setSymbol(String symbol);
	Pattern getValidSymbolsEndPattern();
	boolean equals(SymbolToken token);
}
