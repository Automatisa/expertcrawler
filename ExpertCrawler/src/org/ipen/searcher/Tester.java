package org.ipen.searcher;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Iterator;
import org.ipen.graph.*;

//TODO Http - singleton proxy
//TODO Graph - imprimir grafo
//TODO Crawler - usar grafo de paginas
//TODO Crawler - implementacao algoritmos
//TODO Crawler - definir retrieval model
//TODO Crawler - definir heuristicas
//TODO GUI - parametros/arvore
//TODO GUI - html browser

public class Tester {
	public static void main(String[] args) {
		HashSet queryTerms = new HashSet();
		String seedPage = new String();

		queryTerms.add("nuclear");
		seedPage = "http://download.oracle.com/javase/tutorial/index.html";
		BreadthSearcher crawler = new BreadthSearcher(queryTerms, seedPage, 10, 200);
		crawler.search();

		System.out.println("Frontier");
		Iterator<String> frontierIterator = crawler.getSearchFrontier().iterator();
		while(frontierIterator.hasNext()) {
			System.out.println(frontierIterator.next());
		}
	}
}