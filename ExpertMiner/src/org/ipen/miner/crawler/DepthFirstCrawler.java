package org.ipen.miner.crawler;

import org.ipen.miner.web.Content;

/**
 * This class provides a concrete implementation of a Depth-First Crawler algorithm
 * (LIFO - stack frontier) extending the abstract implementation of Abstract Crawler.
 * @author Thiago Reis
 */
public class DepthFirstCrawler extends AbstractCrawler {
	public DepthFirstCrawler() {
		super();
	}

	@Override
	protected Content removeFromFrontier() {
		return super.frontier.removeFirst();
	}

	@Override
	protected void addToFrontier(Content content) {
		super.frontier.addFirst(content);
	}
}
