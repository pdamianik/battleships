package net.battleships.content.parser.tokens;

import java.util.regex.Pattern;

/**
 * This class is a built-in token for separators for enumerations (, or ; or |)
 * @see net.battleships.content.parser.tokens.GenericSymbolToken
 * @see net.battleships.content.parser.tokens.SymbolToken
 * @see net.battleships.content.parser.tokens.Token
 */
public class EnumerationSeparatorToken extends GenericSymbolToken {
	/**
	 * The default separator used for the parameterless constructor
	 */
	private static final String DEFAULT_SYMBOL = ",";
	/**
	 * The pattern used to find the separators at the end of a string
	 */
	private static final Pattern VALID_SYMBOLS_END_PATTERN = Pattern.compile(".*([,;|])$");
	/**
	 * The pattern used to check if a separator is valid
	 */
	private static final Pattern VALID_SYMBOLS = Pattern.compile("[,;|]");
	/**
	 * The description which will be outputted in the toString() method
	 */
	private static final String SYMBOL_DESCRIPTION = "new line";

	/**
	 * The parameterless constructor (sets the separator symbol to the value of DEFAULT_SYMBOL)
	 */
	public EnumerationSeparatorToken() {
		this.setSymbol(this.getDefaultSymbol());
	}

	/**
	 * The constructor, which parses the separator from a char parameter
	 * @param separator the enumeration separator (will be checked)
	 */
	public EnumerationSeparatorToken(char separator) {
		this.setSymbol(separator + "");
	}

	/**
	 * The constructor, which parses the separator from a String parameter
	 * @param separator the enumeration separator (will be checked)
	 */
	public EnumerationSeparatorToken(String separator) {
		this.setSymbol(separator);
	}

	/**
	 * Getter for the constant VALID_SYMBOLS_END_PATTERN
	 * @return the VALID_SYMBOLS_END_PATTERN
	 */
	@Override
	public Pattern getValidSymbolsEndPattern() {
		return VALID_SYMBOLS_END_PATTERN;
	}

	/**
	 * Getter for the constant DEFAULT_SYMBOL
	 * @return the DEFAULT_SYMBOL
	 */
	public String getDefaultSymbol() {
		return DEFAULT_SYMBOL;
	}

	/**
	 * Getter for the constant VALID_SYMBOLS
	 * @return the VALID_SYMBOLS
	 */
	public Pattern getValidSymbols() {
		return VALID_SYMBOLS;
	}

	/**
	 * Getter for the constant SYMBOL_DESCRIPTION
	 * @return the SYMBOL_DESCRIPTION
	 */
	public String getSymbolDescription() {
		return SYMBOL_DESCRIPTION;
	}

	/**
	 * This method will be used when a token is outputted as a string
	 * @return the string representation of this object
	 * @see java.lang.Object
	 */
	@Override
	public String toString() {
		return "Enumeration Separator: " + this.getSymbol();
	}
}
