package net.battleships.content.parser.tokens;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public class KeyValueSeparatorToken extends GenericSymbolToken {
	private static final String DEFAULT_SYMBOL = ":";
	private static final Pattern VALID_SYMBOLS_END_PATTERN = Pattern.compile(".*(:|->)$");
	private static final Pattern VALID_SYMBOLS = Pattern.compile(":|->");
	private static final String SYMBOL_DESCRIPTION = "new line";

	public KeyValueSeparatorToken() {
		this.setSymbol(this.getDefaultSymbol());
	}

	public KeyValueSeparatorToken(char separator) {
		this.setSymbol(separator + "");
	}

	public KeyValueSeparatorToken(String separator) {
		this.setSymbol(separator);
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

	@Override
	public String toString() {
		return "key value separator: " + this.getSymbol();
	}
}
