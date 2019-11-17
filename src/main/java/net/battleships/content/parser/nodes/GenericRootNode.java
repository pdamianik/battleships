package net.battleships.content.parser.nodes;

import net.battleships.content.parser.tokens.DataToken;

import java.util.ArrayList;

public class GenericRootNode implements Node {
	private ArrayList<Node> childNodes;
	private DataToken[] sourceDataTokens;

	public GenericRootNode(DataToken[] sourceDataTokens) {
		this.sourceDataTokens = sourceDataTokens;
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
	public DataToken[] getSourceDataTokens() {
		return this.sourceDataTokens;
	}
}
