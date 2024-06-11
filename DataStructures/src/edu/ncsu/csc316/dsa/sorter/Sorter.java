package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author Ethan Treece
 * 
 * @param <E> the generic type of data to sort
 */
public interface Sorter<E> {
	
	/**
	 * Sorts an array of elements.
	 * @param e element array
	 */
	void sort(E[] e);
}
