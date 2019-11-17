package net.battleships.content.parser.nodes;

import net.battleships.content.parser.tokens.DataToken;

public interface Node {
	Node[] getChildNodes();
	void addChild(Node node);
	DataToken[] getSourceDataTokens();
}
