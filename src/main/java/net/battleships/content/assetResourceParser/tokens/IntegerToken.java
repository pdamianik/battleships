package net.battleships.content.assetResourceParser.tokens;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class IntegerToken implements NumericToken {
	private String rawData;
	private int numericValue;
	private static final Pattern isIntegerPattern = Pattern.compile("(?:[+\\-]\\d)|\\d+|[+\\\\-]");

	public IntegerToken() {

	}

	public IntegerToken(String data) {
		if (this.matches(data)) {
			this.rawData = data;
			this.numericValue = Integer.parseInt(data);
		}
	}

	public IntegerToken(Number value) {
		this.rawData = value.toString();
		this.numericValue = value.intValue();
	}

	@Override
	public boolean matches(CharSequence stringToMatch) {
		return isIntegerPattern.matcher(stringToMatch).matches();
	}

	@Override
	public Number getNumericValue() {
		return this.numericValue;
	}

	@Override
	public NumericToken tokenWithData(Number value) {
		return new IntegerToken(value);
	}

	@Override
	public IntegerToken tokenWithData(String data) {
		return new IntegerToken(data);
	}

	@Override
	public NumericToken merge(NumericToken tokenToMergeWith) {
		return new GeneralNumericToken(new BigDecimal(this.numericValue + tokenToMergeWith.getNumericValue().intValue()));
	}

	public IntegerToken merge(IntegerToken tokenToMergeWith) {
		return new IntegerToken(this.numericValue + tokenToMergeWith.getNumericValue().intValue());
	}

	@Override
	public NumericToken merge(NumericToken tokenToMergeWith, char operation) {
		switch (operation) {
			case '+':
				return new GeneralNumericToken(new BigDecimal(this.numericValue + tokenToMergeWith.getNumericValue().intValue()));
			case '-':
				return new GeneralNumericToken(new BigDecimal(this.numericValue - tokenToMergeWith.getNumericValue().intValue()));
			case '*':
				return new GeneralNumericToken(new BigDecimal(this.numericValue * tokenToMergeWith.getNumericValue().intValue()));
			case '/':
				return new GeneralNumericToken(new BigDecimal(this.numericValue / tokenToMergeWith.getNumericValue().intValue()));
			default:
				throw new IllegalArgumentException("The operation argument must be either +, -, * or /");
		}
	}

	@Override
	public void add(Number value) {
		this.numericValue += value.intValue();
	}

	@Override
	public void subtract(Number value) {
		this.numericValue -= value.intValue();
	}

	@Override
	public void multiply(Number value) {
		this.numericValue *= value.intValue();
	}

	@Override
	public void divide(Number value) {
		this.numericValue /= value.intValue();
	}

	@Override
	public boolean equals(NumericToken numericToken) {
		return this.numericValue == numericToken.getNumericValue().intValue();
	}


	public void add(int value) {
		this.numericValue += value;
	}

	public void subtract(int value) {
		this.numericValue -= value;
	}

	public void multiply(int value) {
		this.numericValue *= value;
	}

	public void divide(int value) {
		this.numericValue /= value;
	}

	public IntegerToken merge(IntegerToken tokenToMergeWith, char operation) {
		switch (operation) {
			case '+':
				return this.merge(tokenToMergeWith);
			case '-':
				return new IntegerToken(this.numericValue - tokenToMergeWith.getNumericValue().intValue());
			case '*':
				return new IntegerToken(this.numericValue * tokenToMergeWith.getNumericValue().intValue());
			case '/':
				return new IntegerToken(this.numericValue / tokenToMergeWith.getNumericValue().intValue());
			default:
				throw new IllegalArgumentException("The operation argument must be either +, -, * or /");
		}
	}

	@Override
	public Token mergeRaw(Token tokenToMergeWith) {
		return new GeneralToken(this.rawData + tokenToMergeWith.getRawData());
	}

	public IntegerToken mergeRaw(IntegerToken tokenToMergeWith) {
		return new IntegerToken(this.rawData + tokenToMergeWith.getRawData());
	}

	public GeneralNumericToken mergeRaw(NumericToken tokenToMergeWith) {
		return new GeneralNumericToken(this.rawData + tokenToMergeWith.getRawData());
	}

	@Override
	public String getRawData() {
		return this.rawData;
	}

	@Override
	public boolean equals(Token token) {
		return this.rawData.equals(token.getRawData());
	}

	@Override
	public String toString() {
		return "integer: " + this.rawData;
	}
}
