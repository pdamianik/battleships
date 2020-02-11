package net.battleships.datatypes.exceptions;

public class InvalidValueException extends AssetResourceParserException {
	public InvalidValueException() {
		super();
	}

	public InvalidValueException(String msg) {
		super(msg);
	}
}
