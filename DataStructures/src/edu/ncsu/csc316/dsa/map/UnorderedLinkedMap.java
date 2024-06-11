package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {

    /** PositionalList of Map entries */
    private PositionalList<Entry<K, V>> list;
    
    /**
     * Constructs the UnorderedLinkedMap
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * Looks up the position of the entry in the map containing
     * the parameterized key
     * @param key key
     * @return position of the entry containing the key
     */
    private Position<Entry<K, V>> lookUp(K key)
    {
        Position<Entry<K, V>> p = list.first();
        int s = list.size();
        while (s > 0) {
            if (p.getElement().getKey().equals(key)) {
                return p;
            }
            p = list.after(p);
            s--;
        }
        return null;
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if (p != null) {
            V temp = p.getElement().getValue();
            moveToFront(p);
            return temp;
        } else {
            return null;
        }
    }
    
    /**
     * A move-to-front heuristic approach used whenever the value associated with
     * a key is retrieved
     * @param position position
     */
    private void moveToFront(Position<Entry<K, V>> position) {
        if (position != null) {
            list.remove(position);
            list.addFirst(position.getElement());
        }
    }

    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        MapEntry<K, V> e = new MapEntry<K, V>(key, value);
        if (p != null && p.getElement() != null) {
            V temp = p.getElement().getValue();
            list.addAfter(p, e);
            moveToFront(list.after(p));
            list.remove(p);
            return temp;
        } else {
            list.addLast(e);
            moveToFront(list.last());
            return null;
        }
    }
    
    @Override
    public V remove(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if (p != null) {
            return list.remove(p).getValue();
        } else {
            return null;
        }
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
