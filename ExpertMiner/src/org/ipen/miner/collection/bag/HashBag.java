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
import java.util.*;


/**
 * This class implements the <tt>Bag</tt> interface, backed by a hash table
 * (actually a <tt>HashMap</tt> instance).  It makes no guarantees as to the
 * iteration order of the bag; in particular, it does not guarantee that the
 * order will remain constant over time.  This class permits the <tt>null</tt>
 * element.
 *
 * <p>This class offers constant time performance for the basic operations
 * (<tt>add</tt>, <tt>remove</tt>, <tt>contains</tt> and <tt>size</tt>),
 * assuming the hash function disperses the elements properly among the
 * buckets.  Iterating over this bag requires time proportional to the sum of
 * the <tt>HashBag</tt> instance's size (the number of elements) plus the
 * "capacity" of the backing <tt>HashMap</tt> instance (the number of
 * buckets).  Thus, it's very important not to set the initial capacity too
 * high (or the load factor too low) if iteration performance is important.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a hash bag concurrently, and at least one of
 * the threads modifies the bag, it <i>must</i> be synchronized externally.
 * This is typically accomplished by synchronizing on some object that
 * naturally encapsulates the bag.
 *
 * If no such object exists, the bag should be "wrapped" using the
 * {@link Collections#synchronizedBag Collections.synchronizedBag}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the bag:<pre>
 *   Bag s = Collections.synchronizedBag(new HashBag(...));</pre>
 *
 * <p>The iterators returned by this class's <tt>iterator</tt> method are
 * <i>fail-fast</i>: if the bag is modified at any time after the iterator is
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
 * @param <E> the type of elements maintained by this bag
 *
 * @author  Thiago Reis
 * @see     Collection
 * @see     Bag
 * @see     TreeBag
 * @see     HashMap
 * @since   1.2
 */
public class HashBag<E> extends AbstractBag<E> implements Bag<E>, Cloneable, Serializable {
    static final long serialVersionUID = -5024744406713321676L;

    //private transient HashMap<E, Integer> map;

    // Dummy value to associate with an Object in the backing Map
    //private static final Object PRESENT = new Object();

    /**
     * Constructs a new, empty bag; the backing <tt>HashMap</tt> instance has
     * default initial capacity (16) and load factor (0.75).
     */
    public HashBag() {
        map = new HashMap<>();
    }

    /**
     * Constructs a new bag containing the elements in the specified
     * collection.  The <tt>HashMap</tt> is created with default load factor
     * (0.75) and an initial capacity sufficient to contain the elements in
     * the specified collection.
     *
     * @param c the collection whose elements are to be placed into this bag
     * @throws NullPointerException if the specified collection is null
     */
    public HashBag(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }

    /**
     * Constructs a new, empty bag; the backing <tt>HashMap</tt> instance has
     * the specified initial capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hash map
     * @param      loadFactor        the load factor of the hash map
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero, or if the load factor is nonpositive
     */
    public HashBag(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }

    /**
     * Constructs a new, empty bag; the backing <tt>HashMap</tt> instance has
     * the specified initial capacity and default load factor (0.75).
     *
     * @param      initialCapacity   the initial capacity of the hash table
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero
     */
    public HashBag(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    /**
     * Constructs a new, empty linked hash bag.  (This package private
     * constructor is only used by LinkedHashBag.) The backing
     * HashMap instance is a LinkedHashMap with the specified initial
     * capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hash map
     * @param      loadFactor        the load factor of the hash map
     * @param      dummy             ignored (distinguishes this
     *             constructor from other int, float constructor.)
     * @throws     IllegalArgumentException if the initial capacity is less
     *             than zero, or if the load factor is nonpositive
     */
    HashBag(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }

    /**
     * Returns an iterator over the elements in this bag.  The elements
     * are returned in no particular order.
     *
     * @return an Iterator over the elements in this bag
     * @see ConcurrentModificationException
     */
	@Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    /**
     * Returns the number of elements in this bag (its cardinality).
     *
     * @return the number of elements in this bag (its cardinality)
     */
	@Override
    public int size() {
        return map.size();
    }

    /**
     * Returns <tt>true</tt> if this bag contains no elements.
     *
     * @return <tt>true</tt> if this bag contains no elements
     */
	@Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Returns <tt>true</tt> if this bag contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this bag
     * contains an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this bag is to be tested
     * @return <tt>true</tt> if this bag contains the specified element
     */
	@Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    /**
     * Adds the specified element to this bag if it is not already present.
     * More formally, adds the specified element <tt>e</tt> to this bag if
     * this bag contains no element <tt>e2</tt> such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
     * If this bag already contains the element, the call leaves the bag
     * unchanged and returns <tt>false</tt>.
     *
     * @param e element to be added to this bag
     * @return <tt>true</tt> if this bag did not already contain the specified
     * element
     */
	@Override
    public boolean add(E e) {
		return change(e, 1, true);
    }

    /**
     * Removes the specified element from this bag if it is present.
     * More formally, removes an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>,
     * if this bag contains such an element.  Returns <tt>true</tt> if
     * this bag contained the element (or equivalently, if this bag
     * changed as a result of the call).  (This bag will not contain the
     * element once the call returns.)
     *
     * @param o object to be removed from this bag, if present
     * @return <tt>true</tt> if the bag contained the specified element
     */
	@Override
    public boolean remove(Object o) {
		return change(o, 1, false);
    }

    /**
     * Removes all of the elements from this bag.
     * The bag will be empty after this call returns.
     */
	@Override
    public void clear() {
        map.clear();
    }

    /**
     * Returns a shallow copy of this <tt>HashBag</tt> instance: the elements
     * themselves are not cloned.
     *
     * @return a shallow copy of this bag
     */
	@Override
    public Object clone() {
		try {
			HashBag<E> newBag = (HashBag<E>)super.clone();
			HashMap<CharSequence, Integer> newMap = (HashMap<CharSequence, Integer>)super.map;
			newBag.map = (HashMap<E, Integer>)newMap.clone();
			return newBag;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

    /**
     * Save the state of this <tt>HashBag</tt> instance to a stream (that is,
     * serialize it).
     *
     * @serialData The capacity of the backing <tt>HashMap</tt> instance
     *             (int), and its load factor (float) are emitted, followed by
     *             the size of the bag (the number of elements it contains)
     *             (int), followed by all of its elements (each an Object) in
     *             no particular order.
     */
/*
	private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out HashMap capacity and load factor
        s.writeInt(map.capacity());
        s.writeFloat(map.loadFactor());

        // Write out size
        s.writeInt(map.size());

        // Write out all elements in the proper order.
        for (E e : map.keySet())
            s.writeObject(e);
    }
*/
    /**
     * Reconstitute the <tt>HashBag</tt> instance from a stream (that is,
     * deserialize it).
     */
/*
	private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();

        // Read in HashMap capacity and load factor and create backing HashMap
        int capacity = s.readInt();
        float loadFactor = s.readFloat();
        map = (((HashBag)this) instanceof LinkedHashBag ?
               new LinkedHashMap<E,Object>(capacity, loadFactor) :
               new HashMap<E,Object>(capacity, loadFactor));

        // Read in size
        int size = s.readInt();

        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            E e = (E) s.readObject();
            map.put(e, PRESENT);
        }
    }
*/
}
