package net.battleships.content.parser;

import net.battleships.content.parser.tokens.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class Lexer {
	private HashMap<String, ArrayList<Token>> cachedData;
	private ArrayList<GenericDataToken> tokens = new ArrayList<>() {};
	private ArrayList<SymbolToken> separatorTokens = new ArrayList<>();

	public Lexer() {
		this.cachedData = new HashMap<>();
	}

	public void addDataToken(GenericDataToken dataToken) {
		this.tokens.add(dataToken);
	}

	public void addSeparatorToken(SymbolToken symbolToken) {
		this.separatorTokens.add(symbolToken);
	}

	public ArrayList<Token> parse(String rawData) throws IllegalArgumentException {
		if (this.cachedData.containsKey(rawData))
			return this.cachedData.get(rawData);

		ArrayList<Token> data = new ArrayList<>();
		String tmpString = "";
		boolean found;

		for (char letter : rawData.toCharArray()) {
			found = false;
			for (SymbolToken separatorToken : separatorTokens) {
				if (separatorToken.getValidSymbolsEndPattern().matcher(tmpString+letter).matches()) {
					found = true;
					String realData = tmpString.replaceAll(separatorToken.getValidSymbolsEndPattern().pattern(), "");
					for (Token token : tokens)
						if (token.getValidSymbols().matcher(realData).matches()) {
							try {
								data.add(token.getClass().getConstructor(String.class).newInstance(realData));
							} catch (InstantiationException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							break;
						}
					try {
						data.add(separatorToken.getClass().getConstructor(String.class).newInstance(tmpString+letter));
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					tmpString = "";
					break;
				}
			}
			if (!found)
				tmpString += letter;
		}

		this.cachedData.put(rawData, data);
		return data;
	}
}
