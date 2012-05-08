/*
 * Copyright (c) 1997, 2010, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
Class HashGraph<V> - model Class HashSet/LinkedList for new interators
	All Implemented Interfaces:
		Serializable, Cloneable, Iterable<V>, Collection<V>, Set<V>
	Direct Known Subclasses:

	Methods inherited from class java.lang.Object:
		finalize, getClass, notify, notifyAll, wait, wait, wait
	Methods inherited from class java.util.AbstractCollection:
		addAll, containsAll, retainAll, toArray, toArray, toString
	Methods inherited from interface java.util.Graph:
		addAll, containsAll, equals, hashCode, removeAll, retainAll, toArray, toArray
	Methods inherited from class java.util.AbstractGraph:
		equals, hashCode, removeAll
	Constructor Detail:
		public HashGraph()
		public HashGraph(Collection<? extends V> c)
		public HashGraph(int initialCapacity, float loadFactor)
		public HashGraph(int initialCapacity)
	Method Detail:
		public Iterator<V> iterator()
		public int size()
		public boolean isEmpty()
		public boolean contains(Object o)
		public boolean add(V v)
		public boolean remove(Object o)
		public void clear()
		public Object clone()
*/

package org.ipen.miner.collection.graph;

import java.io.Serializable;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

/**
 * This class implements the <tt>Graph</tt> interface, backed by a hash table
 * (actually a <tt>HashMap</tt> instance).  It makes no guarantees as to the
 * iteration order of the graph; in particular, it does not guarantee that the
 * order will remain constant over time.  This class permits the <tt>null</tt>
 * vertex.
 *
 * <p>This class offers constant time performance for the basic operations
 * (<tt>add</tt>, <tt>remove</tt>, <tt>contains</tt> and <tt>size</tt>),
 * assuming the hash function disperses the vertices properly among the
 * buckets.  Iterating over this graph requires time proportional to the sum of
 * the <tt>HashGraph</tt> instance's size (the number of vertices) plus the
 * "capacity" of the backing <tt>HashMap</tt> instance (the number of
 * buckets).  Thus, it's very important not to set the initial capacity too
 * high (or the load factor too low) if iteration performance is important.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a hash graph concurrently, and at least one of
 * the threads modifies the graph, it <i>must</i> be synchronized externally.
 * This is typically accomplished by synchronizing on some object that
 * naturally encapsulates the graph.
 *
 * If no such object exists, the graph should be "wrapped" using the
 * {@link Collections#synchronizedGraph Collections.synchronizedGraph}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the graph:<pre>
 *   Graph s = Collections.synchronizedGraph(new HashGraph(...));</pre>
 *
 * <p>The iterators returned by this class's <tt>iterator</tt> method are
 * <i>fail-fast</i>: if the graph is modified at any time after the iterator is
 * created, in any way except through the iterator's own <tt>remove</tt>
 * method, the Iterator throws a {@link ConcurrentModificationException}.
 * Thus, in the face of concurrent modification, the iterator fails quickly
 * and cleanly, rather than risking arbitrary, non-deterministic behavior at
 * an undetermined time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness: <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <V> the type of vertices maintained by this graph
 *
 * @author  Thiago Reis
 * @see     Collection
 * @see     Set
 * @see     Graph
 * @see     AbstractSet
 * @see     AbstractGraph
 * @see     HashGraph
 * @see     LinkedHashGraph
 * @since   1.0
 */
public class HashGraph<V> extends AbstractGraph<V> implements Cloneable, Serializable {
    static final long serialVersionUID = -5024744406713321676L;

	// Constructors

    /**
     * Constructs a new, empty graph; the backing <tt>HashMap</tt> instance has
     * default initial capacity (16) and load factor (0.75).
     */
    public HashGraph() {
        map = new HashMap<>();
    }

    /**
     * Constructs a new graph containing the vertices in the specified
     * collection.  The <tt>HashMap</tt> is created with default load factor
     * (0.75) and an initial capacity sufficient to contain the vertices in
     * the specified collection.
     *
     * @param c the collection whose vertices are to be placed into this graph
     * @throws NullPointerException if the specified collection is null
     */
    public HashGraph(Collection<? extends V> c) {
		checkNullVertex(c);
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }

    /**
     * Constructs a new, empty graph; the backing <tt>HashMap</tt> instance has
     * the specified initial capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hash map
     * @param      loadFactor        the load factor of the hash map
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero, or if the load factor is nonpositive
     */
    public HashGraph(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }

    /**
     * Constructs a new, empty graph; the backing <tt>HashMap</tt> instance has
     * the specified initial capacity and default load factor (0.75).
     *
     * @param      initialCapacity   the initial capacity of the hash table
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero
     */
    public HashGraph(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

	@Override
	protected boolean concreteAdd(V v) {
		return map.put(v, new HashMap<V, Object>())==null;
	}
}
