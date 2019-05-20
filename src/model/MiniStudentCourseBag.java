package model;

import java.io.Serializable;
import java.util.Random;

public class MiniStudentCourseBag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MiniStudentCourseInfo[] miniStudentInfo;
	private int nElems;

	private Random rand = new Random();

	public MiniStudentCourseBag(int maxSize) {
		miniStudentInfo = new MiniStudentCourseInfo[maxSize];
		nElems = 0;
	}

	public void insert(MiniStudentCourseInfo course) {
		miniStudentInfo[nElems++] = course;
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniStudentInfo[i].toString());
		}
	}
	
	public String[] getCourses() {
		String[] t = new String[nElems];
		for(int i=0;i<nElems;i++)
			t[i] = miniStudentInfo[i].toString();
		return t;
	}
	public MiniStudentCourseInfo emitCourse() {
		return miniStudentInfo[rand.nextInt(nElems)];
	}

}