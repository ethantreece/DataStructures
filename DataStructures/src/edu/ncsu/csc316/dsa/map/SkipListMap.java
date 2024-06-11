package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    // Helper method to determine if an entry is one of the sentinel
    // -INFINITY or +INFINITY nodes (containing a null key)
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    /**
     * Helper method for get(), put(), and remove() methods to assist in
     * finding the location where a node exists (or should exist)
     * @param key key
     * @return the entry in the bottom of the list with the largest key such that
     *         key(p) <= k
     */
    private SkipListNode<K, V> lookUp(K key) {
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public V get(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        if (temp != null && temp.getEntry() != null && temp.getEntry().getKey().equals(key)) {
            return temp.getEntry().getValue();
        } else {
            return null;
        }
    }

    /**
     * Inserts a new node that stores entry e and points to the previous and down nodes
     * @param prev previous
     * @param down down
     * @param entry entry
     * @return new node
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
        // Create a new skip list node
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
        // Set the below and previous entries
        newNode.setBelow(down);
        newNode.setPrevious(prev);
        // Update the next and previous entry pointers
        if (prev != null) {
            newNode.setNext(prev.getNext());
            newNode.getPrevious().setNext(newNode);
        }
        if (newNode.getNext() != null) {
            newNode.getNext().setPrevious(newNode);
        }
        // Update the below entry pointers
        if (down != null) {
            down.setAbove(newNode);
        }
        return newNode;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public V put(K key, V value) {
        // Get the bottom-most entry immediately before the insertion location
        SkipListNode<K, V> temp = lookUp(key);
        // Entry with the key already exists in the map
        if (temp.getEntry() != null && temp.getEntry().getKey().equals(key)) {
            V originalValue = temp.getEntry().getValue();
            while (temp != null) {
                ((MapEntry) temp.getEntry()).setValue(value);
                temp = temp.getAbove();
            }
            return originalValue;
        }
        // Use q to represent the new entry as we move to the level "above" after inserting into the bottom-most list
        SkipListNode<K, V> q = null;
        // Keep track of the current level we are at
        int currentLevel = -1;
        do {
            currentLevel++;
            // Check if we need to add a new level to the top of the skip list
            if (currentLevel >= height)  {
                // Increase the height of the skip list
                height++;
                // Create a pointer to the current "tail" of the topmost list
                SkipListNode<K, V> tail = start.getNext();
                // Insert a new sentinel start node above
                start = insertAfterAbove(null, start, null);
                // Insert a new sentinel tail node above
                insertAfterAbove(start, tail, null);
            }
            // Insert the new entry into current level of the list
            q = insertAfterAbove(temp, q, new MapEntry<K, V>(key, value));
            // Backtrack to the entry immediately before the insertion location in the level "above"
            while (temp.getAbove() == null) {
                temp = temp.getPrevious();
            }
            temp = temp.getAbove();
        } while (coinToss.nextBoolean());
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        if (temp.getEntry() == null || !temp.getEntry().getKey().equals(key)) {
            return null;
        }
        V originalValue = temp.getEntry().getValue();
        while (temp != null) {
            temp.getPrevious().setNext(temp.getNext());
            temp.getNext().setPrevious(temp.getPrevious());
            temp = temp.getAbove();
        }
        size--;
        return originalValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
        }
        current = current.next;
        while (!isSentinel(current)) {
            set.add(current.getEntry());
            current = current.next;
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
//    public String toFullString() {
//        StringBuilder sb = new StringBuilder("SkipListMap[\n");
//        SkipListNode<K, V> cursor = start;
//        SkipListNode<K, V> firstInList = start;
//        while (cursor != null) {
//            firstInList = cursor;
//            sb.append("-INF -> ");
//            cursor = cursor.next;
//            while (cursor != null && !isSentinel(cursor)) {
//                sb.append(cursor.getEntry().getKey() + " -> ");
//                cursor = cursor.next;
//            }
//            sb.append("+INF\n");
//            cursor = firstInList.below;
//        }
//        sb.append("]");
//        return sb.toString();
//    }
    
    /**
     * SkipListNode creates a node for the skip list using an Entry.
     * 
     * @author Dr. King
     * @author Ethan Treece
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    private static class SkipListNode<K, V> {

        /** Current Entry */
        private Entry<K, V> entry;
        
        /** Node above the current one */
        private SkipListNode<K, V> above;
        
        /** Node below the current one */
        private SkipListNode<K, V> below;
        
        /** Node to the left of the current one */
        private SkipListNode<K, V> prev;
        
        /** Node to the right of the current one */
        private SkipListNode<K, V> next;

        /**
         * Constructs the SkipListNode
         * @param entry entry
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }

        /**
         * Gets the above node
         * @return above node
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }

        /**
         * Gets the entry of the current node
         * @return entry of the current node
         */
        public Entry<K, V> getEntry() {
            return entry;
        }

        /**
         * Gets the next node
         * @return next node
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }

        /**
         * Gets the previous node
         * @return previous node
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }

        /**
         * Sets the above node
         * @param up up
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }

        /**
         * Sets the below node
         * @param down down
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }

        /**
         * Sets the entry of the current node
         * @param entry
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        /**
         * Sets the next node
         * @param next next
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }

        /**
         * Sets the previous node
         * @param prev prev
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
    
}
