package net.battleships.content.parser.tokens;

public class TextToken extends GenericToken {
	public TextToken(String text) {
		super.setRawContent(text);
	}

	public String getTextContent() {
		return super.getRawContent();
	}

	public void setTextContent(String text) {
		super.setRawContent(text);
	}

	@Override
	public String toString() {
		return "text token: \"" + super.getRawContent() + "\"";
	}
}
