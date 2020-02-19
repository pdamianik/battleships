package net.battleships.content.assetResourceParser;

import net.battleships.content.assetResourceParser.tokens.KeyValueSeparatorToken;
import net.battleships.content.assetResourceParser.tokens.Token;
import net.battleships.content.assetResourceParser.tokens.WhitespaceToken;
import net.battleships.datatypes.exceptions.KeyAlreadyExistsException;

import java.util.Arrays;
import java.util.List;

public class DataRow {
	private Token[] key;
	private Token[] value;
	private boolean hasKey = false;
	private boolean hasValue = false;

	public DataRow(List<Token> row) throws KeyAlreadyExistsException {
		int start = 0, i = 0, end = 0;
		boolean foundOnlyWhitespace = true;
		for (; i < row.size(); i++)
			if (row.get(i) instanceof KeyValueSeparatorToken)
				if (this.hasKey) {
					throw new KeyAlreadyExistsException("A key has already been identified");
				} else {
					List<Token> key = row.subList(start, end);
					key.toArray(this.key = new Token[key.size()]);
					start = end;
					this.hasKey = true;
					foundOnlyWhitespace = true;
				}
			else if (foundOnlyWhitespace && row.get(i) instanceof WhitespaceToken)
				start = i+1;
			else {
				foundOnlyWhitespace = false;
				end = i+1;
			}

		if (start != end) {
			List<Token> value = row.subList(start, end);
			value.toArray(this.value = new Token[value.size()]);
			this.hasValue = true;
		}
	}

	public Token[] getKey() {
		return key;
	}

	public Token[] getValue() {
		return value;
	}

	public boolean hasKey() {
		return hasKey;
	}

	public boolean hasValue() {
		return hasValue;
	}

	@Override
	public String toString() {
		return "\"" + Arrays.toString(this.key) + "\": \"" + Arrays.toString(this.value) + "\"";
	}
}
