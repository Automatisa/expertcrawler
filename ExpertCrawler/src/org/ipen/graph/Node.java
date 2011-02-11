package org.ipen.graph;

public class Node<O> {
	private int index;
	private String label;
	private O object;

	public Node() {
		return;
	}

	public Node(int index) {
		this.index = index;
	}

	public Node(String label) {
		this.label = label;
	}

	public Node(O object) {
		this.object = object;
	}

	public Node(int index, String label) {
		this.index = index;
		this.label = label;
	}

	public Node(int index, O object) {
		this.index = index;
		this.object = object;
	}

	public Node(String label, O object) {
		this.label = label;
		this.object = object;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public O getObject() {
		return this.object;
	}

	public void setObject(O object) {
		this.object = object;
	}
}