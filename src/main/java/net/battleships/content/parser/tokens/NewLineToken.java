package net.battleships.content.parser.tokens;

import java.util.regex.Pattern;

public class NewLineToken extends GenericSymbolToken {
	private static final String DEFAULT_SYMBOL = "\n";
	private static final Pattern VALID_SYMBOLS_END_PATTERN = Pattern.compile(".*(\\n)$");
	private static final Pattern VALID_SYMBOLS = Pattern.compile("\\n");
	private static final String SYMBOL_DESCRIPTION = "new line";

	public NewLineToken() {

	}

	public NewLineToken(String symbol) {

	}

	@Override
	public Pattern getValidSymbolsEndPattern() {
		return VALID_SYMBOLS_END_PATTERN;
	}

	@Override
	public String getDefaultSymbol() {
		return DEFAULT_SYMBOL;
	}

	@Override
	public Pattern getValidSymbols() {
		return VALID_SYMBOLS;
	}

	@Override
	public String getSymbolDescription() {
		return SYMBOL_DESCRIPTION;
	}
}
