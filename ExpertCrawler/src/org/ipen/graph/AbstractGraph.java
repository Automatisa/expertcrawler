package org.ipen.graph;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public abstract class AbstractGraph<V> extends LinkedHashMap<V, LinkedHashSet<V>> implements Graph<V> {
// Query Operations
// Modification Operations
// Bulk Operations
// Comparison and hashing

	public AbstractGraph() {
		super();
	}

	@Override
	public LinkedHashSet put(V vertex, LinkedHashSet<V> targets) {
		if (vertex == null || targets.contains(null)) throw new IllegalArgumentException();
		Iterator<V> iterator = targets.iterator();
		while (iterator.hasNext()) {
			super.put(iterator.next(), new LinkedHashSet<V>());
		}
		return super.put(vertex, targets);
	}

	public Iterator<V> iterator() {
		//return new graphIterator(V vertex);
		return null;
	}
}
