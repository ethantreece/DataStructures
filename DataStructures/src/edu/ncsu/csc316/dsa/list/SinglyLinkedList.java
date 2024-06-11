package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        if (size == 0) {
            front.setElement(element);
            tail = new LinkedListNode<E>(element);
        } else if (index == 0) {
            LinkedListNode<E> temp = front;
            front = new LinkedListNode<E>(element, front);
            if (size == 1) {
                tail = temp;
            }
        } else if (index == size) {
            if (size == 1) {
                tail = new LinkedListNode<E>(element);
                front.setNext(tail);
            } else {
                tail.setNext(new LinkedListNode<E>(element));
                tail = tail.getNext();
            }
        } else {
            LinkedListNode<E> temp = front;
            while (index > 1) {
                temp = temp.getNext();
                index--;
            }
            temp.setNext(new LinkedListNode<E>(element, temp.getNext()));
        }
        size++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        LinkedListNode<E> temp = front;
        while (index > 0) {
            temp = temp.getNext();
            index--;
        }
        return temp.getElement();
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removed = get(index);
        LinkedListNode<E> temp = front;
        if (index == 0) {
            front = front.getNext();
        } else {
            while (index > 1) {
                temp = temp.getNext();
                index--;
            }
            temp.setNext(temp.getNext().getNext());
        }
        size--;
        return removed;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E removed = get(index);
        LinkedListNode<E> temp = front;
        while (index > 0) {
            temp = temp.getNext();
            index--;
        }
        temp.setElement(element);
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    /**
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.getElement();
    }

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
        if (size == 0) {
            front.setElement(element);
            tail = new LinkedListNode<E>(element);
        } else if (size == 1) {
            tail = new LinkedListNode<E>(element);
            front.setNext(tail);
        } else {
            tail.setNext(new LinkedListNode<E>(element));
            tail = tail.getNext();
        }
        size++;
    }
    
    /**
     * LinkedListNode allows for traversal, modifications, and additions
     * to the singly linked list
     * @author Ethan Treece
     *
     * @param <E> Generic data type E that's stored in the node
     */
    private static class LinkedListNode<E> {
        
        /** Generic E type data */
        private E data;
        
        /** References the next node in the linked list */
        private LinkedListNode<E> next;
        
        /**
         * Constructs a node with a data parameter,
         * next is set to null
         * @param data data
         */
        public LinkedListNode(E data) {
            this(data, null);
        }
        
        /**
         * Constructs a node with a data and next parameter
         * @param data
         * @param next
         */
        public LinkedListNode(E data, LinkedListNode<E> next) {
            this.data = data;
            this.next = next;
        }
        
        /**
         * Gets the next node in the linked list
         * @return next node
         */
        public LinkedListNode<E> getNext() {
            return next;
        }
        
        /**
         * Returns the generic element, data, in the node
         * @return element data
         */
        public E getElement() {
            return data;
        }
        
        /**
         * Sets the next node as the given node
         * @param next next
         */
        public void setNext(LinkedListNode<E> next) {
            this.next = next;
        }
        
        /**
         * Sets the current nodes data as the given data
         * @param data data
         */
        public void setElement(E data) {
            this.data = data;
        }
        
    }
    
    /**
     * ElementIterator gives the capability to iterate through the
     * elements of the ArrayBasedList. Lets you go to the next element,
     * check if there is a next element, and remove the current element.
     * @author Ethan Treece
     *
     */
    private class ElementIterator implements Iterator<E> {
        
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        /** 
         * Keep track of the node that was processed on the last call to 'next'
         */
        private LinkedListNode<E> previous;
        
        /** 
         * Keep track of the previous-previous node that was processed
         * so that we can update 'next' links when removing
         */
        private LinkedListNode<E> previousPrevious;
        
        /**
         * Keep track of whether it's ok to remove an element (based on whether
         * next() has been called immediately before remove())
         */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            current = front;
            previous = null;
            previousPrevious = null;
            removeOK = false;
        }

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E temp = current.getElement();
            previousPrevious = previous;
            previous = current;
            current = current.getNext();
            removeOK = true;
            return temp;
        }
         
        @Override    
        public void remove() {
            if (removeOK && previous != null) {
                if (previousPrevious == null) {
                    front = current;
                } else {
                    previousPrevious.setNext(current);
                    previous = previousPrevious;
                    if (current == null) {
                        tail = previous;
                    }
                }
                size--;
                removeOK = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }
    
}