package net.battleships.content.parser.tokens;

/**
 * A interface for a basic token that contains dynamic data (like text or numbers).
 * This interface should not be used for custom tokens; use the class GenericDataToken instead.
 * @see net.battleships.content.parser.tokens.GenericDataToken
 * @see net.battleships.content.parser.tokens.Token
 */
public interface DataToken extends Token {
	void setRawContent(String rawContent);
	DataToken merge(DataToken dataToken);
	GenericDataToken mergeRaw(DataToken dataToken);
	boolean equals(DataToken dataToken);
}
