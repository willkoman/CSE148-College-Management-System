package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CourseBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Course> courseArray;

	private Random rand = new Random();

	public CourseBag(int maxSize) {
		courseArray = new ArrayList<Course>(maxSize);
	}

	public void insert(Course course) {
		courseArray.add(course);
	}


	public void deleteByCourseID(String courseID) {
		for (Iterator<Course> iterator = courseArray.iterator(); iterator.hasNext(); ) {
			Course g = iterator.next();
			if (g.getCourseNumber().equals(courseID))
				iterator.remove();
		}
	}
	public Course findByCourseID(String courseID) {
		for (Course g : courseArray) {
			if (g.getCourseNumber().equals(courseID))
				return g;
		}
		return null;
	}
	
	public Course emitCourse() {
		return courseArray.get(rand.nextInt(courseArray.size()));
	}

}