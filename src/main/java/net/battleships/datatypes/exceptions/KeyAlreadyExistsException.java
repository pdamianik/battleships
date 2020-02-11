package net.battleships.datatypes.exceptions;

public class KeyAlreadyExistsException extends AssetResourceParserException {
	public KeyAlreadyExistsException() {
		super();
	}

	public KeyAlreadyExistsException(String msg) {
		super(msg);
	}
}
