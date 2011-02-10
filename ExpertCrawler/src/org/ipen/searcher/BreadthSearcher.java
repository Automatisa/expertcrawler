package org.ipen.searcher;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;
import org.ipen.graph.Node;
import org.ipen.graph.Edge;

/**
* This class provides a concrete implementation of a Breadth-First Search algorithm
* (FIFO - queue frontier) extending the abstract implementation of Abstract Searcher.
* @author Thiago Reis
*/
public class BreadthSearcher extends AbstractSearcher {
	private LinkedList<String> searchFrontier;
	private boolean isSearching;
	private int currentNodesIndex;
	private int currentEdgeIndex;

	public BreadthSearcher(HashSet queryTerms, String seedPage) {
		super(queryTerms, seedPage);
		this.searchFrontier = new LinkedList();
		this.isSearching = false;
	}

	public BreadthSearcher(HashSet queryTerms, String seedPage, int maxFrontierSize, int maxGraphSize) {
		super(queryTerms, seedPage, maxFrontierSize, maxGraphSize);
		this.searchFrontier = new LinkedList();
		this.isSearching = false;
	}

	public final boolean isSearching() {
		return this.isSearching;
	}

	public void search() {
		if (!this.isSearching) {
			super.getSearchGraph().removeAll();
			this.searchFrontier.removeAll(searchFrontier);
			this.searchFrontier.addLast(super.getSeedPage());
			this.currentNodesIndex = 0;
			this.currentEdgeIndex = 0;
			this.isSearching = true;
		}

		if (!this.searchFrontier.isEmpty() && super.getSearchGraph().getGraphSize() <= super.getMaxGraphSize()) {
			String currentUrl = this.searchFrontier.removeFirst();
			Page currentPage = new Page(currentUrl, true);

			Node<String> currentNodesParent = new Node(++this.currentNodesIndex, currentUrl);
			super.getSearchGraph().addNode(currentNodesParent);

			if (currentPage.getPageFetched() && currentPage.getPageHtml() != null && currentPage.getPageHtml().length() > 0) {
				Iterator<String> linksIterator = currentPage.getPageLinks().iterator();
				while (linksIterator.hasNext()) {
					String currentLink = linksIterator.next();
					Node<String> currentNodeChild = new Node(++this.currentNodesIndex, currentLink);
					Edge<String> currentEdge = new Edge(currentNodesParent, currentNodeChild, ++this.currentEdgeIndex);

					this.searchFrontier.addLast(currentLink);
					super.getSearchGraph().addNode(currentNodeChild);
					super.getSearchGraph().addEdge(currentEdge);
				}
			}

			search();
		}
		else {
			this.isSearching = false;
		}
	}
}