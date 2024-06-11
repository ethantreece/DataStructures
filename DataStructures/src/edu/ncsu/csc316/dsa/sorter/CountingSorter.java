package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

    @Override
    public void sort(E[] e) {
        // Find the min and the max elements in the input data
        int min = e[0].getId();
        int max = e[0].getId();
        for (int i = 0; i < e.length; i++) {
            min = Math.min(e[i].getId(), min);
            max = Math.max(e[i].getId(), max);
        }
        
        // Calculate the range of the elements
        int k = max - min + 1;
        int[] b = new int[k];
        for (int i = 0; i < e.length; i++) {
            b[e[i].getId() - min] = b[e[i].getId() - min] + 1;
        }
        for (int i = 1; i < k; i++) {
            b[i] = b[i - 1] + b[i];
        }
        
        @SuppressWarnings("unchecked")
        E[] f = (E[])(new Identifiable[e.length]);
        for (int i = e.length - 1; i >= 0; i--) {
            f[b[e[i].getId() - min] - 1] = e[i];
            b[e[i].getId() - min] = b[e[i].getId() - min] - 1;
        }
        
        for (int i = 0; i < e.length; i++) {
            e[i] = f[i];
        }
    }
    
}
