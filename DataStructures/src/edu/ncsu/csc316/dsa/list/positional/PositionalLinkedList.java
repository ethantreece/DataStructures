package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
    public Position<E> addAfter(Position<E> p, E element) {
        PositionalNode<E> node = validate(p);
        return addBetween(element, node.getNext(), node);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E element) {
        PositionalNode<E> node = validate(p);
        return addBetween(element, node, node.getPrevious());
    }

    @Override
    public Position<E> addFirst(E element) {
        return addBetween(element, front.getNext(), front);
    }

    @Override
    public Position<E> addLast(E element) {
        return addBetween(element, tail, tail.getPrevious());
    }

    @Override
    public Position<E> after(Position<E> p) {
        if (validate(p).getNext() == tail) {
            return null;
        }
        return validate(p).getNext();
    }

    @Override
    public Position<E> before(Position<E> p) {
        if (validate(p).getPrevious() == front) {
            return null;
        }
        return validate(p).getPrevious();
    }

    @Override
    public Position<E> first() {
        if (size == 0) {
            return null;
        }
        return front.getNext();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> last() {
        if (size == 0) {
            return null;
        }
        return tail.getPrevious();
    }

    @Override
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }
    
    /**
     * PositionIterable is a wrapper class to help adapt
     * the existing PositionIterator so that an Iterable
     * object can be returned
     * @author Ethan Treece
     *
     */
    private class PositionIterable implements Iterable<Position<E>> {
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    @Override
    public E remove(Position<E> p) {
        if (p == null) {
            return null;
        }
        PositionalNode<E> node = validate(p);
        PositionalNode<E> prev = node.getPrevious();
        PositionalNode<E> next = node.getNext();
        prev.setNext(next);
        next.setPrevious(prev);
        size--;
        return node.getElement();
    }

    @Override
    public E set(Position<E> p, E element) {
        E temp = validate(p).getElement();
        validate(p).setElement(element);
        return temp;
    }

    @Override
    public int size() {
        return size;
    }
    
    /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    /**
     * Adds a new node to the linked list containing the given element, and placed
     * between the two given nodes
     * @param element element
     * @param next next 
     * @param prev previous
     * @return the position of the added node
     */
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
        PositionalNode<E> newNode = new PositionalNode<E>(element, next, prev);
        next.setPrevious(newNode);
        prev.setNext(newNode);
        size++;
        return newNode;
    }
    
    /**
     * PositionalNode allows for the functionality of a ListNode but for
     * a doubly-linked list
     * @author Ethan Treece
     *
     * @param <E> generic element type stored in the node
     */
    private static class PositionalNode<E> implements Position<E> {

        /** Generic type element */
        private E element;
        
        /** Next node */
        private PositionalNode<E> next;
        
        /** Previous node */
        private PositionalNode<E> previous;

        /**
         * Constructs the node with a given generic value
         * @param value value
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * Constructs the node with a given generic value, and
         * a next node
         * @param value value
         * @param next next
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * Constructs the node with a given generic value, a
         * next node, and a previous node
         * @param value value
         * @param next next
         * @param prev previous
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * Sets the previous node to the given node
         * @param prev previous
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Gets the previous node
         * @return previous node
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Sets the next node as the given node
         * @param next next
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * Gets the next node
         * @return next node
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the element for the node as the given generic element
         * @param element element
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * PositionIterator allows for the traversal of the list
     * through iterating it's position
     * @author Ethan Treece
     *
     */
    private class PositionIterator implements Iterator<Position<E>> {

        /** Current position */
        private Position<E> current;
        
        /** True if the item can be removed, false if not */
        private boolean removeOK;

        /**
         * Constructor for the PositionalIterator
         */
        public PositionIterator() {
            current = front;
            removeOK = false;
        }

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            } else if (current == front) {
                current = front.getNext();
            }
            return current.getElement() != null;
        }

        @Override
        public Position<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Position<E> temp = current;
            current = validate(current).getNext();
            removeOK = true;
            return temp;
        }

        @Override
        public void remove() {
            // You should consider delegating to
            // the outer class's remove(position) method,
            // similar to:
            // PositionalLinkedList.this.remove(position);
            if (removeOK) {
                PositionalLinkedList.this.remove(validate(current).getPrevious());
                removeOK = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }
    
    /**
     * ElementIterator allows for the traversal of the list
     * through it's elements
     * @author Ethan Treece
     *
     */
    private class ElementIterator implements Iterator<E> {

        /** Iterator */
        private Iterator<Position<E>> it;

        /**
         * Constructor for the ElementIterator
         */
        public ElementIterator() {
            it = new PositionIterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }

}