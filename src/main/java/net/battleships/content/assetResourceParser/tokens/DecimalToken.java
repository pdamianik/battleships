package net.battleships.content.assetResourceParser.tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalToken implements NumericToken {
	private String rawData;
	private double version;
	private static final Pattern isVersionPattern = Pattern.compile("(?:\\d\\.\\d)|(?:\\.\\d)|(?:\\d\\.)|(?:\\d)");

	public DecimalToken() {

	}

	public DecimalToken(String data) {
		Matcher matcher = isVersionPattern.matcher(data);
		if (matcher.matches()) {
			this.rawData = data;
			this.version = Float.parseFloat(data);
		} else throw new IllegalArgumentException("The passed argument wasn't a decimal number");
	}

	public DecimalToken(Number version) {
		this.rawData = "v" + version.toString();
		this.version = version.floatValue();
	}

	@Override
	public Number getNumericValue() {
		return this.version;
	}

	@Override
	public DecimalToken tokenWithData(Number value) {
		return new DecimalToken();
	}

	@Override
	public boolean matches(CharSequence stringToMatch) {
		return isVersionPattern.matcher(stringToMatch).matches();
	}

	@Override
	public DecimalToken tokenWithData(String data) {
		return new DecimalToken(data);
	}

	@Override
	public Token mergeRaw(Token tokenToMergeWith) {
		return new GeneralToken(this.rawData + tokenToMergeWith);
	}

	public NumericToken mergeRaw(NumericToken tokenToMergeWith) {
		return new GeneralNumericToken(this.rawData + tokenToMergeWith);
	}

	@Override
	public String getRawData() {
		return this.rawData;
	}

	@Override
	public boolean equals(Token token) {
		return this.rawData.equals(token.getRawData());
	}

	public boolean equals(NumericToken numericToken) {
		return this.version == numericToken.getNumericValue().floatValue();
	}

	@Override
	public NumericToken merge(NumericToken tokenToMergeWith) {
		return new GeneralNumericToken(this.version + tokenToMergeWith.getNumericValue().floatValue());
	}

	public DecimalToken merge(DecimalToken tokenToMergeWith) {
		return new DecimalToken(this.version + tokenToMergeWith.getNumericValue().floatValue());
	}

	@Override
	public NumericToken merge(NumericToken tokenToMergeWith, char operation) {
		switch (operation) {
			case '+':
				return new GeneralNumericToken(this.version + tokenToMergeWith.getNumericValue().floatValue());
			case '-':
				return new GeneralNumericToken(this.version - tokenToMergeWith.getNumericValue().floatValue());
			case '*':
				return new GeneralNumericToken(this.version * tokenToMergeWith.getNumericValue().floatValue());
			case '/':
				return new GeneralNumericToken(this.version / tokenToMergeWith.getNumericValue().floatValue());
			default:
				throw new IllegalArgumentException("The operation argument must be either +, -, * or /");
		}
	}

	public DecimalToken merge(DecimalToken tokenToMergeWith, char operation) {
		switch (operation) {
			case '+':
				return new DecimalToken(this.version + tokenToMergeWith.getNumericValue().floatValue());
			case '-':
				return new DecimalToken(this.version - tokenToMergeWith.getNumericValue().floatValue());
			case '*':
				return new DecimalToken(this.version * tokenToMergeWith.getNumericValue().floatValue());
			case '/':
				return new DecimalToken(this.version / tokenToMergeWith.getNumericValue().floatValue());
			default:
				throw new IllegalArgumentException("The operation argument must be either +, -, * or /");
		}
	}

	@Override
	public void add(Number value) {
		this.version += value.floatValue();
	}

	@Override
	public void subtract(Number value) {
		this.version -= value.floatValue();
	}

	@Override
	public void multiply(Number value) {
		this.version *= value.floatValue();
	}

	@Override
	public void divide(Number value) {
		this.version /= value.floatValue();
	}

	@Override
	public String toString() {
		return "version: " + this.version;
	}
}
