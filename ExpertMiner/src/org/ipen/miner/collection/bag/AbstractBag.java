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
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class provides a skeletal implementation of the <tt>Bag</tt>
 * interface to minimize the effort required to implement this
 * interface. <p>
 *
 * The process of implementing a bag by extending this class is identical
 * to that of implementing a Collection by extending AbstractCollection,
 * except that all of the methods and constructors in subclasses of this
 * class must obey the additional constraints imposed by the <tt>Bag</tt>
 * interface (for instance, the add method must not permit addition of
 * multiple instances of an object to a bag).<p>
 *
 * Note that this class does not override any of the implementations from
 * the <tt>AbstractCollection</tt> class.  It merely adds implementations
 * for <tt>equals</tt> and <tt>hashCode</tt>.<p>
 *
 * This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements maintained by this bag
 *
 * @author  Thiago Reis
 * @see Collection
 * @see AbstractCollection
 * @see Bag
 * @since 1.2
 */
public abstract class AbstractBag<E> extends AbstractCollection<E> implements Bag<E> {
	protected transient Map<E, Integer> map;

	/**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected AbstractBag() {
    }

    // Comparison and hashing

    /**
     * Compares the specified object with this bag for equality.  Returns
     * <tt>true</tt> if the given object is also a bag, the two bags have
     * the same size, and every member of the given bag is contained in
     * this bag.  This ensures that the <tt>equals</tt> method works
     * properly across different implementations of the <tt>Bag</tt>
     * interface.<p>
     *
     * This implementation first checks if the specified object is this
     * bag; if so it returns <tt>true</tt>.  Then, it checks if the
     * specified object is a bag whose size is identical to the size of
     * this bag; if not, it returns false.  If so, it returns
     * <tt>containsAll((Collection) o)</tt>.
     *
     * @param o object to be compared for equality with this bag
     * @return <tt>true</tt> if the specified object is equal to this bag
     */
	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Bag))
            return false;
        Collection c = (Collection) o;
        if (c.size() != size())
            return false;
        try {
            return containsAll(c);
        } catch (ClassCastException unused)   {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    /**
     * Returns the hash code value for this bag.  The hash code of a bag is
     * defined to be the sum of the hash codes of the elements in the bag,
     * where the hash code of a <tt>null</tt> element is defined to be zero.
     * This ensures that <tt>s1.equals(s2)</tt> implies that
     * <tt>s1.hashCode()==s2.hashCode()</tt> for any two bags <tt>s1</tt>
     * and <tt>s2</tt>, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * <p>This implementation iterates over the bag, calling the
     * <tt>hashCode</tt> method on each element in the bag, and adding up
     * the results.
     *
     * @return the hash code value for this bag
     * @see Object#equals(Object)
     * @see Bag#equals(Object)
     */
	@Override
    public int hashCode() {
        int h = 0;
        Iterator<E> i = iterator();
        while (i.hasNext()) {
            E obj = i.next();
            if (obj != null)
                h += obj.hashCode();
        }
        return h;
    }

    /**
     * Removes from this bag all of its elements that are contained in the
     * specified collection (optional operation).  If the specified
     * collection is also a bag, this operation effectively modifies this
     * bag so that its value is the <i>asymmetric bag difference</i> of
     * the two bags.
     *
     * <p>This implementation determines which is the smaller of this bag
     * and the specified collection, by invoking the <tt>size</tt>
     * method on each.  If this bag has fewer elements, then the
     * implementation iterates over this bag, checking each element
     * returned by the iterator in turn to see if it is contained in
     * the specified collection.  If it is so contained, it is removed
     * from this bag with the iterator's <tt>remove</tt> method.  If
     * the specified collection has fewer elements, then the
     * implementation iterates over the specified collection, removing
     * from this bag each element returned by the iterator, using this
     * bag's <tt>remove</tt> method.
     *
     * <p>Note that this implementation will throw an
     * <tt>UnsupportedOperationException</tt> if the iterator returned by the
     * <tt>iterator</tt> method does not implement the <tt>remove</tt> method.
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
     * (<a href="Collection.html#optional-restrictions">optional</a>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
	@Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;

		if (c instanceof Bag) {
			Bag bag = (Bag)c;
			Object element;
			int count;

			Iterator<?> iterator = bag.iterator();
			while (iterator.hasNext()) {
				element = iterator.next();
				count = bag.count(element);
				modified |= remove(element, count);
			}
		} else {
			for (Iterator<?> i = c.iterator(); i.hasNext();)
				modified |= remove(i.next());
		}

		/*
        if (size() > c.size()) {
            for (Iterator<?> i = c.iterator(); i.hasNext();)
                modified |= remove(i.next());
        } else {
            for (Iterator<?> i = iterator(); i.hasNext(); ) {
                if (c.contains(i.next())) {
                    i.remove();
                    modified = true;
                }
            }
        }
		*/

		return modified;
    }

	@Override
	public String toString() {
		return map.toString();
	}

	@Override
	public boolean add(E e, int occurrences) {
		return change(e, occurrences, true);
	}

	@Override
	public boolean remove(Object o, int occurrences) {
		return change(o, occurrences, false);
	}

	@Override
	public boolean removeAll(Object o) {
		return map.remove(o) != null;
	}

	@Override
	public boolean occurrences(E e, int occurrences) {
		if (occurrences < 1)
			throw new IllegalArgumentException();

		if (occurrences > Integer.MAX_VALUE) {
			return false;
		} else {
			map.put(e, occurrences);
			return true;
		}
	}

	@Override
	public int count(E e) {
		return map.get(e);
	}

	@Override
	public Set<E> elementSet() {
		return map.keySet();
	}

	@Override
	public Set<Bag.Entry> entrySet() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
		//return map.entrySet();
        //Set<Bag.Entry<E>> es = entrySet;
        //return es != null ? es : (entrySet = new EntrySet());
	}

	/**
	* An Entry maintaining a element and a occurrences.  The occurrences number may be
	* changed using the <tt>setOccurrences</tt> method.  This class
	* facilitates the process of building custom bag
	* implementations. For example, it may be convenient to return
	* arrays of <tt>BagEntry</tt> instances in method
	* <tt>Bag.entrySet().toArray</tt>.
	*
	* @since 1.6
	*/
	public static class BagEntry<E> implements Entry<E>, Serializable {
		private static final long serialVersionUID = -8499721149061103585L;

		private final E element;
		private int occurrences;

		/**
		* Creates an entry representing a mapping from the specified
		* element to the specified occurrences number.
		*
		* @param element the element represented by this entry
		* @param occurrences the occurrences number represented by this entry
		*/
		public BagEntry(E element, int occurrences) {
			this.element = element;
			this.occurrences = occurrences;
		}

		/**
		* Creates an entry representing the same mapping as the
		* specified entry.
		*
		* @param entry the entry to copy
		*/
		public BagEntry(Entry<? extends E> entry) {
			this.element = entry.getElement();
			this.occurrences = entry.getOccurrences();
		}

		/**
		* Returns the element corresponding to this entry.
		*
		* @return the element corresponding to this entry
		*/
		@Override
		public E getElement() {
			return element;
		}

		/**
		* Returns the occurrences corresponding to this entry.
		*
		* @return the occurrences corresponding to this entry
		*/
		@Override
		public int getOccurrences() {
			return occurrences;
		}

		/**
		* Replaces the occurrences corresponding to this entry with the specified
		* value.
		*
		* @param occurrences new occurrences number to be stored in this entry
		* @return the old occurrences number corresponding to the entry
		*/
		@Override
		public int setOccurrences(int occurrences) {
			int oldValue = this.occurrences;
			this.occurrences = occurrences;
			return oldValue;
		}

		/**
		* Compares the specified object with this entry for equality.
		* Returns {@code true} if the given object is also a bag entry and
		* the two entries represent the same mapping.  More formally, two
		* entries {@code e1} and {@code e2} represent the same mapping
		* if<pre>
		*   (e1.getElement()==null ?
		*    e2.getElement()==null :
		*    e1.getElement().equals(e2.getElement()))
		*   &amp;&amp;
		*   (e1.getOccurrences()==null ?
		*    e2.getOccurrences()==null :
		*    e1.getOccurrences().equals(e2.getOccurrences()))</pre>
		* This ensures that the {@code equals} method works properly across
		* different implementations of the {@code Bag.Entry} interface.
		*
		* @param o object to be compared for equality with this bag entry
		* @return {@code true} if the specified object is equal to this bag
		*         entry
		* @see    #hashCode
		*/
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Bag.Entry))
				return false;

			Bag.Entry e = (Bag.Entry)o;
			return element == e.getElement() && occurrences == e.getOccurrences();
		}

		/**
		* Returns the hash code value for this bag entry.  The hash code
		* of a bag entry {@code e} is defined to be: <pre>
		*   (e.getElement()==null   ? 0 : e.getElement().hashCode()) ^
		*   (e.getOccurrences()==null ? 0 : e.getOccurrences().hashCode())</pre>
		* This ensures that {@code e1.equals(e2)} implies that
		* {@code e1.hashCode()==e2.hashCode()} for any two Entries
		* {@code e1} and {@code e2}, as required by the general
		* contract of {@link Object#hashCode}.
		*
		* @return the hash code value for this bag entry
		* @see    #equals
		*/
		@Override
		public int hashCode() {
			return (element == null ? 0 : element.hashCode()) ^
				(new Integer(occurrences) == null ? 0 : new Integer(occurrences).hashCode());
		}

		/**
		* Returns a String representation of this bag entry.  This
		* implementation returns the string representation of this
		* entry's element followed by the equals character ("<tt>=</tt>")
		* followed by the string representation of this entry's occurrences number.
		*
		* @return a String representation of this bag entry
		*/
		@Override
		public String toString() {
			return element + "=" + occurrences;
		}
	}

	protected boolean change(Object o, int occurrences, boolean add) {
		if (occurrences < 1)
			throw new IllegalArgumentException();

		Integer count = map.get(o);
		if (count == null)
			count = 0;

		if (add) {
			if (count + occurrences > Integer.MAX_VALUE) {
				return false;
			} else {
				map.put((E)o, count + occurrences);
				return true;
			}
		} else {
			if (count == 0) {
				return false;
			} else if (count - occurrences < 1) {
				map.remove(o);
				return true;
			} else {
				map.put((E)o, count - occurrences);
				return true;
			}
		}
    }
}
