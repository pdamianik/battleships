package net.battleships.content.parser.nodes;

import net.battleships.content.parser.tokens.Token;

import java.util.ArrayList;

public class GenericRootNode implements Node {
	private ArrayList<Node> childNodes;
	private Token[] sourceTokens;

	public GenericRootNode(Token[] sourceTokens) {
		this.sourceTokens = sourceTokens;
	}

	@Override
	public Node[] getChildNodes() {
		return (Node[]) childNodes.toArray();
	}

	@Override
	public void addChild(Node node) {
		this.childNodes.add(node);
	}

	@Override
	public Token[] getSourceTokens() {
		return this.sourceTokens;
	}
}
