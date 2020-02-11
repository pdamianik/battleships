package net.battleships.content.assetResourceParser.tokens;

public interface NumericToken extends Token {
	Number getNumericValue();
	NumericToken tokenWithData(Number value);
	@Override
	NumericToken tokenWithData(String data);
	NumericToken mergeRaw(NumericToken tokenToMergeWith);
	NumericToken merge(NumericToken tokenToMergeWith);
	NumericToken merge(NumericToken tokenToMergeWith, char operation);
	void add(Number value);
	void subtract(Number value);
	void multiply(Number value);
	void divide(Number value);
	boolean equals(NumericToken numericToken);
}
