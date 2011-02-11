package org.ipen.searcher;

import java.util.HashSet;
import org.ipen.graph.Graph;

/**
* This abstract class provides a skeletal implementation of the Searcher
* interface to minimize the effort required to implement this interface.
* @author Thiago Reis
*/
public abstract class AbstractSearcher implements Searcher {
	private HashSet<String> queryTerms;
	private String seedPage;
	private int maxFrontierSize;
	private int maxGraphSize;
	private Graph searchGraph;

	/** Creates a new Searcher with minimum parameters.
	* @author Thiago*/
	public AbstractSearcher(HashSet queryTerms, String seedPage) {
		this.queryTerms = queryTerms;
		this.seedPage = seedPage;
		this.searchGraph = new Graph();
	}

	/** Creates a new Searcher with all parameters.
	* @author Thiago*/
	public AbstractSearcher(HashSet queryTerms, String seedPage, int maxFrontierSize, int maxGraphSize) {
		this.queryTerms = queryTerms;
		this.seedPage = seedPage;
		this.maxFrontierSize = maxFrontierSize;
		this.maxGraphSize = maxGraphSize;
		this.searchGraph = new Graph();
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

	//Design patterns: template method
	//public final void search() {
	//	if (isSearching()) {
	//		return;
	//	}
	//}
}