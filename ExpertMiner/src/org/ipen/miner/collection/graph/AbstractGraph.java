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
Class AbstractGraph<V> - model Class AbstractSet/HashSet
	All Implemented Interfaces:
		Iterable<E>, Collection<E>, Set<E>
	Direct Known Subclasses:

	Constructor Detail:
		protected AbstractGraph()
	Method Detail:
		public boolean equals(Object o)
		public int hashCode()
		public boolean removeAll(Collection<?> c)
*/

package org.ipen.miner.collection.graph;

import java.util.*;

/**
 * This class provides a skeletal implementation of the <tt>Graph</tt>
 * interface to minimize the effort required to implement this
 * interface. <p>
 *
 * The process of implementing a graph by extending this class is identical
 * to that of implementing a Collection by extending AbstractCollection,
 * except that all of the methods and constructors in subclasses of this
 * class must obey the additional constraints imposed by the <tt>Graph</tt>
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
public abstract class AbstractGraph<V> extends AbstractSet<V> implements Graph<V> {

	/**
	 * The number of times this graph has been <i>structurally modified</i>.
	 * Structural modifications are those that change the size of the
	 * graph, or otherwise perturb it in such a fashion that iterations in
	 * progress may yield incorrect results.
	 *
	 * <p>This field is used by the iterator, breadth-first iterator
	 * and depth-first implementation
	 * returned by the {@code iterator}, {@code breadthFirstIterator}
	 * and {@code depthFirstIterator}methods.
	 * If the value of this field changes unexpectedly, the iterators
	 * will throw a {@code ConcurrentModificationException} in
	 * response to the {@code next} or {@code remove}
	 * operations.  This provides
	 * <i>fail-fast</i> behavior, rather than non-deterministic behavior in
	 * the face of concurrent modification during iteration.
	 *
	 * <p><b>Use of this field by subclasses is optional.</b> If a subclass
	 * wishes to provide fail-fast iterators (and list iterators), then it
	 * merely has to increment this field in its {@code remove(int)} method
	 * (and any other methods that it overrides
	 * that result in structural modifications to the list).  A single call to
	 * {@code remove(int)} must add no more than
	 * one to this field, or the iterators will throw
	 * bogus {@code ConcurrentModificationExceptions}.  If an implementation
	 * does not wish to provide fail-fast iterators, this field may be
	 * ignored.
	 */
	protected transient int modCount = 0;

	//private transient HashMap<E,Object> map;
    //public transient Map<V, Map<V, Object>> map;
    protected transient HashMap<V, HashMap<V, Object>> map;

    // Dummy value to associate with an Object in the backing Map
    protected static final Object PRESENT = new Object();

	/**
	 * Sole constructor.  (For invocation by subclass constructors, typically
	 * implicit.)
	 */
	protected AbstractGraph() {
	}

	// Query Operations

	@Override
    public int size() {
        return map.size();
    }

	@Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

	@Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

	@Override
	public int edgeCount() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int degree(V v) {
		if (!map.containsKey(v))
			throw new NoSuchElementException();

		return map.get(v).size();
	}

	@Override
	public boolean isAdjacent(V source, V target) {
		if (!map.containsKey(source) || !map.containsKey(target))
			throw new NoSuchElementException();

		return map.get(source).containsKey(target) && map.get(target).containsKey(source);
	}

    // Comparison and hashing

	@Override
    public boolean equals(Object o) {
		return super.equals(o);
    }

	@Override
    public int hashCode() {
		return super.hashCode();
    }

	// Bulk Operations
	@Override
    public boolean removeAll(Collection<?> c) {
		//return super.removeAll(c);
		boolean modified = false;
		Iterator<?> vertices = c.iterator();
		while (vertices.hasNext()) {
			modified = removeVertex(vertices.next());
		}
		modCount++;
		return modified;
    }

	@Override
    public void clear() {
        map.clear();
		modCount++;
    }

    /**
     * Returns a shallow copy of this <tt>HashGraph</tt> instance: the vertices
     * themselves are not cloned.
     *
     * @return a shallow copy of this graph
     */
	@Override
    public Object clone() {
        try {
            HashGraph<V> newSet = (HashGraph<V>) super.clone();
			//newSet.map = (HashMap<V, Object>) map.clone();
            newSet.map = (HashMap<V, HashMap<V, Object>>) map.clone();
            return newSet;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

	@Override
	public Set<V> adjacents(V v) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	// Modification Operations

	@Override
	public boolean add(V v) {
		//return map.put(v, PRESENT)==null;
		checkNullVertex(v);
		modCount++;
		return concreteAdd(v);
    }

	protected abstract boolean concreteAdd(V v);

	@Override
    public boolean remove(Object o) {
		//return map.remove(o)==PRESENT;
		modCount++;
		return removeVertex(o);
    }

	@Override
	public boolean link(V source, V target) {
		if (!map.containsKey(source) || !map.containsKey(target)) return false;
		modCount++;
		return map.get(source).put(target, PRESENT)==null && map.get(target).put(source, PRESENT)==null;
	}

	@Override
	public boolean unlink(V source, V target) {
		if (!map.containsKey(source) || !map.containsKey(target)) return false;
		modCount++;
		return map.get(source).remove(target)==PRESENT && map.get(target).remove(source)==PRESENT;
	}

    /*
     * Save the state of this <tt>HashGraph</tt> instance to a stream (that is,
     * serialize it).
     *
     * @serialData The capacity of the backing <tt>HashMap</tt> instance
     *             (int), and its load factor (float) are emitted, followed by
     *             the size of the graph (the number of vertices it contains)
     *             (int), followed by all of its vertices (each an Object) in
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

        // Write out all vertices in the proper order.
        for (V v : map.keySet())
            s.writeObject(v);
    }
*/
    /*
     * Reconstitute the <tt>HashGraph</tt> instance from a stream (that is,
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
        map = (((HashGraph)this) instanceof LinkedHashGraph ?
               new LinkedHashMap<V,Object>(capacity, loadFactor) :
               new HashMap<V,Object>(capacity, loadFactor));

        // Read in size
        int size = s.readInt();

        // Read in all vertices in the proper order.
        for (int i=0; i<size; i++) {
            V v = (V) s.readObject();
            map.put(v, PRESENT);
        }
    }
*/
/*
	protected class Vertex<V> {
		public boolean link(V target) {
			return false;
		}

		public boolean unlink(V source, V target) {
			;
		}
		unlinkAll
	}
*/
	//Iterators

	@Override
    public Iterator<V> iterator() {
        return map.keySet().iterator();
    }

	@Override
	public GraphIterator<V> breadthFirstIterator(V v) {
		return new GraphSearchIterator(v, new QueueIteratorFrontier());
	}

	@Override
	public GraphIterator<V> depthFirstIterator(V v) {
		return new GraphSearchIterator(v, new StackIteratorFrontier());
	}

	protected class GraphSearchIterator implements GraphIterator<V> {
		private FrontierElement<V> next;
		//private IteratorFrontier<V> frontier;
		private IteratorFrontier frontier;
		private HashSet<V> visited;
		private int level = 0;
		private int expectedModCount = modCount;

		GraphSearchIterator(V v, IteratorFrontier frontier) {
			if (!map.containsKey(v))
				throw new NoSuchElementException();

			visited = new HashSet<>();
			this.frontier = frontier;
			//this.frontier.addNext(v);
			this.frontier.addNext(new FrontierElement<>(v, null, level));
		}

		@Override
		public boolean hasNext() {
			return frontier.size() > 0;
		}

		@Override
		public V next() {
			checkForComodification();
			if (!hasNext())
				throw new NoSuchElementException();

			next = frontier.removeCurrent();
			visited.add(next.vertex());

			Iterator<V> targets = map.get(next.vertex()).keySet().iterator();
			if (targets.hasNext())
				level++;
			while (targets.hasNext()) {
				V currentTarget = targets.next();
				if (!visited.contains(currentTarget))
					//frontier.addNext(currentTarget);
					frontier.addNext(new FrontierElement<>(currentTarget, next.vertex(), level));
			}

			return next.vertex();
		}

		@Override
		public boolean isRoot() {
			return next.level() == 0;
		}

		@Override
		public int level() {
			return next.level();
		}

		@Override
		public V parent() {
			if (next.parent() == null)
				throw new NoSuchElementException();

			return next.parent();
		}

		@Override
		public void remove() {
			checkForComodification();
			if (next.vertex() == null)
				throw new IllegalStateException();
			AbstractGraph.this.remove(next.vertex());
			expectedModCount++;
		}

		@Override
		public boolean hasPrevious() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public V previous() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(V v) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(V v) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		final void checkForComodification() {
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
		}
	}

	protected final class FrontierElement<V> {
		private V vertex;
		private V parent;
		private int level;

		protected FrontierElement(V vertex, V parent, int level) {
			this.vertex = vertex;
			this.parent = parent;
			this.level = level;
		}

		public V vertex() {
			return vertex;
		}

		public V parent() {
			return parent;
		}

		public int level() {
			return level;
		}
	}

	protected abstract class IteratorFrontier extends LinkedList {
		abstract void addNext(FrontierElement<V> e);
		abstract FrontierElement<V> removeCurrent();
	}

	//queue - FIFO, offer - inserts at the end, poll - removes and returns at the begining
	protected final class QueueIteratorFrontier extends IteratorFrontier {
		@Override
		public void addNext(FrontierElement<V> e) {
			offer(e);
		}

		@Override
		public FrontierElement<V> removeCurrent() {
			return (FrontierElement<V>)poll();
		}
	}

	//stack - LIFO, push - inserts at the begining, pop - removes and returns at the begining
	protected final class StackIteratorFrontier extends IteratorFrontier {
		@Override
		public void addNext(FrontierElement<V> e) {
			push(e);
		}

		@Override
		public FrontierElement<V> removeCurrent() {
			return (FrontierElement<V>)pop();
		}
	}

	final protected boolean removeVertex(Object o) {
		//remove todas as referencias para o vertice
		Iterator<? extends Map<V, Object>> vertices = map.values().iterator();
		while (vertices.hasNext()) {
			vertices.next().remove(o);
		}
		//remove o vertice
        return map.remove(o)==null;
	}

	/**
     * Verifies if the specified vertex is null (if true, this implementation
	 * will throw an <tt>IllegalArgumentException</tt>).
     *
     * <p>This method is declared as <tt>protected</tt> to ensure and prevent
	 * the derived classes from redefining its behavior incorrectly.
	 *
     * @param  v vertex to be added to this graph
     * @throws IllegalArgumentException if the <tt>vertex</tt> is null
     */
	final protected void checkNullVertex(V v) {
		if (v == null)
			throw new IllegalArgumentException();
	}

    /**
     * Verifies if the specified collection contains a null vertex (if true,
	 * this implementation will throw an <tt>IllegalArgumentException</tt>).
     *
     * <p>This method is declared as <tt>protected</tt> to ensure and prevent
	 * the derived classes from redefining its behavior incorrectly.
	 *
     * @param  c collection to be added to this graph
     * @throws IllegalArgumentException if the <tt>collection</tt> contains a
	 * null vertex
     */
	final protected void checkNullVertex(Collection<? extends V> c) {
		for (V v : c) if (v == null)
			throw new IllegalArgumentException();
	}
}
