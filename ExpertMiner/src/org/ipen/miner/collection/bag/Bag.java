/*
 * Copyright (c) 1997, 2006, Oracle and/or its affiliates. All rights reserved.
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

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A collection that contains duplicate elements.  More formally, bags
 * contain no pair of elements <code>e1</code> and <code>e2</code> such that
 * <code>e1.equals(e2)</code>, and at most one null element.  As implied by
 * its name, this interface models the mathematical <i>bag</i> abstraction.
 *
 * <p>The <tt>Bag</tt> interface places additional stipulations, beyond those
 * inherited from the <tt>Collection</tt> interface, on the contracts of all
 * constructors and on the contracts of the <tt>add</tt>, <tt>equals</tt> and
 * <tt>hashCode</tt> methods.  Declarations for other inherited methods are
 * also included here for convenience.  (The specifications accompanying these
 * declarations have been tailored to the <tt>Bag</tt> interface, but they do
 * not contain any additional stipulations.)
 *
 * <p>The additional stipulation on constructors is, not surprisingly,
 * that all constructors must create a bag that contains no duplicate elements
 * (as defined above).
 *
 * <p>Note: Great care must be exercised if mutable objects are used as bag
 * elements.  The behavior of a bag is not specified if the value of an object
 * is changed in a manner that affects <tt>equals</tt> comparisons while the
 * object is an element in the bag.  A special case of this prohibition is
 * that it is not permissible for a bag to contain itself as an element.
 *
 * <p>Some bag implementations have restrictions on the elements that
 * they may contain.  For example, some implementations prohibit null elements,
 * and some have restrictions on the types of their elements.  Attempting to
 * add an ineligible element throws an unchecked exception, typically
 * <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.  Attempting
 * to query the presence of an ineligible element may throw an exception,
 * or it may simply return false; some implementations will exhibit the former
 * behavior and some will exhibit the latter.  More generally, attempting an
 * operation on an ineligible element whose completion would not result in
 * the insertion of an ineligible element into the bag may throw an
 * exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements maintained by this bag
 *
 * @author  Thiago Reis
 * @see Collection
 * @see List
 * @see SortedBag
 * @see HashBag
 * @see TreeBag
 * @see AbstractBag
 * @see Collections#singleton(java.lang.Object)
 * @see Collections#EMPTY_SET
 * @since 1.2
 */
public interface Bag<E> extends Collection<E> {
    boolean add(E e, int occurrences);
    boolean remove(Object o, int occurrences);
    boolean removeAll(Object o);
    boolean occurrences(E e, int occurrences);
    int count(E e);
    Set<E> elementSet();
    Set<Bag.Entry> entrySet();

	/**
	* A bag entry (element-occurrences pair).  The <tt>Bag.entrySet</tt> method returns
	* a collection-view of the bag, whose elements are of this class.  The
	* <i>only</i> way to obtain a reference to a bag entry is from the
	* iterator of this collection-view.  These <tt>Bag.Entry</tt> objects are
	* valid <i>only</i> for the duration of the iteration; more formally,
	* the behavior of a bag entry is undefined if the backing bag has been
	* modified after the entry was returned by the iterator, except through
	* the <tt>setOccurrences</tt> operation on the bag entry.
	*
	* @see Bag#entrySet()
	* @since 1.2
	*/
	interface Entry<E> {
		/**
		* Returns the element corresponding to this entry.
		*
		* @return the element corresponding to this entry
		* @throws IllegalStateException implementations may, but are not
		*         required to, throw this exception if the entry has been
		*         removed from the backing bag.
		*/
		E getElement();

		/**
		* Returns the number of occurrences corresponding to this entry.  If the mapping
		* has been removed from the backing bag (by the iterator's
		* <tt>remove</tt> operation), the results of this call are undefined.
		*
		* @return the number of occurrences corresponding to this entry
		* @throws IllegalStateException implementations may, but are not
		*         required to, throw this exception if the entry has been
		*         removed from the backing bag.
		*/
		int getOccurrences();

		/**
		* Replaces the occurrences number corresponding to this entry with the specified
		* value (optional operation).  (Writes through to the bag.)  The
		* behavior of this call is undefined if the mapping has already been
		* removed from the bag (by the iterator's <tt>remove</tt> operation).
		*
		* @param occurrences new occurrences number to be stored in this entry
		* @return old occurrences number corresponding to the entry
		* @throws UnsupportedOperationException if the <tt>put</tt> operation
		*         is not supported by the backing bag
		* @throws ClassCastException if the class of the specified occurrences number
		*         prevents it from being stored in the backing bag
		* @throws NullPointerException if the backing bag does not permit
		*         null values, and the specified value is null
		* @throws IllegalArgumentException if some property of this occurrences number
		*         prevents it from being stored in the backing bag
		* @throws IllegalStateException implementations may, but are not
		*         required to, throw this exception if the entry has been
		*         removed from the backing bag.
		*/
		int setOccurrences(int occurrences);

		/**
		* Compares the specified object with this entry for equality.
		* Returns <tt>true</tt> if the given object is also a bag entry and
		* the two entries represent the same mapping.  More formally, two
		* entries <tt>e1</tt> and <tt>e2</tt> represent the same mapping
		* if<pre>
		*     (e1.getElement()==null ?
		*      e2.getElement()==null : e1.getElement().equals(e2.getElement()))  &amp;&amp;
		*     (e1.getOccurrences()==null ?
		*      e2.getOccurrences()==null : e1.getOccurrences().equals(e2.getOccurrences()))
		* </pre>
		* This ensures that the <tt>equals</tt> method works properly across
		* different implementations of the <tt>Bag.Entry</tt> interface.
		*
		* @param o object to be compared for equality with this bag entry
		* @return <tt>true</tt> if the specified object is equal to this bag
		*         entry
		*/
		@Override
		boolean equals(Object o);

		/**
		* Returns the hash code value for this bag entry.  The hash code
		* of a bag entry <tt>e</tt> is defined to be: <pre>
		*     (e.getElement()==null   ? 0 : e.getElement().hashCode()) ^
		*     (e.getOccurrences()==null ? 0 : e.getOccurrences().hashCode())
		* </pre>
		* This ensures that <tt>e1.equals(e2)</tt> implies that
		* <tt>e1.hashCode()==e2.hashCode()</tt> for any two Entries
		* <tt>e1</tt> and <tt>e2</tt>, as required by the general
		* contract of <tt>Object.hashCode</tt>.
		*
		* @return the hash code value for this bag entry
		* @see Object#hashCode()
		* @see Object#equals(Object)
		* @see #equals(Object)
		*/
		@Override
		int hashCode();
	}

	// Query Operations

	/**
	* Returns the number of elements in this bag (its cardinality).  If this
	* bag contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
	* <tt>Integer.MAX_VALUE</tt>.
	*
	* @return the number of elements in this bag (its cardinality)
	*/
	@Override
	int size();

	/**
	* Returns <tt>true</tt> if this bag contains no elements.
	*
	* @return <tt>true</tt> if this bag contains no elements
	*/
	@Override
	boolean isEmpty();

	/**
	* Returns <tt>true</tt> if this bag contains the specified element.
	* More formally, returns <tt>true</tt> if and only if this bag
	* contains an element <tt>e</tt> such that
	* <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	*
	* @param o element whose presence in this bag is to be tested
	* @return <tt>true</tt> if this bag contains the specified element
	* @throws ClassCastException if the type of the specified element
	*         is incompatible with this bag
	* (<a href="Collection.html#optional-restrictions">optional</a>)
	* @throws NullPointerException if the specified element is null and this
	*         bag does not permit null elements
	* (<a href="Collection.html#optional-restrictions">optional</a>)
	*/
	@Override
	boolean contains(Object o);

	/**
	* Returns an iterator over the elements in this bag.  The elements are
	* returned in no particular order (unless this bag is an instance of some
	* class that provides a guarantee).
	*
	* @return an iterator over the elements in this bag
	*/
	@Override
	Iterator<E> iterator();

	/**
	* Returns an array containing all of the elements in this bag.
	* If this bag makes any guarantees as to what order its elements
	* are returned by its iterator, this method must return the
	* elements in the same order.
	*
	* <p>The returned array will be "safe" in that no references to it
	* are maintained by this bag.  (In other words, this method must
	* allocate a new array even if this bag is backed by an array).
	* The caller is thus free to modify the returned array.
	*
	* <p>This method acts as bridge between array-based and collection-based
	* APIs.
	*
	* @return an array containing all the elements in this bag
	*/
	@Override
	Object[] toArray();

	/**
	* Returns an array containing all of the elements in this bag; the
	* runtime type of the returned array is that of the specified array.
	* If the bag fits in the specified array, it is returned therein.
	* Otherwise, a new array is allocated with the runtime type of the
	* specified array and the size of this bag.
	*
	* <p>If this bag fits in the specified array with room to spare
	* (i.e., the array has more elements than this bag), the element in
	* the array immediately following the end of the bag is set to
	* <tt>null</tt>.  (This is useful in determining the length of this
	* bag <i>only</i> if the caller knows that this bag does not contain
	* any null elements.)
	*
	* <p>If this bag makes any guarantees as to what order its elements
	* are returned by its iterator, this method must return the elements
	* in the same order.
	*
	* <p>Like the {@link #toArray()} method, this method acts as bridge between
	* array-based and collection-based APIs.  Further, this method allows
	* precise control over the runtime type of the output array, and may,
	* under certain circumstances, be used to save allocation costs.
	*
	* <p>Suppose <tt>x</tt> is a bag known to contain only strings.
	* The following code can be used to dump the bag into a newly allocated
	* array of <tt>String</tt>:
	*
	* <pre>
	*     String[] y = x.toArray(new String[0]);</pre>
	*
	* Note that <tt>toArray(new Object[0])</tt> is identical in function to
	* <tt>toArray()</tt>.
	*
	* @param a the array into which the elements of this bag are to be
	*        stored, if it is big enough; otherwise, a new array of the same
	*        runtime type is allocated for this purpose.
	* @return an array containing all the elements in this bag
	* @throws ArrayStoreException if the runtime type of the specified array
	*         is not a supertype of the runtime type of every element in this
	*         bag
	* @throws NullPointerException if the specified array is null
	*/
	@Override
	<T> T[] toArray(T[] a);


	// Modification Operations

	/**
	* Adds the specified element to this bag if it is not already present
	* (optional operation).  More formally, adds the specified element
	* <tt>e</tt> to this bag if the bag contains no element <tt>e2</tt>
	* such that
	* <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
	* If this bag already contains the element, the call leaves the bag
	* unchanged and returns <tt>false</tt>.
	*
	* Bags may refuse to add any particular element, including
	* <tt>null</tt>, and throw an exception, as described in the
	* specification for {@link Collection#add Collection.add}.
	* Individual bag implementations should clearly document any
	* restrictions on the elements that they may contain.
	*
	* @param e element to be added to this bag
	* @return <tt>true</tt> if this bag did not already contain the specified
	*         element
	* @throws UnsupportedOperationException if the <tt>add</tt> operation
	*         is not supported by this bag
	* @throws ClassCastException if the class of the specified element
	*         prevents it from being added to this bag
	* @throws NullPointerException if the specified element is null and this
	*         bag does not permit null elements
	* @throws IllegalArgumentException if some property of the specified element
	*         prevents it from being added to this bag
	*/
	@Override
	boolean add(E e);


	/**
	* Removes the specified element from this bag if it is present
	* (optional operation).  More formally, removes an element <tt>e</tt>
	* such that
	* <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
	* this bag contains such an element.  Returns <tt>true</tt> if this bag
	* contained the element (or equivalently, if this bag changed as a
	* result of the call).  (This bag will not contain the element once the
	* call returns.)
	*
	* @param o object to be removed from this bag, if present
	* @return <tt>true</tt> if this bag contained the specified element
	* @throws ClassCastException if the type of the specified element
	*         is incompatible with this bag
	* (<a href="Collection.html#optional-restrictions">optional</a>)
	* @throws NullPointerException if the specified element is null and this
	*         bag does not permit null elements
	* (<a href="Collection.html#optional-restrictions">optional</a>)
	* @throws UnsupportedOperationException if the <tt>remove</tt> operation
	*         is not supported by this bag
	*/
	@Override
	boolean remove(Object o);


	// Bulk Operations

	/**
	* Returns <tt>true</tt> if this bag contains all of the elements of the
	* specified collection.  If the specified collection is also a bag, this
	* method returns <tt>true</tt> if it is a <i>subset</i> of this bag.
	*
	* @param  c collection to be checked for containment in this bag
	* @return <tt>true</tt> if this bag contains all of the elements of the
	*         specified collection
	* @throws ClassCastException if the types of one or more elements
	*         in the specified collection are incompatible with this
	*         bag
	* (<a href="Collection.html#optional-restrictions">optional</a>)
	* @throws NullPointerException if the specified collection contains one
	*         or more null elements and this bag does not permit null
	*         elements
	* (<a href="Collection.html#optional-restrictions">optional</a>),
	*         or if the specified collection is null
	* @see    #contains(Object)
	*/
	@Override
	boolean containsAll(Collection<?> c);

	/**
	* Adds all of the elements in the specified collection to this bag if
	* they're not already present (optional operation).  If the specified
	* collection is also a bag, the <tt>addAll</tt> operation effectively
	* modifies this bag so that its value is the <i>union</i> of the two
	* bags.  The behavior of this operation is undefined if the specified
	* collection is modified while the operation is in progress.
	*
	* @param  c collection containing elements to be added to this bag
	* @return <tt>true</tt> if this bag changed as a result of the call
	*
	* @throws UnsupportedOperationException if the <tt>addAll</tt> operation
	*         is not supported by this bag
	* @throws ClassCastException if the class of an element of the
	*         specified collection prevents it from being added to this bag
	* @throws NullPointerException if the specified collection contains one
	*         or more null elements and this bag does not permit null
	*         elements, or if the specified collection is null
	* @throws IllegalArgumentException if some property of an element of the
	*         specified collection prevents it from being added to this bag
	* @see #add(Object)
	*/
	@Override
	boolean addAll(Collection<? extends E> c);

	/**
	* Retains only the elements in this bag that are contained in the
	* specified collection (optional operation).  In other words, removes
	* from this bag all of its elements that are not contained in the
	* specified collection.  If the specified collection is also a bag, this
	* operation effectively modifies this bag so that its value is the
	* <i>intersection</i> of the two bags.
	*
	* @param  c collection containing elements to be retained in this bag
	* @return <tt>true</tt> if this bag changed as a result of the call
	* @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
	*         is not supported by this bag
	* @throws ClassCastException if the class of an element of this bag
	*         is incompatible with the specified collection
	* (<a href="Collection.html#optional-restrictions">optional</a>)
	* @throws NullPointerException if this bag contains a null element and the
	*         specified collection does not permit null elements
	*         (<a href="Collection.html#optional-restrictions">optional</a>),
	*         or if the specified collection is null
	* @see #remove(Object)
	*/
	@Override
	boolean retainAll(Collection<?> c);

	/**
	* Removes from this bag all of its elements that are contained in the
	* specified collection (optional operation).  If the specified
	* collection is also a bag, this operation effectively modifies this
	* bag so that its value is the <i>asymmetric bag difference</i> of
	* the two bags.
	*
	* @param  c collection containing elements to be removed from this bag
	* @return <tt>true</tt> if this bag changed as a result of the call
	* @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
	*         is not supported by this bag
	* @throws ClassCastException if the class of an element of this bag
	*         is incompatible with the specified collection
	* (<a href="Collection.html#optional-restrictions">optional</a>)
	* @throws NullPointerException if this bag contains a null element and the
	*         specified collection does not permit null elements
	*         (<a href="Collection.html#optional-restrictions">optional</a>),
	*         or if the specified collection is null
	* @see #remove(Object)
	* @see #contains(Object)
	*/
	@Override
	boolean removeAll(Collection<?> c);

	/**
	* Removes all of the elements from this bag (optional operation).
	* The bag will be empty after this call returns.
	*
	* @throws UnsupportedOperationException if the <tt>clear</tt> method
	*         is not supported by this bag
	*/
	@Override
	void clear();

	// Comparison and hashing

	/**
	* Compares the specified object with this bag for equality.  Returns
	* <tt>true</tt> if the specified object is also a bag, the two bags
	* have the same size, and every member of the specified bag is
	* contained in this bag (or equivalently, every member of this bag is
	* contained in the specified bag).  This definition ensures that the
	* equals method works properly across different implementations of the
	* bag interface.
	*
	* @param o object to be compared for equality with this bag
	* @return <tt>true</tt> if the specified object is equal to this bag
	*/
	@Override
	boolean equals(Object o);

	/**
	* Returns the hash code value for this bag.  The hash code of a bag is
	* defined to be the sum of the hash codes of the elements in the bag,
	* where the hash code of a <tt>null</tt> element is defined to be zero.
	* This ensures that <tt>s1.equals(s2)</tt> implies that
	* <tt>s1.hashCode()==s2.hashCode()</tt> for any two bags <tt>s1</tt>
	* and <tt>s2</tt>, as required by the general contract of
	* {@link Object#hashCode}.
	*
	* @return the hash code value for this bag
	* @see Object#equals(Object)
	* @see Bag#equals(Object)
	*/
	@Override
	int hashCode();
}
