package net.battleships.content.parser.tokens;

public interface Token {
	public String getRawContent();
	public Token merge(Token token);
	public boolean equals(Token token);
}
