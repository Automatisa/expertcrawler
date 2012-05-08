package org.ipen.miner.nlp.token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class WordTokenizer implements Tokenizer {
	@Override
	public Chain tokenize(String string) {
		return staticTokenize(string);
	}

	private static Chain staticTokenize(String string) {
		ArrayList<String> tokens = new ArrayList<>();

		Iterator<String> iterator = Arrays.asList(string.split("\\s+|\\W+")).iterator();
		//Iterator<String> iterator = Arrays.asList(string.split("[ \\t\n\\x0B\\f\\r]")).iterator();
		//Iterator<String> iterator = Arrays.asList(string.split("\\b")).iterator();
		while (iterator.hasNext()) {
			tokens.add(iterator.next());
		}

		return new Chain(tokens);
	}
}
