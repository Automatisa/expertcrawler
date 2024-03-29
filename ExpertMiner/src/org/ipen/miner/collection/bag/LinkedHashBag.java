/*
 * Copyright (c) 1998, 2006, Oracle and/or its affiliates. All rights reserved.
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

package org.ipen.miner.collection.bag;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>Hash table and linked list implementation of the <tt>Bag</tt> interface,
 * with predictable iteration order.  This implementation differs from
 * <tt>HashBag</tt> in that it maintains a doubly-linked list running through
 * all of its entries.  This linked list defines the iteration ordering,
 * which is the order in which elements were inserted into the bag
 * (<i>insertion-order</i>).  Note that insertion order is <i>not</i> affected
 * if an element is <i>re-inserted</i> into the bag.  (An element <tt>e</tt>
 * is reinserted into a bag <tt>s</tt> if <tt>s.add(e)</tt> is invoked when
 * <tt>s.contains(e)</tt> would return <tt>true</tt> immediately prior to
 * the invocation.)
 *
 * <p>This implementation spares its clients from the unspecified, generally
 * chaotic ordering provided by {@link HashBag}, without incurring the
 * increased cost associated with {@link TreeBag}.  It can be used to
 * produce a copy of a bag that has the same order as the original, regardless
 * of the original bag's implementation:
 * <pre>
 *     void foo(Bag s) {
 *         Bag copy = new LinkedHashBag(s);
 *         ...
 *     }
 * </pre>
 * This technique is particularly useful if a module takes a bag on input,
 * copies it, and later returns results whose order is determined by that of
 * the copy.  (Clients generally appreciate having things returned in the same
 * order they were presented.)
 *
 * <p>This class provides all of the optional <tt>Bag</tt> operations, and
 * permits null elements.  Like <tt>HashBag</tt>, it provides constant-time
 * performance for the basic operations (<tt>add</tt>, <tt>contains</tt> and
 * <tt>remove</tt>), assuming the hash function disperses elements
 * properly among the buckets.  Performance is likely to be just slightly
 * below that of <tt>HashBag</tt>, due to the added expense of maintaining the
 * linked list, with one exception: Iteration over a <tt>LinkedHashBag</tt>
 * requires time proportional to the <i>size</i> of the bag, regardless of
 * its capacity.  Iteration over a <tt>HashBag</tt> is likely to be more
 * expensive, requiring time proportional to its <i>capacity</i>.
 *
 * <p>A linked hash bag has two parameters that affect its performance:
 * <i>initial capacity</i> and <i>load factor</i>.  They are defined precisely
 * as for <tt>HashBag</tt>.  Note, however, that the penalty for choosing an
 * excessively high value for initial capacity is less severe for this class
 * than for <tt>HashBag</tt>, as iteration times for this class are unaffected
 * by capacity.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a linked hash bag concurrently, and at least
 * one of the threads modifies the bag, it <em>must</em> be synchronized
 * externally.  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the bag.
 *
 * If no such object exists, the bag should be "wrapped" using the
 * {@link Collections#synchronizedBag Collections.synchronizedBag}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the bag: <pre>
 *   Bag s = Collections.synchronizedBag(new LinkedHashBag(...));</pre>
 *
 * <p>The iterators returned by this class's <tt>iterator</tt> method are
 * <em>fail-fast</em>: if the bag is modified at any time after the iterator
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
 * @param <E> the type of elements maintained by this bag
 *
 * @author  Thiago Reis
 * @see     Object#hashCode()
 * @see     Collection
 * @see     Bag
 * @see     HashBag
 * @see     TreeBag
 * @see     Hashtable
 * @since   1.4
 */
public class LinkedHashBag<E> extends HashBag<E> implements Bag<E>, Cloneable, Serializable {
    private static final long serialVersionUID = -2851667679971038690L;

    /**
     * Constructs a new, empty linked hash bag with the specified initial
     * capacity and load factor.
     *
     * @param      initialCapacity the initial capacity of the linked hash bag
     * @param      loadFactor      the load factor of the linked hash bag
     * @throws     IllegalArgumentException  if the initial capacity is less
     *               than zero, or if the load factor is nonpositive
     */
    public LinkedHashBag(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
    }

    /**
     * Constructs a new, empty linked hash bag with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param   initialCapacity   the initial capacity of the LinkedHashBag
     * @throws  IllegalArgumentException if the initial capacity is less
     *              than zero
     */
    public LinkedHashBag(int initialCapacity) {
        super(initialCapacity, .75f, true);
    }

    /**
     * Constructs a new, empty linked hash bag with the default initial
     * capacity (16) and load factor (0.75).
     */
    public LinkedHashBag() {
        super(16, .75f, true);
    }

    /**
     * Constructs a new linked hash bag with the same elements as the
     * specified collection.  The linked hash bag is created with an initial
     * capacity sufficient to hold the elements in the specified collection
     * and the default load factor (0.75).
     *
     * @param c  the collection whose elements are to be placed into
     *           this bag
     * @throws NullPointerException if the specified collection is null
     */
    public LinkedHashBag(Collection<? extends E> c) {
        super(Math.max(2*c.size(), 11), .75f, true);
        addAll(c);
    }
}
