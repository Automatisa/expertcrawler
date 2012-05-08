package org.ipen.miner.crawler;

import java.util.Map;
import org.ipen.miner.collection.graph.Graph;
import org.ipen.miner.web.Content;

/**
 * The root interface in the Crawler Framework. This interface
 * describes the basics functionalities to all Crawlers.
 * @author Thiago Reis
 */
public interface Crawler {
	/**
	 * Returns the query terms used in the crawling process.
	 * @return a <tt>Map</tt> that contains the query terms
	 */
	//Map<String, String> getTerms();

	/**
	 * Returns the seed source used as starting point in the crawling process.
	 * @return a <tt>Collector</tt> used as a seed
	 */
	//Collector getSeed();

	/**
	 * Returns the search graph of the crawling process.
	 * @return the <tt>Graph</tt> build in the process
	 */
	//Graph getNetwork();
	//Stopper getStopper();
	//Estimator getEstimator();
	//Evaluator getEvaluator();
	//Graph crawl(Map<String, String> terms, Set<Collector> seeds, Stopper stopper, Estimator estimator, Evaluator evaluator);

	/**
	 * Executes the search process.
	 * @return the <tt>Graph</tt> build in the process
	 */
	Graph crawl(Graph network, Content seed, Stopper stopper, Map<String, String> tokens);
	//Graph crawl(Map<String, String> terms, Collector seed, Graph network, Stopper stopper);

	/**
	 * Returns the current state of the crawling process.
	 * @return <tt>true</tt> if the crawling process (started by the <tt>crawl</tt>
	 * method) is currently running
	 */
	boolean isCrawling();
}
