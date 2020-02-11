package net.battleships.content.assetResourceParser.tokens;

/**
 * An interface for the tokens used in the
 * AssetResourceParser
 * @see GeneralToken for an example
 * @author Philip Damianik
 * @version 2020-02-01
 */

public interface Token {
	/**
	 * Tells the passed string fits the requirements
	 * to be a token of this tokens type
	 * @param stringToMatch the string to check
	 * @return true, if the requirements are met
	 */
	boolean matches(CharSequence stringToMatch);

	/**
	 * Creates a new Token object of this tokens type
	 * if the requirements are met (checked with the
	 * matches method)
	 * @param data the data for the new token
	 * @return the generated token containing the
	 * passed data
	 */
	Token tokenWithData(String data);

	/**
	 * Merges to tokens raw data (their source strings)
	 * together. Output format:<br>
	 * <code>[this tokens data string] + [other tokens data string]</code>
	 * To merge a specific token type with one of it's type (for example
	 * an integer token) to specially merge the two tokens (to add the
	 * integer values of the tokens together for example) create a
	 * merge-method
	 * @param tokenToMergeWith the token who merges with
	 *                         this token
	 * @return the merged token
	 */
	Token mergeRaw(Token tokenToMergeWith);

	/**
	 * Getter-method for the raw content of this token
	 * @return the raw content of this token
	 */
	String getRawData();

	/**
	 * The to String value will be used to get the type of this token as a
	 * string
	 * @return the type of this token as a string
	 */
	@Override
	String toString();

	boolean equals(Token token);
}
