package org.ipen.crawler;

import java.util.LinkedHashSet;
import org.ipen.graph.DirectedGraph;

//import java.util.LinkedHashSet;

public class Tester {
	public static void main(String[] args) {
		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		DirectedGraph<String> g = new DirectedGraph();
		LinkedHashSet<String> t = new LinkedHashSet();
		t.add(v2);
		g.put(v1, t);
		//t.add("v2");
		//g.add("v1", t);

		System.out.println(g.containsKey("v1"));
		System.out.println(g.containsValue("v2"));
	}
}

//LinkedHashSet<String> l = new LinkedHashSet<String>();
//l.add("teste");
//l.add(null);

//TODO Http - singleton proxy
//TODO Graph - imprimir grafo
//TODO Crawler - usar grafo de paginas
//TODO Crawler - implementacao algoritmos
//TODO Crawler - definir retrieval model
//TODO Crawler - definir heuristicas
//TODO GUI - parametros/arvore
//TODO GUI - html browser
//TODO Crawler - configurar max frontier size
//TODO Crawler - colector assincrono

/*
public class Tester {
	public static void maina(String[] args) {
		HashSet queryTerms = new HashSet();
		String seedPage = new String();

		queryTerms.add("nuclear");
		seedPage = "http://download.oracle.com/javase/tutorial/index.html";

		BreadthCrawler crawler = new BreadthCrawler(queryTerms, seedPage, 10, 20);
		Thread thread = new Thread(crawler);
		thread.start();

		System.out.println("\nSearch graph");
		Iterator<Node> nodesIterator = crawler.getSearchGraph().getNodesIterator();
		while (nodesIterator.hasNext()) {
			Node<String> currentNode = nodesIterator.next();
			System.out.println("Index: " + currentNode.getIndex() + ", URL: " + currentNode.getObject());
		}
	}

	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

	public static void main(String[] args) {
		try {
			HashSet queryTerms = new HashSet();
			String seedPage = new String();

			queryTerms.add("nuclear");
			seedPage = "http://download.oracle.com/javase/tutorial/index.html";

			BreadthCrawler crawler = new BreadthCrawler(queryTerms, seedPage, 10, 20);
			Thread thread = new Thread(crawler);
			thread.start();

			long patience = 1000 * 60 * 60;
			long startTime = System.currentTimeMillis();
			while (thread.isAlive()) {
				threadMessage("Still waiting...");
				//Wait maximum of 1 second for MessageLoop thread to finish.
				thread.join(1000);
				if (((System.currentTimeMillis() - startTime) > patience) && thread.isAlive()) {
					threadMessage("Tired of waiting!");
					thread.interrupt();
					//Shouldn't be long now -- wait indefinitely
					thread.join();
				}
			}

			System.out.println("\nSearch graph");
			Iterator<Node> nodesIterator = crawler.getSearchGraph().getNodesIterator();
			while (nodesIterator.hasNext()) {
				Node<String> currentNode = nodesIterator.next();
				System.out.println("Index: " + currentNode.getIndex() + ", URL: " + currentNode.getObject());
			}
		}
		catch (InterruptedException e) {
			return;
		}
	}
}
*/
