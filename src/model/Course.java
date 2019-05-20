package model;

import java.io.Serializable;

public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseNumber;
	private String courseTitle;
	private String courseDescription;
	private double numberOfCredits;
	private String classroom;
	public Course(String courseNumber, String courseTitle, String courseDescription, double credits,String classroom) {
		super();
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.courseDescription = courseDescription;
		this.numberOfCredits = credits;
		this.classroom=classroom;
	}

	public String getClassroom() {
		return classroom;
	}

	@Override
	public String toString() {
		return courseNumber + ", " + courseTitle + ", "
				+ courseDescription + ", Credits:" + numberOfCredits + ", Classroom:" + classroom;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public double getCredits() {
		return numberOfCredits;
	}
	public String getCourseTitle() {
		return courseTitle;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(double numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

}