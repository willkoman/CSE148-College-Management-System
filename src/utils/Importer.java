package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import model.ClassroomBag;
import model.CourseBag;
import model.PersonBag;
import model.TextbookBag;

public class Importer {
	FileInputStream fis;
	ObjectInputStream ois;
	List<Object> data;

	@SuppressWarnings("unchecked")
	public Importer() throws Exception {
		fis = new FileInputStream("bin/output_data/bags.dat");
		ois = new ObjectInputStream(fis);
		data = (ArrayList<Object>) ois.readObject();

	}

	public ClassroomBag importClassroomBag() throws ClassNotFoundException, IOException {
		return (ClassroomBag) data.get(0);
	}

	public CourseBag importCourseBag() throws ClassNotFoundException, IOException {
		return (CourseBag) data.get(1);
	}

	public PersonBag importPersonBag() throws ClassNotFoundException, IOException {
		return (PersonBag) data.get(2);
	}

	public TextbookBag importTextbookBag() throws ClassNotFoundException, IOException {
		return (TextbookBag) data.get(3);
	}

	public void close() throws IOException {
		ois.close();
	}
}
