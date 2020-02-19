package net.battleships.datatypes.exceptions;

public class MissingDataException extends AssetResourceParserException {
	public MissingDataException() {
		super();
	}

	public MissingDataException(String msg) {
		super(msg);
	}
}
