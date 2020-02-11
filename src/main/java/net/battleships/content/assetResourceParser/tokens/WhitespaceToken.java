package net.battleships.content.assetResourceParser.tokens;

public class WhitespaceToken implements Token {
	private String rawData;

	public WhitespaceToken() {

	}

	public WhitespaceToken(String data) {
		if (this.matches(data))
			this.rawData = data;
		else
			throw new IllegalArgumentException("The data argument has to be either \\t or a space, not: \"" + data + "\"");
	}

	@Override
	public boolean matches(CharSequence stringToMatch) {
		return " ".contentEquals(stringToMatch) || "\t".contentEquals(stringToMatch);
	}

	@Override
	public WhitespaceToken tokenWithData(String data) {
		return new WhitespaceToken(data);
	}

	@Override
	public Token mergeRaw(Token tokenToMergeWith) {
		return new GeneralToken(this.rawData + tokenToMergeWith.getRawData());
	}

	@Override
	public String getRawData() {
		return this.rawData;
	}

	@Override
	public String toString() {
		if (this.rawData == null)
			return "whitespace";
		return "whitespace: \"" + (" ".equals(this.rawData) ? " " : "\\t") + "\"";
	}

	@Override
	public boolean equals(Token token) {
		return this.rawData.equals(token.getRawData());
	}
}
