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

import java.util.Iterator;

/**
 * A {@link SortedBag} extended with navigation methods reporting
 * closest matches for given search targets. Methods {@code lower},
 * {@code floor}, {@code ceiling}, and {@code higher} return elements
 * respectively less than, less than or equal, greater than or equal,
 * and greater than a given element, returning {@code null} if there
 * is no such element.  A {@code NavigableBag} may be accessed and
 * traversed in either ascending or descending order.  The {@code
 * descendingBag} method returns a view of the bag with the senses of
 * all relational and directional methods inverted. The performance of
 * ascending operations and views is likely to be faster than that of
 * descending ones.  This interface additionally defines methods
 * {@code pollFirst} and {@code pollLast} that return and remove the
 * lowest and highest element, if one exists, else returning {@code
 * null}.  Methods {@code subBag}, {@code headBag},
 * and {@code tailBag} differ from the like-named {@code
 * SortedBag} methods in accepting additional arguments describing
 * whether lower and upper bounds are inclusive versus exclusive.
 * Subbags of any {@code NavigableBag} must implement the {@code
 * NavigableBag} interface.
 *
 * <p> The return values of navigation methods may be ambiguous in
 * implementations that permit {@code null} elements. However, even
 * in this case the result can be disambiguated by checking
 * {@code contains(null)}. To avoid such issues, implementations of
 * this interface are encouraged to <em>not</em> permit insertion of
 * {@code null} elements. (Note that sorted bags of {@link
 * Comparable} elements intrinsically do not permit {@code null}.)
 *
 * <p>Methods
 * {@link #subBag(Object, Object) subBag(E, E)},
 * {@link #headBag(Object) headBag(E)}, and
 * {@link #tailBag(Object) tailBag(E)}
 * are specified to return {@code SortedBag} to allow existing
 * implementations of {@code SortedBag} to be compatibly retrofitted to
 * implement {@code NavigableBag}, but extensions and implementations
 * of this interface are encouraged to override these methods to return
 * {@code NavigableBag}.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author Thiago Reis
 * @param <E> the type of elements maintained by this bag
 * @since 1.6
 */
public interface NavigableBag<E> extends SortedBag<E> {
    /**
     * Returns the greatest element in this bag strictly less than the
     * given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the greatest element less than {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException if the specified element cannot be
     *         compared with the elements currently in the bag
     * @throws NullPointerException if the specified element is null
     *         and this bag does not permit null elements
     */
    E lower(E e);

    /**
     * Returns the greatest element in this bag less than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the greatest element less than or equal to {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException if the specified element cannot be
     *         compared with the elements currently in the bag
     * @throws NullPointerException if the specified element is null
     *         and this bag does not permit null elements
     */
    E floor(E e);

    /**
     * Returns the least element in this bag greater than or equal to
     * the given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the least element greater than or equal to {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException if the specified element cannot be
     *         compared with the elements currently in the bag
     * @throws NullPointerException if the specified element is null
     *         and this bag does not permit null elements
     */
    E ceiling(E e);

    /**
     * Returns the least element in this bag strictly greater than the
     * given element, or {@code null} if there is no such element.
     *
     * @param e the value to match
     * @return the least element greater than {@code e},
     *         or {@code null} if there is no such element
     * @throws ClassCastException if the specified element cannot be
     *         compared with the elements currently in the bag
     * @throws NullPointerException if the specified element is null
     *         and this bag does not permit null elements
     */
    E higher(E e);

    /**
     * Retrieves and removes the first (lowest) element,
     * or returns {@code null} if this bag is empty.
     *
     * @return the first element, or {@code null} if this bag is empty
     */
    E pollFirst();

    /**
     * Retrieves and removes the last (highest) element,
     * or returns {@code null} if this bag is empty.
     *
     * @return the last element, or {@code null} if this bag is empty
     */
    E pollLast();

    /**
     * Returns an iterator over the elements in this bag, in ascending order.
     *
     * @return an iterator over the elements in this bag, in ascending order
     */
    Iterator<E> iterator();

    /**
     * Returns a reverse order view of the elements contained in this bag.
     * The descending bag is backed by this bag, so changes to the bag are
     * reflected in the descending bag, and vice-versa.  If either bag is
     * modified while an iteration over either bag is in progress (except
     * through the iterator's own {@code remove} operation), the results of
     * the iteration are undefined.
     *
     * <p>The returned bag has an ordering equivalent to
     * <tt>{@link Collections#reverseOrder(Comparator) Collections.reverseOrder}(comparator())</tt>.
     * The expression {@code s.descendingBag().descendingBag()} returns a
     * view of {@code s} essentially equivalent to {@code s}.
     *
     * @return a reverse order view of this bag
     */
    NavigableBag<E> descendingBag();

    /**
     * Returns an iterator over the elements in this bag, in descending order.
     * Equivalent in effect to {@code descendingBag().iterator()}.
     *
     * @return an iterator over the elements in this bag, in descending order
     */
    Iterator<E> descendingIterator();

    /**
     * Returns a view of the portion of this bag whose elements range from
     * {@code fromElement} to {@code toElement}.  If {@code fromElement} and
     * {@code toElement} are equal, the returned bag is empty unless {@code
     * fromInclusive} and {@code toInclusive} are both true.  The returned bag
     * is backed by this bag, so changes in the returned bag are reflected in
     * this bag, and vice-versa.  The returned bag supports all optional bag
     * operations that this bag supports.
     *
     * <p>The returned bag will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint of the returned bag
     * @param fromInclusive {@code true} if the low endpoint
     *        is to be included in the returned view
     * @param toElement high endpoint of the returned bag
     * @param toInclusive {@code true} if the high endpoint
     *        is to be included in the returned view
     * @return a view of the portion of this bag whose elements range from
     *         {@code fromElement}, inclusive, to {@code toElement}, exclusive
     * @throws ClassCastException if {@code fromElement} and
     *         {@code toElement} cannot be compared to one another using this
     *         bag's comparator (or, if the bag has no comparator, using
     *         natural ordering).  Implementations may, but are not required
     *         to, throw this exception if {@code fromElement} or
     *         {@code toElement} cannot be compared to elements currently in
     *         the bag.
     * @throws NullPointerException if {@code fromElement} or
     *         {@code toElement} is null and this bag does
     *         not permit null elements
     * @throws IllegalArgumentException if {@code fromElement} is
     *         greater than {@code toElement}; or if this bag itself
     *         has a restricted range, and {@code fromElement} or
     *         {@code toElement} lies outside the bounds of the range.
     */
    NavigableBag<E> subBag(E fromElement, boolean fromInclusive,
                           E toElement,   boolean toInclusive);

    /**
     * Returns a view of the portion of this bag whose elements are less than
     * (or equal to, if {@code inclusive} is true) {@code toElement}.  The
     * returned bag is backed by this bag, so changes in the returned bag are
     * reflected in this bag, and vice-versa.  The returned bag supports all
     * optional bag operations that this bag supports.
     *
     * <p>The returned bag will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param toElement high endpoint of the returned bag
     * @param inclusive {@code true} if the high endpoint
     *        is to be included in the returned view
     * @return a view of the portion of this bag whose elements are less than
     *         (or equal to, if {@code inclusive} is true) {@code toElement}
     * @throws ClassCastException if {@code toElement} is not compatible
     *         with this bag's comparator (or, if the bag has no comparator,
     *         if {@code toElement} does not implement {@link Comparable}).
     *         Implementations may, but are not required to, throw this
     *         exception if {@code toElement} cannot be compared to elements
     *         currently in the bag.
     * @throws NullPointerException if {@code toElement} is null and
     *         this bag does not permit null elements
     * @throws IllegalArgumentException if this bag itself has a
     *         restricted range, and {@code toElement} lies outside the
     *         bounds of the range
     */
    NavigableBag<E> headBag(E toElement, boolean inclusive);

    /**
     * Returns a view of the portion of this bag whose elements are greater
     * than (or equal to, if {@code inclusive} is true) {@code fromElement}.
     * The returned bag is backed by this bag, so changes in the returned bag
     * are reflected in this bag, and vice-versa.  The returned bag supports
     * all optional bag operations that this bag supports.
     *
     * <p>The returned bag will throw an {@code IllegalArgumentException}
     * on an attempt to insert an element outside its range.
     *
     * @param fromElement low endpoint of the returned bag
     * @param inclusive {@code true} if the low endpoint
     *        is to be included in the returned view
     * @return a view of the portion of this bag whose elements are greater
     *         than or equal to {@code fromElement}
     * @throws ClassCastException if {@code fromElement} is not compatible
     *         with this bag's comparator (or, if the bag has no comparator,
     *         if {@code fromElement} does not implement {@link Comparable}).
     *         Implementations may, but are not required to, throw this
     *         exception if {@code fromElement} cannot be compared to elements
     *         currently in the bag.
     * @throws NullPointerException if {@code fromElement} is null
     *         and this bag does not permit null elements
     * @throws IllegalArgumentException if this bag itself has a
     *         restricted range, and {@code fromElement} lies outside the
     *         bounds of the range
     */
    NavigableBag<E> tailBag(E fromElement, boolean inclusive);

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code subBag(fromElement, true, toElement, false)}.
     *
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    SortedBag<E> subBag(E fromElement, E toElement);

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code headBag(toElement, false)}.
     *
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
na     */
    SortedBag<E> headBag(E toElement);

    /**
     * {@inheritDoc}
     *
     * <p>Equivalent to {@code tailBag(fromElement, true)}.
     *
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    SortedBag<E> tailBag(E fromElement);
}
