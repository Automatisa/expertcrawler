package org.ipen.crawler;

import java.util.HashSet;
import java.util.Iterator;
import org.ipen.searcher.BreadthSearcher;
import org.ipen.graph.Node;

public class BreadthCrawler extends BreadthSearcher implements Runnable {
	public BreadthCrawler(HashSet queryTerms, String seedPage) {
		super(queryTerms, seedPage);
	}

	public BreadthCrawler(HashSet queryTerms, String seedPage, int maxFrontierSize, int maxGraphSize) {
		super(queryTerms, seedPage, maxFrontierSize, maxGraphSize);
	}

	public boolean matcher() {
		boolean relevance = true;
		Iterator<Node> nodesIterator = super.getSearchGraph().getNodesIterator();
		while (nodesIterator.hasNext()) {
			Node<String> currentNode = nodesIterator.next();

			Iterator<String> queryTermsIterator = super.getQueryTerms().iterator();
			while (queryTermsIterator.hasNext()) {
				String currentQueryTerm = queryTermsIterator.next();

				if (!currentNode.getObject().toLowerCase().contains(currentQueryTerm)) {
					relevance = false;
				}
			}
		}
		return relevance;
	}

	@Override public void run() {
		super.search();
	}
}