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

import java.util.Comparator;

/**
 * A {@link Bag} that further provides a <i>total ordering</i> on its elements.
 * The elements are ordered using their {@linkplain Comparable natural
 * ordering}, or by a {@link Comparator} typically provided at sorted
 * bag creation time.  The bag's iterator will traverse the bag in
 * ascending element order. Several additional operations are provided
 * to take advantage of the ordering.  (This interface is the bag
 * analogue of {@link SortedMap}.)
 *
 * <p>All elements inserted into a sorted bag must implement the <tt>Comparable</tt>
 * interface (or be accepted by the specified comparator).  Furthermore, all
 * such elements must be <i>mutually comparable</i>: <tt>e1.compareTo(e2)</tt>
 * (or <tt>comparator.compare(e1, e2)</tt>) must not throw a
 * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and <tt>e2</tt> in
 * the sorted bag.  Attempts to violate this restriction will cause the
 * offending method or constructor invocation to throw a
 * <tt>ClassCastException</tt>.
 *
 * <p>Note that the ordering maintained by a sorted bag (whether or not an
 * explicit comparator is provided) must be <i>consistent with equals</i> if
 * the sorted bag is to correctly implement the <tt>Bag</tt> interface.  (See
 * the <tt>Comparable</tt> interface or <tt>Comparator</tt> interface for a
 * precise definition of <i>consistent with equals</i>.)  This is so because
 * the <tt>Bag</tt> interface is defined in terms of the <tt>equals</tt>
 * operation, but a sorted bag performs all element comparisons using its
 * <tt>compareTo</tt> (or <tt>compare</tt>) method, so two elements that are
 * deemed equal by this method are, from the standpoint of the sorted bag,
 * equal.  The behavior of a sorted bag <i>is</i> well-defined even if its
 * ordering is inconsistent with equals; it just fails to obey the general
 * contract of the <tt>Bag</tt> interface.
 *
 * <p>All general-purpose sorted bag implementation classes should
 * provide four "standard" constructors: 1) A void (no arguments)
 * constructor, which creates an empty sorted bag sorted according to
 * the natural ordering of its elements.  2) A constructor with a
 * single argument of type <tt>Comparator</tt>, which creates an empty
 * sorted bag sorted according to the specified comparator.  3) A
 * constructor with a single argument of type <tt>Collection</tt>,
 * which creates a new sorted bag with the same elements as its
 * argument, sorted according to the natural ordering of the elements.
 * 4) A constructor with a single argument of type <tt>SortedBag</tt>,
 * which creates a new sorted bag with the same elements and the same
 * ordering as the input sorted bag.  There is no way to enforce this
 * recommendation, as interfaces cannot contain constructors.
 *
 * <p>Note: several methods return subbags with restricted ranges.
 * Such ranges are <i>half-open</i>, that is, they include their low
 * endpoint but not their high endpoint (where applicable).
 * If you need a <i>closed range</i> (which includes both endpoints), and
 * the element type allows for calculation of the successor of a given
 * value, merely request the subrange from <tt>lowEndpoint</tt> to
 * <tt>successor(highEndpoint)</tt>.  For example, suppose that <tt>s</tt>
 * is a sorted bag of strings.  The following idiom obtains a view
 * containing all of the strings in <tt>s</tt> from <tt>low</tt> to
 * <tt>high</tt>, inclusive:<pre>
 *   SortedBag&lt;String&gt; sub = s.subBag(low, high+"\0");</pre>
 *
 * A similar technique can be used to generate an <i>open range</i> (which
 * contains neither endpoint).  The following idiom obtains a view
 * containing all of the Strings in <tt>s</tt> from <tt>low</tt> to
 * <tt>high</tt>, exclusive:<pre>
 *   SortedBag&lt;String&gt; sub = s.subBag(low+"\0", high);</pre>
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements maintained by this bag
 *
 * @author  Thiago Reis
 * @see Bag
 * @see TreeBag
 * @see SortedMap
 * @see Collection
 * @see Comparable
 * @see Comparator
 * @see ClassCastException
 * @since 1.2
 */
public interface SortedBag<E> extends Bag<E> {
    /**
     * Returns the comparator used to order the elements in this bag,
     * or <tt>null</tt> if this bag uses the {@linkplain Comparable
     * natural ordering} of its elements.
     *
     * @return the comparator used to order the elements in this bag,
     *         or <tt>null</tt> if this bag uses the natural ordering
     *         of its elements
     */
	Comparator<? super E> comparator();

    /**
     * Returns a view of the portion of this bag whose elements range
     * from <tt>fromElement</tt>, inclusive, to <tt>toElement</tt>,
     * exclusive.  (If <tt>fromElement</tt> and <tt>toElement</tt> are
     * equal, the returned bag is empty.)  The returned bag is backed
     * by this bag, so changes in the returned bag are reflected in
     * this bag, and vice-versa.  The returned bag supports all
     * optional bag operations that this bag supports.
     *
     * <p>The returned bag will throw an <tt>IllegalArgumentException</tt>
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint (inclusive) of the returned bag
     * @param toElement high endpoint (exclusive) of the returned bag
     * @return a view of the portion of this bag whose elements range from
     *         <tt>fromElement</tt>, inclusive, to <tt>toElement</tt>, exclusive
     * @throws ClassCastException if <tt>fromElement</tt> and
     *         <tt>toElement</tt> cannot be compared to one another using this
     *         bag's comparator (or, if the bag has no comparator, using
     *         natural ordering).  Implementations may, but are not required
     *         to, throw this exception if <tt>fromElement</tt> or
     *         <tt>toElement</tt> cannot be compared to elements currently in
     *         the bag.
     * @throws NullPointerException if <tt>fromElement</tt> or
     *         <tt>toElement</tt> is null and this bag does not permit null
     *         elements
     * @throws IllegalArgumentException if <tt>fromElement</tt> is
     *         greater than <tt>toElement</tt>; or if this bag itself
     *         has a restricted range, and <tt>fromElement</tt> or
     *         <tt>toElement</tt> lies outside the bounds of the range
     */
    SortedBag<E> subBag(E fromElement, E toElement);

    /**
     * Returns a view of the portion of this bag whose elements are
     * strictly less than <tt>toElement</tt>.  The returned bag is
     * backed by this bag, so changes in the returned bag are
     * reflected in this bag, and vice-versa.  The returned bag
     * supports all optional bag operations that this bag supports.
     *
     * <p>The returned bag will throw an <tt>IllegalArgumentException</tt>
     * on an attempt to insert an element outside its range.
     *
     * @param toElement high endpoint (exclusive) of the returned bag
     * @return a view of the portion of this bag whose elements are strictly
     *         less than <tt>toElement</tt>
     * @throws ClassCastException if <tt>toElement</tt> is not compatible
     *         with this bag's comparator (or, if the bag has no comparator,
     *         if <tt>toElement</tt> does not implement {@link Comparable}).
     *         Implementations may, but are not required to, throw this
     *         exception if <tt>toElement</tt> cannot be compared to elements
     *         currently in the bag.
     * @throws NullPointerException if <tt>toElement</tt> is null and
     *         this bag does not permit null elements
     * @throws IllegalArgumentException if this bag itself has a
     *         restricted range, and <tt>toElement</tt> lies outside the
     *         bounds of the range
     */
    SortedBag<E> headBag(E toElement);

    /**
     * Returns a view of the portion of this bag whose elements are
     * greater than or equal to <tt>fromElement</tt>.  The returned
     * bag is backed by this bag, so changes in the returned bag are
     * reflected in this bag, and vice-versa.  The returned bag
     * supports all optional bag operations that this bag supports.
     *
     * <p>The returned bag will throw an <tt>IllegalArgumentException</tt>
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint (inclusive) of the returned bag
     * @return a view of the portion of this bag whose elements are greater
     *         than or equal to <tt>fromElement</tt>
     * @throws ClassCastException if <tt>fromElement</tt> is not compatible
     *         with this bag's comparator (or, if the bag has no comparator,
     *         if <tt>fromElement</tt> does not implement {@link Comparable}).
     *         Implementations may, but are not required to, throw this
     *         exception if <tt>fromElement</tt> cannot be compared to elements
     *         currently in the bag.
     * @throws NullPointerException if <tt>fromElement</tt> is null
     *         and this bag does not permit null elements
     * @throws IllegalArgumentException if this bag itself has a
     *         restricted range, and <tt>fromElement</tt> lies outside the
     *         bounds of the range
     */
    SortedBag<E> tailBag(E fromElement);

    /**
     * Returns the first (lowest) element currently in this bag.
     *
     * @return the first (lowest) element currently in this bag
     * @throws NoSuchElementException if this bag is empty
     */
    E first();

    /**
     * Returns the last (highest) element currently in this bag.
     *
     * @return the last (highest) element currently in this bag
     * @throws NoSuchElementException if this bag is empty
     */
    E last();
}
