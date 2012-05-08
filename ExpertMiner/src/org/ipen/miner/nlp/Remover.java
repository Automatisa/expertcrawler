package org.ipen.miner.nlp;

public interface Remover {
	boolean isRemovable(CharSequence word);
	boolean containsRemovables(CharSequence text);
	String remove(CharSequence text);
}
