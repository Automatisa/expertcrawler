package org.ipen.graph;

import java.util.HashSet;
import java.util.Iterator;

public class Graph {
	private HashSet<Node> graphNodes;
	private HashSet<Edge> graphEdges;

	public Graph() {
		this.graphNodes = new HashSet();
		this.graphEdges = new HashSet();
	}

	public boolean addNode(Node node) {
		return this.graphNodes.add(node);
	}

	public boolean removeNode(Node node) {
		return this.graphNodes.remove(node);
	}

	public boolean containsNode(Node node) {
		return this.graphNodes.contains(node);
	}

	public Iterator getNodesIterator() {
		return this.graphNodes.iterator();
	}

	public boolean addEdge(Edge edge) {
		if (!this.graphNodes.contains(edge.getSourceNode()) || !this.graphNodes.contains(edge.getTargetNode()) || this.graphEdges.contains(edge)) {
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

	public boolean addChild(Node childNode, Node parentNode) {
		Edge currentEdge = new Edge(parentNode, childNode);

		if (!this.graphNodes.contains(parentNode) || this.graphNodes.contains(childNode) || this.graphEdges.contains(currentEdge)) {
			return false;
		}
		else if(this.addNode(childNode) && this.addEdge(currentEdge)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean addParent(Node parentNode, Node childNode) {
		Edge currentEdge = new Edge(parentNode, childNode);

		if (!this.graphNodes.contains(childNode) || this.graphNodes.contains(parentNode) || this.graphEdges.contains(currentEdge)) {
			return false;
		}
		else if(this.addNode(parentNode) && this.addEdge(currentEdge)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean removeAll() {
		boolean operation1 = this.graphEdges.removeAll(graphEdges);
		boolean operation2 = this.graphNodes.removeAll(graphNodes);

		if (operation1 && operation2) {
			return true;
		}
		else {
			return false;
		}
	}

	public int getNodesSize() {
		return this.graphNodes.size();
	}

	public int getEdgesSize() {
		return this.graphEdges.size();
	}

	public int getGraphSize() {
		return this.graphNodes.size();
	}
}