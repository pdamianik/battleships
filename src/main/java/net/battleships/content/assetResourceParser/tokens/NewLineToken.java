package net.battleships.content.assetResourceParser.tokens;

public class NewLineToken implements Token {
	private String rawData;

	public NewLineToken() {

	}

	public NewLineToken(String data) {
		if (this.matches(data))
			this.rawData = data;
		else
			throw new IllegalArgumentException("The data argument has to be a \\n or \\r\\n (newline)");
	}

	@Override
	public boolean matches(CharSequence stringToMatch) {
		return "\n".contentEquals(stringToMatch) || "\r\n".contentEquals(stringToMatch);
	}

	@Override
	public NewLineToken tokenWithData(String data) {
		return new NewLineToken(data);
	}

	@Override
	public Token mergeRaw(Token tokenToMergeWith) {
		return new GeneralToken(this.rawData + tokenToMergeWith.getRawData());
	}

	public NewLineToken mergeRaw(NewLineToken tokenToMergeWith) {
		return new NewLineToken(this.rawData + tokenToMergeWith.getRawData());
	}

	@Override
	public String getRawData() {
		return this.rawData;
	}

	@Override
	public String toString() {
		if (this.rawData == null) return "new line";
		return "new line: " + (this.rawData.equals("\n") ? "\\n" : "\\r\\n");
	}

	@Override
	public boolean equals(Token token) {
		return this.rawData.equals(token.getRawData());
	}

	boolean equals(NewLineToken token) {
		return this.rawData.equals(token.rawData);
	}
}
