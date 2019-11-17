package net.battleships.content.parser.tokens;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public class GenericDataToken implements DataToken {
	private String rawContent;
	private static final Pattern VALID_SYMBOLS = Pattern.compile(".+");

	public GenericDataToken() {
		this.rawContent = "";
	}

	public GenericDataToken(String rawContent) {
		this.setRawContent(rawContent);
	}

	public GenericDataToken(DataToken dataToken) {
		this.setRawContent(dataToken.getRawContent());
	}

	public Pattern getValidSymbols() {
		return VALID_SYMBOLS;
	}

	@Override
	public String getRawContent() {
		return rawContent;
	}

	public void setRawContent(String rawContent) {
		if (!getValidSymbols().matcher(rawContent).matches())
			throw new InvalidParameterException("The rawContent \"" + rawContent + "\" parameter is is invalid");
		this.rawContent = rawContent;
	}

	public DataToken merge(DataToken dataToken) {
		return new GenericDataToken(this.rawContent + dataToken.getRawContent());
	}

	public GenericDataToken mergeRaw(DataToken dataToken) {
		return new GenericDataToken(this.rawContent + dataToken.getRawContent());
	}

	@Override
	public String toString() {
		return "generic: " + this.rawContent;
	}

	public boolean equals(DataToken dataToken) {
		return this.getRawContent().equals(dataToken.getRawContent());
	}

	@Override
	public boolean equals(Token token) {
		return this.rawContent.equals(token.getRawContent());
	}
}
