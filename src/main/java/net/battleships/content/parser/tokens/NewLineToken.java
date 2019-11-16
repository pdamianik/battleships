package net.battleships.content.parser.tokens;

public class NewLineToken implements Token {
	@Override
	public String getRawContent() {
		return "\n";
	}

	@Override
	public Token merge(Token token) {
		return new TextToken(token.getRawContent() + "\n");
	}

	@Override
	public String toString() {
		return "new line token";
	}

	@Override
	public boolean equals(Token token) {
		return "\n".equals(token.getRawContent());
	}
}
