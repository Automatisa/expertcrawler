package org.ipen.miner.nlp.token;

public interface StringSequence extends StringSet {
	String stringAt(int index);
	//int count();
	StringSequence innerSequence(int start, int end);
	//Chain toChain(Tokenizer tokenizer);
}
