/*
 * Copyright (c) 2000, 2006, Oracle and/or its affiliates. All rights reserved.
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

package org.ipen.miner.collection.graph;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * <p>Hash table and linked list implementation of the <tt>Digraph</tt> interface,
 * with predictable iteration order.  This implementation differs from
 * <tt>HashGraph</tt> in that it maintains a doubly-linked list running through
 * all of its entries.  This linked list defines the iteration ordering,
 * which is the order in which vertices were inserted into the digraph
 * (<i>insertion-order</i>).  Note that insertion order is <i>not</i> affected
 * if a vertex is <i>re-inserted</i> into the digraph.  (A vertex <tt>v</tt>
 * is reinserted into a digraph <tt>s</tt> if <tt>s.add(v)</tt> is invoked when
 * <tt>s.contains(v)</tt> would return <tt>true</tt> immediately prior to
 * the invocation.)
 *
 * <p>This implementation spares its clients from the unspecified, generally
 * chaotic ordering provided by {@link HashGraph}, without incurring the
 * increased cost associated with {@link TreeSet}.  It can be used to
 * produce a copy of a digraph that has the same order as the original, regardless
 * of the original digraph's implementation:
 * <pre>
 *     void foo(Digraph g) {
 *         Digraph copy = new LinkedHashGraph(g);
 *         ...
 *     }
 * </pre>
 * This technique is particularly useful if a module takes a digraph on input,
 * copies it, and later returns results whose order is determined by that of
 * the copy.  (Clients generally appreciate having things returned in the same
 * order they were presented.)
 *
 * <p>This class provides all of the optional <tt>Digraph</tt> operations, and
 * permits null vertices.  Like <tt>HashGraph</tt>, it provides constant-time
 * performance for the basic operations (<tt>add</tt>, <tt>contains</tt> and
 * <tt>remove</tt>), assuming the hash function disperses vertices
 * properly among the buckets.  Performance is likely to be just slightly
 * below that of <tt>HashGraph</tt>, due to the added expense of maintaining the
 * linked list, with one exception: Iteration over a <tt>LinkedHashGraph</tt>
 * requires time proportional to the <i>size</i> of the digraph, regardless of
 * its capacity.  Iteration over a <tt>HashGraph</tt> is likely to be more
 * expensive, requiring time proportional to its <i>capacity</i>.
 *
 * <p>A linked hash digraph has two parameters that affect its performance:
 * <i>initial capacity</i> and <i>load factor</i>.  They are defined precisely
 * as for <tt>HashGraph</tt>.  Note, however, that the penalty for choosing an
 * excessively high value for initial capacity is less severe for this class
 * than for <tt>HashGraph</tt>, as iteration times for this class are unaffected
 * by capacity.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a linked hash digraph concurrently, and at least
 * one of the threads modifies the digraph, it <em>must</em> be synchronized
 * externally.  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the digraph.
 *
 * If no such object exists, the digraph should be "wrapped" using the
 * {@link Collections#synchronizedGraph Collections.synchronizedGraph}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the digraph: <pre>
 *   Digraph g = Collections.synchronizedGraph(new LinkedHashGraph(...));</pre>
 *
 * <p>The iterators returned by this class's <tt>iterator</tt> method are
 * <em>fail-fast</em>: if the digraph is modified at any time after the iterator
 * is created, in any way except through the iterator's own <tt>remove</tt>
 * method, the iterator will throw a {@link ConcurrentModificationException}.
 * Thus, in the face of concurrent modification, the iterator fails quickly
 * and cleanly, rather than risking arbitrary, non-deterministic behavior at
 * an undetermined time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:   <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <V> the type of vertices maintained by this digraph
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
public class LinkedHashDigraph<V> extends AbstractDigraph<V> implements Cloneable, Serializable {
	private static final long serialVersionUID = -2851667679971038690L;

	// Constructors

    /**
     * Constructs a new, empty digraph; the backing <tt>HashMap</tt> instance has
     * default initial capacity (16) and load factor (0.75).
     */
    public LinkedHashDigraph() {
        map = new LinkedHashMap<>();
    }

    /**
     * Constructs a new digraph containing the vertices in the specified
     * collection.  The <tt>HashMap</tt> is created with default load factor
     * (0.75) and an initial capacity sufficient to contain the vertices in
     * the specified collection.
     *
     * @param c the collection whose vertices are to be placed into this digraph
     * @throws NullPointerException if the specified collection is null
     */
    public LinkedHashDigraph(Collection<? extends V> c) {
		checkNullVertex(c);
        map = new LinkedHashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }

    /**
     * Constructs a new, empty digraph; the backing <tt>HashMap</tt> instance has
     * the specified initial capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hash map
     * @param      loadFactor        the load factor of the hash map
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero, or if the load factor is nonpositive
     */
    public LinkedHashDigraph(int initialCapacity, float loadFactor) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }

    /**
     * Constructs a new, empty digraph; the backing <tt>HashMap</tt> instance has
     * the specified initial capacity and default load factor (0.75).
     *
     * @param      initialCapacity   the initial capacity of the hash table
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero
     */
    public LinkedHashDigraph(int initialCapacity) {
        map = new LinkedHashMap<>(initialCapacity);
    }

	@Override
	protected boolean concreteAdd(V v) {
        return map.put(v, new LinkedHashMap<V, Object>())==null;
	}
}
