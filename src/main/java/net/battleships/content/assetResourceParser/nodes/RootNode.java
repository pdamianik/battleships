package net.battleships.content.assetResourceParser.nodes;

import java.util.*;

public class RootNode implements ParentNode {
	private ArrayList<ChildNode> childNodes;

	public RootNode(ArrayList<ChildNode> childNodes) {
		this.childNodes = childNodes;
	}

	public RootNode() {
		this.childNodes = new ArrayList<>();
	}

	@Override
	public boolean equals(Node node) {
		return false;
	}

	public boolean equals(RootNode node) {
		return this.childNodes.equals(node.getChildNodes());
	}

	public ArrayList<ChildNode> getChildNodes() {
		return new ArrayList<>(this.childNodes);
	}

	@Override
	public int size() {
		return this.childNodes.size();
	}

	@Override
	public boolean isEmpty() {
		return this.childNodes.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.childNodes.contains(o);
	}

	@Override
	public Iterator<ChildNode> iterator() {
		return this.childNodes.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.childNodes.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.childNodes.toArray(a);
	}

	@Override
	public boolean add(ChildNode childNode) {
		return this.childNodes.add(childNode);
	}

	@Override
	public boolean remove(Object o) {
		return this.childNodes.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.childNodes.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends ChildNode> c) {
		return this.childNodes.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends ChildNode> c) {
		return this.childNodes.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.childNodes.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.childNodes.retainAll(c);
	}

	@Override
	public void clear() {
		this.childNodes.clear();
	}

	@Override
	public ChildNode get(int index) {
		return this.childNodes.get(index);
	}

	@Override
	public ChildNode set(int index, ChildNode element) {
		return this.childNodes.set(index, element);
	}

	@Override
	public void add(int index, ChildNode element) {
		this.childNodes.add(index, element);
	}

	@Override
	public ChildNode remove(int index) {
		return this.childNodes.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return this.childNodes.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.childNodes.lastIndexOf(o);
	}

	@Override
	public ListIterator<ChildNode> listIterator() {
		return this.childNodes.listIterator();
	}

	@Override
	public ListIterator<ChildNode> listIterator(int index) {
		return this.childNodes.listIterator(index);
	}

	@Override
	public List<ChildNode> subList(int fromIndex, int toIndex) {
		return this.childNodes.subList(fromIndex, toIndex);
	}
}
