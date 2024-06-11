package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * AbstractComparisonSorter implements a wrapper method compare(), to
 * compare two elements using the provided comparator type.
 * @author Ethan Treece
 *
 * @param <E> the generic type of data to sort
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** Comparator */
	private Comparator<E> comparator;
    
	/**
	 * Constructor for AbstractComparisonSorter with the comparator parameter
	 * @param comparator comparator
	 */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Sets the comparator to the one given as a parameter. If the parameter is
     * null, than comparator is set to the NaturalOrder comparator.
     * 
     * @param comparator comparator
     */
    private void setComparator(Comparator<E> comparator) {
        if ( comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
     * If the client attempts to sort without specifying a Comparator, this is the
     * fall-back natural ordering of objects.
     * 
     * @author Ethan Treece
     *
     */
    private class NaturalOrder implements Comparator<E> {

        /**
         * Constructor for the NaturalOrder comparator
         */
        private NaturalOrder() {
        }

        /**
         * Uses the compareTo method to compare with natural ordering
         * 
         * @param first  first object
         * @param second second object
         * @return less than zero if first should be before second,
         */
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }

    }
    
    /**
     * Uses the compare function for the given comparator
     * @param data1 data one
     * @param data2 data two
     * @return less than zero if first should be before second,
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}
