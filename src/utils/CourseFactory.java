package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.ClassroomBag;
import model.Course;
import model.CourseBag;

public class CourseFactory {
	private CourseBag courseBag;
	private ClassroomBag cb;
	private Scanner scanner;
	private List<Course> courses = new ArrayList<Course>();
	public CourseFactory(CourseBag courseBag,ClassroomBag cb) {
		this.courseBag = courseBag;
		this.cb = cb;
		init();
	}

	private void init() {
		try {
			importData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void importData() throws FileNotFoundException {
		File file = new File("bin/input_data/Course_Inventory.txt");
		scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			scanner.nextLine();
			String numberStr = scanner.nextLine();
			String titleStr = scanner.nextLine();
			String description = scanner.nextLine();
			String creditStr = scanner.nextLine();
			Course course = new Course(numberStr, titleStr, description, Double.parseDouble(creditStr),cb.emitClassroom().getRoomNumber());
			courses.add(course);	
		}
		scanner.close();
	}
	public void importCourses(int amt) {
		for(int i=0;i<amt;i++) {
			courseBag.insert(courses.get(i));
			
		}
	}

}
