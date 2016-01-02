<pre>
public interface Bag<E> extends Collection<E>, Iterable<E><br>
//bag interface<br>
int cardinality();<br>
int frequency(Object object);<br>
boolean contains(Object object, int min, int max);<br>
*	Set<E> toSet(Supplier<Set<E>> factory);<br>
*	Map<E, Integer> toMap(Supplier<Map<E, Integer>> factory);<br>
<br>
*	Iterator<E> iterator(boolean unique);<br>
*	BagIterator<E> bagIterator();<br>
*	BagIterator<E> bagIterator(boolean unique);<br>
*	Stream<E> stream(boolean unique);<br>
*	Stream<E> parallelStream(boolean unique);<br>
<br>
int add(E element, int occurrences);<br>
int remove(Object object, int occurrences);<br>
int put(E element, int occurrences);<br>
int set(E element, int frequency);<br>
int place(Object object, int frequency);<br>
int erase(Object object);<br>
*	int eraseIf(Predicate<? super E> filter);<br>
<br>
//collection interface<br>
int size();															HashSet/RegularEnumSet/JumboEnumSet/TreeSet<br>
boolean isEmpty();													HashSet/RegularEnumSet/JumboEnumSet/TreeSet<br>
boolean contains(Object object);									AbstractCollection/HashSet/RegularEnumSet/JumboEnumSet/TreeSet<br>
Object[] toArray();													AbstractCollection<br>
<T> T[] toArray(T[] array);											AbstractCollection<br>
<br>
*	Stream<E> stream();													Collection<br>
*	Stream<E> parallelStream();											Collection<br>
<br>
boolean add(E element);												AbstractCollection/HashSet/RegularEnumSet/JumboEnumSet/TreeSet<br>
boolean remove(Object object);										AbstractCollection/HashSet/RegularEnumSet/JumboEnumSet/TreeSet<br>
<br>
boolean containsAll(Collection<?> collection);						AbstractCollection/RegularEnumSet/JumboEnumSet<br>
boolean addAll(Collection<? extends E> collection);					AbstractCollection/RegularEnumSet/JumboEnumSet/TreeSet<br>
boolean removeAll(Collection<?> collection);						AbstractCollection/AbstractSet/RegularEnumSet/JumboEnumSet<br>
boolean retainAll(Collection<?> collection);						AbstractCollection/RegularEnumSet/JumboEnumSet<br>
*	boolean removeIf(Predicate<? super E> filter);						Collection<br>
void clear();														AbstractCollection/HashSet/RegularEnumSet/JumboEnumSet/TreeSet<br>
<br>
//iterable interface<br>
Iterator<E> iterator();												HashSet/RegularEnumSet/JumboEnumSet/TreeSet<br>
*	Spliterator<T> spliterator();										Iterable/Collection/Set/HashSet/LinkedHashSet/SortedSet/TreeSet<br>
*	void forEach(Consumer<? super T> action);							Iterable<br>
<br>
//object interface<br>
int hashCode();														AbstractSet<br>
boolean equals(Object object);										AbstractSet/RegularEnumSet/JumboEnumSet<br>
String toString();													AbstractCollection<br>
Objetct clone();													HashSet/EnumSet/JumboEnumSet/TreeSet<br>
<br>
public interface BagIterator<E> extends Iterator<E><br>
//bag iterator behaviour<br>
int occurrence();<br>
int frequency();<br>
//int set(int frequency);<br>
//int delete();<br>
<br>
//iterator behaviour<br>
boolean hasNext();<br>
E next();<br>
void remove();<br>
<br>
protected static final class Frequency implements Serializable<br>
public Frequency(int frequency)<br>
public int get()<br>
public int set(int frequency)<br>
<br>
//object interface<br>
public int hashCode()<br>
public boolean equals(Object object)<br>
public String toString()<br>
<br>
//#####################################################################################################<br>
<br>
Objects<br>
Collections<br>
Bags<br>
<br>
public class Object<br>
public interface Iterable<T><br>
public interface Collection<E> extends Iterable<E><br>
public abstract class AbstractCollection<E> implements Collection<E><br>
<br>
public interface Set<E> extends Collection<E><br>
public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E><br>
public class HashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable<br>
public class LinkedHashSet<E> extends HashSet<E> implements Set<E>, Cloneable, Serializable<br>
public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E> implements Cloneable, Serializable<br>
class RegularEnumSet<E extends Enum<E>> extends EnumSet<E><br>
class JumboEnumSet<E extends Enum<E>> extends EnumSet<E><br>
public interface SortedSet<E> extends Set<E><br>
public interface NavigableSet<E> extends SortedSet<E><br>
public class TreeSet<E> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, Serializable<br>
<br>
public class IdentityHashSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable<br>
public class WeakHashSet<E> extends AbstractSet<E> implements Set<E><br>
<br>
public interface Bag<E> extends Collection<E><br>
public abstract class AbstractBag<E> extends AbstractCollection<E> implements Bag<E><br>
public class HashBag<E> extends AbstractBag<E> implements Bag<E>, Cloneable, Serializable<br>
public class LinkedHashBag<E> extends HashBag<E> implements Bag<E>, Cloneable, Serializable<br>
public abstract class EnumBag<E extends Enum<E>> extends AbstractBag<E> implements Cloneable, Serializable<br>
class RegularEnumBag<E extends Enum<E>> extends EnumBag<E><br>
class JumboEnumBag<E extends Enum<E>> extends EnumBag<E><br>
public class IdentityHashBag<E> extends AbstractBag<E> implements Bag<E>, Serializable, Cloneable<br>
public class WeakHashBag<E> extends AbstractBag<E> implements Bag<E><br>
public interface SortedBag<E> extends Bag<E><br>
public interface NavigableBag<E> extends SortedBag<E><br>
public class TreeBag<E> extends AbstractBag<E> implements NavigableBag<E>, Cloneable, Serializable<br>
<br>
public interface Map<K,V><br>
public abstract class AbstractMap<K,V> implements Map<K,V><br>
public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable<br>
public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V><br>
public class EnumMap<K extends Enum<K>, V> extends AbstractMap<K, V> implements Serializable, Cloneable<br>
public class IdentityHashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Serializable, Cloneable<br>
public class WeakHashMap<K,V> extends AbstractMap<K,V> implements Map<K,V><br>
public interface SortedMap<K,V> extends Map<K,V><br>
public interface NavigableMap<K,V> extends SortedMap<K,V><br>
public class TreeMap<K,V> extends AbstractMap<K,V> implements NavigableMap<K,V>, Cloneable, Serializable<br>
<br>
//#####################################################################################################<br>
<br>
http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/<br>
http://commons.apache.org/proper/commons-collections/javadocs/api-release/index.html<br>
http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/index.html<br>
http://www.jroller.com/rajasekar/entry/java_fail_fast_iterators_exposed<br>
<br>
sobrecarga de metodos para operacoes distinct<br>
criar iterator implementation fail-fast<br>
criar bulk operations com distinct<br>
criar Bag.Entry<E> para stream<br>
tirar modify, concentrar em put/set<br>
<br>
Entry(E element)<br>
get()<br>
add()<br>
remove()<br>
put(E element, int occurrences)<br>
set(E element, int frequency)<br>
putIfFound(Object object, int occurrences)<br>
setIfFound(Object object, int frequency)<br>
frequency()<br>
<br>
public interface Bag<E> implements Collection<E>, Map<E, Integer><br>
<br>
int remove(Object object, boolean distinct);<br>
boolean containsAll(Collection<?> collection, boolean distinct);<br>
boolean addAll(Collection<? extends E> collection, boolean distinct);<br>
boolean removeAll(Collection<?> collection, boolean distinct);<br>
boolean retainAll(Collection<?> collection, boolean distinct);<br>
boolean removeIf(Predicate<? super E> filter, boolean distinct);<br>
int addIf(Predicate<? super E> filter, int occurrences);<br>
int setIf(Predicate<? super E> filter, int occurrences);<br>
<br>
Iterator<E> iterator(boolean distinct);<br>
BagIterator<E> bagIterator(boolean distinct);<br>
Stream<E> stream(boolean distinct);<br>
Stream<E> parallelStream(boolean distinct);<br>
<br>
Iterator<Bag.Entry<E>> entryIterator();<br>
Spliterator<Bag.Entry<E>> entrySpliterator();<br>
Stream<Bag.Entry<E>> entryStream(boolean unique);<br>
Stream<Bag.Entry<E>> entryParallelStream(boolean unique);<br>
<br>
Set<E> toView(Supplier<Set<E>> factory);																Returns a Set view of the keys contained in this map.<br>
Set<Bag.Entry<E>> toView(Supplier<Set<Bag.Entry<E>>> factory);										Returns a Set view of the mappings contained in this map.<br>
<br>
<T> Set<T> toView();<br>
<br>
boolean contains(Predicate<? super E> filter);<br>
boolean contains(Predicate<? super Bag.Entry<E>> filter);<br>
</pre>

<pre>
Interface;Return;;Method;Description<br>
Collection;boolean;;add(E e);Ensures that this collection contains the specified element (optional operation).<br>
Collection;boolean;;addAll(Collection<? extends E> c);Adds all of the elements in the specified collection to this collection (optional operation).<br>
Collection;void;;clear();Removes all of the elements from this collection (optional operation).<br>
Collection;boolean;;contains(Object o);Returns true if this collection contains the specified element.<br>
Collection;boolean;;containsAll(Collection<?> c);Returns true if this collection contains all of the elements in the specified collection.<br>
Collection;boolean;;equals(Object o);Compares the specified object with this collection for equality.<br>
Collection;int;;hashCode();Returns the hash code value for this collection.<br>
Collection;boolean;;isEmpty();Returns true if this collection contains no elements.<br>
Collection;Iterator<E>;;iterator();Returns an iterator over the elements in this collection.<br>
Collection;default Stream<E>;;parallelStream();Returns a possibly parallel Stream with this collection as its source.<br>
Collection;boolean;;remove(Object o);Removes a single instance of the specified element from this collection, if it is present (optional operation).<br>
Collection;boolean;;removeAll(Collection<?> c);Removes all of this collection's elements that are also contained in the specified collection (optional operation).<br>
Collection;default boolean;;removeIf(Predicate<? super E> filter);Removes all of the elements of this collection that satisfy the given predicate.<br>
Collection;boolean;;retainAll(Collection<?> c);Retains only the elements in this collection that are contained in the specified collection (optional operation).<br>
Collection;int;;size();Returns the number of elements in this collection.<br>
Collection;default Spliterator<E>;;spliterator();Creates a Spliterator over the elements in this collection.<br>
Collection;default Stream<E>;;stream();Returns a sequential Stream with this collection as its source.<br>
Collection;Object[];;toArray();Returns an array containing all of the elements in this collection.<br>
Collection;<T> T[];;toArray(T[] a);"Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array."<br>
Map;void;;clear();Removes all of the mappings from this map (optional operation).<br>
Map;default V;;compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction);Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).<br>
Map;default V;;computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction);If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless null.<br>
Map;default V;;computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction);If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.<br>
Map;boolean;;containsKey(Object key);Returns true if this map contains a mapping for the specified key.<br>
Map;boolean;;containsValue(Object value);Returns true if this map maps one or more keys to the specified value.<br>
Map;Set<Map.Entry<K,V>>;toView();entrySet();Returns a Set view of the mappings contained in this map.<br>
Map;boolean;;equals(Object o);Compares the specified object with this map for equality.<br>
Map;default void;forEach(BiConsumer<? super E,Integer> action);forEach(BiConsumer<? super K,? super V> action);Performs the given action for each entry in this map until all entries have been processed or the action throws an exception.<br>
Map;V;frequency(Object o);get(Object key);Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.<br>
Map;default V;;getOrDefault(Object key, V defaultValue);Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.<br>
Map;int;;hashCode();Returns the hash code value for this map.<br>
Map;boolean;;isEmpty();Returns true if this map contains no key-value mappings.<br>
Map;Set<K>;toView();keySet();Returns a Set view of the keys contained in this map.<br>
Map;default V;;merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction);If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value.<br>
Map;V;;put(K key, V value);Associates the specified value with the specified key in this map (optional operation).<br>
Map;void;;putAll(Map<? extends K,? extends V> m);Copies all of the mappings from the specified map to this map (optional operation).<br>
Map;default V;;putIfAbsent(K key, V value);If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.<br>
Map;V;;remove(Object key);Removes the mapping for a key from this map if it is present (optional operation).<br>
Map;default boolean;;remove(Object key, Object value);Removes the entry for the specified key only if it is currently mapped to the specified value.<br>
Map;default boolean;;replace(K key, V oldValue, V newValue);Replaces the entry for the specified key only if currently mapped to the specified value.<br>
Map;default V;;replace(K key, V value);Replaces the entry for the specified key only if it is currently mapped to some value.<br>
Map;default void;;replaceAll(BiFunction<? super K,? super V,? extends V> function);Replaces each entry's value with the result of invoking the given function on that entry until all entries have been processed or the function throws an exception.<br>
Map;int;;size();Returns the number of key-value mappings in this map.<br>
Map;Collection<V>;;values();Returns a Collection view of the values contained in this map.<br>
</pre>