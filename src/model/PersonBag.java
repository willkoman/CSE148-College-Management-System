package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import utils.IdFactory;

public class PersonBag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<Person> personArray;
	public int nElems;
	private Random rand = new Random();
	private IdFactory idf = new IdFactory();
	
	public PersonBag(int maxSize) {
		personArray = new ArrayList<Person>();
		nElems = personArray.size();
	}

	public void insert(Person person) {
		personArray.add(person);
	}


	public Person emitPerson() {
		return personArray.get(rand.nextInt(personArray.size()));
	}

	public Student emitStudent() {
		List<Student> temp = new ArrayList<Student>();
		for (Person s : personArray) {
			if (s.getClass().isAssignableFrom(Student.class))
				temp.add((Student) s);
		}
		return temp.get(rand.nextInt(temp.size()));
	}

	public Faculty emitFaculty() {
		List<Faculty> temp = new ArrayList<Faculty>();
		for (Person s : personArray) {
			if (s.getClass().isAssignableFrom(Faculty.class))
				temp.add((Faculty) s);
		}
		return temp.get(rand.nextInt(temp.size()));
	}

	public void deleteByName(String name) {
		for (Iterator<Person> iterator = personArray.iterator(); iterator.hasNext(); ) {
		    Person g = iterator.next();
			if (g.getName().equals(name)) {
				iterator.remove();
			}
		}

	}
	public void deleteByID(String id) {
		for (Iterator<Person> iterator = personArray.iterator(); iterator.hasNext(); ) {
		    Person g = iterator.next();
			if (g.getId().equals(idf.generateID(id))) {
				iterator.remove();
			}
		}

	}

	public Person findByID(String id) {
		for (Person g : personArray) {
			if (g.getId().equals(idf.generateID(id))) {
				return g;
			}
		}
		return null;
	}

	public Student findStudentByID(String id) {
		for (Person g : personArray) {
			if (g.getId().equals(idf.generateID(id)) && g.getClass().isAssignableFrom(Student.class)) {
				return (Student) g;
			}
		}
		return null;
	}

	public Faculty findFacultyByID(String id) {
		for (Person g : personArray) {
			if (g.getId().equals(idf.generateID(id)) && g.getClass().isAssignableFrom(Faculty.class)) {
				return (Faculty) g;
			}
		}
		return null;
	}

	public Person findByName(String name) {
		for (Person g : personArray) {
			if (g.getName().equals(name)) {
				return g;
			}
		}
		return null;
	}
	public Student findStudentByName(String name) {
		for (Person g : personArray) {
			if (g.getName().equals(name) && g.getClass().isAssignableFrom(Student.class)) {
				return (Student)g;
			}
		}
		return null;
	}
	public Faculty findFacultyByName(String name) {
		for (Person g : personArray) {
			if (g.getName().equals(name) && g.getClass().isAssignableFrom(Faculty.class)) {
				return (Faculty) g;
			}
		}
		return null;
	}

}
