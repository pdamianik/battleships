package net.battleships.content.parser.tokens;

public class IntegerToken extends GenericToken {
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

	public GenericToken mergeRaw(Token token) {
		return super.merge(token);
	}

	@Override
	public String toString() {
		return "integer token: " + super.getRawContent();
	}
}
