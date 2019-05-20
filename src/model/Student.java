package model;

import java.io.Serializable;
import java.util.Random;

public class Student extends Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Major {
		ACC, BUS, MKT, RET, AUT, CYB, COT, DRF, ELT, ENS, FPT, TYT, CHI, CIN, COM, DNC, DMA, ENG, FRE, GER, GRD, HUM,
		INT, ITL, JPN, LAT, MUS, MTR, PHL, ART, SPN, THR, WST, HIS, SOC, ASL, CDC, DTE, PAR, PFS, HSC, MED, HIT, HUS,
		NUR, OTA, PED, PTA, PNU, AST, BIO, CHE, ESC, ENV, MAR, MAT, MET, PHY, ANT, ECO, GEO, POL, PSY, COL, CSE, CRJ,
		CUL, EDU, ESL, HVA, HRM, CST, IND, LAW, LIB, MFT, POA, RTV, RDG, VST, NONE;
		public static Major getRandomMajor() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

	private double gpa;

	public double getGpa() {
		return gpa;
	}

	public MiniStudentCourseBag getMiniStudentCourseBag() {
		return miniStudentCourseBag;
	}

	public Major getMajor() {
		return major;
	}
	public void setMajor(Major major) {
		this.major=major;
	}
	public MiniStudentCourseBag miniStudentCourseBag;
	private Major major;
	public String phone;

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	public Student(String firstName, String lastName, Major major, MiniStudentCourseBag miniStudentCourseBag,
			String phone) {
		super(firstName, lastName);
		this.major = major;
		this.phone = phone;
		this.miniStudentCourseBag = miniStudentCourseBag;
		double v = 0;
		for (MiniStudentCourseInfo i : miniStudentCourseBag.miniStudentInfo) {
			v+=i.credits;
			gpa += (i.letterGrade.getValue()*i.credits);
		}
		gpa /= v;
	}

	public Student(String firstName, String lastName, Major major, MiniStudentCourseBag miniStudentCourseBag,
			String phone, int id) {
		super(firstName, lastName);
		this.major = major;
		this.setId(id);
		this.phone = phone;
		this.miniStudentCourseBag = miniStudentCourseBag;
		double v = 0;
		for (MiniStudentCourseInfo i : miniStudentCourseBag.miniStudentInfo) {
			v++;
			gpa += i.letterGrade.getValue();

		}
		gpa /= v;
	}

	@Override
	public String toString() {
		return "Student " + getFirstName() + " " + getLastName() + " ID: " + getId() + " Major: " + major + " GPA: "
				+ String.format("%.2f", gpa);
	}

}
