package model;

import java.io.Serializable;
import java.util.Random;

public class MiniFacultyCourseInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String courseNumber;
	String courseTitle;
	String classroom;
	public enum TeachStatus {
		Taught, Teaching;
		public static TeachStatus getRandomStatus() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

	public TeachStatus courseStatus;

	public MiniFacultyCourseInfo(String courseNumber, String courseTitle,String classroom) {
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		this.classroom = classroom;
	}

	public MiniFacultyCourseInfo(CourseBag courseBag) {
		Course course = courseBag.emitCourse();
		this.courseNumber = course.getCourseNumber();
		this.courseTitle = course.getCourseTitle();
		this.classroom = course.getClassroom();
	}

	@Override
	public String toString() {
		return  courseNumber + ", " + courseTitle
				+ ", Status:" + courseStatus+", Room:"+classroom;
	}
}
