package org.ipen.miner.crawler;

import org.ipen.miner.web.Content;

/**
 * This class provides a concrete implementation of a Breadth-First Crawler algorithm
 * (FIFO - queue frontier) extending the abstract implementation of Abstract Crawler.
 * @author Thiago Reis
 */
public class BreadthFirstCrawler extends AbstractCrawler {
	public BreadthFirstCrawler() {
		super();
	}

	@Override
	protected Content removeFromFrontier() {
		return super.frontier.removeFirst();
	}

	@Override
	protected void addToFrontier(Content content) {
		super.frontier.addLast(content);
	}
}
