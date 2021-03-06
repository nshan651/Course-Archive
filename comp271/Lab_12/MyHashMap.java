package Lab_12;

import java.util.*;

/**
 * A generic HashMap custom implementation using chaining. Concretely, the table is an ArrayList of
 * chains represented as LinkedLists.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class MyHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_TABLE_SIZE = 11; // a prime

    private List<List<Entry<K, V>>> table;

    public MyHashMap() {
        this(DEFAULT_TABLE_SIZE);
    }

    public MyHashMap(final int tableSize) {
        // allocate a table of the given size
        table = new ArrayList<>(tableSize);
        // then create an empty chain at each position
        for (var i = 0; i < tableSize; i += 1) {
            table.add(new LinkedList<>());
        }
    }

    @Override
    public int size() {
        int result = 0;
        for (var i=0; i < table.size(); i++) {
            result += table.get(i).size();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(final Object key) {
        final int index = calculateIndex(key);
        final var iter = table.get(index).iterator();
        while (iter.hasNext()) {
            final var entry = iter.next();
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(final Object value) {
        final var iter = values().iterator();
        while (iter.hasNext()) {
            final var entry = iter.next();
            if (entry.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(final Object key) {
        final int index = calculateIndex(key);
        final var iter = table.get(index).iterator();
        while (iter.hasNext()) {
            final var entry = iter.next();
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(final K key, final V value) {
        final int index = calculateIndex(key);
        final var iter = table.get(index).iterator();
        while (iter.hasNext()) {
            final var entry = iter.next();
            if (entry.getKey().equals(key)) {
                final var oldValue = entry.getValue();
                iter.remove();
                table.get(index).add(Map.entry(key,value));
                return oldValue;
            }
        }
        table.get(index).add(Map.entry(key,value));
        return null;
    }

    @Override
    public V remove(final Object key) {
        final int index = calculateIndex(key);
        final var iter = table.get(index).iterator();
        while (iter.hasNext()) {
            final var entry = iter.next();
            if (entry.getKey().equals(key)) {
                final var oldValue = entry.getValue();
                iter.remove();
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> m) {
        /* add each entry in m's entrySet */
        final var iter = m.entrySet().iterator();
        while (iter.hasNext()) {
            final var entry =iter.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        /* clear each chain */
        final var iter = table.iterator();
        while (iter.hasNext()) {
            iter.next().clear();
        }
    }

    /** The resulting keySet is not "backed" by the Map, so we keep it unmodifiable. */
    @Override
    public Set<K> keySet() {
        final Set<K> result = new HashSet<>();
        final var iter = table.iterator();
        while (iter.hasNext()) {
            var iterTemp = iter.next().iterator();
            while (iterTemp.hasNext()) {
                var entry = iterTemp.next();
                result.add(entry.getKey());
            }
        }
        return Collections.unmodifiableSet(result);
    }

    /** The resulting values collection is not "backed" by the Map, so we keep it unmodifiable. */
    @Override
    public Collection<V> values() {
        final List<V> result = new LinkedList<>();
        final var iter = table.iterator();
        while (iter.hasNext()) {
            var iterTemp = iter.next().iterator();
            while (iterTemp.hasNext()) {
                var entry = iterTemp.next();
                result.add(entry.getValue());
            }
        }
        return Collections.unmodifiableCollection(result);
    }

    /** The resulting entrySet is not "backed" by the Map, so we keep it unmodifiable. */
    @Override
    public Set<Entry<K, V>> entrySet() {
        final Set<Entry<K, V>> result = new HashSet<>();
        final var iter = table.iterator();
        while (iter.hasNext()) {
            var iterTemp = iter.next().iterator();
            while (iterTemp.hasNext()) {
                var entry = iterTemp.next();
                result.add(entry);
            }
        }
        return Collections.unmodifiableSet(result);
    }

    @Override
    public String toString() {
        return entrySet().toString();
    }

    public boolean equals(final Object that) {
        if (this == that) {
            return true;
        } else if (!(that instanceof Map)) {
            return false;
        } else {
            return entrySet().equals(((Map)that).entrySet());
        }
    }

    private int calculateIndex(final Object key) {
        // positive remainder (as opposed to %)
        // required in case hashCode is negative!
        return Math.floorMod(key.hashCode(), table.size());
    }

}