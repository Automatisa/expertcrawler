package org.ipen.graph;

public class Edge<O> {
	private int index;
	private String label;
	private O object;
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

	public Edge(Node sourceNode, Node targetNode, String label) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.label = label;
	}

	public Edge(Node sourceNode, Node targetNode, O object) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.object = object;
	}

	public Edge(Node sourceNode, Node targetNode, float weight) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.weight = weight;
	}

	public Edge(Node sourceNode, Node targetNode, int index, String label) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.index = index;
		this.label = label;
	}

	public Edge(Node sourceNode, Node targetNode, int index, O object) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.index = index;
		this.object = object;
	}

	public Edge(Node sourceNode, Node targetNode, int index, float weight) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.index = index;
		this.weight = weight;
	}

	public Edge(Node sourceNode, Node targetNode, String label, O object) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.label = label;
		this.object = object;
	}

	public Edge(Node sourceNode, Node targetNode, String label, float weight) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.label = label;
		this.weight = weight;
	}

	public Edge(Node sourceNode, Node targetNode, O object, float weight) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.object = object;
		this.weight = weight;
	}

	public Edge(Node sourceNode, Node targetNode, int index, String label, O object) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.index = index;
		this.label = label;
		this.object = object;
	}

	public Edge(Node sourceNode, Node targetNode, int index, String label, float weight) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.index = index;
		this.label = label;
		this.weight = weight;
	}

	public Edge(Node sourceNode, Node targetNode, int index, O object, float weight) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.index = index;
		this.object = object;
		this.weight = weight;
	}

	public Edge(Node sourceNode, Node targetNode, String label, O object, float weight) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.label = label;
		this.object = object;
		this.weight = weight;
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

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Node getSourceNode() {
		return this.sourceNode;
	}

	public void setSourceNode(Node sourceNode) {
		this.sourceNode = sourceNode;
	}

	public Node getTargetNode() {
		return this.targetNode;
	}

	public void setTargetNode(Node targetNode) {
		this.targetNode = targetNode;
	}
}