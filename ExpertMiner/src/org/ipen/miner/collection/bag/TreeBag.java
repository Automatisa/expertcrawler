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
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * A {@link NavigableBag} implementation based on a {@link TreeMap}.
 * The elements are ordered using their {@linkplain Comparable natural
 * ordering}, or by a {@link Comparator} provided at bag creation
 * time, depending on which constructor is used.
 *
 * <p>This implementation provides guaranteed log(n) time cost for the basic
 * operations ({@code add}, {@code remove} and {@code contains}).
 *
 * <p>Note that the ordering maintained by a bag (whether or not an explicit
 * comparator is provided) must be <i>consistent with equals</i> if it is to
 * correctly implement the {@code Bag} interface.  (See {@code Comparable}
 * or {@code Comparator} for a precise definition of <i>consistent with
 * equals</i>.)  This is so because the {@code Bag} interface is defined in
 * terms of the {@code equals} operation, but a {@code TreeBag} instance
 * performs all element comparisons using its {@code compareTo} (or
 * {@code compare}) method, so two elements that are deemed equal by this method
 * are, from the standpoint of the bag, equal.  The behavior of a bag
 * <i>is</i> well-defined even if its ordering is inconsistent with equals; it
 * just fails to obey the general contract of the {@code Bag} interface.
 *
 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a tree bag concurrently, and at least one
 * of the threads modifies the bag, it <i>must</i> be synchronized
 * externally.  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the bag.
 * If no such object exists, the bag should be "wrapped" using the
 * {@link Collections#synchronizedSortedBag Collections.synchronizedSortedBag}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the bag: <pre>
 *   SortedBag s = Collections.synchronizedSortedBag(new TreeBag(...));</pre>
 *
 * <p>The iterators returned by this class's {@code iterator} method are
 * <i>fail-fast</i>: if the bag is modified at any time after the iterator is
 * created, in any way except through the iterator's own {@code remove}
 * method, the iterator will throw a {@link ConcurrentModificationException}.
 * Thus, in the face of concurrent modification, the iterator fails quickly
 * and cleanly, rather than risking arbitrary, non-deterministic behavior at
 * an undetermined time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw {@code ConcurrentModificationException} on a best-effort basis.
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
 * @see     Collection
 * @see     Bag
 * @see     HashBag
 * @see     Comparable
 * @see     Comparator
 * @see     TreeMap
 * @since   1.2
 */
public class TreeBag<E> extends AbstractBag<E> implements NavigableBag<E>, Cloneable, Serializable {
    private static final long serialVersionUID = -2479143000061671589L;

	/**
     * The backing map.
     */
    //private transient NavigableMap<E, Integer> m;

    // Dummy value to associate with an Object in the backing Map
    //private static final Object PRESENT = new Object();

    /**
     * Constructs a bag backed by the specified navigable map.
     */
    TreeBag(NavigableMap<E, Integer> m) {
		map = m;
        //this.m = m;
    }

    /**
     * Constructs a new, empty tree bag, sorted according to the
     * natural ordering of its elements.  All elements inserted into
     * the bag must implement the {@link Comparable} interface.
     * Furthermore, all such elements must be <i>mutually
     * comparable</i>: {@code e1.compareTo(e2)} must not throw a
     * {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the bag.  If the user attempts to add an element
     * to the bag that violates this constraint (for example, the user
     * attempts to add a string element to a bag whose elements are
     * integers), the {@code add} call will throw a
     * {@code ClassCastException}.
     */
    public TreeBag() {
        this(new TreeMap<E, Integer>());
    }

    /**
     * Constructs a new, empty tree bag, sorted according to the specified
     * comparator.  All elements inserted into the bag must be <i>mutually
     * comparable</i> by the specified comparator: {@code comparator.compare(e1,
     * e2)} must not throw a {@code ClassCastException} for any elements
     * {@code e1} and {@code e2} in the bag.  If the user attempts to add
     * an element to the bag that violates this constraint, the
     * {@code add} call will throw a {@code ClassCastException}.
     *
     * @param comparator the comparator that will be used to order this bag.
     *        If {@code null}, the {@linkplain Comparable natural
     *        ordering} of the elements will be used.
     */
    public TreeBag(Comparator<? super E> comparator) {
        this(new TreeMap<E, Integer>(comparator));
    }

    /**
     * Constructs a new tree bag containing the elements in the specified
     * collection, sorted according to the <i>natural ordering</i> of its
     * elements.  All elements inserted into the bag must implement the
     * {@link Comparable} interface.  Furthermore, all such elements must be
     * <i>mutually comparable</i>: {@code e1.compareTo(e2)} must not throw a
     * {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the bag.
     *
     * @param c collection whose elements will comprise the new bag
     * @throws ClassCastException if the elements in {@code c} are
     *         not {@link Comparable}, or are not mutually comparable
     * @throws NullPointerException if the specified collection is null
     */
    public TreeBag(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    /**
     * Constructs a new tree bag containing the same elements and
     * using the same ordering as the specified sorted bag.
     *
     * @param s sorted bag whose elements will comprise the new bag
     * @throws NullPointerException if the specified sorted bag is null
     */
    public TreeBag(SortedBag<E> s) {
        this(s.comparator());
        addAll(s);
    }

    /**
     * Returns an iterator over the elements in this bag in ascending order.
     *
     * @return an iterator over the elements in this bag in ascending order
     */
	@Override
    public Iterator<E> iterator() {
        return ((NavigableMap)map).navigableKeySet().iterator();
    }

    /**
     * Returns an iterator over the elements in this bag in descending order.
     *
     * @return an iterator over the elements in this bag in descending order
     * @since 1.6
     */
	@Override
    public Iterator<E> descendingIterator() {
        return ((NavigableMap)map).descendingKeySet().iterator();
    }

    /**
     * @since 1.6
     */
	@Override
    public NavigableBag<E> descendingBag() {
        return new TreeBag<>(((NavigableMap)map).descendingMap());
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
     * Returns {@code true} if this bag contains no elements.
     *
     * @return {@code true} if this bag contains no elements
     */
	@Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Returns {@code true} if this bag contains the specified element.
     * More formally, returns {@code true} if and only if this bag
     * contains an element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o object to be checked for containment in this bag
     * @return {@code true} if this bag contains the specified element
     * @throws ClassCastException if the specified object cannot be compared
     *         with the elements currently in the bag
     * @throws NullPointerException if the specified element is null
     *         and this bag uses natural ordering, or its comparator
     *         does not permit null elements
     */
	@Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    /**
     * Adds the specified element to this bag if it is not already present.
     * More formally, adds the specified element {@code e} to this bag if
     * the bag contains no element {@code e2} such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
     * If this bag already contains the element, the call leaves the bag
     * unchanged and returns {@code false}.
     *
     * @param e element to be added to this bag
     * @return {@code true} if this bag did not already contain the specified
     *         element
     * @throws ClassCastException if the specified object cannot be compared
     *         with the elements currently in this bag
     * @throws NullPointerException if the specified element is null
     *         and this bag uses natural ordering, or its comparator
     *         does not permit null elements
     */
	@Override
    public boolean add(E e) {
		return change(e, 1, true);
    }

    /**
     * Removes the specified element from this bag if it is present.
     * More formally, removes an element {@code e} such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>,
     * if this bag contains such an element.  Returns {@code true} if
     * this bag contained the element (or equivalently, if this bag
     * changed as a result of the call).  (This bag will not contain the
     * element once the call returns.)
     *
     * @param o object to be removed from this bag, if present
     * @return {@code true} if this bag contained the specified element
     * @throws ClassCastException if the specified object cannot be compared
     *         with the elements currently in this bag
     * @throws NullPointerException if the specified element is null
     *         and this bag uses natural ordering, or its comparator
     *         does not permit null elements
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
     * Adds all of the elements in the specified collection to this bag.
     *
     * @param c collection containing elements to be added to this bag
     * @return {@code true} if this bag changed as a result of the call
     * @throws ClassCastException if the elements provided cannot be compared
     *         with the elements currently in the bag
     * @throws NullPointerException if the specified collection is null or
     *         if any element is null and this bag uses natural ordering, or
     *         its comparator does not permit null elements
     */
	@Override
    public  boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
        // Use linear-time version if applicable
		/*
        if (m.size()==0 && c.size() > 0 && c instanceof SortedBag && m instanceof TreeMap) {
            SortedBag<? extends E> set = (SortedBag<? extends E>) c;
            TreeMap<E, Integer> map = (TreeMap<E, Integer>) m;
            Comparator<? super E> cc = (Comparator<? super E>) set.comparator();
            Comparator<? super E> mc = map.comparator();
            if (cc==mc || (cc != null && cc.equals(mc))) {
                map.addAllForTreeSet(set, PRESENT);
                return true;
            }
        }
        return super.addAll(c);
		*/
    }

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} or {@code toElement}
     *         is null and this bag uses natural ordering, or its comparator
     *         does not permit null elements
     * @throws IllegalArgumentException {@inheritDoc}
     * @since 1.6
     */
	@Override
    public NavigableBag<E> subBag(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return new TreeBag<>(((NavigableMap)map).subMap(fromElement, fromInclusive, toElement, toInclusive));
    }

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if {@code toElement} is null and
     *         this bag uses natural ordering, or its comparator does
     *         not permit null elements
     * @throws IllegalArgumentException {@inheritDoc}
     * @since 1.6
     */
	@Override
    public NavigableBag<E> headBag(E toElement, boolean inclusive) {
        return new TreeBag<>(((NavigableMap)map).headMap(toElement, inclusive));
    }

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} is null and
     *         this bag uses natural ordering, or its comparator does
     *         not permit null elements
     * @throws IllegalArgumentException {@inheritDoc}
     * @since 1.6
     */
	@Override
    public NavigableBag<E> tailBag(E fromElement, boolean inclusive) {
        return new TreeBag<>(((NavigableMap)map).tailMap(fromElement, inclusive));
    }

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} or
     *         {@code toElement} is null and this bag uses natural ordering,
     *         or its comparator does not permit null elements
     * @throws IllegalArgumentException {@inheritDoc}
     */
	@Override
    public SortedBag<E> subBag(E fromElement, E toElement) {
        return subBag(fromElement, true, toElement, false);
    }

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if {@code toElement} is null
     *         and this bag uses natural ordering, or its comparator does
     *         not permit null elements
     * @throws IllegalArgumentException {@inheritDoc}
     */
	@Override
    public SortedBag<E> headBag(E toElement) {
        return headBag(toElement, false);
    }

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if {@code fromElement} is null
     *         and this bag uses natural ordering, or its comparator does
     *         not permit null elements
     * @throws IllegalArgumentException {@inheritDoc}
     */
	@Override
    public SortedBag<E> tailBag(E fromElement) {
        return tailBag(fromElement, true);
    }

	@Override
    public Comparator<? super E> comparator() {
        return ((NavigableMap)map).comparator();
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
	@Override
    public E first() {
        return (E)((NavigableMap)map).firstKey();
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
	@Override
    public E last() {
        return (E)((NavigableMap)map).lastKey();
    }

    // NavigableBag API methods

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     *         and this bag uses natural ordering, or its comparator
     *         does not permit null elements
     * @since 1.6
     */
	@Override
    public E lower(E e) {
        return (E)((NavigableMap)map).lowerKey(e);
    }

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     *         and this bag uses natural ordering, or its comparator
     *         does not permit null elements
     * @since 1.6
     */
	@Override
    public E floor(E e) {
        return (E)((NavigableMap)map).floorKey(e);
    }

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     *         and this bag uses natural ordering, or its comparator
     *         does not permit null elements
     * @since 1.6
     */
	@Override
    public E ceiling(E e) {
        return (E)((NavigableMap)map).ceilingKey(e);
    }

    /**
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException if the specified element is null
     *         and this bag uses natural ordering, or its comparator
     *         does not permit null elements
     * @since 1.6
     */
	@Override
    public E higher(E e) {
        return (E)((NavigableMap)map).higherKey(e);
    }

    /**
     * @since 1.6
     */
	@Override
    public E pollFirst() {
        Map.Entry<E,?> e = ((NavigableMap)map).pollFirstEntry();
        return (e == null) ? null : e.getKey();
    }

    /**
     * @since 1.6
     */
	@Override
    public E pollLast() {
        Map.Entry<E,?> e = ((NavigableMap)map).pollLastEntry();
        return (e == null) ? null : e.getKey();
    }

    /**
     * Returns a shallow copy of this {@code TreeBag} instance. (The elements
     * themselves are not cloned.)
     *
     * @return a shallow copy of this bag
     */
	@Override
    public Object clone() {
        TreeBag<E> clone = null;
        try {
            clone = (TreeBag<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }

        clone.map = new TreeMap<>(map);
        return clone;
    }

    /**
     * Save the state of the {@code TreeBag} instance to a stream (that is,
     * serialize it).
     *
     * @serialData Emits the comparator used to order this bag, or
     *             {@code null} if it obeys its elements' natural ordering
     *             (Object), followed by the size of the bag (the number of
     *             elements it contains) (int), followed by all of its
     *             elements (each an Object) in order (as determined by the
     *             bag's Comparator, or by the elements' natural ordering if
     *             the bag has no Comparator).
     */
/*
	private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        // Write out any hidden stuff
        s.defaultWriteObject();

        // Write out Comparator
        s.writeObject(m.comparator());

        // Write out size
        s.writeInt(m.size());

        // Write out all elements in the proper order.
        for (E e : m.keySet())
            s.writeObject(e);
    }
*/
    /**
     * Reconstitute the {@code TreeBag} instance from a stream (that is,
     * deserialize it).
     */
/*
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
        // Read in any hidden stuff
        s.defaultReadObject();

        // Read in Comparator
        Comparator<? super E> c = (Comparator<? super E>) s.readObject();

        // Create backing TreeMap
        TreeMap<E,Object> tm;
        if (c==null)
            tm = new TreeMap<>();
        else
            tm = new TreeMap<>(c);
        m = tm;

        // Read in size
        int size = s.readInt();

        tm.readTreeBag(size, s, PRESENT);
    }
*/
}
