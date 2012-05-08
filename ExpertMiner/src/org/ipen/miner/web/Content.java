package org.ipen.miner.web;

import java.util.Iterator;
import org.ipen.miner.nlp.token.StringSequence;

public interface Content extends StringSequence {
	Object source();
	Object content();
	Iterator targets();
	boolean collect();
	Object clone();
}
