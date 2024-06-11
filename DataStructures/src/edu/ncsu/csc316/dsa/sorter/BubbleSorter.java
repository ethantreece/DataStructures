package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter uses the bubble sort algorithm to sort data
 * 
 * @author Ethan Treece
 *
 * @param <E> the generic type of data to sort
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
    /**
     * Constructor for BubbleSorter with no parameters
     */
    public BubbleSorter() {
        this(null);
    }

    /**
     * Constructor for BubbleSorter with the comparator parameter
     * @param comparator comparator
     */
    public BubbleSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public void sort(E[] e) {
        boolean r = true;
        while (r) {
            r = false;
            for (int i = 1; i < e.length; i++) {
                if (super.compare(e[i], e[i - 1]) < 0) {
                    E x = e[i - 1];
                    e[i - 1] = e[i];
                    e[i] = x;
                    r = true;
                }
            }
        }
    }

}
