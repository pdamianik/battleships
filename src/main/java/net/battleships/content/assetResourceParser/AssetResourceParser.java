package net.battleships.content.assetResourceParser;

import net.battleships.content.assetResourceParser.tokens.*;
import net.battleships.datatypes.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AssetResourceParser {
	private HashMap<String, AssetData> cachedData = new HashMap<>();
	private static HashMap<String, Integer> keyToState = new HashMap<>() {
		{
			put("name", 0);
			put("author", 1);
			put("version", 2);
			put("damage pattern", 3);
		}
	};
	private AssetResourceLexer lexer;

	public AssetResourceParser() {
		this.lexer = new AssetResourceLexer();

		this.lexer.addToken(Arrays.asList(
				new EnumerationSeparatorToken(),
				new IntegerToken(),
				new KeyValueSeparatorToken(),
				new NewLineToken(),
				new TextToken(),
				new DecimalToken(),
				new WhitespaceToken()
		));
	}

	public AssetData parse(String rawData) throws InvalidKeyException, KeyAlreadyExistsException, InvalidValueException {
		if (this.cachedData.containsKey(rawData))
			return this.cachedData.get(rawData);
		AssetData result = this.parse(this.lexer.parse(rawData));
		this.cachedData.put(rawData, result);
		return result;
	}

	AssetData parse(ArrayList<Token> tokens) throws InvalidKeyException, KeyAlreadyExistsException, InvalidValueException {
		AssetData result = new AssetData();
		int type = -1;
		int state = 0;
		int currentNumber = 0;
		int[][] arrayData;

		for (Token token : tokens) {
			if (state == 0) {
				if (token.getClass().equals(TextToken.class)) {
					if (!keyToState.containsKey(token.getRawData()))
						throw new InvalidKeyException("The key " + token.getRawData() + " is invalid");
					type = keyToState.get(token.getRawData());
					state++;
				}
			} else if (state == 1) {
				if (token.getClass().equals(KeyValueSeparatorToken.class))
					state++;
			} else if (state == 2) {
				switch (type) {
					case 0:
						if (token.getClass().equals(TextToken.class)) {
							if (result.getName() == null)
								result.setName(token.getRawData());
							else
								throw new KeyAlreadyExistsException("The name key already exists");
							state++;
							continue;
						} else throw new InvalidValueException();
					case 1:
						if (token.getClass().equals(TextToken.class)) {
							if (result.getAuthor() == null)
								result.setAuthor(token.getRawData());
							else
								throw new KeyAlreadyExistsException("The author key already exists");
							state++;
							continue;
						} else throw new InvalidValueException();
					case 2:
						if (token.getClass().equals(DecimalToken.class)) {
							if (result.getVersion() == 0d)
								result.setVersion(((DecimalToken) token).getNumericValue().doubleValue());
							else
								throw new KeyAlreadyExistsException("The version key already exists");
							state++;
							continue;
						} else throw new InvalidValueException();
					case 3:
						if (token.getClass().equals(IntegerToken.class)) {

						}
				}
			} else {

			}
		}

		return result;
	}
}
