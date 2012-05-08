package org.ipen.miner.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.ipen.miner.nlp.token.Chain;
import org.ipen.miner.nlp.token.StringSequence;
import org.ipen.miner.nlp.token.Tokenizer;

public abstract class AbstractContent implements Content {
	protected Object source;
	protected Object content;
	protected Collector collector;
	protected HashSet<? extends Content> targets;
	protected boolean createdFromCollector = false;

	@Override
	public Object source() {
		return source;
	}

	@Override
	public Object content() {
		if (createdFromCollector) {
			return content;
		} else if (collector.isCollected()) {
			return content;
		} else if (collect()) {
			return content;
		} else {
			return null;
		}
	}

	@Override
	public Iterator targets() {
		if (createdFromCollector) {
			return new TargetsIterator();
		} else if (collector.isCollected()) {
			return new TargetsIterator();
		} else if (collect()) {
			return new TargetsIterator();
		} else {
			return null;
		}
	}

	@Override
	public boolean collect() {
		collector.collect();

		if (collector.isCollected()) {
			return true;
		} else {
			this.content = null;
			this.targets = null;
			return false;
		}
	}

	@Override
	public Object clone() {
		AbstractContent result = null;

		try {
			result = (AbstractContent)super.clone();
		} catch (CloneNotSupportedException e) {
			// assert false;
		}

		result.source = source;
		result.content = content;
		result.collector = collector;
		result.targets = targets;
		return result;
	}

	final protected class TargetsIterator implements Iterator {
		Iterator iterator;

		public TargetsIterator() {
			iterator = targets.iterator();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public Content next() {
			return (Content)iterator.next();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}

	//StringSequence interface
	@Override
	public String stringAt(int index) {
		return segments.get(index);
	}

	@Override
	public int count() {
		return segments.size();
	}

	@Override
	public StringSequence innerSequence(int start, int end) {
		return new Chain((ArrayList)segments.subList(start, end));
	}

	@Override
	public Chain toChain(Tokenizer tokenizer) {
		return tokenizer.tokenize(toString());
	}

	//CharSequence interface
	@Override
	public final char charAt(int index) {
		return toString().charAt(index);
	}

	@Override
	public final int length() {
		return toString().length();
	}

	@Override
	public final CharSequence subSequence(int start, int end) {
		return toString().subSequence(start, end);
	}

	@Override
	public final String toString() {
		return content.toString();
	}
}
