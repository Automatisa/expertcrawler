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

package org.ipen.miner.collection.graph;

import java.util.*;

/**
 * This class provides a skeletal implementation of the <tt>Digraph</tt>
 * interface to minimize the effort required to implement this
 * interface. <p>
 *
 * The process of implementing a graph by extending this class is identical
 * to that of implementing a Collection by extending AbstractCollection,
 * except that all of the methods and constructors in subclasses of this
 * class must obey the additional constraints imposed by the <tt>Digraph</tt>
 * interface (for instance, the add method must not permit addition of
 * multiple instances of an object to a graph).<p>
 *
 * Note that this class does not override any of the implementations from
 * the <tt>AbstractCollection</tt> class.  It merely adds implementations
 * for <tt>equals</tt> and <tt>hashCode</tt>.<p>
 *
 * This class is a member of the
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
public abstract class AbstractDigraph<V> extends AbstractGraph<V> implements Digraph<V> {
	/**
	 * Sole constructor.  (For invocation by subclass constructors, typically
	 * implicit.)
	 */
	protected AbstractDigraph() {
	}

	// Query Operations

	@Override
	public int degree(V v) {
		if (!map.containsKey(v))
			throw new NoSuchElementException();

		int inDegree=0, outDegree=map.get(v).size();

		Iterator<HashMap<V, Object>> predecessors = map.values().iterator();
		while (predecessors.hasNext()) {
			if (predecessors.next().containsKey(v)) inDegree++;
		}
		return outDegree + inDegree;
	}

	@Override
	public int edgeCount() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	// Bulk Operations

	@Override
	public Set<V> adjacents(V v) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean link(V source, V target) {
		if (!map.containsKey(source) || !map.containsKey(target)) return false;
		modCount++;
		return map.get(source).put(target, PRESENT)==null;
	}

	@Override
	public boolean unlink(V source, V target) {
		if (!map.containsKey(source) || !map.containsKey(target)) return false;
		modCount++;
		return map.get(source).remove(target)==PRESENT;
	}

	@Override
	public boolean isSuccessor(V source, V target) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPredecessor(V source, V target) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int inDegree(V v) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int outDegree(V v) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<V> successors(V v) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<V> predecessors(V v) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
