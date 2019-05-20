package model;

import java.io.Serializable;

import utils.IdFactory;

public abstract class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Person [getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getId()=" + getId()
				+ "]";
	}

	private String firstName;
	private String lastName;
	private String id;
	private static int idCounter = 0;
	private IdFactory ids = new IdFactory();

	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = ids.generateID(String.valueOf(idCounter++));
	}
	public void setId(int idcount) {
		this.id = ids.generateID(String.valueOf(idcount));
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return getFirstName()+" "+getLastName();
	}
}
