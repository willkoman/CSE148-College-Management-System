package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.ClassroomBag;
import model.CourseBag;
import model.PersonBag;
import model.TextbookBag;

public class Exporter {
	ClassroomBag classbag;
	CourseBag coursebag;
	PersonBag pb;
	TextbookBag tb;

	public Exporter(ClassroomBag classbag, CourseBag coursebag, PersonBag pb, TextbookBag tb) {
		this.classbag = classbag;
		this.coursebag = coursebag;
		this.pb = pb;
		this.tb = tb;
	}

	public void write() throws IOException {
		FileOutputStream fos = new FileOutputStream("bin/output_data/bags.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		List<Object> dataToSave = new ArrayList<Object>();
		dataToSave.add(classbag);
		dataToSave.add(coursebag);
		dataToSave.add(pb);
		dataToSave.add(tb);
		oos.writeObject(dataToSave);
		oos.close();
	}
}
