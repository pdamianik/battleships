package net.battleships.content.assetResourceParser.tokens;

import java.util.regex.Pattern;

public class TextToken implements Token {
	private String rawData;
	private static final Pattern isTextPattern = Pattern.compile("(?:[\\p{L}-._]+[ \\t]*)*");

	public TextToken() {

	}

	public TextToken(String data) {
		if (this.matches(data))
			this.rawData = data;
		else throw new IllegalArgumentException("The passed argument wasn't a string of text");
	}

	@Override
	public boolean matches(CharSequence stringToMatch) {
		return isTextPattern.matcher(stringToMatch).matches();
	}

	@Override
	public Token tokenWithData(String data) {
		return new TextToken(data);
	}

	@Override
	public Token mergeRaw(Token tokenToMergeWith) {
		return new GeneralToken(this.rawData + tokenToMergeWith.getRawData());
	}

	public TextToken mergeRaw(TextToken tokenToMergeWith) {
		return new TextToken(this.rawData + tokenToMergeWith.getRawData());
	}

	@Override
	public String getRawData() {
		return this.rawData;
	}

	@Override
	public String toString() {
		return "text: " + this.rawData;
	}

	@Override
	public boolean equals(Token token) {
		return this.rawData.equals(token.getRawData());
	}

	public boolean equals(TextToken token) {
		return this.rawData.equals(token.getRawData());
	}
}
