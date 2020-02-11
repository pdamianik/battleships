package net.battleships.content.assetResourceParser.tokens;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class GeneralNumericToken implements NumericToken {
	private String rawData;
	private BigDecimal numericValue;
	private static final Pattern isNumericPattern = Pattern.compile("[+-]?\\d*\\.?\\d+");

	public GeneralNumericToken() {

	}

	public GeneralNumericToken(String data) {
		if (this.matches(data)) {
			this.rawData = data;
			this.numericValue = new BigDecimal(data);
		} else
			throw new IllegalArgumentException("The argument must be the string representation of any number");
	}

	public GeneralNumericToken(BigDecimal value) {
		this.rawData = value.toString();
		this.numericValue = value;
	}

	public GeneralNumericToken(Number value) {
		this.rawData = value.toString();
		this.numericValue = BigDecimal.valueOf(value.doubleValue());
	}

	@Override
	public Number getNumericValue() {
		return this.numericValue;
	}

	@Override
	public GeneralNumericToken tokenWithData(Number value) {
		return new GeneralNumericToken(value);
	}

	public GeneralNumericToken tokenWithData(BigDecimal value) {
		return new GeneralNumericToken(value);
	}

	@Override
	public boolean matches(CharSequence stringToMatch) {
		return isNumericPattern.matcher(stringToMatch).matches();
	}

	@Override
	public NumericToken tokenWithData(String data) {
		return new GeneralNumericToken(data);
	}

	@Override
	public Token mergeRaw(Token tokenToMergeWith) {
		return new GeneralToken(this.rawData + tokenToMergeWith.getRawData());
	}

	public NumericToken mergeRaw(NumericToken tokenToMergeWith) {
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
	public NumericToken merge(NumericToken tokenToMergeWith) {
		return new GeneralNumericToken(this.numericValue.add(BigDecimal.valueOf(tokenToMergeWith.getNumericValue().doubleValue())));
	}

	@Override
	public NumericToken merge(NumericToken tokenToMergeWith, char operation) {
		switch (operation) {
			case '+':
				return this.merge(tokenToMergeWith);
			case '-':
				return new GeneralNumericToken(this.numericValue.subtract(BigDecimal.valueOf(tokenToMergeWith.getNumericValue().doubleValue())));
			case '*':
				return new GeneralNumericToken(this.numericValue.multiply(BigDecimal.valueOf(tokenToMergeWith.getNumericValue().doubleValue())));
			case '/':
				return new GeneralNumericToken(this.numericValue.divide(BigDecimal.valueOf(tokenToMergeWith.getNumericValue().doubleValue())));
			default:
				throw new IllegalArgumentException("The operation argument must be either +, -, * or /");
		}
	}

	@Override
	public void add(Number value) {
		this.numericValue = this.numericValue.add(BigDecimal.valueOf(value.doubleValue()));
		this.rawData = this.numericValue.toString();
	}

	@Override
	public void subtract(Number value) {
		this.numericValue = this.numericValue.subtract(BigDecimal.valueOf(value.doubleValue()));
		this.rawData = this.numericValue.toString();
	}

	@Override
	public void multiply(Number value) {
		this.numericValue = this.numericValue.subtract(BigDecimal.valueOf(value.doubleValue()));
		this.rawData = this.numericValue.toString();
	}

	@Override
	public void divide(Number value) {
		this.numericValue = this.numericValue.subtract(BigDecimal.valueOf(value.doubleValue()));
		this.rawData = this.numericValue.toString();
	}

	@Override
	public boolean equals(NumericToken numericToken) {
		return this.numericValue.equals(numericToken.getNumericValue());
	}

	@Override
	public String toString() {
		return "general: " + this.rawData;
	}
}
