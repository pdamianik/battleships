package net.battleships.content.parser.tokens;

import java.util.regex.Pattern;

/**
 * This class is a built-in token for floating point numbers
 * @see net.battleships.content.parser.tokens.GenericDataToken
 * @see net.battleships.content.parser.tokens.DataToken
 * @see net.battleships.content.parser.tokens.Token
 */

public class FloatToken extends GenericDataToken {
	/**
	 * The regex pattern that will be used to check if a string is valid to be used for this token
	 */
	private static final Pattern VALID_SYMBOLS = Pattern.compile("-?((?:\\d+(?:\\.\\d*)?)|(?:\\d*\\.\\d+))[f|d|F|D]?");
	/**
	 * The numeric value of this token
	 */
	private double floatContent;

	/**
	 * The parameterless constructor (calls FloatToken("0") -> sets the value to zero)
	 */
	public FloatToken() {
		this("0");
	}

	/**
	 * A constructor which parses the floating point number from a string
	 * @param rawContent The floating point number in a string
	 * @throws NumberFormatException will be thrown when the parameter isn't a string containing a floating point number (will be checked with the VALID_SYMBOLS pattern)
	 */
	public FloatToken(String rawContent) throws NumberFormatException {
		super(rawContent);
		this.floatContent = Double.parseDouble(rawContent);
	}

	/**
	 * The getter method for the VALID_SYMBOLS pattern
	 * @return the pattern that is being used for checking if a string contains a floating point number
	 */
	public Pattern getValidSymbols() {
		return VALID_SYMBOLS;
	}

	/**
	 * The setter method for the raw content of this token (a string that contains a floating point number)
	 * @param rawContent The floating point number in a string
	 * @throws NumberFormatException will be thrown when the parameter isn't a string containing a floating point number (will be checked with the VALID_SYMBOLS pattern)
	 */
	public void setRawContent(String rawContent) throws NumberFormatException {
		super.setRawContent(rawContent);
		this.floatContent = Double.parseDouble(rawContent);
	}

	/**
	 * The getter method for float content
	 * @return the numeric value of the floating point number parsed from the rawContent string
	 */
	public double getFloatContent() {
		return floatContent;
	}

	/**
	 * This merging method merges the values of two FloatTokens
	 * @param token the other float token
	 * @return the merged token
	 */
	public FloatToken merge(FloatToken token) {
		return new FloatToken((this.floatContent + token.getFloatContent()) + "");
	}

	/**
	 * This method will be used when a token is outputted as a string
	 * @return the string representation of this object
	 * @see java.lang.Object
	 */
	@Override
	public String toString() {
		return "integer: " + super.getRawContent();
	}
}
