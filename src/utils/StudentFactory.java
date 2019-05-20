package utils;

import model.MiniStudentCourseBag;
import model.Student;
import model.Student.Major;

public class StudentFactory {
	private String firstname, lastname;
	private Major major;

	public Student generateStudent(String firstname, String lastname, Major major,MiniStudentCourseBag miniStudentCourseBag,String phone) {
		Student s = new Student(firstname, lastname, major,miniStudentCourseBag,phone);
		return s;
	}
	
}
