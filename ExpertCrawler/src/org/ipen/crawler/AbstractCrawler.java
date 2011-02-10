package org.ipen.crawler;

import java.util.HashSet;
import org.ipen.graph.Graph;

/**
* This abstract class provides a skeletal implementation of the Crawler
* interface to minimize the effort required to implement this interface.
* @author Thiago
*/
public abstract class AbstractCrawler implements Crawler {
	private HashSet<String> queryTerms = new HashSet();
	private String seedPage = new String();
	private int maxFrontierSize;
	private int maxGraphSize;
	private Graph searchGraph = new Graph();

	/** Creates a new Crawler with minimum parameters.
	* @author Thiago*/
	public AbstractCrawler(HashSet queryTerms, String seedPage) {
		this.queryTerms = queryTerms;
		this.seedPage = seedPage;
	}

	/** Creates a new Crawler with all parameters.
	* @author Thiago*/
	public AbstractCrawler(HashSet queryTerms, String seedPage, int maxFrontierSize, int maxGraphSize) {
		this.queryTerms = queryTerms;
		this.seedPage = seedPage;
		this.maxFrontierSize = maxFrontierSize;
		this.maxGraphSize = maxGraphSize;
	}

	public final HashSet getQueryTerms() {
		return this.queryTerms;
	}

	public final void setQueryTerms(HashSet queryTerms) {
		this.queryTerms = queryTerms;
	}

	public final String getSeedPage() {
		return this.seedPage;
	}

	public final void setSeedPage(String seedPage) {
		this.seedPage = seedPage;
	}

	public final int getMaxFrontierSize() {
		return this.maxFrontierSize;
	}

	public final void setMaxFrontierSize(int maxFrontierSize) {
		this.maxFrontierSize = maxFrontierSize;
	}

	public final int getMaxGraphSize() {
		return this.maxGraphSize;
	}

	public final void setMaxGraphSize(int maxGraphSize) {
		this.maxGraphSize = maxGraphSize;
	}

	public final Graph getSearchGraph() {
		return this.searchGraph;
	}
}