package org.ipen.miner.nlp.token;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public final class Chain implements StringSequence, Serializable, Comparable<Chain> {
	//private final String source;
	private final ArrayList<String> segments;

	Chain(ArrayList<String> segments) {
		//this.source = source;
		this.segments = segments;
	}

	//@Override
	//public String source() {
	//	return source;
	//}

	//@Override
	//public Tokenizer tokenizer() {
	//	return source;
	//}

	public boolean isEmpty() {
		return segments.isEmpty();
	}

	public int indexOf(String segment) {
		return segments.indexOf(segment);
	}

	public boolean contains(StringSequence sequence) {
		return toString().contains(sequence);
	}

	public boolean containsString(String segment) {
		return segments.contains(segment);
	}

	public boolean containsSubString(String subSegment) {
		boolean result = false;

		Iterator<String> iterator = segments.iterator();
		while (iterator.hasNext()) {
			result = result || iterator.next().contains(subSegment);
			if (result) break;
		}

		return result;
	}

	public Iterator<String> iterator() {
		return new Iterator<String>() {
			Iterator iterator = segments.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public String next() {
				return (String)iterator.next();
			}

			@Override
			public void remove() throws UnsupportedOperationException {
				throw new UnsupportedOperationException();
			}
		};
	}

	public Chain subChain(int beginIndex) {
		return subChain(beginIndex, segments.size());
	}

	public Chain subChain(int beginIndex, int endIndex) {
		if (beginIndex < 0) {
			throw new ChainIndexOutOfBoundsException(beginIndex);
		}
		if (endIndex > segments.size()) {
			throw new ChainIndexOutOfBoundsException(endIndex);
		}
		if (beginIndex > endIndex) {
			throw new ChainIndexOutOfBoundsException(endIndex - beginIndex);
		}
		return new Chain((ArrayList)segments.subList(beginIndex, endIndex));
	}

	public Chain concat(Chain chain) {
		ArrayList<String> segmentsConcat = new ArrayList<>();
		segmentsConcat.addAll(segments);
		segmentsConcat.addAll(chain.segments);
		return new Chain(segmentsConcat);
	}

	//Comparable interface
	@Override
	public int compareTo(Chain chain) {
		return toString().compareTo(chain.toString());
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
		return this;
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
		StringBuilder segmentsString = new StringBuilder();

		Iterator<String> iterator = segments.iterator();
		while (iterator.hasNext()) {
			segmentsString.append(iterator.next()).append(" ");
		}

		return segmentsString.toString();
	}
}
