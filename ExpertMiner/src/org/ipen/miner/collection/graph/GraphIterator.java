/*
 * Copyright (c) 1997, 2007, Oracle and/or its affiliates. All rights reserved.
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

import java.util.Iterator;

/**
 * An iterator for graphs that allows the programmer
 * to traverse the graph in either direction, modify
 * the graph during iteration, and obtain the iterator's
 * current position in the graph. A {@code ListIterator}
 * has no current vertex; its <I>cursor position</I> always
 * lies between the vertex that would be returned by a call
 * to {@code previous()} and the vertex that would be
 * returned by a call to {@code next()}.
 * An iterator for a graph of length {@code n} has {@code n+1} possible
 * cursor positions, as illustrated by the carets ({@code ^}) below:
 * <PRE>
 *                      Vertex(0)   Vertex(1)   Vertex(2)   ... Vertex(n-1)
 * cursor positions:  ^            ^            ^            ^                  ^
 * </PRE>
 * Note that the {@link #remove} and {@link #set(Object)} methods are
 * <i>not</i> defined in terms of the cursor position;  they are defined to
 * operate on the last vertex returned by a call to {@link #next} or
 * {@link #previous()}.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author  Thiago Reis
 * @see     Collection
 * @see     Graph
 * @see     Iterator
 * @see     Enumeration
 * @see     Graph#breadthFirstIterator()
 * @see     Graph#depthFirstIterator()
 * @since   1.0
 */
public interface GraphIterator<V> extends Iterator<V> {
    // Query Operations

    /**
     * Returns {@code true} if this graph iterator has more vertices when
     * traversing the graph in the forward direction. (In other words,
     * returns {@code true} if {@link #next} would return an vertex rather
     * than throwing an exception.)
     *
     * @return {@code true} if the graph iterator has more vertices when
     *         traversing the graph in the forward direction
     */
	@Override
    boolean hasNext();

    /**
     * Returns the next vertex in the graph and advances the cursor position.
     * This method may be called repeatedly to iterate through the graph,
     * or intermixed with calls to {@link #previous} to go back and forth.
     * (Note that alternating calls to {@code next} and {@code previous}
     * will return the same vertex repeatedly.)
     *
     * @return the next vertex in the graph
     * @throws NoSuchElementException if the iteration has no next vertex
     */
	@Override
    V next();

    /**
     * Returns {@code true} if the last vertex that was returned by
	 * {@link #next} is the root vertex in this iteration.
     *
     * @return {@code true} if the last vertex that was returned by
     *         {@link #next} is the root vertex in this iteration.
     */
    boolean isRoot();

    /**
     * Returns the level of the last vertex returned by {@link #next}.
     *
     * @return the level of the last vertex returned by {@link #next}
     */
    int level();

    /**
     * Returns the parent vertex of the last vertex that was returned by {@link #next}.
     *
     * @return the parent vertex of the last vertex returned by {@link #next}
     * @throws NoSuchElementException if the last vertex returned by{@link #next}
	 *         has no parent vertex (only for the root vertex of this iteration)
     */
    V parent();

    /*
     * Returns the child vertex of the last vertex returned by {@code next}.
	 * This method do not iterate through the graph.
     *
     * @return the child vertex of the last vertex returned by {@code next}
     * @throws NoSuchElementException if the last vertex returned by {@code next}
	 *         has no children vertex
     */
    //V child();

    /**
     * Returns {@code true} if this graph iterator has more vertices when
     * traversing the graph in the reverse direction.  (In other words,
     * returns {@code true} if {@link #previous} would return a vertex
     * rather than throwing an exception.)
     *
     * @return {@code true} if the graph iterator has more vertices when
     *         traversing the graph in the reverse direction
     */
    boolean hasPrevious();

    /**
     * Returns the previous vertex in the graph and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the graph backwards, or intermixed with calls to
     * {@link #next} to go back and forth.  (Note that alternating calls
     * to {@code next} and {@code previous} will return the same
     * vertex repeatedly.)
     *
     * @return the previous vertex in the graph
     * @throws NoSuchElementException if the iteration has no previous
     *         vertex
     */
    V previous();

    // Modification Operations

    /**
     * Removes from the graph the last vertex that was returned by {@link
     * #next} or {@link #previous} (optional operation).  This call can
     * only be made once per call to {@code next} or {@code previous}.
     * It can be made only if {@link #add} has not been
     * called after the last call to {@code next} or {@code previous}.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *         operation is not supported by this graph iterator
     * @throws IllegalStateException if neither {@code next} nor
     *         {@code previous} have been called, or {@code remove} or
     *         {@code add} have been called after the last call to
     *         {@code next} or {@code previous}
     */
	@Override
    void remove();

    /**
     * Replaces the last vertex returned by {@link #next} or
     * {@link #previous} with the specified vertex (optional operation).
     * This call can be made only if neither {@link #remove} nor {@link
     * #add} have been called after the last call to {@code next} or
     * {@code previous}.
     *
     * @param v the vertex with which to replace the last vertex returned by
     *          {@code next} or {@code previous}
     * @throws UnsupportedOperationException if the {@code set} operation
     *         is not supported by this graph iterator
     * @throws ClassCastException if the class of the specified vertex
     *         prevents it from being added to this graph
     * @throws IllegalArgumentException if some aspect of the specified
     *         vertex prevents it from being added to this graph
     * @throws IllegalStateException if neither {@code next} nor
     *         {@code previous} have been called, or {@code remove} or
     *         {@code add} have been called after the last call to
     *         {@code next} or {@code previous}
     */
    void set(V v);

    /**
     * Inserts the specified vertex into the graph (optional operation).
     * The vertex is inserted immediately before the vertex that
     * would be returned by {@link #next}, if any, and after the vertex
     * that would be returned by {@link #previous}, if any.  (If the
     * graph contains no vertices, the new vertex becomes the sole vertex
     * on the graph.)  The new vertex is inserted before the implicit
     * cursor: a subsequent call to {@code next} would be unaffected, and a
     * subsequent call to {@code previous} would return the new vertex.
     * (This call increases by one the value that would be returned by a
     * call to {@code nextIndex} or {@code previousIndex}.)
     *
     * @param v the vertex to insert
     * @throws UnsupportedOperationException if the {@code add} method is
     *         not supported by this graph iterator
     * @throws ClassCastException if the class of the specified vertex
     *         prevents it from being added to this graph
     * @throws IllegalArgumentException if some aspect of this vertex
     *         prevents it from being added to this graph
     */
    void add(V v);
}
