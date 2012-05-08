//TODO verificar writeObject/readObject
//TODO implementar retainAll do grafo
//TODO criar package level comments
//TODO adicionar classe vertice e operacoes no vertice
//TODO crawler multi-thread
//TODO implementar/definir Serializable, Cloneable, Comprable, CharSequence

import org.ipen.miner.collection.graph.Graph;
import org.ipen.miner.collection.graph.GraphIterator;
import org.ipen.miner.collection.graph.HashDigraph;
import org.ipen.miner.collection.graph.HashGraph;
import org.ipen.miner.collection.bag.TreeBag;
import org.ipen.miner.collection.bag.Bag;
import org.ipen.miner.model2.NegationExpression;
import org.ipen.miner.model2.Expression;
import org.ipen.miner.model2.DisjunctionExpression;
import org.ipen.miner.model2.ExclusiveDisjunctionExpression;
import org.ipen.miner.model2.ConjunctionExpression;
import org.ipen.miner.crawler.BreadthFirstCrawler;
import org.ipen.miner.crawler.Crawler;
import org.ipen.miner.crawler.Stopper;
import org.ipen.miner.crawler.NetworkSizeStopper;
import org.ipen.miner.web.Content;
import org.ipen.miner.web.WebCollector;
import org.ipen.miner.web.WebContent;
import org.ipen.miner.web.Collector;
import org.ipen.miner.nlp.*;
import org.ipen.miner.nlp.token.*;
//import org.ipen.miner.text.*;
import java.util.*;

public class Tester {
	public static void main(String[] args) {
		Bag segments = new TreeBag();
		segments.add("nuclear");
		segments.add("energy");
		segments.add("nuclear");
		segments.add("nuclear");
		segments.add("atomic");
		segments.add("ipen", 3);
		segments.add("nuclear", 5);

		segments.remove("nuclear", 2);
		System.out.println(segments.toString());
	}

	public static void main92(String[] args) {
		String text = "The quick brown fox jumps over the lazy dog.";
		Tokenizer tokenizer = new WordTokenizer();
		Chain chain = tokenizer.tokenize(text);

		Expression expression1 = new NegationExpression("dog");
		Expression expression2 = new DisjunctionExpression("quick", "lazy");
		Expression expression3 = new ConjunctionExpression(expression1, expression2);
		Expression expression4 = new ExclusiveDisjunctionExpression("quick", "lazy");
		System.out.println(expression1.toString());
		System.out.println(expression2.toString());
		System.out.println(expression3.toString());
		System.out.println(expression4.toString());

		System.out.println(expression1.evaluate(chain));
		System.out.println(expression2.evaluate(chain));
		System.out.println(expression3.evaluate(chain));
		System.out.println(expression4.evaluate(chain));

		//ConjunctionExpression temp = (ConjunctionExpression)expression2;
		//System.out.println(temp.getOperand1());
		//System.out.println(temp.getOperand2());
	}

	public static void main93(String[] args) {
		//StringTokenizer string = new WordTokenizer("o cao mordeu o gato");
		//System.out.println(string.countTokens());
		//while (string.countTokens() > 0 && string.hasMoreTokens()) {
		//	System.out.println(string.nextToken());
		//}

		//String[] result = "this is a test".split("\\s");
		//for (int x=0; x < result.length; x++)
		//	System.out.println(result[x]);

		//String text = "this is\t\ta\n\ntest";

		//System.out.println(tokens.containsToken(""));
		//System.out.println(tokens.countTokens());
		//System.out.println(tokens.getString());
		//System.out.println(tokens.getToken(0));
		//System.out.println(tokens.isEmpty());

		//String text = "The quick brown fox jumps over the lazy dog.";
		String text = "The quick\tbrown fox \njumps over the,lazy  dog<h1>.";

		Remover remover = new StopWordRemover();
		//Remover remover = new PunctuationRemover();
		//Tagger tagger = new POSTagger();
		Tokenizer tokenizer = new WordTokenizer();
		Stemmer stemmer = new PorterStemmer();

		String textRemoved = remover.remove(text).toString();
		String textTokenized = tokenizer.tokenize(text).toString();
		Chain textChainzed = tokenizer.tokenize(text);
		String textStemmed = stemmer.stem(text).toString();

		Iterator<Token> iterator = tokenizer.tokenize(text).iterator();
		while (iterator.hasNext()) {
			Token current = iterator.next();
			//System.out.println(current);
			System.out.println(stemmer.stem(current.get()));
		}
	}

	public static void main94(String[] args) {
		Collector collector = new WebCollector("http://crystalkeep.com/");
		Content content = collector.collect();
		System.out.println(collector.isCollected());
		//System.out.println(content.collect());
		System.out.println(content.source());
		System.out.println(content.content());
	}

	public static void main95(String[] args) {
		Content content = new WebContent("http://crystalkeep.com/");
		System.out.println(content.collect());
		System.out.println(content.source());
		System.out.println(content.content());
		System.out.println("###############################################################");

		Iterator<Content> iterator = content.targets();
		while (iterator.hasNext()) {
			Content target = iterator.next();
			System.out.println(target.collect());
			System.out.println(target.source());
			System.out.println(target.content());
			System.out.println("###############################################################");
		}
	}

	public static void main96(String[] args) {
		Graph<Content> network = new HashDigraph<>();
		Content seed = new WebContent("http://pt.wikipedia.org/wiki/Energia_nuclear");
		Stopper stopper = new NetworkSizeStopper(network, 1000);
		Map<String, String> tokens = new HashMap();
		Crawler crawler = new BreadthFirstCrawler();

		tokens.put("nuclear", "nuclear");
		tokens.put("reator", "nuclear");
		tokens.put("atomico", "nuclear");
		crawler.crawl(network, seed, stopper, tokens);

		System.out.println(network.size());
		System.out.println("###############################################################");

		GraphIterator<Content> iterator = network.breadthFirstIterator(seed);
		while (iterator.hasNext()) {
			Content vertex = iterator.next();
			if (iterator.isRoot()) {
				//System.out.println("Vertex: " + vertex.source() + "; Parent: ROOT; Level: " + iterator.level());
				System.out.println(vertex.source() + "@null@" + iterator.level());
			} else {
				System.out.println(vertex.source() + "@" + iterator.parent().source() + "@" + iterator.level());
			}
		}
	}

	public static void main97(String[] args) {
		//Stemmer s = new PorterStemmer();
		//System.out.println(s.stem("testing"));

		Remover r = new StopWordRemover();
		Stemmer s = new PorterStemmer();
		String w = "Deployment and running of software, MSI,scripts,hotfixes on WIN network";
		System.out.println(r.remove(w));
		System.out.println(s.stem(r.remove(w)));
	}

	public static void main98(String[] args) {
		WebCollector collector = new WebCollector("http://en.wikipedia.org/wiki/Class_(computer_programming)");
		String content = collector.collect();

		//Collector collector2 = (WebCollector)collector.clone();
		//String content2 = (String)collector2.collect();

		System.out.println(collector.getSource());
		System.out.println(collector.getContent());
		//System.out.println(collector2.getSource());
		//System.out.println(collector == collector2);
	}

	public static void main99(String[] args) {
		String v1 = "v1";
		String v2 = "v2";
		String v3 = "v3";
		String v4 = "v4";
		String v5 = "v5";
		String v6 = "v6";
		String v7 = "v7";

		Graph<String> g = new HashGraph();
		//Graph<String> g = new LinkedHashGraph();
		//Graph<String> g = new HashDigraph();
		//Graph<String> g = new LinkedHashDigraph();

		g.add(v1);
		g.add(v2);
		g.add(v3);
		g.add(v4);
		g.add(v5);
		g.add(v6);
		g.add(v7);
		g.link(v1, v2);
		g.link(v1, v3);
		g.link(v2, v4);
		g.link(v2, v5);
		g.link(v5, v6);
		g.link(v5, v7);
		System.out.println(g.isAdjacent(v1, v2));
		System.out.println(g.degree(v1));
		System.out.println(g.size());

		//Iterator<String> breadthIterator2 = g.breadthFirstIterator(v1);
		//while (breadthIterator2.hasNext()) {
		//	if (breadthIterator2.next().equals(v5))
		//		breadthIterator2.remove();
		//}

		System.out.println("###############################################################");

		GraphIterator<String> breadthIterator = g.breadthFirstIterator(v1);
		while (breadthIterator.hasNext()) {
			String vertex = breadthIterator.next();
			if (breadthIterator.isRoot()) {
				System.out.println("Vertex: " + vertex + "; Parent: ROOT; Level: " + breadthIterator.level());
			} else {
				System.out.println("Vertex: " + vertex + "; Parent: " + breadthIterator.parent() + "; Level: " + breadthIterator.level());
			}
		}

		System.out.println("###############################################################");

		GraphIterator<String> depthIterator = g.depthFirstIterator(v1);
		while (depthIterator.hasNext()) {
			String vertex = depthIterator.next();
			if (depthIterator.isRoot()) {
				System.out.println("Vertex: " + vertex + "; Parent: ROOT; Level: " + depthIterator.level());
			} else {
				System.out.println("Vertex: " + vertex + "; Parent: " + depthIterator.parent() + "; Level: " + depthIterator.level());
			}
		}
	}
}
