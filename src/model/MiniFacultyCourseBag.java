package model;

import java.io.Serializable;
import java.util.Random;

public class MiniFacultyCourseBag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MiniFacultyCourseInfo[] miniFacultyCourseInfo;
	private int nElems;
	
	private Random rand = new Random();

	public MiniFacultyCourseBag(int maxSize) {
		miniFacultyCourseInfo = new MiniFacultyCourseInfo[maxSize];
		nElems = 0;
	}

	public void insert(MiniFacultyCourseInfo course) {
		miniFacultyCourseInfo[nElems++] = course;
	}

	public String[] getCourses() {
		String[] t = new String[nElems];
		for(int i=0;i<nElems;i++)
			t[i] = miniFacultyCourseInfo[i].toString();
		return t;
	}
	public MiniFacultyCourseInfo emitCourse() {
		return miniFacultyCourseInfo[rand.nextInt(nElems)];
	}

}
