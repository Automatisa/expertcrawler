package org.ipen.graph;

public class Edge<V> {
	private int index;
	private V value;
	private float weight;
	private Node sourceNode;
	private Node targetNode;

	public Edge(Node sourceNode, Node targetNode) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
	}

	public Edge(Node sourceNode, Node targetNode, int index) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.index = index;
	}

	public Edge(Node sourceNode, Node targetNode, V value) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.value = value;
	}

	public Edge(Node sourceNode, Node targetNode, int index, V value) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
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

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getWeight() {
		return this.weight;
	}

	public Node getSourceNode() {
		return this.sourceNode;
	}

	public Node getTargetNode() {
		return this.targetNode;
	}
}