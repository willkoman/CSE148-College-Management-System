package utils;

import java.util.Random;

import model.Faculty;
import model.Faculty.Title;
import model.MiniFacultyCourseBag;
import model.Student.Major;

public class FacultyFactory {

	private Random rand = new Random();

	public Faculty generateFaculty(String firstname, String lastname, String phoneNumber, MiniFacultyCourseBag temp) {
		Faculty f = new Faculty(firstname, lastname, Title.getRandomTitle(), (rand.nextDouble() * 40000.00)+60000.00, Major.getRandomMajor(), phoneNumber, temp);
		return f;
	}
}
