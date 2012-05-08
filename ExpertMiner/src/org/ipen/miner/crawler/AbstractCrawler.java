package org.ipen.miner.crawler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.ipen.miner.collection.graph.Graph;
import org.ipen.miner.web.Content;

/**
 * This abstract class provides a skeletal implementation of the Crawler
 * interface to minimize the effort required to implement this interface.
 * @author Thiago Reis
 */
public abstract class AbstractCrawler implements Crawler {
	protected LinkedList<Content> frontier = new LinkedList<>();
	protected HashSet<Object> visited = new HashSet<>();
	protected boolean crawling = false;

	@Override
	public boolean isCrawling() {
		return crawling;
	}

	@Override
	public Graph crawl(Graph network, Content seed, Stopper stopper, Map<String, String> tokens) {
		if (!crawling) {
			frontier.add(seed);
			crawling = true;
		//} else if (crawling && !recursion) {
		//	throw new UnsupportedOperationException();
		}

		if (!frontier.isEmpty() && !stopper.stop()) {
			Content content = removeFromFrontier();

			if (content.collect()) {
				if (!visited.contains(content.content())) {
					visited.add(content.content());
					network.add(content);

					Iterator targets = content.targets();
					while (targets.hasNext() && !stopper.stop()) {
						Content targetContent = (Content)targets.next();
						addToFrontier(targetContent);
						network.add(targetContent);
						network.link(content, targetContent);
					}
				}
			}

			crawl(network, seed, stopper, tokens);
		}

		frontier.clear();
		visited.clear();
		crawling = false;
		return network;
	}

	protected abstract Content removeFromFrontier();

	protected abstract void addToFrontier(Content content);
}
