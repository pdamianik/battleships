package net.battleships.content.parser.nodes;

import net.battleships.content.parser.tokens.Token;

import java.util.ArrayList;

interface Node {
	public Node[] getChildNodes();
	public void addChild(Node node);
	public Token[] getSourceTokens();
}
