package model;

import java.io.Serializable;
import java.util.Random;

import model.Student.Major;

public class Faculty extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Faculty " + getFirstName() + " " + getLastName() + " ID: " + getId()
				+ " Title: " + title + " Salary: $" + String.format("%,.2f",salary) + " Department: " + department + " Office Phone: "
				+ officePhone ;
	}
	public MiniFacultyCourseBag miniFacultyCourseBag;
	public Title title;
	public double salary;
	public Major department;
	public String officePhone;

	public Faculty(String firstName, String lastName, Title title, double salary, Major department,
			String officePhone,MiniFacultyCourseBag miniFacultyCourseBag,int id) {
		super(firstName, lastName);
		this.title = title;
		this.salary = salary;
		this.setId(id);
		this.miniFacultyCourseBag=miniFacultyCourseBag;
		this.department = department;
		this.officePhone = officePhone;
	}
	public Faculty(String firstName, String lastName, Title title, double salary, Major department,
			String officePhone,MiniFacultyCourseBag miniFacultyCourseBag) {
		super(firstName, lastName);
		this.title = title;
		this.salary = salary;
		this.miniFacultyCourseBag=miniFacultyCourseBag;
		this.department = department;
		this.officePhone = officePhone;
	}
	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Major getDepartment() {
		return department;
	}

	public void setDepartment(Major department) {
		this.department = department;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public enum Title {
		Prof("Professor"), Associate_prof("Associate Professor"), Assistant_prof("Assistant Professor"), Instructor("Instructor");
		public static Title getRandomTitle() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
		private String name;
		Title(String name) {
			this.name=name;
		}
		public String getName() {
			return name;
		}
	}



}
