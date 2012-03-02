package org.ipen.graph;

import java.util.LinkedHashSet;
import java.util.Map;

public interface Graph<V> extends Map<V, LinkedHashSet<V>>, Iterable {
	// Map Interface
	// Query Operations
	//int size();
	//boolean isEmpty();
	//boolean containsKey(Object key);
	//boolean containsValue(Object value);
	//V get(Object key);

	// Modification Operations
	//V put(K key, V value);
	//V remove(Object key);

	// Bulk Operations
	//void putAll(Map<? extends K, ? extends V> m);
	//void clear();

	// Views
	//Set<K> keySet();
	//Collection<V> values();
	//Set<Map.Entry<K, V>> entrySet();
	//interface Entry<K,V>
	//	K getKey();
	//	V getValue();
	//	V setValue(V value);
	//	boolean equals(Object o);
	//	int hashCode();

	// Comparison and hashing
	//boolean equals(Object o);
	//int hashCode();

	// Iterable Interface
	//Iterator<T> iterator();
}
