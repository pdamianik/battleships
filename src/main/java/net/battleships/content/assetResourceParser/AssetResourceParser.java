package net.battleships.content.assetResourceParser;

import net.battleships.content.assetResourceParser.tokens.*;
import net.battleships.datatypes.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AssetResourceParser {
	private HashMap<String, AssetData> cachedData = new HashMap<>();
	private static HashMap<String, Integer> keyToType = new HashMap<>() {
		{
			put("name", 0);
			put("author", 1);
			put("version", 2);
			put("health pattern", 3);
			put("damage pattern", 4);
		}
	};
	private AssetResourceLexer lexer;

	public AssetResourceParser() {
		this.lexer = new AssetResourceLexer();

		this.lexer.addToken(Arrays.asList(
				new IntegerToken(),
				new EnumerationSeparatorToken(),
				new KeyValueSeparatorToken(),
				new NewLineToken(),
				new TextToken(),
				new DecimalToken(),
				new WhitespaceToken()
		));
	}

	public AssetData parse(String rawData) throws InvalidKeyException, KeyAlreadyExistsException, MissingDataException {
		if (this.cachedData.containsKey(rawData))
			return this.cachedData.get(rawData);
		AssetData result = this.parse(this.lexer.parse(rawData));
		this.cachedData.put(rawData, result);
		return result;
	}

	AssetData parse(ArrayList<Token> tokens) throws InvalidKeyException, KeyAlreadyExistsException, MissingDataException {
		AssetData result = new AssetData();
		ArrayList<DataRow> rows = new ArrayList<>();

		int start = 0, i = 0;

		for (; i < tokens.size(); i++)
			if (tokens.get(i) instanceof NewLineToken) {
				rows.add(new DataRow(tokens.subList(start, i)));
				start = i+1;
			}
		if (start != i)
			rows.add(new DataRow(tokens.subList(start, i)));

		for (i = 0; i < rows.size(); i++) {
			DataRow row = rows.get(i);
			if (row.hasKey()) {
				String key = row.getKey()[0].getRawData();
				if (keyToType.containsKey(key))
					switch (keyToType.get(key)) {
						case 0:
							// name key
							if (result.getName() == null)
								result.setName(row.getValue()[0].getRawData());
							else throw new KeyAlreadyExistsException("Found duplicate name key");
							break;
						case 1:
							// author key
							if (result.getAuthor() == null)
								result.setAuthor(row.getValue()[0].getRawData());
							else throw new KeyAlreadyExistsException("Found duplicate author key");
							break;
						case 2:
							// version key
							if (result.getVersion() == -1d)
								result.setVersion(((DecimalToken)row.getValue()[1]).getNumericValue().doubleValue());
							else throw new KeyAlreadyExistsException("Found duplicate version key");
							break;
						case 3:
						case 4:
							// damage/health pattern key
							if (result.getDamagePattern() != null)
								throw new KeyAlreadyExistsException("Found duplicate damage pattern/health key");
							ArrayList<ArrayList<Integer>> table = new ArrayList<>();
							int value = 0;
							if (row.hasValue()) {
								table.add(new ArrayList<>());
								for (Token token : row.getValue())
									if (token instanceof IntegerToken)
										value = ((IntegerToken) token).getNumericValue().intValue();
									else if (token instanceof EnumerationSeparatorToken) {
										table.get(0).add(value);
										value = 0;
									}
							}
							for (; ++i < rows.size() && !rows.get(i).hasKey() && rows.get(i).hasValue(); ) {
								table.add(new ArrayList<>());
								for (Token tableRowToken : rows.get(i).getValue())
									if (tableRowToken instanceof IntegerToken)
										value = ((IntegerToken) tableRowToken).getNumericValue().intValue();
									else if (tableRowToken instanceof EnumerationSeparatorToken) {
										table.get(table.size()-1).add(value);
										value = 0;
									}
							}
							int[][] resultingTable = new int[table.size()][];
							for (int y = 0; y < table.size(); y++) {
								resultingTable[y] = new int[table.get(y).size()];
								for (int x = 0; x < table.get(y).size(); x++)
									resultingTable[y][x] = table.get(y).get(x);
							}
							result.setDamagePattern(resultingTable);
							break;
					}
				else throw new InvalidKeyException("\"" + key + "\" isn't a valid key");
			}
		}

		if (result.isComplete())
			return result;
		else throw new MissingDataException("The parsed Data isn't complete");
	}
}
