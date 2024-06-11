package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 * @author Ethan Treece
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

    @Override
    public void sort(E[] e) {
        // Find largest value in the input data
        int k = 0;
        for (int i = 0; i < e.length; i++) {
            k = Math.max(k, e[i].getId());
        }
        // Determine how many digits are in the largest value
        double x = Math.ceil( Math.log(k + 1) / Math.log(10));
        
        int p = 1;
        for (int j = 1; j <= x; j++) {
            int[] b = new int[10];
            for (int i = 0; i <= e.length - 1; i++) {
                b[ (e[i].getId() / p) % 10] = b[ (e[i].getId() / p) % 10] + 1;
            }
            for (int i = 1; i <= 9; i++) {
                b[i] = b[i - 1] + b[i];
            }
            @SuppressWarnings("unchecked")
            E[] f = (E[])(new Identifiable[e.length]);
            for (int i = e.length - 1; i >= 0; i--) {
                f[ b[ (e[i].getId() / p) % 10] - 1] = e[i];
                b[ (e[i].getId() / p) % 10] = b[ (e[i].getId() / p) % 10] - 1;
            }
            for (int i = 0; i <= e.length - 1; i++) {
                e[i] = f[i];
            }
            p = p * 10;
        }
    }
    
}
