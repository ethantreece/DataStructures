package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Ethan Treece
 * 
 * @param <E> the generic type of data to sort
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructor for InsertionSorter with no parameters
     */
    public InsertionSorter() {
        this(null);
    }

    /**
     * Constructor for InsertionSorter with a comparator as a parameter
     * 
     * @param comparator comparator
     */
    public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public void sort(E[] e) {
        for (int i = 1; i <= e.length - 1; i++) {
            E x = e[i];
            int j = i - 1;
            while (j >= 0 && super.compare(e[j], x) > 0) {
                e[j + 1] = e[j];
                j = j - 1;
            }
            e[j + 1] = x;
        }
    }

}
