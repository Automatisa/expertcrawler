package org.ipen.graph;

public class Edge {
	private int index;
	private float weight;
	private Vertex sourceVertex;
	private Vertex targetVertex;

	public Edge(Vertex sourceVertex, Vertex targetVertex) {
		this.sourceVertex = sourceVertex;
		this.targetVertex = targetVertex;
	}

	public Edge(Vertex sourceVertex, Vertex targetVertex, int index) {
		this.sourceVertex = sourceVertex;
		this.targetVertex = targetVertex;
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getWeight() {
		return this.weight;
	}

	public Vertex getSourceVertex() {
		return this.sourceVertex;
	}

	public Vertex getTargetVertex() {
		return this.targetVertex;
	}
}