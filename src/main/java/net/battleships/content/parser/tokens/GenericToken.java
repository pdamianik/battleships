package net.battleships.content.parser.tokens;

public class GenericToken implements Token {
	private String rawContent;

	public GenericToken() {
		this.rawContent = "";
	}

	public GenericToken(String rawContent) {
		this.rawContent = rawContent;
	}

	public GenericToken(Token token) {
		this.rawContent = token.getRawContent();
	}

	@Override
	public String getRawContent() {
		return rawContent;
	}

	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}

	@Override
	public GenericToken merge(Token token) {
		return new GenericToken(this.rawContent + token.getRawContent());
	}

	@Override
	public String toString() {
		return "generic token: " + this.rawContent;
	}

	public boolean equals (Token token) {
		return this.getRawContent().equals(token.getRawContent());
	}
}
