package org.ipen.graph;

public class Node<V> {
	private int index;
	private V value;

	public Node() {
		return;
	}

	public Node(int index) {
		this.index = index;
	}

	public Node(int index, V value) {
		this.index = index;
		this.value = value;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public V getValue() {
		return this.value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}