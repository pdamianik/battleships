package net.battleships.content.parser.tokens;

public class TextToken extends GenericDataToken {
	public TextToken() { super(); }

	public TextToken(String text) {
		super.setRawContent(text);
	}

	public TextToken(DataToken token) { super.setRawContent(token.getRawContent()); }

	public String getTextContent() {
		return super.getRawContent();
	}

	public void setTextContent(String text) {
		super.setRawContent(text);
	}

	@Override
	public String toString() {
		return "text: \"" + super.getRawContent() + "\"";
	}
}
