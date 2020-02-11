package net.battleships.content.assetResourceParser;

import net.battleships.content.assetResourceParser.tokens.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A dynamic lexer for the .ship and .weapon files
 * @author Philip Damianik
 * @version 2020-02-01
 */

class AssetResourceLexer {
	private ArrayList<Token> tokens = new ArrayList<>() {{add(new GeneralToken());}};

	/**
	 * with this method you can add a data token which will be
	 * used in the parsing process
	 * @param token the token to add
	 */
	public void addToken(Token token) {
		this.tokens.add(token);
	}

	public void addToken(Collection<Token> token) {
		this.tokens.addAll(token);
	}

	/**
	 * The parsing method which uses the data tokens and
	 * the data separator tokens to identify specific parts
	 * of the raw data input string
	 * @param rawData the input string which will be parsed
	 * according to the data tokens and the data separator
	 * tokens
	 * @return a ArrayList with the parsed tokens
	 * @throws IllegalArgumentException will be thrown when
	 * a token ins't working correctly
	 */
	public ArrayList<Token> parse(String rawData) throws IllegalArgumentException {

		// parsed tokens
		ArrayList<Token> data = new ArrayList<>();
		// matching tokens from the previous round
		ArrayList<Token> previouslyMatchingToken = new ArrayList<>(this.tokens);
		// matching tokens from the currently running round
		ArrayList<Token> matchingToken = new ArrayList<>();
		// tmp String to search for tokens
		StringBuilder tmpString = new StringBuilder();

		for (int i = 0; i < rawData.length();) {
			char letter = rawData.charAt(i);
			tmpString.append(letter);
			for (Token token : previouslyMatchingToken)
				if (token.matches(tmpString))
					matchingToken.add(token);

			if (matchingToken.size() < 2) {
				if (previouslyMatchingToken.size() == 2)
					data.add(previouslyMatchingToken.get(1).tokenWithData(tmpString.substring(0, tmpString.length()-1)));
				else
					data.add(this.tokens.get(0).tokenWithData(tmpString.substring(0, tmpString.length()-1)));
				tmpString = new StringBuilder(letter+"");
				previouslyMatchingToken.clear();
				for (Token token : this.tokens)
					if (token.matches(tmpString))
						previouslyMatchingToken.add(token);
			} else {
				previouslyMatchingToken = new ArrayList<>(matchingToken);
			}
			i++;
			matchingToken.clear();
		}

		if (previouslyMatchingToken.size() == 2)
			data.add(previouslyMatchingToken.get(1).tokenWithData(tmpString.toString()));
		else
			data.add(this.tokens.get(0).tokenWithData(tmpString.toString()));

		return data;
	}
}
