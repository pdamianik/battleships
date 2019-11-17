package net.battleships.content.parser.tokens;

import java.util.regex.Pattern;

public class IntegerToken extends GenericDataToken {
	private static final Pattern VALID_SYMBOLS = Pattern.compile("-?\\d+");
	private int integerContent;

	public IntegerToken() {
		super();
		this.integerContent = 0;
	}

	public IntegerToken(int integer) {
		super(Integer.toString(integer));
		this.integerContent = integer;
	}

	public IntegerToken(String rawContent) throws NumberFormatException {
		super(rawContent);
		this.integerContent = Integer.parseInt(rawContent);
	}

	public Pattern getValidSymbols() {
		return VALID_SYMBOLS;
	}

	public void setRawContent(String rawContent) throws NumberFormatException {
		super.setRawContent(rawContent);
		this.integerContent = Integer.parseInt(rawContent);
	}

	public void setIntegerContent(int integerContent) {
		super.setRawContent(Integer.toString(integerContent));
		this.integerContent = integerContent;
	}

	public int getIntegerContent() {
		return integerContent;
	}

	public IntegerToken merge(IntegerToken token) {
		return new IntegerToken(this.integerContent + token.getIntegerContent());
	}

	@Override
	public String toString() {
		return "integer: " + super.getRawContent();
	}
}
