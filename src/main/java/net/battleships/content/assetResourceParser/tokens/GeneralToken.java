package net.battleships.content.assetResourceParser.tokens;

public class GeneralToken implements Token {
	private String rawData;

	public GeneralToken() {

	}

	public GeneralToken(String data) {
		this.rawData = data;
	}

	@Override
	public boolean matches(CharSequence stringToMatch) {
		return true;
	}

	@Override
	public Token tokenWithData(String data) {
		return new GeneralToken(data);
	}

	@Override
	public Token mergeRaw(Token tokenToMergeWith) {
		return new GeneralToken(tokenToMergeWith.getRawData());
	}

	@Override
	public String getRawData() {
		return this.rawData;
	}

	@Override
	public String toString() {
		return "general: " + this.rawData;
	}

	@Override
	public boolean equals(Token token) {
		return this.rawData.equals(token.getRawData());
	}
}
