package net.battleships.content.parser.tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionToken extends GenericDataToken {
	private static final Pattern VALID_SYMBOLS = Pattern.compile("v(\\d+(?:[\\.][0-9]+)*)");
	private String versionIdentifier;

	public VersionToken() {
		super();
		this.versionIdentifier = "v1.0";
	}

	public VersionToken(String rawContent) throws NumberFormatException {
		this.setRawContent(rawContent);
	}

	public Pattern getValidSymbols() {
		return VALID_SYMBOLS;
	}

	public void setRawContent(String rawContent) throws NumberFormatException {
		super.setRawContent(rawContent);
		Matcher occurrences = VALID_SYMBOLS.matcher(rawContent);
		if (occurrences.matches())
			this.versionIdentifier = occurrences.group(occurrences.groupCount());
		else
			throw new IllegalArgumentException("The rawContent pattern has to match the regex pattern: \"" + VALID_SYMBOLS.pattern() + "\"");
	}

	public void setVersionIdentifier(String versionIdentifier) {
		this.setRawContent(versionIdentifier);
	}

	public String getVersionIdentifier() {
		return this.versionIdentifier;
	}

	@Override
	public String toString() {
		return "version: " + super.getRawContent();
	}
}
