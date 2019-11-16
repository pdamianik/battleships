package net.battleships.content.parser.nodes;

import net.battleships.content.parser.tokens.Token;

public interface Node {
	Node[] getChildNodes();
	void addChild(Node node);
	Token[] getSourceTokens();
}
