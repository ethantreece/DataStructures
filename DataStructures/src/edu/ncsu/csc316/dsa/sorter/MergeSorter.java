package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

    @Override
    public void sort(E[] e) {
        int n = e.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        E[] left = Arrays.copyOfRange(e, 0, mid);
        E[] right = Arrays.copyOfRange(e, mid, n);
        
        sort(left);
        sort(right);
        
        merge(left, right, e);
    }
    
    /**
     * Merges the left and right arrays in sorted order into the e array
     * @param left left array
     * @param right right array
     * @param e e array
     */
    private void merge(E[] left, E[] right, E[] e) {
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex + rightIndex < e.length) {
            if (rightIndex == right.length || ( leftIndex < left.length && super.compare(left[leftIndex], right[rightIndex]) < 0)) {
                e[leftIndex + rightIndex] = left[leftIndex];
                leftIndex++;
            } else {
                e[leftIndex + rightIndex] = right[rightIndex];
                rightIndex++;
            }
        }
    }

}
