package edu.ncsu.csc316.dsa.data;

import java.util.Objects;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Ethan Treece
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	
	/** Students' first name */
	private String first;
	
	/** Students' last name */
	private String last;
	
	/** Students' id */
	private int id;
	
	/** Students' number of credit hours */
	private int creditHours;
	
	/** Students' gpa */
	private double gpa;
	
	/** Students' unity id */
	private String unityID;
	
	/**
	 * Constructs the Student
	 * @param first first name
	 * @param last last name
	 * @param id id
	 * @param creditHours number of credit hours
	 * @param gpa gpa
	 * @param unityID unity id
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}
	
	

	/**
	 * Gets the students' first name
	 * @return students' first name
	 */
	public String getFirst() {
		return first;
	}





	/**
	 * Sets the students' first name
	 * @param first students' first name
	 */
	public void setFirst(String first) {
		this.first = first;
	}





	/**
	 * Gets the students' last name
	 * @return students' last name
	 */
	public String getLast() {
		return last;
	}





	/**
	 * Sets the students' last name
	 * @param last students' last name
	 */
	public void setLast(String last) {
		this.last = last;
	}





	/**
	 * Gets the students' id
	 * @return students' id
	 */
	public int getId() {
		return id;
	}





	/**
	 * Sets the students' id
	 * @param id students' id
	 */
	public void setId(int id) {
		this.id = id;
	}





	/**
	 * Gets the students' number of credit hours
	 * @return students' creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}





	/**
	 * Sets the students' number of credit hours
	 * @param creditHours students' number of credit hours
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}





	/**
	 * Gets the students' gpa
	 * @return students' gpa
	 */
	public double getGpa() {
		return gpa;
	}





	/**
	 * Sets the students' gpa
	 * @param gpa students' gpa
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}





	/**
	 * Gets the students' unity id
	 * @return students' unityID
	 */
	public String getUnityID() {
		return unityID;
	}





	/**
	 * Sets the students' unity id
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}





	@Override
	public int compareTo(Student o) {
		if (last.equals(o.getLast()) && first.equals(o.getFirst())) {
			return unityID.compareTo(o.getUnityID());
		} else if (last.equals(o.getLast())) {
			return first.compareTo(o.getFirst());
		} else {
			return last.compareTo(o.getLast());
		}
	}



	@Override
	public int hashCode() {
		return Objects.hash(creditHours, first, gpa, id, last, unityID);
	}



	@Override
	public boolean equals(Object obj) {
		Student s = (Student) obj;
		return first.equals(s.getFirst()) && last.equals(s.getLast()) && id == s.getId();
	}



	@Override
	public String toString() {
		return first + " " + last + "," + id + "," + creditHours + "," + gpa + "," + unityID;
	}
	
	
	
}
