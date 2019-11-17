package net.battleships.content.parser;

import net.battleships.content.parser.tokens.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A dynamic lexer for the used for the .ship and .weapon files
 */

public class Lexer {
	/**
	 * A simple Hashmap cache for the parsed data
	 */
	private HashMap<String, ArrayList<Token>> cachedData;
	/**
	 * These data tokens will be used in the parsing process
	 */
	private ArrayList<GenericDataToken> tokens = new ArrayList<>() {};
	/**
	 * These data separator tokens will be used in the parsing process
	 */
	private ArrayList<SymbolToken> separatorTokens = new ArrayList<>();

	/**
	 * A constructor that simply initializes the cache
	 */
	public Lexer() {
		this.cachedData = new HashMap<>();
	}

	/**
	 * With this method you can add a data token which will be used in the parsing process
	 * @param dataToken the data token to add
	 */
	public void addDataToken(GenericDataToken dataToken) {
		this.tokens.add(dataToken);
	}

	/**
	 * With this method you can add a data separator token which will be used in the parsing process
	 * @param symbolToken the data separator token to add
	 */
	public void addSeparatorToken(SymbolToken symbolToken) {
		this.separatorTokens.add(symbolToken);
	}

	/**
	 * The parsing method which uses the data tokens and the data separator tokens to identify specific parts of the raw data input string
	 * @param rawData the input string which will be parsed according to the data tokens and the data separator tokens
	 * @return a ArrayList with the parsed tokens
	 * @throws IllegalArgumentException will be thrown when a token ins't working correctly
	 */
	public ArrayList<Token> parse(String rawData) throws IllegalArgumentException {
		if (this.cachedData.containsKey(rawData))
			return this.cachedData.get(rawData);

		ArrayList<Token> data = new ArrayList<>();
		StringBuilder tmpString = new StringBuilder();
		boolean found;

		for (char letter : rawData.toCharArray()) {
			found = false;
			for (SymbolToken separatorToken : separatorTokens) {
				if (separatorToken.getValidSymbolsEndPattern().matcher(tmpString.toString() +letter).matches()) {
					found = true;
					String realData = tmpString.toString().replaceAll(separatorToken.getValidSymbolsEndPattern().pattern(), "");
					for (Token token : tokens)
						if (token.getValidSymbols().matcher(realData).matches()) {
							try {
								data.add(token.getClass().getConstructor(String.class).newInstance(realData));
							} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
								e.printStackTrace();
							}
							break;
						}
					try {
						data.add(separatorToken.getClass().getConstructor(String.class).newInstance(tmpString.toString() +letter));
					} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
						e.printStackTrace();
					}
					tmpString = new StringBuilder();
					break;
				}
			}
			if (!found)
				tmpString.append(letter);
		}

		this.cachedData.put(rawData, data);
		return data;
	}
}
