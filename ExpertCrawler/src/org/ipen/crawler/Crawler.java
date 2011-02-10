package org.ipen.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import org.ipen.graph.Graph;

/**
* The root interface in the Crawler Framework. This interface describes the basics
* functionalities of all Crawlers.
* @author Thiago
*/
public abstract interface Crawler {
	/**Returns the query terms collection used in the crawling process.
	* @return */
	public abstract HashSet<String> getQueryTerms();

	/**Sets the query terms collection used in the crawling process.
	* @param queryTerms The query terms collection to be set.*/
	public abstract void setQueryTerms(HashSet<String> queryTerms);

	/**Returns the seed page used as starting point in the crawling process.
	* @return */
	public abstract String getSeedPage();

	/**Sets the seed page used as starting point in the crawling process.
	* @param seedPages The seed page to be set.*/
	public abstract void setSeedPage(String seedPage);

	/**Returns the maximum number of links to be kept in the search frontier.
	* @return */
	public abstract int getMaxFrontierSize();

	/**Sets the maximum number of links to be kept in the search frontier.
	* @param maxFrontierSize The maximum number of links to be kept in the search frontier during the crawling process.*/
	public abstract void setMaxFrontierSize(int maxFrontierSize);

	/**Returns the maximum number of pages to be kept in the search graph.
	* @return */
	public abstract int getMaxGraphSize();

	/**Sets the maximum number of pages to be kept in the search graph.
	* @param maxGraphSize The maximum number of pages to be kept in the search graph during the crawling process.*/
	public abstract void setMaxGraphSize(int maxGraphSize);

	/**Returns the search frontier of the crawling process.
	* @return */
	public abstract LinkedList<String> getSearchFrontier();

	/**Returns the search graph of the crawling process.
	* @return */
	public abstract Graph getSearchGraph();

	/**Returns the current state of the crawling process.
	* @return */
	public abstract boolean isSearching();

	/**Executes the crawling process.*/
	public abstract void search();
}