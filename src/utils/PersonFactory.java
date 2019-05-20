package utils;

import java.util.Random;

import model.Course;
import model.CourseBag;
import model.Faculty;
import model.MiniFacultyCourseBag;
import model.MiniFacultyCourseInfo;
import model.MiniStudentCourseBag;
import model.MiniStudentCourseInfo;
import model.MiniStudentCourseInfo.CourseStatus;
import model.MiniFacultyCourseInfo.TeachStatus;
import model.MiniStudentCourseInfo.LetterGrade;
import model.PersonBag;
import model.Student;
import model.Student.Major;

public class PersonFactory {
	NameFactory nf;
	StudentFactory sf = new StudentFactory();
	FacultyFactory ff = new FacultyFactory();
	PhoneFactory pf = new PhoneFactory();
	CourseBag cb;
	PersonBag pb;
	Random rand = new Random();

	public PersonFactory(PersonBag pb, NameFactory nf, CourseBag cb) {
		this.nf = nf;
		this.pb = pb;
		this.cb = cb;
	}

	public Student generateStudent(int amountOfClasses) {

		MiniStudentCourseBag temp = new MiniStudentCourseBag(amountOfClasses);
		for (int i = 0; i < amountOfClasses; i++) {
			Course t = cb.emitCourse();
			MiniStudentCourseInfo msi = new MiniStudentCourseInfo(t.getCourseNumber(), t.getCredits(),
					t.getClassroom());
			msi.courseStatus = CourseStatus.getRandomStatus();
			msi.letterGrade = LetterGrade.getRandomGrade();
			temp.insert(msi);
		}
		return sf.generateStudent(nf.emitFirstName(), nf.emitLastName(), Major.getRandomMajor(), temp,
				pf.generatePhoneNumber());
	}

	public Faculty generateFaculty(int amountOfClasses) {
		MiniFacultyCourseBag temp = new MiniFacultyCourseBag(amountOfClasses);
		for (int i = 0; i < amountOfClasses; i++) {
			Course t = cb.emitCourse();
			MiniFacultyCourseInfo msi = new MiniFacultyCourseInfo(t.getCourseNumber(), t.getCourseTitle(),
					t.getClassroom());
			msi.courseStatus = TeachStatus.getRandomStatus();
			temp.insert(msi);
		}
		return ff.generateFaculty(nf.emitFirstName(), nf.emitLastName(), pf.generatePhoneNumber(), temp);
	}

	public void insertStudents(int amt) {
		for (int i = 0; i < amt; i++)
			pb.insert(generateStudent(rand.nextInt(8) + 1));

	}

	public void insertFaculty(int amt) {
		for (int i = 0; i < amt; i++)
			pb.insert(generateFaculty(rand.nextInt(8) + 1));
	}
}
