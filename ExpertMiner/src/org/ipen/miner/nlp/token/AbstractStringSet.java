package org.ipen.miner.nlp.token;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractStringSet implements StringSet {
	private Set<CharSequence> segments;

	public AbstractStringSet() {
		this.segments = new HashSet<>();
	}

	public AbstractStringSet(Set<CharSequence> segments) {
		this.segments = segments;
	}
/*
	@Override
	public boolean isEmpty() {
		return segments.isEmpty();
	}

	public boolean contains(StringSequence sequence) {
		return toString().contains(sequence);
	}

	public boolean containsString(String segment) {
		return segments.contains(segment);
	}

	public boolean containsSubString(String subSegment) {
		boolean result = false;

		Iterator<CharSequence> iterator = segments.iterator();
		while (iterator.hasNext()) {
			result = result || iterator.next().toString().contains(subSegment);
			if (result) break;
		}

		return result;
	}

	@Override
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

	public StringSet concat(StringSet stringSet) {
		Set<String> segmentsConcat = new ArrayList<>();
		segmentsConcat.addAll(segments);
		
		segmentsConcat.addAll(stringSet.segments);
		return new Chain(segmentsConcat);
	}

	//StringSet interface
	@Override
	public int count() {
		return segments.size();
	}

	@Override
	public Chain toChain(Tokenizer tokenizer) {
		ArrayList<CharSequence> chainSegments = new ArrayList<>();
		chainSegments.addAll(segments);
		return new Chain(chainSegments);
	}
*/

	//Set interface
	@Override
	public boolean add(CharSequence e) {
		return segments.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends CharSequence> c) {
		return segments.addAll(c);
	}

	@Override
	public void clear() {
		segments.clear();
	}

	@Override
	public boolean contains(Object o) {
		return segments.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return segments.containsAll(c);
	}

	@Override
	public boolean equals(Object o) {
		return segments.equals(o);
	}

	@Override
	public int hashCode() {
		return segments.hashCode();
	}

	@Override
	public boolean isEmpty() {
		return segments.isEmpty();
	}

	@Override
	public Iterator<CharSequence> iterator() {
		return segments.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return segments.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return segments.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return segments.retainAll(c);
	}

	@Override
	public int size() {
		return segments.size();
	}

	@Override
	public Object[] toArray() {
		return segments.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return segments.toArray(a);
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

		Iterator<CharSequence> iterator = segments.iterator();
		while (iterator.hasNext()) {
			segmentsString.append(iterator.next()).append(" ");
		}

		return segmentsString.toString();
	}

	//Comparable interface
	@Override
	public int compareTo(StringSet stringSet) {
		return toString().compareTo(stringSet.toString());
	}
}
