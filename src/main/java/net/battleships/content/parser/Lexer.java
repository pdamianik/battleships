package net.battleships.content.parser;

import net.battleships.content.parser.tokens.IntegerToken;
import net.battleships.content.parser.tokens.NewLineToken;
import net.battleships.content.parser.tokens.TextToken;
import net.battleships.content.parser.tokens.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class Lexer {
	private HashMap<String, ArrayList<Token>> cachedData;

	public Lexer() {
		this.cachedData = new HashMap<>();
	}

	public ArrayList<Token> parse(String rawData) throws IllegalArgumentException {
		if (this.cachedData.containsKey(rawData))
			return this.cachedData.get(rawData);

		ArrayList<Token> data = new ArrayList<>();
		String[] lines = rawData.split("\\n");
		int lineCount = lines.length;

		if (lineCount < 2) {
			throw new IllegalArgumentException("A weapon or a ship file needs at least 2 lines: 1 line for the name and 1 line for the damage/health pattern");
		}

		data.add(new TextToken(lines[0]));
		data.add(new NewLineToken());

		for (int i = 1; i < lineCount; i++) {
			String line = lines[i];
			String[] values = line.split(",");

			for (int j = 0; j < values.length; j++) {
				String value = values[j].replaceAll("\\s+", "");
				if (value.equals(""))
					value = "0";
				data.add(new IntegerToken(value));
			}

			data.add(new NewLineToken());
		}

		this.cachedData.put(rawData, data);
		return data;
	}
}
