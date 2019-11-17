package net.battleships.content.parser.nodes;

import net.battleships.content.parser.tokens.DataToken;

import java.util.ArrayList;

public class GenericNode extends GenericRootNode implements Node {
	private ArrayList<Node> childNodes;
	private Node parentNode;

	public GenericNode(DataToken[] sourceDataTokens, Node parentNode) {
		super(sourceDataTokens);
		this.parentNode = parentNode;
	}

	public Node getParenNode() {
		return this.parentNode;
	}

	@Override
	public Node[] getChildNodes() {
		return (Node[]) childNodes.toArray();
	}

	@Override
	public void addChild(Node node) {
		this.childNodes.add(node);
	}
}
