package net.battleships.content.parser.tokens;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericSymbolToken implements SymbolToken {
	private static final String DEFAULT_SYMBOL = " ";
	private static final Pattern VALID_SYMBOLS = Pattern.compile(" ");
	private static final Pattern VALID_SYMBOLS_END_PATTERN = Pattern.compile(".*(" + VALID_SYMBOLS.pattern() + ")$");
	private static final String SYMBOL_DESCRIPTION = "whitespace";
	private String symbol;

	public GenericSymbolToken() {
		this.symbol = this.getDefaultSymbol();
	}

	public GenericSymbolToken(String symbol) {
		this.setSymbol(symbol);
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

	public String getDefaultSymbol() {
		return DEFAULT_SYMBOL;
	}

	@Override
	public Pattern getValidSymbolsEndPattern() {
		return VALID_SYMBOLS_END_PATTERN;
	}

	public Pattern getValidSymbols() {
		return VALID_SYMBOLS;
	}

	public String getSymbolDescription() {
		return SYMBOL_DESCRIPTION;
	}

	@Override
	public void setSymbol(String symbol) {
		if (!this.getValidSymbols().matcher(symbol).matches()) {
			Matcher occurrence = this.getValidSymbolsEndPattern().matcher(symbol);
			if (!occurrence.matches())
				throw new InvalidParameterException("The symbol parameter \"" + symbol + "\" is invalid");
			symbol = occurrence.group(occurrence.groupCount());
		}
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return this.getSymbolDescription();
	}

	@Override
	public boolean equals(SymbolToken symbolToken) {
		return this.symbol == symbolToken.getSymbol();
	}

	@Override
	public String getRawContent() {
		return this.symbol + "";
	}

	@Override
	public boolean equals(Token token) {
		return this.getRawContent().equals(token.getRawContent());
	}
}
