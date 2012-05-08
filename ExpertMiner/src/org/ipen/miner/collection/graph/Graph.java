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

/*
Interface Graph<V> - model Interface SortedSet
	All Superinterfaces:
		Collection<E>, Iterable<E>, Set<E>
	All Known Subinterfaces:

	All Known Implementing Classes:

	Methods inherited from interface java.util.Set:
		add, addAll, clear, contains, containsAll, equals, hashCode, isEmpty, iterator, remove, removeAll, retainAll, size, toArray, toArray
	Method Detail:
		Comparator<? super V> comparator()
		//SortedSet<V> subSet(V fromElement, V toElement)
		//SortedSet<V> headSet(V toElement)
		//SortedSet<V> tailSet(V fromElement)
		//V first()
		//V last()
*/

package org.ipen.miner.collection.graph;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * A collection that contains no duplicate vertices.  More formally, graphs
 * contain no pair of vertices <code>v1</code> and <code>v2</code> such that
 * <code>v1.equals(v2)</code>, and at most one null vertex.  As implied by
 * its name, this interface models the mathematical <i>graph</i> abstraction.
 *
 * <p>The <tt>Graph</tt> interface places additional stipulations, beyond those
 * inherited from the <tt>Collection</tt> interface, on the contracts of all
 * constructors and on the contracts of the <tt>add</tt>, <tt>equals</tt> and
 * <tt>hashCode</tt> methods.  Declarations for other inherited methods are
 * also included here for convenience.  (The specifications accompanying these
 * declarations have been tailored to the <tt>Graph</tt> interface, but they do
 * not contain any additional stipulations.)
 *
 * <p>The additional stipulation on constructors is, not surprisingly,
 * that all constructors must create a graph that contains no duplicate vertices
 * (as defined above).
 *
 * <p>Note: Great care must be exercised if mutable objects are used as graph
 * vertices.  The behavior of a graph is not specified if the value of an object
 * is changed in a manner that affects <tt>equals</tt> comparisons while the
 * object is an vertex in the graph.  A special case of this prohibition is
 * that it is not permissible for a graph to contain itself as an vertex.
 *
 * <p>Some graph implementations have restrictions on the vertices that
 * they may contain.  For example, some implementations prohibit null vertices,
 * and some have restrictions on the types of their vertices.  Attempting to
 * add an ineligible vertex throws an unchecked exception, typically
 * <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.  Attempting
 * to query the presence of an ineligible vertex may throw an exception,
 * or it may simply return false; some implementations will exhibit the former
 * behavior and some will exhibit the latter.  More generally, attempting an
 * operation on an ineligible vertex whose completion would not result in
 * the insertion of an ineligible vertex into the graph may throw an
 * exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * <p>This interface is a member of the
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
public interface Graph<V> extends Set<V> {
    // Query Operations

    /**
     * Returns the number of vertices in this graph (its cardinality).  If this
     * graph contains more than <tt>Integer.MAX_VALUE</tt> vertices, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of vertices in this graph (its cardinality)
     */
	@Override
    int size();

    /**
     * Returns <tt>true</tt> if this graph contains no vertices.
     *
     * @return <tt>true</tt> if this graph contains no vertices
     */
	@Override
    boolean isEmpty();

    /**
     * Returns <tt>true</tt> if this graph contains the specified vertex.
     * More formally, returns <tt>true</tt> if and only if this graph
     * contains a vertex <tt>v</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;v==null&nbsp;:&nbsp;o.equals(v))</tt>.
     *
     * @param o vertex whose presence in this graph is to be tested
     * @return <tt>true</tt> if this graph contains the specified vertex
     * @throws ClassCastException if the type of the specified vertex
     *         is incompatible with this graph
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified vertex is null and this
     *         graph does not permit null vertices
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     */
	@Override
    boolean contains(Object o);

    /**
     * Returns the degree of the specified vertex (the number
	 * of edges incident to the vertex, with loops counted twice).
     *
     * @return the degree of the specified vertex
     */
	int degree(V v);

    /**
     * Returns the total number of edges in this graph.
	 *
     * @return the total number of edges in this graph
     */
	int edgeCount();

    /**
     * Returns <tt>true</tt> if the specified <tt>source</tt> vertex is
	 * adjacent (linked) to the specified <tt>target</tt>
     *
     * @param source vertex to be checked against the target
     * @param target vertex to be checked against the source
     * @return <tt>true</tt> if this both vertices are adjacent
     */
	boolean isAdjacent(V source, V target);

    /**
     * Returns an iterator over the vertices in this graph.  The vertices are
     * returned in no particular order (unless this graph is an instance of some
     * class that provides a guarantee).
     *
     * @return an iterator over the vertices in this graph
     */
	@Override
    Iterator<V> iterator();

	/**
	 * Returns a breadth-first iterator over the vertices in this graph,
	 * starting at the specified vertex.
	 *
	 * The breadth-first iterator is <i>fail-fast</i>: if the graph is structurally
	 * modified at any time after the Iterator is created, in any way except
	 * through the breadth-first iterator's own {@code remove} or {@code add}
	 * methods, the breadth-first iterator will throw a
	 * {@code ConcurrentModificationException}.  Thus, in the face of
	 * concurrent modification, the iterator fails quickly and cleanly, rather
	 * than risking arbitrary, non-deterministic behavior at an undetermined
	 * time in the future.
	 *
	 * @param v vertex of the graph to start the breadth-first iterator
	 *          (by a call to {@code next})
	 * @return a breadthFirstIterator of the vertices in this graph
	 *         starting at the specified vertex
	 * @throws NoSuchElementException {@inheritDoc}
	 * @throws ConcurrentModificationException {@inheritDoc}
	 */
	GraphIterator<V> breadthFirstIterator(V v);

	/**
	 * Returns a depth-first iterator over the vertices in this graph,
	 * starting at the specified vertex.
	 *
	 * The depth-first iterator is <i>fail-fast</i>: if the graph is structurally
	 * modified at any time after the Iterator is created, in any way except
	 * through the depth-first iterator's own {@code remove} or {@code add}
	 * methods, the depth-first iterator will throw a
	 * {@code ConcurrentModificationException}.  Thus, in the face of
	 * concurrent modification, the iterator fails quickly and cleanly, rather
	 * than risking arbitrary, non-deterministic behavior at an undetermined
	 * time in the future.
	 *
	 * @param v vertex of the graph to start the depth-first iterator
	 *          (by a call to {@code next})
	 * @return a depthFirstIterator of the vertices in this graph
	 *         starting at the specified vertex
	 * @throws NoSuchElementException {@inheritDoc}
	 * @throws ConcurrentModificationException {@inheritDoc}
	 */
	GraphIterator<V> depthFirstIterator(V v);

    /**
     * Returns an array containing all of the vertices in this graph.
     * If this graph makes any guarantees as to what order its vertices
     * are returned by its iterator, this method must return the
     * vertices in the same order.
     *
     * <p>The returned array will be "safe" in that no references to it
     * are maintained by this graph.  (In other words, this method must
     * allocate a new array even if this graph is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all the vertices in this graph
     */
	@Override
    Object[] toArray();

    /**
     * Returns an array containing all of the vertices in this graph; the
     * runtime type of the returned array is that of the specified array.
     * If the graph fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this graph.
     *
     * <p>If this graph fits in the specified array with room to spare
     * (i.e., the array has more vertices than this graph), the vertex in
     * the array immediately following the end of the graph is set to
     * <tt>null</tt>.  (This is useful in determining the length of this
     * graph <i>only</i> if the caller knows that this graph does not contain
     * any null vertices.)
     *
     * <p>If this graph makes any guarantees as to what order its vertices
     * are returned by its iterator, this method must return the vertices
     * in the same order.
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose <tt>x</tt> is a graph known to contain only strings.
     * The following code can be used to dump the graph into a newly allocated
     * array of <tt>String</tt>:
     *
     * <pre>
     *     String[] y = x.toArray(new String[0]);</pre>
     *
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the array into which the vertices of this graph are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing all the vertices in this graph
     * @throws ArrayStoreException if the runtime type of the specified array
     *         is not a supertype of the runtime type of every vertex in this
     *         graph
     * @throws NullPointerException if the specified array is null
     */
	@Override
    <T> T[] toArray(T[] a);

    // Modification Operations

    /**
     * Adds the specified vertex to this graph if it is not already present
     * (optional operation).  More formally, adds the specified vertex
     * <tt>v</tt> to this graph if the graph contains no vertex <tt>v2</tt>
     * such that
     * <tt>(v==null&nbsp;?&nbsp;v2==null&nbsp;:&nbsp;v.equals(v2))</tt>.
     * If this graph already contains the vertex, the call leaves the graph
     * unchanged and returns <tt>false</tt>.  In combination with the
     * restriction on constructors, this ensures that graphs never contain
     * duplicate vertices.
     *
     * <p>The stipulation above does not imply that graphs must accept all
     * vertices; graphs may refuse to add any particular vertex, including
     * <tt>null</tt>, and throw an exception, as described in the
     * specification for {@link Collection#add Collection.add}.
     * Individual graph implementations should clearly document any
     * restrictions on the vertices that they may contain.
     *
     * @param v vertex to be added to this graph
     * @return <tt>true</tt> if this graph did not already contain the specified
     *         vertex
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *         is not supported by this graph
     * @throws ClassCastException if the class of the specified vertex
     *         prevents it from being added to this graph
     * @throws NullPointerException if the specified vertex is null and this
     *         graph does not permit null vertices
     * @throws IllegalArgumentException if some property of the specified vertex
     *         prevents it from being added to this graph
     */
	@Override
    boolean add(V v);

    /**
     * Removes the specified vertex from this graph if it is present
     * (optional operation).  More formally, removes a vertex <tt>v</tt>
     * such that
     * <tt>(o==null&nbsp;?&nbsp;v==null&nbsp;:&nbsp;o.equals(v))</tt>, if
     * this graph contains such a vertex.  Returns <tt>true</tt> if this graph
     * contained the vertex (or equivalently, if this graph changed as a
     * result of the call).  (This graph will not contain the vertex once the
     * call returns.)
     *
     * @param o object to be removed from this graph, if present
     * @return <tt>true</tt> if this graph contained the specified vertex
     * @throws ClassCastException if the type of the specified vertex
     *         is incompatible with this graph
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified vertex is null and this
     *         graph does not permit null vertices
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *         is not supported by this graph
     */
	@Override
    boolean remove(Object o);

    /**
     * Links the specified <tt>source</tt> vertex to the specified
	 * <tt>target</tt> vertex (creates an edge between both vertices).
     *
     * @param source vertex to be linked to the target
     * @param target vertex to be linked to the source
     * @return <tt>true</tt> if this graph contained the specified source
	 * and target vertices
     */
    boolean link(V source, V target);

    /**
     * Unlinks the specified <tt>source</tt> vertex from the specified
	 * <tt>target</tt> vertex (removes an edge between both vertices).
     *
     * @param source vertex to be unlinked to the target
     * @param target vertex to be unlinked to the source
     * @return <tt>true</tt> if this graph contained the specified source
	 * and target vertices
     */
    boolean unlink(V source, V target);

    // Bulk Operations

    /**
     * Returns <tt>true</tt> if this graph contains all of the vertices of the
     * specified collection.  If the specified collection is also a graph, this
     * method returns <tt>true</tt> if it is a <i>subset</i> of this graph.
     *
     * @param  c collection to be checked for containment in this graph
     * @return <tt>true</tt> if this graph contains all of the vertices of the
     *         specified collection
     * @throws ClassCastException if the types of one or more vertices
     *         in the specified collection are incompatible with this
     *         graph
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *         or more null vertices and this graph does not permit null
     *         vertices
     * (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see    #contains(Object)
     */
	@Override
    boolean containsAll(Collection<?> c);

    /**
     * Adds all of the vertices in the specified collection to this graph if
     * they're not already present (optional operation).  If the specified
     * collection is also a graph, the <tt>addAll</tt> operation effectively
     * modifies this graph so that its value is the <i>union</i> of the two
     * graphs.  The behavior of this operation is undefined if the specified
     * collection is modified while the operation is in progress.
     *
     * @param  c collection containing vertices to be added to this graph
     * @return <tt>true</tt> if this graph changed as a result of the call
     *
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *         is not supported by this graph
     * @throws ClassCastException if the class of a vertex of the
     *         specified collection prevents it from being added to this graph
     * @throws NullPointerException if the specified collection contains one
     *         or more null vertices and this graph does not permit null
     *         vertices, or if the specified collection is null
     * @throws IllegalArgumentException if some property of a vertex of the
     *         specified collection prevents it from being added to this graph
     * @see #add(Object)
     */
	@Override
    boolean addAll(Collection<? extends V> c);

    /**
     * Retains only the vertices in this graph that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this graph all of its vertices that are not contained in the
     * specified collection.  If the specified collection is also a graph, this
     * operation effectively modifies this graph so that its value is the
     * <i>intersection</i> of the two graphs.
     *
     * @param  c collection containing vertices to be retained in this graph
     * @return <tt>true</tt> if this graph changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
     *         is not supported by this graph
     * @throws ClassCastException if the class of a vertex of this graph
     *         is incompatible with the specified collection
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this graph contains a null vertex and the
     *         specified collection does not permit null vertices
     *         (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     */
	@Override
    boolean retainAll(Collection<?> c);

    /**
     * Removes from this graph all of its vertices that are contained in the
     * specified collection (optional operation).  If the specified
     * collection is also a graph, this operation effectively modifies this
     * graph so that its value is the <i>asymmetric graph difference</i> of
     * the two graphs.
     *
     * <p>This implementation determines which is the smaller of this graph
     * and the specified collection, by invoking the <tt>size</tt>
     * method on each.  If this graph has fewer vertices, then the
     * implementation iterates over this graph, checking each vertex
     * returned by the iterator in turn to see if it is contained in
     * the specified collection.  If it is so contained, it is removed
     * from this graph with the iterator's <tt>remove</tt> method.  If
     * the specified collection has fewer vertices, then the
     * implementation iterates over the specified collection, removing
     * from this graph each vertex returned by the iterator, using this
     * graph's <tt>remove</tt> method.
     *
     * <p>Note that this implementation will throw an
     * <tt>UnsupportedOperationException</tt> if the iterator returned by the
     * <tt>iterator</tt> method does not implement the <tt>remove</tt> method.
     *
     * @param  c collection containing vertices to be removed from this graph
     * @return <tt>true</tt> if this graph changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
     *         is not supported by this graph
     * @throws ClassCastException if the class of a vertex of this graph
     *         is incompatible with the specified collection
     * (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if this graph contains a null vertex and the
     *         specified collection does not permit null vertices
     * (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
	@Override
    boolean removeAll(Collection<?> c);

    /**
     * Removes all of the vertices from this graph (optional operation).
     * The graph will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> method
     *         is not supported by this graph
     */
	@Override
    void clear();

	/**
	 * {@inheritDoc}
	 *
	 * <p>This implementation returns a set that subclasses
	 * {@code AbstractSet}.  The subclass stores, in private fields, the
	 * offset of the subList within the backing list, the size of the subList
	 * (which can change over its lifetime), and the expected
	 * {@code modCount} value of the backing list.  There are two variants
	 * of the subclass, one of which implements {@code RandomAccess}.
	 * If this list implements {@code RandomAccess} the returned list will
	 * be an instance of the subclass that implements {@code RandomAccess}.
	 *
	 * <p>The subclass's {@code set(int, E)}, {@code get(int)},
	 * {@code add(int, E)}, {@code remove(int)}, {@code addAll(int,
	 * Collection)} and {@code removeRange(int, int)} methods all
	 * delegate to the corresponding methods on the backing abstract list,
	 * after bounds-checking the index and adjusting for the offset.  The
	 * {@code addAll(Collection c)} method merely returns {@code addAll(size,
	 * c)}.
	 *
	 * <p>The {@code listIterator(int)} method returns a "wrapper object"
	 * over a list iterator on the backing list, which is created with the
	 * corresponding method on the backing list.  The {@code iterator} method
	 * merely returns {@code listIterator()}, and the {@code size} method
	 * merely returns the subclass's {@code size} field.
	 *
	 * <p>All methods first check to see if the actual {@code modCount} of
	 * the backing list is equal to its expected value, and throw a
	 * {@code ConcurrentModificationException} if it is not.
	 *
	 * @throws IndexOutOfBoundsException if an endpoint index value is out of range
	 *         {@code (fromIndex < 0 || toIndex > size)}
	 * @throws IllegalArgumentException if the endpoint indices are out of order
	 *         {@code (fromIndex > toIndex)}
	 */
	Set<V> adjacents(V v);

    // Comparison and hashing

    /**
     * Compares the specified object with this graph for equality.  Returns
     * <tt>true</tt> if the specified object is also a graph, the two graphs
     * have the same size, and every member of the specified graph is
     * contained in this graph (or equivalently, every member of this graph is
     * contained in the specified graph).  This definition ensures that the
     * equals method works properly across different implementations of the
     * graph interface.
     *
     * @param o object to be compared for equality with this graph
     * @return <tt>true</tt> if the specified object is equal to this graph
     */
	@Override
    boolean equals(Object o);

    /**
     * Returns the hash code value for this graph.  The hash code of a graph is
     * defined to be the sum of the hash codes of the vertices in the graph,
     * where the hash code of a <tt>null</tt> vertex is defined to be zero.
     * This ensures that <tt>s1.equals(s2)</tt> implies that
     * <tt>s1.hashCode()==s2.hashCode()</tt> for any two graphs <tt>s1</tt>
     * and <tt>s2</tt>, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * @return the hash code value for this graph
     * @see Object#equals(Object)
     * @see Graph#equals(Object)
     */
	@Override
    int hashCode();
}
