package org.ipen.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;
import org.ipen.graph.Vertex;
import org.ipen.graph.Edge;

/**
* This class provides a concrete implementation of a Breadth-First Crawler algorithm
* (FIFO - queue frontier) extending the abstract implementation of Abstract Crawler.
* @author Thiago
*/
public class BreadthSearcher extends AbstractSearcher {
	private LinkedList<String> searchFrontier = new LinkedList();
	private int currentVertexIndex = 0;
	private int currentEdgeIndex = 0;
	private boolean isSearching = false;

	public BreadthSearcher(HashSet queryTerms, String seedPage) {
		super(queryTerms, seedPage);
	}

	public BreadthSearcher(HashSet queryTerms, String seedPage, int maxFrontierSize, int maxGraphSize) {
		super(queryTerms, seedPage, maxFrontierSize, maxGraphSize);
	}

	public LinkedList<String> getSearchFrontier() {
		return this.searchFrontier;
	}

	public final boolean isSearching() {
		return this.isSearching;
	}

	public void search() {
		if (!this.isSearching) {
			this.searchFrontier.addLast(super.getSeedPage());
			this.isSearching = true;
		}

		if (!this.searchFrontier.isEmpty() && super.getSearchGraph().getGraphSize() <= super.getMaxGraphSize()) {
			Vertex currentParent = new Vertex(++this.currentVertexIndex);
			super.getSearchGraph().addVertex(currentParent);

			String currentUrl = this.searchFrontier.removeFirst();
			Page currentPage = new Page(currentUrl, true);

			if (currentPage.getPageFetched() && currentPage.getPageHtml() != null && currentPage.getPageHtml().length() > 0) {
				Iterator<String> linksIterator = currentPage.getPageLinks().iterator();
				while(linksIterator.hasNext()) {
					String currentLink = linksIterator.next();
					Vertex currentChild = new Vertex(++this.currentVertexIndex);
					Edge currentEdge = new Edge(currentParent, currentChild, ++this.currentEdgeIndex);

					this.searchFrontier.addLast(currentLink);
					super.getSearchGraph().addVertex(currentChild);
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