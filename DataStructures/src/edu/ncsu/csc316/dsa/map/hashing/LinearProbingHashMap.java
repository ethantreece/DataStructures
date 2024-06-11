package edu.ncsu.csc316.dsa.map.hashing;


/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

    private TableEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for (int i = 0; i < capacity(); i++) {
            if (table[i] != null && !table[i].isDeleted) {
                collection.add(table[i]);
            }
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    /**
     * Checks if the position at index is available
     * @param index position to check
     * @return true if available, false if not
     */
    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    @Override
    public V bucketGet(int hash, K key) {
        int j = findBucket(hash, key);
        if (j < 0) {
            return null;
        }
        return table[j].getValue();
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        int j = findBucket(hash, key);
        if (j >= 0) {
            V temp = table[j].getValue();
            table[j].setValue(value);
            return temp;
        }
        table[-(j + 1)] = new TableEntry<K, V>(key, value);
        size++;
        return null;
    }

    /**
     * Finds the bucket that contains the key we are looking for. If the key cannot be
     * found, -(a + 1) is returned such that the key should be added at the bucket with
     * index -(a + 1).
     * @param index index into the hash table
     * @param key key of the entry to locate
     * @return the index of where the bucket is located (if it exists), or if the entry
     *         is not in the map, return -(a + 1) where a is the index where the entry
     *         should be inserted
     */
    private int findBucket(int index, K key) {
        int avail = -1;
        int j = index;
        do {
            if (isAvailable(j)) {
                if (avail == -1) {
                    avail = j;
                }
                if (table[j] == null) {
                    return -(avail + 1);
                }
            } else if (table[j].getKey().equals(key)) {
                return j;
            }
            j = (j + 1) % table.length;
        } while (j != index);
        return -(avail + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) {
        int j = findBucket(hash, key);
        if (j < 0) {
            return null;
        }
        V answer = table[j].getValue();
        table[j].setDeleted(true);
        size--;
        return answer;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    /**
     * Stores the new element and a field to indicate whether the element
     * at the bucket has been deleted.
     * 
     * @author Dr. King
     * @author Ethan Treece
     *
     * @param <K> key
     * @param <V> value
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

        /** Keeps track if entry is deleted */
        private boolean isDeleted;

        /**
         * Constructs the table entry
         * @param key key
         * @param value value
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * Returns whether the table entry has been deleted
         * @return true if deleted, false if not
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /**
         * Sets whether the entry has been deleted
         * @param deleted status
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}