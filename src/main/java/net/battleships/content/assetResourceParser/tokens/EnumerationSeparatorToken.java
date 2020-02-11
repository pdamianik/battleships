package net.battleships.content.assetResourceParser.tokens;

public class EnumerationSeparatorToken implements Token {
	private String rawData;

	public EnumerationSeparatorToken() {

	}

	public EnumerationSeparatorToken(String data) {
		if (this.matches(data))
			this.rawData = data;
		else throw new IllegalArgumentException("The passed argument wasn't either \",\", \"|\" or \";\"");
	}

	@Override
	public boolean matches(CharSequence stringToMatch) {
		return ",".contentEquals(stringToMatch) || ";".contentEquals(stringToMatch) || "|".contentEquals(stringToMatch);
	}

	@Override
	public Token tokenWithData(String data) {
		return new EnumerationSeparatorToken(data);
	}

	@Override
	public Token mergeRaw(Token tokenToMergeWith) {
		return new GeneralToken(this.rawData + tokenToMergeWith.getRawData());
	}

	public KeyValueSeparatorToken mergeRaw(KeyValueSeparatorToken tokenToMergeWith) {
		return new KeyValueSeparatorToken(this.rawData + tokenToMergeWith.getRawData());
	}

	@Override
	public String getRawData() {
		return this.rawData;
	}

	@Override
	public boolean equals(Token token) {
		return this.rawData.equals(token.getRawData());
	}

	@Override
	public String toString() {
		return "enumeration separator: " + this.rawData;
	}
}
