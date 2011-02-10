package org.ipen.graph;

import java.util.HashSet;
import java.util.Iterator;

public class Graph {
	private HashSet<Vertex> graphVertexes = new HashSet();
	private HashSet<Edge> graphEdges = new HashSet();

	public boolean addVertex(Vertex vertex) {
		return this.graphVertexes.add(vertex);
	}

	public boolean removeVertex(Vertex vertex) {
		return this.graphVertexes.remove(vertex);
	}

	public boolean containsVertex(Vertex vertex) {
		return this.graphVertexes.contains(vertex);
	}

	public Iterator getVertexesIterator() {
		return this.graphVertexes.iterator();
	}

	public boolean addEdge(Edge edge) {
		if (!this.graphVertexes.contains(edge.getSourceVertex()) || !this.graphVertexes.contains(edge.getTargetVertex()) || this.graphEdges.contains(edge)) {
			return false;
		}

		return this.graphEdges.add(edge);
	}

	public boolean removeEdge(Edge edge) {
		return this.graphEdges.remove(edge);
	}

	public boolean containsEdge(Edge edge) {
		return this.graphEdges.contains(edge);
	}

	public Iterator getEdgesIterator() {
		return this.graphEdges.iterator();
	}

	public int getVertexesSize() {
		return this.graphVertexes.size();
	}

	public int getEdgesSize() {
		return this.graphEdges.size();
	}

	public int getGraphSize() {
		return this.graphVertexes.size();
	}
}