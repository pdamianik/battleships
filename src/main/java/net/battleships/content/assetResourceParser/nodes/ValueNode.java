package net.battleships.content.assetResourceParser.nodes;

public class ValueNode<T> extends ChildNode {
	private T value;

	public ValueNode() {
	}

	public ValueNode(T value) {
		this.value = value;
	}

	public T getValue() {
		return this.value;
	}

	void setValue(T value) {
		this.value = value;
	}
}
