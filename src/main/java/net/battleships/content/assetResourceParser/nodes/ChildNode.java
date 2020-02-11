package net.battleships.content.assetResourceParser.nodes;

public class ChildNode implements Node {
	ParentNode parentNode;

	public ParentNode getParentNode() {
		return this.parentNode;
	}

	void setParentNode(ParentNode parentNode) {
		this.parentNode = parentNode;
	}

	@Override
	public boolean equals(Node node) {
		return super.equals(node);
	}
}
