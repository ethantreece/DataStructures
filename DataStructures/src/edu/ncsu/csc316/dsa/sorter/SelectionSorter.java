package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor for SelectionSorter with no parameters
	 */
	public SelectionSorter() {
		this(null);
	}
	
	/**
	 * Constructor for SelectionSorter with the comparator parameter
	 * @param comparator comparator
	 */
	public SelectionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	@Override
    public void sort(E[] e) {
        for (int i = 0; i <= e.length - 1; i++) {
        	int min = i;
        	for (int j = i + 1; j <= e.length - 1; j++) {
        		if (super.compare(e[j], e[min]) < 0) {
        			min = j;
        		}
        	}
        	if (i != min) {
        		E x = e[i];
        		e[i] = e[min];
        		e[min] = x;
        	}
        }
    }
}
