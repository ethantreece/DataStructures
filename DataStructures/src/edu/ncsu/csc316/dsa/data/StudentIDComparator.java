package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order
	 */
	@Override
	public int compare(Student one, Student two) {
		int oneId = one.getId();
		int twoId = two.getId();
		if (oneId > twoId) {
			return 1;
		} else if (oneId < twoId) {
			return -1;
		} else {
			return 0;
		}
	}

}
