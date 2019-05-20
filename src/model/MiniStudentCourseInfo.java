package model;

import java.io.Serializable;
import java.util.Random;

public class MiniStudentCourseInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String courseNumber;
	public double credits;

	public enum CourseStatus {
		Taken, Taking, ToTake;
		public static CourseStatus getRandomStatus() {
			Random random = new Random();
			return values()[random.nextInt(values().length - 1)];
		}
	}

	public enum LetterGrade {
		A(4.0,"A"), B_PLUS(3.5,"B+"), B(3.0,"B"), C_PLUS(2.5,"C+"), C(2.0,"C"), D_PLUS(1.5,"D+"), D(1.0,"D"), F(0,"F");
		public static LetterGrade getRandomGrade() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}

		private final double value;
		private final String name;
		
		private LetterGrade(double value,String name) {
			this.value = value;
			this.name=name;
		}

		public double getValue() {
			return value;
		}
		public String getName() {
			return name;
		}

	}

	public LetterGrade letterGrade;
	public CourseStatus courseStatus;
	public String classroom;

	public MiniStudentCourseInfo(String courseNumber, double credits, String classroom) {
		this.courseNumber = courseNumber;
		this.credits = credits;
		this.classroom = classroom;
	}

	public MiniStudentCourseInfo(CourseBag courseBag) {
		Course course = courseBag.emitCourse();
		this.courseNumber = course.getCourseNumber();
		this.credits = course.getCredits();
		this.classroom = course.getClassroom();
	}

	@Override
	public String toString() {
		return  courseNumber + ", Credits:" + credits + ", Grade:"
				+ letterGrade.name + ", GPA: " + letterGrade.value + ", Status:" + courseStatus + ", Room: "
				+ classroom;
	}

}
